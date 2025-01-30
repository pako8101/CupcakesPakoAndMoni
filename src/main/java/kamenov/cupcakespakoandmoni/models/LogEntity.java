package kamenov.cupcakespakoandmoni.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class LogEntity extends BaseEntity {
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private CupCakeEntity cupCake;
    @Column(name = "action",nullable = false)
    private String action;
    @Column(name = "date_time",nullable = false)
    private LocalDateTime dateTime;

    @Column
    private Instant appearanceTime;

    public LogEntity() {
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public LogEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public CupCakeEntity getCupCake() {
        return cupCake;
    }

    public LogEntity setCupCake(CupCakeEntity cupCake) {
        this.cupCake = cupCake;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LogEntity setAction(String action) {
        this.action = action;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Instant getAppearanceTime() {
        return appearanceTime;
    }

    public LogEntity setAppearanceTime(Instant appearanceTime) {
        this.appearanceTime = appearanceTime;
        return this;
    }
}
