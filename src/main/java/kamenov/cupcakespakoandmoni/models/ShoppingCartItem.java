package kamenov.cupcakespakoandmoni.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "cart_item")
public class ShoppingCartItem extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
@ManyToOne
    private CupCakeEntity cupCakeEntity;
@ManyToOne
private ShoppingBasket basket;
    private String name;
    private int quantity;
    private double price;
    private CupCakeTypeEnum type;


    public double getTotal() {
        return quantity * price;
    }

    public ShoppingCartItem() {
    }



    public CupCakeEntity getCupCakeEntity() {
        return cupCakeEntity;
    }

    public ShoppingCartItem setCupCakeEntity(CupCakeEntity cupCakeEntity) {
        this.cupCakeEntity = cupCakeEntity;
        return this;
    }

    public ShoppingBasket getBasket() {
        return basket;
    }

    public ShoppingCartItem setBasket(ShoppingBasket basket) {
        this.basket = basket;
        return this;
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
