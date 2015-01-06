package seoul.research.poll.dao.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import seoul.research.domain.SurveyResult;
import seoul.research.poll.dao.SurveyResultDAO;
import seoul.research.poll.service.SurveyResultSearchVO;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

import com.sds.emp.common.EmpUtil;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 
 * 프로그램명 : SurveyResultDAOQueryImpl.java
 * 설명 : 조사결과 
 *  
 * -----------------------------------------
 *    변경일            이름          설명 
 * -----------------------------------------
 * 2013.09.11.  하현숙    최초작성  
 * 
 */

@Repository("surveyResultDAOQuery")
public class SurveyResultDAOQueryImpl extends AbstractDAO implements SurveyResultDAO {

	private static Log log = LogFactory.getLog(SurveyResultDAOQueryImpl.class);

	/** an instance variable for the propertiesService. */
	@Resource
	private IPropertiesService propertiesService;

	@Resource
	private IQueryService queryService;

	@PostConstruct
	public void initialize() {
		setQueryService(this.queryService);
	}

	public SurveyResult findSurveyResult(int pollIdx) throws Exception {

		SurveyResult surveyResult = new SurveyResult();
		surveyResult.setPollIdx(pollIdx);

		return (SurveyResult) findByPk("SurveyResult", surveyResult);
	}

	/**
	 * 여론조사 결과 
	 */
	public Page findSurveyResultList(SurveyResultSearchVO searchVO) throws Exception {
		
		log.debug("--  SurveyResultDAOQueryImpl (1) : ");

		String searchCondition = EmpUtil.null2str(searchVO.getSearchCondition());
		String searchPollTitle = EmpUtil.null2str(searchVO.getSearchPollTitle());

		if ("1".equals(searchCondition)) { // 부서명
			searchVO.setSearchPollAsk(searchPollTitle);
		}
		else if ("2".equals(searchCondition)) { // 제목
			searchVO.setSearchPollTitle(searchPollTitle);
		}

		int pageIndex = searchVO.getPageIndex();
		int pageSize = propertiesService.getInt("PAGE_SIZE");
		int pageUnit = propertiesService.getInt("PAGE_UNIT");

		return findListWithPaging("SurveyResult", searchVO, pageIndex, pageSize, pageUnit);
	}

	public List getDropDownSurveyResultList() throws Exception {
		return (List) getQueryService().find("findDropDownSurveyResultList", new Object[] {});
	}
	
	/**
	 * Poll_Idx 구하기
	 */
	public int getNextPollIdx() throws Exception {
		Collection collection = getQueryService().find("findSurveyResultList", new Object[] {});
		Iterator iterator = collection.iterator();
		if (iterator.hasNext()) {
			Map map = (Map) iterator.next();
			String pollIdx = map.get("pollIdx").toString();
			return Integer.parseInt(pollIdx);
		}
		return 0;
	}
	
	/**
	 * 메인 여론조사결과조회 
	 */
	public Page findSurveyResultMain(SurveyResultSearchVO searchVO) throws Exception {
		
		log.debug(" ==>>  SurveyResultDAOQueryImpl 109 : " + (searchVO.getSearchPollTitle())); 
		//SurveyResult surveyResult = new SurveyResult();
		//surveyResult.setPollTitle(searchVO.getSearchPollTitle());
		
		int pageSize = propertiesService.getInt("PAGE_SIZE");
		int pageUnit = propertiesService.getInt("PAGE_UNIT");

		return findListWithPaging("SurveyResultMain", searchVO, 1, pageSize, pageUnit);
		
	}
	

	/**
	 * 여론조사결과조회 팝업
	 */
	public SurveyResult findSurveyResultPop(SurveyResultSearchVO searchVO) throws Exception {
		
		log.debug(" ==>>  SurveyResultDAOQueryImpl 130 : " + (searchVO.getSearchPollIdx())); 
		
		return (SurveyResult) findByPk("SurveyResultPop", searchVO);		
	}
	
}
