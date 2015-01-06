package seoul.research.poll.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import anyframe.common.Page;
import anyframe.core.idgen.IIdGenerationService;

import com.sds.emp.common.EmpException;

import seoul.research.domain.PollMng;
import seoul.research.domain.QuestMng;
import seoul.research.domain.MyPageJoin;
import seoul.research.poll.dao.MyPageJoinDAO;
import seoul.research.poll.service.MyPageJoinSearchVO;
import seoul.research.poll.service.MyPageJoinService;

//import com.sds.emp.sales.service.ProductService;

/**
 * This MyPageJoinServiceImpl is implementing from MyPageJoinService, this class contains
 * MyPageJoinDAO,ProductService,IIdGenerationService setter methods and createMyPageJoin,
 * updateMyPageJoin,findMyPageJoinList,getDropDownMyPageJoinList and findMyPageJoin method.
 * 
 * @author Jeryeon Kim
 */

@Service("myPageJoinService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class MyPageJoinServiceImpl implements MyPageJoinService {

	private static Log log = LogFactory.getLog(MyPageJoinServiceImpl.class);

	/**
	 * myPageJoinDAO is used to access the MyPageJoinDAO methods createMyPageJoin, updateMyPageJoin, removeMyPageJoin,
	 * findMyPageJoin,findMyPageJoinList and getDropDownMyPageJoinList.
	 */
	@Resource(name = "myPageJoinDAOQuery")
	// @Resource(name="myPageJoinDAOHibernate")
	private MyPageJoinDAO myPageJoinDAO;

	/**
	 * productService is used to get the ProductService services.
	 */
	// @Resource
	// private ProductService productService;

	/**
	 * IIdGenerationService Object is used to get idGenerationService services.
	 */
	/*
	@Resource(name = "idGenerationServiceMyPageJoin")
	private IIdGenerationService idGenerationService;
*/
	/**
	 * messageSource is used to call getMessage method.
	 */
	// well-known "resolvable dependencies" using
	// @Resource
	@Resource
	private MessageSource messageSource;

	public Page findMyPageJoinList(MyPageJoinSearchVO searchVO) throws Exception {
		return myPageJoinDAO.findMyPageJoinList(searchVO);
	}
	
}
