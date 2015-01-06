package seoul.research.admincenter.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import seoul.research.admincenter.dao.PopupzoneMngDAO;
import seoul.research.admincenter.service.PopupzoneMngSearchVO;
import seoul.research.admincenter.service.PopupzoneMngService;
import seoul.research.domain.PopupMng;
import anyframe.common.Page;
import anyframe.core.properties.IPropertiesService;

/**
 * This PopupzoneMngServiceImpl is implementing from PopupzoneMngService, this class contains
 * PopupzoneMngDAO,ProductService,IIdGenerationService setter methods and createPopupzoneMng,
 * updatePopupzoneMng,findPopupzoneMngList,getDropDownPopupzoneMngList and findPopupzoneMng method.
 * 
 * @author Jeryeon Kim
 */

@Service("popupzoneMngService")
@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
public class PopupzoneMngServiceImpl implements PopupzoneMngService {

	private static Log log = LogFactory.getLog(PopupzoneMngServiceImpl.class);

	/**
	 * popupzoneMngDAO is used to access the PopupzoneMngDAO methods createPopupzoneMng, updatePopupzoneMng, removePopupzoneMng,
	 * findPopupzoneMng,findPopupzoneMngList and getDropDownPopupzoneMngList.
	 */
	@Resource(name = "popupzoneMngDAOQuery")
	private PopupzoneMngDAO popupzoneMngDAO;

	@Resource
	private MessageSource messageSource;

	@Resource
	private IPropertiesService propertiesService;

	public Page findPopupzoneMngList(PopupzoneMngSearchVO searchVO) throws Exception {
		return popupzoneMngDAO.findPopupzoneMngList(searchVO);
	}

	public void updatePopupzoneMng(PopupMng popupMng) throws Exception {
		popupzoneMngDAO.updatePopupzoneMng(popupMng);
	}

