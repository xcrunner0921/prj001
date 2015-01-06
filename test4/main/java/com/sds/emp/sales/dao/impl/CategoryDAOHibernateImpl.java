package com.sds.emp.sales.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import anyframe.common.Page;
import anyframe.core.hibernate.IDynamicHibernateService;
import anyframe.core.properties.IPropertiesService;

import com.sds.emp.common.EmpUtil;
import com.sds.emp.domain.Category;
import com.sds.emp.sales.dao.CategoryDAO;
import com.sds.emp.sales.service.CategorySearchVO;

/**
 * This DAO implements class contains
 * IDynamicHibernateService to get
 * IDynamicHibernateService services and
 * IPropertiesService to get IPropertiesService
 * services , createCategory, updateCategory,
 * findCategory, findCategoryList, and
 * getDropDownCategoryList methods for different
 * functionality on Category.
 * 
 * @author Jeryeon Kim
 */
@Repository("categoryDAOHibernate")
public class CategoryDAOHibernateImpl extends HibernateDaoSupport implements
        CategoryDAO {

    /** an instance variable for the propertiesService. */
    @Resource
    private IPropertiesService propertiesService;

    /**
     * This dynamicHibernateService is used to get
     * dynamicHibernateService services
     */
    @Resource
    private IDynamicHibernateService dynamicHibernateService;

    @Resource
    private SessionFactory sessionFactory;

    @PostConstruct
    public void initialize() {
        setSessionFactory(this.sessionFactory);
    }

    public void createCategory(Category category) throws Exception {
        category.setRegDate(new Date());
        this.getHibernateTemplate().save(category);
    }

    public void updateCategory(Category category) throws Exception {
        category.setModifyDate(new Date());
        this.getHibernateTemplate().update(category);
    }

    public void removeCategory(Category category) throws Exception {
        this.getHibernateTemplate().delete(category);
    }

    public Category findCategory(String categoryNo) throws Exception {
        return (Category) this.getHibernateTemplate().get(Category.class,
            categoryNo);
    }

    public Page findCategoryList(CategorySearchVO searchVO) throws Exception {
        int pageIndex = searchVO.getPageIndex();
        int pageSize = propertiesService.getInt("PAGE_SIZE");
        int pageUnit = propertiesService.getInt("PAGE_UNIT");

        String searchCondition =
            EmpUtil.null2str(searchVO.getSearchCondition());
        String searchKeyword = EmpUtil.null2str(searchVO.getSearchKeyword());

        Object[] args = new Object[2];

        if (("".equals(searchCondition) || "0".equals(searchCondition)))
            args[0] = "categoryNo=%" + searchKeyword + "%";
        else
            args[0] = "categoryName=%" + searchKeyword + "%";

        if (searchVO.getSearchUseYn() != null
            && !"A".equals(searchVO.getSearchUseYn()))
            args[1] = "useYn=" + searchVO.getSearchUseYn();

        List resultList =
            dynamicHibernateService.findList("findCategoryList", args,
                pageIndex, pageSize);

        // get total count
        Long totalSize =
            (Long) dynamicHibernateService.find("countCategoryList", args);

        Page resultPage =
            new Page(resultList, pageIndex, totalSize.intValue(), pageUnit,
                pageSize);

        return resultPage;
    }

    public List getDropDownCategoryList() throws Exception {
        return this.getHibernateTemplate().findByNamedQuery(
            "getDropDownCategoryList");
    }

}
