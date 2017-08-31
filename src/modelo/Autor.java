package modelo;

import java.util.ArrayList;

public class Autor {
	
	private String nome;
	private ArrayList<Livro> livros = new ArrayList<Livro>();

	public Autor(String nome) {
		super();
		this.nome = nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void addLivro(Livro livro) {
		livros.add(livro);
		if( livro.localizarAutor(this.getNome()) == null )
			livro.adicionarAutor(this);
	}
	
	public void removerLivro(Livro livro) {
		livros.remove(livro);
		livro.removerAutor(this);
	}
	
	public ArrayList<Livro> getLivros() {
		return livros;
	}
	
	public Livro localizarLivro(String titulo) {
		for(Livro l : livros) {
			if(l.getTitulo().equals(titulo))
				return l;
		}
		return null;
	}

}
