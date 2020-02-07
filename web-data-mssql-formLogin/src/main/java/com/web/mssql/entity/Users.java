package com.web.mssql.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 16.12.2018 15:00
 */
@Entity
@Table(name = "Users", catalog = "webdb", schema = "dbo")
public class Users implements Serializable {

    private static final long serialVersionUID = 6666427567437710594L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 93)
    private String name;

    @Column(name = "pass", nullable = false, length = 101)
    private String pass;

    @Column(name = "active", nullable = false)
    private Integer active;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Roles.class)
    @JoinColumn(name = "fkRole", referencedColumnName = "id", nullable = false)
    private Roles roles;

    @Column(name = "cDate", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (!id.equals(users.id)) return false;
        if (!name.equals(users.name)) return false;
        if (!pass.equals(users.pass)) return false;
        if (!active.equals(users.active)) return false;
        return date.equals(users.date);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + pass.hashCode();
        result = 31 * result + active.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", active=" + active +
                ", date=" + date +
                '}';
    }
}
