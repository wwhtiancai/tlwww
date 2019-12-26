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
<c:out value="${_site_qrscan}" escapeXml="false"/>
<style>
input[node-type=jsbridge]{
    display:none;
}
</style>
<script language="JavaScript" type="text/javascript">
$(document).ready(function(){
	
});
$(function() {
	Qrcode.init($('[node-type=qr-btn]'));
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
function callbackQrscan(d){
	if(d.length>0&&d.toLowerCase().startsWith("http://www.tmri.cn/rfid/")){
		$("#id").val(d.substr(24,12));
		query();
	}else if(d=='error decoding QR Code'){
		alertbox("扫描的二维码不够清晰，无法识别！");
	}else{
		alertbox(d);
	}
}
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row bg-primary top">
		<div class="col-xs-9"><a href="index.htm" class="fontbold white" style="text-decoration:none;"><i class="icon-credit-card mr5"></i>标签卡查询</a></div>
		<div class="col-xs-3 tr"><a href="index.htm" class="white" style="text-decoration:none;"><i class="icon-align-justify"></i></a></div>
	</div>
	<div class="row mt20">
		<div class="col-xs-12">
			<form role="form" action="" method="post" name="myform" id="myform">
			  <div class="form-group">
			    <label for="name" class="font14">汽车电子标识卡号：</label>
			    <input type="text" class="form-control" name="id" id="id" style="padding:5px;font-size:16px;font-famil:arial;" id="name" placeholder="请输入汽车电子标识卡号" maxlength="12">
			  </div>
			  <div class="tc"><button type="button" class="btn btn-primary btn-lg" onClick="query()">查询</button></div>
			</form>
		</div>
	</div>
	<hr style="margin:30px 0;border:1px dashed #ccc;"/>
	<div class="row mt10">
		<div class="col-xs-12">
		  <div class="tc">
		  	<div class="btn btn-primary btn-lg" node-type="qr-btn">扫描标识卡二维码
					<input node-type="jsbridge" type="file" name="myPhoto" value="扫描标识卡二维码" accept=".jpeg,.jpg,.png" />
        </div>
       	<div class="font12 mt10 tc" style="line-height:1.2;">提示：因手机端权限限制。点击扫描按钮后，<br>请先选择<b>拍照</b>，再<b>拍摄</b>二维码。</div>
		  </div>
		</div>
	</div>
</div>
<c:out value="${_site_alterbox}" escapeXml="false"/>
<nav class="navbar navbar-default navbar-fixed-bottom bottom">
  <div class="container"><a href="http://www.tmri.cn" class="darkblue">公安部交通管理科学研究所</a> 版权所有</div>
</nav>

</body>
</html>
