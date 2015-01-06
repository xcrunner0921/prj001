<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="anyframe" uri="/WEB-INF/anyframe-message.tld" %>

<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="com.sds.emp.domain.Category" %>
<%@ page import="com.sds.emp.sales.service.CategorySearchVO" %>

<%
	Category category = (Category)request.getAttribute("category");
	CategorySearchVO categorySearchVO = (CategorySearchVO)request.getAttribute("categorySearchVO");
%>

<html>
<head>
<title>Update Category Information</title>
<link rel="stylesheet" href="<c:url value='/sample/css/admin.css'/>" type="text/css">

<script language="javascript" src="<c:url value='/sample/javascript/CommonScript.js'/>"></script>
<script language="JavaScript">
<!--
function fncUpdateCategory() {
	// Form validation
	if(FormValidation(document.detailForm) != false) {		
	    document.detailForm.action="<c:url value='/updateCategory.do'/>";
	    document.detailForm.submit();
	} 
}
function fncDeleteCategory(){
		document.detailForm.searchCondition.value="";
		document.detailForm.searchKeyword.value="";
		document.detailForm.pageIndex.value=1;
	    document.detailForm.action="<c:url value='/deleteCategory.do'/>";
	    document.detailForm.submit();
}

function resetData() {
	document.detailForm.reset();
}
-->
</script>


</head>

<body bgcolor="#ffffff" text="#000000">

<form:form name="detailForm" method="post" enctype="multipart/form-data" commandName="category">
<!--************************** begin of contents *****************************-->

<!-- begin of title -->
<table width="100%" height="37" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="<c:url value='/sample/images/ct_ttl_img01.gif'/>" width="15" height="37"></td>
		<td background="<c:url value='/sample/images/ct_ttl_img02.gif'/>" width="100%" style="padding-left:10px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="93%" class="ct_ttl01"><spring:message code="update.category.title"/></td>
				<td width="20%" align="right">&nbsp;</td>
			</tr>
		</table>
		</td>
		<td width="12" height="37"><img src="<c:url value='/sample/images/ct_ttl_img03.gif'/>" width="12" height="37"></td>
	</tr>
</table>
<!-- end of title -->

<!-- begin of table -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:13px;">
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">Category No. </td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<form:input path="categoryNo" cssClass="ct_input_bg" cssStyle="width:150px; height:19px" readonly="true" maxlength="20" />
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">Category Name <img src="<c:url value='/sample/images/ct_icon_red.gif'/>" width="3" height="3" align="absmiddle"></td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="categoryName" class="ct_input_g" style="width:150px; height:19px" value="${category.categoryName}" required fieldTitle="Category Name" maxLength="50" minLength="2" char="sn">
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">Usable</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<form:select path="useYn" cssClass="ct_input_g" cssStyle="width:80px">
				<option value="Y" <%= ("Y".equals(category.getUseYn()) ? "selected" : "")%>>Yes</option>
				<option value="N" <%= ("N".equals(category.getUseYn()) ? "selected" : "")%>>No</option>				
			</form:select>			
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
	<tr>
		<td width="104" class="ct_write">Description</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<textarea name="categoryDesc" cols="50" rows="5">${category.categoryDesc}</textarea>
		</td>
	</tr>
	<tr>
		<td height="1" colspan="3" bgcolor="D6D6D6"></td>
	</tr>
</table>
<!-- end of table -->

<!-- remain search condition of search UI previously-->
<input type="hidden" name="searchCondition" value="<%= EmpUtil.null2str(categorySearchVO.getSearchCondition()) %>">
<input type="hidden" name="searchKeyword" value="<%= EmpUtil.null2str(categorySearchVO.getSearchKeyword()) %>">
<input type="hidden" name="pageIndex" value="<%= categorySearchVO.getPageIndex() %>">
<input type="hidden" name="searchUseYn" value="<%= categorySearchVO.getSearchUseYn() %>">

<!-- begin of button -->
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	style="margin-top:10px;">
	<tr>
		<td width="53%">
		
		</td>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23"><img src="<c:url value='/sample/images/ct_btnbg01.gif'/>" width="17" height="23"></td>
					<td background="<c:url value='/sample/images/ct_btnbg02.gif'/>" class="ct_btn01" style="padding-top:3px;"><a href="javascript:fncUpdateCategory();">Update</a></td>
					<td width="14" height="23"><img src="<c:url value='/sample/images/ct_btnbg03.gif'/>" width="14" height="23"></td>
					
					<td width="30"></td>
					<td width="17" height="23"><img src="<c:url value='/sample/images/ct_btnbg01.gif'/>" width="17" height="23"></td>
					<td background="<c:url value='/sample/images/ct_btnbg02.gif'/>" class="ct_btn01" style="padding-top:3px;"><a href="javascript:fncDeleteCategory();">Delete</a></td>
					<td width="14" height="23"><img src="<c:url value='/sample/images/ct_btnbg03.gif'/>" width="14" height="23"></td>
										<td width="30"></td>										
					<td width="17" height="23"><img src="<c:url value='/sample/images/ct_btnbg01.gif'/>" width="17" height="23"></td>
					<td background="<c:url value='/sample/images/ct_btnbg02.gif'/>" class="ct_btn01" style="padding-top:3px;"><a href="javascript:resetData();">Cancel</a></td>
					<td width="14" height="23"><img src="<c:url value='/sample/images/ct_btnbg03.gif'/>" width="14" height="23"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- end of button -->

</form:form>

<script language="JavaScript">
document.getElementsByName("categoryName")[0].focus();
</script>

</body>
</html>
