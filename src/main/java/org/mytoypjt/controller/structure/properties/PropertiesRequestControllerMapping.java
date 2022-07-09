package org.mytoypjt.controller.structure.properties;

import org.mytoypjt.controller.structure.IRequestControllerMapping;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertiesRequestControllerMapping implements IRequestControllerMapping {

    private Map<String, IController> controllerMap;

    public PropertiesRequestControllerMapping() {
        this.controllerMap = new HashMap<String, IController>();
    }

    @Override
    public IController getController(String uri) {
        return this.controllerMap.get(uri);
    }

    @Override
    public void entryControllers() {

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
}
