package seoul.research.admincenter.dao.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import seoul.research.admincenter.dao.ApplMngDAO;
import seoul.research.admincenter.service.ApplMngSearchVO;
import seoul.research.domain.AnswerMng;
import seoul.research.domain.PanelState;
import seoul.research.domain.PollAddfile;
import seoul.research.domain.PollMng;
import seoul.research.domain.PollPart;
import seoul.research.domain.QuestMng;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

/**
 * This DAO implements class contains IQueryService to get IQueryService services and IPropertiesService to get
 * IPropertiesService services , createApplMng, updateApplMng, findApplMng, findApplMngList, and getDropDownApplMngList methods
 * for different functionality on PollMng.
 * 
 * @author Jeryeon Kim
 */
@Repository("applMngDAOQuery")
public class ApplMngDAOQueryImpl extends AbstractDAO implements ApplMngDAO {

	/** an instance variable for the propertiesService. */
	@Resource
	private IPropertiesService propertiesService;

	@Resource
	private IQueryService queryService;

	@PostConstruct
	public void initialize() {
		setQueryService(this.queryService);
	}

	public Page findPollMngList(ApplMngSearchVO searchVO) throws Exception {
		// String searchRegDatestart = EmpUtil.null2str(searchVO.getSearchRegDatestart());
		// String searchRegDateend = EmpUtil.null2str(searchVO.getSearchRegDateend());
		// String searchCondition = EmpUtil.null2str(searchVO.getSearchCondition());
		// String searchKeyword = EmpUtil.null2str(searchVO.getSearchKeyword());
		/*
		 * if (!"".equals(searchRegDatestart)) { searchVO.setSearchRegDatestart(searchRegDatestart + " 00:00:00"); } if
		 * (!"".equals(searchRegDateend)) { searchVO.setSearchRegDateend(searchRegDateend + " 23:59:59"); }
		 */
		searchVO.setSearchPollTitle(searchVO.getSearchKeyword());

		// int pageIndex = searchVO.getPageIndex();
		int pageSize = propertiesService.getInt("PAGE_SIZE");
		int pageUnit = propertiesService.getInt("PAGE_UNIT");

		return findListWithPaging("PollMng4", searchVO, searchVO.getPageIndex(), pageSize, pageUnit);
	}

	public void createPollMng(PollMng pollMng) throws Exception {
		// String pollTermstartYmd = EmpUtil.null2str(pollMng.getPollTermstartYmd());
		// String pollTermstartHour = EmpUtil.null2str(pollMng.getPollTermstartHour());
		// String pollTermstartMinute = EmpUtil.null2str(pollMng.getPollTermstartMinute());
		// String pollTermendYmd = EmpUtil.null2str(pollMng.getPollTermendYmd());
		// String pollTermendHour = EmpUtil.null2str(pollMng.getPollTermendHour());
		// String pollTermendMinute = EmpUtil.null2str(pollMng.getPollTermendMinute());

		pollMng.setPollTermstart(new Date(Integer.parseInt(pollMng.getPollTermstartYmd().substring(0, 4)) - 1900, Integer
				.parseInt(pollMng.getPollTermstartYmd().substring(5, 7)) - 1, Integer.parseInt(pollMng.getPollTermstartYmd()
				.substring(8, 10)), Integer.parseInt(pollMng.getPollTermstartHour()), Integer.parseInt(pollMng
				.getPollTermstartMinute())));
		pollMng.setPollTermend(new Date(Integer.parseInt(pollMng.getPollTermendYmd().substring(0, 4)) - 1900, Integer
				.parseInt(pollMng.getPollTermendYmd().substring(5, 7)) - 1, Integer.parseInt(pollMng.getPollTermendYmd()
				.substring(8, 10)), Integer.parseInt(pollMng.getPollTermendHour()), Integer.parseInt(pollMng
				.getPollTermendMinute())));

		create("PollMng", pollMng);
	}

	public PollMng findPollMng(int pollIdx) throws Exception {

		PollMng pollMng = new PollMng();
		pollMng.setPollIdx(pollIdx);

		return (PollMng) findByPk("PollMng3", pollMng);
	}

	public PollMng findPollMng2(int pollIdx) throws Exception {

		PollMng pollMng = new PollMng();
		pollMng.setPollIdx(pollIdx);

		return (PollMng) findByPk("PollMng4", pollMng);
	}

