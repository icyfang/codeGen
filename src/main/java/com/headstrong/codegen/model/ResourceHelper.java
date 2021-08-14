package com.headstrong.codegen.model;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class ResourceHelper {

    private static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream("application.properties"));
        } catch (IOException e) {
            log.error(ExceptionParser.getStackTrace(e));
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
