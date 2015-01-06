package seoul.research.poll.service;

import java.util.Collection;
import java.util.List;

import anyframe.common.Page;

import seoul.research.domain.PollMng;
import seoul.research.domain.MobileResult;


/**
 * This MobileResultService interface class contains
 * declaration of createMobileResult, updateMobileResult,
 * removeMobileResult, findMobileResultList,
 * getDropDownMobileResultList and findMobileResult method.
 * 
 * @author Jeryeon Kim
 */
public interface MobileResultService {

    /**
     * @param searchVO
     *        This findMobileResultList method is used to
     *        get list of MobileResult with
     *        propertiesService Services
     * @return page
     * @throws Exception
     *         Exception will raised, when
     *         findMobileResultList failed
     */
	//모바일 결과보기
    Collection findMobileResultList(MobileResultSearchVO searchVO) throws Exception;
    
    //모바일 결과보기 타이틀 가져오기
    MobileResult findMobileResult(int pollIdx) throws Exception;
}
