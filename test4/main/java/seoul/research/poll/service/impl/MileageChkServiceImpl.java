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
import seoul.research.domain.MileageChk;
import seoul.research.poll.dao.MileageChkDAO;
import seoul.research.poll.service.MileageChkSearchVO;
import seoul.research.poll.service.MileageChkService;

//import com.sds.emp.sales.service.ProductService;

/**
 * This MileageChkServiceImpl is implementing from MileageChkService, this class contains
 * MileageChkDAO,ProductService,IIdGenerationService setter methods and createMileageChk,
 * updateMileageChk,findMileageChkList,getDropDownMileageChkList and findMileageChk method.
 * 
 * @author Jeryeon Kim
 */

@Service("mileageChkService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class MileageChkServiceImpl implements MileageChkService {

	private static Log log = LogFactory.getLog(MileageChkServiceImpl.class);

	/**
	 * mileageChkDAO is used to access the MileageChkDAO methods createMileageChk, updateMileageChk, removeMileageChk,
	 * findMileageChk,findMileageChkList and getDropDownMileageChkList.
	 */
	@Resource(name = "mileageChkDAOQuery")
	// @Resource(name="mileageChkDAOHibernate")
	private MileageChkDAO mileageChkDAO;

	/**
	 * productService is used to get the ProductService services.
	 */
	// @Resource
	// private ProductService productService;

	/**
	 * IIdGenerationService Object is used to get idGenerationService services.
	 */
	/*
	@Resource(name = "idGenerationServiceMileageChk")
	private IIdGenerationService idGenerationService;
*/
	/**
	 * messageSource is used to call getMessage method.
	 */
	// well-known "resolvable dependencies" using
	// @Resource
	@Resource
	private MessageSource messageSource;

	public Collection findMileageChkList(MileageChkSearchVO searchVO) throws Exception {
		//return mileageChkDAO.findMileageChkList(searchVO);
		
		ArrayList mileageChkList = (ArrayList) mileageChkDAO.findMileageChkList(searchVO);
		return mileageChkList;
	}
	
}
