<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri='/WEB-INF/anyframe-page.tld' prefix='anyframe' %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="anyframe.common.Page" %>
<%@ page import="com.sds.emp.common.*" %>
<%@ page import="java.text.*" %>
<%@ page import="seoul.research.domain.PopupMng" %>
<%@ page import="seoul.research.admincenter.service.PopupzoneMngSearchVO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>

<%
	ArrayList PorderList = (ArrayList)request.getAttribute("PorderList");
	Page resultPage = (Page)request.getAttribute("resultPage");
	ArrayList resultList = (ArrayList)resultPage.getList();	
	int pagesize = resultPage.getPagesize();
	
	PopupzoneMngSearchVO popupzoneMngSearchVO = (PopupzoneMngSearchVO)request.getAttribute("popupzoneMngSearchVO");
	String keyword = EmpUtil.null2str(popupzoneMngSearchVO.getSearchTitle());

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String today = sdf.format(date);	

	String role = (String)session.getAttribute("role");
%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사 시스템 관리자 페이지</title>
<link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
<script src="/include/jquery-latest.min.js"></script>
<script src="/include/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<script>
</script>

<script language="javascript" src="<c:url value='/sample/javascript/CommonScript.js'/>"></script>
<script language="JavaScript">
function fncLogOut() {
	if (confirm("정말 로그아웃하시겠습니까?")) {
		document.frm.target = "_top";
	    document.frm.action = "<c:url value='/logout.do' />";
	    document.frm.submit();	
    }
}

//검색
function fncPopupzoneMngList(arg) {
	if(arg == "1"){
		document.frm.pageIndex.value = 1;
	}
   	document.frm.action="<c:url value='/listPopupzoneMng.do'/>";
   	document.frm.submit();		
}

function detail(str){
	$("#SNo").val(str);
	document.frm.action="<c:url value='/detailPopupzoneMng.do'/>";
   	document.frm.submit();	
}
</script>
</head>

<body>
<form id="frm" name="frm">
<input type="hidden" name="pageIndex" value="<%=resultPage.getCurrentPage()%>" />
<input type="hidden" name="SNo" id="SNo" value="" />
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
					<h2>팝업존관리</h2>
					<ul>
						<li><a href="<c:url value='/listPopupzoneMng.do' />" class="active">팝업존관리</a></li>
					</ul>
				</div>
			</div>	
			<!-- // #sidebar -->
			
			<!-- contents -->
			<div id="contents" class="survey">	
				<h2>팝업존 현황</h2>
				
				<table class="srch_tbl">
					<colgroup>
						<col width="50px;" />
						<col width="*" />
						<col width="80px;" />
					</colgroup>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="searchTitle" id="searchTitle" title="제목" class="ip100" value="<%= keyword %>" />
						</td>
						<td rowspan="2"><div class="btnareaC"><span class="btn radiusG"><a href="javascript:fncPopupzoneMngList('1');">검색</a></span></div></td>
					</tr>
				</table><!-- .srch_tbl -->				
					
				<div class="btnarea">
					<span class="btnareaL total">총 <strong><%= resultPage.getTotalCount() %></strong>건</span>
					<%
					if (role.equals("GG101") || role.equals("GG102")) {
					%>
					<span class="btn radiusB"><a href="<c:url value='/addPopupzoneMngView.do' />">등록</a></span>
					<%
					}
					%>
				</div>
				
				<table class="list_tbl">
					<colgroup>
						<col width="40px;" />
						<col width="*" />
						<col width="120px;" />
						<col width="40px;" />
						<col width="180px;" />
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>기간</th>
							<th>순위</th>
							<th>링크주소</th>
						</tr>
					</thead>
					<tbody>
					<%
						int currentPageIndex = resultPage.getCurrentPage();
						int no = (currentPageIndex - 1) * pagesize;
						
						if (resultList.size() > 0) {
							for (int i = 0; i < resultList.size(); i++) {
								PopupMng popupMng = (PopupMng)resultList.get(i);
					%>						
						<tr>
							<td><%= ++no %></td>
							<td class="tl"><a href="javascript:detail('<%= popupMng.getSNo()%>');"><%= popupMng.getTitle()%></a></td>
							<td class="tl"><%= popupMng.getStrDateYmd()%>&nbsp;<%= popupMng.getStrDateHour()%>:<%= popupMng.getStrDateMinute()%> ~ <br/><%= popupMng.getEndDateYmd()%>&nbsp;<%= popupMng.getEndDateHour()%>:<%= popupMng.getEndDateMinute()%></td>
							<td><%= popupMng.getPorder()%></td>
							<td class="tl"><a href="<%= popupMng.getLinkUrl()%>" target="_blank"><%= popupMng.getLinkUrl()%></a></td>
						</tr>
						<%
								}
						%>
						
					</tbody>
				</table><!-- .list_tbl -->
				
				<div class="paginate">
					<anyframe:pagenavigator linkUrl="javascript:fncPopupzoneMngList(2);" 
							             	pages="<%=resultPage%>" 
							             	formName="frm"
							              	firstImg="${pageContext.request.contextPath}/img/admincenter/btn_prev_more.gif" 
							              	prevImg="${pageContext.request.contextPath}/img/admincenter/btn_prev.gif" 
							              	lastImg="${pageContext.request.contextPath}/img/admincenter/btn_next.gif" 
							              	nextImg="${pageContext.request.contextPath}/img/admincenter/btn_next_more.gif" />
				</div><!-- .paginate -->
				
						<%
							}
							else {
						%>
						<tr>
							<td colspan="5">조회 결과가 없습니다.</td>
						</tr>
					</tbody>
				</table><!-- .list_tbl -->
						<%
							}
						%>
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->
</form>
</body>
</html>
