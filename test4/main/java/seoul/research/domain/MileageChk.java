package seoul.research.domain;

import java.util.Collection;
import java.util.Date;

/**
 * This MileageChk entity class contains all attributes of CATEGORY table and putter,setter methods of them. And it contains
 * toString method for logging.
 * 
 * @author Jeryeon Kim
 */
public class MileageChk implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String pollTitle; 
	private String pollSeq;
	private String id;
	private String path;
	private String regdate;
	
	
	public String getPollTitle() {
		return pollTitle;
	}
	public void setPollTitle(String pollTitle) {
		this.pollTitle = pollTitle;
	}
	
	public String getPollSeq() {
		return pollSeq;
	}
	public void setPollSeq(String pollSeq) {
		this.pollSeq = pollSeq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	
	
	
	
	
	
}
