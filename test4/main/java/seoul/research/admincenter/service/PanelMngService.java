package seoul.research.admincenter.service;

import java.util.Collection;

import anyframe.common.Page;

/**
 * 패널 현황 조회 : PanelMngService.java
 * 
 * -----------------------------------------
 * 
 * ----------------------------------------- 2013.10.11.
 * 
 */

public interface PanelMngService {

	/**
	 * 패널 현황 조회
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */

	Page listPanelMng(PanelMngSearchVO panelMngSearchVO) throws Exception;

	Collection findPanelList(PanelMngSearchVO panelMngSearchVO) throws Exception;
}
