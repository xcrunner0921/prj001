package seoul.research.admincenter.service;

import com.sds.emp.common.SearchVO;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 
 * 프로그램명 : PanelMngSearchVO.java
 * 설명 : 패널현황 검색 조건  
 *  
 * -----------------------------------------
 *    변경일            이름          설명 
 * -----------------------------------------
 * 2013.10.12.  하현숙    최초작성  
 * 
 */

public class PanelMngSearchVO extends SearchVO {

	private static final long serialVersionUID = 1L;
	
	private String searchRegDatestart;	//시작일
	private String searchRegDateend;	//종료일
	private String searchRegDate;		//등록일 
	private String searchPanelStatus; 	//상태
	private String searchType;			//아이디,이름
	private String searchText;			//검색어
	
	public String getSearchRegDatestart() {
		return searchRegDatestart;
	}
	public void setSearchRegDatestart(String searchRegDatestart) {
		this.searchRegDatestart = searchRegDatestart;
	}
	
	public String getSearchRegDateend() {
		return searchRegDateend;
	}
	public void setSearchRegDateend(String searchRegDateend) {
		this.searchRegDateend = searchRegDateend;
	}
	
	public String getSearchRegDate() {
		return searchRegDate;
	}
	public void setSearchRegDate(String searchRegDate) {
		this.searchRegDate = searchRegDate;
	}
	
	public String getSearchPanelStatus() {
		return searchPanelStatus;
	}
	public void setSearchPanelStatus(String searchPanelStatus) {
		this.searchPanelStatus = searchPanelStatus;
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	
}
