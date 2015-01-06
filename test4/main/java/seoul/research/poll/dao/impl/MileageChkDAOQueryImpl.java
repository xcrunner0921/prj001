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
import seoul.research.domain.MileageChk;
import seoul.research.poll.dao.MileageChkDAO;
import seoul.research.poll.service.MileageChkSearchVO;

/**
 * This DAO implements class contains
 * IQueryService to get IQueryService
 * services and IPropertiesService to get
 * IPropertiesService services , createMileageChk,
 * updateMileageChk, findMileageChk, findMileageChkList, and
 * getDropDownMileageChkList methods for different
 * functionality on MileageChk.
 * 
 * @author Jeryeon Kim
 */
@Repository("mileageChkDAOQuery")
public class MileageChkDAOQueryImpl extends AbstractDAO implements MileageChkDAO {

    /** an instance variable for the propertiesService. */
    @Resource
    private IPropertiesService propertiesService;

    @Resource
    private IQueryService queryService;

    @PostConstruct
    public void initialize() {
        setQueryService(this.queryService);
    }

    //마일리지 체크 리스트
    public Collection findMileageChkList(MileageChkSearchVO searchVO) throws Exception {
		return findList("MileageChk", searchVO);

    }
}
