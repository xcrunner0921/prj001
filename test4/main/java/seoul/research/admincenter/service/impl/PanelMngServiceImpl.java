package seoul.research.admincenter.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import seoul.research.admincenter.dao.PanelMngDAO;
import seoul.research.admincenter.service.PanelMngSearchVO;
import seoul.research.admincenter.service.PanelMngService;
import anyframe.common.Page;

/**
 * 패널현황 조회 : PartMngServiceImpl.java
 * 
 * 
 * -----------------------------------------
 * 
 * ----------------------------------------- 2013.10.11.
 * 
 */

@Service("panelMngService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class PanelMngServiceImpl implements PanelMngService {

	private static Log log = LogFactory.getLog(PanelMngServiceImpl.class);

	/**
	 * 
	 */
	@Resource(name = "panelMngDAOQuery")
	private PanelMngDAO panelMngDAO;

	@Resource
	private MessageSource messageSource;

	public Page listPanelMng(PanelMngSearchVO panelMngSearchVO) throws Exception {
		return panelMngDAO.listPanelMng(panelMngSearchVO);
	}

	public Collection findPanelList(PanelMngSearchVO panelMngSearchVO) throws Exception {
		return panelMngDAO.findPanelList(panelMngSearchVO);
	}
}
