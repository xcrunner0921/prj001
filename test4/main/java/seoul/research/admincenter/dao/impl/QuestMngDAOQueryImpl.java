package seoul.research.admincenter.dao.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import seoul.research.admincenter.dao.QuestMngDAO;
import seoul.research.admincenter.service.QuestMngSearchVO;
import seoul.research.domain.ExampleMng;
import seoul.research.domain.FilterMng;
import seoul.research.domain.ImgMng;
import seoul.research.domain.PollMng;
import seoul.research.domain.QuestAddfile;
import seoul.research.domain.QuestMng;
import seoul.research.domain.QuestTypeMng;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;
import anyframe.core.query.AbstractDAO;
import anyframe.core.query.IQueryService;

/**
 * This DAO implements class contains IQueryService to get IQueryService services and IPropertiesService to get
 * IPropertiesService services , createQuestMng, updateQuestMng, findQuestMng, findQuestMngList, and getDropDownQuestMngList
 * methods for different functionality on QuestMng.
 * 
 * @author Jeryeon Kim
 */
@Repository("questMngDAOQuery")
public class QuestMngDAOQueryImpl extends AbstractDAO implements QuestMngDAO {

	/** an instance variable for the propertiesService. */
	@Resource
	private IPropertiesService propertiesService;

	@Resource
	private IQueryService queryService;

	@PostConstruct
	public void initialize() {
		setQueryService(this.queryService);
	}

	public Page findPollMngList(QuestMngSearchVO searchVO) throws Exception {
		// String searchRegDatestart = EmpUtil.null2str(searchVO.getSearchRegDatestart());
		// String searchRegDateend = EmpUtil.null2str(searchVO.getSearchRegDateend());
		// String searchCondition = EmpUtil.null2str(searchVO.getSearchCondition());
		// String searchKeyword = EmpUtil.null2str(searchVO.getSearchKeyword());
		/*
		 * if (!"".equals(searchRegDatestart)) { searchVO.setSearchRegDatestart(searchRegDatestart + " 00:00:00"); } if
		 * (!"".equals(searchRegDateend)) { searchVO.setSearchRegDateend(searchRegDateend + " 23:59:59"); }
		 */
		if ("F".equals(searchVO.getSearchCondition())) {
			searchVO.setSearchPollFieldName(searchVO.getSearchKeyword());
		}
		else if ("T".equals(searchVO.getSearchCondition())) {
			searchVO.setSearchPollTitle(searchVO.getSearchKeyword());
		}

		// int pageIndex = searchVO.getPageIndex();
		int pageSize = propertiesService.getInt("PAGE_SIZE");
		int pageUnit = propertiesService.getInt("PAGE_UNIT");

		return findListWithPaging("PollMng2", searchVO, searchVO.getPageIndex(), pageSize, pageUnit);
	}

	public PollMng findPollMng(int pollIdx) throws Exception {

		PollMng pollMng = new PollMng();
		pollMng.setPollIdx(pollIdx);

		return (PollMng) findByPk("PollMng", pollMng);
	}

	public Collection findQuestMngList(int pollIdx) throws Exception {
		QuestMng vo = new QuestMng();
		vo.setPollIdx(pollIdx);

		return findList("QuestMng", vo);
	}

	public void createQuestMng(QuestMng questMng) throws Exception {
		/*
		 * String pollTermstartYmd = EmpUtil.null2str(questMng.getPollTermstartYmd()); String pollTermstartHour =
		 * EmpUtil.null2str(questMng.getPollTermstartHour()); String pollTermstartMinute =
		 * EmpUtil.null2str(questMng.getPollTermstartMinute()); String pollTermendYmd =
		 * EmpUtil.null2str(questMng.getPollTermendYmd()); String pollTermendHour =
		 * EmpUtil.null2str(questMng.getPollTermendHour()); String pollTermendMinute =
		 * EmpUtil.null2str(questMng.getPollTermendMinute());
		 * 
		 * questMng.setPollTermstart(new Date(Integer.parseInt(pollTermstartYmd.substring(0, 4)) - 1900, Integer
		 * .parseInt(pollTermstartYmd.substring(5, 7)) - 1, Integer.parseInt(pollTermstartYmd.substring(8, 10)), Integer
		 * .parseInt(pollTermstartHour), Integer.parseInt(pollTermstartMinute))); questMng.setPollTermend(new
		 * Date(Integer.parseInt(pollTermendYmd.substring(0, 4)) - 1900, Integer .parseInt(pollTermendYmd.substring(5, 7)) - 1,
		 * Integer.parseInt(pollTermendYmd.substring(8, 10)), Integer .parseInt(pollTermendHour),
		 * Integer.parseInt(pollTermendMinute)));
		 */
		create("QuestMng", questMng);
	}

	public QuestMng findQuestMng(int pollIdx, int questNo) throws Exception {

		QuestMng questMng = new QuestMng();
		questMng.setPollIdx(pollIdx);
		questMng.setQuestNo(questNo);

		return (QuestMng) findByPk("QuestMng", questMng);
	}

