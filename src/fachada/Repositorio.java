package fachada;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import modelo.*;

public class Repositorio {

    private TreeMap<String, Autor> autores = new TreeMap<>();
    private TreeMap<String, Livro> livros = new TreeMap<>();
    //private ArrayList<Autor> autores = new ArrayList<Autor>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    //private ArrayList<Livro> livros = new ArrayList<Livro>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    
    
    /**
     * Adicionar livro a lista.
     * 
     * @param livro
     */
    public void adicionarLivro(Livro livro) {
            livros.put(livro.getTitulo(), livro);
    }

    /**
     * Remover livro da lista.
     * 
     * @param livro
     */
    public void removerLivro(Livro livro) {
            livros.remove(livro.getTitulo());
    }

    /**
     * Pesquisa livro por titulo exatamente igual.
     * 
     * @param titulo
     * @return Livro
     */
    public Livro localizarLivro(String titulo) {
        return livros.get(titulo);
    }

    /**
     * Retorna todos os livros salvos.
     * 
     * @return Collection<Livro>
     */
    public Collection<Livro> getLivros() {
        return livros.values();
    }

    /**
     * Retorna a quantidade de livros.
     * 
     * @return int
     */
    public int getTotalLivros() {
            return livros.size();
    }


    /* --------------- */


    /**
     * Adiciona um autor a lista.
     * 
     * @param autor
     */
    public void adicionarAutor(Autor autor) {
            autores.put(autor.getNome(), autor);
    }

    /**
     * Remove um autor da lista.
     * 
     * @param autor
     */
    public void removerAutor(Autor autor) {
            autores.remove(autor.getNome());
    }

    /**
     * Pesquisa por autor pelo nome.
     * 
     * @param nome
     * @return Autor
     */
    public Autor localizarAutor(String nome) {
            return autores.get(nome);
    }

    /**
     * Retorna todos os autores salvos.
     * 
     * @return Collection<Autor>
     */
    public Collection<Autor> getAutores() {
            return autores.values();
    }

    /**
     * Retorna a quantidade de autores.
     * 
     * @return int
     */
    public int getTotalAutores() {
            return autores.size();
    }


    /* --------------- */


    /**
     * Adiciona usuario a lista.
     * 
     * @param user
     */
    public void adicionarUsuario(Usuario user) {
            usuarios.add(user);
    }

    /**
     * Remove usu�rio da lista.
     * 
     * @param user
     */
    public void removerUsuario(Usuario user) {
            usuarios.remove(user);
    }

    /**
     * Localiza usu�rio pelo nome.
     * 
     * @param nome
     * @return Usuario
     */
    public Usuario localizarUsuario(String nome) {
            for(Usuario u : usuarios) {
                    if(u.getNome().equals(nome))
                            return u;
            }
            return null;
    }

    /**
     * Localiza usu�rio com base no email e senha.
     * 
     * @param email
     * @param senha
     * @return
     */
    public Usuario localizarUsuario(String email, String senha) {
            for(Usuario user : usuarios) {
                    if( user.getEmail().equals(email) && user.getSenha().equals(senha) )
                            return user;
            }
            return null;
    }

    /**
     * Retorna todos os usu�rios salvos.
     * 
     * @return ArrayList<Usuario>
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Define a lista com os usu�rios.
     * 
     * @param usuarios
     */
    public void setUsuarios(ArrayList<Usuario> usuarios) {
            this.usuarios = usuarios;
    }

    /**
     * Retorna a quantidade de usu�rios.
     * 
     * @return int
     */
    public int getTotalUsuarios() {
            return usuarios.size();
    }


    /* --------------- */

    /**
     * Adiciona emprestimo a lista.
     * 
     * @param emp
     */
    public void adicionarEmprestimo(Emprestimo emp) {
            emprestimos.add(emp);
    }

    /**
     * Remove emprestimo da lista.
     * 
     * @param emp
     */
    public void removerEmprestimo(Emprestimo emp) {
            emprestimos.remove(emp);
    }

    /**
     * Localiza um emprestimo pelo id;
     * 
     * @param id
     * @return
     */
    public Emprestimo localizarEmprestimo(int id) {
            for(Emprestimo e : emprestimos) {
                    if(e.getId() == id)
                            return e;
            }
            return null;
    }

    /**
     * Retorna todos os emprestimos salvos.
     * 
     * @return
     */
    public ArrayList<Emprestimo> getEmprestimos() {
            return emprestimos;
    }

    /**
     * Define a lista com os emprestimos.
     * 
     * @param emprestimos
     */
    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
            this.emprestimos = emprestimos;
    }

    /**
     * Retorna a quantidade de emprestimos.
     * 
     * @return int
     */
    public int getTotalEmprestimos() {
            return emprestimos.size();
    }


    /* --------------- */
}