	public void updatePollMng(PollMng pollMng) throws Exception {
		// String pollTermstartYmd = EmpUtil.null2str(pollMng.getPollTermstartYmd());
		// String pollTermstartHour = EmpUtil.null2str(pollMng.getPollTermstartHour());
		// String pollTermstartMinute = EmpUtil.null2str(pollMng.getPollTermstartMinute());
		// String pollTermendYmd = EmpUtil.null2str(pollMng.getPollTermendYmd());
		// String pollTermendHour = EmpUtil.null2str(pollMng.getPollTermendHour());
		// String pollTermendMinute = EmpUtil.null2str(pollMng.getPollTermendMinute());

		pollMng.setPollTermstart(new Date(Integer.parseInt(pollMng.getPollTermstartYmd().substring(0, 4)) - 1900, Integer
				.parseInt(pollMng.getPollTermstartYmd().substring(5, 7)) - 1, Integer.parseInt(pollMng.getPollTermstartYmd()
				.substring(8, 10)), Integer.parseInt(pollMng.getPollTermstartHour()), Integer.parseInt(pollMng
				.getPollTermstartMinute())));
		pollMng.setPollTermend(new Date(Integer.parseInt(pollMng.getPollTermendYmd().substring(0, 4)) - 1900, Integer
				.parseInt(pollMng.getPollTermendYmd().substring(5, 7)) - 1, Integer.parseInt(pollMng.getPollTermendYmd()
				.substring(8, 10)), Integer.parseInt(pollMng.getPollTermendHour()), Integer.parseInt(pollMng
				.getPollTermendMinute())));

		update("PollMng2", pollMng);
	}

	public void removePollMng(PollMng pollMng) throws Exception {

		remove("PollMng", pollMng);
	}

	public int getNextPollIdx() throws Exception {
		Collection collection = getQueryService().find("getNextPollIdx", new Object[] {});

		Iterator iterator = collection.iterator();
		if (iterator.hasNext()) {
			Map map = (Map) iterator.next();

			String pollIdx = map.get("pollIdx").toString();
			return Integer.parseInt(pollIdx);
		}

		return 0;
	}

	public int getNextSNo(int pollIdx) throws Exception {
		PollMng vo = new PollMng();
		vo.setPollIdx(pollIdx);

		Collection collection = getQueryService().find("getNextSNo", new Object[] { new Object[] { "vo", vo } });

		Iterator iterator = collection.iterator();
		if (iterator.hasNext()) {
			Map map = (Map) iterator.next();

			String sNo = map.get("sNo").toString();
			return Integer.parseInt(sNo);
		}

		return 0;
	}

	// public Collection findPollPartList(int pollIdx, String partGubun) throws Exception {
	// PollPart pollPart = new PollPart();
	// pollPart.setPollIdx(pollIdx);
	// pollPart.setPartGubun(partGubun);

	// return findList("PollPart", pollPart);
	// }

	public Collection findPollAddfileList(int pollIdx, String fileGubun) throws Exception {
		PollAddfile pollAddfile = new PollAddfile();
		pollAddfile.setPollIdx(pollIdx);
		pollAddfile.setFileGubun(fileGubun);

		return findList("PollAddfile", pollAddfile);
	}

	public Collection findQuestMngList(int pollIdx) throws Exception {
		QuestMng questMng = new QuestMng();
		questMng.setPollIdx(pollIdx);

		return findList("QuestMng2", questMng);
	}

	public Collection findQuestMng2List(int pollIdx) throws Exception {
		QuestMng questMng = new QuestMng();
		questMng.setPollIdx(pollIdx);

		return findList("QuestMng3", questMng);
	}

	public Collection findQuestMng3List(int pollIdx) throws Exception {
		QuestMng questMng = new QuestMng();
		questMng.setPollIdx(pollIdx);

		return findList("QuestMng4", questMng);
	}

	public QuestMng findQuestMng(int pollIdx) throws Exception {
		QuestMng questMng = new QuestMng();
		questMng.setPollIdx(pollIdx);

		return (QuestMng) findByPk("QuestMng2", questMng);
	}

	public Collection findAnswerMngList(int pollIdx, int questNo) throws Exception {
		AnswerMng answerMng = new AnswerMng();
		answerMng.setPollIdx(pollIdx);
		answerMng.setQuestNo(questNo);

		return findList("AnswerMng", answerMng);
	}

	public Collection findAnswerMng2List(int pollIdx, int questNo, String questType) throws Exception {
		AnswerMng answerMng = new AnswerMng();
		answerMng.setPollIdx(pollIdx);
		answerMng.setQuestNo(questNo);
		answerMng.setQuestType(questType);

		return findList("AnswerMng2", answerMng);
	}

	public Collection findPanelStateList(int pollIdx) throws Exception {
		PanelState panelState = new PanelState();
		panelState.setPollIdx(pollIdx);

		return findList("PanelState", panelState);
	}

	public void createPollPart(PollPart pollPart) throws Exception {

		create("PollPart", pollPart);
	}

	public void createPollAddfile(PollAddfile pollAddfile) throws Exception {
		// System.out.println(multipartFile.getName() + ", " + multipartFile.getOriginalFilename() + ", "
		// + multipartFile.getContentType() + ", " + multipartFile.isEmpty() + ", " + multipartFile.getSize());
		// System.out.println(tempFile.getName());

		create("PollAddfile", pollAddfile);
	}

	public void removePollPart(PollPart pollPart) throws Exception {

		remove("PollPart", pollPart);
	}

	public void removePollAddfile(PollAddfile pollAddfile) throws Exception {

		remove("PollAddfile", pollAddfile);
	}
}
