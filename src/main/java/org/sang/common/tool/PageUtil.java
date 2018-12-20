package org.sang.common.tool;

import java.util.List;

public class PageUtil {

    //当前页数
    private int pageNo;
    //一页显示的条数
    private int pageSize;
    //总条数
    private int totalCount;
    //总页数
    private int totalPage;
    //数据
    private List list;

    public PageUtil(int pageNo,int pageSize,int totalCount){
        setPageSize(pageSize);
        setTotalCount(totalCount);
        setPageNo(pageNo);
    }

    public int getPage(){
        return (pageNo-1)*pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo<1) {
            this.pageNo=1;
        }else if(pageNo>this.totalPage){
            this.pageNo=this.totalPage;
        }else {
            this.pageNo = pageNo;
        }
        if(this.totalPage==0){
            this.pageNo=1;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if (totalCount>0) {
            totalPage=(totalCount%pageSize==0)?totalCount/pageSize:totalCount/pageSize+1;
        }
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
