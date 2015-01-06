package seoul.research.admincenter.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import seoul.research.admincenter.dao.AnswerMngDAO;
import seoul.research.admincenter.service.AnswerMngSearchVO;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

/**
 * This DAO implements class contains IQueryService to get IQueryService services and IPropertiesService to get
 * IPropertiesService services , createPollMng, updatePollMng, findPollMng, findPollMngList, and getDropDownPollMngList methods
 * for different functionality on PollMng.
 * 
 * @author Jeryeon Kim
 */
@Repository("answerMngDAOQuery")
public class AnswerMngDAOQueryImpl extends AbstractDAO implements AnswerMngDAO {

	/** an instance variable for the propertiesService. */
	@Resource
	private IPropertiesService propertiesService;

	@Resource
	private IQueryService queryService;

	@PostConstruct
	public void initialize() {
		setQueryService(this.queryService);
	}

	public Page findAnswerMngList(AnswerMngSearchVO searchVO) throws Exception {
		// String searchRegDatestart = EmpUtil.null2str(searchVO.getSearchRegDatestart());
		// String searchRegDateend = EmpUtil.null2str(searchVO.getSearchRegDateend());
		// String searchCondition = EmpUtil.null2str(searchVO.getSearchCondition());
		// String searchKeyword = EmpUtil.null2str(searchVO.getSearchKeyword());
		/*
		 * if (!"".equals(searchRegDatestart)) { searchVO.setSearchRegDatestart(searchRegDatestart + " 00:00:00"); } if
		 * (!"".equals(searchRegDateend)) { searchVO.setSearchRegDateend(searchRegDateend + " 23:59:59"); }
		 */
		if ("F".equals(searchVO.getSearchCondition())) {
			searchVO.setSearchPollFieldName(searchVO.getSearchKeyword());
		}
		else if ("T".equals(searchVO.getSearchCondition())) {
			searchVO.setSearchPollTitle(searchVO.getSearchKeyword());
		}

		// int pageIndex = searchVO.getPageIndex();
		int pageSize = propertiesService.getInt("PAGE_SIZE");
		int pageUnit = propertiesService.getInt("PAGE_UNIT");

		return findListWithPaging("PollMng3", searchVO, searchVO.getPageIndex(), pageSize, pageUnit);
	}
}
