package seoul.research.poll.service;

import seoul.research.domain.MobileResult;

import com.sds.emp.common.SearchVO;

/**
 * This MobileResultSearchVO entity class is inheritance class of
 * com.sds.emp.common.SearchVO. And it contains
 * searchUseYn attribute for product list search and
 * putter,setter methods of it.
 * 
 * @author Jeryeon Kim
 */
public class MobileResultSearchVO extends SearchVO {

    private static final long serialVersionUID = 1L;
    
    private int pollIdx;

	public int getPollIdx() {
		return pollIdx;
	}

	public void setPollIdx(int pollIdx) {
		this.pollIdx = pollIdx;
	}

    
    
	
}
