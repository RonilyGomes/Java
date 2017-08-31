package modelo;

public class Emprestimo {
	
	private int id;
	private Usuario usuario;
	private Livro livro;
	private String dataemp;
	private String datadev;
	private double multa;
	private boolean devolvido = false;

	public Emprestimo(Usuario usuario, Livro livro, String dataemp) {
		super();
		this.usuario = usuario;
		this.livro = livro;
		this.dataemp = dataemp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getDataemp() {
		return dataemp;
	}

	public void setDataemp(String dataemp) {
		this.dataemp = dataemp;
	}

	public String getDatadev() {
		return datadev;
	}

	public void setDatadev(String datadev) {
		this.datadev = datadev;
	}

	public double getMulta() {
		return multa;
	}

	public void setMulta(double multa) {
		this.multa = multa;
	}

	public boolean isDevolvido() {
		return devolvido;
	}

	public void setDevolvido(boolean devolvido) {
		this.devolvido = devolvido;
	}

}
