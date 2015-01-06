package com.sds.emp.sales.service;

import com.sds.emp.common.SearchVO;

/**
 * This CategorySearchVO entity class is inheritance class of
 * com.sds.emp.common.SearchVO. And it contains
 * searchUseYn attribute for product list search and
 * putter,setter methods of it.
 * 
 * @author Jeryeon Kim
 */
public class CategorySearchVO extends SearchVO {

    private static final long serialVersionUID = 1L;
    
    private String searchUseYn;

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public String toString() {
        StringBuffer arguments = new StringBuffer();
        arguments.append(" searchCondition - " + getSearchCondition() + "\n");
        arguments.append(" searchKeyword - " + getSearchKeyword() + "\n");
        arguments.append(" pageIndex - " + getPageIndex() + "\n");
        arguments.append(" searchUseYn - " + searchUseYn + "\n");

        return arguments.toString();
    }

}
