package seoul.research.admincenter.web;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import seoul.research.admincenter.service.AnswerMngSearchVO;
import seoul.research.admincenter.service.AnswerMngService;
import anyframe.common.Page;

import com.sds.emp.code.service.CodeService;

/**
 * controller class for CRUD(create, read, update, delete) a pollMng. this class apply to prevent double form submit about add,
 * update, delete.
 * 
 * @author Heewon Jung
 */
@Controller
@SessionAttributes("answerMng")
public class AnswerMngController {
	@Resource
	private AnswerMngService answerMngService = null;

	@Resource
	private CodeService codeService = null;

	/**
	 * get list of pollMng
	 * 
	 * @param pollMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listAnswerMng.do")
	public ModelAndView list(AnswerMngSearchVO answerMngSearchVO) throws Exception {
		// System.out.println(pollMngSearchVO.getSearchRegDatestart() + "," + pollMngSearchVO.getSearchRegDateend() + ","
		// + pollMngSearchVO.getSearchPollStop() + "," + pollMngSearchVO.getSearchPollAsk() + ","
		// + pollMngSearchVO.getSearchCondition() + "," + pollMngSearchVO.getSearchKeyword());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);

		if (answerMngSearchVO.getSearchRegDatestart() == null) {
			if (!today.substring(5, 7).equals("01")) {
				answerMngSearchVO.setSearchRegDatestart(today.substring(0, 4)
						+ "-"
						+ ((Integer.parseInt(today.substring(5, 7)) - 1 >= 10) ? ""
								+ (Integer.parseInt(today.substring(5, 7)) - 1) : "0"
								+ (Integer.parseInt(today.substring(5, 7)) - 1)) + "-01");
			}
			else {
				answerMngSearchVO.setSearchRegDatestart((Integer.parseInt(today.substring(0, 4)) - 1) + "-12-01");
			}
		}
		if (answerMngSearchVO.getSearchRegDateend() == null) {
			answerMngSearchVO.setSearchRegDateend(today);
		}

		Page resultPage = answerMngService.findAnswerMngList(answerMngSearchVO);

		Collection askCodeList = codeService.findAskCodeList();

		ModelAndView mav = new ModelAndView("listAnswerMng");
		mav.addObject("answerMngSearchVO", answerMngSearchVO);
		mav.addObject("resultPage", resultPage);

		mav.addObject("askCodeList", askCodeList);
		return mav;
	}
}
