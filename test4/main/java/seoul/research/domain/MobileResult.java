package seoul.research.domain;

import java.util.Collection;
import java.util.Date;

/**
 * This MobileResult entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class MobileResult implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	//POLL_MNG Table
	private int pollIdx;
	private String pollField;
	private String pollAsk;
	private String pollDepartment;
	private String pollTitle;
	private String pollTermstart;
	private String pollTermend;
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
	
	private int questNo;
	private int examNo;
	private int cnt;
	private int total;
	private int cntHh101;
	private int totalHh101;
	
	
	private String questTitle;
	private String questType;
	private String examType;
	private String graphType;
	private int avge;
	private int avge2;
	private int cntHh104;
	private int avge3;
	
	
	
	// collection
	private Collection mobileResultList;
	
	
	public int getCntHh104() {
		return cntHh104;
	}
	public void setCntHh104(int cntHh104) {
		this.cntHh104 = cntHh104;
	}
	public int getAvge3() {
		return avge3;
	}
	public void setAvge3(int avge3) {
		this.avge3 = avge3;
	}
	public int getAvge2() {
		return avge2;
	}
	public void setAvge2(int avge2) {
		this.avge2 = avge2;
	}
	public int getAvge() {
		return avge;
	}
	public void setAvge(int avge) {
		this.avge = avge;
	}
	public int getTotalHh101() {
		return totalHh101;
	}
	public void setTotalHh101(int totalHh101) {
		this.totalHh101 = totalHh101;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCntHh101() {
		return cntHh101;
	}
	public void setCntHh101(int cntHh101) {
		this.cntHh101 = cntHh101;
	}
	public Collection getMobileResultList() {
		return mobileResultList;
	}
	public void setMobileResultList(Collection mobileResultList) {
		this.mobileResultList = mobileResultList;
	}
	
	public int getQuestNo() {
		return questNo;
	}
	public void setQuestNo(int questNo) {
		this.questNo = questNo;
	}
	public int getExamNo() {
		return examNo;
	}
	public void setExamNo(int examNo) {
		this.examNo = examNo;
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
	public String getGraphType() {
		return graphType;
	}
	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
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
	public String getPollTermstart() {
		return pollTermstart;
	}
	public void setPollTermstart(String pollTermstart) {
		this.pollTermstart = pollTermstart;
	}
	public String getPollTermend() {
		return pollTermend;
	}
	public void setPollTermend(String pollTermend) {
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
	public String getPollGrade() {
		return pollGrade;
	}
	public void setPollGrade(String pollGrade) {
		this.pollGrade = pollGrade;
	}
	
	
	
	
	
	
	
	
}
