package seoul.research.admincenter.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import seoul.research.admincenter.service.PopupzoneMngSearchVO;
import seoul.research.admincenter.service.PopupzoneMngService;
import seoul.research.domain.PopupMng;
import anyframe.common.Page;
import anyframe.common.exception.BaseException;
import anyframe.core.properties.IPropertiesService;

import com.sds.emp.code.service.CodeService;

/**
 * controller class for CRUD(create, read, update, delete) a pollMng. this class apply to prevent double form submit about add,
 * update, delete.
 * 
 * @author Heewon Jung
 */
@Controller
@SessionAttributes("popupzoneMng")
public class PopupzoneMngController {
	@Resource
	private PopupzoneMngService popupzoneMngService = null;

	@Resource
	private CodeService codeService = null;

	@Resource
	private MessageSource messageSource;

	@Resource
	private IPropertiesService propertiesService;

	@Resource(name = "txManager")
	protected DataSourceTransactionManager txManager;

	public TransactionStatus getTxStatus() {
		DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
		txDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return txManager.getTransaction(txDefinition);
	}

	/**
	 * get list of pollMng
	 * 
	 * @param popupzoneMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listPopupzoneMng.do")
	public ModelAndView list(PopupzoneMngSearchVO popupzoneMngSearchVO) throws Exception {
		Page resultPage = popupzoneMngService.findPopupzoneMngList(popupzoneMngSearchVO);
		ModelAndView mav = new ModelAndView("listPopupzoneMng");
		mav.addObject("resultPage", resultPage);
		return mav;
	}

	/**
	 * display add pollMng view
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addPopupzoneMngView.do")
	public ModelAndView addView(PopupMng popupMng) throws Exception {
		Collection PorderList = popupzoneMngService.findPorderList(popupMng);

		ModelAndView mav = new ModelAndView("addPopupzoneMngView");
		mav.addObject("PorderList", PorderList);
		return mav;
	}

	/**
	 * add pollMng
	 * 
	 * @param pollMng
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addPopupzoneMng.do")
	public ModelAndView add(HttpServletRequest request, PopupMng popupMng, HttpSession session, SessionStatus status)
			throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			String userId = (String) session.getAttribute("userId");
			popupMng.setRegId(userId);
			popupMng.setModId(userId);

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("popupzoneAddfile");

			int porder = popupMng.getPorder();

			if (!multipartFile.isEmpty()) {
				// System.out.println("===========>>  controller /addPopupzoneMng.do (91) porder : " + porder);
				Collection PorderList = popupzoneMngService.findPorderList(popupMng);
				popupMng.setTotalCnt(PorderList.size());
				// popupzoneMngService.updatePopupzoneMng(popupMng);
				popupMng.setPorder(porder);
				popupzoneMngService.createPopupzoneAddfile(popupMng, multipartFile);
			}

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.popupzonemngcontroller.add");
		}

		status.setComplete();

		return new ModelAndView("listPopupzoneMngAction");
	}

	/**
	 * 상세보기
	 * 
	 * @param popupMng
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detailPopupzoneMng.do")
	public ModelAndView detail(PopupMng popupMng) throws Exception {

		PopupMng detailPopupzone = popupzoneMngService.findDetailPopupzoneMng(popupMng);
		Collection PorderList = popupzoneMngService.findPorderList(popupMng);

		ModelAndView mav = new ModelAndView("detailPopupzoneMng");
		mav.addObject("PorderList", PorderList);
		mav.addObject("detailPopupzone", detailPopupzone);
		return mav;
	}

	@RequestMapping("/modifyPopupzoneMng.do")
	public ModelAndView modify(PopupMng popupMng) throws Exception {

		PopupMng modifyPopupzone = popupzoneMngService.findDetailPopupzoneMng(popupMng);
		Collection PorderList = popupzoneMngService.findPorderList(popupMng);

		ModelAndView mav = new ModelAndView("modifyPopupzoneMng");
		mav.addObject("PorderList", PorderList);
		mav.addObject("modifyPopupzone", modifyPopupzone);
		return mav;
	}

	/**
	 * update pollMng
	 * 
	 * @param session
	 * @param pollMng
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatePopupzoneMng.do")
	public ModelAndView update(HttpServletRequest request, PopupMng popupMng, HttpSession session, SessionStatus status)
			throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			String userId = (String) session.getAttribute("userId");
			popupMng.setModId(userId);

			// System.out.println("===========>>  controller  (169)  ");
			popupzoneMngService.updatePopupzoneMng(request, popupMng);

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.popupzonemngcontroller.update");
		}

		status.setComplete();

		return new ModelAndView("listPopupzoneMngAction");
	}

	/**
	 * 삭제
	 * 
	 * @param
	 * @param popupzoneMngSearchVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deletePopupzoneMng.do")
	public ModelAndView delete(PopupMng popupMng, SessionStatus status) throws Exception {
		TransactionStatus txStatus = getTxStatus();

		try {
			popupzoneMngService.removePopupzoneMng(popupMng);

			txManager.commit(txStatus);
		}
		catch (Exception ex) {
			txManager.rollback(txStatus);

			throw new BaseException(messageSource, "error.popupzonemngcontroller.delete");
		}

		status.setComplete();

		return new ModelAndView("listPopupzoneMngAction");
	}

	@RequestMapping("/downloadPopupzoneMng.do")
	public void download(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		String popupzoneDir = propertiesService.getString("POPUPZONE_DIR");

		String filePath = request.getParameter("filePath") != null ? request.getParameter("filePath").trim() : "";
		String savFname = request.getParameter("savFname") != null ? request.getParameter("savFname").trim() : "";
		String orgFname = request.getParameter("orgFname") != null ? request.getParameter("orgFname").trim() : "";

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + orgFname + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");

		FileCopyUtils.copy(new FileInputStream(new File(popupzoneDir, savFname)), response.getOutputStream());
	}
}
