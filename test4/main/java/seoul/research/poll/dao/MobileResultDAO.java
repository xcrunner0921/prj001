package seoul.research.poll.dao;

import java.util.Collection;
import seoul.research.domain.MobileResult;
import seoul.research.domain.PollMng;
import seoul.research.poll.service.MileageChkSearchVO;
import seoul.research.poll.service.MobileResultSearchVO;

/**
 * This DAO interface contains declaration of
 * addMobileResult, updateMobileResult, findMobileResult,
 * findMobileResultList and getDropDownMobileResultList methods
 * for different functionality on MobileResult.
 * 
 * @author Jeryeon Kim
 */
public interface MobileResultDAO {
	
    /**
     * This findMobileResultList method is used to get list
     * of MobileResult with propertiesService Services from
     * database
     * @param searchVO
     *        searchVO is used to get the details
     * @return page page contains the MobileResultList
     * @throws Exception
     *         Exception will raised, when
     *         findMobileResultList failed
     */
	//모바일 결과보기
    Collection findMobileResultList(MobileResultSearchVO searchVO) throws Exception;
    
    //모바일 결과보기 타이틀 가져오기
    MobileResult findMobileResult(int pollIdx) throws Exception;
}
