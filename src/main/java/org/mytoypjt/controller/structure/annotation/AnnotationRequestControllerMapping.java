package org.mytoypjt.controller.structure.annotation;

import org.mytoypjt.controller.structure.IRequestControllerMapping;
import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.utils.PropertiesUtil;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;


public class AnnotationRequestControllerMapping implements IRequestControllerMapping {

    private HashMap<String, Object> controllerMap;

    public AnnotationRequestControllerMapping() {
        controllerMap = new HashMap<String, Object>();
    }

    @Override
    public Object getController(String uri) {
        return controllerMap.get(uri);
    }

    @Override
    public void entryControllers(){
        PropertiesUtil propertiesUtil = new PropertiesUtil("/annotation_config.properties");
        String className = (String) propertiesUtil.getProperty("controller.mapping.root");

        try {
            Class basedClass = Class.forName(className);
            URL url = basedClass.getResource(".");

            File directory = new File(url.getFile());
            String rootPath = directory.getPath() + "/";
            String packageName = basedClass.getPackageName();

            dfsSearchFromDir(directory.list(), rootPath, packageName);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dfsSearchFromDir(String[] dir, String filePath, String classPath) {
        for (String className : dir) {
            if (isInnerClass(className)) continue;
            if (isDirectory(className)) {
                File childDir = new File(filePath + className);
                String childPath = filePath + className + "/";
                String childClassPath = classPath + "." + className;

                dfsSearchFromDir(childDir.list(), childPath, childClassPath);
                continue;
            }

            if (className.indexOf(".class") < 0) continue;

            className = (classPath + "." + className).replace(".class", "");

            Class aClass = convertNameToClass(className);
            if (aClass != null)
                examineMethods(aClass);
        }
    }

    public boolean isInnerClass(String filename) {
        if (filename.indexOf("$") > -1) return true;
        return false;
    }

    public boolean isDirectory(String filename) {
        if (filename.indexOf(".class") < 0) return true;
        return false;
    }

    public Class convertNameToClass(String filename){
        Class aClass = null;
        try {
            aClass = Class.forName(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aClass;
    }

    public void annotationTest(String value){
        System.out.println(value);
    }

    public void examineMethods(Class aClass){
        String uri = "";
        String methodName = "";

        for (Method method : aClass.getDeclaredMethods()) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

            if (requestMapping == null) continue;
            uri = requestMapping.uri();
            methodName = requestMapping.method();

            try {
                controllerMap.put(uri, aClass.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
