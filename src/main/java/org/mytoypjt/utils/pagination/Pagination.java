package org.mytoypjt.utils.pagination;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Pagination<T> {
    int pageNo;
    int pageCount;
    int firstPageNo;
    int displayPageCount;
    int displayItemCountInPage;
    int startItemNo;
    int endItemNo;
    List<T> items;

    public Pagination() {
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getFirstPageNo() {
        return firstPageNo;
    }

    public void setFirstPageNo(int firstPageNo) {
        this.firstPageNo = firstPageNo;
    }

    public int getDisplayPageCount() {
        return displayPageCount;
    }

    public void setDisplayPageCount(int displayPageCount) {
        this.displayPageCount = displayPageCount;
    }

    public int getStartItemNo() {
        return startItemNo;
    }

    public void setStartItemNo(int startItemNo) {
        this.startItemNo = startItemNo;
    }

    public int getEndItemNo() {
        return endItemNo;
    }

    public void setEndItemNo(int endItemNo) {
        this.endItemNo = endItemNo;
    }

    public int getDisplayItemCountInPage() {
        return displayItemCountInPage;
    }

    public void setDisplayItemCountInPage(int displayItemCountInPage) {
        this.displayItemCountInPage = displayItemCountInPage;
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

    public void sort(Comparator comparator) {
        Collections.sort(items,  comparator);
    }

    public void sort(Comparable comparable) {
        items.sort((Comparator<T>) comparable);
    }

    public void sort() {
        Collections.sort((List) items);
    }

    public boolean isCorrectPagination() {
        if (pageCount < firstPageNo)
            return false;

        if (pageCount > firstPageNo + displayPageCount)
            return false;

        int pageCapacity = (int) items.size() / displayItemCountInPage;
        if (pageCount > pageCapacity)
            return false;

        return true;
    }


}
