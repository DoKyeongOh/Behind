package org.mytoypjt.controller.structure.annotation;

import org.mytoypjt.controller.structure.BaseControllerAdapter;
import org.mytoypjt.controller.structure.RequestControllerMappingFactory;
import org.mytoypjt.controller.structure.annotations.RequestMapping;
import org.mytoypjt.controller.structure.enums.MappingName;
import org.mytoypjt.models.etc.ViewInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AnnotationControllerAdapter extends BaseControllerAdapter {
    public AnnotationControllerAdapter() {
        this.requestControllerMapping =
                RequestControllerMappingFactory.getMappingClass(MappingName.Annotation);
        requestControllerMapping.entryController();
    }

    @Override
    public ViewInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        String methodName = req.getMethod();

        Object controllerObject = requestControllerMapping.getController(uri);
        Method controllerMethod = getControllerMethod(controllerObject, uri, methodName);

        return getViewInfoByMethod(controllerMethod, controllerObject);
    }

    public Method getControllerMethod(Object object, String uri, String methodName){
        for (Method method : object.getClass().getMethods()) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping == null) continue;
            if (!methodName.equals(requestMapping.method())) continue;
            if (!uri.equals(requestMapping.uri())) continue;
            return method;
        }
        return null;
    }

    public ViewInfo getViewInfoByMethod(Method method, Object classObject){
        Object viewObject = null;
        try {
            viewObject = method.invoke(classObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (viewObject instanceof String)
            return new ViewInfo((String) viewObject);
        else if (viewObject instanceof ViewInfo)
            return (ViewInfo) viewObject;
        else
            return new ViewInfo("pageNotFound");
    }
}
