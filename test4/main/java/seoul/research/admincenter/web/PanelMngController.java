package seoul.research.admincenter.web;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import seoul.research.admincenter.service.PanelMngSearchVO;
import seoul.research.admincenter.service.PanelMngService;
import anyframe.common.Page;

/**
 * 
 * : PanelMngController.java
 * 
 * -----------------------------------------
 * 
 * ----------------------------------------- 2013.10.12.
 * 
 */
@Controller
@SessionAttributes("panelMng")
public class PanelMngController {

	private static Log log = LogFactory.getLog(PanelMngController.class);

	@Resource
	private PanelMngService panelMngService = null;
	protected HttpSession session = null;

	/**
	 * 
	 * 패널 현황 조회
	 * 
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listPanelMng.do")
	public ModelAndView list(PanelMngSearchVO panelMngSearchVO) throws Exception {

		log.info("===>>>  (1)  :   listPanelMngController : ");

		ModelAndView mav = new ModelAndView("listPanelMng");
		Page resultPage = null;

		if (null == panelMngSearchVO.getSearchRegDatestart() || "".equals(panelMngSearchVO.getSearchRegDatestart())) {

		}
		else {
			/*
			 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); Date date = new Date(); String today =
			 * sdf.format(date);
			 * 
			 * if (panelMngSearchVO.getSearchRegDatestart() == null) { if (!today.substring(5, 7).equals("01")) {
			 * panelMngSearchVO.setSearchRegDatestart(today.substring(0, 4) + "-" + ((Integer.parseInt(today.substring(5, 7)) -
			 * 1 >= 10) ? "" + (Integer.parseInt(today.substring(5, 7)) - 1) : "0" + (Integer.parseInt(today.substring(5, 7)) -
			 * 1)) + "-01"); } else { panelMngSearchVO.setSearchRegDatestart((Integer.parseInt(today.substring(0, 4)) - 1) +
			 * "-12-01"); } } if (panelMngSearchVO.getSearchRegDateend() == null) { panelMngSearchVO.setSearchRegDateend(today);
			 * }
			 */

			resultPage = panelMngService.listPanelMng(panelMngSearchVO);

		}
		mav.addObject("resultPage", resultPage);
		return mav;
	}

	@RequestMapping("/excelPanelMng.do")
	public String excel(PanelMngSearchVO panelMngSearchVO, Map map) throws Exception {

		// ModelAndView mav = new ModelAndView("listPanelMng");
		Collection panelList = null;

		if (null == panelMngSearchVO.getSearchRegDatestart() || "".equals(panelMngSearchVO.getSearchRegDatestart())) {
		}
		else {
			/*
			 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); Date date = new Date(); String today =
			 * sdf.format(date);
			 * 
			 * if (panelMngSearchVO.getSearchRegDatestart() == null) { if (!today.substring(5, 7).equals("01")) {
			 * panelMngSearchVO.setSearchRegDatestart(today.substring(0, 4) + "-" + ((Integer.parseInt(today.substring(5, 7)) -
			 * 1 >= 10) ? "" + (Integer.parseInt(today.substring(5, 7)) - 1) : "0" + (Integer.parseInt(today.substring(5, 7)) -
			 * 1)) + "-01"); } else { panelMngSearchVO.setSearchRegDatestart((Integer.parseInt(today.substring(0, 4)) - 1) +
			 * "-12-01"); } } if (panelMngSearchVO.getSearchRegDateend() == null) { panelMngSearchVO.setSearchRegDateend(today);
			 * }
			 */
			panelList = panelMngService.findPanelList(panelMngSearchVO);
		}

		map.put("panelList", panelList);
		return "excelPanelMngView";
	}
}
