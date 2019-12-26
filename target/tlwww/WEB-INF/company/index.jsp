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
body {background-image: url("../images/sys/login.jpg");background-position: center 0;background-repeat: no-repeat;background-attachment: fixed;background-size: cover;-webkit-background-size: cover;-o-background-size: cover;-moz-background-size: cover;-ms-background-size: cover;}
</style>
<script type="text/javascript">
function doLogin(){
	if(!doChecking()) return;
	closes();
	var request=$.ajax({type:'POST',url:"login.htm",data:$('#myform').serialize()});
	request.done(returns);
	request.fail(AjaxError);
}
function returns(d){
	var data=json(d);
	if(data["code"]=="1"){
		location.href="main.htm?method=index";
	}else{
		alert(decodeURIComponent(data["message"]));
		opens();
	};
}
function doChecking(){
	//object:FRM_SYSUSER
	//regulation:yhzh(非空)
	//regulation:mm(非空)
	if(!checkNull($("#yhzh"),"用户帐号")) return false;
	if(!checkNull($("#mm"),"用户密码")) return false;
	return true;
}
</script>
</head>
<body>
<c:out value="${_site_navigation}" escapeXml="false"/>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-6"></div>
		<div class="col-xs-5">
			<div style="width:100%;height:280px;background:#fff;">
				<div class="p10">
					<div class="mt20 mb20"><h2 class="text-center text-info">汽车电子标识企业发行系统<small>（公共版）</small></h2></div>
					<div>
						<form class="form-horizontal" role="form" action="" method="post" name="myform" id="myform">
							<div class="form-group">
							<label for="firstname" class="col-sm-3 control-label font16">账号：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="yhzh" id="yhzh" placeholder="请输入企业账号">
							</div>
							</div>
							<div class="form-group">
							<label for="lastname" class="col-sm-3 control-label font16">密码：</label>
							<div class="col-sm-8">
								<input type="password" class="form-control" name="mm" id="mm" placeholder="请输入账号密码">
							</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-8">
									<button type="button" class="btn btn-lg btn-primary" onClick="doLogin()">&nbsp;&nbsp;进入&nbsp;&nbsp;</button>
	 							</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-1"></div>
	</div>
</div>
<nav class="navbar navbar-inverse navbar-fixed-bottom bottom" style="color:#ccc;">
  <div class="container white">Copyright&nbsp;&copy;&nbsp;2018-2019&nbsp;<a href="http://www.tmri.cn" class="white">公安部交通管理科学研究所</a>&nbsp;&nbsp;版权所有</div>
</nav>

</body>
</html>
