package seoul.research.admincenter.service;

import com.sds.emp.common.SearchVO;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 
 * 프로그램명 : PartMngSearchVO.java
 * 설명 : 조사결과 검색 조건  
 *  
 * -----------------------------------------
 *    변경일            이름          설명 
 * -----------------------------------------
 * 2013.10.11.  하현숙    최초작성  
 * 
 */

public class PartMngSearchVO extends SearchVO {

	private static final long serialVersionUID = 1L;
	
	private String searchPollIdx;	//여론조사 제목
	private String searchPollTitle;	//여론조사 제목
	private String searchPollStatus;//상태(완료,중단,필터)
	private String searchPollType;	//아이디,이름
	private String searchPollText;	//검색어
	
	
	public String getSearchPollIdx() {
		return searchPollIdx;
	}
	public void setSearchPollIdx(String searchPollIdx) {
		this.searchPollIdx = searchPollIdx;
	}
	
	public String getSearchPollTitle() {
		return searchPollTitle;
	}
	public void setSearchPollTitle(String searchPollTitle) {
		this.searchPollTitle = searchPollTitle;
	}
	
	public String getSearchPollStatus() {
		return searchPollStatus;
	}
	public void setSearchPollStatus(String searchPollStatus) {
		this.searchPollStatus = searchPollStatus;
	}
	
	public String getSearchPollType() {
		return searchPollType;
	}
	public void setSearchPollType(String searchPollType) {
		this.searchPollType = searchPollType;
	}
	
	public String getSearchPollText() {
		return searchPollText;
	}
	public void setSearchPollText(String searchPollText) {
		this.searchPollText = searchPollText;
	}
	
}
