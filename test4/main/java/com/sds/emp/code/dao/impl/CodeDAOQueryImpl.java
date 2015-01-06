package com.sds.emp.code.dao.impl;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

import com.sds.emp.code.dao.CodeDAO;
import com.sds.emp.domain.Code;

/**
 * This CodeDAO implements class contains IExtendedQueryService to get IExtendedQueryService services , findCodeList method for
 * functionality on Code.
 * 
 * @author Taeho Kim
 */
@Repository("codeDAOQuery")
public class CodeDAOQueryImpl extends AbstractDAO implements CodeDAO {
	@Resource
	private IQueryService queryService;

	@PostConstruct
	public void initialize() {
		setQueryService(this.queryService);
	}

	public Collection findCodeList(String midCode) throws Exception {
		Code code = new Code();
		code.setMidCode(midCode);
		return findList("Code", code);
	}

}
