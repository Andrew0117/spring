package org.web.springboot.util;

public class Constants {

    public static final String PROTOCOL;
    public static final String DOUBLE_SLASH;
    public static final String HOST;
    public static final String AUTHENTICATION_URL;
    public static final String CLIENT_END_POINT_URL;

    static {
        PROTOCOL = "http";
        DOUBLE_SLASH = "://";
        HOST = "localhost:8097";
        AUTHENTICATION_URL = PROTOCOL + DOUBLE_SLASH + HOST + "/api/auth/authenticate";
        CLIENT_END_POINT_URL = PROTOCOL + DOUBLE_SLASH + HOST + "/api/users/welcome";
    }
}
