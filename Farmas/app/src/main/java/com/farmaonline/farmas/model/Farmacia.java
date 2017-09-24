package com.farmaonline.farmas.model;

import com.farmaonline.farmas.model.Endereco;

import java.io.Serializable;

/**
 * Created by Romano on 13/09/2017.
 */

public class Farmacia implements Serializable {

    private String nome;

    private String nomeLogo;

    private String fone;

    private Endereco enderco;

    public Farmacia(String nome, String nomeLogo, String fone) {
        this.nome = nome;
        this.nomeLogo = nomeLogo;
        this.fone = fone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeLogo() {
        return nomeLogo;
    }

    public void setNomeLogo(String nomeLogo) {
        this.nomeLogo = nomeLogo;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Endereco getEnderco() {
        return enderco;
    }

    public void setEnderco(Endereco enderco) {
        this.enderco = enderco;
    }
}
