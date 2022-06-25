package org.mytoypjt.controller;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ControllerFactory {

    private Map<String, IController> controllerMap = new HashMap<String, IController>();

    ControllerFactory (String loc) {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("/uri_handler_mapping.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public IController getController(ServletContext servletContext, String propertyLocation) {


        return null;
    }
}
