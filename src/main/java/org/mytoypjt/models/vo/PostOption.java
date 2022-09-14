package org.mytoypjt.models.vo;

import java.util.HashMap;
import java.util.Map;

public class PostOption {

    String pageNo = "";
    String sortType = "";
    int pageStartNo = 0;
    int pageEndNo = 0;
    int pageCountLimit = 5;
    int postCountLimitInPage = 12;
    Map<String, String> optionMap;

    public PostOption() {
        this.pageNo = "";
        this.sortType = "";
        this.pageStartNo = 1;
        this.pageEndNo = 1;
        optionMap = new HashMap<>();
    }

    public PostOption(String pageNo, String sortType) {
        this.pageNo = pageNo;
        this.sortType = sortType;
        this.pageStartNo = 1;
        this.pageEndNo = 1;
        this.pageCountLimit = 5;
        this.postCountLimitInPage = 12;

        if (pageNo == null)
            this.pageNo = "";
        if (sortType == null)
            this.sortType = "";
        optionMap = new HashMap<>();
    }

    public PostOption(String pageNo, String sortType, int pageStartNo, int pageEndNo, int pageCountLimit, int postCountLimitInPage) {
        this.pageNo = pageNo;
        this.sortType = sortType;
        this.pageStartNo = pageStartNo;
        this.pageEndNo = pageEndNo;
        this.pageCountLimit = pageCountLimit;
        this.postCountLimitInPage = postCountLimitInPage;

        if (pageNo == null)
            this.pageNo = "";
        if (sortType == null)
            this.sortType = "";
        if (pageCountLimit < 0)
            pageCountLimit = 1;

        optionMap = new HashMap<>();
    }

    public void setStartEndPageNo(int postCount) {
        int pageCount = postCount / postCountLimitInPage;
        if (postCount % postCountLimitInPage != 0)
            pageCount++;

        pageStartNo = calcStartNo(pageCountLimit);
        setPageCountLimit(pageCountLimit);

        if (pageStartNo > postCount)
            pageStartNo = 1;

        pageEndNo = pageStartNo;
        for (int i = 0; i< pageCount; i++) {
            if (pageEndNo >= pageCount)
                break;
            if (pageEndNo >= pageCountLimit)
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

    public void setPostCountLimitInMainPage(){
        this.pageCountLimit = 5;
        this.postCountLimitInPage = 12;
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

    public int getPageCountLimit() {
        return pageCountLimit;
    }

    public void setPageCountLimit(int pageCountLimit) {
        this.pageCountLimit = pageCountLimit;
    }

    public int getPostCountLimitInPage() {
        return postCountLimitInPage;
    }

    public void setPostCountLimitInPage(int postCountLimitInPage) {
        this.postCountLimitInPage = postCountLimitInPage;
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

        PostOption vo = null;
        try {
            vo = (PostOption) obj;
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
