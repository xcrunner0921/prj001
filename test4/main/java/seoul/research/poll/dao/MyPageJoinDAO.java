package seoul.research.poll.dao;

import java.util.List;

import anyframe.common.Page;

import seoul.research.domain.PollMng;
import seoul.research.domain.MyPageJoin;
import seoul.research.poll.service.MyPageJoinSearchVO;

/**
 * This DAO interface contains declaration of
 * addMyPageJoin, updateMyPageJoin, findMyPageJoin,
 * findMyPageJoinList and getDropDownMyPageJoinList methods
 * for different functionality on MyPageJoin.
 * 
 * @author Jeryeon Kim
 */
public interface MyPageJoinDAO {
	
    /**
     * This findMyPageJoinList method is used to get list
     * of MyPageJoin with propertiesService Services from
     * database
     * @param searchVO
     *        searchVO is used to get the details
     * @return page page contains the MyPageJoinList
     * @throws Exception
     *         Exception will raised, when
     *         findMyPageJoinList failed
     */
    Page findMyPageJoinList(MyPageJoinSearchVO searchVO) throws Exception;
}
