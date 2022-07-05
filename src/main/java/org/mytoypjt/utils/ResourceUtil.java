package org.mytoypjt.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class ResourceUtil {
    private Properties properties;
    private String path;

    public ResourceUtil(String path) {
        properties = new Properties();
        setPath(path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        try {
            FileReader fileReader = new FileReader(path);
            properties.load(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public boolean putProperty(String key, Object value) {
        if (properties.containsKey(key))
            return false;
        properties.put(key, value);
        return true;
    }
}
