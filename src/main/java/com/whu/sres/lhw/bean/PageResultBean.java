package com.whu.sres.lhw.bean;

import lombok.Data;

import java.util.List;

/**
 * Desc: 分页结果
 * Created by lvhongwei on 2018/4/14.
 */
@Data
public class PageResultBean<T> {

    public PageResultBean(){

    }

    /** pageSize，每页记录数 */
    private int size;

    /** pageNumber，第几页 */
    private int number;

    /** 总页数 */
    private int totalPages;

    /** 记录总数 */
    private long totalElements;

    /** 是否首页 */
    private boolean first;

    /** 是否尾页 */
    private boolean last;

    /** */
    private int numberOfElements;

    /** 排序 */
//    private List<Sort.Order> sort;

    /** 数据内容 */
    private List<T> content;
}
