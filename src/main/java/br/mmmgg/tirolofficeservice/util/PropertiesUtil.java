package br.mmmgg.tirolofficeservice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {
    
    public static Properties loadProperties(String resourceFileName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(resourceFileName);
        properties.load(inputStream);
        inputStream.close();
        return properties;

    }
}
