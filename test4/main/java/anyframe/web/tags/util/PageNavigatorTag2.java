package anyframe.web.tags.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import anyframe.common.Page;

public class PageNavigatorTag2 extends TagSupport {
	private String firstImg = "anyframe/web/image/page/page_before1.gif";

	private String prevImg = "anyframe/web/image/page/page_before.gif";

	private String lastImg = "anyframe/web/image/page/page_after1.gif";

	private String nextImg = "anyframe/web/image/page/page_after.gif";

	private String imgHeight = "13";

	private String imgWidth = "15";

	private String numClass = "page";

	private String clickNumClass = "page_s";

	private String formName = "forms[0]";

	private String linkUrl = "";

	private Page pages = Page.EMPTY_PAGE;

	private int currentPage = 1;

	public int doStartTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			String page_num_script = "javascript:document." + getFormName() + ".pageIndex.value=";

			out.print("<table border='0' cellspacing='0' cellpadding='0' style='width: 10%;' align='center'><tr>");
			if (this.pages.hasPreviousPageUnit()) {
				out.print("<td width='20'><a href='" + page_num_script + this.pages.getPageOfPreviousPageUnit() + ";"
						+ this.linkUrl + "'>");

				out.print("<img src='" + this.firstImg + "' width='" + this.imgWidth + "' height='" + this.imgHeight
						+ "' border='0' alt='조회목록 처음으로 가기' /></a></td>");
			}
			else {
				out.print("<td width='20'><img disabled src='" + this.firstImg + "' width='" + this.imgWidth + "' height='"
						+ this.imgHeight + "' border='0' alt='조회목록 처음으로 가기' /></td>");
			}

			// out.print("<td width='3'></td>");

			if (this.pages.hasPreviousPage()) {
				out.print("<td width='28'><a href='" + page_num_script + this.pages.getPreviousPage() + ";" + this.linkUrl
						+ "'>");

				out.print("<img src='" + this.prevImg + "' border='0' alt='조회목록 앞으로 가기' /></a></td>");
			}
			else {
				out.print("<td width='28'><img src='" + this.prevImg + "' border='0' alt='조회목록 앞으로 가기' /></td>");
			}

			// out.print("<td width='3'></td>");

			if (this.pages.isEmptyPage()) {
				out.print("<td class='" + this.numClass + "'>1</td>");
			}
			else {
				out.print("<td class='" + this.numClass + "'>");
				for (int i = this.pages.getBeginUnitPage(); i <= this.pages.getEndListPage(); i++) {
					if (i == this.pages.getCurrentPage()) {
						out.print("&nbsp;<font class='" + this.clickNumClass + "'>" + i + "</font>" + "&nbsp;");
					}
					else {
						out.print("&nbsp;<a href='" + page_num_script + i + ";" + this.linkUrl + "'>");

						out.print(i + "</a>&nbsp;");
					}
				}
				out.print("</td>");
			}

			// out.print("<td width='3'></td>");

			if (this.pages.hasNextPage()) {
				out.print("<td width='28'><a href='" + page_num_script + this.pages.getNextPage() + ";" + this.linkUrl + "'>");

				out.print("<img src='" + this.nextImg + "' border='0' alt='조회목록 뒤로 가기' /></a></td>");
			}
			else {
				out.print("<td width='28'><img src='" + this.nextImg + "' border='0' alt='조회목록 뒤로 가기' /></td>");
			}

			// out.print("<td width='3'></td>");

			if (this.pages.hasNextPageUnit()) {
				out.print("<td width='20'><a href='" + page_num_script + this.pages.getPageOfNextPageUnit() + ";"
						+ this.linkUrl + "'>");

				out.print("<img src='" + this.lastImg + "' width='" + this.imgWidth + "' height='" + this.imgHeight
						+ "' border='0' alt='조회목록 마지막으로 가기' /></a></td>");
			}
			else {
				out.print("<td width='20'><img src='" + this.lastImg + "' width='" + this.imgWidth + "' height='"
						+ this.imgHeight + "' border='0' alt='조회목록 마지막으로 가기' /></td>");
			}

			out.print("</tr></table>");
		}
		catch (Exception ex) {
			throw new JspTagException(ex.getMessage());
		}
		return 0;
	}

	public String getClickNumClass() {
		return this.clickNumClass;
	}

	public String getFirstImg() {
		return this.firstImg;
	}

	public String getImgHeight() {
		return this.imgHeight;
	}

	public String getImgWidth() {
		return this.imgWidth;
	}

	public String getLastImg() {
		return this.lastImg;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public String getNextImg() {
		return this.nextImg;
	}

	public String getNumClass() {
		return this.numClass;
	}

	public String getPrevImg() {
		return this.prevImg;
	}

	public void setClickNumClass(String string) {
		this.clickNumClass = string;
	}

	public void setFirstImg(String string) {
		this.firstImg = string;
	}

	public void setImgHeight(String string) {
		this.imgHeight = string;
	}

	public void setImgWidth(String string) {
		this.imgWidth = string;
	}

	public void setLastImg(String string) {
		this.lastImg = string;
	}

	public void setLinkUrl(String string) {
		this.linkUrl = string;
	}

	public void setNextImg(String string) {
		this.nextImg = string;
	}

	public void setNumClass(String string) {
		this.numClass = string;
	}

	public void setPrevImg(String string) {
		this.prevImg = string;
	}

	public Page getPages() {
		return this.pages;
	}

	public void setCurrentPage(int i) {
		this.currentPage = i;
	}

	public void setPages(Page page) {
		this.pages = page;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public String getFormName() {
		return this.formName;
	}

	public void setFormName(String string) {
		this.formName = string;
	}
}
