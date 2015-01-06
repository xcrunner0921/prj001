<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sds.emp.domain.Code" %>
<%@ page import="seoul.research.domain.PollMng" %>
<%@ page import="seoul.research.domain.QuestMng" %>
<%@ page import="seoul.research.domain.ExampleMng" %>
<%@ page import="seoul.research.domain.ImgMng" %>
<%@ page import="seoul.research.domain.QuestAddfile" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<title>서울시 여론조사 시스템 관리자 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/commonAdmin.css"  media="screen" />
<link rel="stylesheet" type="text/css" href="/css/contentAdmin.css"  media="screen" />
<%
	PollMng pollMng = (PollMng)request.getAttribute("pollMng");

	ArrayList questMngList = (ArrayList)request.getAttribute("questMngList");

	String role = (String)session.getAttribute("role");
%>
<script language="javascript" src="<c:url value='/sample/javascript/CommonScript.js'/>"></script>
<script language="JavaScript">
<!--
function fncLogOut() {
	if (confirm("정말 로그아웃하시겠습니까?")) {
		document.detailForm.target = "_top";
	    document.detailForm.action = "<c:url value='/logout.do' />";
	    document.detailForm.submit();	
    }
}

function fncUpdateQuestMngView(questNo) {
	document.detailForm.questNo.value = questNo;
   	document.detailForm.action = "<c:url value='/updateQuestMngView.do' />";
   	document.detailForm.submit();
}

function fncDeleteQuestMng(questNo) {
	if (document.detailForm.branchedYn.value == "Y") {
		alert("분기되는 문항은 삭제할 수 없습니다.");
		return;
	}
		
	if (confirm("문항을 삭제하시겠습니까?")) {
		document.detailForm.questNo.value = questNo;
		document.detailForm.action = "<c:url value='/deleteQuestMng.do' />";
	    document.detailForm.submit();
	}
}

function fncAddQuestMngView(pollIdx) {
	document.detailForm.pollIdx.value = pollIdx;
    document.detailForm.action = "<c:url value='/addQuestMngView.do' />";
    document.detailForm.submit();
}

function fncSetPollQuestStatus(pollQuestStatus) {
	document.detailForm.pollQuestStatus.value = pollQuestStatus;
    document.detailForm.action = "<c:url value='/setPollQuestStatus.do' />";
    document.detailForm.submit();
}

