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
<title>审核：<c:out value="${bean.hphm}"/>-<c:out value="${bean.kh}"/></title>
<c:out value="${_site_global}" escapeXml="false"/>
<c:out value="${_site_company}" escapeXml="false"/>
<c:out value="${_site_datetimepicker}" escapeXml="false"/>
<script type="text/javascript">
$(document).ready(function(){

});
function save(r){
	$("#zt").val(r);
	var m="";
	if("1"==r){
		m="是否确定审核通过该受理业务？";
	}else{
		m="是否确定不通过该受理业务？\r\n不通过对业务将由前端操作人员进行重新补录！";
	}
	if(confirm(m)){
		closes();
		var request=$.ajax({type:'POST',url:"flow.htm?method=auditCheck",data:$('#myform').serialize()});
		request.done(returns);
		request.fail(AjaxError);
	}
}
function returns(d){
	var data=json(d);
	if(data["code"]=="1"){
		window.opener.query();
		alert("操作成功！");
		window.close();
	}else{
		alert(decodeURIComponent(data["message"]));
		opens();
	};
}
</script>
</head>
<body>
<div class="banner"><c:out value="${_user_breadcrumb}" escapeXml="false"/></div>
<div class="panel">
<form class="form-horizontal" role="form" action="" method="post" name="myform" id="myform">
<input type="hidden" name="sqbh" id="sqbh" value="<c:out value='${bean.sqbh}'/>">
<input type="hidden" name="zt" id="zt" value="">
  <div class="form-group">
    <label class="col-xs-2 control-label">号牌种类：</label>
    <div class="col-xs-4 control-body"><c:out value="${bean.hpzlmc}"/></div>
    <label class="col-xs-2 control-label">号牌号码：</label>
    <div class="col-xs-4 control-body"><c:out value="${bean.hphm}"/></div>
  </div>
  <div class="form-group">
    <label class="col-xs-2 control-label">标识卡号：</label>
    <div class="col-xs-4 control-body"><c:out value="${bean.kh}"/><a href="#"><i class="icon-credit-card darkblue font20 fontbold ml10"></i></a></div>
    <label class="col-xs-2 control-label"></label>
    <div class="col-xs-4 control-body"></div>
  </div>
  <div class="form-group">
    <label class="col-xs-2 control-label">行驶证号：</label>
    <div class="col-xs-4 control-body"><c:out value="${bean.xszh}"/></div>
    <label class="col-xs-2 control-label">商业产品ID：</label>
    <div class="col-xs-4 control-body"><c:out value="${bean.sycpid}"/></div>
  </div>
  <div class="form-group">
    <label class="col-xs-2 control-label">受理人：</label>
    <div class="col-xs-4 control-body"><c:out value="${bean.lrrmc}"/></div>
    <label class="col-xs-2 control-label">受理时间：</label>
    <div class="col-xs-4 control-body"><c:out value="${bean.lrsj}"/></div>
  </div>
  <div class="form-group">
    <label class="col-xs-2 control-label">受理单位：</label>
    <div class="col-xs-10 control-body"><c:out value="${bean.ywdwmc}"/></div>
  </div>
  <div class="form-group">
    <label class="col-xs-2 control-label">照片：</label>
    <div class="col-xs-10 control-body">
    	<table border="0" cellspacing="0" cellpadding="0" style="width:100%;">
    		<tr>
    			<td width="32%"><a href="applydocpic.do?sqbh=<c:out value="${bean.sqbh}"/>&lx=11" target="_blank"><img src="applydocpic.do?sqbh=<c:out value="${bean.sqbh}"/>&lx=11" alt="" border="0" style="width:100%;height:auto;max-width:100%;max-height:100%;border:1px solid #000;padding:1px;"></a></td>
    			<td width="2"></td>
    			<td width="32%"><a href="applydocpic.do?sqbh=<c:out value="${bean.sqbh}"/>&lx=12" target="_blank"><img src="applydocpic.do?sqbh=<c:out value="${bean.sqbh}"/>&lx=12" alt="" border="0" style="width:100%;height:auto;max-width:100%;max-height:100%;border:1px solid #000;padding:1px;"></a></td>
    			<td width="2"></td>
    			<td width="32%"><a href="applydocpic.do?sqbh=<c:out value="${bean.sqbh}"/>&lx=13" target="_blank"><img src="applydocpic.do?sqbh=<c:out value="${bean.sqbh}"/>&lx=13" alt="" border="0" style="width:100%;height:auto;max-width:100%;max-height:100%;border:1px solid #000;padding:1px;"></a></td>
    		</tr>
    	</table>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-2">
      <button type="button" class="btn btn-danger" onClick="save('0')">审核不通过</button>
    </div>
    <div class="col-sm-8 tr">
    	<button type="button" class="btn btn-primary" onClick="save('1')">确认并审核通过&nbsp;(Enter)</button>
    </div>
  </div>
</form>
</div>
</body>
</html>
