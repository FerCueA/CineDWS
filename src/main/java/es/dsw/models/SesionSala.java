package es.dsw.models;

public class SesionSala {
    private int numSala;
    private int idPelicula;
    private int idSesion;

    public SesionSala() {
    }

    public SesionSala(int numSala, int idPelicula, int idSesion) {
        this.numSala = numSala;
        this.idPelicula = idPelicula;
        this.idSesion = idSesion;
    }

    public int getNumSala() {
        return numSala;
    }

    public void setNumSala(int numSala) {
        this.numSala = numSala;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }
}
