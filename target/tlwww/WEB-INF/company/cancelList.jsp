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
	$("#kssj").val(getLastWeek());
	$("#jssj").val(getNow());
	<c:if test="${command!=null}">
		$("#hphm").val("<c:out value='${command.hphm}'/>");
		$("#kssj").val("<c:out value='${command.kssj}'/>");
		$("#jssj").val("<c:out value='${command.jssj}'/>");
		<c:if test="${command.clzt!=null}">$("#clzt").val("<c:out value='${command.clzt}'/>");</c:if>
	</c:if>

});
function query(){
	if(!compareDate($("#kssj").val(),$("#jssj").val(),"开始时间","结束时间")) return false;
	$("#myform").attr("action","flow.htm?method=queryCancel");
	closes();
	$("#myform").submit();
}
function detail(pk){
	openwin1024('flow.htm?method=detailCancel&sqbh='+pk,'cancel');
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
						<col width="15%">
						<col width="9%">
						<col width="33%">
						<col width="9%">
						<col width="15%">
						<col width="20%">
						<tr>
							
							<td class="head">号牌号码</td>
							<td class="body"><input type="text" class="form-control text-uppercase" name="hphm" id="hphm"></td>
							<td class="head">申请时间</td>
							<td class="body">
								<b class="input-append date kssj"><input type="text" name="kssj" id="kssj" value="" class="dateinput readonly" maxlength="10" readonly><span class="add-on"><i class="icon-calendar datetimebox"></i></span></b>
								&nbsp;-&nbsp;
								<b class="input-append date jssj"><input type="text" name="jssj" id="jssj" value="" class="dateinput readonly" maxlength="10" readonly><span class="add-on"><i class="icon-calendar datetimebox"></i></span></b>
							</td>
							<td class="head">业务状态</td>
							<td class="body"><select id="clzt" name="clzt" class="select form-control">
											<option value="1" selected="selected">1:可退办</option>
											<option value="2">2:已退办（已无效）</option>
							</select></td>
							<td class="command"><button type="button" class="btn btn-info" onClick="query()">查询</button><td>
						</tr>
					</table>
					</form>
				</div>
				<c:if test="${queryList!=null}">
				<div class="mb20">
					<table class="table table-bordered table-hover mb0">
						<thead>
							<tr class="info">
								<th width="28%">单位</th>
								<th width="16%">号牌种类</th>
								<th width="12%">号牌号码</th>
								<th width="14%">卡号</th>
								<th width="20%">申请时间</th>
								<th width="10%">业务状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${queryList}" var="current">
							<tr class="click" onClick="detail('<c:out value="${current.sqbh}"/>')">
								<td><c:out value="${current.ywdwmc}"/></td>
								<td><c:out value="${current.hpzlmc}"/></td>
								<td><c:out value="${current.hphm}"/></td>
								<td><c:out value="${current.kh}"/></td>
								<td><c:out value="${current.lrsj}"/></td>
								<td style='<c:if test="${current.zt=='5'}">color:#0f0;</c:if><c:if test="${current.zt=='8'}">color:#f60;</c:if><c:if test="${current.zt=='9'}">color:#f00;</c:if>'><c:out value="${current.ztmc}"/></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

</body>
</html>
