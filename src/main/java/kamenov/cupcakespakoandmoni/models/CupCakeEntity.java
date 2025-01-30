package kamenov.cupcakespakoandmoni.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;

@Entity
@Table(name = "cup_cake")
public class CupCakeEntity  {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true,name = "name", length = 50)
        private String name;
        @Column(columnDefinition = "TEXT")
        private String description;

        @Column(name = "image_url",nullable = false)
        private String image;
        @NotNull
        private double price;

        @Column(unique = true, nullable = false,name = "type")
        @Enumerated(EnumType.STRING)
        private CupCakeTypeEnum type;

        @NotNull
        private Integer quantity;


        public CupCakeEntity() {
        }

        public CupCakeEntity(Long id, String name, String description, String image, double price, CupCakeTypeEnum type, Integer quantity) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.image = image;
                this.price = price;
                this.type = type;
                this.quantity = quantity;
        }

        public Long getId() {
                return id;
        }

        public CupCakeEntity setId(Long id) {
                this.id = id;
                return this;
        }

        public CupCakeTypeEnum getType() {
                return type;
        }

        public CupCakeEntity setType(CupCakeTypeEnum type) {
                this.type = type;
                return this;
        }

        public @NotNull Integer getQuantity() {
                return quantity;
        }

        public CupCakeEntity setQuantity(@NotNull Integer quantity) {
                this.quantity = quantity;
                return this;
        }

        public String getName() {
                return name;
        }

        public CupCakeEntity setName(String name) {
                this.name = name;
                return this;
        }

        public String getDescription() {
                return description;
        }

        public CupCakeEntity setDescription(String description) {
                this.description = description;
                return this;
        }

        public String getImage() {
                return image;
        }

        public CupCakeEntity setImage(String image) {
                this.image = image;
                return this;
        }

        @NotNull
        public double getPrice() {
                return price;
        }

        public CupCakeEntity setPrice(@NotNull double price) {
                this.price = price;
                return this;
        }
}
