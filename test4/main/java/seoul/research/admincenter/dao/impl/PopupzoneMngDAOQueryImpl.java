package seoul.research.admincenter.dao.impl;

import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import seoul.research.admincenter.dao.PopupzoneMngDAO;
import seoul.research.admincenter.service.PopupzoneMngSearchVO;
import seoul.research.domain.PollPart;
import seoul.research.domain.PopupMng;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

/**
 * This DAO implements class contains IQueryService to get IQueryService services and IPropertiesService to get
 * IPropertiesService services , createPopupzoneMng, updatePopupzoneMng, findPopupzoneMng, findPopupzoneMngList, and
 * getDropDownPopupzoneMngList methods for different functionality on PollMng.
 * 
 * @author Jeryeon Kim
 */
@Repository("popupzoneMngDAOQuery")
public class PopupzoneMngDAOQueryImpl extends AbstractDAO implements PopupzoneMngDAO {

	private static Log log = LogFactory.getLog(PopupzoneMngDAOQueryImpl.class);
	
	/** an instance variable for the propertiesService. */
	@Resource
	private IPropertiesService propertiesService;

	@Resource
	private IQueryService queryService;

	@PostConstruct
	public void initialize() {
		setQueryService(this.queryService);
	}

	public Page findPopupzoneMngList(PopupzoneMngSearchVO searchVO) throws Exception {
        int pageIndex = searchVO.getPageIndex();
		int pageSize = propertiesService.getInt("PAGE_SIZE");
		int pageUnit = propertiesService.getInt("PAGE_UNIT");
		
		return findListWithPaging("PopupzoneMng", searchVO, pageIndex, pageSize, pageUnit);
	}

	public void createPopupzoneAddfile(PopupMng popupMng) throws Exception {
		
		popupMng.setStrDate(new Date(Integer.parseInt(popupMng.getStrDateYmd().substring(0, 4)) - 1900, Integer
				.parseInt(popupMng.getStrDateYmd().substring(5, 7)) - 1, Integer.parseInt(popupMng.getStrDateYmd()
				.substring(8, 10)), Integer.parseInt(popupMng.getStrDateHour()), Integer.parseInt(popupMng
				.getStrDateMinute())));
		popupMng.setEndDate(new Date(Integer.parseInt(popupMng.getEndDateYmd().substring(0, 4)) - 1900, Integer
				.parseInt(popupMng.getEndDateYmd().substring(5, 7)) - 1, Integer.parseInt(popupMng.getEndDateYmd()
				.substring(8, 10)), Integer.parseInt(popupMng.getEndDateHour()), Integer.parseInt(popupMng
				.getEndDateMinute())));

		log.info("===============QueryImple StartDate : " + popupMng.getStrDate());
		log.info("===============QueryImple endDate : " + popupMng.getEndDate());
		
		create("PopupzoneAddfile", popupMng);
	}
	
	public void updatePopupzoneAddfile1(PopupMng popupMng) throws Exception {
			popupMng.setStrDate(new Date(Integer.parseInt(popupMng.getStrDateYmd().substring(0, 4)) - 1900, Integer
					.parseInt(popupMng.getStrDateYmd().substring(5, 7)) - 1, Integer.parseInt(popupMng.getStrDateYmd()
					.substring(8, 10)), Integer.parseInt(popupMng.getStrDateHour()), Integer.parseInt(popupMng
					.getStrDateMinute())));
			popupMng.setEndDate(new Date(Integer.parseInt(popupMng.getEndDateYmd().substring(0, 4)) - 1900, Integer
					.parseInt(popupMng.getEndDateYmd().substring(5, 7)) - 1, Integer.parseInt(popupMng.getEndDateYmd()
					.substring(8, 10)), Integer.parseInt(popupMng.getEndDateHour()), Integer.parseInt(popupMng
					.getEndDateMinute())));
			update("PopupMng", popupMng);
	}

	public void updatePopupzoneAddfile2(PopupMng popupMng) throws Exception {
		
		popupMng.setStrDate(new Date(Integer.parseInt(popupMng.getStrDateYmd().substring(0, 4)) - 1900, Integer
				.parseInt(popupMng.getStrDateYmd().substring(5, 7)) - 1, Integer.parseInt(popupMng.getStrDateYmd()
				.substring(8, 10)), Integer.parseInt(popupMng.getStrDateHour()), Integer.parseInt(popupMng
				.getStrDateMinute())));
		popupMng.setEndDate(new Date(Integer.parseInt(popupMng.getEndDateYmd().substring(0, 4)) - 1900, Integer
				.parseInt(popupMng.getEndDateYmd().substring(5, 7)) - 1, Integer.parseInt(popupMng.getEndDateYmd()
				.substring(8, 10)), Integer.parseInt(popupMng.getEndDateHour()), Integer.parseInt(popupMng
				.getEndDateMinute())));
	
		update("PopupMng", popupMng);
	}
	
