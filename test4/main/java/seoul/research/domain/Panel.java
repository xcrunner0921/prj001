package seoul.research.domain;

import java.util.Collection;
import java.util.Date;

/**
 *
 * : 2013.09.17.
 * 
 * -----------------------------------------
 *    
 * -----------------------------------------
 * 2013.09.17. 
 * 
 */
public class Panel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int SNo;
	private String pnlId;
	private String pnlName;
	private String delflag;
	private Date regDate;
	private String grade;
	private String phoneNo;
	private String email;
	private String birthday;
	private String sido;
	private String mf;
	private String pnlSubject;
	
	//2013.10.10. 하현숙 추가 
	//TB_MEM001_M_RESEARCH : 연령대 (10대/20대/30대/40/대/50대/60대 이상)
	private String age;
	private String ageGroup;
	
	
	public Panel(){
		
	}
	

	public int getSNo() {
		return SNo;
	}

	public void setSNo(int SNo) {
		this.SNo = SNo;
	}
	
	public String getPnlId() {
		return pnlId;
	}
	public void setPnlId(String pnlId) {
		this.pnlId = pnlId;
	}
	public String getPnlName() {
		return pnlName;
	}
	public void setPnlName(String pnlName) {
		this.pnlName = pnlName;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getMf() {
		return mf;
	}
	public void setMf(String mf) {
		this.mf = mf;
	}
	public String getPnlSubject() {
		return pnlSubject;
	}
	public void setPnlSubject(String pnlSubject) {
		this.pnlSubject = pnlSubject;
	}
	

	//회원통합정보 : 연령대
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	//회원통합정보 : 나이
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}


}
