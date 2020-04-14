package com.croco.demo.config;

import io.github.jhipster.config.JHipsterConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

public class DefaultProfileUtlil {

    private static final String SPRING_PROFILE_DEFAULT = "spring.profile.default";

    private DefaultProfileUtlil(){

    }

    public static void addDefaultProfile(SpringApplication app){
        Map<String, Object> defProperties = new HashMap<>();

        defProperties.put(SPRING_PROFILE_DEFAULT, JHipsterConstants.SPRING_PROFILE_TEST);
        app.setDefaultProperties(defProperties);
    }

    public static String[] getActiveProfiles(Environment env){
        String[] profiles = env.getActiveProfiles();
        if(profiles.length == 0){
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