    public Collection findPorderList(PopupMng popupMng) throws Exception {
		return findList("Porder", popupMng);

    }
    
    public void updatePopupzoneMng(PopupMng popupMng) throws Exception {
    	int TotalCnt = popupMng.getTotalCnt();
    	int uTotalCnt = popupMng.getTotalCnt();
    	int Porder = popupMng.getPorder();
    	int BeforePorder = popupMng.getBeforePorder();
    	int Cnt = 0;
    	int uCnt = 0;
    	for(int i=1; i<=TotalCnt; i++){
    		if(i >= Porder){
    			log.info("=====plus=====");
	    		uCnt = uTotalCnt - Cnt;
				popupMng.setPorder(uCnt);
	        	update("PopupMngPlus", popupMng);
	        	Cnt++;
    		}else if((i > Porder)){
    			log.info("=====minus=====");
	    		uCnt = uTotalCnt - Cnt;
    			popupMng.setPorder(uCnt);
	        	update("PopupMngMinus", popupMng);
    		}
    	}
    	
    }
    
    public PopupMng findDetailPopupzoneMng(PopupMng popupMng) throws Exception {
    	
		return (PopupMng) findByPk("DetailPopupzone", popupMng);
	}
    
    
    
    /**
     * 삭제 
     */
	public void removePopupzoneMng (PopupMng popupMng) throws Exception {

		remove("PopupMng", popupMng);
	}

	//porder == 1 인 경우 
	public void updatePorderFirst(PopupMng popupMng) throws Exception{
		update("PopupMngFirst", popupMng);
	}
	
	//porder > 1 and porder < 5  인 경우
	public void updatePorderMid(PopupMng popupMng) throws Exception{
		update("PopupMngMid", popupMng);
	}
	//등록 
	public void createPopupzone(PopupMng popupMng) throws Exception {
		popupMng.setStrDate(new Date(Integer.parseInt(popupMng.getStrDateYmd().substring(0, 4)) - 1900, Integer
				.parseInt(popupMng.getStrDateYmd().substring(5, 7)) - 1, Integer.parseInt(popupMng.getStrDateYmd()
				.substring(8, 10)), Integer.parseInt(popupMng.getStrDateHour()), Integer.parseInt(popupMng
				.getStrDateMinute())));
		popupMng.setEndDate(new Date(Integer.parseInt(popupMng.getEndDateYmd().substring(0, 4)) - 1900, Integer
				.parseInt(popupMng.getEndDateYmd().substring(5, 7)) - 1, Integer.parseInt(popupMng.getEndDateYmd()
				.substring(8, 10)), Integer.parseInt(popupMng.getEndDateHour()), Integer.parseInt(popupMng
				.getEndDateMinute())));

		log.info("===============QueryImple StartDate (157) : " + popupMng.getStrDate());
		log.info("===============QueryImple endDate   (158) : " + popupMng.getEndDate());
		
		create("Popupzone", popupMng);
	}
	
	//수정 
	public void updatePopupzoneMngSec(PopupMng popupMng) throws Exception {
		
	}
	
	//순서를 1->2로 변경 
	public int updatePorderToLarge(PopupMng popupMng) throws Exception{
		int rtnValue = -1;
		rtnValue =  update("PopmngPorderToLarge", popupMng);
		
		return rtnValue;
	}
	//순서를4->2로 변경 
	public int updatePorderToSmall(PopupMng popupMng) throws Exception{
		int rtnValue = -1;
		rtnValue =  update("PopmngPorderToSmall", popupMng);
		
		return rtnValue;
	}
	
	//메인 팝업존 
	public Page listMain() throws Exception {
        int pageIndex = 1;//searchVO.getPageIndex();
		int pageSize = 1;//propertiesService.getInt("PAGE_SIZE");
		int pageUnit = propertiesService.getInt("PAGE_UNIT");

		log.debug("=====>>  /main.do  메인 : 팝업존  DAO Query ServiceImpl  시작 ");
		return findListWithPaging("PopupzoneMngMain", pageIndex, pageSize, pageUnit);
	}

}
