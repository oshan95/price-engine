package com.calculator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductCarton {

    @Id
    private String productCartonId;
    private boolean rareProduct;
    private int unitsPerCarton;
    private int cartonPrice;

    public String getProductCartonId() {
        return productCartonId;
    }

    public void setProductCartonId(String productCartonId) {
        this.productCartonId = productCartonId;
    }

    public boolean isRareProduct() {
        return rareProduct;
    }

    public void setRareProduct(boolean rareProduct) {
        this.rareProduct = rareProduct;
    }

    public int getUnitsPerCarton() {
        return unitsPerCarton;
    }

    public void setUnitsPerCarton(int unitsPerCarton) {
        this.unitsPerCarton = unitsPerCarton;
    }

    public int getCartonPrice() {
        return cartonPrice;
    }

    public void setCartonPrice(int cartonPrice) {
        this.cartonPrice = cartonPrice;
    }
}
