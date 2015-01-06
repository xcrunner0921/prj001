package com.sds.emp.code.service;

import java.util.Collection;

/**
 * This CodeService interface class contains declaration of findCodeList method.
 * 
 * @author Taeho Kim
 */
public interface CodeService {

	/**
	 * @param codeType
	 *            This findCodeList method is used to get list of Code By Code Type
	 * @return Collection
	 * @throws Exception
	 *             Exception will raised, when findCodeList failed
	 */
	Collection findAskCodeList() throws Exception;

	Collection findFieldCodeList() throws Exception;

	Collection findMethodCodeList() throws Exception;

	Collection findContractCodeList() throws Exception;

	Collection findCompanyCodeList() throws Exception;

	Collection findMfCodeList() throws Exception;

	Collection findAgeCodeList() throws Exception;

	Collection findRegionCodeList() throws Exception;

	Collection findGradeCodeList() throws Exception;

	Collection findQuestCodeList() throws Exception;

	Collection findExamCodeList() throws Exception;

	Collection findGraphCodeList() throws Exception;

	Collection findChoiceCodeList() throws Exception;

	Collection findSubjectCodeList() throws Exception;
}
