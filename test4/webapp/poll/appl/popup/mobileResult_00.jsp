<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="seoul.research.poll.service.MobileResultSearchVO" %>
<%@ page import="seoul.research.domain.MobileResult" %>
<%@ page import="com.sds.emp.common.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/surveyTemplate.css"/>
<%
	MobileResult mobileResultTitle = (MobileResult)request.getAttribute("mobileResultTitle");
	ArrayList mobileResultList = (ArrayList)request.getAttribute("mobileResultList");
%>
<script type="text/javascript" src="/include/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
		if( navigator.userAgent.match(/Android/i)
		|| navigator.userAgent.match(/webOS/i)
		|| navigator.userAgent.match(/iPhone/i)
		|| navigator.userAgent.match(/iPad/i)
		|| navigator.userAgent.match(/iPod/i)
		|| navigator.userAgent.match(/BlackBerry/i)
		|| navigator.userAgent.match(/Windows Phone/i) 
		){
		//	alert("app"); 	//app
		}else {
		//	alert("web");	//web
		}
});
//마우스 오른 쪽 금지
document.oncontextmenu = function() { return false; }
</script>

</head>
<body>

	<div class="txtWrap brBot">
            <ul class="surveyInfo">
            
	<table cellpadding="0" cellspacing="0" summary="지난투표결과 내용입니다." border="1" >
		<caption>지난투표결과 내용입니다.</caption>
		<colgroup>			
			<col width="100" />
			<col width="*" /> 
		</colgroup>
		
		<thead>
			<tr>
				<th style="width:100px;" scope="row">설문기간</th>
				<td><%= mobileResultTitle.getPollTermstart() %> ~ <%= mobileResultTitle.getPollTermend() %> 까지 시행</td>
			</tr>
			<tr>
				<th scope="row">투표안건</th>
				<td><span style="font-weight:bold;"><%= mobileResultTitle.getPollTitle().replaceAll("<", "&lt;").replaceAll("&", "&amp;") %></span></td>
			</tr>
			<tr>
				<th scope="row">내용</th>
				<td><%= mobileResultTitle.getPollContents().replaceAll("<", "&lt;").replaceAll("&", "&amp;") %></td>
			</tr>
			<tr>
				<th scope="row">누적 투표자 수 </th>
				<td><%= mobileResultTitle.getCnt() %>명</td>
			</tr>
		</thead>
		
		<tbody>
		<tr>
			<td colspan="2">
			
			<%
			String ExamNo 		= "";
			String QuestType 	= "";
			String QuestTitle	= "";
			String ImgUrl		= "";
			
			int CntHH101,TotalHH101,Avge,AvgeGraph,CntHH104,Avge4;
			int Cnt,Total,Avge2,Avge2Graph,Avge4Graph;
			
			for (int i = 0; i < mobileResultList.size(); i++){
				MobileResult mobileResult = (MobileResult)mobileResultList.get(i);
				
				ExamNo 		= Integer.toString(mobileResult.getExamNo());
				QuestType 	= mobileResult.getQuestType();
				QuestTitle	= mobileResult.getQuestTitle();
				QuestTitle  = QuestTitle.replaceAll("&", "&amp;");
				QuestTitle	= QuestTitle.replaceAll("<", "&lt;");
				
			%>
			<%
				if("0".equals(ExamNo)){
			%>
				<br />
				<% if("HH103".equals(QuestType)){ %>
				<h4><%= mobileResult.getQuestNo() %>. <%= QuestTitle %> (주관식 문제입니다.)</h4>
				<% }else{%>
				<h4><%= mobileResult.getQuestNo() %>. <%= QuestTitle %></h4>
				<% } %>
			<%
				}else{
			%> 
				<%
					if("HH101".equals(QuestType)){
						CntHH101	=	mobileResult.getCntHh101();
						TotalHH101	=	mobileResult.getTotalHh101();
						Avge		=	mobileResult.getAvge();
						AvgeGraph	=	Avge * 2;
							
						if(Avge < 10){
							ImgUrl = "bar_10.gif";
						}else if(Avge >= 10 && Avge < 21){
							ImgUrl = "bar_20.gif";
						}else if(Avge >= 20 && Avge < 31){
							ImgUrl = "bar_30.gif";
						}else if(Avge >= 30 && Avge < 41){
							ImgUrl = "bar_40.gif";
						}else if(Avge >= 40 && Avge < 51){
							ImgUrl = "bar_50.gif";
						}else if(Avge >= 50 && Avge < 61){
							ImgUrl = "bar_60.gif";
						}else if(Avge >= 60 && Avge < 71){
							ImgUrl = "bar_70.gif";
						}else if(Avge >= 70 && Avge < 81){
							ImgUrl = "bar_80.gif";
						}else if(Avge >= 80 && Avge < 91){
							ImgUrl = "bar_90.gif";
						}else{
							ImgUrl = "bar_100.gif";
						}
				%>
						<ul>
							<li style="padding: 4px;"><%= ExamNo %>. <%= QuestTitle %> <br /><img src="/img/poll/<%= ImgUrl %>" width="<%= AvgeGraph %>" height="15" border="0" alt="<%= Avge %>%"/> <%= mobileResult.getCntHh101() %>명 <%= Avge %>%</li>
						</ul> 
				<%
					}else if("HH102".equals(QuestType)){
						CntHH101	=	mobileResult.getCnt();
						TotalHH101	=	mobileResult.getTotal();
						Avge2		=	mobileResult.getAvge2();
						Avge2Graph	=	Avge2 * 2;
						if(Avge2 < 10){
							ImgUrl = "bar_10.gif";
						}else if(Avge2 >= 10 && Avge2 < 21){
							ImgUrl = "bar_20.gif";
						}else if(Avge2 >= 20 && Avge2 < 31){
							ImgUrl = "bar_30.gif";
						}else if(Avge2 >= 30 && Avge2 < 41){
							ImgUrl = "bar_40.gif";
						}else if(Avge2 >= 40 && Avge2 < 51){
							ImgUrl = "bar_50.gif";
						}else if(Avge2 >= 50 && Avge2 < 61){
							ImgUrl = "bar_60.gif";
						}else if(Avge2 >= 60 && Avge2 < 71){
							ImgUrl = "bar_70.gif";
						}else if(Avge2 >= 70 && Avge2 < 81){
							ImgUrl = "bar_80.gif";
						}else if(Avge2 >= 80 && Avge2 < 91){
							ImgUrl = "bar_90.gif";
						}else{
							ImgUrl = "bar_100.gif";
						}
				%>
						<ul>
						<li style="padding: 4px;"><%= ExamNo %>. <%= QuestTitle %> <br /><img src="/img/poll/<%= ImgUrl %>" width="<%= Avge2Graph %>" height="15" border="0" alt="<%= Avge2 %>%"/> <%= mobileResult.getCnt() %>명 <%= Avge2 %>%</li>
						</ul>
				<%		
					}else if("HH104".equals(QuestType)){
						CntHH104	=	mobileResult.getCntHh104();
						Avge4		=	mobileResult.getAvge3();
						Avge4Graph	=	Avge4 * 2;
						if(Avge4 < 10){
							ImgUrl = "bar_10.gif";
						}else if(Avge4 >= 10 && Avge4 < 21){
							ImgUrl = "bar_20.gif";
						}else if(Avge4 >= 20 && Avge4 < 31){
							ImgUrl = "bar_30.gif";
						}else if(Avge4 >= 30 && Avge4 < 41){
							ImgUrl = "bar_40.gif";
						}else if(Avge4 >= 40 && Avge4 < 51){
							ImgUrl = "bar_50.gif";
						}else if(Avge4 >= 50 && Avge4 < 61){
							ImgUrl = "bar_60.gif";
						}else if(Avge4 >= 60 && Avge4 < 71){
							ImgUrl = "bar_70.gif";
						}else if(Avge4 >= 70 && Avge4 < 81){
							ImgUrl = "bar_80.gif";
						}else if(Avge4 >= 80 && Avge4 < 91){
							ImgUrl = "bar_90.gif";
						}else{
							ImgUrl = "bar_100.gif";
						}
				%>
						
						<ul>
						<li style="padding: 4px;"><%= ExamNo %>. <%= QuestTitle %> <br /><img src="/img/poll/<%= ImgUrl %>" width="<%= Avge4Graph %>" height="15" border="0" alt="<%= Avge4 %>%"/> <%= mobileResult.getCntHh104() %>명 <%= Avge4 %>%</li>
						</ul>
				<%		
					}
				%>
			<%
				}
			%>
			<%
			}
			%>
			</td>
		</tr>
		</tbody>
	</table>
	
	
	
	</ul>
	</div>
	
	

</body>
</html>