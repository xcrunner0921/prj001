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
import seoul.research.domain.MobileResult;
import seoul.research.poll.dao.MobileResultDAO;
import seoul.research.poll.service.MileageChkSearchVO;
import seoul.research.poll.service.MobileResultSearchVO;

/**
 * This DAO implements class contains
 * IQueryService to get IQueryService
 * services and IPropertiesService to get
 * IPropertiesService services , createMobileResult,
 * updateMobileResult, findMobileResult, findMobileResultList, and
 * getDropDownMobileResultList methods for different
 * functionality on MobileResult.
 * 
 * @author Jeryeon Kim
 */
@Repository("mobileResultDAOQuery")
public class MobileResultDAOQueryImpl extends AbstractDAO implements MobileResultDAO {

    /** an instance variable for the propertiesService. */
    @Resource
    private IPropertiesService propertiesService;

    @Resource
    private IQueryService queryService;

    @PostConstruct
    public void initialize() {
        setQueryService(this.queryService);
    }

    //모바일 결과보기
    public Collection findMobileResultList(MobileResultSearchVO searchVO) throws Exception {
		return findList("MobileResult", searchVO);

    }
    
    //모바일 결과보기 타이틀 가져오기
    public MobileResult findMobileResult(int pollIdx) throws Exception {

    	MobileResult mobileResult = new MobileResult();
		mobileResult.setPollIdx(pollIdx);

		return (MobileResult) findByPk("mobileResult", mobileResult);
	}
}
