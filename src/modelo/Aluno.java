package modelo;

public class Aluno extends Usuario {
	
	private String curso;

	public Aluno(String nome, String email, String senha, String curso) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.curso = curso;
		this.prazo = 20;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public String getCurso() {
		return curso;
	}
	
}
