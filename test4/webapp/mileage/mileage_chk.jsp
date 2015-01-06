<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="seoul.research.domain.MileageChk" %>
<%
	ArrayList mileageChkList = (ArrayList)request.getAttribute("mileageChkList");
%>


<root>
<% 
try{
	for (int i = 0; i < mileageChkList.size(); i++) 
	{  
		MileageChk mileageChk = (MileageChk)mileageChkList.get(i); %>
	<items>
		<poll_title><%= mileageChk.getPollTitle() %></poll_title>
		<poll_seq><%= mileageChk.getPollSeq() %></poll_seq>
		<id><%= mileageChk.getId() %></id>
		<path><%= mileageChk.getPath() %></path>
		<regdate><%= mileageChk.getRegdate() %></regdate>
	</items>
<% } 
}catch (Exception ex){
	System.out.println("====>>  마일리지 Exception : " + ex.toString());
%>
<items>
		<poll_title>조회 데이터가 없습니다.</poll_title>
		<poll_seq>:</poll_seq>
		<id>:</id>
		<path>:</path>
		<regdate>:</regdate>
	</items>
<%	
}
%>
</root>

