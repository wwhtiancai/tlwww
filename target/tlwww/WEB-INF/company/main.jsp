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
function doLogin(){

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
			<div class="mt20" style="background:#fff;width:100%;min-height:468px;">
				<div class="p20">
					<div class="row">
						<div class="col-xs-6">
							<div class="panel panel-info">
								<div class="panel-heading">
									<h3 class="panel-title">用户信息</h3>
								</div>
								<div class="panel-body" style="font-size:14px;line-height:2;">
									账号：${user.yhzh}<br>
									姓名：${user.xm}<br>
									单位：${department.bmqc}<br>
									最近登录时间：${user.zjdlsj}<br>
									最近登录IP：${user.zjdlip}<br>
								</div>
							</div>
						</div>
						<div class="col-xs-6"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>
