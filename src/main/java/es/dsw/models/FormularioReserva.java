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
    private int totalButacas;
    private String titular;
    private String numero;
    private String mesCaduca;
    private String anioCaduca;
    private String ccs;
    private Integer idSesion;
    private Integer idPelicula;
    private Integer numSala;
    private String tituloPelicula;
    private String butacasSeleccionadas;

    public String getButacasSeleccionadas() {
        return butacasSeleccionadas;
    }

    public void setButacasSeleccionadas(String butacasSeleccionadas) {
        this.butacasSeleccionadas = butacasSeleccionadas;
    }

    public FormularioReserva() {
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMesCaduca() {
        return mesCaduca;
    }

    public void setMesCaduca(String mesCaduca) {
        this.mesCaduca = mesCaduca;
    }

    public String getAnioCaduca() {
        return anioCaduca;
    }

    public void setAnioCaduca(String anioCaduca) {
        this.anioCaduca = anioCaduca;
    }

    public String getCcs() {
        return ccs;
    }

    public void setCcs(String ccs) {
        this.ccs = ccs;
    }

    public int getTotalButacas() {
        return totalButacas;
    }

    public void setTotalButacas(int totalButacas) {
        this.totalButacas = totalButacas;
    }

    public String getImgSelec() {
        return imgSelec;
    }

    public void setImgSelec(String imgSelec) {
        this.imgSelec = imgSelec;
    }

    public String getFnom() {
        return this.fnom;
    }

    public void setFnom(String fnom) {
        this.fnom = fnom;
    }

    public String getFapell() {
        return this.fapell;
    }

    public void setFapell(String fapell) {
        this.fapell = fapell;
    }

    public String getFmail() {
        return this.fmail;
    }

    public void setFmail(String fmail) {
        this.fmail = fmail;
    }

    public String getFrepmail() {
        return this.frepmail;
    }

    public void setFrepmail(String frepmail) {
        this.frepmail = frepmail;
    }

    public String getFdate() {
        return this.fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getFhour() {
        return this.fhour;
    }

    public void setFhour(String fhour) {
        this.fhour = fhour;
    }

    public int getFnumentradasadult() {
        return this.fnumentradasadult;
    }

    public void setFnumentradasadult(int fnumentradasadult) {
        this.fnumentradasadult = fnumentradasadult;
    }

    public int getFnumentradasmen() {
        return this.fnumentradasmen;
    }

    public void setFnumentradasmen(int fnumentradasmen) {
        this.fnumentradasmen = fnumentradasmen;
    }

    public Integer getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Integer getNumSala() {
        return numSala;
    }

    public void setNumSala(Integer numSala) {
        this.numSala = numSala;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    @Override
    public String toString() {
        return "{" +
                " imgSelec='" + getImgSelec() + "'" +
                ", fnom='" + getFnom() + "'" +
                ", fapell='" + getFapell() + "'" +
                ", fmail='" + getFmail() + "'" +
                ", frepmail='" + getFrepmail() + "'" +
                ", fdate='" + getFdate() + "'" +
                ", fhour='" + getFhour() + "'" +
                ", fnumentradasadult='" + getFnumentradasadult() + "'" +
                ", fnumentradasmen='" + getFnumentradasmen() + "'" +
                ", totalButacas='" + getTotalButacas() + "'" +
                ", titular='" + getTitular() + "'" +
                ", numero='" + getNumero() + "'" +
                ", mesCaduca='" + getMesCaduca() + "'" +
                ", anioCaduca='" + getAnioCaduca() + "'" +
                ", ccs='" + getCcs() + "'" +
                ", idSesion='" + getIdSesion() + "'" +
                ", idPelicula='" + getIdPelicula() + "'" +
                ", numSala='" + getNumSala() + "'" +
                ", tituloPelicula='" + getTituloPelicula() + "'" +
                ", butacasSeleccionadas='" + getButacasSeleccionadas() + "'" +
                "}";
    }

}
