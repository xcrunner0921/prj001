package seoul.research.poll.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

import com.sds.emp.common.EmpUtil;

import seoul.research.domain.ExampleMng;
import seoul.research.domain.PollMng;
import seoul.research.domain.QuestMng;
import seoul.research.domain.SurveyWeb;
import seoul.research.poll.dao.SurveyWebDAO;
import seoul.research.poll.service.SurveyWebSearchVO;

/**
 * This DAO implements class contains
 * IQueryService to get IQueryService
 * services and IPropertiesService to get
 * IPropertiesService services , createSurveyWeb,
 * updateSurveyWeb, findSurveyWeb, findSurveyWebList, and
 * getDropDownSurveyWebList methods for different
 * functionality on SurveyWeb.
 * 
 * @author Jeryeon Kim
 */
@Repository("surveyWebDAOQuery")
public class SurveyWebDAOQueryImpl extends AbstractDAO implements SurveyWebDAO {

    /** an instance variable for the propertiesService. */
    @Resource
    private IPropertiesService propertiesService;

    @Resource
    private IQueryService queryService;

    @PostConstruct
    public void initialize() {
        setQueryService(this.queryService);
    }

    public void createSurveyWeb(SurveyWeb surveyWeb) throws Exception {
    	//ANSWER_MNG Table
        create("SurveyWeb", surveyWeb);
        
    	//PANEL_STATE Table
        create("SurveyWeb2", surveyWeb);

    }

    public void updateSurveyWeb(SurveyWeb surveyWeb) throws Exception {
        
        update("SurveyWeb", surveyWeb);

    }

    public void removeSurveyWeb(SurveyWeb surveyWeb) throws Exception {

        remove("SurveyWeb", surveyWeb);
    }

    public SurveyWeb findSurveyWeb(int pollIdx) throws Exception {
    	//System.out.println("pollIdx : " + pollIdx);
        SurveyWeb surveyWeb = new SurveyWeb();
        surveyWeb.setPollIdx(pollIdx);

        return (SurveyWeb) findByPk("SurveyWeb", surveyWeb);
    }

    //여론조사 리스트
    public Page findSurveyWebList(SurveyWebSearchVO searchVO) throws Exception {
        int pageIndex = searchVO.getPageIndex();
        int pageSize = propertiesService.getInt("PAGE_SIZE");
        int pageUnit = propertiesService.getInt("PAGE_UNIT");

        String searchCondition	= EmpUtil.null2str(searchVO.getSearchCondition());
        String searchKeyword 	= EmpUtil.null2str(searchVO.getSearchKeyword());
        String searchCount 		= EmpUtil.null2str(searchVO.getSearchCount());

        SurveyWeb surveyWeb = new SurveyWeb();
        
        //System.out.println("searchCondition : " + searchCondition);
        //System.out.println("searchKeyword : " + searchKeyword);
        //System.out.println("searchCount : " + searchCount);

        //출력수
        pageSize = Integer.parseInt(searchCount);
        
        if ("".equals(searchCount) || "0".equals(searchCount)) {
        	pageSize = 10;
    	}
        
        //검색조건
        if ("1".equals(searchCondition)) {
			surveyWeb.setPollDivisionName(searchKeyword);
		}else if ("2".equals(searchCondition)) {
			surveyWeb.setPollTitle(searchKeyword);
		}else{
			surveyWeb.setSearchKeyword(searchKeyword);
		}
        
        String userId = searchVO.getUserId();
        surveyWeb.setUserId(userId);
        
        //System.out.println("@@@userId@@@ : " + userId);
        
        return findListWithPaging("SurveyWeb", surveyWeb, pageIndex, pageSize, pageUnit);

    }
    
    //문제읽어오기
    public Page findSurveyQuestList(SurveyWebSearchVO searchVO) throws Exception {
        int pageIndex = searchVO.getPageIndex();
        int pageSize = 100;
        int pageUnit = 100;

        return findListWithPaging("SurveyQuest", searchVO, pageIndex, pageSize, pageUnit);

    }

    public List getDropDownSurveyWebList() throws Exception {
        return (List) getQueryService().find("findDropDownSurveyWebList", new Object[] {});
    }
    
