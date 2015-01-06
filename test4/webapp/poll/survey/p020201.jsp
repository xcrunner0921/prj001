<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	int gMenuIndex		= 2;
	int gSubMenuIndex	= 2;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>서울시 여론조사</title>
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/content.css"/>

<script type="text/javascript">

</script>

</head>
<body>
<div id="wrap">
    <!-- header -->
    <%@ include file="/include/top.jsp" %>
    <!-- //header -->

    <!-- container -->
    <div id="container" class="introJoinPanel">
        <!-- snb -->
        <%@ include file="/include/panel/left.jsp" %>
        <!-- //snb -->
        <!-- content -->
        <div id="content" class="content">
            <div class="spot">
                <h2><img src="../../img/poll/tit_introSurvey.gif" alt="시정 여론조사 소개" /></h2>
            </div>
            <div class="article">
                <p class="txt_joinPanel">
                    서울시에서는 시민과 소통하고 시민과 함께 만드는 시정을 구현하고자 <em class="emph">복지, 문화, 경제, 환경, 안전 등 각 시정 분야에 걸쳐 다양한 여론조사를 수행</em>하고 있습니다.<br />
                    주요 시책에 대해 외부 전문기관과 조사를 진행, 시민의 의견을 객관적으로 파악하며, 또한 자체 온라인 조사를 통해 온라인 패널분들의 의견을 경청하여 시정에 반영하려 노력하고 있습니다.<br />
                    
                </p>
                <h3>시정여론조사 절차</h3>
                <ol class="panelStep">
                    <li>
                        <img src="../../img/poll/img_step01.gif" alt="Step01" />
                        정기적 혹은 수시로 여러 부서에서 시정과 관련된 여론조사를 요청합니다
                    </li>
                    <li>
                        <img src="../../img/poll/img_step02.gif" alt="Step02" />
                        서울시 간부 및 여론 전문가, 교수, 연구원 등 각계 전문가로 구성된 ‘시정여론조사 자문위원회’를 개최하여 여론조사 주제의 실시여부를 검토합니다. 단, 시급한 현안이거나 예산이 크게 소요되지 않는 조사의 경우는 내부 검토를 통해 실시하기도 합니다.
                    </li>
                    <li>
                        <img src="../../img/poll/img_step03.gif" alt="Step03" />
                        선정된 주제에 대해 개별 조사 설계를 마친 후 이에 대해 실제 조사(실사)를 수행하게 되며 통계처리 및 결과 분석을 마치고 나면 조사를 요청한 부서에 그 결과를 알려줍니다. 
                    </li>
                    <li class="stepLst">
                        <img src="../../img/poll/img_step04.gif" alt="Step04" />
                        각 부서는 담당 분야의 시책을 수립하거나 추진해 나갈 때 여론 조사결과를 활용합니다.

                    </li>
                </ol>
                <h3>시정여론조사 활용</h3>
                <ul class="list_style">
                    <li>각계 각층 시민들의 다양한 목소리를 전문가의 관리를 통해 객관적이고 과학적으로 모아낸 시정여론조사 결과는 단기 혹은 장기적으로 시정에 반영되어 시민의 삶을 개선해 나가는데 중요하게 활용되고 있습니다.</li>
                    
                </ul>
            </div>
        </div>
        <!-- //content -->
    </div>
    <!-- //container -->

    <!-- footer -->
    <%@ include file="/include/footer.jsp" %>
    <!-- //footer -->
</div>
</body>
</html>