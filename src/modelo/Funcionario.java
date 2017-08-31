package modelo;

public class Funcionario extends Usuario {
	
    private String departamento;

    public Funcionario(String nome, String email, String senha, String departamento) {
            super();
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.departamento = departamento;
            this.prazo = 30;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
