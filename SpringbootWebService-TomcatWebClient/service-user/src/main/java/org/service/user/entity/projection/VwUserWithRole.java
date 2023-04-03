package org.service.user.entity.projection;

public interface VwUserWithRole {

    Long getUserID();

    String getName();

    String getPass();

    Integer getActive();

    Integer getRoleID();

    String getAuthority();

}
