package es.dsw.models;

public class FormularioReserva {
    private String imgSelec;
    private String fnom;
    private String fapell;
    private String fmail;
    private String frepmail;
    private String fdate;
    private String fhour;
    private int fnumentradasadult;
    private int fnumentradasmen;

    // Nuevos campos para datos de la sesión y película
    private Integer idSesion;
    private Integer idPelicula;
    private Integer numSala;
    private String tituloPelicula;
    private String butacasSeleccionadas;
    public String getButacasSeleccionadas() { return butacasSeleccionadas; }
    public void setButacasSeleccionadas(String butacasSeleccionadas) { this.butacasSeleccionadas = butacasSeleccionadas; }

    public FormularioReserva() {}

    public String getImgSelec() { return imgSelec; }
    public void setImgSelec(String imgSelec) { this.imgSelec = imgSelec; }

    public String getFnom() { return this.fnom; }
    public void setFnom(String fnom) { this.fnom = fnom; }

    public String getFapell() { return this.fapell; }
    public void setFapell(String fapell) { this.fapell = fapell; }

    public String getFmail() { return this.fmail; }
    public void setFmail(String fmail) { this.fmail = fmail; }

    public String getFrepmail() { return this.frepmail; }
    public void setFrepmail(String frepmail) { this.frepmail = frepmail; }

    public String getFdate() { return this.fdate; }
    public void setFdate(String fdate) { this.fdate = fdate; }

    public String getFhour() { return this.fhour; }
    public void setFhour(String fhour) { this.fhour = fhour; }

    public int getFnumentradasadult() { return this.fnumentradasadult; }
    public void setFnumentradasadult(int fnumentradasadult) { this.fnumentradasadult = fnumentradasadult; }

    public int getFnumentradasmen() { return this.fnumentradasmen; }
    public void setFnumentradasmen(int fnumentradasmen) { this.fnumentradasmen = fnumentradasmen; }

    public Integer getIdSesion() { return idSesion; }
    public void setIdSesion(Integer idSesion) { this.idSesion = idSesion; }

    public Integer getIdPelicula() { return idPelicula; }
    public void setIdPelicula(Integer idPelicula) { this.idPelicula = idPelicula; }

    public Integer getNumSala() { return numSala; }
    public void setNumSala(Integer numSala) { this.numSala = numSala; }

    public String getTituloPelicula() { return tituloPelicula; }
    public void setTituloPelicula(String tituloPelicula) { this.tituloPelicula = tituloPelicula; }
}
