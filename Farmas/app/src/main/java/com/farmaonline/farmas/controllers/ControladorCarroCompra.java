package com.farmaonline.farmas.controllers;

import com.farmaonline.farmas.model.Product;
import com.farmaonline.farmas.model.CarroCompra;

import java.math.BigDecimal;

/**
 * Created by Romano on 13/09/2017.
 */

public class ControladorCarroCompra {

    private static CarroCompra carroCompra;

    public static CarroCompra getUniqueInstanceCarroCompra() {

        if (carroCompra == null) {
            carroCompra = new CarroCompra(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return carroCompra;
    }

    public static void addProductToListAnd(Product product) {
        carroCompra.getProducts().add(product);

        //update values of car
        updateTotals(product, true);
    }

    public static void removeProduct(Product product) {
        carroCompra.getProducts().remove(product);

        //update values of car
        updateTotals(product, false);
    }

    private static void updateTotals(Product product, boolean addition) {

        if (addition) {
            carroCompra.setTotal(carroCompra.getTotal().add(product.getValor()));
            carroCompra.setSubtotal(carroCompra.getSubtotal().add(product.getValor()));
            return;
        }

        carroCompra.setSubtotal(carroCompra.getSubtotal().subtract(product.getValor()));
        carroCompra.setTotal(carroCompra.getTotal().subtract(product.getValor()));
    }

    public static void limparCarroCompra() {

        CarroCompra carro = getUniqueInstanceCarroCompra();
        carro.setDesconto(BigDecimal.ZERO);
        carro.setTotal(BigDecimal.ZERO);
        carro.setSubtotal(BigDecimal.ZERO);
        carro.getProducts().clear();
    }
}
