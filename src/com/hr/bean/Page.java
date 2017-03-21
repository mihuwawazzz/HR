package com.hr.bean;

import java.util.List;

/**
 * ��������ڷ�ҳ��ѯ�õģ�����
 * @param <T>
 */
public class Page<T> {
    private int currentPage;        //��ǰҳ��
    private List<T> list;           //��ѯ�ļ���
    private int pageSize;           //ÿҳ����
    private long maxRows;           //�����

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
