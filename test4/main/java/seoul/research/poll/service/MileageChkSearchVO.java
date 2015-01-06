package seoul.research.poll.service;

import seoul.research.domain.MileageChk;

import com.sds.emp.common.SearchVO;

/**
 * This MileageChkSearchVO entity class is inheritance class of
 * com.sds.emp.common.SearchVO. And it contains
 * searchUseYn attribute for product list search and
 * putter,setter methods of it.
 * 
 * @author Jeryeon Kim
 */
public class MileageChkSearchVO extends SearchVO {

    private static final long serialVersionUID = 1L;
    
    private String schdate;
	private String schdate2;
	
	public String getSchdate() {
		return schdate;
	}
	public void setSchdate(String schdate) {
		this.schdate = schdate;
	}
	public String getSchdate2() {
		return schdate2;
	}
	public void setSchdate2(String schdate2) {
		this.schdate2 = schdate2;
	}
	
	
}
