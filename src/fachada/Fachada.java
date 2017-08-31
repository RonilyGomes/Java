package fachada;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;

import modelo.*;

public class Fachada{
	
    private static Repositorio biblioteca = new Repositorio();
    private static int idEmprestimo = 1;

    private static Usuario logado = null;

    /* --------------- */


    public static Livro cadastrarLivro(String titulo, int qtd, String ... autores) throws Exception {
            // Verifica se o usu�rio est� logado
            /*if( logado == null )
                    throw new Exception("Voc� precisa estar logado!");
            if( !(logado instanceof Administrador) )
                    throw new Exception("Voc� n�o possui permiss�o suficiente!");*/

            // Se o titulo estive em branco ou vazio
            if( titulo.isEmpty() ) {
                throw new Exception("O título está vazio!");
            }
                    
            // Se a quantidade de livros for insuficiente para o cadastro
            if( qtd <= 0 ) {
                throw new Exception("A quantidade de " + qtd + " itens é inválida!");
            }

            // Pesquisa pelo livro
            Livro livro = biblioteca.localizarLivro(titulo);

            // Se o livro existir
            if( livro != null ) {
                throw new Exception("Livro já existe: " + titulo);
            }       

            // Cria um novo livro
            livro = new Livro(titulo, qtd);
            biblioteca.adicionarLivro(livro);

            for(String nome : autores){
                // Procura pelo autor
                Autor autor = biblioteca.localizarAutor(nome);

                // Se não existir
                if( autor == null ) {
                    autor = new Autor(nome);
                    biblioteca.adicionarAutor(autor);
                }

                // Se o livro já possuir o autor
                if( livroContemAutor(livro, nome) ) {
                    throw new Exception("O livro \"" + titulo + "\" j� possui o autor \"" + nome + "\"!");
                }
                        
                // Se o autor já possuir o livro
                if( autorContemLivro(autor, titulo) ) {
                    throw new Exception("O autor \"" + nome + "\" j� possui o livro \"" + titulo + "\"!");
                }

                // Define o autor no livro
                livro.adicionarAutor(autor);
            }

            return livro;
    }

    // função de sistema
    public static Autor cadastrarAutor(String nome) throws Exception {
            // Verifica se o usu�rio est� logado
            /*if( logado == null )
                    throw new Exception("Voc� precisa estar logado!");
            if( !(logado instanceof Administrador) )
                    throw new Exception("Voc� n�o possui permiss�o suficiente!");*/

            // Se o nome estiver vazio
            if( nome.isEmpty() ) {
                throw new Exception("O nome est� vazio!");
            }
                    
            // Pesquisar pelo autor
            Autor autor = biblioteca.localizarAutor(nome);

            // Se já existir
            if( autor != null ) {
                throw new Exception("O autor \"" + nome + "\" já existe!");
            }

            // Cria um novo autor
            autor = new Autor(nome);

            biblioteca.adicionarAutor(autor);

            return autor;
    }

    public static Usuario cadastrarUsuario(String tipo, String nome, String email, String senha, String opt) throws Exception {
            // Verifica se o usu�rio est� logado
            /*
            if( logado == null ) {
                throw new Exception("Você precisa estar logado!");
            }
                    
            if( !(logado instanceof Administrador) ) {
                throw new Exception("Você não possui permissão suficiente!");
            }
            */

            email = email.toLowerCase();

            // Se todos os valores estiverem vazios
            if( nome.isEmpty() || email.isEmpty() || senha.isEmpty() || opt.isEmpty() ) {
                throw new Exception("Informe todos os dados do usuário!");
            }

            // Pesquisa pelo usuario
            Usuario usuario = biblioteca.localizarUsuario(nome);

            // Se o usuário ja existi
            if( usuario != null ) {
                throw new Exception("Usuário já existe!");
            }

            // Pesquisa pelo email
            if( verificarEmail(email) ) {
                throw new Exception("Este e-mail já está sendo utilizado!");
            }

            // Cria um novo usuário
            switch( tipo ) {
                case "Administrador":
                    usuario = new Administrador(nome, email, senha, opt);
                    usuario.setPrazo(30);
                    break;
                case "Funcionario":
                    usuario = new Funcionario(nome, email, senha, opt);
                    usuario.setPrazo(30);
                    break;
                case "Aluno":
                    usuario = new Aluno(nome, email, senha, opt);
                    usuario.setPrazo(20);
                    break;
                default:
                    throw new Exception("Tipo de usuário inválido!");
            }
            biblioteca.adicionarUsuario(usuario);

            return usuario;
    }

