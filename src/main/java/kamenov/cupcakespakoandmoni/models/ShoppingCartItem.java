package kamenov.cupcakespakoandmoni.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;

@Entity
@Table(name = "cart")
public class ShoppingCartItem extends BaseEntity{

    private String name;
    private int quantity;
    private double price;
    private CupCakeTypeEnum type;

    public double getTotal() {
        return quantity * price;
    }

    public ShoppingCartItem() {
    }

    public CupCakeTypeEnum getType() {
        return type;
    }

    public ShoppingCartItem setType(CupCakeTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShoppingCartItem setName(String name) {
        this.name = name;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public ShoppingCartItem setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ShoppingCartItem setPrice(double price) {
        this.price = price;
        return this;
    }
}
