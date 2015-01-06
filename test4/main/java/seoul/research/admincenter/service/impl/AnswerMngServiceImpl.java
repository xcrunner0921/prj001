package seoul.research.admincenter.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import seoul.research.admincenter.dao.AnswerMngDAO;
import seoul.research.admincenter.service.AnswerMngSearchVO;
import seoul.research.admincenter.service.AnswerMngService;
import anyframe.common.Page;

/**
 * This PollMngServiceImpl is implementing from PollMngService, this class contains
 * PollMngDAO,ProductService,IIdGenerationService setter methods and createPollMng,
 * updatePollMng,findPollMngList,getDropDownPollMngList and findPollMng method.
 * 
 * @author Jeryeon Kim
 */

@Service("answerMngService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class AnswerMngServiceImpl implements AnswerMngService {

	private static Log log = LogFactory.getLog(AnswerMngServiceImpl.class);

	/**
	 * pollMngDAO is used to access the PollMngDAO methods createPollMng, updatePollMng, removePollMng,
	 * findPollMng,findPollMngList and getDropDownPollMngList.
	 */
	@Resource(name = "answerMngDAOQuery")
	// @Resource(name="pollMngDAOHibernate")
	private AnswerMngDAO answerMngDAO;

	/**
	 * productService is used to get the ProductService services.
	 */
	// @Resource
	// private ProductService productService;

	/**
	 * IIdGenerationService Object is used to get idGenerationService services.
	 */
	// @Resource(name = "idGenerationServicePollMng")
	// private IIdGenerationService idGenerationService;

	/**
	 * messageSource is used to call getMessage method.
	 */
	// well-known "resolvable dependencies" using
	// @Resource
	@Resource
	private MessageSource messageSource;

	public Page findAnswerMngList(AnswerMngSearchVO searchVO) throws Exception {
		return answerMngDAO.findAnswerMngList(searchVO);
	}
}
