package org.mytoypjt.models.etc;

import lombok.Getter;
import lombok.Setter;

public class ViewInfo {
    String viewName = "";
    boolean redirectRequire = false;

    boolean isContainView = true;

    public ViewInfo() {

    }

    public ViewInfo(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public boolean isRedirectRequire() {
        return redirectRequire;
    }

    public void setRedirectRequire(boolean redirectRequire) {
        this.redirectRequire = redirectRequire;
    }

    public boolean isContainView() {
        return isContainView;
    }

    public void setContainView(boolean containView) {
        isContainView = containView;
    }

    public void setRedirectTo(String url){
        setRedirectRequire(true);
        setViewName(url);
    }
}
