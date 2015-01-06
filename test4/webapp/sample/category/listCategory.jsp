<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.String" %>
<%@ page import="anyframe.common.Page" %>
<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="com.sds.emp.domain.Category" %>
<%@ page import="com.sds.emp.sales.service.CategorySearchVO" %> 
<html>
<head>
<title>Search List of Category</title>
<link rel="stylesheet" href="<c:url value='/sample/css/admin.css'/>" type="text/css">

<%
	Page resultPage = (Page)request.getAttribute("page");
	ArrayList resultList = (ArrayList)resultPage.getList();	
	
	CategorySearchVO categorySearchVO = (CategorySearchVO)request.getAttribute("categorySearchVO");
	String keyword = EmpUtil.null2str(categorySearchVO.getSearchKeyword());
	String condition = EmpUtil.null2str(categorySearchVO.getSearchCondition());
	String useYn = categorySearchVO.getSearchUseYn();
	
	int pageSize = resultPage.getPagesize();
%>

<script language="JavaScript">
<!--
function fncGetCategoryList(arg) {
	if(arg == "1")
		document.listForm.pageIndex.value = 1;
   	document.listForm.action="<c:url value='/listCategory.do'/>";
   	document.listForm.submit();		
}

function fncGetCategory(categoryNo) {
	document.listForm.categoryNo.value = categoryNo;
   	document.listForm.action="<c:url value='/getCategory.do'/>";
   	document.listForm.submit();
}

function fncAddCategoryView() {
    document.listForm.action="<c:url value='/addCategoryView.do'/>";
    document.listForm.submit();
}
-->
</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<form name="listForm" method="post" action="<c:url value='/listCategory.do'/>">

<!--************************** begin of contents *****************************-->

<!-- begin of title-->
<table width="100%" height="37" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="<c:url value='/sample/images/ct_ttl_img01.gif'/>" width="15" height="37"></td>
		<td background="<c:url value='/sample/images/ct_ttl_img02.gif'/>" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">Search List of Category</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="<c:url value='/sample/images/ct_ttl_img03.gif'/>" width="12" height="37"></td>
	</tr>
</table>
<!-- end of title -->

<!-- begin of search -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:100px">
				<option value="0" <%= (condition.equals("0") ? "selected" : "")%>>Category No.</option>
				<option value="1" <%= (condition.equals("1") ? "selected" : "")%>>Category Name</option>
			</select>
			<input type="text" name="searchKeyword" value="<%= keyword %>" class="ct_input_g" style="width:150px; height:19px" required fieldTitle="Keyword" maxLength="50">
		</td>
		<td align="right" width="150">
			Usable
			<select name="searchUseYn" class="ct_input_g" style="width:80px">
				<option value="A">전체</option>
				<option value="Y" <%= ("Y".equals(useYn) ? "selected" : "")%>>Yes</option>
				<option value="N" <%= ("N".equals(useYn) ? "selected" : "")%>>No</option>				
			</select>	
			&nbsp;&nbsp;
		</td>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23"><img src="<c:url value='/sample/images/ct_btnbg01.gif'/>" width="17" height="23"></td>
					<td background="<c:url value='/sample/images/ct_btnbg02.gif'/>" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetCategoryList('1');">Search</a>
					</td>
					<td width="14" height="23"><img src="<c:url value='/sample/images/ct_btnbg03.gif'/>" width="14" height="23"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- end of search -->

<!-- begin of list-->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td class="ct_list_b" width="50">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">Category No.</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">Category Name</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">Usable</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">Registered Date</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<% 	int currentPageIndex = resultPage.getCurrentPage();
		int no = (currentPageIndex - 1) * pageSize;
		
		for(int i=0; i<resultList.size(); i++) {
			Category category = (Category)resultList.get(i);
	%>
	<tr class="ct_list_pop">
		<td align="center"><%=++no%></td>
		<td></td>
		<td align="left"><a href="javascript:fncGetCategory('<%= category.getCategoryNo() %>');"><%= category.getCategoryNo() %></a></td>
		<td></td>
		<td align="left"><%= category.getCategoryName() %></td>
		<td></td>
		<td align="center" style="padding-right:3px;"><%= category.getUseYn() %></td>
		<td></td>
		<td align="center"><%= EmpUtil.toDateStr(category.getRegDate()) %></td> 
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% } %>
</table>
<!-- selected userId -->
<input type="hidden" name="categoryNo" >
<!-- end of list -->

<!--  begin of page Navigator -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	style="margin-top:10px;">
	<tr>
		<td align="center">
             <input type="hidden" name="pageIndex" value="<%=resultPage.getCurrentPage()%>">
             <anyframe:pagenavigator linkUrl="javascript:fncGetCategoryList(2);" 
             	pages="<%=resultPage%>" 
             	formName="listForm"
              firstImg="${pageContext.request.contextPath}/sample/images/ct_btn_pre.gif" 
              prevImg="${pageContext.request.contextPath}/sample/images/ct_btn_pre01.gif" 
              lastImg="${pageContext.request.contextPath}/sample/images/ct_btn_next.gif" 
              nextImg="${pageContext.request.contextPath}/sample/images/ct_btn_next01.gif" />				
    	</td>
	</tr>
</table>
<!--  end of page Navigator -->
<!-- begin of button -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	style="margin-top:10px;">
	<tr>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23"><img src="<c:url value='/sample/images/ct_btnbg01.gif'/>" width="17" height="23"></td>
					<td background="<c:url value='/sample/images/ct_btnbg02.gif'/>" class="ct_btn01" style="padding-top:3px;"><a href="javascript:fncAddCategoryView();">Add</a></td>
					<td width="14" height="23"><img src="<c:url value='/sample/images/ct_btnbg03.gif'/>" width="14" height="23"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- end of button -->

</form>
<script language="JavaScript">
document.listForm.searchKeyword.focus();
</script>
</body>
</html>
