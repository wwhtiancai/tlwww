<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charSet="utf-8"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="renderer" content="webkit"/>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="author" content="weng.yf" />
<meta http-equiv="keywords" content="公安部,交通,管理,交通管理,交管,科研所,科研,牌证部,电子标识,汽车电子标识,RFID,智能交通" />
<meta name="description" content="公安部交通管理科学研究所-汽车电子标识"/>
<title></title>
<c:out value="${_site_global}" escapeXml="false"/>
</head>
<body>
<div class="container-fluid">
	<div class="row bg-primary top">
		<div class="col-xs-9"><a href="index.htm" class="fontbold white" style="text-decoration:none;"><i class="icon-credit-card mr5"></i>汽车电子标识</a></div>
		<div class="col-xs-3"></div>
	</div>
	<div class="row">
		<div class="col-xs-6 col-md-3"><a href="eri.htm?method=forwardApply" class="black"><div class="bg-success mt20 font18 tc vm" style="border-radius:5px;height:80px;line-height:80px;"><i class="icon-paste mr5"></i>办卡申请</div></a></div>
		<div class="col-xs-6 col-md-3"><a href="eri.htm?method=forwardCard" class="black"><div class="bg-info mt20 font18 tc vm" style="border-radius:5px;height:80px;line-height:80px;"><i class="icon-indent-right mr5"></i>标签卡查询</div></a></div>
<%--
		<div class="col-xs-6 col-md-3"><div class="bg-warning mt20 font18 tc vm" style="border-radius:5px;height:80px;line-height:80px;"><i class="icon-coffee mr5"></i>会员中心</div></div>
		<div class="col-xs-6 col-md-3"><div class="bg-danger mt20 font18 tc vm" style="border-radius:5px;height:80px;line-height:80px;"><i class="icon-ambulance mr5"></i>企业用户</div></div>
--%>
	</div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom bottom">
  <div class="container"><a href="http://www.tmri.cn" class="darkblue">公安部交通管理科学研究所</a> 版权所有</div>
</nav>



</body>
</html>
