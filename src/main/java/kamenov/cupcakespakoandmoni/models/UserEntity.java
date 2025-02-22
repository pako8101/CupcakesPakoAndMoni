package kamenov.cupcakespakoandmoni.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.List;
import java.util.UUID;

import static java.sql.Types.VARCHAR;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity{

    @JdbcTypeCode(VARCHAR)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    @NotNull
    private String email;

    @Column(unique = true, nullable = false)
    @NotNull
    private String username;

    @Column(unique = true, nullable = false,name = "full_name")
    @NotNull
    private String fullName;

    @Column(nullable = false)
    @NotNull
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;


    public UserEntity() {
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UserEntity setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public @NotNull String getFullName() {
        return fullName;
    }

    public UserEntity setFullName(@NotNull String fullName) {
        this.fullName = fullName;
        return this;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public UserEntity setEmail(@NotNull String email) {
        this.email = email;
        return this;
    }

    public @NotNull String getUsername() {
        return username;
    }

    public UserEntity setUsername(@NotNull String username) {
        this.username = username;
        return this;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public UserEntity setPassword(@NotNull String password) {
        this.password = password;
        return this;
    }
}
