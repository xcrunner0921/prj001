<%@ page contentType="text/html; charset=utf-8" %>
<%
	int pollIdx = Integer.parseInt(request.getParameter("pollIdx"));
	String user = (String) request.getParameter("user");
%>

<html>
<head>
    <script type = "text/javascript" src = "http://research.seoul.go.kr/oz60/ozrviewer/ztransferx_browers.js"></script>
    <script type = "text/javascript" src = "http://research.seoul.go.kr/oz60/ozrviewer/ozviewer_browers.js"></script>
    <script type = "text/javascript">
        function ZTInstallEndCommand_ZTransferX(param1,param2) {
            Create_Div();
            Initialize_OZViewer("OZReportViewer", "CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25", "98%", "98%", "application/OZRViewer");
            Insert_OZViewer_Param("connection.servlet", "http://research.seoul.go.kr/oz60/server");
            Insert_OZViewer_Param("connection.reportname","/poll/seoul.ozr");
            Insert_OZViewer_Param("viewer.isframe", "false");
            Insert_OZViewer_Param("viewer.namespace", "seoul_research\\ozviewer");

            Insert_OZViewer_Param("connection.pcount", "1");            
            Insert_OZViewer_Param("connection.args1", "isadmin=<%= user %>");

            Insert_OZViewer_Param("odi.odinames", "seoul");
            Insert_OZViewer_Param("odi.seoul.pcount", "1");
            Insert_OZViewer_Param("odi.seoul.args1", "pollIdx=<%= pollIdx %>");

            Start_OZViewer();
        }
    </script>
</head>

<body>
<div id = "InstallOZViewer">
    <script type = "text/javascript">
        //Initialize_ZT("ZTransferX", "CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67", "0", "0", "http://research.seoul.go.kr/oz60/ozrviewer/ZTransferX.cab#version=2,2,3,8", "application/OZTransferX_1013");
        Initialize_ZT("ZTransferX", "CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67", "0", "0", "http://research.seoul.go.kr/oz60/ozrviewer/ZTransferX.cab#version=2,2,3,8", "application/OZTransferX_1104");
        Insert_ZT_Param("download.server", "http://research.seoul.go.kr/oz60/ozrviewer/");
        Insert_ZT_Param("download.port", "80");
        Insert_ZT_Param("download.instruction", "ozrviewer.idf");
        Insert_ZT_Param("install.base", "<PROGRAMS>/Forcs");
        Insert_ZT_Param("install.namespace", "seoul_research");
        Insert_ZT_Param("imageurl", "http://citizen.seoul.kr/images/citizen/logo_top.gif");
        Insert_ZT_Param("message", "OZ Viewer 설치 중입니다. 잠시 기다려주세요.");
        Start_ZT();
    </script>
</div>
</body>
</html>
