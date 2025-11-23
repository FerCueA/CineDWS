package es.dsw.models;

import java.io.Serializable;

public class TarjetaDatos implements Serializable {
    private String titular;
    private String numero;
    private String mesCaduca;
    private String anioCaduca;
    private String ccs;

    public TarjetaDatos() {
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
}
