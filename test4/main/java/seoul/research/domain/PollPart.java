package seoul.research.domain;

/**
 * This Survey entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class PollPart implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	private String partGubun;
	private String porCode;

	private int partNumber;

	// extra fields
	private String porCodeName;

	public PollPart() {
	}

	public int getPollIdx() {
		return pollIdx;
	}

	public void setPollIdx(int pollIdx) {
		this.pollIdx = pollIdx;
	}

	public String getPartGubun() {
		return partGubun;
	}

	public void setPartGubun(String partGubun) {
		this.partGubun = partGubun;
	}

	public String getPorCode() {
		return porCode;
	}

	public void setPorCode(String porCode) {
		this.porCode = porCode;
	}

	public int getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}

	public String getPorCodeName() {
		return porCodeName;
	}

	public void setPorCodeName(String porCodeName) {
		this.porCodeName = porCodeName;
	}
}
