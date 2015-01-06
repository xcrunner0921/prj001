package seoul.research.domain;

/**
 * This Survey entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class ExampleMng implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	private int questNo;
	private int examNo;

	private String examTitle;
	private int branchNo;
	private String mergeNo;
	private String mergeTitle;

	// extra fields
	private String branchYn;

	public ExampleMng() {
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

	public String getBranchYn() {
		return branchYn;
	}

	public void setBranchYn(String branchYn) {
		this.branchYn = branchYn;
	}
}
