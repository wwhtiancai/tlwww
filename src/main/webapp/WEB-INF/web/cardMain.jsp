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
$(document).ready(function() {
	$("#ipt").focus();
	$("#ipt").on('input', function() {
		var val = $("#ipt").val();
		if (val) {
			if (isNaN(val.substr(val.length-1,1))) {
				$("#ipt").val(val.substring(0,val.length-1))
			} else {
				if(val.length>2&&val.substr(2,1)!=","){
					val=val.substring(0,2)+","+val.substring(2,val.length);
					val=val.ReplaceAll(",,",",");
					$("#ipt").val(val);
				}
				if(val.length>8&&val.substr(8,1)!=","){
					val=val.substring(0,8)+","+val.substring(8,val.length);
					val=val.ReplaceAll(",,",",");
					$("#ipt").val(val);
				}
			}
		}
		var t=$("#ipt").val(); 
		$("#ipt").val("").focus().val(t);
	})
});
function query(){
	$("#id").val($("#ipt").val().ReplaceAll(",",""));
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
	<div class="row mt20">
		<div class="col-xs-6 col-xs-offset-3">
			<form role="form" action="" method="post" name="myform" id="myform">
				<input type="hidden" name="id" id="id"/>
			  <div class="form-group">
			    <label for="name" class="font14">汽车电子标识卡号：</label>
			    <input type="text" class="form-control" name="ipt" id="ipt" style="padding:5px;font-size:16px;font-famil:arial;" id="name" placeholder="请输入汽车电子标识卡号" maxlength="14" onkeyup="this.value=this.value.replace(/[^0-9,]+/,'')">
			  </div>
			  <div class="tc"><button type="button" class="btn btn-primary btn-lg" onClick="query()">查询</button></div>
			</form>
			<div style="margin-top:40px;text-align:center;">
				<img src="images/card_no.jpg" alt="" border=""/>
			</div>
			<div class="mt5 tc">
				<b class="red mt10">汽车电子标识卡号位于标识卡右上角处，如红框所示。</b>
			</div>
		</div>
	</div>
</div>
<c:out value="${_site_alterbox}" escapeXml="false"/>
<nav class="navbar navbar-inverse navbar-fixed-bottom bottom" style="color:#ccc;">
  <div class="container white"><a href="http://www.tmri.cn" class="white">公安部交通管理科学研究所</a>&nbsp;&nbsp;版权所有</div>
</nav>

</body>
</html>
