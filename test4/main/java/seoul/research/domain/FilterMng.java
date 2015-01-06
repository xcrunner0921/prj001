package seoul.research.domain;

/**
 * This Survey entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class FilterMng implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	private int questNo;

	private String filterAnswer;

	public FilterMng() {
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

	public String getFilterAnswer() {
		return filterAnswer;
	}

	public void setFilterAnswer(String filterAnswer) {
		this.filterAnswer = filterAnswer;
	}
}
