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
<c:out value="${_site_company}" escapeXml="false"/>
<script type="text/javascript">
$(document).ready(function(){
	$("#mm").val("");
	$("#xm").val("");
	$("#mc").val("");
});
function save(){
	if($("#mm").val().length<1){
		alert("请输入原密码！");
		return false;
	}
	if($("#xm").val().length<1||$("#mc").val().length<1){
		alert("请输入新密码和确认密码！");
		return false;
	}
	if($("#xm").val()!=$("#mc").val()){
		alert("新密码和确认密码不一致！");
		return false;
	}
	closes();
	var request=$.ajax({type:'POST',url:"main.htm?method=savePassword",data:$('#myform').serialize()});
	request.done(returns);
	request.fail(AjaxError);
}
function returns(d){
	var data=json(d);
	if(data["code"]=="1"){
		alert("修改成功！请退出后再次登录用户！");
		location.href="index.htm";
	}else{
		alert(decodeURIComponent(data["message"]));
		opens();
	};
}
</script>
</head>
<body>
<div class="container-fluid">
	<c:out value="${_user_top}" escapeXml="false"/>
	<div class="row">
		<div class="col-xs-4 col-md-2">
			<div class="font18 fontbold mt20 mb10 darkblue"><i class="icon-th ml5 mr5"></i>菜单</div>
			<c:out value="${_user_menus}" escapeXml="false"/>
		</div>
		<div class="col-xs-12 col-md-10">
			<div class="panel mt20">
			<form class="form-horizontal" role="form" action="" method="post" name="myform" id="myform">
				<div class="row mb20">
					<div class="col-xs-6 col-xs-offset-2">
					  <div class="form-group">
					    <label for="input1">账号：</label>
					    <input type="input" class="form-control" name="yhzh" id="yhzh" value="<c:out value='${user.yhzh}'/>">
					  </div>
					  <div class="form-group">
					    <label for="input2">原密码：</label>
					    <input type="password" class="form-control" name="mm" id="mm" placeholder="请输入原密码" value="" maxlength="16">
					  </div>
					  <div class="form-group">
					    <label for="input3">新密码：</label>
					    <input type="password" class="form-control" name="xm" id="xm" placeholder="请输入新密码" value="" maxlength="16">
					  </div>
					  <div class="form-group">
					    <label for="input4">确认新密码：</label>
					    <input type="password" class="form-control" name="mc" id="mc" placeholder="请再次确认新密码" value="" maxlength="16">
					  </div>
					</div>
					<div class="col-xs-6 col-xs-offset-2 tc">
						<button type="button" class="btn btn-primary" onClick="save()">保存</button>
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>

</body>
</html>
