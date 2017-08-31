package modelo;

import java.util.ArrayList;

public abstract class Usuario implements Comparable<Usuario>{
	
    protected String nome;
    protected String email;
    protected String senha;
    protected ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    protected int prazo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void adicionarEmprestimo(Emprestimo emp) {
        emprestimos.add(emp);
        emp.setUsuario(this);
    }

    public void removerEmprestimo(Emprestimo emp) {
        emprestimos.remove(emp);
        emp.setUsuario(null);
    }

    public Emprestimo localizarEmprestimo(int id) {
        for(Emprestimo e : emprestimos) {
            if(e.getId() == id && e.getUsuario().equals(this)) {
                return e;
            }
        }
        
        return null;
    }

    public int getTotalEmprestimos() {
        return emprestimos.size();
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public int getPrazo() {
        return prazo;
    }
   
    @Override
    public int compareTo(Usuario a) {
        return this.nome.compareToIgnoreCase(a.getNome());
    }
}
