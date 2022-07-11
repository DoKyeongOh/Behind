package org.mytoypjt.models.etc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewInfo {
    String viewName = "";
    boolean redirectRequire = false;

    boolean isContainView = true;

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
