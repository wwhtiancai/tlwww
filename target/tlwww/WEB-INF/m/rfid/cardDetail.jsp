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
<c:out value="${_site_qrcode}" escapeXml="false"/>
<script language="JavaScript" type="text/javascript">
$(document).ready(function(){
	<c:if test="${eri!=null&&eri.zt=='8'}">	$("#qrcodebox").qrcode({text:"${eri.kh}",render:"table",width:200,height:200});</c:if>
	<c:if test="${not empty error}">alertbox("${error}")</c:if>
});
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row bg-primary top">
		<div class="col-xs-9"><a href="index.htm" class="fontbold white" style="text-decoration:none;"><i class="icon-credit-card mr5"></i>汽车电子标识</a></div>
		<div class="col-xs-3 tr">
		<c:if test="${back=='0'}"><a href="index.htm" class="white" style="text-decoration:none;"><i class="icon-align-justify"></i></a></c:if>
		<c:if test="${back=='1'}"><a href="eri.htm?method=forwardCard" class="white" style="text-decoration:none;"><i class="icon-share"></i></a></c:if>
		</div>
	</div>
<c:if test="${not empty error}">
<c:out value="${_site_alterbox}" escapeXml="false"/>
</c:if>
<c:if test="${empty error}">
	<c:if test="${eri!=null&&(eri.zt=='A'||eri.zt=='7')}">
	<div class="alert alert-danger font16 fontbold mt10">提示：该汽车电子标识卡已办理受理业务，但未进行安装。</div>
	</c:if>
	<div class="row font18 mt10">
	  <div class="col-xs-4 darkblue tr clear">卡号：</div>
	  <div class="col-xs-8 tl pl0">${eri.kh}</div>
	</div>
	<c:if test="${eri!=null&&eri.zt=='8'&&showprivate=='1'}">
	<div class="row font18 mt5">
	  <div class="col-xs-4 darkblue tr clear">号牌种类：</div>
	  <div class="col-xs-8 tl pl0">${eri.hpzlmc}</div>
	</div>
	<div class="row font18 mt5">
	  <div class="col-xs-4 darkblue tr clear">号牌号码：</div>
	  <div class="col-xs-8 tl pl0">${eri.hphm}</div>
	</div>
	<div class="row font18 mt5">
	  <div class="col-xs-4 darkblue tr clear">年检期止：</div>
	  <div class="col-xs-8 tl pl0">${eri.yxqz}</div>
	</div>
	</c:if>
	<div class="row font18 mt5">
	  <div class="col-xs-4 darkblue tr clear">发卡单位：</div>
	  <div class="col-xs-8 tl pl0">${eri.sydwmc}</div>
	</div>
	<c:if test="${eri!=null&&(eri.zt=='A'||eri.zt=='7'||eri.zt=='8')}">
	<div class="row font18 mt5">
		<div class="col-xs-4 darkblue tr clear">发卡时间：</div>
	  <div class="col-xs-8 tl pl0">${eri.gxhsj}</div>
	</div>
	</c:if>
	<c:if test="${eri!=null&&eri.zt=='6'}">
	<div class="alert alert-warning tc mt10">
		<div class="black" style="line-height:2;">该汽车电子标识卡为空白卡！<br>需办理和使用的，请咨询相关发行机构。</a></div>
	</div>
	</c:if>
	<c:if test="${eri!=null&&eri.zt=='8'}">
	<table border="0" cellspacing="0" cellpadding="0" align="center" class="mt10">
		<tr>
			<td><div id="qrcodebox"></div></td>
		</tr>
	</table>
	</c:if>
</c:if>
	<div style="height:70px;"></div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom bottom">
  <div class="container"><a href="http://www.tmri.cn" class="darkblue">公安部交通管理科学研究所</a> 版权所有</div>
</nav>

</body>
</html>
