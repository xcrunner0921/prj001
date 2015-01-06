package seoul.research.poll.service.impl;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import seoul.research.poll.dao.SurveyResultDAO;
import seoul.research.poll.service.SurveyResultSearchVO;
import seoul.research.poll.service.SurveyResultService;
import seoul.research.domain.PollMng;
import seoul.research.domain.SurveyResult;
import anyframe.common.Page;

import com.sds.emp.common.EmpException;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 
 * 프로그램명 : SurveyResultServiceImpl.java
 * 설명 : 조사결과 
 *  
 * -----------------------------------------
 *    변경일            이름          설명 
 * -----------------------------------------
 * 2013.09.11.  하현숙    최초작성  
 * 
 */

@Service("surveyResultService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class SurveyResultServiceImpl implements SurveyResultService {

	private static Log log = LogFactory.getLog(SurveyResultServiceImpl.class);

	/**
	 * SurveyWebDAO is used to access the SurveyWebDAO methods createSurveyWeb, updateSurveyWeb, removeSurveyWeb, findSurveyWeb,findSurveyWebList and
	 * getDropDownSurveyWebList.
	 */
	@Resource(name = "surveyResultDAOQuery")
	private SurveyResultDAO surveyResultDAO;

	/**
	 * productService is used to get the ProductService services.
	 */
	 private SurveyResultService surveyResultService;

	/**
	 * messageSource is used to call getMessage method.
	 */
	@Resource
	private MessageSource messageSource;

	public Page findSurveyResultList(SurveyResultSearchVO searchVO) throws Exception {
		return surveyResultDAO.findSurveyResultList(searchVO);
	}
	
	public SurveyResult findSurveyResult(int pollIdx) throws Exception {

		SurveyResult SurveyResult = surveyResultDAO.findSurveyResult(pollIdx);

		if (SurveyResult == null) {

			throw new EmpException(messageSource.getMessage("error.SurveyWeb.get.notexists", new String[] { Integer
					.toString(pollIdx) }, Locale.getDefault()));

		}
		return SurveyResult;
	}
	
	public List getDropDownSurveyResultList() throws Exception {
		return surveyResultDAO.getDropDownSurveyResultList();
	}

	/**
	 * 메인 여론조사결과 조회
	 */
	public Page findSurveyResultMain(SurveyResultSearchVO searchVO) throws Exception {

		if (surveyResultDAO.findSurveyResultMain(searchVO) == null) {
			System.out.println("====>>  surveyResultService  : null  (87)");
		}
		
		return surveyResultDAO.findSurveyResultMain(searchVO);
		
	}
	

	/**
	 * 여론조사 결과 조회 팝업 
	 */
	public SurveyResult findSurveyResultPop(SurveyResultSearchVO surveyResultSearchVO) throws Exception {

		log.debug("===>> surveyResultService pollIdx : " + surveyResultSearchVO.getSearchPollIdx() );
		SurveyResult SurveyResult = surveyResultDAO.findSurveyResultPop(surveyResultSearchVO);

		if (SurveyResult == null) {
			System.out.println("====>>  surveyResultService  : null  (104)");
		}
		return SurveyResult;
	}
}
