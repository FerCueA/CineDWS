package es.dsw.models;

public class PeliculaSalaView {
    private int numSala;
    private int idSesion;
    private String tituloPelicula;
    private double precio;
    private int idPelicula;
    private String rutaImagen;

    public PeliculaSalaView(int numSala, int idSesion, String tituloPelicula, double precio, int idPelicula, String rutaImagen) {
        this.numSala = numSala;
        this.idSesion = idSesion;
        this.tituloPelicula = tituloPelicula;
        this.precio = precio;
        this.idPelicula = idPelicula;
        this.rutaImagen = rutaImagen;
    }

    public int getNumSala() { return numSala; }
    public int getIdSesion() { return idSesion; }
    public String getTituloPelicula() { return tituloPelicula; }
    public double getPrecio() { return precio; }
    public int getIdPelicula() { return idPelicula; }
    public String getRutaImagen() { return rutaImagen; }
}
