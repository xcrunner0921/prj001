package seoul.research.poll.service.impl;

import java.util.ArrayList;
import java.util.Collection;
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

import seoul.research.domain.PollMng;
import seoul.research.domain.QuestMng;
import seoul.research.domain.SurveyWeb;
import seoul.research.poll.dao.SurveyWebDAO;
import seoul.research.poll.service.SurveyWebSearchVO;
import seoul.research.poll.service.SurveyWebService;

//import com.sds.emp.sales.service.ProductService;

/**
 * This SurveyWebServiceImpl is implementing from SurveyWebService, this class contains
 * SurveyWebDAO,ProductService,IIdGenerationService setter methods and createSurveyWeb,
 * updateSurveyWeb,findSurveyWebList,getDropDownSurveyWebList and findSurveyWeb method.
 * 
 * @author Jeryeon Kim
 */

@Service("surveyWebService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class SurveyWebServiceImpl implements SurveyWebService {

	private static Log log = LogFactory.getLog(SurveyWebServiceImpl.class);

	/**
	 * surveyWebDAO is used to access the SurveyWebDAO methods createSurveyWeb, updateSurveyWeb, removeSurveyWeb,
	 * findSurveyWeb,findSurveyWebList and getDropDownSurveyWebList.
	 */
	@Resource(name = "surveyWebDAOQuery")
	private SurveyWebDAO surveyWebDAO;


	/**
	 * messageSource is used to call getMessage method.
	 */
	@Resource
	private MessageSource messageSource;

	public void createSurveyWeb(SurveyWeb surveyWeb) throws Exception {

		surveyWebDAO.createSurveyWeb(surveyWeb);

		log.info("One surveyWeb is created.");
	}

	public void updateSurveyWeb(SurveyWeb surveyWeb) throws Exception {

		// if ("Y".equals(surveyWeb.getUseYn())
		// && productService.countProductListBySurveyWeb(surveyWeb
		// .getSurveyWebNo()) > 0) {

		// throw new EmpException(messageSource.getMessage(
		// "error.surveyWeb.update.if", new String[] {surveyWeb
		// .getSurveyWebNo() }, Locale.getDefault()));
		// }
		log.info("SurveyWeb that has no. '" + surveyWeb.getPollIdx() + "' is updated.");

		surveyWebDAO.updateSurveyWeb(surveyWeb);
	}

	public void removeSurveyWeb(SurveyWeb surveyWeb) throws Exception {

		int pollIdx = surveyWeb.getPollIdx();

		// if (productService.countProductListBySurveyWeb(surveyWebNo) > 0) {

		// throw new EmpException(messageSource.getMessage(
		// "error.surveyWeb.remove.if", new String[] {surveyWebNo }, Locale
		// .getDefault()));
		// }
		surveyWebDAO.removeSurveyWeb(surveyWeb);
	}

	public SurveyWeb findSurveyWeb(int pollIdx) throws Exception {
		SurveyWeb surveyWeb = surveyWebDAO.findSurveyWeb(pollIdx);
/*
		if (surveyWeb == null) {
			throw new EmpException(messageSource.getMessage("error.surveyWeb.get.notexists", new String[] { pollIdx }, Locale.getDefault()));
		}
*/
		return surveyWeb;
	}

	public Page findSurveyWebList(SurveyWebSearchVO searchVO) throws Exception {
		return surveyWebDAO.findSurveyWebList(searchVO);
	}
	
	
	//문제출제
	public Page findSurveyQuestList(SurveyWebSearchVO searchVO) throws Exception {
		return surveyWebDAO.findSurveyQuestList(searchVO);
	}
/*	
	//다음문제
	public Page addQuestNextView(SurveyWebSearchVO searchVO) throws Exception {
		return surveyWebDAO.addQuestNextView(searchVO);
	}
*/
	public List getDropDownSurveyWebList() throws Exception {
		return surveyWebDAO.getDropDownSurveyWebList();
	}

	public void forTransactionTest(SurveyWeb surveyWeb) throws Exception {

		surveyWebDAO.updateSurveyWeb(surveyWeb);

		// invoke the exception for test
		//throw new EmpException(messageSource.getMessage("error.surveyWeb.update.test", new String[] { surveyWeb.getPollIdx() }, Locale.getDefault()));
	}
	
	//설문조사 질문타이틀 가져오기
	public SurveyWeb findQuestTitle(int pollIdx, int qusetNo) throws Exception {

		SurveyWeb surveyWeb = surveyWebDAO.findQuestTitle(pollIdx, qusetNo);

		if (surveyWeb == null) {
			throw new EmpException(messageSource.getMessage("error.surveyWeb.get.notexists", new String[] { Integer.toString(pollIdx) }, Locale.getDefault()));
		}
		return surveyWeb;
	}
	
	//설문조사 타이틀 가져오기 
	public SurveyWeb findQuestExitTitle(SurveyWeb surveyWeb) throws Exception {
		surveyWebDAO.findQuestExitTitle(surveyWeb);
		return surveyWeb;
	}
	
	//스크리닝 타이틀 가져오기 
	public SurveyWeb findQuestPassTitle(SurveyWeb surveyWeb) throws Exception {
		surveyWebDAO.findQuestPassTitle(surveyWeb);
		return surveyWeb;
	}
	
	//설문조사 panel확인 
	public SurveyWeb findSurveyUserChk(SurveyWeb surveyWeb) throws Exception {
		surveyWeb = surveyWebDAO.findSurveyUserChk(surveyWeb);
    	System.out.println("Imple PanelCount : " + surveyWeb.getPanelCount());
		return surveyWeb;
	}
}
