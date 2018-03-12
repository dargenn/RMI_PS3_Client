package io.dargenn.common;

import java.io.IOException;
import java.util.Properties;

public class SecurityUtils {
    private SecurityUtils() {
    }

    public static void prepareSecurity() {
        System.setProperty("java.security.policy", "C://Users//micha//IdeaProjects//RMI_PS3_Client//src//main//resources//security.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }
}
