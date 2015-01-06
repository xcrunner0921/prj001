package seoul.research.admincenter.dao;

import java.util.Collection;

import seoul.research.admincenter.service.PartMngSearchVO;
import anyframe.common.Page;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 
 * 프로그램명 : PartMngDAO.java
 * 설명 : 조사결과 
 *  
 * -----------------------------------------
 *    변경일            이름          설명 
 * -----------------------------------------
 * 2013.10.11.  하현숙    최초작성  
 * 
 */

public interface PartMngDAO {

	/**
	 * 응답자 현황 조회 
	 * 
	 * @param searchVO
	 *            searchVO is used to get the details
	 * @return page page contains the SurveyWebList
	 * @throws Exception
	 *             Exception will raised, when findSurveyWebList failed
	 */
	Page listPartMng(PartMngSearchVO partMngSearchVO) throws Exception;
	

	/**
	 * 여론조사 목록 
	 */
	public Collection pollMngList() throws Exception;

}
