package com.calculator.domain;

public class PriceComponent {

    Integer id;
    Integer quantity;
    Double price;

    public PriceComponent(Integer id, Integer quantity, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PriceComponent [id=" + id + ", quantity=" + quantity + ", price=" + price +"]";
    }
}
