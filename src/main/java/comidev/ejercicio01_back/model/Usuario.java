package comidev.ejercicio01_back.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import comidev.ejercicio01_back.dto.UsuarioRequest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 6)
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @NotNull
    @Length(min = 3)
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @ManyToMany(mappedBy = "users")
    private Set<Tramo> tramos;

    public Usuario() {

    }

    public Usuario(UsuarioRequest usuario, Set<Role> roles) {
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        this.roles = roles;
    }

    public Usuario(Long id, @NotNull @Length(min = 6) String username, @NotNull @Length(min = 3) String password,
            Set<Role> roles, Set<Tramo> tramos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.tramos = tramos;
    }
}
