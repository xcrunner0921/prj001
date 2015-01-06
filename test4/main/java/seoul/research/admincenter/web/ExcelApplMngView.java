package seoul.research.admincenter.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import seoul.research.domain.AnswerMng;
import seoul.research.domain.PanelState;
import seoul.research.domain.PollMng;
import seoul.research.domain.QuestMng;

public class ExcelApplMngView extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map map, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String target = map.get("target").toString();

		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 20);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);

		HSSFCellStyle cellStyle2 = workbook.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font2 = workbook.createFont();
		font2.setFontHeightInPoints((short) 15);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle2.setFont(font2);

		HSSFCellStyle cellStyle3 = workbook.createCellStyle();
		cellStyle3.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle cellStyle4 = workbook.createCellStyle();
		cellStyle4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		String filename = null;
		int rownum = 0;

		if (target.equals("S")) {
			filename = URLEncoder.encode("주관식", "UTF-8");

			// sheet = workbook.createSheet("�ְ��");
			sheet = workbook.createSheet();
			workbook.setSheetName(0, "주관식", HSSFWorkbook.ENCODING_UTF_16);

			@SuppressWarnings("unchecked")
			PollMng pollMng = (PollMng) map.get("pollMng");

			row = sheet.createRow(rownum++);
			// row.setHeight((short) 0x165);

			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("조사 제목 : " + pollMng.getPollTitle());

			ArrayList questMngList = (ArrayList) map.get("questMngList");

			for (int i = 0; i < questMngList.size(); i++) {
				QuestMng questMng = (QuestMng) questMngList.get(i);

				row = sheet.createRow(rownum++);
				// row.setHeight((short) 0x165);

				row = sheet.createRow(rownum++);
				// row.setHeight((short) 0x165);

				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle2);
				cell.setCellValue(questMng.getQuestNo() + ". " + questMng.getQuestTitle());

				ArrayList answerMngList = (ArrayList) questMng.getAnswerMngList();

				for (int j = 0; j < answerMngList.size(); j++) {
					AnswerMng answerMng = (AnswerMng) answerMngList.get(j);

					row = sheet.createRow(rownum++);
					// row.setHeight((short) 0x165);

					cell = row.createCell((short) 0);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle3);
					cell.setCellValue(j + 1);

					cell = row.createCell((short) 1);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle4);
					cell.setCellValue(answerMng.getPanelId());

					cell = row.createCell((short) 2);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle3);
					cell.setCellValue(answerMng.getMf());

					cell = row.createCell((short) 3);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle3);
					cell.setCellValue(answerMng.getAgeGroup());

					cell = row.createCell((short) 4);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle4);
					cell.setCellValue(answerMng.getEtcAnswer());
				}
			}
		}
		else if (target.equals("E")) {
			filename = URLEncoder.encode("기타", "UTF-8");

			// sheet = workbook.createSheet("��Ÿ");
			sheet = workbook.createSheet();
			workbook.setSheetName(0, "기타", HSSFWorkbook.ENCODING_UTF_16);

			@SuppressWarnings("unchecked")
			PollMng pollMng = (PollMng) map.get("pollMng");

			row = sheet.createRow(rownum++);
			// row.setHeight((short) 0x165);

			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("조사 제목 : " + pollMng.getPollTitle());

			ArrayList questMngList = (ArrayList) map.get("questMngList");

			for (int i = 0; i < questMngList.size(); i++) {
				QuestMng questMng = (QuestMng) questMngList.get(i);

				row = sheet.createRow(rownum++);
				// row.setHeight((short) 0x165);

				row = sheet.createRow(rownum++);
				// row.setHeight((short) 0x165);

				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle2);
				cell.setCellValue(questMng.getQuestNo() + ". " + questMng.getQuestTitle());

				ArrayList answerMngList = (ArrayList) questMng.getAnswerMngList();

				for (int j = 0; j < answerMngList.size(); j++) {
					AnswerMng answerMng = (AnswerMng) answerMngList.get(j);

					row = sheet.createRow(rownum++);
					// row.setHeight((short) 0x165);

					cell = row.createCell((short) 0);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle3);
					cell.setCellValue(j + 1);

					cell = row.createCell((short) 1);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle4);
					cell.setCellValue(answerMng.getPanelId());

					cell = row.createCell((short) 2);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle3);
					cell.setCellValue(answerMng.getMf());

					cell = row.createCell((short) 3);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle3);
					cell.setCellValue(answerMng.getAgeGroup());

					cell = row.createCell((short) 4);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle4);
					cell.setCellValue(answerMng.getEtcAnswer());
				}
			}
		}
		else if (target.equals("R")) {
			filename = URLEncoder.encode("로데이터", "UTF-8");

			// sheet = workbook.createSheet("��Ÿ");
			sheet = workbook.createSheet();
			workbook.setSheetName(0, "로데이터", HSSFWorkbook.ENCODING_UTF_16);

			// @SuppressWarnings("unchecked")
			// PollMng pollMng = (PollMng) map.get("pollMng");

			// row = sheet.createRow(rownum++);
			// row.setHeight((short) 0x165);

			// cell = row.createCell((short) 0);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			// cell.setCellStyle(cellStyle);
			// cell.setCellValue("���� ���� : " + pollMng.getPollTitle());

			// row = sheet.createRow(rownum++);
			// row.setHeight((short) 0x165);

			row = sheet.createRow(rownum++);
			// row.setHeight((short) 0x165);

			ArrayList questMngList = (ArrayList) map.get("questMngList");

			for (int i = 0; i < questMngList.size(); i++) {
				QuestMng questMng = (QuestMng) questMngList.get(i);

				cell = row.createCell((short) i);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle2);
				cell.setCellValue(questMng.getHeader());
			}

			QuestMng questMng = (QuestMng) map.get("questMng");

			String[] cnts = questMng.getCnts().split(",");
			String[] maxs = questMng.getMaxs().split(",");
			String[] choiceCnts = questMng.getChoiceCnts().split(",");

			ArrayList panelStateList = (ArrayList) map.get("panelStateList");

			for (int i = 0; i < panelStateList.size(); i++) {
				PanelState panelState = (PanelState) panelStateList.get(i);
				short column = 0;

				row = sheet.createRow(rownum++);
				// row.setHeight((short) 0x165);

				cell = row.createCell(column++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle3);
				cell.setCellValue(i + 1);

				cell = row.createCell(column++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle4);
				cell.setCellValue(panelState.getPanelId());

				cell = row.createCell(column++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle3);
				cell.setCellValue(panelState.getMf());

				cell = row.createCell(column++);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle3);
				cell.setCellValue(panelState.getAgeGroup());

				String[] answers = panelState.getAnswers().split(",");

				for (int j = 0; j < answers.length; j++) {
					if (Integer.parseInt(choiceCnts[j]) == 0) {
						if (Integer.parseInt(cnts[j]) > 1) {
							for (int k = 0; k < Integer.parseInt(cnts[j]); k++) {
								cell = row.createCell(column++);
								if (!answers[j].equals("")) {
									cell.setEncoding(HSSFCell.ENCODING_UTF_16);
									cell.setCellStyle(cellStyle3);
									cell.setCellValue(answers[j].substring(k, k + 1));
								}
							}
						}
						else {
							cell = row.createCell(column++);
							if (!answers[j].equals("")) {
								cell.setEncoding(HSSFCell.ENCODING_UTF_16);
								cell.setCellStyle(cellStyle3);
								cell.setCellValue(answers[j]);
							}
						}
					}
					else {
						for (int k = 0; k < Integer.parseInt(choiceCnts[j]); k++) {
							cell = row.createCell(column++);
							if (answers[j].indexOf("" + (k + 1)) != -1) {
								cell.setEncoding(HSSFCell.ENCODING_UTF_16);
								cell.setCellStyle(cellStyle3);
								if (answers[j].indexOf("" + (k + 1)) + 1 == Integer.parseInt(cnts[j])
										&& 99 == Integer.parseInt(maxs[j])) {
									cell.setCellValue(99);
								}
								else {
									cell.setCellValue(answers[j].indexOf("" + (k + 1)) + 1);
								}
							}
						}
					}
				}
			}
		}

		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename=" + filename + ".xls");
		// response.setHeader("Content-Transfer-Encoding", "binary");
	}
}
