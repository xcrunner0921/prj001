package seoul.research.poll.service.impl;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import seoul.research.domain.Panel;
import seoul.research.poll.dao.PanelDAO;
import seoul.research.poll.dao.impl.PanelDAOQueryImpl;
import seoul.research.poll.service.PanelService;

import com.sds.emp.common.EmpException;

/**
 * 
 * : PanelServiceImpl.java
 * 
 *  
 * -----------------------------------------
 *   
 * -----------------------------------------
 * 2013.09.18. 
 * 
 */

@Service("panelService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class PanelServiceImpl implements PanelService {

	private static Log log = LogFactory.getLog(PanelServiceImpl.class);

	/**
	 * 
	 */
	@Resource(name = "panelDAOQuery")
	private PanelDAO panleDAO;// = new PanelDAOQueryImpl();

	 
	@Resource
	private MessageSource messageSource;

	public Panel getPanelInfo(String userId) throws Exception {
		Panel panel = null;

		log.info("===>>>PanelServiceImpl - 53 :   " + userId);		
		try{
			log.info("===>>>PanelServiceImpl - 55 :   " + userId);
			
			panel = panleDAO.getPanelInfo(userId);
			
			if (panel == null) {
	
				throw new EmpException(messageSource.getMessage("error - panel info >>  ", new String[] { userId }, Locale.getDefault()));
	
			}else{
	
				log.info("===>>>PanelServiceImpl -65 :   " +  panel.getPnlId().toString());
				
			}
		}catch(Exception ex){

			log.info("===>>> Exception -70 :   " + ex.toString());
		}
		
		return panel;
	}

	// 
	public int getPanelCount(String userId) throws Exception {
		
		int cnt = 0;
		log.info("===>>>PanelServiceImpl - 80 :   " + userId);		

		cnt = panleDAO.getPanelCount(userId);

		return cnt;
	}
	
	// (N->Y)
	public void updatePanelDelflagN(Panel panel) throws Exception{
		panleDAO.updatePanelDelflagN(panel);
		log.info("===>>>PanelServiceImpl - 90 :   " + panel.getPnlId());		
	}
	

	//  (Y->N)
	public void updatePanelDelflagY(Panel panel) throws Exception{
		panleDAO.updatePanelDelflagY(panel);
		log.info("===>>>PanelServiceImpl - 97 :   " + panel.getPnlId());		
	}
	
	// 
	public void createPanel(Panel panel) throws Exception{

		int sNo = panleDAO.getSNo();
		panel.setSNo(sNo);
		log.info("===>>>PanelServiceImpl - 104 :  sNo :  " + sNo);
		panleDAO.createPanel(panel);
	}
	

	// 패널가입 후 회원통합정보 insert 
	public void createTbmem001(Panel panel) throws Exception{
		
		log.info("===>>>PanelServiceImpl - 113 :  sNo :  " + panel.getAgeGroup());
		panleDAO.createTbmem001(panel);
	}
	
	// 회원통합정보
	public int getTbmem001Count(String userId) throws Exception {		
		int cnt = 0;
		log.info("===>>>PanelServiceImpl - 120 :   " + userId);		
		cnt = panleDAO.getTbmem001Count(userId);
		return cnt;
	}
}
