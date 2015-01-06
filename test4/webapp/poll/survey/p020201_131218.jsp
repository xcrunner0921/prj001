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
                    서울시에서는 시민 여러분의 목소리에 귀 기울이는 열린 행정을 구현하고자 1989년 여론조사 전문 공무원을 두어 시정여론조사 업무를 시작하였으며, 현재까지 <em class="emph">교통,환경,문화,복지 등 시정 시책의 기초자료 및 정책효과분석,시정방향에 대한 시민의견 수렴 등 다양한 여론조사를 수행</em>하고 있습니다.<br />
                    서울시 주요시책 실시 사전-중간-사후에 외부 전문 리서치 기관에 의뢰해 조사의 객관성, 신뢰성을 높이고 있습니다.<br />
                    면접,전화,인터넷,FGI(좌담회),심층면접 등 다양한 방법으로 시민들의 다양한 의견을 수렴하고 있습니다.
                </p>
                <h3>시정여론조사 절차</h3>
                <ol class="panelStep">
                    <li>
                        <img src="../../img/poll/img_step01.gif" alt="Step01" />
                        정기적 혹은 수시로 서울시 여러 부서에서 시정과 관련된 여론조사를 요청합니다.
                    </li>
                    <li>
                        <img src="../../img/poll/img_step02.gif" alt="Step02" />
                        서울시 간부 및 여론 전문가, 교수, 연구원 등 각계 전문가로 구성된 ‘시정여론조사 자문위원회’를 개최하여 그 해에 실시할 여론조사 주제를 선정합니다. 단, 시급한 시정현안인 경우는 즉시 실시하기도 합니다.
                    </li>
                    <li>
                        <img src="../../img/poll/img_step03.gif" alt="Step03" />
                        선정된 주제에 대해 개별 조사 설계를 마친 후 이에 대해 실제 조사(실사)를 수행하게 되며 통계처리 및 결과 분석을 마치고 나면 조사를 요청한 부서에 그 결과를 알려줍니다.
                    </li>
                    <li class="stepLst">
                        <img src="../../img/poll/img_step04.gif" alt="Step04" />
                        각 부서는 교통이나 환경,복지 등 여러 분야에서 시책을 수립하거나 추진해 나갈 때 여론 조사결과를 적극 활용하게 됩니다.
                    </li>
                </ol>
                <h3>시정여론조사 활용</h3>
                <ul class="list_style">
                    <li>각계 각층의 시민들의 다양한 목소리를 한 쪽에 치우치지 않고 지역별, 성별, 연령별로 과학적이고 체계적 방법을 통해 골고루 의견을 모은 시정여론조사 결과는 단기 혹은 장기적으로 시정에 반영되어 시민생활의 불편을 개선해 나가는데 중요하게 활용되고 있습니다.</li>
                    <li>시정여론조사에 대해 궁금한 사항이 있으시거나 좋은 의견이 있으시면 뉴미디어 담당관실로 연락 주시면 성의껏 답해 드리겠습니다.</li>
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