    //설문조사 질문타이틀 가져오기
    public SurveyWeb findQuestTitle(int pollIdx, int questNo) throws Exception {

    	SurveyWeb surveyWeb = new SurveyWeb();
    	surveyWeb.setPollIdx(pollIdx);
    	surveyWeb.setQuestNo(questNo);
    	
    	surveyWeb = (SurveyWeb) findByPk("QuestTitle", surveyWeb);
    	
    	String[] positionArry = surveyWeb.getPosition().split(",");
    	//System.out.println("===============surveyWeb.getOrgFname2() : " + surveyWeb.getOrgFname2());
    	//System.out.println("===============surveyWeb.getSavFname2() : " + surveyWeb.getSavFname2());
    	//System.out.println("===============surveyWeb.getFilePath2() : " + surveyWeb.getFilePath2());
    	//System.out.println("===============surveyWeb.getFileSize2() : " + surveyWeb.getFileSize2());
    	
    	String[] orgFname2Arry = surveyWeb.getOrgFname2().split(",");
    	String[] savFname2Arry = surveyWeb.getSavFname2().split(",");
    	String[] filePath2Arry = surveyWeb.getFilePath2().split(",");
    	String[] fileSize2Arry = surveyWeb.getFileSize2().split(",");
    	
    	//System.out.println("===============positionArry.length : " + positionArry.length);
    	if(positionArry.length == 1){
    		if(("D").equals(positionArry[0].toString())){

    	    	//System.out.println("=============== 문항 아래  : " + positionArry[0].toString());
    			//System.out.println("=============== setOrgFname2_1 : " + orgFname2Arry[0].toString());
    			//System.out.println("=============== setSavFname2_1 : " + savFname2Arry[0].toString());
    			//System.out.println("=============== setFilePath2_1  : " + filePath2Arry[0].toString());
        		surveyWeb.setPosition_1(positionArry[0]);
        		surveyWeb.setOrgFname2_1(orgFname2Arry[0]);
        		surveyWeb.setSavFname2_1(savFname2Arry[0]);
        		surveyWeb.setFilePath2_1(filePath2Arry[0]);
        		surveyWeb.setFileSize2_1(fileSize2Arry[0]);
        		
    		}else{
    			//System.out.println("=============== 문항 위 : " + positionArry[0].toString());
    			//System.out.println("=============== setOrgFname2_2 : " + orgFname2Arry[0].toString());
    			//System.out.println("=============== setSavFname2_2 : " + savFname2Arry[0].toString());
    			//System.out.println("=============== setFilePath2_2  : " + filePath2Arry[0].toString());
        		surveyWeb.setPosition_2(positionArry[0]);
        		surveyWeb.setOrgFname2_2(orgFname2Arry[0]);
        		surveyWeb.setSavFname2_2(savFname2Arry[0]);
        		surveyWeb.setFilePath2_2(filePath2Arry[0]);
        		surveyWeb.setFileSize2_2(fileSize2Arry[0]);
    		}
    	}else if(positionArry.length== 2){

    		surveyWeb.setPosition_1(positionArry[0]);
    		surveyWeb.setOrgFname2_1(orgFname2Arry[0]);
    		surveyWeb.setSavFname2_1(savFname2Arry[0]);
    		surveyWeb.setFilePath2_1(filePath2Arry[0]);
    		surveyWeb.setFileSize2_1(fileSize2Arry[0]);

    		//System.out.println("=============== 문항 아래  : " + positionArry[0].toString());
    		//System.out.println("=============== setOrgFname2_1 : " + orgFname2Arry[0].toString());
    		//System.out.println("=============== setSavFname2_1 : " + savFname2Arry[0].toString());
    		//System.out.println("=============== setFilePath2_1  : " + filePath2Arry[0].toString());
	    	
    		surveyWeb.setPosition_2(positionArry[1]);
    		surveyWeb.setOrgFname2_2(orgFname2Arry[1]);
    		surveyWeb.setSavFname2_2(savFname2Arry[1]);
    		surveyWeb.setFilePath2_2(filePath2Arry[1]);
    		surveyWeb.setFileSize2_2(fileSize2Arry[1]);

    		//System.out.println("=============== 문항 위 : " + positionArry[1].toString());
    		//System.out.println("=============== setOrgFname2_2 : " + orgFname2Arry[1].toString());
    		//System.out.println("=============== setSavFname2_2 : " + savFname2Arry[1].toString());
    		//System.out.println("=============== setFilePath2_2  : " + filePath2Arry[1].toString());
    	}
    
    	//System.out.println("===============getPosition_1 : " + surveyWeb.getPosition_1());
    	//System.out.println("===============getPosition_2 : " + surveyWeb.getPosition_2());
		return surveyWeb;
	}

	//설문조사 타이틀 가져오기  
    public SurveyWeb findQuestExitTitle(SurveyWeb surveyWeb) throws Exception {
		return (SurveyWeb) findByPk("QuestExitTitle", surveyWeb);
	}
    
    //스크리닝 타이틀 가져오기
    public SurveyWeb findQuestPassTitle(SurveyWeb surveyWeb) throws Exception {
		return (SurveyWeb) findByPk("QuestPassTitle", surveyWeb);
	}
    
	//설문조사 panel확인   
    public SurveyWeb findSurveyUserChk(SurveyWeb surveyWeb) throws Exception {
    	surveyWeb = (SurveyWeb) findByPk("SurveyUserChk", surveyWeb);
    	System.out.println("DAO PanelCount : " + surveyWeb.getPanelCount());
		return surveyWeb;
	}
    
}