    public static boolean excluirUsuario(String nome) throws Exception {
            // Verifica se o usu�rio est� logado
            /*
        if( logado == null ){
            throw new Exception("Você precisa estar logado!");
        }

        if( !(logado instanceof Administrador) ) {
            throw new Exception("Você não possui permissão suficiente!");
        }
        */

        // Procura usuario no repositorio
        Usuario usuario = biblioteca.localizarUsuario(nome);

        // Verifica se usuario existe
        if( usuario == null ) {
            throw new Exception("Usuario nao encontrado!");
        }

        // Verifica se � o usu�rio atual
        if( usuario.equals(logado) ) {
            throw new Exception("Voc� n�o pode se auto-excluir!");
        }

        // Verifica os empr�stimos
        for(Emprestimo e : usuario.getEmprestimos()) {
            if( !e.isDevolvido() ) {
                throw new Exception("Este usu�rio possui empr�stimos em aberto!");
            }
        }

        // Remove os empr�stimos
        for(Emprestimo e : usuario.getEmprestimos()) {
            biblioteca.removerEmprestimo(e);
        }

        biblioteca.removerUsuario(usuario);

        return true;
    }

    public static Emprestimo cadastrarEmprestimo(Livro livro) throws Exception {
            // Verifica se o usu�rio est� logado
            if( logado == null ) {
                throw new Exception("Você precisa estar logado!");
            }
                    
            // Se o livro for nulo
            if( livro == null ) {
                throw new Exception("O livro é inválido!");
            }

            // Verifica se tem ao menos um exemplar do livro
            if( livro.getQuantidade() < 1 ) {
                throw new Exception("O livro não tem exemplares disponíveis!");
            }

            // Verifica se há emprestimo
            for(Emprestimo e : logado.getEmprestimos()) {
                if(e.getLivro().equals(livro) && !e.isDevolvido()) {
                    throw new Exception("Já existe um empréstimo do livro \"" + 
                            livro.getTitulo() + 
                            "\" para o usuário \"" + 
                            logado.getNome() + 
                            "\" em aberto!");
                }
                    
            }

            // Cria novo emprestimo
            Emprestimo emprestimo = new Emprestimo(logado, livro, dateToString(LocalDate.now()));
            emprestimo.setId(idEmprestimo);
            emprestimo.setDatadev(dateToString(LocalDate.now().plusDays(logado.getPrazo())));

            // Diminui a quantidade de exemplares do livro
            livro.setQuantidade( livro.getQuantidade() - 1 );
            livro.upQtdEmprestimos();

            idEmprestimo++;

            logado.adicionarEmprestimo(emprestimo);
            biblioteca.adicionarEmprestimo(emprestimo);

            return emprestimo;
    }


    public static Emprestimo devolverEmprestimo(int idemprestimo) throws Exception {
        // Verifica se o usuario esta logado
        if( logado == null ){
            throw new Exception("Voc� precisa estar logado!");
        }

        // Verifica o id do emprestimo
        if( idemprestimo <= 0 || idemprestimo > idEmprestimo ) {
            throw new Exception("O ID do empr�stimo � inv�lido!");
        }

        Emprestimo emp = biblioteca.localizarEmprestimo(idemprestimo);

        // Se nao houver empr�stimo
        if( emp == null ) {
            throw new Exception("Não há empréstimo com esse ID!");
        }

        // Verifica se � do usu�rio
        if( !emp.getUsuario().equals(logado) ) {
            throw new Exception("Este empréstimo não pertence ao usuário informado!");
        }

        // Verifica se j� foi devolvido
        if( emp.isDevolvido() ) {
            throw new Exception("Este empréstimo já foi devolvido!");
        }

        emp.setMulta(calcularMulta(dateToLocalDate(emp.getDatadev()), LocalDate.now()));
        emp.setDatadev(dateToString(LocalDate.now()));
        emp.setDevolvido(true);

        Livro livro = emp.getLivro();
        livro.setQuantidade( livro.getQuantidade() + 1 );

        return emp;
    }

