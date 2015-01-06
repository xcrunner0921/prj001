package com.sds.emp.code.dao;

import java.util.Collection;

/**
 * This DAO interface class contains declaration of findCodeList method for functionality on Code.
 * 
 * @author Taeho Kim
 */
public interface CodeDAO {

	/**
	 * @param codeType
	 *            This findCodeList method is used to get list of Code By Code Type from database
	 * @return Collection
	 * @throws Exception
	 *             Exception will raised, when findCodeList failed
	 */
	Collection findCodeList(String midCode) throws Exception;

}
