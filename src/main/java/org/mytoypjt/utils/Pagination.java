package org.mytoypjt.utils;

import java.util.List;

public class Pagination<T> {
    int pageCount;
    int totalPageCount;
    int displayPageCount;
    int displayFirstPage;
    List<T> items;

    public Pagination() {
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getDisplayPageCount() {
        return displayPageCount;
    }

    public void setDisplayPageCount(int displayPageCount) {
        this.displayPageCount = displayPageCount;
    }

    public int getDisplayFirstPage() {
        return displayFirstPage;
    }

    public void setDisplayFirstPage(int displayFirstPage) {
        this.displayFirstPage = displayFirstPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void addItems(List<T> list) {
        this.items.addAll(list);
    }

    public void addItem(T t) {
        this.items.add(t);
    }
}
