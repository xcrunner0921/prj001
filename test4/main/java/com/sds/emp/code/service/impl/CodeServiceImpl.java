package com.sds.emp.code.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.emp.code.dao.CodeDAO;
import com.sds.emp.code.service.CodeService;

/**
 * This CodeServiceImpl is implementing from CodeService, this class contains CodeDAO setter methods and findCodeList method.
 * 
 * @author Taeho Kim
 */

@Service("codeService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class CodeServiceImpl implements CodeService {

	/**
	 * codeDAO is used to access the CodeDAO method findCodeList .
	 */
	@Resource(name = "codeDAOQuery")
	// @Resource(name="codeDAOHibernate")
	private CodeDAO codeDAO;

	public Collection findAskCodeList() throws Exception {
		return codeDAO.findCodeList("AA000");
	}

	public Collection findFieldCodeList() throws Exception {
		return codeDAO.findCodeList("BB000");
	}

	public Collection findMethodCodeList() throws Exception {
		return codeDAO.findCodeList("CC000");
	}

	public Collection findContractCodeList() throws Exception {
		return codeDAO.findCodeList("DD000");
	}

	public Collection findCompanyCodeList() throws Exception {
		return codeDAO.findCodeList("EE000");
	}

	public Collection findMfCodeList() throws Exception {
		return codeDAO.findCodeList("FF100");
	}

	public Collection findAgeCodeList() throws Exception {
		return codeDAO.findCodeList("FF200");
	}

	public Collection findRegionCodeList() throws Exception {
		return codeDAO.findCodeList("FF300");
	}

	public Collection findGradeCodeList() throws Exception {
		return codeDAO.findCodeList("GG000");
	}

	public Collection findQuestCodeList() throws Exception {
		return codeDAO.findCodeList("HH000");
	}

	public Collection findExamCodeList() throws Exception {
		return codeDAO.findCodeList("II000");
	}

	public Collection findGraphCodeList() throws Exception {
		return codeDAO.findCodeList("JJ000");
	}

	public Collection findChoiceCodeList() throws Exception {
		return codeDAO.findCodeList("KK000");
	}

	public Collection findSubjectCodeList() throws Exception {
		return codeDAO.findCodeList("MM000");
	}
}
