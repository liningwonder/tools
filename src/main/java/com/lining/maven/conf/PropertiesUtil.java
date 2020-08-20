package com.lining.maven.conf;

import com.lining.maven.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties properties = null;

    private static final PropertiesUtil INSTANCE = new PropertiesUtil();

    private PropertiesUtil() {

    }

    public static PropertiesUtil getINSTANCE() {
        return INSTANCE;
    }

    public void init(String filename) {
        InputStream inputStream = null;
        try {
            String defaultConfig = System.getProperty(Constants.USER_DIR) + File.separator + filename;
            File file = new File(defaultConfig);
            if (file.exists()) {
                inputStream = new FileInputStream(file);
            } else {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            }
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("load config file failure");
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure");
                }
            }
        }
    }

    public static String getStringProperty(String key) {
        String value = "";
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }

    public static String getStringProperty(String key, String defaultValue) {
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        } else {
            return defaultValue;
        }

    }
}
