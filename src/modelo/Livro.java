package modelo;

import java.util.ArrayList;

public class Livro {
	
	private String titulo;
	private int quantidade;
	private ArrayList<Autor> autores = new ArrayList<Autor>();
	private int qtdEmprestimos = 0;

	public Livro(String titulo, int quantidade) {
		super();
		this.titulo = titulo;
		this.quantidade = quantidade;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void upQtdEmprestimos() {
		qtdEmprestimos++;
	}
	
	public int getQtdEmprestimos() {
		return qtdEmprestimos;
	}
	
	public void adicionarAutor(Autor autor) {
		autores.add(autor);
		if( autor.localizarLivro(this.getTitulo()) == null )
			autor.addLivro(this);
	}
	
	public void removerAutor(Autor autor) {
		autores.remove(autor);
		autor.removerLivro(this);
	}
	
	public ArrayList<Autor> getAutores() {
		return autores;
	}
	
	public Autor localizarAutor(String nome) {
		for(Autor a : autores) {
			if(a.getNome().equals(nome))
				return a;
		}
		return null;
	}
	
}
