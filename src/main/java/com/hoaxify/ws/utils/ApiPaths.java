package com.hoaxify.ws.utils;

/**
 * Created By Yasin Memic on Mar, 2020
 */
public class ApiPaths {

    private static final String BASE_PATH = "/api";
    private static final String VERSION = "/1.0";

    public static class UserCtrl {
        public static final String CTRL = BASE_PATH + VERSION + "/users";
    }

    public static class AuthCtrl {
        public static final String CTRL = BASE_PATH + VERSION + "/auth";
    }

    public static class HoaxCtrl {
        public static final String CTRL = BASE_PATH + VERSION + "/hoaxes";
    }
}
