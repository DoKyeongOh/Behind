package org.mytoypjt.models.dto;

public class PostsOptionDto {

    String pageNo = "";
    String sortType = "";

    public PostsOptionDto() {
        this.pageNo = "";
        this.sortType = "";
    }

    public PostsOptionDto(String pageNo, String sortType) {
        this.pageNo = pageNo;
        this.sortType = sortType;
        if (pageNo == null)
            this.pageNo = "";
        if (sortType == null)
            this.sortType = "";
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
