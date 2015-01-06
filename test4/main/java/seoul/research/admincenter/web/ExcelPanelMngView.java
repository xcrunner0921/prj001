package seoul.research.admincenter.web;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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

import seoul.research.domain.Panel;

public class ExcelPanelMngView extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map map, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		// String target = map.get("target").toString();

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
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font2 = workbook.createFont();
		font2.setFontHeightInPoints((short) 15);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle2.setFont(font2);

		HSSFCellStyle cellStyle3 = workbook.createCellStyle();
		cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle cellStyle4 = workbook.createCellStyle();
		cellStyle4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		String filename = null;
		int rownum = 0;

		filename = URLEncoder.encode("패널현황", "UTF-8");

		// sheet = workbook.createSheet("��Ÿ");
		sheet = workbook.createSheet();
		workbook.setSheetName(0, "패널현황", HSSFWorkbook.ENCODING_UTF_16);

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

		cell = row.createCell((short) 0);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle3);
		cell.setCellValue("번호");

		cell = row.createCell((short) 1);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle3);
		cell.setCellValue("아이디");

		cell = row.createCell((short) 2);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle3);
		cell.setCellValue("이름");

		cell = row.createCell((short) 3);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle3);
		cell.setCellValue("이메일");

		cell = row.createCell((short) 4);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle3);
		cell.setCellValue("가입일 (탈퇴일)");

		cell = row.createCell((short) 5);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle3);
		cell.setCellValue("생일");

		cell = row.createCell((short) 6);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle3);
		cell.setCellValue("지역");

		cell = row.createCell((short) 7);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle3);
		cell.setCellValue("성별");

		cell = row.createCell((short) 8);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellStyle(cellStyle3);
		cell.setCellValue("상태");

		ArrayList panelList = (ArrayList) map.get("panelList");

		for (int i = 0; i < panelList.size(); i++) {
			Panel panel = (Panel) panelList.get(i);

			row = sheet.createRow(rownum++);
			// row.setHeight((short) 0x165);

			cell = row.createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle3);
			cell.setCellValue(i + 1);

			cell = row.createCell((short) 1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle3);
			cell.setCellValue(panel.getPnlId());

			cell = row.createCell((short) 2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle3);
			cell.setCellValue(panel.getPnlName());

			cell = row.createCell((short) 3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle4);
			cell.setCellValue(panel.getEmail());

			cell = row.createCell((short) 4);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle3);
			// cell.setCellValue(panel.getRegDate());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String regDate = sdf.format(panel.getRegDate());
			cell.setCellValue(regDate);

			cell = row.createCell((short) 5);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle3);
			cell.setCellValue(panel.getBirthday());

			cell = row.createCell((short) 6);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle3);
			cell.setCellValue(panel.getSido());

			cell = row.createCell((short) 7);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle3);
			cell.setCellValue(panel.getMf());

			cell = row.createCell((short) 8);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellStyle(cellStyle3);
			cell.setCellValue(panel.getDelflag());
		}

		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename=" + filename + ".xls");
		// response.setHeader("Content-Transfer-Encoding", "binary");
	}
}
