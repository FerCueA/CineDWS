package es.dsw.models;

public class PeliculasBD {

	private int id;
	private String titulo;
	private String sinopsis;
	private String director;
	private String reparto;
	private int anio;
	private String fechaEstreno;


	public PeliculasBD() {
	}


	public PeliculasBD(int id, String titulo, String sinopsis, String director, String reparto, int anio,
			String fechaEstreno) {
		this.id = id;
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.director = director;
		this.reparto = reparto;
		this.anio = anio;
		this.fechaEstreno = fechaEstreno;
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = reparto;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
}
