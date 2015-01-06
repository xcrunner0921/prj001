package seoul.research.admincenter.service;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import seoul.research.domain.PopupMng;
import anyframe.common.Page;

/**
 * This PopupzoneMngService interface class contains declaration of createPopupzoneMng, updatePopupzoneMng, removePopupzoneMng,
 * findPopupzoneMngList, getDropDownPopupzoneMngList and findPopupzoneMng method.
 * 
 * @author Jeryeon Kim
 */
public interface PopupzoneMngService {

	/**
	 * @param searchVO
	 *            This findPopupzoneMngList method is used to get list of PollMng with propertiesService Services
	 * @return page
	 * @throws Exception
	 *             Exception will raised, when findPopupzoneMngList failed
	 */
	Page findPopupzoneMngList(PopupzoneMngSearchVO searchVO) throws Exception;

//pjh start
	void createPopupzoneAddfile(PopupMng popupMng, MultipartFile multipartFile) throws Exception;
	
	Collection findPorderList(PopupMng popupMng) throws Exception;
	
	void updatePopupzoneMng(PopupMng popupMng) throws Exception;

    PopupMng findDetailPopupzoneMng(PopupMng popupMng) throws Exception;

    void updatePopupzoneAddfile1(PopupMng popupMng, MultipartFile multipartFile) throws Exception;
    
    void updatePopupzoneAddfile2(PopupMng popupMng) throws Exception;
//pjh end
    
    /**
     * 수정 
     */
    void updatePopupzoneMng(HttpServletRequest request, PopupMng popupMng) throws Exception;
    

	/**
	 * 삭제
	 * 
	 * @throws Exception
	 *             Exception will be raise, when removePopupzoneMng failed
	 */
	void removePopupzoneMng(PopupMng popupMng) throws Exception;

	/**
	 * 메인 팝업존 
	 * @return
	 * @throws Exception
	 */
	Page listMain() throws Exception;
}
