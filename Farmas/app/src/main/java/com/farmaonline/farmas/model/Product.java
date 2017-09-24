package com.farmaonline.farmas.model;

import com.farmaonline.farmas.model.Farmacia;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Romano on 11/09/2017.
 */

public class Product implements Serializable {

    private String descricao;

    private BigDecimal valor;

    private String nomeImagem;

    private Farmacia farmacia;

    public Product(String descricao, Farmacia farmacia, BigDecimal valor, String nomeImagem) {
        this.descricao = descricao;
        this.farmacia = farmacia;
        this.valor = valor;
        this.nomeImagem = nomeImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
}
