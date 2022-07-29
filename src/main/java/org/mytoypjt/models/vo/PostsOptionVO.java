package org.mytoypjt.models.vo;

public class PostsOptionVO {

    String pageNo = "";
    String sortType = "";
    int pageStartNo = 0;
    int pageEndNo = 0;
    int displayPageCount = 5;

    public PostsOptionVO() {
        this.pageNo = "";
        this.sortType = "";
        this.pageStartNo = 1;
        this.pageEndNo = 1;
    }

    public PostsOptionVO(String pageNo, String sortType) {
        this.pageNo = pageNo;
        this.sortType = sortType;
        this.pageStartNo = 1;
        this.pageEndNo = 1;
        this.displayPageCount = 5;
        if (pageNo == null)
            this.pageNo = "";
        if (sortType == null)
            this.sortType = "";
    }

    public PostsOptionVO(String pageNo, String sortType, int pageStartNo, int pageEndNo, int displayPageCount) {
        this.pageNo = pageNo;
        this.sortType = sortType;
        this.pageStartNo = pageStartNo;
        this.pageEndNo = pageEndNo;
        this.displayPageCount = displayPageCount;

        if (pageNo == null)
            this.pageNo = "";
        if (sortType == null)
            this.sortType = "";
        if (displayPageCount < 0)
            displayPageCount = 1;
    }

    public void setStartEndPageNo(int pageTotalCount, int displayPageCount) {
        this.pageStartNo = calcStartNo(displayPageCount);
        setDisplayPageCount(displayPageCount);
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

    public int getDisplayPageCount() {
        return displayPageCount;
    }

    public void setDisplayPageCount(int displayPageCount) {
        this.displayPageCount = displayPageCount;
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
