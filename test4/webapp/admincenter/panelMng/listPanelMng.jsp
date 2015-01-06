<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.*" %>
<%@ page import="anyframe.common.Page" %>
<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.admincenter.service.PanelMngSearchVO" %> 
<%@ page import="seoul.research.domain.Panel" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<%
	PanelMngSearchVO panelMngSearchVO = (PanelMngSearchVO)request.getAttribute("panelMngSearchVO");
	String regDatestart = EmpUtil.null2str(panelMngSearchVO.getSearchRegDatestart());
	String regDateend = EmpUtil.null2str(panelMngSearchVO.getSearchRegDateend());
	String panelStatus = EmpUtil.null2str(panelMngSearchVO.getSearchPanelStatus());
	String condition = EmpUtil.null2str(panelMngSearchVO.getSearchType());
	String keyword = EmpUtil.null2str(panelMngSearchVO.getSearchText());

	Page resultPage = (Page)request.getAttribute("resultPage");
	ArrayList resultList = null;//(ArrayList)resultPage.getList();	
	int pagesize = 0;//resultPage.getPagesize();
	
	if(resultPage != null){
		resultList = (ArrayList)resultPage.getList();	
		pagesize = resultPage.getPagesize();
	}

	NumberFormat nf = NumberFormat.getInstance(); //숫자 컴마 
	nf.setMaximumIntegerDigits(5); //최대5자리

	String role = (String)session.getAttribute("role");
%>
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
<script src="/include/jquery-latest.min.js"></script>
<script src="/include/jquery-ui.js"></script>
<script>
$(function() {
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		weekHeader: 'Wk',
		dateFormat: 'yy-mm-dd',
		firstDay: 0,
		isRTL: false,
		duration: 200,
		showAnim: 'show',
		showMonthAfterYear: true,
		yearSuffix: '년'
	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	
	$("#searchRegDatestart").datepicker({
	    showOtherMonths: true,
	    selectOtherMonths: true,
		showOn: "button",
		buttonImage: "/img/admincenter/icon_month.gif",
		buttonImageOnly: true
	});

  	$("#searchRegDateend").datepicker({
	    showOtherMonths: true,
	    selectOtherMonths: true,
		showOn: "button",
		buttonImage: "/img/admincenter/icon_month.gif",
		buttonImageOnly: true
	});
});
</script>

<script language="JavaScript">
<!--
function fncLogOut() {
	if (confirm("정말 로그아웃하시겠습니까?")) {
		document.listForm.target = "_top";
	    document.listForm.action = "<c:url value='/logout.do' />";
	    document.listForm.submit();	
    }
}

//패널관리
function fncListPanelMng(arg){
	var sDate = document.getElementById("searchRegDatestart").value;
	var eDate = document.getElementById("searchRegDateend").value;

	if(sDate == "" || eDate == ""){
		alert("날짜를 입력해 주세요.");
		return;
	}
	
	if (arg == "1")
		document.listForm.pageIndex.value = 1;
 	document.listForm.action = "<c:url value='/listPanelMng.do' />";
 	document.listForm.submit();		
}

