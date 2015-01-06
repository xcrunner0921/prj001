package seoul.research.admincenter.dao.impl;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import seoul.research.admincenter.dao.PanelMngDAO;
import seoul.research.admincenter.service.PanelMngSearchVO;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 프로그램명 : PanelMngDAOQueryImpl.java 설명 : 패널현황
 * 
 * ----------------------------------------- 변경일 이름 설명 ----------------------------------------- 2013.10.12. 하현숙 최초작성
 * 
 */

@Repository("panelMngDAOQuery")
public class PanelMngDAOQueryImpl extends AbstractDAO implements PanelMngDAO {

	private static Log log = LogFactory.getLog(PanelMngDAOQueryImpl.class);

	/** an instance variable for the propertiesService. */
	@Resource
	private IPropertiesService propertiesService;

	@Resource
	private IQueryService queryService;

	@PostConstruct
	public void initialize() {
		setQueryService(this.queryService);
	}

	/**
	 * 패널현황 조회
	 */
	public Page listPanelMng(PanelMngSearchVO panelMngSearchVO) throws Exception {

		log.debug("--  PanelMngDAOQueryImpl (1) : ");

		int pageIndex = panelMngSearchVO.getPageIndex();
		int pageSize = propertiesService.getInt("PAGE_SIZE");
		int pageUnit = propertiesService.getInt("PAGE_UNIT");

		return findListWithPaging("PanelMng", panelMngSearchVO, pageIndex, pageSize, pageUnit);
	}

	public Collection findPanelList(PanelMngSearchVO panelMngSearchVO) throws Exception {
		return findList("PanelMng2", panelMngSearchVO);
	}
}