	public void createPopupzoneAddfile(PopupMng popupMng, MultipartFile multipartFile) throws Exception {

		String popmngDir = propertiesService.getString("POPUPZONE_DIR");
		File directory = new File(popmngDir);
		File tempFile = File.createTempFile("popupzone", multipartFile.getOriginalFilename(), directory);
		FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tempFile));

		// PopupMng popupMng = new PopupMng();
		// pollAddfile.setPollIdx(popupMng.getPollIdx());
		// pollAddfile.setSNo(popupMng.getSNo());

		popupMng.setOrgFname(multipartFile.getOriginalFilename());
		popupMng.setSavFname(tempFile.getName());
		popupMng.setFilePath("/upload/popupzone/");
		popupMng.setFileSize((int) multipartFile.getSize());

		// 팝업존 순서 관련 처리
		String rtn = "N";
		int order = popupMng.getPorder();
		// (1)porder = 1 : 기존 porder 를 +1 하여 update
		if (order == 1) {
			popupzoneMngDAO.updatePorderFirst(popupMng);
			rtn = "Y";

			// (2)porder = 5 : insert
		}
		else if (order == 5) {

			rtn = "Y";

			// (3)porder > 1 and porder < 5
			// 자신보다 작은것은 -1, 자신보다 크면 +1
		}
		else {
			popupzoneMngDAO.updatePorderMid(popupMng);
			rtn = "Y";
		}

		// 신규등록
		if ("Y".equals(rtn)) {
			popupzoneMngDAO.createPopupzone(popupMng);
		}

		// popupzoneMngDAO.createPopupzoneAddfile(popupMng);
	}

	public Collection findPorderList(PopupMng popupMng) throws Exception {

		ArrayList porderList = (ArrayList) popupzoneMngDAO.findPorderList(popupMng);
		return porderList;
	}

	public PopupMng findDetailPopupzoneMng(PopupMng popupMng) throws Exception {

		return popupzoneMngDAO.findDetailPopupzoneMng(popupMng);
	}

	public void updatePopupzoneAddfile1(PopupMng popupMng, MultipartFile multipartFile) throws Exception {
		// log.info("==================(2)");
		String popmngDir = propertiesService.getString("POPUPZONE_DIR");
		File directory = new File(popmngDir);
		File tempFile = File.createTempFile("popupzone", multipartFile.getOriginalFilename(), directory);
		FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tempFile));

		// log.info("==================(3)");
		popupMng.setOrgFname(multipartFile.getOriginalFilename());
		popupMng.setSavFname(tempFile.getName());
		popupMng.setFilePath("/upload/popupzone/");
		popupMng.setFileSize((int) multipartFile.getSize());

		// log.info("==================(4)");
		popupzoneMngDAO.updatePopupzoneAddfile1(popupMng);
	}

	public void updatePopupzoneAddfile2(PopupMng popupMng) throws Exception {
		popupzoneMngDAO.updatePopupzoneAddfile2(popupMng);
	}

	/****************** 하현숙 작업 시작 *************************************/
	// 삭제
	public void removePopupzoneMng(PopupMng popupMng) throws Exception {

		popupzoneMngDAO.removePopupzoneMng(popupMng);
		// log.info("===>>>  팝업존 삭제 완료 ");
	}

	/**
	 * 수정
	 */
	public void updatePopupzoneMng(HttpServletRequest request, PopupMng popupMng) throws Exception {

		// System.out.println("==================PopupzoneMngServiceImpl ===============================");

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("popupzoneAddfile");

		String beforePorder = Integer.toString(popupMng.getBeforePorder());
		String porder = Integer.toString(popupMng.getPorder());

		// System.out.println("==============  beforePorder : " + beforePorder + "         porder : "+ porder +
		// " ==========================");

		// 기존 순서 변경 없이 내용만 수정 할 때
		if (beforePorder.equals(porder)) {

			if (!multipartFile.isEmpty()) {

				String popmngDir = propertiesService.getString("POPUPZONE_DIR");
				File directory = new File(popmngDir);
				File tempFile = File.createTempFile("popupzone", multipartFile.getOriginalFilename(), directory);
				FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tempFile));

				popupMng.setOrgFname(multipartFile.getOriginalFilename());
				popupMng.setSavFname(tempFile.getName());
				popupMng.setFilePath("/upload/popupzone/");
				popupMng.setFileSize((int) multipartFile.getSize());

				popupzoneMngDAO.updatePopupzoneAddfile1(popupMng);

			}
			else {
				popupzoneMngDAO.updatePopupzoneAddfile2(popupMng);
			}

			// 기존 순위 변경됨
		}
		else {

			// 작은 순서에서 큰 순서로 변경되는 경우 ( 1->2으로 변경)
			if (Integer.parseInt(beforePorder) < Integer.parseInt(porder)) {
				// update : porder -1

				// System.out.println("===>>   ( 1->2으로 변경)  popupMng.getSNo() : " + popupMng.getSNo());
				// 자신이외의 내역 order를 변경
				int rtnValue = popupzoneMngDAO.updatePorderToLarge(popupMng);

				// 자신 값을 최종 수정
				if (rtnValue >= 1) {
					if (!multipartFile.isEmpty()) {

						String popmngDir = propertiesService.getString("POPUPZONE_DIR");
						File directory = new File(popmngDir);
						File tempFile = File.createTempFile("popupzone", multipartFile.getOriginalFilename(), directory);
						FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tempFile));

						popupMng.setOrgFname(multipartFile.getOriginalFilename());
						popupMng.setSavFname(tempFile.getName());
						popupMng.setFilePath("/upload/popupzone/");
						popupMng.setFileSize((int) multipartFile.getSize());

						popupzoneMngDAO.updatePopupzoneAddfile1(popupMng);

					}
					else {
						popupzoneMngDAO.updatePopupzoneAddfile2(popupMng);
					}
				}

				// 큰 순서에서 작은 순서로 변경되는 경우 ( 4->2로 변경 )
			}
			else {
				// System.out.println("===>>   ( 4->2으로 변경)  popupMng.getSNo() : " + popupMng.getSNo());
				// 자신이외의 내역 order를 변경
				int rtnValue = popupzoneMngDAO.updatePorderToSmall(popupMng);

				// 자신 값을 최종 수정
				if (rtnValue >= 1) {
					if (!multipartFile.isEmpty()) {

						String popmngDir = propertiesService.getString("POPUPZONE_DIR");
						File directory = new File(popmngDir);
						File tempFile = File.createTempFile("popupzone", multipartFile.getOriginalFilename(), directory);
						FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(tempFile));

						popupMng.setOrgFname(multipartFile.getOriginalFilename());
						popupMng.setSavFname(tempFile.getName());
						popupMng.setFilePath("/upload/popupzone/");
						popupMng.setFileSize((int) multipartFile.getSize());

						popupzoneMngDAO.updatePopupzoneAddfile1(popupMng);

					}
					else {
						popupzoneMngDAO.updatePopupzoneAddfile2(popupMng);
					}
				}

			}
		}

	}

	/**
	 * 메인 : 팝업존
	 */
	public Page listMain() throws Exception {
		log.debug("=====>>  /main.do  메인 : 팝업존  ServiceImpl  시작 ");
		return popupzoneMngDAO.listMain();
	}
}
