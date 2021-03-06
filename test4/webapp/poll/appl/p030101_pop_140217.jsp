<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.*" %>
<%@ page import="com.sds.emp.common.EmpUtil" %>
<%@ page import="seoul.research.poll.service.SurveyResultSearchVO" %>
<%@ page import="seoul.research.domain.SurveyResult" %>
<%@ page import="seoul.research.domain.PollAddfile" %>
<%@ page import="seoul.research.domain.PollMng" %>

<%
	SurveyResult surveyResult = (SurveyResult)request.getAttribute("surveyResult");

	ArrayList pollAddfileList = (ArrayList)request.getAttribute("pollAddfileList");
	ArrayList pollAddfile2List = (ArrayList)request.getAttribute("pollAddfile2List");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>여론조사 결과조회</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/content.css"/>
</head>
<body>
<div id="pop_wrap">
    <div class="pop_head">
        <strong><img src="/img/poll/tit_popSurveyDetail.png" alt="여론조사결과" /></strong>
    </div>
    <div class="pop_content">
        <table class="tbl_view" summary="">
            <caption></caption>
            <colgroup>
                <col width="18%" />
                <col width="" />
            </colgroup>
            <tr>
                <th scope="row">조사제목</th>
                <td class="noLine"><%= surveyResult.getPollTitle() %></td>
            </tr>
            <tr>
                <th scope="row">조사기간</th>
                <td class="noLine"><%= surveyResult.getPollTermstart() %> &nbsp;~&nbsp; <%= surveyResult.getPollTermend() %></td>
            </tr>
            <tr>
                <th scope="row">담당부서</th>
                <td class="noLine"><%= surveyResult.getPollAskName().replaceAll(", ", "<br />") %> </td>
            </tr>
            <tr>
                <th scope="row">조사부서</th>
                <td class="noLine"><%= surveyResult.getPollDepartment() %> </td>
            </tr>
            <tr>
                <th scope="row">조사내용</th>
                <td class="noLine"><%= surveyResult.getPollContents().replaceAll("\n", "<br />") %> </td>
            </tr>
            <tr>
                <th scope="row">표&nbsp;본&nbsp;수</th>
                <td class="noLine"><%= surveyResult.getPollSample() %> &nbsp;명</td>
            </tr>
            <!-- 2013.10.24. 뉴미디어과 의견 결정이 필요하여 숨김 처리
            <tr>
                <th scope="row">기&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;타</th>
                <td class="noLine"></td>
            </tr>
            <tr>
                <th scope="row">결과보기</th>
                <td class="noLine"></td>
            </tr>
            -->
            <tr>
                <th scope="row">서울도서관 <br /> 원문DB조회</th>
                <td class="noLine"><br />
				<%
				if (pollAddfile2List.size() > 0) {
					for (int i = 0; i < pollAddfile2List.size(); i++) {						
						PollAddfile pollAddfile = (PollAddfile)pollAddfile2List.get(i);
				%>
					<a href="<%= pollAddfile.getSavFname() %>" target="_blank">결과보고서 원문<%= (i+1) %></a><br />
				<%
					}
				}
				%>	
				<br /><em class="emph">※&nbsp;전자원문은 서울도서관(http://lib.seoul.go.kr)에서 제공합니다.
				<br />조사에 상세문의는 위 담당부서에서 안내받으실 수 있습니다.</em>
                </td>
            </tr>
        </table>
        <a href="javascript:window.close();" class="btn_close"><img src="/img/poll/btn_close02.gif" alt="닫기" /></a>
    </div>
</div>
</body>
</html>