package seoul.research.domain;

/**
 * This Survey entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class PanelState implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	private int questNo;
	private String panelId;

	private int nextQuestNo;
	private String filterYn;
	private String progYn;

	// extra fields
	private String ageGroup;
	private String answers;
	private String mf;

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

	public String getPanelId() {
		return panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}

	public int getNextQuestNo() {
		return nextQuestNo;
	}

	public void setNextQuestNo(int nextQuestNo) {
		this.nextQuestNo = nextQuestNo;
	}

	public String getFilterYn() {
		return filterYn;
	}

	public void setFilterYn(String filterYn) {
		this.filterYn = filterYn;
	}

	public String getProgYn() {
		return progYn;
	}

	public void setProgYn(String progYn) {
		this.progYn = progYn;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}
}
