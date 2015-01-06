package seoul.research.domain;

import java.util.Date;

/**
 * This Survey entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class AnswerMng implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	private int questNo;
	private int sNo;

	private String answer;
	private String etcAnswer;
	private String panelId;
	private Date answerDate;

	// extra fields
	private String ageGroup;
	private String mf;
	private String questType;

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

	public int getSNo() {
		return sNo;
	}

	public void setSNo(int sNo) {
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

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public String getQuestType() {
		return questType;
	}

	public void setQuestType(String questType) {
		this.questType = questType;
	}
}
