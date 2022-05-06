package comidev.ejercicio01_back.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "role", uniqueConstraints = { @UniqueConstraint(columnNames = { "roleName" }) })
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> users;

    public Role() {

    }

    public Role(Long id, String roleName, Set<Usuario> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
    }
}
