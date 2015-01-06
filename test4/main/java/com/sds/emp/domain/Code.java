package com.sds.emp.domain;

import java.sql.Date;

/**
 * This Code entity class contains all attributes of CODE table and putter,setter methods of them. And it contains toString
 * method for logging information
 * 
 * @author Taeho Kim
 */
public class Code implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String codeId;
	private String codeName;
	private String codeDesc;
	private String midCode;
	private String useYn;

	private Date regDate;
	private String regId;
	private Date modDate;
	private String modId;

	public Code() {
	}

	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getMidCode() {
		return this.midCode;
	}

	public void setMidCode(String midCode) {
		this.midCode = midCode;
	}

	public String getUseYn() {
		return this.useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRegId() {
		return this.regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Date getModDate() {
		return this.regDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getModId() {
		return this.modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String toString() {
		StringBuffer arguments = new StringBuffer();

		arguments.append(" codeId   - " + codeId + "\n");
		arguments.append(" codeName - " + codeName + "\n");
		arguments.append(" codeDesc - " + codeDesc + "\n");
		arguments.append(" midCode  - " + midCode + "\n");
		arguments.append(" useYn    - " + useYn + "\n");
		arguments.append(" regDate  - " + regDate);
		arguments.append(" regId    - " + regId + "\n");
		arguments.append(" modDate  - " + modDate);
		arguments.append(" modId    - " + modId + "\n");

		return arguments.toString();
	}
}
