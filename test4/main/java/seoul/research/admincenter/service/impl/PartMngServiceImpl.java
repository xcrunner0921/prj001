package seoul.research.admincenter.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import seoul.research.admincenter.dao.PartMngDAO;
import seoul.research.admincenter.service.PartMngSearchVO;
import seoul.research.admincenter.service.PartMngService;
import anyframe.common.Page;

/**
 * 응답자현황 조회 
 * : PartMngServiceImpl.java
 * 
 *  
 * -----------------------------------------
 *   
 * -----------------------------------------
 * 2013.10.11. 
 * 
 */

@Service("partMngService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class PartMngServiceImpl implements PartMngService {

	private static Log log = LogFactory.getLog(PartMngServiceImpl.class);

	/**
	 * 
	 */
	@Resource(name = "partMngDAOQuery")
	private PartMngDAO partMngDAO;

	 
	@Resource
	private MessageSource messageSource;


	public Page listPartMng(PartMngSearchVO partMngSearchVO) throws Exception {
		return partMngDAO.listPartMng(partMngSearchVO);
	}

	/**
	 * 여론조사 목록 
	 */
	public Collection pollMngList() throws Exception{
		return partMngDAO.pollMngList();
	}
}
