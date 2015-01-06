package seoul.research.domain;



/**
 * 응답자현황 조회 
 * PartMng.java
 * 
 * @author 2013.10.11. 하현숙  
 * 
 * */

public class PartMng implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int pollIdx;
	
	private String pollTitle;
	private String pollPanelId;
	private String pollPanelName;
	
	private String pollTermstartYmd;
	private String pollTermendHour;
	private String pollTermendMinute;
	private String pollTermstartHour;
	private String pollTermstartMinute;
	private String pollTermendYmd;
	
	private String pollAnswerDate;//참여일
	private String pollPanelStatus;//상태
	
	private String pollMileageDate; //마일리지 지급일
	private String pollMileage;		//마일리지 점수
	
	

	public PartMng() {
	}

	public int getPollIdx() {
		return pollIdx;
	}
	public void setPollIdx(int pollIdx) {
		this.pollIdx = pollIdx;
	}

	public String getPollTitle() {
		return pollTitle;
	}
	public void setPollTitle(String pollTitle) {
		this.pollTitle = pollTitle;
	}


	public String getPollPanelid() {
		return pollPanelId;
	}
	public void setPollPanelid(String pollPanelId) {
		this.pollPanelId = pollPanelId;
	}
	
	public String getPollPanelname() {
		return pollPanelName;
	}

	public void setPollPanelname(String pollPanelName) {
		this.pollPanelName = pollPanelName;
	}
	public String getPollTermstartYmd() {
		return pollTermstartYmd;
	}

	public void setPollTermstartYmd(String pollTermstartYmd) {
		this.pollTermstartYmd = pollTermstartYmd;
	}
	public String getPollTermendHour() {
		return pollTermendHour;
	}
	
	public void setPollTermendHour(String pollTermendHour) {
		this.pollTermendHour = pollTermendHour;
	}

	public String getPollTermendMinute() {
		return pollTermendMinute;
	}

	public void setPollTermendMinute(String pollTermendMinute) {
		this.pollTermendMinute = pollTermendMinute;
	}
	public String getPollTermstartHour() {
		return pollTermstartHour;
	}
	public void setPollTermstartHour(String pollTermstartHour) {
		this.pollTermstartHour = pollTermstartHour;
	}
	public String getPollTermstartMinute() {
		return pollTermstartMinute;
	}
	public void setPollTermstartMinute(String pollTermstartMinute) {
		this.pollTermstartMinute = pollTermstartMinute;
	}
	
	public String getPollTermendYmd() {
		return pollTermendYmd;
	}
	public void setPollTermendYmd(String pollTermendYmd) {
		this.pollTermendYmd = pollTermendYmd;
	}

	public String getPollAnswerDate() {
		return pollAnswerDate;
	}
	public void setPollAnswerDate(String pollAnswerDate) {
		this.pollAnswerDate = pollAnswerDate;
	}
	public String getPollPanelStatus() {
		return pollPanelStatus;
	}

	public void setPollPanelStatus(String pollPanelStatus) {
		this.pollPanelStatus = pollPanelStatus;
	}

	public String getPollMileageDate() {
		return pollMileageDate;
	}

	public void setPollMileageDate(String pollMileageDate) {
		this.pollMileageDate = pollMileageDate;
	}
	
	public String getPollMileage() {
		return pollMileage;
	}
	public void setPollMileage(String pollMileage) {
		this.pollMileage = pollMileage;
	}

}
