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
<style>
.query{
	text-align:left;
}
.query .head{
	text-align:right;
	font-size:14px;
	padding:0 5px;
}
.query .body{
	text-align:left;
	padding:0 5px;
	font-size:14px;
}
</style>
<script type="text/javascript">
function doLogin(){
	$("#myform").attr("action","login.htm?method=login");
	closes();
	$("#myform").submit();
} 
</script>
</head>
<body style="background:#f4f4f4;">
<div class="container-fluid">
	<div class="row" style="background:#058;">
		<div class="col-xs-8 font24 white" style="padding:10px 20px;"><i class="icon-credit-card mr5"></i>汽车电子标识发行管理系统</div>
		<div class="col-xs-3 tr" style="padding:5px 10px;">
			<a href="#" class="font16" style="color:#fff;text-decoration:none;"><i class="icon-envelope"></i> <span class="badge">21</span></a>
			<div class="btn-group">
				<button type="button" class="btn btn-link dropdown-toggle font16" style="color:#fff;text-decoration:none;" data-toggle="dropdown">用户名称&nbsp;<span class="caret"></span></button>
				<ul class="dropdown-menu" role="menu">
					<li><a href="login.htm?method=main">返回首页</a></li>
					<li><a href="login.htm?method=editPassword">修改密码</a></li>
					<li class="divider"></li>
					<li><a href="login.htm?method=exit">退出</a></li>
				</ul>
			</div>
		</div>
		<div class="col-xs-1"></div>
	</div>
	<div class="row">
		<div class="col-xs-4 col-md-2">
			<div class="font18 fontbold mt20 mb10 darkblue"><i class="icon-th ml5 mr5"></i>菜单</div>
			<ul class="list-group">
				<li class="list-group-item active">功能菜单1</li>
				<li class="list-group-item"><a href="#" class="black">功能菜单2</a></li>
				<li class="list-group-item"><a href="#" class="black">功能菜单3</a></li>
				<li class="list-group-item"><a href="#" class="black">功能菜单4</a></li>
				<li class="list-group-item"><a href="#" class="black">功能菜单5</a></li>
			</ul>
		</div>
		<div class="col-xs-12 col-md-10">
			<div class="mt20" style="background:#fff;width:100%;min-height:468px;">
				<div class="p20">
					<form class="form-horizontal" role="form" action="" method="post" name="myform" id="myform">
					<div class="row mb20">
						<div class="col-xs-6">
							<table border="0" cellspacing="0" cellpadding="0" class="query">
								<tr>
									<td class="head">标题名称</td>
									<td class="body"><input type="text" class="form-control" name="bt" id="bt" placeholder="请输入名称"></td>
									<td class="head">内容名称</td>
									<td class="body"><input type="text" class="form-control" name="mc" id="mc" placeholder="请输入名称"></td>
								</tr>
							</table>							
						</div>
						<div class="col-xs-6 tr">
							<button type="button" class="btn btn-default">默认</button>
							<button type="button" class="btn btn-primary">保存</button>
							<button type="button" class="btn btn-success">新增</button>
							<button type="button" class="btn btn-info">查询</button>
							<button type="button" class="btn btn-warning">关闭</button>
							<button type="button" class="btn btn-danger">删除</button>
						</div>

					</div>
					</form>
					<div class="mb20">
						<table class="table table-bordered table-hover mb0">
							<thead>
								<tr class="info">
									<th>名称</th>
									<th>城市</th>
									<th>邮编</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Tanmay</td>
									<td>Bangalore</td>
									<td>560001</td>
								</tr>
								<tr>
									<td>Sachin</td>
									<td>Mumbai</td>
									<td>400003</td>
								</tr>
								<tr>
									<td>Uma</td>
									<td>Pune</td>
									<td>411027</td>
								</tr>
							</tbody>
						</table>
						<div class="tr">
							<ul class="pagination mt5">
								<li><a href="#">&laquo;</a></li>
								<li class="active"><a href="#">1</a></li>
								<li class="disabled"><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">&raquo;</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>
