<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html>
<head>
<title>Anyframe Template</title>
</head>
<body>
<table>
	<tr>
		<td colspan="2"><tiles:insertAttribute name="header" /></td>
	</tr>
	<tr>
		<td valign="top"><tiles:insertAttribute name="footer" /></td>
		<td valign="top"><tiles:insertAttribute name="body" /></td>
	</tr>
</table>
</body>
</html>