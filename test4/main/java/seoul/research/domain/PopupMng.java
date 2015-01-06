package seoul.research.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class PopupMng implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String sNo;
    private String title;
    private Date strDate;
    private Date endDate;
    private String linkUrl;
    private int porder;
    private String pdesc;
    private String filePath;
    private String orgFname;
    private String savFname;
    private int fileSize;
    private String regId;
    private String modId;
    
    private int totalCnt;
    private int beforePorder;
   
	//YYYY-MM-DD  hh24:mm 
    private String strDateYmd;
    private String strDateHour;
    private String strDateMinute;
	private String endDateYmd;
	private String endDateHour;
	private String endDateMinute;
    
    
	public String getSNo() {
		return sNo;
	}
	public void setSNo(String sNo) {
		this.sNo = sNo;
	}
	public String getStrDateYmd() {
		return strDateYmd;
	}
	public void setStrDateYmd(String strDateYmd) {
		this.strDateYmd = strDateYmd;
	}
	public String getStrDateHour() {
		return strDateHour;
	}
	public void setStrDateHour(String strDateHour) {
		this.strDateHour = strDateHour;
	}
	public String getStrDateMinute() {
		return strDateMinute;
	}
	public void setStrDateMinute(String strDateMinute) {
		this.strDateMinute = strDateMinute;
	}
	public String getEndDateYmd() {
		return endDateYmd;
	}
	public void setEndDateYmd(String endDateYmd) {
		this.endDateYmd = endDateYmd;
	}
	public String getEndDateHour() {
		return endDateHour;
	}
	public void setEndDateHour(String endDateHour) {
		this.endDateHour = endDateHour;
	}
	public String getEndDateMinute() {
		return endDateMinute;
	}
	public void setEndDateMinute(String endDateMinute) {
		this.endDateMinute = endDateMinute;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public int getBeforePorder() {
		return beforePorder;
	}
	public void setBeforePorder(int beforePorder) {
		this.beforePorder = beforePorder;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getStrDate() {
		return strDate;
	}
	public void setStrDate(Date strDate) {
		this.strDate = strDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public int getPorder() {
		return porder;
	}
	public void setPorder(int porder) {
		this.porder = porder;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
    
    

    
}