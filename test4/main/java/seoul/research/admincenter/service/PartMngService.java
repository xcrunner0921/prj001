package seoul.research.admincenter.service;


import java.util.Collection;

import seoul.research.admincenter.service.PartMngSearchVO;
import anyframe.common.Page;


/**
 *  응답자 현황 조회 
 *  : PartMngService.java  
 *  
 * -----------------------------------------
 *     
 * -----------------------------------------
 * 2013.10.11.   
 * 
 */

public interface PartMngService {

	/**
	 * 응답자 현황 조회 
	 * @param 
	 * @return  
	 * @throws Exception
	 */
	
	Page listPartMng(PartMngSearchVO partMngSearchVO) throws Exception;

	/**
	 * 여론조사 목록 
	 */
	Collection pollMngList() throws Exception;
}
