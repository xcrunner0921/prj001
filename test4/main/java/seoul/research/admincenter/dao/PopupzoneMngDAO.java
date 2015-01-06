package seoul.research.admincenter.dao;

import java.util.Collection;

import seoul.research.admincenter.service.PopupzoneMngSearchVO;
import seoul.research.domain.PopupMng;
import anyframe.common.Page;

/**
 * This DAO interface contains declaration of addPopupzoneMng, updatePopupzoneMng, findPopupzoneMng, findPopupzoneMngList and
 * getDropDownPopupzoneMngList methods for different functionality on PollMng.
 * 
 * @author Jeryeon Kim
 */
public interface PopupzoneMngDAO {

	/**
	 * This findPopupzoneMngList method is used to get list of PollMng with propertiesService Services from database
	 * 
	 * @param searchVO
	 *            searchVO is used to get the details
	 * @return page page contains the PopupzoneMngList
	 * @throws Exception
	 *             Exception will raised, when findPopupzoneMngList failed
	 */
	Page findPopupzoneMngList(PopupzoneMngSearchVO searchVO) throws Exception;


//pjh start
	void createPopupzoneAddfile(PopupMng popupMng) throws Exception;

    Collection findPorderList(PopupMng popupMng) throws Exception;

	void updatePopupzoneMng(PopupMng popupMng) throws Exception;

	PopupMng findDetailPopupzoneMng(PopupMng popupMng) throws Exception;

	void updatePopupzoneAddfile1(PopupMng popupMng) throws Exception;
	void updatePopupzoneAddfile2(PopupMng popupMng) throws Exception;
	
//pjh end
	void removePopupzoneMng(PopupMng popupMng) throws Exception;

	void updatePorderFirst(PopupMng popupMng) throws Exception;
	void updatePorderMid(PopupMng popupMng) throws Exception;
	void createPopupzone(PopupMng popupMng) throws Exception;
	void updatePopupzoneMngSec(PopupMng popupMng) throws Exception;
	//순서를 1->2로 변경 
	int updatePorderToLarge(PopupMng popupMng) throws Exception;
	//순서를 4->2로 변경 
	int updatePorderToSmall(PopupMng popupMng) throws Exception;
	//메인 팝업존 
	Page listMain() throws Exception;
	
}
