package com.selenium.test.configuration;

import com.selenium.test.configuration.properties.PropertiesLoader;
import com.selenium.test.configuration.properties.Property;
import com.selenium.test.configuration.properties.PropertyFile;

/**
 * Created by Maciej Targosz on 11/26/2017.
 */
@PropertyFile("language_en.properties")
public class TestsData extends TestsProperties {

    private static TestsData data;

    public static TestsData getData() {
        if (data == null) {
            data = new TestsData();
        }
        return data;
    }
    public TestsData() {
        PropertiesLoader.populate(this);
    }
}
