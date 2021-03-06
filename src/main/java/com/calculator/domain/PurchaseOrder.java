package com.calculator.domain;

public class PurchaseOrder {

    Integer productId;
    Boolean cartonOrder;
    Integer units;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Boolean getCartonOrder() {
        return cartonOrder;
    }

    public void setCartonOrder(Boolean cartonOrder) {
        this.cartonOrder = cartonOrder;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "PurchaseOrder [productId=" + productId + ", cartonOrder=" + cartonOrder + ", units=" + units +"]";
    }
}
