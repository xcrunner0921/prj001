package com.sds.emp.sales.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import anyframe.common.Page;

import com.sds.emp.domain.Category;
import com.sds.emp.sales.service.CategorySearchVO;
import com.sds.emp.sales.service.CategoryService;

/**
 * controller class for CRUD(create, read, update,
 * delete) a category. this class apply to prevent
 * double form submit about add, update, delete.
 * @author Heewon Jung
 */
@Controller
@SessionAttributes("category")
public class CategoryController {
    
    @Resource
    private CategoryService categoryService = null;

    /**
     * display add category view
     * @return
     * @throws Exception
     */
    @RequestMapping("/addCategoryView.do")
    public ModelAndView addView() throws Exception {
        ModelAndView mnv =
            new ModelAndView("addCategoryView", "category", new Category());
        return mnv;
    }

    /**
     * add category
     * @param category
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/addCategory.do")
    public ModelAndView add(Category category, SessionStatus status)
            throws Exception {
        categoryService.createCategory(category);
        status.setComplete();
        
        return new ModelAndView("listCategoryAction");
    }

    /**
     * get detail of category
     * @param categoryNo
     * @param categorySearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/getCategory.do")
    public ModelAndView get(@RequestParam("categoryNo")
            String categoryNo, CategorySearchVO categorySearchVO)
            throws Exception {

        Category category = categoryService.findCategory(categoryNo);
        ModelAndView mav = new ModelAndView("getCategory");
        mav.addObject("category", category);
        mav.addObject("categorySearchVO", categorySearchVO);
        return mav;

    }

    /**
     * update category
     * @param session
     * @param category
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateCategory.do")
    public ModelAndView update(HttpSession session, Category category,
            SessionStatus status) throws Exception {
        String modifyId = (String) session.getAttribute("userId");
        category.setModifyId(modifyId);
        categoryService.updateCategory(category);
        status.setComplete();
        return new ModelAndView("listCategoryAction");
    }

    /**
     * get list of category
     * @param categorySearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/listCategory.do")
    public ModelAndView list(CategorySearchVO categorySearchVO)
            throws Exception {
        Page resultPage = categoryService.findCategoryList(categorySearchVO);
        System.out.println("resultPage surveyWeb : " + resultPage);
        ModelAndView mav = new ModelAndView("listCategory");
        mav.addObject("categorySearchVO", categorySearchVO);
        mav.addObject("page", resultPage);
        return mav;
    }

    /**
     * delete category
     * @param category
     * @param categorySearchVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteCategory.do")
    public ModelAndView delete(@RequestParam("categoryNo")
    String categoryNo, CategorySearchVO categorySearchVO)
            throws Exception {
        Category category = new Category();
        category.setCategoryNo(categoryNo);
        categoryService.removeCategory(category);
        
        return new ModelAndView("listCategoryAction", "categorySearchVO",
            categorySearchVO);
    }
}
