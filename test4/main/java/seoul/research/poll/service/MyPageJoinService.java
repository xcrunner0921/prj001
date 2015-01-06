package seoul.research.poll.service;

import java.util.List;

import anyframe.common.Page;

import seoul.research.domain.PollMng;
import seoul.research.domain.MyPageJoin;


/**
 * This MyPageJoinService interface class contains
 * declaration of createMyPageJoin, updateMyPageJoin,
 * removeMyPageJoin, findMyPageJoinList,
 * getDropDownMyPageJoinList and findMyPageJoin method.
 * 
 * @author Jeryeon Kim
 */
public interface MyPageJoinService {

    /**
     * @param searchVO
     *        This findMyPageJoinList method is used to
     *        get list of MyPageJoin with
     *        propertiesService Services
     * @return page
     * @throws Exception
     *         Exception will raised, when
     *         findMyPageJoinList failed
     */
    Page findMyPageJoinList(MyPageJoinSearchVO searchVO) throws Exception;

}
