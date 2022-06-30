package org.mytoypjt.controller.structure;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class RequestControllerMapping {

    private Map<String, IController> controllerMap = new HashMap<String, IController>();

    @SuppressWarnings("deprecation")
    public RequestControllerMapping() {
        Properties properties = new Properties();
        String uri;
        String controllerName;



        Class clazz = getClass();
        URL url = clazz.getResource("/uri_handler_mapping.properties");

        try {
            FileReader fileReader = new FileReader(url.getPath());
            properties.load(fileReader);
            Iterator<Object> keyItr = properties.keySet().iterator();
            while (keyItr.hasNext()) {

                uri = (String) keyItr.next();
                controllerName = properties.getProperty(uri);
                Class aClass = Class.forName(controllerName);
                IController iController = (IController) aClass.newInstance();
                controllerMap.put(uri, iController);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public IController getHandler(String uri) {
        if (controllerMap.containsKey(uri)) return controllerMap.get(uri);
        else return null;
    }
}