    public static Livro localizarLivro( String titulo ) {
        for(Livro l : biblioteca.getLivros()) {
            if( l.getTitulo().toLowerCase().contains(titulo.toLowerCase()) ){
                return l;
            }
        }
        return null;
    }

    public static Collection<Livro> listarLivros() {
        return biblioteca.getLivros();
    }

    public static ArrayList<Emprestimo> listarEmprestimos() {
        return biblioteca.getEmprestimos();
    }

    public static ArrayList<Usuario> listarUsuarios() {
        return biblioteca.getUsuarios();
    }

    public static ArrayList<Emprestimo> listarEmprestimosUsuario() throws Exception {
        // Verifica se o usuario esta logado
        if( logado == null ){
            throw new Exception("Voc� precisa estar logado!");
        }

        return logado.getEmprestimos();
    }

    public static ArrayList<Livro> buscarLivroPorTitulo(String titulo) {
        ArrayList<Livro> livros = new ArrayList<>();
        
        for(Livro l : biblioteca.getLivros()) {
                if( l.getTitulo().toLowerCase().contains(titulo.toLowerCase()) ) {
                    livros.add(l);
                }
        }
        
        return livros;
    }

    public static ArrayList<Livro> buscarLivroPorAutor(String nome) {
        ArrayList<Livro> livros = new ArrayList<>();
        
        for(Autor a : biblioteca.getAutores()) {
            if( a.getNome().toLowerCase().contains(nome.toLowerCase()) ) {
                livros.removeAll(a.getLivros());
                livros.addAll(a.getLivros());
            }
        }
        
        return livros;
    }

    public static void login(String email, String senha) throws Exception {
        // Verifica se j� tem algu�m logado
        Usuario user = biblioteca.localizarUsuario(email, senha);
        
        if( logado != null ){
            throw new Exception("Já há alguém logado!");
        }
        
        if( user == null ) {
            throw new Exception("Não foi encontrado nenhum usu�rio com este email e senha.");
        }
        
        logado = user;
    }

    public static void logoff() {
            logado = null;
    }

    public static Usuario getLogado() {
            return logado;
    }

    /**
     * Formata uma data do formato LocalDate (2017-08-06) para String (6/8/2017).
     * 
     * @param data
     * @return String
     */
    private static String dateToString(LocalDate data) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");
            
            return data.format(formatter);
    }

    /**
     * Formatar uma data do formato String (6/8/2017) para LocalDate (2017-08-06).
     * 
     * @param data
     * @return LocalDate
     */
    private static LocalDate dateToLocalDate(String data) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");
            
            return LocalDate.parse(data, formatter);
    }

    /**
     * Calcula o valor da multa com base nas datas fornecidas.
     * 
     * @param datadev
     * @param dataatual
     * @return
     * @throws Exception
     */
    private static double calcularMulta(LocalDate datadev, LocalDate dataatual) throws Exception {
        // Verifica ambas datas
        if( datadev == null || dataatual == null ){
            throw new Exception("As datas são inválidas!");
        }

        Long days = ChronoUnit.DAYS.between(datadev, dataatual);

        return (days > 0) ? (1 * days) : 0;
    }

    /**
     * Verifica se o email ja esta cadastrado.
     * 
     * @param email
     * @return boolean
     */
    private static boolean verificarEmail(String email) {
        email = email.toLowerCase();
        for(Usuario u : biblioteca.getUsuarios()) {
            if(u.getEmail().equals(email)){
                return true;
            }       
        }
        return false;
    }

    /**
     * Verifica se um livro ja contem um autor com base no nome.
     * 
     * @param livro
     * @param autor
     * @return
     */
    private static boolean livroContemAutor(Livro livro, String autor) {
        for(Autor a : livro.getAutores()) {
            if( a.getNome().equals(autor) ) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Verifica se um autor ja contem um livro com base no titulo.
     * 
     * @param autor
     * @param livro
     * @return
     */
    private static boolean autorContemLivro(Autor autor, String titulo) {
        for(Livro l : autor.getLivros()) {
            if( l.getTitulo().equals(titulo) ){
                return true;
            }
        }
        
        return false;
    }
    
    public static void ordenarUsuario(ArrayList<Usuario> usuarios) {
        Collections.sort(usuarios);
    }
    
    public static Usuario buscarUsuario(String nome) {
        return biblioteca.localizarUsuario(nome);
    }
}
