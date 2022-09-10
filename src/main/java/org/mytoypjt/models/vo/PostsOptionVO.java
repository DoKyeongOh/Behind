package org.mytoypjt.models.vo;

import java.util.HashMap;
import java.util.Map;

public class PostsOptionVO {

    String pageNo = "";
    String sortType = "";
    int pageStartNo = 0;
    int pageEndNo = 0;
    int pageCount = 5;
    int postCountInPage = 12;
    Map<String, String> optionMap;

    public PostsOptionVO() {
        this.pageNo = "";
        this.sortType = "";
        this.pageStartNo = 1;
        this.pageEndNo = 1;
        optionMap = new HashMap<>();
    }

    public PostsOptionVO(String pageNo, String sortType) {
        this.pageNo = pageNo;
        this.sortType = sortType;
        this.pageStartNo = 1;
        this.pageEndNo = 1;
        this.pageCount = 5;
        this.postCountInPage = 12;

        if (pageNo == null)
            this.pageNo = "";
        if (sortType == null)
            this.sortType = "";
        optionMap = new HashMap<>();
    }

    public PostsOptionVO(String pageNo, String sortType, int pageStartNo, int pageEndNo, int pageCount, int postCountInPage) {
        this.pageNo = pageNo;
        this.sortType = sortType;
        this.pageStartNo = pageStartNo;
        this.pageEndNo = pageEndNo;
        this.pageCount = pageCount;
        this.postCountInPage = postCountInPage;

        if (pageNo == null)
            this.pageNo = "";
        if (sortType == null)
            this.sortType = "";
        if (pageCount < 0)
            pageCount = 1;

        optionMap = new HashMap<>();
    }

    public void setStartEndPageNo(int pageTotalCount, int displayPageCount) {
        this.pageStartNo = calcStartNo(displayPageCount);
        setPageCount(displayPageCount);
        if (this.pageStartNo > pageTotalCount)
            this.pageStartNo = 1;

        this.pageEndNo = this.pageStartNo;
        for (int i=0 ; i<displayPageCount ; i++) {
            if (this.pageEndNo >= pageTotalCount)
                break;
            if (this.pageEndNo >= displayPageCount)
                break;
            this.pageEndNo++;
        }
    }

    public int calcStartNo(int unit){
        try {
            int pageNo = Integer.parseInt(this.pageNo);
            int mulInt = 0;

            if (pageNo % unit == 0) mulInt = (int) pageNo / unit - 1;
            else mulInt = (int) pageNo / unit;

            return (int) mulInt * unit + 1;
        } catch (Exception e) {
            return 1;
        }
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

    public int getPageStartNo() {
        return pageStartNo;
    }

    public void setPageStartNo(int pageStartNo) {
        this.pageStartNo = pageStartNo;
    }

    public int getPageEndNo() {
        return pageEndNo;
    }

    public void setPageEndNo(int pageEndNo) {
        this.pageEndNo = pageEndNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPostCountInPage() {
        return postCountInPage;
    }

    public void setPostCountInPage(int postCountInPage) {
        this.postCountInPage = postCountInPage;
    }

    public Map<String, String> getOptionMap() {
        return optionMap;
    }

    public void setOptionMap(Map<String, String> optionMap) {
        this.optionMap = optionMap;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        PostsOptionVO vo = null;
        try {
            vo = (PostsOptionVO) obj;
        } catch (Exception e) {
            return false;
        }

        if (this.pageNo != vo.pageNo)
            return false;
        if (this.sortType != vo.sortType)
            return false;
        if (this.pageStartNo != vo.pageStartNo)
            return false;
        if (this.pageEndNo != vo.pageEndNo)
            return false;
        return true;
    }
}