function fncExcelPanelMng() {
	var sDate = document.getElementById("searchRegDatestart").value;
	var eDate = document.getElementById("searchRegDateend").value;

	if (sDate == "" || eDate == "") {
		alert("날짜를 입력해 주세요.");
		return;
	}
	
 	document.listForm.action = "<c:url value='/excelPanelMng.do' />";
 	document.listForm.submit();		
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
<form name="listForm" method="post">

	<div id="wrap">
		<div class="inner_wrap">
		
			<!-- #header -->
			<div id="header">
				<h1><a href="/admincenter/"><img src="/img/admincenter/logo.jpg" alt="서울시 여론조사" /></a></h1>
				<p><a href="javascript:fncLogOut();">로그아웃</a></p>
				<div class="gnb">
					<ul class="clearfix">
					<%
					if (role.equals("GG101") || role.equals("GG102")) {
					%>
						<li><a href="<c:url value='/listSurveyMng.do' />">여론조사관리</a></li>
						<li><a href="<c:url value='/listPanelMng.do' />">패널현황</a></li>
						<li><a href="<c:url value='/listPartMng.do' />">응답자관리</a></li>
						<li><a href="<c:url value='/listApplMng.do' />">조사결과</a></li>
						<li><a href="<c:url value='/listPopupzoneMng.do' />">팝업존관리</a></li>
					<%
					}
					else {
					%>
						<li><a href="#">여론조사관리</a></li>
						<li><a href="#">패널현황</a></li>
						<li><a href="#">응답자관리</a></li>
						<li><a href="<c:url value='/listApplMng.do' />">조사결과</a></li>
						<li><a href="#">팝업존관리</a></li>
					<%
					}
					%>
					</ul>
				</div><!-- .gnb -->			
			</div>
			<!-- // #header -->
			
			<!-- #sidebar -->
			<div id="sidebar">
				<div class="inner">
					<h2>패널현황</h2>
					<ul>
						<li><a href="<c:url value='/listPanelMng.do' />" class="active">패널현황</a></li>
						<li><a href="/admincenter/panelMng/reportPanelMng.jsp">패널통계</a></li>
					</ul>
				</div>
			</div>	
			<!-- // #sidebar -->
			
			<!-- contents -->
			<div id="contents" class="survey">	
				<h2>패널 현황</h2>
				
				<table class="srch_tbl">
					<tr>
						<th>가입일/탈퇴일</th>
						<td>
							<input type="text" name="searchRegDatestart" id="searchRegDatestart" title="등록일" class="ip80" value="<%= regDatestart %>" readonly />
							~ 
							<input type="text" name="searchRegDateend" id="searchRegDateend" title="등록일 뒷자리" class="ip80" value="<%= regDateend %>" readonly />
						</td>
						<th><label for="">상태</label></th>
						<td>
							<select name="searchPanelStatus" id="searchPanelStatus">
								<option value="">전체</option>
								<option value="N" <%= ("N".equals(panelStatus) ? "selected" : "")%>>가입</option>
								<option value="Y" <%= ("Y".equals(panelStatus) ? "selected" : "")%>>탈퇴</option>
							</select>				
						</td>
						<td rowspan="2"><div class="btnareaC"><span class="btn radiusG"><a href="javascript:fncListPanelMng('1');">검색</a></span></div></td>
					</tr>
					<tr>
						<th><label for="">기타</label></th>
						<td colspan="3">
							<select name="searchType" id="searchType">
								<option value="id" <%= ("id".equals(condition) ? "selected" : "")%>>아이디</option>
								<option value="name" <%= ("name".equals(condition) ? "selected" : "")%>>이름</option>
							</select>
							<input type="text" name="searchText" id="searchText" value="<%= keyword %>" />
						</td>
					</tr>
				</table>
				<div class="btnarea">
					<span class="btnareaL total">총 <strong>
<%try{ %>
				
					<%= ( null == nf.format(resultPage.getTotalCount()) ? 0: nf.format(resultPage.getTotalCount()))%></strong>&nbsp;건</span>
					<span class="btn radiusB"><a href="javascript:fncExcelPanelMng();">엑셀</a></span>
				</div>
				<table class="list_tbl">
				<colgroup>
					<col width="40px;" />
					<col width="60px;" />
					<col width="80px;" />
					<col width="*" />
					<col width="80px;" />
					<col width="80px;" />
					<col width="60px;" />
					<col width="60px;" />
					<col width="80px;" />
				</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>이메일</th>
							<th>가입일<br /> (탈퇴일)</th>
							<th>생일</th>
							<th>지역</th>
							<th>성별</th>
							<th>상태</th>
						</tr>
					</thead>
					<tbody>
					<%
						int currentPageIndex = resultPage.getCurrentPage();
						int no = (currentPageIndex - 1) * pagesize;
						
						if (resultList.size() > 0) {
							for (int i = 0; i < resultList.size(); i++) {
								Panel panel = (Panel)resultList.get(i);
					%>
						<tr>
							<td><%= ++no %></td>
							<td><%= panel.getPnlId() %></td>
							<td><%= panel.getPnlName() %></td>
							<td class="tl"><%= panel.getEmail() %></td>
							<%if ("탈퇴".equals(panel.getDelflag())){ %>
							<td><font style="color:red"><%= panel.getRegDate() %></font></td>
							<%}else{ %>
							<td><%= panel.getRegDate() %></td>							
							<%} %>
							<td><%= panel.getBirthday() %></td>
							<td><%= panel.getSido() %></td>
							<td><%= panel.getMf() %></td>
							<td><%= panel.getDelflag() %></td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table><!-- .list_tbl -->
				
				<div class="paginate">
					<anyframe:pagenavigator linkUrl="javascript:fncListPanelMng(2);" 
							             	pages="<%=resultPage%>" 
							             	formName="listForm"
							              	firstImg="${pageContext.request.contextPath}/img/admincenter/btn_prev_more.gif" 
							              	prevImg="${pageContext.request.contextPath}/img/admincenter/btn_prev.gif" 
							              	lastImg="${pageContext.request.contextPath}/img/admincenter/btn_next.gif" 
							              	nextImg="${pageContext.request.contextPath}/img/admincenter/btn_next_more.gif" />
				</div><!-- .paginate -->
				
						<%
							}else {
						%>
						<tr>
							<td colspan="9">조회 결과가 없습니다.</td>
						</tr>
					</tbody>
				</table><!-- .list_tbl -->
						<%
							}
						%>
<!-- selected userId -->
<input type="hidden" name="pollIdx" />
<input type="hidden" name="pageIndex" value="<%= resultPage.getCurrentPage() %>" />
<!-- end of list -->						

<%}catch(Exception ex) {%>

					0</strong>&nbsp;건</span>
					<span class="btn radiusB"><a href="javascript:fncExcelPanelMng();">엑셀</a></span>
				</div>
			<table class="list_tbl">
				<colgroup>
					<col width="40px;" />
					<col width="60px;" />
					<col width="80px;" />
					<col width="*" />
					<col width="80px;" />
					<col width="80px;" />
					<col width="60px;" />
					<col width="60px;" />
					<col width="80px;" />
				</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>이메일</th>
							<th>가입일<br /> (탈퇴일)</th>
							<th>생일</th>
							<th>지역</th>
							<th>성별</th>
							<th>상태</th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<td colspan="9">조회 결과가 없습니다.</td>
						</tr>
					</tbody>
				</table>
<!-- selected userId -->
<input type="hidden" name="pollIdx" />
<input type="hidden" name="pageIndex" value="0" />
<!-- end of list -->		
<%} %>						
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->

</form>
<script language="JavaScript">
document.listForm.searchText.focus();
</script>
</body>
</html>