function fncListQuestMng() {
	document.detailForm.action = "<c:url value='/listQuestMng.do' />";
    document.detailForm.submit();
}
-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">
<form name="detailForm" method="post">

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
					<h2>여론조사관리</h2>
					<ul>
						<li><a href="<c:url value='/listSurveyMng.do' />">여론조사관리</a></li>
						<li><a href="<c:url value='/listQuestMng.do' />" class="active">문항관리</a></li>
						<li><a href="<c:url value='/listAnswerMng.do' />">응답현황</a></li>
					</ul>
				</div>
			</div>	
			<!-- // #sidebar -->
			
			<!-- contents -->
			<div id="contents" class="querst">	
				<h2>문항 상세조회</h2>
				
				<h3>제목 : <%= pollMng.getPollTitle() %></h3>
				
				<div class="detail">
				<%
					for (int i = 0; i < questMngList.size(); i++) {
						QuestMng questMng = (QuestMng)questMngList.get(i);
				%>
				<%
						ArrayList questAddfileList = (ArrayList)questMng.getQuestAddfileList();
				
						for (int j = 0; j < questAddfileList.size(); j++) {
							QuestAddfile questAddfile = (QuestAddfile)questAddfileList.get(j);
							
							if (questAddfile.getPosition().equals("U")) {
				%>
					<div style="margin-bottom: 10px;">
						<p>
							<img src="<%= questAddfile.getFilePath() %><%= questAddfile.getSavFname() %>" alt="" />
						</p>
					</div>
				<%
								break;
							}
						}
				%>							
					<div class="inner">
						<p>
							<span class="num"><%= i + 1 %>. </span><%= questMng.getQuestTitle() %>
				<%
						if (pollMng.getPollQuestStatus().equals("N")) {
				%>
					<%
					if (role.equals("GG101") || role.equals("GG102")) {
					%>
							<span class="btn lightG"><a href="javascript:fncUpdateQuestMngView('<%= questMng.getQuestNo() %>');">수정</a></span>
					<%
					}
					%>
				<%
							if (i == questMngList.size() - 1) {
				%>
					<%
					if (role.equals("GG101") || role.equals("GG102")) {
					%>
							<span class="btn lightB"><a href="javascript:fncDeleteQuestMng('<%= questMng.getQuestNo() %>');">삭제</a></span>
					<%
					}
					%>
							<input type="hidden" name="branchedYn" value="<%= questMng.getBranchedYn() %>" />
				<%
							}
						}
				%>
						</p>
				<%
						for (int j = 0; j < questAddfileList.size(); j++) {
							QuestAddfile questAddfile = (QuestAddfile)questAddfileList.get(j);
							
							if (questAddfile.getPosition().equals("D")) {
				%>
						<p>
							<img src="<%= questAddfile.getFilePath() %><%= questAddfile.getSavFname() %>" alt="" />
						</p>
				<%
								break;
							}
						}
				
						if (questMng.getQuestType().equals("HH101")) {
							if (questMng.getExamType().equals("II101")) {
				%>
						<table class="noborder">
							<colgroup>
								<col width="16px" />
								<col width="*" />
							</colgroup>
				<%
							}
							else if (questMng.getExamType().equals("II102")) {
				%>
						<div class="img_wrap">
				<%
							}
						}
						else if (questMng.getQuestType().equals("HH102") || questMng.getQuestType().equals("HH104")) {
							if (questMng.getExamType().equals("II101")) {
				%>
						<table class="noborder">
							<colgroup>
								<col width="18px" />
								<col width="*" />
							</colgroup>
				<%
							}
							else if (questMng.getExamType().equals("II102")) {
				%>
						<div class="img_wrap">
				<%
							}
						}
				
						ArrayList exampleMngList = (ArrayList)questMng.getExampleMngList();
						ArrayList imgMngList = (ArrayList)questMng.getImgMngList();
				
						for (int j = 0; j < exampleMngList.size(); j++) {
							ExampleMng exampleMng = (ExampleMng)exampleMngList.get(j);

							if (questMng.getQuestType().equals("HH101")) {
								if (questMng.getExamType().equals("II101")) {
				%>
							<tr>
								<td><input type="radio" name="" id="" /></td>
								<td><label for=""><%= exampleMng.getExamTitle() %>
				<%
									if (exampleMng.getExamNo() == 99) {
				%>
									<input type="text" name="" id="" class="ip60" readonly />		
				<%
									}
									
									if (questMng.getBranchYn().equals("Y")) {
				%>
									<span class="red">(분기:<%= exampleMng.getBranchNo() %>)</span>
				<%
									}
				%>
								</label></td>
							</tr>
				<%
								}
								else if (questMng.getExamType().equals("II102")) {
									ImgMng imgMng = (ImgMng)imgMngList.get(j);
				%>
							<label for=""><input type="radio" name="" id="" /><%= imgMng.getFileDesc() %>
				<%
									if (questMng.getBranchYn().equals("Y")) {
				%>
								<span class="red">(분기:<%= exampleMng.getBranchNo() %>)</span>
				<%
									}
				%>
							</label>
							<img src="<%= imgMng.getFilePath() %><%= imgMng.getSavFname() %>" alt="" />
				<%
								}
							}
							else if (questMng.getQuestType().equals("HH102") || questMng.getQuestType().equals("HH104")) {
								if (questMng.getExamType().equals("II101")) {
				%>
							<tr>
								<td><input type="checkbox" name="" id="" /></td>
								<td><label for=""><%= exampleMng.getExamTitle() %>
				<%
									if (exampleMng.getExamNo() == 99) {
				%>
									<input type="text" name="" id="" class="ip60" readonly />		
				<%
									}
				%>
								</label></td>
							</tr>
				<%
								}
								else if (questMng.getExamType().equals("II102")) {
									ImgMng imgMng = (ImgMng)imgMngList.get(j);
				%>
							<label for=""><input type="checkbox" name="" id="" /><%= imgMng.getFileDesc() %></label>
							<img src="<%= imgMng.getFilePath() %><%= imgMng.getSavFname() %>" alt="" />
				<%
								}
							}
						}
						
						if (questMng.getQuestType().equals("HH101")) {
							if (questMng.getExamType().equals("II101")) {
				%>
						</table>
				<%
							}
							else if (questMng.getExamType().equals("II102")) {
				%>
						</div>
				<%
							}
						}
						else if (questMng.getQuestType().equals("HH102") || questMng.getQuestType().equals("HH104")) {
							if (questMng.getExamType().equals("II101")) {
				%>
						</table>
				<%
							}
							else if (questMng.getExamType().equals("II102")) {
				%>
						</div>
				<%
							}
						}
						
						if (questMng.getQuestType().equals("HH103")) {
				%>
						<div class="img_wrap">
							<textarea name="" id="" class="ip100" cols="30" rows="10" readonly></textarea>
						</div>
				<%
						}
				%>
					</div><!--  .inner-->
				<%
					}
				%>
				</div><!-- .detail -->

				<div class="btnareaC">
				<%
					if (pollMng.getPollQuestStatus().equals("N")) {
				%>
					<span class="btn radiusB"><a href="javascript:fncAddQuestMngView('<%= pollMng.getPollIdx() %>');">문항 추가</a></span>
					<span class="btn radiusB"><a href="javascript:fncSetPollQuestStatus('Y');">완료</a></span>
				<%
					}
				%>
					<span class="btn radiusB"><a href="javascript:fncListQuestMng();">목록</a></span>
				</div>
				
			</div>
			<!-- // contents -->
			
		</div>
		<!-- // .inner_wrap -->
	</div>
	<!-- // #wrap -->

<!-- selected userId -->
<input type="hidden" name="pollIdx" value="<%= pollMng.getPollIdx() %>">
<input type="hidden" name="questNo" value="">

<input type="hidden" name="branchYn" value="N" />
<input type="hidden" name="examRotateyn" value="Y" />
<input type="hidden" name="filterYn" value="N" />
<input type="hidden" name="etcUseYn" value="N" />
<input type="hidden" name="etcUseYn2" value="N" />
<input type="hidden" name="etcUseYn4" value="N" />
<input type="hidden" name="pollQuestStatus" value="N">
<!-- end of list -->

</form>

<script language="JavaScript">
// document.getElementsByName("questMngName")[0].focus();
</script>

</body>
</html>
