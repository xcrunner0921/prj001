<html>
<head>
    <script type = "text/javascript" src = "http://research.seoul.go.kr/oz60/ozrviewer/ztransferx_browers.js"></script>
    <script type = "text/javascript" src = "http://research.seoul.go.kr/oz60/ozrviewer/ozviewer_browers.js"></script>
    <script type = "text/javascript">
        function ZTInstallEndCommand_ZTransferX(param1,param2) {
            Create_Div();
            Initialize_OZViewer("OZReportViewer", "CLSID:0DEF32F8-170F-46f8-B1FF-4BF7443F5F25", "98%", "98%", "application/OZRViewer");
            Insert_OZViewer_Param("connection.servlet", "http://research.seoul.go.kr/oz60/server");
            Insert_OZViewer_Param("connection.reportname","/admincenter/admin_panel.ozr");
            Insert_OZViewer_Param("viewer.isframe", "false");
            Insert_OZViewer_Param("viewer.namespace", "custom_namespace\\ozviewer");
            Insert_OZViewer_Param("viewer.zoom", "80");
            Start_OZViewer();
        }
    </script>
</head>

<body>
<div id = "InstallOZViewer">
    <script type = "text/javascript">
        Initialize_ZT("ZTransferX", "CLSID:C7C7225A-9476-47AC-B0B0-FF3B79D55E67", "0", "0", "http://research.seoul.go.kr/oz60/ozrviewer/ZTransferX.cab#version=2,2,3,8", "application/OZTransferX_1013");
        Insert_ZT_Param("download.server", "http://research.seoul.go.kr/oz60/ozrviewer/");
        Insert_ZT_Param("download.port", "80");
        Insert_ZT_Param("download.instruction", "ozrviewer.idf");
        Insert_ZT_Param("install.base", "<PROGRAMS>/Forcs");
        Insert_ZT_Param("install.namespace", "custom_namespace");
        Start_ZT();
    </script>
</div>
</body>
</html>
