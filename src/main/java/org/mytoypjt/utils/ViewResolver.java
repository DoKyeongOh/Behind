package org.mytoypjt.utils;

import org.mytoypjt.entity.ModelView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ViewResolver {

    String prefix = "";
    String suffix = "";
    String rootPath = "";

    Map<String, Boolean> viewFileMap;

    public ViewResolver() {

    }

    public ViewResolver(String rootPath) {
        this.rootPath = rootPath;
        viewFileMap = new HashMap<String, Boolean>();
    }

    public void setSuffix (String suffix) {
        this.suffix = suffix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public boolean isExistPage(String pageName){
        if (viewFileMap.containsKey(pageName)) {
            return true;
        }

        String page = this.prefix + pageName + this.suffix;
        page = page.substring(1);
        page = rootPath + page;

        if (!new File(page).exists())
            return false;

        viewFileMap.put(pageName, true);
        return true;
    }

    public String getViewName(String pageName) {
        if (isExistPage(pageName))
            return this.prefix + pageName + this.suffix;
        else
            return this.prefix + "pageNotFound" + this.suffix;
    }
}
