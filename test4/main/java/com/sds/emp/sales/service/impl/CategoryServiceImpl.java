package com.sds.emp.sales.service.impl;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import anyframe.common.Page;
import anyframe.core.idgen.IIdGenerationService;

import com.sds.emp.common.EmpException;
import com.sds.emp.domain.Category;
import com.sds.emp.sales.dao.CategoryDAO;
import com.sds.emp.sales.service.CategorySearchVO;
import com.sds.emp.sales.service.CategoryService;

//import com.sds.emp.sales.service.ProductService;

/**
 * This CategoryServiceImpl is implementing from CategoryService, this class contains
 * CategoryDAO,ProductService,IIdGenerationService setter methods and createCategory,
 * updateCategory,findCategoryList,getDropDownCategoryList and findCategory method.
 * 
 * @author Jeryeon Kim
 */

@Service("categoryService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class CategoryServiceImpl implements CategoryService {

	private static Log log = LogFactory.getLog(CategoryServiceImpl.class);

	/**
	 * categoryDAO is used to access the CategoryDAO methods createCategory, updateCategory, removeCategory,
	 * findCategory,findCategoryList and getDropDownCategoryList.
	 */
	@Resource(name = "categoryDAOQuery")
	// @Resource(name="categoryDAOHibernate")
	private CategoryDAO categoryDAO;

	/**
	 * productService is used to get the ProductService services.
	 */
	// @Resource
	// private ProductService productService;

	/**
	 * IIdGenerationService Object is used to get idGenerationService services.
	 */
	@Resource(name = "idGenerationServiceCategory")
	private IIdGenerationService idGenerationService;

	/**
	 * messageSource is used to call getMessage method.
	 */
	// well-known "resolvable dependencies" using
	// @Resource
	@Resource
	private MessageSource messageSource;

	public void createCategory(Category category) throws Exception {

		// Id generation Service
		// The related file is
		// [src/main/resource/spring/common/context-idgen.xml]
		String categoryNo = idGenerationService.getNextStringId();
		category.setCategoryNo(categoryNo);

		categoryDAO.createCategory(category);

		log.info("One category is created.");
	}

	public void updateCategory(Category category) throws Exception {

		// if ("Y".equals(category.getUseYn())
		// && productService.countProductListByCategory(category
		// .getCategoryNo()) > 0) {

		// throw new EmpException(messageSource.getMessage(
		// "error.category.update.if", new String[] {category
		// .getCategoryNo() }, Locale.getDefault()));
		// }
		log.info("Category that has no. '" + category.getCategoryNo() + "' is updated.");

		categoryDAO.updateCategory(category);
	}

	public void removeCategory(Category category) throws Exception {

		String categoryNo = category.getCategoryNo();

		// if (productService.countProductListByCategory(categoryNo) > 0) {

		// throw new EmpException(messageSource.getMessage(
		// "error.category.remove.if", new String[] {categoryNo }, Locale
		// .getDefault()));
		// }
		categoryDAO.removeCategory(category);
	}

	public Category findCategory(String categoryNo) throws Exception {

		Category category = categoryDAO.findCategory(categoryNo);

		if (category == null) {

			throw new EmpException(messageSource.getMessage("error.category.get.notexists", new String[] { categoryNo }, Locale
					.getDefault()));

		}
		return category;
	}

	public Page findCategoryList(CategorySearchVO searchVO) throws Exception {
		return categoryDAO.findCategoryList(searchVO);
	}

	public List getDropDownCategoryList() throws Exception {
		return categoryDAO.getDropDownCategoryList();
	}

	public void forTransactionTest(Category category) throws Exception {

		categoryDAO.updateCategory(category);

		// invoke the exception for test
		throw new EmpException(messageSource.getMessage("error.category.update.test",
				new String[] { category.getCategoryNo() }, Locale.getDefault()));

	}
}
