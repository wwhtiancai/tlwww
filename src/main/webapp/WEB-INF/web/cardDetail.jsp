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
<meta name="description" content="公安部交通管理科学研究所 汽车电子标识"/>
<title></title>
<c:out value="${_site_global}" escapeXml="false"/>
<style type="text/css">

</style>
<script type="text/javascript">
$(document).ready(function(){
	<c:if test="${not empty error}">alertbox("${error}")</c:if>
});
function query(){
	if($("#id").val().length!=12){
		alertbox("请输入完整的汽车电子标识卡号！<br>卡号位于汽车电子标识的右上侧，仅为12位数字（不包括“-”后面1位）。<br>如：320123456789")
		return;
	}
	$("#myform").attr("action","eri.htm?method=getCard&back=1");
	closes();
	$("#myform").submit();
}
</script>
</head>
<body>
<c:out value="${_site_navigation}" escapeXml="false"/>
<div class="container-fluid">
<c:if test="${not empty error}">
<c:out value="${_site_alterbox}" escapeXml="false"/>
</c:if>
<c:if test="${empty error}">
	<div class="row font18 mt10">
	  <div class="col-xs-4 darkblue tr clear">卡号：</div>
	  <div class="col-xs-8 tl pl0">${eri.kh}</div>
	</div>
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
	<div class="row font18 mt5">
	  <div class="col-xs-4 darkblue tr clear">状态：</div>
	  <div class="col-xs-8 tl pl0">
			<c:if test="${eri!=null&&(eri.zt=='A'||eri.zt=='7'||eri.zt=='8')}">已使用<c:if test="${eri!=null&&(eri.zt=='A'||eri.zt=='7')}">（但未安装）</c:if></c:if>
			<c:if test="${eri!=null&&(eri.zt=='9')}">已作废</c:if>
			<c:if test="${eri!=null&&(eri.zt=='0')}">无效</c:if>
			<c:if test="${eri!=null&&(eri.zt=='1'||eri.zt=='2'||eri.zt=='3'||eri.zt=='4'||eri.zt=='5'||eri.zt=='6')}">未使用</c:if>
		</div>
	</div>
</c:if>
	<div class="tc mt20"><a class="btn btn-warning btn-lg" href="eri.htm?method=forwardCard">返回</a></div>
	<div style="height:70px;"></div>
</div>
<nav class="navbar navbar-inverse navbar-fixed-bottom bottom" style="color:#ccc;">
  <div class="container white"><a href="http://www.tmri.cn" class="white">公安部交通管理科学研究所</a>&nbsp;&nbsp;版权所有</div>
</nav>

</body>
</html>
