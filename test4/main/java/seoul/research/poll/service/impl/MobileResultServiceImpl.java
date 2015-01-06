package seoul.research.poll.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.emp.common.EmpException;

import seoul.research.domain.MobileResult;
import seoul.research.poll.dao.MobileResultDAO;
import seoul.research.poll.service.MobileResultSearchVO;
import seoul.research.poll.service.MobileResultService;

//import com.sds.emp.sales.service.ProductService;

/**
 * This MobileResultServiceImpl is implementing from MobileResultService, this class contains
 * MobileResultDAO,ProductService,IIdGenerationService setter methods and createMobileResult,
 * updateMobileResult,findMobileResultList,getDropDownMobileResultList and findMobileResult method.
 * 
 * @author Jeryeon Kim
 */

@Service("mobileResultService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class MobileResultServiceImpl implements MobileResultService {

	private static Log log = LogFactory.getLog(MobileResultServiceImpl.class);

	/**
	 * mobileResultDAO is used to access the MobileResultDAO methods createMobileResult, updateMobileResult, removeMobileResult,
	 * findMobileResult,findMobileResultList and getDropDownMobileResultList.
	 */
	@Resource(name = "mobileResultDAOQuery")
	private MobileResultDAO mobileResultDAO;

	@Resource
	private MessageSource messageSource;

	//모바일 결과보기
	public Collection findMobileResultList(MobileResultSearchVO searchVO) throws Exception {
		ArrayList mobileResultList = (ArrayList) mobileResultDAO.findMobileResultList(searchVO);
		return mobileResultList;
	}
	
	//모바일 결과보기 타이틀 가져오기
	public MobileResult findMobileResult(int pollIdx) throws Exception {
		MobileResult mobileResult = mobileResultDAO.findMobileResult(pollIdx);
		if (mobileResult == null) {
			throw new EmpException(messageSource.getMessage("error.mobileResult.get.notexists", new String[] { Integer.toString(pollIdx) }, Locale.getDefault()));
		}
		return mobileResult;
	}
	
}
