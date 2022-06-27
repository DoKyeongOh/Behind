package org.mytoypjt.utils;

public class ViewResolver {

    String prefix = "";
    String suffix = "";

    public ViewResolver() {

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

    public String getViewName(String pageName) {
        return this.prefix + pageName + this.suffix;
    }
}
