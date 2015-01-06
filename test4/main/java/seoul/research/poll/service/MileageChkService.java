package seoul.research.poll.service;

import java.util.Collection;
import java.util.List;

import anyframe.common.Page;

import seoul.research.domain.PollMng;
import seoul.research.domain.MileageChk;


/**
 * This MileageChkService interface class contains
 * declaration of createMileageChk, updateMileageChk,
 * removeMileageChk, findMileageChkList,
 * getDropDownMileageChkList and findMileageChk method.
 * 
 * @author Jeryeon Kim
 */
public interface MileageChkService {

    /**
     * @param searchVO
     *        This findMileageChkList method is used to
     *        get list of MileageChk with
     *        propertiesService Services
     * @return page
     * @throws Exception
     *         Exception will raised, when
     *         findMileageChkList failed
     */
    Collection findMileageChkList(MileageChkSearchVO searchVO) throws Exception;

}
