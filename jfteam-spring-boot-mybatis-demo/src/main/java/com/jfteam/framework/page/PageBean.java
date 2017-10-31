package com.jfteam.framework.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fengwenping on 2016/8/7.
 * 分页对象,主要与数据库进行交互
 */
public class PageBean<T> implements Serializable {

    private PageParam pageParam;

    private List<T> result;

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
