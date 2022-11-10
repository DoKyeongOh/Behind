package org.mytoypjt.utils.pagination;

import java.util.List;

public class PaginationBuilder<T>{

    private Pagination pagination;

    public PaginationBuilder(List<T> items, int pageNo) {
        pagination = new Pagination();
        pagination.setPageNo(pageNo);
        pagination.setItems(items);
    }

    public PaginationBuilder displayItemCountInPage(int displayItemCount) {
        pagination.setDisplayItemCountInPage(displayItemCount);
        return this;
    }

    public PaginationBuilder displayPageCount(int displayPageCount) {
        pagination.setDisplayPageCount(displayPageCount);
        return this;
    }

    public Pagination build() {
        int pageCapacity = (int) pagination.getItems().size() / pagination.getDisplayItemCountInPage();
        if (pagination.getItems().size() % pagination.getDisplayItemCountInPage() != 0)
            pageCapacity++;

        int pageNo = pagination.getPageNo();
        if (pageNo > pageCapacity)
            pageNo = pageCapacity;

        int displayCount = pagination.getDisplayPageCount();
        if (displayCount == 0)
            displayCount = 1;

        int firstPageNo = ((int) pageNo / displayCount) * displayCount + 1;

        if (firstPageNo + displayCount - 1 > pageCapacity)
            displayCount = pageCapacity - firstPageNo + 1;

        int startItemNo = (pageNo-1) * pagination.getDisplayItemCountInPage() + 1;
        int endItemNo = startItemNo + pagination.getDisplayItemCountInPage() - 1;

        pagination.setPageNo(pageNo);
        pagination.setFirstPageNo(firstPageNo);
        pagination.setDisplayPageCount(displayCount);
        pagination.setPageCount(pageCapacity);
        pagination.setStartItemNo(startItemNo);
        pagination.setEndItemNo(endItemNo);

        return pagination;
    }
}
