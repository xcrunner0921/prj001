package seoul.research.domain;

import java.util.Collection;
import java.util.Date;

/**
 * This QuestMng entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class QuestMng implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	private int questNo;

	private String questTitle;
	private String questType;
	private String examType;
	private String branchYn;
	private String examRotateyn;
	private String graphType;
	private String filterYn;
	private String mergeType;
	private Date regDate;
	private String regId;
	private Date modDate;
	private String modId;

	// extra fields
	private String branchedYn;
	private String branchNo;
	private String branchNo2;
	private String choiceCnt;
	private String choiceCnt21;
	private String choiceCnt22;
	private String choiceCnt41;
	private String choiceCnt42;
	private String choiceCnts;
	private String choiceRange;
	private String choiceRange21;
	private String choiceRange22;
	private String choiceRange41;
	private String choiceRange42;
	private String cnts;
	private String delYn;
	private String delYnd;
	private String delYnu;
	private String examNo;
	private String examNo11;
	private String examNo12;
	private String examNo21;
	private String examNo22;
	private String examNo41;
	private String examNo42;
	private String examTitle;
	private String examTitle11;
	private String examTitle21;
	private String examTitle41;
	private String fileDesc;
	private String fileDesc12;
	private String fileDesc22;
	private String fileDesc42;
	private String filePath;
	private String filePath12;
	private String filePath22;
	private String filePath42;
	private String fileSize;
	private String fileSize12;
	private String fileSize22;
	private String fileSize42;
	private String filterAnswer;
	private String header;
	private String maxs;
	private String mergeTitle;
	private String mergeTitle2;
	private String mergeTitle3;
	private String orgFname;
	private String orgFname12;
	private String orgFname22;
	private String orgFname42;
	private String pollAsk;
	private String pollAskName;
	private String pollFieldName;
	private String pollQuestStatus;
	private Date pollTermend;
	private Date pollTermstart;
	private String pollTitle;
	private String position;
	private String savFname;
	private String savFname12;
	private String savFname22;
	private String savFname42;

	// collection
	private Collection answerMngList;
	private Collection exampleMngList;
	private Collection imgMngList;
	private Collection questAddfileList;

	public QuestMng() {
	}

	public int getPollIdx() {
		return pollIdx;
	}

	public void setPollIdx(int pollIdx) {
		this.pollIdx = pollIdx;
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

	public String getBranchedYn() {
		return branchedYn;
	}

	public void setBranchedYn(String branchedYn) {
		this.branchedYn = branchedYn;
	}

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public String getBranchNo2() {
		return branchNo2;
	}

	public void setBranchNo2(String branchNo2) {
		this.branchNo2 = branchNo2;
	}

	public String getChoiceCnt() {
		return choiceCnt;
	}

	public void setChoiceCnt(String choiceCnt) {
		this.choiceCnt = choiceCnt;
	}

	public String getChoiceCnt21() {
		return choiceCnt21;
	}

	public void setChoiceCnt21(String choiceCnt21) {
		this.choiceCnt21 = choiceCnt21;
	}

	public String getChoiceCnt22() {
		return choiceCnt22;
	}

	public void setChoiceCnt22(String choiceCnt22) {
		this.choiceCnt22 = choiceCnt22;
	}

	public String getChoiceCnt41() {
		return choiceCnt41;
	}

	public void setChoiceCnt41(String choiceCnt41) {
		this.choiceCnt41 = choiceCnt41;
	}

	public String getChoiceCnt42() {
		return choiceCnt42;
	}

	public void setChoiceCnt42(String choiceCnt42) {
		this.choiceCnt42 = choiceCnt42;
	}

	public String getChoiceCnts() {
		return choiceCnts;
	}

	public void setChoiceCnts(String choiceCnts) {
		this.choiceCnts = choiceCnts;
	}

	public String getChoiceRange() {
		return choiceRange;
	}

	public void setChoiceRange(String choiceRange) {
		this.choiceRange = choiceRange;
	}

	public String getChoiceRange21() {
		return choiceRange21;
	}

	public void setChoiceRange21(String choiceRange21) {
		this.choiceRange21 = choiceRange21;
	}

	public String getChoiceRange22() {
		return choiceRange22;
	}

	public void setChoiceRange22(String choiceRange22) {
		this.choiceRange22 = choiceRange22;
	}

	public String getChoiceRange41() {
		return choiceRange41;
	}

	public void setChoiceRange41(String choiceRange41) {
		this.choiceRange41 = choiceRange41;
	}

	public String getChoiceRange42() {
		return choiceRange42;
	}

	public void setChoiceRange42(String choiceRange42) {
		this.choiceRange42 = choiceRange42;
	}

	public String getCnts() {
		return cnts;
	}

	public void setCnts(String cnts) {
		this.cnts = cnts;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getDelYnd() {
		return delYnd;
	}

	public void setDelYnd(String delYnd) {
		this.delYnd = delYnd;
	}

	public String getDelYnu() {
		return delYnu;
	}

	public void setDelYnu(String delYnu) {
		this.delYnu = delYnu;
	}

	public String getExamNo() {
		return examNo;
	}

	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}

	public String getExamNo11() {
		return examNo11;
	}

	public void setExamNo11(String examNo11) {
		this.examNo11 = examNo11;
	}

	public String getExamNo12() {
		return examNo12;
	}

	public void setExamNo12(String examNo12) {
		this.examNo12 = examNo12;
	}

	public String getExamNo21() {
		return examNo21;
	}

	public void setExamNo21(String examNo21) {
		this.examNo21 = examNo21;
	}

	public String getExamNo22() {
		return examNo22;
	}

	public void setExamNo22(String examNo22) {
		this.examNo22 = examNo22;
	}

	public String getExamNo41() {
		return examNo41;
	}

	public void setExamNo41(String examNo41) {
		this.examNo41 = examNo41;
	}

	public String getExamNo42() {
		return examNo42;
	}

	public void setExamNo42(String examNo42) {
		this.examNo42 = examNo42;
	}

	public String getExamTitle() {
		return examTitle;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}

	public String getExamTitle11() {
		return examTitle11;
	}

	public void setExamTitle11(String examTitle11) {
		this.examTitle11 = examTitle11;
	}

	public String getExamTitle21() {
		return examTitle21;
	}

	public void setExamTitle21(String examTitle21) {
		this.examTitle21 = examTitle21;
	}

	public String getExamTitle41() {
		return examTitle41;
	}

	public void setExamTitle41(String examTitle41) {
		this.examTitle41 = examTitle41;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getFileDesc12() {
		return fileDesc12;
	}

	public void setFileDesc12(String fileDesc12) {
		this.fileDesc12 = fileDesc12;
	}

	public String getFileDesc22() {
		return fileDesc22;
	}

	public void setFileDesc22(String fileDesc22) {
		this.fileDesc22 = fileDesc22;
	}

	public String getFileDesc42() {
		return fileDesc42;
	}

	public void setFileDesc42(String fileDesc42) {
		this.fileDesc42 = fileDesc42;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath12() {
		return filePath12;
	}

	public void setFilePath12(String filePath12) {
		this.filePath12 = filePath12;
	}

	public String getFilePath22() {
		return filePath22;
	}

	public void setFilePath22(String filePath22) {
		this.filePath22 = filePath22;
	}

	public String getFilePath42() {
		return filePath42;
	}

	public void setFilePath42(String filePath42) {
		this.filePath42 = filePath42;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileSize12() {
		return fileSize12;
	}

	public void setFileSize12(String fileSize12) {
		this.fileSize12 = fileSize12;
	}

	public String getFileSize22() {
		return fileSize22;
	}

	public void setFileSize22(String fileSize22) {
		this.fileSize22 = fileSize22;
	}

	public String getFileSize42() {
		return fileSize42;
	}

	public void setFileSize42(String fileSize42) {
		this.fileSize42 = fileSize42;
	}

	public String getFilterAnswer() {
		return filterAnswer;
	}

	public void setFilterAnswer(String filterAnswer) {
		this.filterAnswer = filterAnswer;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMaxs() {
		return maxs;
	}

	public void setMaxs(String maxs) {
		this.maxs = maxs;
	}

	public String getMergeTitle() {
		return mergeTitle;
	}

	public void setMergeTitle(String mergeTitle) {
		this.mergeTitle = mergeTitle;
	}

	public String getMergeTitle2() {
		return mergeTitle2;
	}

	public void setMergeTitle2(String mergeTitle2) {
		this.mergeTitle2 = mergeTitle2;
	}

	public String getMergeTitle3() {
		return mergeTitle3;
	}

	public void setMergeTitle3(String mergeTitle3) {
		this.mergeTitle3 = mergeTitle3;
	}

	public String getOrgFname() {
		return orgFname;
	}

	public void setOrgFname(String orgFname) {
		this.orgFname = orgFname;
	}

	public String getOrgFname12() {
		return orgFname12;
	}

	public void setOrgFname12(String orgFname12) {
		this.orgFname12 = orgFname12;
	}

	public String getOrgFname22() {
		return orgFname22;
	}

	public void setOrgFname22(String orgFname22) {
		this.orgFname22 = orgFname22;
	}

	public String getOrgFname42() {
		return orgFname42;
	}

	public void setOrgFname42(String orgFname42) {
		this.orgFname42 = orgFname42;
	}

	public String getPollAsk() {
		return pollAsk;
	}

	public void setPollAsk(String pollAsk) {
		this.pollAsk = pollAsk;
	}

	public String getPollAskName() {
		return pollAskName;
	}

	public void setPollAskName(String pollAskName) {
		this.pollAskName = pollAskName;
	}

	public String getPollFieldName() {
		return pollFieldName;
	}

	public void setPollFieldName(String pollFieldName) {
		this.pollFieldName = pollFieldName;
	}

	public String getPollQuestStatus() {
		return pollQuestStatus;
	}

	public void setPollQuestStatus(String pollQuestStatus) {
		this.pollQuestStatus = pollQuestStatus;
	}

	public Date getPollTermend() {
		return pollTermend;
	}

	public void setPollTermend(Date pollTermend) {
		this.pollTermend = pollTermend;
	}

	public Date getPollTermstart() {
		return pollTermstart;
	}

	public void setPollTermstart(Date pollTermstart) {
		this.pollTermstart = pollTermstart;
	}

	public String getPollTitle() {
		return pollTitle;
	}

	public void setPollTitle(String pollTitle) {
		this.pollTitle = pollTitle;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSavFname() {
		return savFname;
	}

	public void setSavFname(String savFname) {
		this.savFname = savFname;
	}

	public String getSavFname12() {
		return savFname12;
	}

	public void setSavFname12(String savFname12) {
		this.savFname12 = savFname12;
	}

	public String getSavFname22() {
		return savFname22;
	}

	public void setSavFname22(String savFname22) {
		this.savFname22 = savFname22;
	}

	public String getSavFname42() {
		return savFname42;
	}

	public void setSavFname42(String savFname42) {
		this.savFname42 = savFname42;
	}

	public Collection getAnswerMngList() {
		return answerMngList;
	}

	public void setAnswerMngList(Collection answerMngList) {
		this.answerMngList = answerMngList;
	}

	public Collection getExampleMngList() {
		return exampleMngList;
	}

	public void setExampleMngList(Collection exampleMngList) {
		this.exampleMngList = exampleMngList;
	}

	public Collection getImgMngList() {
		return imgMngList;
	}

	public void setImgMngList(Collection imgMngList) {
		this.imgMngList = imgMngList;
	}

	public Collection getQuestAddfileList() {
		return questAddfileList;
	}

	public void setQuestAddfileList(Collection questAddfileList) {
		this.questAddfileList = questAddfileList;
	}
}
