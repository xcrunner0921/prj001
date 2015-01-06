package seoul.research.domain;

import java.util.Date;

/**
 * 시스템명 : 온라인 여론조사 시스템 
 * 설명:홈페이지 조사결과 조회
 * 작성일 : 2013.09.11.
 * 작성자 : 하현숙
 * -----------------------------------------
 *     변경일          이름         내용 
 * -----------------------------------------
 * 2013.09.11.  하현숙     최초작성 
 * 
 */
public class SurveyResult implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	private String pollField;
	private String pollAsk;
	private String pollDepartment;
	private String pollTitle;

	private Date pollTermstart;
	private Date pollTermend;
	private String pollTarget;
	private int pollSample;
	private String pollCompany;

	private String pollMethod;
	private String pollContents;
	private String pollKeyword;
	private String pollOpen;
	private String pollGrade;

	private int pollBudget;
	private String pollContract;
	private String pollUtilize;
	private String pollRefer;
	private String pollOpinion;

	private int pollHit;
	private String pollSubtitle;
	private String pollStopyn;
	private String pollStopcond;
	private int pollMileage;

	private String pollMfyn;
	private String pollAgeyn;
	private String pollRegionyn;
	private String pollIntro;
	private String pollMessage;

	private String pollGubun;
	private String pollSubject;
	private String pollBgimg;
	private String pollQuestStatus;
	
	private Date regDate;
	private String regId;
	private Date modDate;
	private String modId;

	// extra field
	private String pollFieldName;
	private String pollAskName;
	private String pollStop;
	
	private String gubunName;
	private String codeName;
	private String searchGubun;
	private String searchName;
	private String searchKeyword;
	private String setSearchCondition;
	
	//여론조사 첨부파일 
	private int sNo;
	private String fileGubun;
	private String orgFname;
	private String savFname;
	private String filePath;
	private int fileSize;

	
	public String getSetSearchCondition() {
		return setSearchCondition;
	}

	public void setSetSearchCondition(String setSearchCondition) {
		this.setSearchCondition = setSearchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchGubun() {
		return searchGubun;
	}

	public void setSearchGubun(String searchGubun) {
		this.searchGubun = searchGubun;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public SurveyResult() {
	}

	public String getGubunName() {
		return gubunName;
	}

	public void setGubunName(String gubunName) {
		this.gubunName = gubunName;
	}

	public int getPollIdx() {
		return pollIdx;
	}

	public void setPollIdx(int pollIdx) {
		this.pollIdx = pollIdx;
	}

	public String getPollField() {
		return pollField;
	}

	public void setPollField(String pollField) {
		this.pollField = pollField;
	}

	public String getPollAsk() {
		return pollAsk;
	}

	public void setPollAsk(String pollAsk) {
		this.pollAsk = pollAsk;
	}

	public String getPollDepartment() {
		return pollDepartment;
	}

	public void setPollDepartment(String pollDepartment) {
		this.pollDepartment = pollDepartment;
	}

	public String getPollTitle() {
		return pollTitle;
	}

	public void setPollTitle(String pollTitle) {
		this.pollTitle = pollTitle;
	}

	public Date getPollTermstart() {
		return pollTermstart;
	}

	public void setPollTermstart(Date pollTermstart) {
		this.pollTermstart = pollTermstart;
	}

	public Date getPollTermend() {
		return pollTermend;
	}

	public void setPollTermend(Date pollTermend) {
		this.pollTermend = pollTermend;
	}

	public String getPollTarget() {
		return pollTarget;
	}

	public void setPollTarget(String pollTarget) {
		this.pollTarget = pollTarget;
	}

	public int getPollSample() {
		return pollSample;
	}

	public void setPollSample(int pollSample) {
		this.pollSample = pollSample;
	}

	public String getPollCompany() {
		return pollCompany;
	}

	public void setPollCompany(String pollCompany) {
		this.pollCompany = pollCompany;
	}

	public String getPollMethod() {
		return pollMethod;
	}

	public void setPollMethod(String pollMethod) {
		this.pollMethod = pollMethod;
	}

	public String getPollContents() {
		return pollContents;
	}

	public void setPollContents(String pollContents) {
		this.pollContents = pollContents;
	}

	public String getPollKeyword() {
		return pollKeyword;
	}

	public void setPollKeyword(String pollKeyword) {
		this.pollKeyword = pollKeyword;
	}

	public String getPollOpen() {
		return pollOpen;
	}

	public void setPollOpen(String pollOpen) {
		this.pollOpen = pollOpen;
	}

	public String getPollGrade() {
		return pollGrade;
	}

	public void setPollGrade(String pollGrade) {
		this.pollGrade = pollGrade;
	}

/*
	public int getPollGrade() {
		return pollGrade;
	}

	public void setPollGrade(int pollGrade) {
		this.pollGrade = pollGrade;
	}
*/
	public int getPollBudget() {
		return pollBudget;
	}

	public void setPollBudget(int pollBudget) {
		this.pollBudget = pollBudget;
	}

	public String getPollContract() {
		return pollContract;
	}

	public void setPollContract(String pollContract) {
		this.pollContract = pollContract;
	}

	public String getPollUtilize() {
		return pollUtilize;
	}

	public void setPollUtilize(String pollUtilize) {
		this.pollUtilize = pollUtilize;
	}

	public String getPollRefer() {
		return pollRefer;
	}

	public void setPollRefer(String pollRefer) {
		this.pollRefer = pollRefer;
	}

	public String getPollOpinion() {
		return pollOpinion;
	}

	public void setPollOpinion(String pollOpinion) {
		this.pollOpinion = pollOpinion;
	}

	public int getPollHit() {
		return pollHit;
	}

	public void setPollHit(int pollHit) {
		this.pollHit = pollHit;
	}

	public String getPollSubtitle() {
		return pollSubtitle;
	}

	public void setPollSubtitle(String pollSubtitle) {
		this.pollSubtitle = pollSubtitle;
	}

	public String getPollStopyn() {
		return pollStopyn;
	}

	public void setPollStopyn(String pollStopyn) {
		this.pollStopyn = pollStopyn;
	}

	public String getPollStopcond() {
		return pollStopcond;
	}

	public void setPollStopcond(String pollStopcond) {
		this.pollStopcond = pollStopcond;
	}

	public int getPollMileage() {
		return pollMileage;
	}

	public void setPollMileage(int pollMileage) {
		this.pollMileage = pollMileage;
	}

	public String getPollMfyn() {
		return pollMfyn;
	}

	public void setPollMfyn(String pollMfyn) {
		this.pollMfyn = pollMfyn;
	}

	public String getPollAgeyn() {
		return pollAgeyn;
	}

	public void setPollAgeyn(String pollAgeyn) {
		this.pollAgeyn = pollAgeyn;
	}

	public String getPollRegionyn() {
		return pollRegionyn;
	}

	public void setPollRegionyn(String pollRegionyn) {
		this.pollRegionyn = pollRegionyn;
	}

	public String getPollIntro() {
		return pollIntro;
	}

	public void setPollIntro(String pollIntro) {
		this.pollIntro = pollIntro;
	}

	public String getPollMessage() {
		return pollMessage;
	}

	public void setPollMessage(String pollMessage) {
		this.pollMessage = pollMessage;
	}

	public String getPollGubun() {
		return pollGubun;
	}

	public void setPollGubun(String pollGubun) {
		this.pollGubun = pollGubun;
	}

	public String getPollSubject() {
		return pollSubject;
	}

	public void setPollSubject(String pollSubject) {
		this.pollSubject = pollSubject;
	}

	public String getPollBgimg() {
		return pollBgimg;
	}

	public void setPollBgimg(String pollBgimg) {
		this.pollBgimg = pollBgimg;
	}

	public String getPollQuestStatus() {
		return pollQuestStatus;
	}

	public void setPollQuestStatus(String pollQuestStatus) {
		this.pollQuestStatus = pollQuestStatus;
	}
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getPollFieldName() {
		return pollFieldName;
	}

	public void setPollFieldName(String pollFieldName) {
		this.pollFieldName = pollFieldName;
	}

	public String getPollAskName() {
		return pollAskName;
	}

	public void setPollAskName(String pollAskName) {
		this.pollAskName = pollAskName;
	}

	public String getPollStop() {
		return pollStop;
	}

	public void setPollStop(String pollStop) {
		this.pollStop = pollStop;
	}

	/**
	 * 여론조사 첨부파일 :일련번호 
	 * @return
	 */
	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	/**
	 * 여론조사 첨부파일 :파일 구분 (1-첨부파일, 2-도서관 원문 DB)
	 * @return
	 */
	public String getFileGubun() {
		return fileGubun;
	}

	public void setFileGubun(String fileGubun) {
		this.fileGubun = fileGubun;
	}

	/**
	 * 여론조사 첨부파일 : 원본파일명 
	 * @return
	 */
	public String getOrgFname() {
		return orgFname;
	}

	public void setOrgFname(String orgFname) {
		this.orgFname = orgFname;
	}

	/**
	 * 여론조사 첨부파일 : 저장파일명(중복파일명 방지)
	 * @return
	 */
	public String getSavFname() {
		return savFname;
	}

	public void setSavFname(String savFname) {
		this.savFname = savFname;
	}

	/**
	 * 여론조사 첨부파일 : 저장파일 경로
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 여론조사 첨부파일 : 파일 크기 
	 * @return
	 */
	public int getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	public String toString() {
		StringBuffer arguments = new StringBuffer();

		arguments.append("\n");
		arguments.append(" gubunName  - " + gubunName + "\n");
		arguments.append(" pollIdx    - " + pollIdx + "\n");
		arguments.append(" pollField  - " + pollField + "\n");
		arguments.append(" pollAsk    - " + pollAsk + "\n");
		arguments.append(" pollTitle  - " + pollTitle + "\n");
		arguments.append(" pollTerm   - " + pollTermstart + "~" + pollTermend + "\n");
		arguments.append(" pollSample - " + pollSample + "\n");
		arguments.append(" pollStop   - " + pollStop + "\n");
		arguments.append(" regDate    - " + regDate);

		return arguments.toString();
	}
}
