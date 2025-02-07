package kamenov.cupcakespakoandmoni.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_basket")
public class ShoppingBasket extends BaseEntity {

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,fetch = FetchType.EAGER)
    private List<ShoppingCartItem> items = new ArrayList<>();
    @OneToOne
    private UserEntity userEntity;

    private boolean completed = false;

    public ShoppingBasket() {
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public ShoppingBasket setItems(List<ShoppingCartItem> items) {
        this.items = items;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public ShoppingBasket setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public boolean isCompleted() {
        return completed;
    }

    public ShoppingBasket setCompleted(boolean completed) {
        this.completed = completed;
        return this;
    }
}
