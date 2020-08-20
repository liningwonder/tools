package com.lining.maven.conf;

import com.lining.maven.constant.Constants;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BuilderParameters;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    private static Configuration config;

    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {

    }

    public static AppConfig getINSTANCE() {
        return INSTANCE;
    }

    public static void init(String file) throws Exception {
        Parameters params = new Parameters();
        File propertiesFile = new File(file);
        List<FileLocationStrategy> subs = Arrays.asList(
                new ProvidedURLLocationStrategy(),
                new FileSystemLocationStrategy(),
                new ClasspathLocationStrategy());
        FileLocationStrategy strategy = new CombinedLocationStrategy(subs);
        BuilderParameters fp = params.fileBased()
                .setListDelimiterHandler(new DefaultListDelimiterHandler(Constants.COMMA))
                .setEncoding(Constants.UTF8)
                .setLocationStrategy(strategy)
                .setFile(propertiesFile)
                .setThrowExceptionOnMissing(true);
        ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(fp);
        //TODO 热配置实现
        config = builder.getConfiguration();

    }


    public static String getString(String key) {
        return config.getString(key);
    }

    public static int getInteger(String key) {
        return config.getInt(key);
    }

    public static long getLong(String key) {
        return config.getLong(key);
    }

    public static double getDouble(String key) {
        return config.getDouble(key);
    }


}
