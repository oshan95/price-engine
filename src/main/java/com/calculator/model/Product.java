package com.calculator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private Integer productId;
    private String productName;
    private boolean rareProduct;
    private Integer unitsPerCarton;
    private Integer cartonPrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isRareProduct() {
        return rareProduct;
    }

    public void setRareProduct(boolean rareProduct) {
        this.rareProduct = rareProduct;
    }

    public Integer getUnitsPerCarton() {
        return unitsPerCarton;
    }

    public void setUnitsPerCarton(Integer unitsPerCarton) {
        this.unitsPerCarton = unitsPerCarton;
    }

    public Integer getCartonPrice() {
        return cartonPrice;
    }

    public void setCartonPrice(Integer cartonPrice) {
        this.cartonPrice = cartonPrice;
    }
}
