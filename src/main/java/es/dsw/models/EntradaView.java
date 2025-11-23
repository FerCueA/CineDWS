package es.dsw.models;

public class EntradaView {
    private String codigo;
    private String pelicula;
    private String sala;
    private String fecha;
    private String hora;
    private String fila;
    private String butaca;

    public EntradaView(String codigo, String pelicula, String sala, String fecha, String hora, String fila,
            String butaca) {
        this.codigo = codigo;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.hora = hora;
        this.fila = fila;
        this.butaca = butaca;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPelicula() {
        return pelicula;
    }

    public String getSala() {
        return sala;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getFila() {
        return fila;
    }

    public String getButaca() {
        return butaca;
    }
}
