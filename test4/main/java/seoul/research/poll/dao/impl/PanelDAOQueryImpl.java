package seoul.research.poll.dao.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import seoul.research.domain.Panel;
import seoul.research.poll.dao.PanelDAO;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;
import anyframe.core.query.impl.QueryServiceImpl;

/**
 * 
 * : PanelDAOQueryImpl.java
 * -----------------------------------------
 *  
 * -----------------------------------------
 * 2013.09.18. 
 * 
 */

@Repository("panelDAOQuery")
public class PanelDAOQueryImpl extends AbstractDAO implements PanelDAO {

	private static Log log = LogFactory.getLog(PanelDAOQueryImpl.class);

	/** an instance variable for the propertiesService. */
	@Resource
	private IPropertiesService propertiesService;

	@Resource
	private IQueryService queryService;// = new QueryServiceImpl();

	@PostConstruct
	public void initialize() {
		setQueryService(this.queryService);
	}

	public Panel getPanelInfo(String userId) throws Exception {
		Panel target = new Panel();
		target.setPnlId(userId);
		Panel panel = null;
		try{
			
			log.info("===>>> panelDAO QueryImpl - 44 :   " +  userId);
			panel = (Panel)findByPk("getPanelInfo", target);
			
			log.info("====>> panel :  "+ panel);
			log.info("====>> DAO Query impl 58  :  ~~~~~");
		
		}catch(Exception ex){
			log.info("====>> DAO Query impl  Exception (65) : " + ex.toString());
			ex.printStackTrace();
		}
		//return (Panel) findByPk("getPanelInfo", userId);
		return panel;
	}

	// 
	public int getPanelCount(String userId) throws Exception {
		Panel vo = new Panel();
		vo.setPnlId(userId);
		int cnt = 0;
		
		log.info("===>>> panelDAO QueryImpl - 73 :   " +  userId);
		Collection collection = getQueryService().find("getPanelCount", new Object[] { new Object[] { "vo", vo } });
		Iterator iterator = collection.iterator();
		
		if (iterator.hasNext()) {
			Map map = (Map) iterator.next();

			String panelCnt = map.get("cnt").toString();
			cnt =  Integer.parseInt(panelCnt);

			log.info("====>> panelCnt 85  :  "+ panelCnt);
		}
		
		log.info("====>> cnt  88 :  "+ cnt);
		log.info("====>> DAO Query impl 89  :  ~~~~~");


		return cnt;
	}

	// (N->Y)
	public void updatePanelDelflagN(Panel panel) throws Exception{
		log.info("====>> DAO Query impl 96 :  update sql call");
		update("PanelDelflagN", panel);
		log.info("====>> DAO Query impl 96 :  update end ");
	}
	

	//  (Y->N)
	public void updatePanelDelflagY(Panel panel) throws Exception{
		log.info("====>> DAO Query impl 104 :  update sql call");
		update("PanelDelflagY", panel);
		log.info("====>> DAO Query impl 106 :  update end ");
	}
	
	// 
	public int getSNo() throws Exception{
		int sNo = 0;
		Collection collection = getQueryService().find("getSNo", new Object[] {});

		log.info("====>> DAO Query impl 114  start ~~~~~~~~~~~~~~~~~~  ");
		Iterator iterator = collection.iterator();
		if (iterator.hasNext()) {
			Map map = (Map) iterator.next();

			String sno = map.get("sNo").toString();
			log.info("====>> DAO Query impl 120    sno :  " + sno);
			sNo = Integer.parseInt(sno);
		}
		return sNo;
	}
	
	public void createPanel(Panel panel) throws Exception{
		log.info("====>> DAO Query impl 127 :  insert sql call");
		create("Panel", panel);
		log.info("====>> DAO Query impl 129 :  insert end ");
	}
	
	//패널가입 후 회원통합정보 insert
	public void createTbmem001(Panel panel) throws Exception{
		log.info("====>> DAO Query impl 134 :  insert sql call");
		create("Tbmem001", panel);
		log.info("====>> DAO Query impl 136 :  insert end ");
	}

	// 회원통합정보
	public int getTbmem001Count(String userId) throws Exception {
		Panel vo = new Panel();
		vo.setPnlId(userId);
		int cnt = 0;
		
		log.info("===>>> panelDAO QueryImpl - 145 :   " +  userId);
		Collection collection = getQueryService().find("getTbmem001Count", new Object[] { new Object[] { "vo", vo } });
		Iterator iterator = collection.iterator();
		
		if (iterator.hasNext()) {
			Map map = (Map) iterator.next();

			String panelCnt = map.get("cnt").toString();
			cnt =  Integer.parseInt(panelCnt);
			log.info("====>> panelCnt 154  :  "+ panelCnt);
		}		
		log.info("====>> cnt  156 :  "+ cnt);
		log.info("====>> DAO Query impl 157  :  ~~~~~");
		
		return cnt;
	}

}
