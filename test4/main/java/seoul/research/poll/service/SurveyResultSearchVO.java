package seoul.research.poll.service;

import com.sds.emp.common.SearchVO;

/**
 * 시스템 : 서울시 온라인 여론조사 시스템 
 * 프로그램명 : SurveyResultController.java
 * 설명 : 조사결과 검색 조건  
 *  
 * -----------------------------------------
 *    변경일            이름          설명 
 * -----------------------------------------
 * 2013.09.11.  하현숙    최초작성  
 * 
 */

public class SurveyResultSearchVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	private String searchRegDatestart;
	private String searchRegDateend;

	private String searchPollFieldName;
	private String searchPollTitle;
	private String searchCondition;
	
	private String searchPollAsk;
	private int searchPollIdx;
	private String userId; 
	private String userSubject; 
	
	/**
	 * 조사기간 - 시작일 
	 * @return
	 */
	public String getSearchRegDatestart() {
		return searchRegDatestart;
	}
	public void setSearchRegDatestart(String searchRegDatestart) {
		this.searchRegDatestart = searchRegDatestart;
	}
	/**
	 * 조사기간 - 종료일 
	 * @return
	 */
	public String getSearchRegDateend() {
		return searchRegDateend;
	}
	public void setSearchRegDateend(String searchRegDateend) {
		this.searchRegDateend = searchRegDateend;
	}
	
	/**
	 * 검색조건 - 분야
	 * @return
	 */
	public String getSearchPollFieldName() {
		return searchPollFieldName;
	}
	public void setSearchPollFieldName(String searchPollFieldName) {
		this.searchPollFieldName = searchPollFieldName;
	}
	
	/**
	 * 검색조건 - 부서/제목 
	 */

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	/**
	 * 검색어 입력 
	 * @return
	 */
	public String getSearchPollTitle() {
		return searchPollTitle;
	}
	
	public void setSearchPollTitle(String searchPollTitle) {
		this.searchPollTitle = searchPollTitle;
	}
	
	/**
	 * 검색조건 - (요청)부서
	 * @return
	 */
	
	public void setSearchPollAsk(String searchPollAsk) {
		this.searchPollAsk = searchPollAsk;
	}
	public String getSearchPollAsk() {
		return searchPollAsk;
	}


	/**
	 * 여론조사 번호
	 * @return
	 */
	public int getSearchPollIdx() {
		return searchPollIdx;
	}
	public void setSearchPollIdx(int searchPollIdx) {
		this.searchPollIdx = searchPollIdx;
	}
	

	/**
	 * 회원아이디
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 회원구분
	 * @return
	 */
	public String getUserSubject() {
		return userSubject;
	}
	public void setUserSubject(String userSubject) {
		this.userSubject = userSubject;
	}
}
