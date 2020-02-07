package com.web.mssql.entity.projection;

import java.time.Instant;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 16.12.2018 15:43
 */
public interface VwUserWithRole {

    Long getUID();

    String getName();

    String getPass();

    Integer getActive();

    Instant getUDate();

    Integer getRID();

    String getAuthority();

    Instant getRDate();
}
