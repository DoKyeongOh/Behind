package org.mytoypjt.entity;

import java.util.HashMap;
import java.util.Map;

public class ModelView {

    Map<String, Object> paramMap = null;
    String viewName = "";
    boolean redirectRequire = false;

    public ModelView() {
        paramMap = new HashMap<String, Object>();
    }

    public ModelView(String viewName) {
        paramMap = new HashMap<String, Object>();
        this.viewName = viewName;
    }

    public void setRedirectRequire(boolean redirectRequire) {
        this.redirectRequire = redirectRequire;
    }

    public boolean isRedirectRequire() {
        return redirectRequire;
    }

    public void setParam(String name, Object value) {
        paramMap.put(name, value);
    }

    public Object getParam(String name) {
        return paramMap.get(name);
    }

    public void removeParam(String name) {
        if (paramMap.containsKey(name))
            paramMap.remove(name);
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

}
