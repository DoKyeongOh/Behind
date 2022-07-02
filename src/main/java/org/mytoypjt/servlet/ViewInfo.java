package org.mytoypjt.servlet;

public class ViewInfo {
    String viewName = "";
    boolean redirectRequire = false;

    public ViewInfo() {

    }

    public ViewInfo(String viewName) {
        this.viewName = viewName;
    }

    public void setRedirectRequired() {
        this.redirectRequire = true;
    }

    public void setNotRedirectRequired(){
        this.redirectRequire = false;
    }

    public boolean isRedirectRequire() {
        return redirectRequire;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

}
