package seoul.research.domain;

/**
 * This Survey entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class QuestTypeMng implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	private int questNo;
	private String questType;

	int choiceCnt;
	String choiceRange;

	public QuestTypeMng() {
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

	public String getQuestType() {
		return questType;
	}

	public void setQuestType(String questType) {
		this.questType = questType;
	}

	public int getChoiceCnt() {
		return choiceCnt;
	}

	public void setChoiceCnt(int choiceCnt) {
		this.choiceCnt = choiceCnt;
	}

	public String getChoiceRange() {
		return choiceRange;
	}

	public void setChoiceRange(String choiceRange) {
		this.choiceRange = choiceRange;
	}
}
