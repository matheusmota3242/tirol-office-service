package br.mmmgg.tirolofficeservice.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


public class PropertiesUtil {
    
    public static Properties loadProperties(String resourceFileName) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(resourceFileName);
        properties.load(inputStream);
        inputStream.close();
        return properties;

    }
}
