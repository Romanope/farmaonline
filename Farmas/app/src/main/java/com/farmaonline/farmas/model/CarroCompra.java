package com.farmaonline.farmas.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Romano on 12/09/2017.
 */

public class CarroCompra {

    private long id;

    private BigDecimal desconto;

    private BigDecimal sutoTotal;

    private List<Product> products;

    private BigDecimal total;

    public CarroCompra(BigDecimal desconto, BigDecimal sutoTotal, BigDecimal total) {
        this.desconto = desconto;
        this.sutoTotal = sutoTotal;
        this.total = total;
        this.products = new ArrayList<Product>();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public void setSubtotal(BigDecimal sutoTotal) {
        this.sutoTotal = sutoTotal;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public BigDecimal getSubtotal() {
        return sutoTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
