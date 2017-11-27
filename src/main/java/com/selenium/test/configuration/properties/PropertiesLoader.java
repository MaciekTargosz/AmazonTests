package com.selenium.test.configuration.properties;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.configuration.TestsProperties;
import com.selenium.test.exceptions.TestsConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Created by Sidelnikov Mikhail on 18.09.14., Modified by Maciej Targosz on 26.11.17
 * Class for loading base tests properties. It gets properties - system or from file (by specified names) and sets it to fields of TestsProperties object
 */
public class PropertiesLoader {

    /**
     * Sets TestConfig object fields values to specified properties values
     *
     * @param testsProperties {@link com.selenium.test.configuration.TestsProperties} object
     */
    public static void populate(TestsProperties testsProperties) {
        Properties properties = null;
        PropertyFile propertyFileAnnotation = testsProperties.getClass().getAnnotation(PropertyFile.class);
        if (propertyFileAnnotation != null) {
            String propertyFileName = propertyFileAnnotation.value();
            if (propertyFileName == null) {
                throw new TestsConfigurationException("Property file name cannot be empty. Class name : " + testsProperties.getClass().getName());
            } else {
                properties = new Properties();
                try {
                    InputStream stream = PropertiesLoader.class.getClassLoader().getResourceAsStream(propertyFileName);
                    if(stream != null) {
                        properties.load(stream);
                    } else {
                        throw new TestsConfigurationException("Unable to read property file with name '" + propertyFileName + "' - file not found");
                    }
                } catch (IOException e) {
                    throw new TestsConfigurationException("Error while reading property file with name '" + propertyFileName + "' : " + e.getMessage(), e);
                }
            }
        } else {
            properties = System.getProperties();
        }
        Field[] fields = testsProperties.getClass().getDeclaredFields();
        for (Field field : fields) {
            Property propertyAnnotation = field.getAnnotation(Property.class);
            if (propertyAnnotation != null) {
                String propertyName = propertyAnnotation.value();
                if (propertyName == null) {
                    throw new TestsConfigurationException("Property value cannot be empty. Field name : " + field.getName());
                }
                String propertyValue = properties.getProperty(propertyName);
                if (propertyValue != null) {
                    try {
                        field.setAccessible(true);
                        field.set(testsProperties, propertyValue);
                    } catch (IllegalAccessException e) {
                        throw new TestsConfigurationException("Field cannot be set...", e);
                    }
                }
            }
        }
    }

}
