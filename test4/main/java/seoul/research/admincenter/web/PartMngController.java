package seoul.research.admincenter.web;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import seoul.research.admincenter.service.PartMngSearchVO;
import seoul.research.admincenter.service.PartMngService;
import anyframe.common.Page;

/**
 * 
 * : PanelController.java
 * 
 * -----------------------------------------
 * 
 * ----------------------------------------- 2013.09.18.
 * 
 */
@Controller
@SessionAttributes("partMng")
public class PartMngController {

	private static Log log = LogFactory.getLog(PartMngController.class);

	@Resource
	private PartMngService partMngService = null;
	protected HttpSession session = null;

	/**
	 * 
	 * 응답자 현황 조회
	 * 
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listPartMng.do")
	public ModelAndView list(PartMngSearchVO partMngSearchVO) throws Exception {

		log.info("===>>>  (1)  :   getPanelInfo : ");

		Collection pollMngList = partMngService.pollMngList(); // 여론조사목록

		Page resultPage = partMngService.listPartMng(partMngSearchVO);
		ModelAndView mav = new ModelAndView("listPartMng");
		mav.addObject("resultPage", resultPage);
		mav.addObject("pollMngList", pollMngList);
		return mav;
	}
}
