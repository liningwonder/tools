package com.lining.maven;

import com.lining.maven.conf.AppConfig;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Test;

import java.io.File;

public class CommonConfigTest {

    @Test
    public void testProperties() {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("D:\\1_UP\\maven\\src\\main\\resources\\app.properties"));
            System.out.println(config.getString("database.host"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testXml() {
        Configurations configs = new Configurations();
        try {
            XMLConfiguration config = configs.xml(new File("D:\\1_UP\\maven\\src\\main\\resources\\app.xml"));
            System.out.println(config.getString("processing.paths.path(1)"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void save() {
        Configurations configs = new Configurations();
        try
        {
            // obtain the configuration
            FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder("D:\\1_UP\\maven\\src\\main\\resources\\app.xml");
            XMLConfiguration config = builder.getConfiguration();

            // update property
            config.addProperty("newProperty", "newValue");

            // save configuration
            builder.save();
        }
        catch (ConfigurationException cex)
        {
            // Something went wrong
        }
    }


    @Test
    public void another() {
        try {
            AppConfig.init("app.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Thread.sleep(3000);
                System.out.println(AppConfig.getString("database.host"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
