package com.web.mssql.filters;

import java.io.Serializable;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 17.12.2018 13:55
 */
public class UserFilter implements Serializable {

    private static final long serialVersionUID = 5250484790452185767L;

    private String name;
    private String pass;
    private Integer active;
    private Integer role;

    public UserFilter() {
        this.name = null;
        this.pass = null;
        this.active = null;
        this.role = -1;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserFilter{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}
