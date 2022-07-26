package org.mytoypjt.models.vo;

public class PostsOptionVO {

    String pageNo = "";
    String sortType = "";
    int pageStartNo = 0;
    int pageEndNo = 0;

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
        if (pageNo == null)
            this.pageNo = "";
        if (sortType == null)
            this.sortType = "";
    }

    public PostsOptionVO(String pageNo, String sortType, int pageStartNo, int pageEndNo) {
        this.pageNo = pageNo;
        this.sortType = sortType;
        this.pageStartNo = pageStartNo;
        this.pageEndNo = pageEndNo;

        if (pageNo == null)
            this.pageNo = "";
        if (sortType == null)
            this.sortType = "";
    }

    public void setStartEndPageNo(int pageTotalCount, int unit) {
        this.pageStartNo = calcStartNo(unit);
        if (this.pageStartNo > pageTotalCount)
            this.pageStartNo = 1;

        this.pageEndNo = this.pageStartNo;
        for (int i=0 ; i<unit ; i++) {
            if (this.pageEndNo >= pageTotalCount)
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
