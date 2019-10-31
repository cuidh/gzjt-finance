package cn.gzjt.finance.domain;

import java.util.List;

/**
 * 分页通用参数
 *
 * @author jianwei.zhou
 * @date 2019/10/18 8:59
 */
public class PageBean<T> {
    //总记录数
    public int totalCount;
    //总页码
    public int totalPage;
    //每页数据list
    public List<T> list;
    //当前页码
    public int curPage;
    //每页显示条数
    public int rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", curPage=" + curPage +
                ", rows=" + rows +
                '}';
    }
}
