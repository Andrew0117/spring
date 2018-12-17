package com.web.mssql.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 16.12.2018 15:06
 */
@Entity
@Table(name = "Roles", catalog = "webdb", schema = "dbo")
public class Roles implements Serializable, Comparable<Roles> {

    private static final long serialVersionUID = 5502575492149645858L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    @Column(name = "cDate", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant date;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Users.class, mappedBy = "roles")
    private List<Users> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roles roles = (Roles) o;

        if (!id.equals(roles.id)) return false;
        if (!authority.equals(roles.authority)) return false;
        return date.equals(roles.date);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + authority.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public int compareTo(Roles o) {
        return (this.getId() < o.getId()) ? -1 : ((this.getId() == o.getId()) ? 0 : 1);
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", date=" + date +
                '}';
    }
}
