package org.service.user.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class Users {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Include
    @Column(name = "name", nullable = false, length = 93)
    private String name;

    @ToString.Include
    @Column(name = "pass", nullable = false, length = 101)
    private String pass;

    @ToString.Include
    @Column(name = "active", nullable = false)
    private Integer active;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Roles.class)
    @JoinColumn(name = "fkRole", referencedColumnName = "id", nullable = false)
    private Roles roles;

    public Users() {
    }
}
