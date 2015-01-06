package seoul.research.domain;

/**
 * This Survey entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class PollAddfile implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	private int sNo;

	private String fileGubun;
	private String orgFname;
	private String savFname;
	private String filePath;
	private int fileSize;

	public PollAddfile() {
	}

	public int getPollIdx() {
		return pollIdx;
	}

	public void setPollIdx(int pollIdx) {
		this.pollIdx = pollIdx;
	}

	public int getSNo() {
		return sNo;
	}

	public void setSNo(int sNo) {
		this.sNo = sNo;
	}

	public String getFileGubun() {
		return fileGubun;
	}

	public void setFileGubun(String fileGubun) {
		this.fileGubun = fileGubun;
	}

	public String getOrgFname() {
		return orgFname;
	}

	public void setOrgFname(String orgFname) {
		this.orgFname = orgFname;
	}

	public String getSavFname() {
		return savFname;
	}

	public void setSavFname(String savFname) {
		this.savFname = savFname;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
}
