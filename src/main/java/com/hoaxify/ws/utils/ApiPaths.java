package com.hoaxify.ws.utils;

/**
 * Created By Yasin Memic on Mar, 2020
 */
public class ApiPaths {

    private static final String BASE_PATH = "/api";
    private static final String VERSION = "/1.0";
    private static final String ID = "/{id:[0-9]+}";
    private static final String USERNAME = "/{username}";
    private static final String HOAXES = "/hoaxes";
    private static final String USERS = "/users";
    private static final String AUTH = "/auth";
    private static final String HOAX_ATTACHMENTS = "/hoax-attachments";

    public static class UserCtrl {
        public static final String CTRL = BASE_PATH + VERSION + USERS;

        public static class UserCtrlWithUsernameCtrl {
            public static final String CTRL = UserCtrl.CTRL + USERNAME;
        }
    }

    public static class AuthCtrl {
        public static final String CTRL = BASE_PATH + VERSION + AUTH;
    }

    public static class HoaxCtrl {
        public static final String CTRL = BASE_PATH + VERSION + HOAXES;

        public static class HoaxAttachmentsCtrl {
            public static final String CTRL = BASE_PATH + VERSION + HOAX_ATTACHMENTS;
        }

        public static class HoaxIdCtrl {
            public static final String CTRL = HoaxCtrl.CTRL + ID;
        }

        public static class HoaxByUsernameAndIdRelativeCtrl {
            public static final String CTRL = UserCtrl.CTRL + USERNAME + HOAXES + ID;
        }

        public static class HoaxByUsernameAndIdForUserPageCtrl {
            public static final String CTRL = UserCtrl.CTRL + USERNAME + HOAXES;
        }

    }
}
