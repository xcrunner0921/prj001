package com.sds.emp.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This Category entity class contains all attributes
 * of CATEGORY table and putter,setter methods of them.
 * And it contains toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class Category implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String categoryNo;
    private String categoryName;
    private String categoryDesc;
    private String useYn;
    private String regId;
    private Date regDate;
    private String modifyId;
    private Date modifyDate;
    private Set products = new HashSet(0);

    public Category() {
    }

    public Category(String categoryNo, String categoryName) {
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
    }

    public Category(String categoryNo, String categoryName,
            String categoryDesc, String useYn, String regId, Date regDate,
            String modifyId, Date modifyDate, Set products) {
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.useYn = useYn;
        this.regId = regId;
        this.regDate = regDate;
        this.modifyId = modifyId;
        this.modifyDate = modifyDate;
        this.products = products;
    }

    public String getCategoryNo() {
        return this.categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return this.categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getUseYn() {
        return this.useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getRegId() {
        return this.regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Date getRegDate() {
        return this.regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getModifyId() {
        return this.modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set getProducts() {
        return this.products;
    }

    public void setProducts(Set products) {
        this.products = products;
    }

    public String toString() {
        StringBuffer arguments = new StringBuffer();
        arguments.append(" categoryNo - " + categoryNo + "\n");
        arguments.append(" categoryName - " + categoryName + "\n");
        arguments.append(" categoryDesc - " + categoryDesc + "\n");
        arguments.append(" useYn - " + useYn + "\n");
        arguments.append(" regId - " + regId + "\n");
        arguments.append(" regDate - " + regDate + "\n");
        arguments.append(" modifyId - " + modifyId + "\n");
        arguments.append(" modifyDate - " + modifyDate);

        return arguments.toString();
    }

}
