package seoul.research.admincenter.dao.impl;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import seoul.research.admincenter.dao.PartMngDAO;
import seoul.research.admincenter.service.PartMngSearchVO;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 
 * 프로그램명 : PartMngDAOQueryImpl.java
 * 설명 : 조사결과 
 *  
 * -----------------------------------------
 *    변경일            이름          설명 
 * -----------------------------------------
 * 2013.10.11.  하현숙    최초작성  
 * 
 */

@Repository("partMngDAOQuery")
public class PartMngDAOQueryImpl extends AbstractDAO implements PartMngDAO {

	private static Log log = LogFactory.getLog(PartMngDAOQueryImpl.class);

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
	 * 응답자현황 조회  
	 */
	public Page listPartMng(PartMngSearchVO partMngSearchVO) throws Exception {
		
		log.debug("--  PartMngDAOQueryImpl (1) : ");

		int pageIndex = partMngSearchVO.getPageIndex();
		int pageSize = propertiesService.getInt("PAGE_SIZE");
		int pageUnit = propertiesService.getInt("PAGE_UNIT");

		return findListWithPaging("PartMng", partMngSearchVO, pageIndex, pageSize, pageUnit);
	}

	/**
	 * 여론조사 목록 
	 */
	public Collection pollMngList() throws Exception{
		
		log.debug("--  PartMngDAOQueryImpl (67) : ");

		Collection collection = getQueryService().find("pollMngList", new Object[] {});
		return collection;
	}
	
}
