package seoul.research.poll.service;

import seoul.research.domain.Panel;
import seoul.research.domain.PollMng;


/**
 *  
 *  : PanelService.java  
 *  
 * -----------------------------------------
 *     
 * -----------------------------------------
 * 2013.09.18.   
 * 
 */

public interface PanelService {

	/**
	 * @param 
	 * @return  
	 * @throws Exception
	 */
	Panel getPanelInfo(String userId) throws Exception;

	// 
	int getPanelCount(String userId) throws Exception;
	
	// (N->Y)
	void updatePanelDelflagN(Panel panel) throws Exception;
	
	// (Y->N)
	void updatePanelDelflagY(Panel panel) throws Exception;
	
	
	//패널가입.탈퇴 등 
	void createPanel(Panel panel) throws Exception;
	

	//패널가입 후 회원통합정보 insert  
	void createTbmem001(Panel panel) throws Exception;
	
	// 회원통합정보 
	int getTbmem001Count(String userId) throws Exception;
	
}
