package org.service.user.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "roles")
public class Roles {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ToString.Include
    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Users.class, mappedBy = "roles")
    private List<Users> users;

    public Roles() {
    }
}
