package seoul.research.admincenter.dao;

import java.util.Collection;

import seoul.research.admincenter.service.PanelMngSearchVO;
import anyframe.common.Page;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 프로그램명 : PanelMngDAO.java 설명 : 패널현황 조회
 * 
 * ----------------------------------------- 변경일 이름 설명 ----------------------------------------- 2013.10.11. 하현숙 최초작성
 * 
 */

public interface PanelMngDAO {

	/**
	 * 패널 현황 조회
	 * 
	 * @param searchVO
	 *            searchVO is used to get the details
	 * @return page page contains the SurveyWebList
	 * @throws Exception
	 *             Exception will raised, when findSurveyWebList failed
	 */
	Page listPanelMng(PanelMngSearchVO panelMngSearchVO) throws Exception;

	Collection findPanelList(PanelMngSearchVO panelMngSearchVO) throws Exception;
}