	public void updateQuestMng(QuestMng questMng) throws Exception {
		/*
		 * String pollTermstartYmd = EmpUtil.null2str(questMng.getPollTermstartYmd()); String pollTermstartHour =
		 * EmpUtil.null2str(questMng.getPollTermstartHour()); String pollTermstartMinute =
		 * EmpUtil.null2str(questMng.getPollTermstartMinute()); String pollTermendYmd =
		 * EmpUtil.null2str(questMng.getPollTermendYmd()); String pollTermendHour =
		 * EmpUtil.null2str(questMng.getPollTermendHour()); String pollTermendMinute =
		 * EmpUtil.null2str(questMng.getPollTermendMinute());
		 * 
		 * questMng.setPollTermstart(new Date(Integer.parseInt(pollTermstartYmd.substring(0, 4)) - 1900, Integer
		 * .parseInt(pollTermstartYmd.substring(5, 7)) - 1, Integer.parseInt(pollTermstartYmd.substring(8, 10)), Integer
		 * .parseInt(pollTermstartHour), Integer.parseInt(pollTermstartMinute))); questMng.setPollTermend(new
		 * Date(Integer.parseInt(pollTermendYmd.substring(0, 4)) - 1900, Integer .parseInt(pollTermendYmd.substring(5, 7)) - 1,
		 * Integer.parseInt(pollTermendYmd.substring(8, 10)), Integer .parseInt(pollTermendHour),
		 * Integer.parseInt(pollTermendMinute)));
		 */
		update("QuestMng", questMng);
	}

	public void removeQuestMng(QuestMng questMng) throws Exception {

		remove("QuestMng", questMng);
	}

	public int getNextQuestNo(int pollIdx) throws Exception {
		QuestMng vo = new QuestMng();
		vo.setPollIdx(pollIdx);

		Collection collection = getQueryService().find("getNextQuestNo", new Object[] { new Object[] { "vo", vo } });

		Iterator iterator = collection.iterator();
		if (iterator.hasNext()) {
			Map map = (Map) iterator.next();

			String questNo = map.get("questNo").toString();
			return Integer.parseInt(questNo);
		}

		return 0;
	}

	public void setPollQuestStatus(int pollIdx, String pollQuestStatus) throws Exception {
		PollMng vo = new PollMng();
		vo.setPollIdx(pollIdx);
		vo.setPollQuestStatus(pollQuestStatus);

		getQueryService().update("setPollQuestStatus", new Object[] { new Object[] { "vo", vo } });
	}

	public Collection findExampleMngList(int pollIdx, int questNo) throws Exception {
		ExampleMng vo = new ExampleMng();
		vo.setPollIdx(pollIdx);
		vo.setQuestNo(questNo);

		return findList("ExampleMng", vo);
	}

	public Collection findExampleMng2List(int pollIdx, int questNo) throws Exception {
		ExampleMng vo = new ExampleMng();
		vo.setPollIdx(pollIdx);
		vo.setQuestNo(questNo);

		return findList("ExampleMng2", vo);
	}

	public Collection findImgMngList(int pollIdx, int questNo) throws Exception {
		ImgMng vo = new ImgMng();
		vo.setPollIdx(pollIdx);
		vo.setQuestNo(questNo);

		return findList("ImgMng", vo);
	}

	public Collection findQuestAddfileList(int pollIdx, int questNo) throws Exception {
		QuestAddfile vo = new QuestAddfile();
		vo.setPollIdx(pollIdx);
		vo.setQuestNo(questNo);

		return findList("QuestAddfile", vo);
	}

	public void createQuestTypeMng(QuestTypeMng questTypeMng) throws Exception {

		create("QuestTypeMng", questTypeMng);
	}

	public void createFilterMng(FilterMng filterMng) throws Exception {

		create("FilterMng", filterMng);
	}

	public void createExampleMng(ExampleMng exampleMng) throws Exception {

		create("ExampleMng", exampleMng);
	}

	public void createImgMng(ImgMng imgMng) throws Exception {

		create("ImgMng", imgMng);
	}

	public void createQuestAddfile(QuestAddfile questAddfile) throws Exception {

		create("QuestAddfile", questAddfile);
	}

	public void removeQuestTypeMng(QuestTypeMng questTypeMng) throws Exception {

		remove("QuestTypeMng", questTypeMng);
	}

	public void removeFilterMng(FilterMng filterMng) throws Exception {

		remove("FilterMng", filterMng);
	}

	public void removeExampleMng(ExampleMng exampleMng) throws Exception {

		remove("ExampleMng", exampleMng);
	}

	public void removeImgMng(ImgMng imgMng) throws Exception {

		remove("ImgMng", imgMng);
	}

	public void removeQuestAddfile(QuestAddfile questAddfile) throws Exception {

		remove("QuestAddfile", questAddfile);
	}
}
