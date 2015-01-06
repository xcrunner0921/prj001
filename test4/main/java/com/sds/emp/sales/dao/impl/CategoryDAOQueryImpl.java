package com.sds.emp.sales.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

import com.sds.emp.common.EmpUtil;
import com.sds.emp.domain.Category;
import com.sds.emp.sales.dao.CategoryDAO;
import com.sds.emp.sales.service.CategorySearchVO;

/**
 * This DAO implements class contains
 * IQueryService to get IQueryService
 * services and IPropertiesService to get
 * IPropertiesService services , createCategory,
 * updateCategory, findCategory, findCategoryList, and
 * getDropDownCategoryList methods for different
 * functionality on Category.
 * 
 * @author Jeryeon Kim
 */
@Repository("categoryDAOQuery")
public class CategoryDAOQueryImpl extends AbstractDAO implements CategoryDAO {

    /** an instance variable for the propertiesService. */
    @Resource
    private IPropertiesService propertiesService;

    @Resource
    private IQueryService queryService;

    @PostConstruct
    public void initialize() {
        setQueryService(this.queryService);
    }

    public void createCategory(Category category) throws Exception {
        
        create("Category", category);

    }

    public void updateCategory(Category category) throws Exception {
        
        update("Category", category);

    }

    public void removeCategory(Category category) throws Exception {

        remove("Category", category);
    }

    public Category findCategory(String categoryNo) throws Exception {

        Category category = new Category();
        category.setCategoryNo(categoryNo);

        return (Category) findByPk("Category", category);
    }

    public Page findCategoryList(CategorySearchVO searchVO) throws Exception {
        int pageIndex = searchVO.getPageIndex();
        int pageSize = propertiesService.getInt("PAGE_SIZE");
        int pageUnit = propertiesService.getInt("PAGE_UNIT");

        String searchCondition =
            EmpUtil.null2str(searchVO.getSearchCondition());
        String searchKeyword = EmpUtil.null2str(searchVO.getSearchKeyword());

        Category category = new Category();

        if (("".equals(searchCondition) || "0".equals(searchCondition))) {

            category.setCategoryNo(searchKeyword);

        } else {

            category.setCategoryName(searchKeyword);
        }

        if (searchVO.getSearchUseYn() != null
            && !"A".equals(searchVO.getSearchUseYn())) {

            String useYn = searchVO.getSearchUseYn();
            category.setUseYn(useYn);

        }

        return findListWithPaging("Category", category, pageIndex, pageSize,
            pageUnit);

    }

    public List getDropDownCategoryList() throws Exception {
        return (List) getQueryService().find("findDropDownCategoryList",
            new Object[] {});
    }

}
