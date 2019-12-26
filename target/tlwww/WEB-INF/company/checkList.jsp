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
<c:out value="${_site_datetimepicker}" escapeXml="false"/>
<script type="text/javascript">
$(document).ready(function(){
	$(".kssj").datetimepicker({
		format:"yyyy-mm-dd",
		minView:3,
		todayBtn:true,
		autoclose:true
	});
	$(".jssj").datetimepicker({
		format:"yyyy-mm-dd",
		minView:3,
		todayBtn:true,
		autoclose:true
	});
	<c:if test="${command!=null}">
		$("#hpzl").val("<c:out value='${command.hpzl}'/>");
		$("#hphm").val("<c:out value='${command.hphm}'/>");
		$("#kssj").val("<c:out value='${command.kssj}'/>".substr(0,10));
		$("#jssj").val("<c:out value='${command.jssj}'/>".substr(0,10));
	</c:if>

});
function query(){
	if(!compareDate($("#kssj").val(),$("#jssj").val(),"开始时间","结束时间")) return false;
	$("#myform").attr("action","flow.htm?method=queryCheck");
	closes();
	$("#myform").submit();
}
function detail(pk){
	openwin1024('flow.htm?method=detailCheck&sqbh='+pk,'check');
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
				<div class="row mb20">
					<form class="form-horizontal" role="form" action="" method="post" name="myform" id="myform">
					<table border="0" cellspacing="1" cellpadding="0" class="query">
						<col width="9%">
						<col width="17%">
						<col width="9%">
						<col width="14%">
						<col width="9%">
						<col width="32%">
						<col width="10%">
						<tr>
							<td class="head">号牌种类</td>
							<td class="body"><h:codebox list='${hpzl}' id="hpzl" className="form-control" haveNull='1'/></td>
							<td class="head">号牌号码</td>
							<td class="body"><input type="text" class="form-control text-uppercase" name="hphm" id="hphm"></td>
							<td class="head">申请时间</td>
							<td class="body">
								<b class="input-append date kssj"><input type="text" name="kssj" id="kssj" value="" class="dateinput readonly" maxlength="10" readonly><span class="add-on"><i class="icon-calendar datetimebox"></i></span></b>
								&nbsp;-&nbsp;
								<b class="input-append date jssj"><input type="text" name="jssj" id="jssj" value="" class="dateinput readonly" maxlength="10" readonly><span class="add-on"><i class="icon-calendar datetimebox"></i></span></b>
							</td>
							<td class="command"><button type="button" class="btn btn-info" onClick="query()">查询</button><td>
						</tr>
					</table>
					</form>
				</div>
				<div class="mb20">
					<table class="table table-bordered table-hover mb0">
						<thead>
							<tr class="info">
								<th width="28%">单位</th>
								<th width="10%">申请人</th>
								<th width="16%">号牌种类</th>
								<th width="12%">号牌号码</th>
								<th width="14%">卡号</th>
								<th width="20%">申请时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${queryList}" var="current">
							<tr class="click" onClick="detail('<c:out value="${current.sqbh}"/>')">
								<td><c:out value="${current.ywdwmc}"/></td>
								<td><c:out value="${current.lrrmc}"/></td>
								<td><c:out value="${current.hpzlmc}"/></td>
								<td><c:out value="${current.hphm}"/></td>
								<td><c:out value="${current.kh}"/></td>
								<td><c:out value="${current.lrsj}"/></td>
							</tr>
							</c:forEach>
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

</body>
</html>
