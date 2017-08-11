package com.white.util;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * BootStrap 分页
 */
public class BootStrapPage<T> implements Serializable {

    // 当前页码
    private int pageNumber = 1;
    // 每页数据量
    private int pageSize = 10;
    // 总条数
    private Integer total;
    // 当前显示的数据
    private List<T> rows;

    public BootStrapPage(HttpServletRequest request) {
        // 开始的数据行数
        String pageNumber = request.getParameter("pageNumber");
        // 每页的数据数
        String pageSize = request.getParameter("pageSize");

        this.setPageNumber(Integer.parseInt(pageNumber));
        this.setPageSize(Integer.parseInt(pageSize));

    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
