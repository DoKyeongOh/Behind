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
        requestControllerMapping.entryControllers();
    }

    @Override
    public ViewInfo execute(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        String methodName = req.getMethod();

        Object controller = requestControllerMapping.getController(uri);
        if (controller == null)
            return new ViewInfo("pageNotFoundPage");

        Method method = getControllerMethod(controller, uri, methodName);

        Object viewObject = null;
        try {
            if (hasHttpParam(method)) {
                // 반드시 req, resp의 순서대로 넣어야함.
                viewObject = method.invoke(controller, req, resp);
            } else {
                viewObject = method.invoke(controller, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ObjectToViewInfo(viewObject);
    }

    public Method getControllerMethod(Object controllerObject, String uri, String methodName){
        for (Method method : controllerObject.getClass().getDeclaredMethods()) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping == null) continue;
            String mappedName = methodName.toUpperCase();
            String inputName = requestMapping.method().toUpperCase();

            if (!mappedName.equals(inputName)) continue;
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
            return new ViewInfo("pageNotFoundPage");
    }

    public boolean hasHttpParam(Method method){
        Class[] classes = method.getParameterTypes();
        if (classes.length != 2) return false;

        for (Class aClass : classes) {
            if (aClass.equals(HttpServletRequest.class))
                continue;

            if (aClass.equals(HttpServletResponse.class))
                continue;

            return false;
        }
        return true;
    }

    public ViewInfo ObjectToViewInfo(Object object){
        if (object instanceof String)
            return new ViewInfo((String) object);
        else if (object instanceof ViewInfo)
            return (ViewInfo) object;
        else
            return new ViewInfo("pageNotFoundPage");
    }

}
