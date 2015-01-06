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
import seoul.research.domain.MyPageJoin;
import seoul.research.poll.dao.MyPageJoinDAO;
import seoul.research.poll.service.MyPageJoinSearchVO;

/**
 * This DAO implements class contains
 * IQueryService to get IQueryService
 * services and IPropertiesService to get
 * IPropertiesService services , createMyPageJoin,
 * updateMyPageJoin, findMyPageJoin, findMyPageJoinList, and
 * getDropDownMyPageJoinList methods for different
 * functionality on MyPageJoin.
 * 
 * @author Jeryeon Kim
 */
@Repository("myPageJoinDAOQuery")
public class MyPageJoinDAOQueryImpl extends AbstractDAO implements MyPageJoinDAO {

    /** an instance variable for the propertiesService. */
    @Resource
    private IPropertiesService propertiesService;

    @Resource
    private IQueryService queryService;

    @PostConstruct
    public void initialize() {
        setQueryService(this.queryService);
    }

    
    //내가 참여한 여론조사 리스트
    public Page findMyPageJoinList(MyPageJoinSearchVO searchVO) throws Exception {
        int pageIndex = searchVO.getPageIndex();
        int pageSize = propertiesService.getInt("PAGE_SIZE");
        int pageUnit = propertiesService.getInt("PAGE_UNIT");

        String searchCondition	= EmpUtil.null2str(searchVO.getSearchCondition());
        String searchKeyword 	= EmpUtil.null2str(searchVO.getSearchKeyword());
        String searchCount 		= EmpUtil.null2str(searchVO.getSearchCount());

        MyPageJoin myPageJoin = new MyPageJoin();
        
        System.out.println("searchCondition : " + searchCondition);
        System.out.println("searchKeyword : " + searchKeyword);
        System.out.println("searchCount : " + searchCount);

        //출력수
        pageSize = Integer.parseInt(searchCount);
        
        if ("".equals(searchCount) || "0".equals(searchCount)) {
        	pageSize = 10;
    	}
        
        //검색조건
        if ("1".equals(searchCondition)) {
			myPageJoin.setPollDivisionName(searchKeyword);
		}else if ("2".equals(searchCondition)) {
			myPageJoin.setPollTitle(searchKeyword);
		}else{
			myPageJoin.setSearchKeyword(searchKeyword);
		}
        
        String userId = searchVO.getUserId();
        myPageJoin.setUserId(userId);
        
        System.out.println("@@@userId@@@ : " + userId);
        
        return findListWithPaging("MyPageJoin", myPageJoin, pageIndex, pageSize, pageUnit);

    }
}
