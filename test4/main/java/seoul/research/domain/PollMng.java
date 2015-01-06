package seoul.research.domain;

import java.util.Date;

/**
 * This PollMng entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class PollMng implements java.io.Serializable {

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

	// extra fields
	private String delYn;
	private String delYn1;
	private String delYn2;
	private String delYn3;
	private String delYn4;
	private String delYn5;
	private String filePath;
	private String filePath1;
	private String filePath2;
	private String filePath3;
	private String filePath4;
	private String filePath5;
	private int fileSize;
	private int fileSize1;
	private int fileSize2;
	private int fileSize3;
	private int fileSize4;
	private int fileSize5;
	private String orgFname;
	private String orgFname1;
	private String orgFname2;
	private String orgFname3;
	private String orgFname4;
	private String orgFname5;
	private int panelCnt;
	private String partNumberAge;
	private String partNumberMf;
	private String partNumberRegion;
	private String pollAddfilea;
	private String pollAddfileb;
	private String pollAddfilec;
	private String pollAskName;
	private String pollCompanyName;
	private String pollContractName;
	private String pollDepartmentName;
	private String pollFieldName;
	private String pollGradeName;
	private String pollMethodName;
	private String pollQuest;
	private int sNo;
	private String pollStop;
	private String pollSubjectName;
	private String pollTermendHour;
	private String pollTermendMinute;
	private String pollTermendYmd;
	private String pollTermstartHour;
	private String pollTermstartMinute;
	private String pollTermstartYmd;
	private String porAgeCode;
	private String porMfCode;
	private String porRegionCode;
	private String savFname;
	private String savFname1;
	private String savFname2;
	private String savFname3;
	private String savFname4;
	private String savFname5;

	public PollMng() {
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

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getDelYn1() {
		return delYn1;
	}

	public void setDelYn1(String delYn1) {
		this.delYn1 = delYn1;
	}

	public String getDelYn2() {
		return delYn2;
	}

	public void setDelYn2(String delYn2) {
		this.delYn2 = delYn2;
	}

	public String getDelYn3() {
		return delYn3;
	}

	public void setDelYn3(String delYn3) {
		this.delYn3 = delYn3;
	}

	public String getDelYn4() {
		return delYn4;
	}

	public void setDelYn4(String delYn4) {
		this.delYn4 = delYn4;
	}

	public String getDelYn5() {
		return delYn5;
	}

	public void setDelYn5(String delYn5) {
		this.delYn5 = delYn5;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath1() {
		return filePath1;
	}

	public void setFilePath1(String filePath1) {
		this.filePath1 = filePath1;
	}

	public String getFilePath2() {
		return filePath2;
	}

	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
	}

	public String getFilePath3() {
		return filePath3;
	}

	public void setFilePath3(String filePath3) {
		this.filePath3 = filePath3;
	}

	public String getFilePath4() {
		return filePath4;
	}

	public void setFilePath4(String filePath4) {
		this.filePath4 = filePath4;
	}

	public String getFilePath5() {
		return filePath5;
	}

	public void setFilePath5(String filePath5) {
		this.filePath5 = filePath5;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public int getFileSize1() {
		return fileSize1;
	}

	public void setFileSize1(int fileSize1) {
		this.fileSize1 = fileSize1;
	}

	public int getFileSize2() {
		return fileSize2;
	}

	public void setFileSize2(int fileSize2) {
		this.fileSize2 = fileSize2;
	}

	public int getFileSize3() {
		return fileSize3;
	}

	public void setFileSize3(int fileSize3) {
		this.fileSize3 = fileSize3;
	}

	public int getFileSize4() {
		return fileSize4;
	}

	public void setFileSize4(int fileSize4) {
		this.fileSize4 = fileSize4;
	}

	public int getFileSize5() {
		return fileSize5;
	}

	public void setFileSize5(int fileSize5) {
		this.fileSize5 = fileSize5;
	}

	public String getOrgFname() {
		return orgFname;
	}

	public void setOrgFname(String orgFname) {
		this.orgFname = orgFname;
	}

	public String getOrgFname1() {
		return orgFname1;
	}

	public void setOrgFname1(String orgFname1) {
		this.orgFname1 = orgFname1;
	}

	public String getOrgFname2() {
		return orgFname2;
	}

	public void setOrgFname2(String orgFname2) {
		this.orgFname2 = orgFname2;
	}

	public String getOrgFname3() {
		return orgFname3;
	}

	public void setOrgFname3(String orgFname3) {
		this.orgFname3 = orgFname3;
	}

	public String getOrgFname4() {
		return orgFname4;
	}

	public void setOrgFname4(String orgFname4) {
		this.orgFname4 = orgFname4;
	}

	public String getOrgFname5() {
		return orgFname5;
	}

	public void setOrgFname5(String orgFname5) {
		this.orgFname5 = orgFname5;
	}

	public int getPanelCnt() {
		return panelCnt;
	}

	public void setPanelCnt(int panelCnt) {
		this.panelCnt = panelCnt;
	}

	public String getPartNumberAge() {
		return partNumberAge;
	}

	public void setPartNumberAge(String partNumberAge) {
		this.partNumberAge = partNumberAge;
	}

	public String getPartNumberMf() {
		return partNumberMf;
	}

	public void setPartNumberMf(String partNumberMf) {
		this.partNumberMf = partNumberMf;
	}

	public String getPartNumberRegion() {
		return partNumberRegion;
	}

	public void setPartNumberRegion(String partNumberRegion) {
		this.partNumberRegion = partNumberRegion;
	}

	public String getPollAddfilea() {
		return pollAddfilea;
	}

	public void setPollAddfilea(String pollAddfilea) {
		this.pollAddfilea = pollAddfilea;
	}

	public String getPollAddfileb() {
		return pollAddfileb;
	}

	public void setPollAddfileb(String pollAddfileb) {
		this.pollAddfileb = pollAddfileb;
	}

	public String getPollAddfilec() {
		return pollAddfilec;
	}

	public void setPollAddfilec(String pollAddfilec) {
		this.pollAddfilec = pollAddfilec;
	}

	public String getPollAskName() {
		return pollAskName;
	}

	public void setPollAskName(String pollAskName) {
		this.pollAskName = pollAskName;
	}

	public String getPollCompanyName() {
		return pollCompanyName;
	}

	public void setPollCompanyName(String pollCompanyName) {
		this.pollCompanyName = pollCompanyName;
	}

	public String getPollContractName() {
		return pollContractName;
	}

	public void setPollContractName(String pollContractName) {
		this.pollContractName = pollContractName;
	}

	public String getPollDepartmentName() {
		return pollDepartmentName;
	}

	public void setPollDepartmentName(String pollDepartmentName) {
		this.pollDepartmentName = pollDepartmentName;
	}

	public String getPollFieldName() {
		return pollFieldName;
	}

	public void setPollFieldName(String pollFieldName) {
		this.pollFieldName = pollFieldName;
	}

	public String getPollGradeName() {
		return pollGradeName;
	}

	public void setPollGradeName(String pollGradeName) {
		this.pollGradeName = pollGradeName;
	}

	public String getPollMethodName() {
		return pollMethodName;
	}

	public void setPollMethodName(String pollMethodName) {
		this.pollMethodName = pollMethodName;
	}

	public String getPollQuest() {
		return pollQuest;
	}

	public void setPollQuest(String pollQuest) {
		this.pollQuest = pollQuest;
	}

	public int getSNo() {
		return sNo;
	}

	public void setSNo(int sNo) {
		this.sNo = sNo;
	}

	public String getPollStop() {
		return pollStop;
	}

	public void setPollStop(String pollStop) {
		this.pollStop = pollStop;
	}

	public String getPollSubjectName() {
		return pollSubjectName;
	}

	public void setPollSubjectName(String pollSubjectName) {
		this.pollSubjectName = pollSubjectName;
	}

	public String getPollTermendHour() {
		return pollTermendHour;
	}

	public void setPollTermendHour(String pollTermendHour) {
		this.pollTermendHour = pollTermendHour;
	}

	public String getPollTermendMinute() {
		return pollTermendMinute;
	}

	public void setPollTermendMinute(String pollTermendMinute) {
		this.pollTermendMinute = pollTermendMinute;
	}

	public String getPollTermendYmd() {
		return pollTermendYmd;
	}

	public void setPollTermendYmd(String pollTermendYmd) {
		this.pollTermendYmd = pollTermendYmd;
	}

	public String getPollTermstartHour() {
		return pollTermstartHour;
	}

	public void setPollTermstartHour(String pollTermstartHour) {
		this.pollTermstartHour = pollTermstartHour;
	}

	public String getPollTermstartMinute() {
		return pollTermstartMinute;
	}

	public void setPollTermstartMinute(String pollTermstartMinute) {
		this.pollTermstartMinute = pollTermstartMinute;
	}

	public String getPollTermstartYmd() {
		return pollTermstartYmd;
	}

	public void setPollTermstartYmd(String pollTermstartYmd) {
		this.pollTermstartYmd = pollTermstartYmd;
	}

	public String getPorAgeCode() {
		return porAgeCode;
	}

	public void setPorAgeCode(String porAgeCode) {
		this.porAgeCode = porAgeCode;
	}

	public String getPorMfCode() {
		return porMfCode;
	}

	public void setPorMfCode(String porMfCode) {
		this.porMfCode = porMfCode;
	}

	public String getPorRegionCode() {
		return porRegionCode;
	}

	public void setPorRegionCode(String porRegionCode) {
		this.porRegionCode = porRegionCode;
	}

	public String getSavFname() {
		return savFname;
	}

	public void setSavFname(String savFname) {
		this.savFname = savFname;
	}

	public String getSavFname1() {
		return savFname1;
	}

	public void setSavFname1(String savFname1) {
		this.savFname1 = savFname1;
	}

	public String getSavFname2() {
		return savFname2;
	}

	public void setSavFname2(String savFname2) {
		this.savFname2 = savFname2;
	}

	public String getSavFname3() {
		return savFname3;
	}

	public void setSavFname3(String savFname3) {
		this.savFname3 = savFname3;
	}

	public String getSavFname4() {
		return savFname4;
	}

	public void setSavFname4(String savFname4) {
		this.savFname4 = savFname4;
	}

	public String getSavFname5() {
		return savFname5;
	}

	public void setSavFname5(String savFname5) {
		this.savFname5 = savFname5;
	}
}
