package seoul.research.admincenter.service;

import com.sds.emp.common.SearchVO;

/**
 * This QuestMngSearchVO entity class is inheritance class of com.sds.emp.common.SearchVO. And it contains searchUseYn attribute
 * for product list search and putter,setter methods of it.
 * 
 * @author Jeryeon Kim
 */
public class QuestMngSearchVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	private String searchRegDatestart;
	private String searchRegDateend;
	private String searchPollQuest;
	private String searchPollAsk;

	// extra fields
	private String searchPollFieldName;
	private String searchPollTitle;

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

	public String getSearchPollQuest() {
		return searchPollQuest;
	}

	public void setSearchPollQuest(String searchPollQuest) {
		this.searchPollQuest = searchPollQuest;
	}

	public String getSearchPollAsk() {
		return searchPollAsk;
	}

	public void setSearchPollAsk(String searchPollAsk) {
		this.searchPollAsk = searchPollAsk;
	}

	public String getSearchPollFieldName() {
		return searchPollFieldName;
	}

	public void setSearchPollFieldName(String searchPollFieldName) {
		this.searchPollFieldName = searchPollFieldName;
	}

	public String getSearchPollTitle() {
		return searchPollTitle;
	}

	public void setSearchPollTitle(String searchPollTitle) {
		this.searchPollTitle = searchPollTitle;
	}
}
