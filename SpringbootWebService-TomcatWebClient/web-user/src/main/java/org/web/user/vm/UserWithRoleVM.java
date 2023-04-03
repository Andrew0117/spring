package org.web.user.vm;

import lombok.Data;

@Data
public class UserWithRoleVM {

    private Long userID;

    private String name;

    private String pass;

    private Integer active;

    private Integer roleID;

    private String authority;

    public UserWithRoleVM() {
    }
}
