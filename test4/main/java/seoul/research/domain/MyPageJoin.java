package seoul.research.domain;

import java.util.Collection;
import java.util.Date;

/**
 * This MyPageJoin entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class MyPageJoin implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	//POLL_MNG Table
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
	
	//QUEST_MNG Table
	private int questNo;
	private String questTitle;
	private String questType;
	private String examType;
	private String branchYn;
	private String examRotateyn;
	private String graphType;
	private String filterYn;
	private String mergeType;
	
	//EXAMPLE_MNG Table
	private int examNo;
	private String examTitle;
	private int branchNo;
	private String mergeNo;
	private String mergeTitle;
	
	//DTL_CODE Table
	private String gubunName;

	//FILTER_MNG Table
	private String filterAnswer;
	private String questNumber;
	
	//IMG_MNG Table
	private String filePath;
	private String savFname;
	
	//
	//private String myPageJoinNo;
	private String codeName;
	
	//select
	private String pollDivisionName;
	private String searchKeyword; 
	
	//insert
	private String panelId;
	private String answer;
	private String etcAnswer;
	private String sNo;
	private String sNoAnswer;

	private int nextQuestNo;
	private String progYn;
	
	private String userId;
	private int questCount;
	
	//조사기간 
	private String pollTermstartYmd;
	private String pollTermstartHour;
	private String pollTermstartMinute;
	private String pollTermendYmd;
	private String pollTermendHour;
	private String pollTermendMinute;
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSavFname() {
		return savFname;
	}

	public void setSavFname(String savFname) {
		this.savFname = savFname;
	}

	public int getQuestCount() {
		return questCount;
	}

	public void setQuestCount(int questCount) {
		this.questCount = questCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSNoAnswer() {
		return sNoAnswer;
	}

	public void setSNoAnswer(String sNoAnswer) {
		this.sNoAnswer = sNoAnswer;
	}
	
	public int getNextQuestNo() {
		return nextQuestNo;
	}

	public void setNextQuestNo(int nextQuestNo) {
		this.nextQuestNo = nextQuestNo;
	}

	public String getProgYn() {
		return progYn;
	}

	public void setProgYn(String progYn) {
		this.progYn = progYn;
	}

	public String getSNo() {
		return sNo;
	}

	public void setSNo(String sNo) {
		this.sNo = sNo;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEtcAnswer() {
		return etcAnswer;
	}

	public void setEtcAnswer(String etcAnswer) {
		this.etcAnswer = etcAnswer;
	}

	public String getPanelId() {
		return panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}

	public String getQuestNumber() {
		return questNumber;
	}

	public void setQuestNumber(String questNumber) {
		this.questNumber = questNumber;
	}

	public String getFilterAnswer() {
		return filterAnswer;
	}

	public void setFilterAnswer(String filterAnswer) {
		this.filterAnswer = filterAnswer;
	}
	
	public int getQuestNo() {
		return questNo;
	}

	public void setQuestNo(int questNo) {
		this.questNo = questNo;
	}

	public String getQuestTitle() {
		return questTitle;
	}

	public void setQuestTitle(String questTitle) {
		this.questTitle = questTitle;
	}

	public String getQuestType() {
		return questType;
	}

	public void setQuestType(String questType) {
		this.questType = questType;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getBranchYn() {
		return branchYn;
	}

	public void setBranchYn(String branchYn) {
		this.branchYn = branchYn;
	}

	public String getExamRotateyn() {
		return examRotateyn;
	}

	public void setExamRotateyn(String examRotateyn) {
		this.examRotateyn = examRotateyn;
	}

	public String getGraphType() {
		return graphType;
	}

	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}

	public String getFilterYn() {
		return filterYn;
	}

	public void setFilterYn(String filterYn) {
		this.filterYn = filterYn;
	}

	public String getMergeType() {
		return mergeType;
	}

	public void setMergeType(String mergeType) {
		this.mergeType = mergeType;
	}

	public int getExamNo() {
		return examNo;
	}

	public void setExamNo(int examNo) {
		this.examNo = examNo;
	}

	public String getExamTitle() {
		return examTitle;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}

	public int getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(int branchNo) {
		this.branchNo = branchNo;
	}

	public String getMergeNo() {
		return mergeNo;
	}

	public void setMergeNo(String mergeNo) {
		this.mergeNo = mergeNo;
	}

	public String getMergeTitle() {
		return mergeTitle;
	}

	public void setMergeTitle(String mergeTitle) {
		this.mergeTitle = mergeTitle;
	}

	public String getPollDivisionName() {
		return pollDivisionName;
	}

	public void setPollDivisionName(String pollDivisionName) {
		this.pollDivisionName = pollDivisionName;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public MyPageJoin() {
    }

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
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

	public String getGubunName() {
		return gubunName;
	}

	public void setGubunName(String gubunName) {
		this.gubunName = gubunName;
	}
	
	//조사기간 

	public String getPollTermstartYmd() {
		return pollTermstartYmd;
	}

	public void setPollTermstartYmd(String pollTermstartYmd) {
		this.pollTermstartYmd = pollTermstartYmd;
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

	public String getPollTermendYmd() {
		return pollTermendYmd;
	}

	public void setPollTermendYmd(String pollTermendYmd) {
		this.pollTermendYmd = pollTermendYmd;
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

}
