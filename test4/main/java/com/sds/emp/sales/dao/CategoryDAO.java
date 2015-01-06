package com.sds.emp.sales.dao;

import java.util.List;

import anyframe.common.Page;

import com.sds.emp.domain.Category;
import com.sds.emp.sales.service.CategorySearchVO;

/**
 * This DAO interface contains declaration of
 * addCategory, updateCategory, findCategory,
 * findCategoryList and getDropDownCategoryList methods
 * for different functionality on Category.
 * 
 * @author Jeryeon Kim
 */
public interface CategoryDAO {

    /**
     * This createCategory is used to add new Category
     * to the database with
     * categoryNo,categoryName,categoryDesc,useYn,regId
     * @param category
     *        category parameter is used to get the
     *        category details
     * @throws Exception
     *         Exception will raise, when
     *         createCategory method failed
     */
    void createCategory(Category category) throws Exception;

    /**
     * This updateCategory method is used to update the
     * category data to database
     * @param category
     *        category is used to get the category
     *        details
     * @throws Exception
     *         Exception will be raise, when
     *         updateCategory failed
     */
    void updateCategory(Category category) throws Exception;

    /**
     * This removeCategory method is used to remove the
     * category data from database
     * @param category
     *        category is used to get the category
     *        details
     * @throws Exception
     *         Exception will be raise, when
     *         removeCategory failed
     */
    void removeCategory(Category category) throws Exception;

    /**
     * This findCategoryList method is used to get list
     * of Category with propertiesService Services from
     * database
     * @param searchVO
     *        searchVO is used to get the details
     * @return page page contains the CategoryList
     * @throws Exception
     *         Exception will raised, when
     *         findCategoryList failed
     */
    Page findCategoryList(CategorySearchVO searchVO) throws Exception;

    /**
     * This findCategory method is used to the Category
     * details of specified categoryNo from database
     * @param categoryNo
     *        categoryNo is used to get the details of
     *        specified category Number
     * @return Category Category is contains category
     *         details
     * @throws Exception
     *         Exception will raised, when
     *         findgetCategory failed
     */
    Category findCategory(String categoryNo) throws Exception;

    /**
     * This getDropDownCategoryList method is used to
     * generate list based on category id or category
     * name from database
     * @return List Drop down list of category values
     * @throws Exception
     *         Exception will raised, when
     *         getDropDownCategoryList failed
     */
    List getDropDownCategoryList() throws Exception;
}
