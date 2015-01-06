package seoul.research.admincenter.service;

import com.sds.emp.common.SearchVO;

/**
 * This PopupzoneMngSearchVO entity class is inheritance class of com.sds.emp.common.SearchVO. And it contains searchUseYn
 * attribute for product list search and putter,setter methods of it.
 * 
 * @author Jeryeon Kim
 */
public class PopupzoneMngSearchVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	private String searchTitle;

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

}
