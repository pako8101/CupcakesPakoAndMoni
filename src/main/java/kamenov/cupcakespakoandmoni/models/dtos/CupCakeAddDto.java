package kamenov.cupcakespakoandmoni.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;

import java.math.BigDecimal;

public class CupCakeAddDto {


        private long id;
        @Size(min = 3,max = 100, message = "Cupcake title must be between 3 and 100 characters")
        @NotNull
        private String name;
        @NotNull(message = "{add.article.content.message}")
        @Size(min = 10,message = "{add.article.content.message}")
        private String description;
        @NotNull(message = "You have to write price of cupcake!")
        @Positive(message = "The price should be positive!")
        private double price;
        @NotNull
        private String image;
        @NotNull
        private CupCakeTypeEnum type;
        @NotNull(message = "You have to write quantity of wine!")
        @PositiveOrZero
        private Integer quantity;

        public long getId() {
                return id;
        }

        public CupCakeAddDto setId(long id) {
                this.id = id;
                return this;
        }

        public @NotNull CupCakeTypeEnum getType() {
                return type;
        }

        public CupCakeAddDto setType(@NotNull CupCakeTypeEnum type) {
                this.type = type;
                return this;
        }

        public @Size(min = 3, max = 100, message = "Cupcake title must be between 3 and 100 characters") @NotNull String getName() {
                return name;
        }

        public CupCakeAddDto setName(@Size(min = 3, max = 100, message = "Cupcake title must be between 3 and 100 characters") @NotNull String name) {
                this.name = name;
                return this;
        }

        public @NotNull(message = "{add.article.content.message}") @Size(min = 10, message = "{add.article.content.message}") String getDescription() {
                return description;
        }

        public CupCakeAddDto setDescription(@NotNull(message = "{add.article.content.message}") @Size(min = 10, message = "{add.article.content.message}") String description) {
                this.description = description;
                return this;
        }

        @NotNull(message = "You have to write price of cupcake!")
        @Positive(message = "The price should be positive!")
        public double getPrice() {
                return price;
        }

        public CupCakeAddDto setPrice(@NotNull(message = "You have to write price of cupcake!") @Positive(message = "The price should be positive!") double price) {
                this.price = price;
                return this;
        }

        public @NotNull String getImage() {
                return image;
        }

        public CupCakeAddDto setImage(@NotNull String image) {
                this.image = image;
                return this;
        }

        public @NotNull(message = "You have to write quantity of wine!") @PositiveOrZero Integer getQuantity() {
                return quantity;
        }

        public CupCakeAddDto setQuantity(@NotNull(message = "You have to write quantity of wine!") @PositiveOrZero Integer quantity) {
                this.quantity = quantity;
                return this;
        }
}
