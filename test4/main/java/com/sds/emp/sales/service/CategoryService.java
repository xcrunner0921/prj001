package com.sds.emp.sales.service;

import java.util.List;

import anyframe.common.Page;

import com.sds.emp.domain.Category;


/**
 * This CategoryService interface class contains
 * declaration of createCategory, updateCategory,
 * removeCategory, findCategoryList,
 * getDropDownCategoryList and findCategory method.
 * 
 * @author Jeryeon Kim
 */
public interface CategoryService {

    /**
     * @param category
     *        This createCategory is used to add new
     *        Category with
     *        categoryNo,categoryName,categoryDesc,useYn,regId
     * @return String
     * @throws Exception
     *         Exception will raise, when
     *         createCategory method failed
     */
    void createCategory(Category category) throws Exception;

    /**
     * @param category
     *        This updateCategory method is used to
     *        update the category data
     * @throws Exception
     *         Exception will be raise, when
     *         updateCategory failed
     */
    void updateCategory(Category category) throws Exception;

    /**
     * @param category
     *        This removeCategory method is used to
     *        remove the category data
     * @throws Exception
     *         Exception will be raise, when
     *         removeCategory failed
     */
    void removeCategory(Category category) throws Exception;

    /**
     * @param searchVO
     *        This findCategoryList method is used to
     *        get list of Category with
     *        propertiesService Services
     * @return page
     * @throws Exception
     *         Exception will raised, when
     *         findCategoryList failed
     */
    Page findCategoryList(CategorySearchVO searchVO) throws Exception;

    /**
     * @param categoryNo
     *        This findCategory method is used to the
     *        Category details of specified categoryNo
     * @return Category
     * @throws Exception
     *         Exception will raised, when
     *         findCategoryList failed
     */
    Category findCategory(String categoryNo) throws Exception;

    /**
     * This getDropDownCategoryList method is used to
     * generate list based on category id or category
     * name.
     * @return List
     * @throws Exception
     *         Exception will raised, when
     *         getDropDownCategoryList failed
     */
    List getDropDownCategoryList() throws Exception;

    /**
     * This forTransactionTest method is used to test
     * for transaction function
     * @param category
     *        This forTransactionTest method is used to
     *        create the category data
     * @throws Exception
     *         Exception will raised
     */
    void forTransactionTest(Category category) throws Exception;

}
