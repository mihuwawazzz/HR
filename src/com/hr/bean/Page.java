package com.hr.bean;

import java.util.List;

/**
 * 这个类用于分页查询用的，超酷
 * @param <T>
 */
public class Page<T> {
    private int currentPage;        //当前页面
    private List<T> list;           //查询的集合
    private int pageSize;           //每页行数
    private long maxRows;           //最大数

    public Page() {
    }

    public Page(int currentPage, List<T> list, int pageSize, long maxRows) {
        this.currentPage = currentPage;
        this.list = list;
        this.pageSize = pageSize;
        this.maxRows = maxRows;
    }

    public int getCurrentPage() {
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > getTotalPage()) {
            currentPage = getTotalPage();
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(long maxRows) {
        this.maxRows = maxRows;
    }

    public int getTotalPage() {
        int totalPage = (int) maxRows / pageSize;
        if (maxRows % pageSize != 0) {
            totalPage++;
        }
        return totalPage;
    }

}
