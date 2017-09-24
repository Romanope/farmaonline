package com.farmaonline.farmas.model;

import java.io.Serializable;

/**
 * Created by Romano on 13/09/2017.
 */

public class Endereco implements Serializable {

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String latitude;

    private String longitude;

    public Endereco() {

    }

    public Endereco(String rua, String numero, String bairro, String cidade, String latitude, String longitude) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
