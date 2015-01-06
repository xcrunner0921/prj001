package seoul.research.poll.dao;

import java.util.Collection;
import java.util.List;

import anyframe.common.Page;

import seoul.research.domain.PollMng;
import seoul.research.domain.MileageChk;
import seoul.research.poll.service.MileageChkSearchVO;

/**
 * This DAO interface contains declaration of
 * addMileageChk, updateMileageChk, findMileageChk,
 * findMileageChkList and getDropDownMileageChkList methods
 * for different functionality on MileageChk.
 * 
 * @author Jeryeon Kim
 */
public interface MileageChkDAO {
	
    /**
     * This findMileageChkList method is used to get list
     * of MileageChk with propertiesService Services from
     * database
     * @param searchVO
     *        searchVO is used to get the details
     * @return page page contains the MileageChkList
     * @throws Exception
     *         Exception will raised, when
     *         findMileageChkList failed
     */
	
    Collection findMileageChkList(MileageChkSearchVO searchVO) throws Exception;
}
