package seoul.research.poll.service;

import com.sds.emp.common.SearchVO;

/**
 * This SurveyWebSearchVO entity class is inheritance class of
 * com.sds.emp.common.SearchVO. And it contains
 * searchUseYn attribute for product list search and
 * putter,setter methods of it.
 * 
 * @author Jeryeon Kim
 */
public class SurveyWebSearchVO extends SearchVO {

    private static final long serialVersionUID = 1L;
    
    private String searchUseYn;
    private String searchName;
	private int searchCount;
    private int pollIdx;
	private int questNo; 
	private String userId; 
	private String examRotateyn;
	
	
	
	
	
	public String getExamRotateyn() {
		return examRotateyn;
	}

	public void setExamRotateyn(String examRotateyn) {
		this.examRotateyn = examRotateyn;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }
/*
    public String toString() {
        StringBuffer arguments = new StringBuffer();
        arguments.append(" searchCondition - " + getSearchCondition() + "\n");
        arguments.append(" searchKeyword - " + getSearchKeyword() + "\n");
        arguments.append(" searchKeyword - " + getSearchGubun() + "\n");
        arguments.append(" pageIndex - " + getPageIndex() + "\n");
        arguments.append(" searchUseYn - " + searchUseYn + "\n");
        arguments.append(" userId - " + userId + "\n");

        return arguments.toString();
    }
*/
}
