String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.ReplaceAll = stringReplaceAll;
function stringReplaceAll(AFindText,ARepText){
	raRegExp = new RegExp(AFindText,"g");
	return this.replace(raRegExp,ARepText)
};
function opens(){
	$("input:button").each(function(){
		$(this).attr("disabled",false);
	});
}
function closes(){
	$("input:button").each(function(){
		$(this).attr("disabled",true);
	});
}
function closewindow(){
	window.close();
}
function AjaxError(jqXHR,textStatus){
	alert("Request failed:"+textStatus);
}
function alertbox(s){
	$("#myModalMessage").html(s);
	$('#myModal').modal('toggle');
}
function openwindow(url,winname,width,height){
	var windowwidth=screen.width;
	var windowheight=screen.height-60;
	windowwidth=(windowwidth-width)/2;
	if(windowwidth<0) windowwidth=0;
	windowheight=(windowheight-height)/2;
	subwinname=window.open(url,winname,"resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no,location=no,directories=no,width="+width+",height="+height+",top="+windowheight+",left="+windowwidth);
	subwinname.focus();
	return subwinname;
}
function openwin(url,winname){
	var windowwidth=screen.width;
	var windowheight=screen.height;
	openwindow(url,winname,windowwidth,windowheight);
}
function openwin800(url,winname){
	openwindow(url,winname,800,600);
}
function openwin1024(url,winname){
	openwindow(url,winname,1024,700);
}
function openwin1280(url,winname){
	openwindow(url,winname,1280,700);
}
function json(d){
	return eval("("+d+")");
}
//检查对象是否为空，obj-对象，name-提示的输入项
function checkNull(obj,name){
	if (obj.val()==""){
		alert("“"+name+"”不能为空！");
		obj.focus();
		return false;
	}else{
		return true;
	}
}
//检查对象值的长度是否相等
function checkPrecision(obj,leng,name){
	if (typeof obj=="undefined"){
		return true;
	}
	if (obj!=null&&obj.val()!=null&&obj.val().length>0){
		if(obj.val().length!=leng){
			alert("“"+name+"”中输入值必须是"+leng+"个长度！");
			obj.focus();
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
//检查对象值的长度是否符合范围
function checkLength(obj,leng,name){
	if (typeof obj=="undefined"){
		return true;
	}
	if (obj!=null&&obj.val()!=null&&obj.val().length>0){
		if(obj.val().length>leng){
			alert("“"+name+"”中输入值超过"+leng+"个长度的限制！");
			obj.focus();
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
//校验传入的文本框文本是否为数值类型
function checkIntNumber(obj,name){
	if (typeof obj=="undefined"){
		return true;
	}
	if (obj!=null&&obj.val()!=null&&obj.val().length>0){
		if(!invalidLetter(name,obj.val(),"0123456789")){
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
//校验传入的文本框文本是否为数值类型
function checkNumber(obj,name){
	if (typeof obj=="undefined"){
		return true;
	}
	if (obj!=null&&obj.val()!=null&&obj.val().length>0){
		if(!invalidLetter(name,obj.val(),"0123456789.")){
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
//校验传入的文本框文本是否为电话号码
function checkTel(obj,name){
	if (typeof obj=="undefined"){
		return true;
	}
	if (obj!=null&&obj.val()!=null&&obj.val().length>0){
		if(!invalidLetter(name,obj.val(),"0123456789-(),")){
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}
//校验输入值的时间格式是否正确，限定于YYYY-MM-DD和YYYY-MM-DD HH24:MI的格式
function checkDate(thedate,name){
	if (typeof thedate=="undefined"){
		return true;
	}
	try{
		if(thedate.length==10){
			var objRegExp=/^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$/
			if(!objRegExp.test(thedate)){
				alert("“"+name+"”中日期数据不符合要求，\r\n应输入为“YYYY-MM-DD”的格式。");
				return false;
			}else{
				var arrayDate=thedate.split(RegExp.$1);
				var intDay=new Number(arrayDate[2],10);
				var intYear=new Number(arrayDate[0],10);
				var intMonth=new Number(arrayDate[1],10);
				if(intMonth>12||intMonth<1){ 
					alert("“"+name+"”中日期数据不符合要求，\r\n应输入为“YYYY-MM-DD”的格式。");
					return false;
				} 
				var arrayLookup={'1' : 31,'3' : 31, '4' : 30,'5' : 31,'6' : 30,'7' : 31, '8' : 31,'9' : 30,'10' : 31,'11' : 30,'12' : 31}
				if(arrayLookup[intMonth]!=null){ 
					if(intDay<=arrayLookup[intMonth]&&intDay!=0)
						return true;
				}
				if (intMonth-2==0){
					var booLeapYear=(intYear%4==0&&(intYear%100!=0||intYear%400==0));
					if( ((booLeapYear&&intDay<=29)||(!booLeapYear&&intDay<=28))&&intDay!=0)
						return true;
				}
			}
			alert("“"+name+"”中日期数据不符合要求，\r\n应输入为“YYYY-MM-DD”的格式。");
			return false;
		}else if(thedate.length==16){
			var objRegExp=/^(\d{4})\-(\d{2})\-(\d{2}) (\d{2}):(\d{2})$/;
			if(!objRegExp.test(thedate)){
				alert("“"+name+"”中日期数据不符合要求，\r\n应输入为“YYYY-MM-DD HH:MM”的格式。");
				return false; 
			}else{
				var intYear=new Number(thedate.substr(0,4));
				var intMonth=new Number(thedate.substr(5,2));
				var intDay=new Number(thedate.substr(8,2));
				var intHour=new Number(thedate.substr(11,2));
				var intMinute=new Number(thedate.substr(14,2));
				if(intMonth>12||intMonth<1){
					alert("“"+name+"”中日期数据不符合要求，\r\n应输入为“YYYY-MM-DD HH:MM”的格式。");
					return false;
				}
				if(intHour>23||intHour<0){
					alert("“"+name+"”中日期数据不符合要求，\r\n应输入为“YYYY-MM-DD HH:MM”的格式。");
					return false;
				}
				if(intHour>59||intHour<0){
					alert("“"+name+"”中日期数据不符合要求，\r\n应输入为“YYYY-MM-DD HH:MM”的格式。");
					return false;
				}
				var arrayLookup={'1' : 31,'3' : 31, '4' : 30,'5' : 31,'6' : 30,'7' : 31, '8' : 31,'9' : 30,'10' : 31,'11' : 30,'12' : 31}
				if(arrayLookup[intMonth]!=null){
					if(intDay<=arrayLookup[intMonth]&&intDay!=0)
						return true;
				}
				if (intMonth-2==0){
					var booLeapYear=(intYear%4==0&&(intYear%100!=0||intYear%400==0));
					if( ((booLeapYear&&intDay<=29)||(!booLeapYear&&intDay<=28))&&intDay!=0)
						return true;
				}
			} 
			alert("“"+name+"”中日期数据不符合要求，\r\n应输入为“YYYY-MM-DD HH:MM”的格式。");
			return false;
		}else if(thedate.length==0){
			return true;
		}else{
			alert("“"+name+"”中日期格式不符合要求");
			return false;
		}
	}catch(e){
		alert("“"+name+"”中日期格式不符合要求");
		return false;
	}
}
//检测开始时间和结束时间
function compareDate(kssj,jssj,name1,name2){
	if(!checkDate(kssj,name1))return false;
	if(!checkDate(jssj,name2))return false;
	if(kssj>jssj){
		alert("“"+name1+"”不能大于“"+name2+"”！");
		return false;
	}
	return true;
}
//取得当前时间
function getNow(time){
	var len= arguments.length;
	var r;
	var d=new Date();
	if(0==len){
		r=d.getFullYear()+"-";
		if(d.getMonth()>=9)
			r+=(d.getMonth()+1)+"-";
		else
			r+="0"+(d.getMonth()+1)+"-";	
		if(d.getDate()<10)
			r+="0"+(d.getDate());
		else
			r+=(d.getDate());
	}else if(1==len){
		r=d.getFullYear()+"-";
		if(d.getMonth()>=9)
			r+=(d.getMonth()+1)+"-";
		else
			r+="0"+(d.getMonth()+1)+"-";	
		if(d.getDate()<10)
			r+="0"+(d.getDate())+" ";
		else
			r+=(d.getDate())+" ";
		if(d.getHours()<10)
			r+="0"+(d.getHours())+":";
		else
			r+=(d.getHours())+":";
		if(d.getMinutes()<10)
			r+="0"+(d.getMinutes())+":";
		else
			r+=(d.getMinutes())+":";
		if(d.getSeconds()<10)
			r+="0"+(d.getSeconds());
		else
			r+=(d.getSeconds());
	}
	return r;
}
//取得昨天时间
function getYesterday(){
	var today = new Date();   
	today.setTime(today.getTime()-(24*60*60*1000));   
	var month=today.getMonth()+1; 
	var day=today.getDate(); 
	if(month<10) sMonth="0"+month;
	else sMonth = month;
	if(day<10) sDay="0"+day; 
	else sDay = day; 
	r=today.getFullYear()+"-"+sMonth+"-"+sDay; 
	return r;
}
function getTomorrow(){
	var today = new Date();   
	today.setTime(today.getTime()+(24*60*60*1000));   
	var month=today.getMonth()+1; 
	var day=today.getDate(); 
	if(month<10) sMonth="0"+month;
	else sMonth = month;
	if(day<10) sDay="0"+day; 
	else sDay = day; 
	r=today.getFullYear()+"-"+sMonth+"-"+sDay; 
	return r;
}
//取得上周时间
function getLastWeek(){
	var r;
	var d=new Date();
	var t = d.getDate();
	t = t-7;
	d.setDate(t);
	r=d.getFullYear()+"-";
	if(d.getMonth()>=9)
		r+=(d.getMonth()+1)+"-";
	else
		r+="0"+(d.getMonth()+1)+"-";	
	if(d.getDate()<10)
		r+="0"+(d.getDate());
	else
		r+=(d.getDate());
	return r;
}
//获取下周日期
function getNextWeek(){
	var r;
	var d=new Date();
	var t = d.getDate();
	t = t+7;
	d.setDate(t);
	r=d.getFullYear()+"-";
	if(d.getMonth()>=9)
		r+=(d.getMonth()+1)+"-";
	else
		r+="0"+(d.getMonth()+1)+"-";	
	if(d.getDate()<10)
		r+="0"+(d.getDate());
	else
		r+=(d.getDate());
	return r;
}
//取得本月第一天时间
function getCurMonthFirstDay(){
	var date1 = new Date();
	year1 = date1.getFullYear();
	month1 = date1.getMonth(); 
	sMonth = month1+1;
	if(sMonth < 10)
		sMonth = "0" + sMonth;
	sDay = "01";
	var myredate = year1 + "-" + sMonth + "-" + sDay;
	return myredate;
}
//取得上月时间
function getLastMonth(){
	var date1 = new Date();
	var month1 = date1.getMonth();
	date1.setMonth(month1-1);
	month1 = date1.getMonth();
	var date2 = new Date();
	var day2 = date2.getDate();
	var msecs = date2 - date1;
	var days = msecs/24/60/60/1000;
	var year1 = "";
	if(days < day2){
		var tmpmonth = date1.getMonth();
		date1.setMonth(tmpmonth-1);
		date1.setDate(days);
	}
	year1 = date1.getFullYear();
	month1 = date1.getMonth() + 1;
	date1 = date1.getDate();
	var sMonth = month1;
	var sDay = date1;

	if(month1 < 10)
		sMonth = "0" + month1;
	if(date1 < 10)
		sDay = "0" + date1;
	var myredate = year1 + "-" + sMonth + "-" + sDay;
	return myredate;
}
//取得下月时间
function getNextMonth(){
	var today = new Date(); 
	var x=today.getMonth(); 
	x=x+1;
	today.setMonth(x); 
	var month=today.getMonth()+1;
	var day=today.getDate(); 
	if(month<10) sMonth="0"+month; 
	else sMonth = month; 
	if(day<10) sDay="0"+day; 
	else sDay = day; 
	r=today.getFullYear()+"-"+sMonth+"-"+sDay; 
	return r;
}
//取得去年时间
function getLastYear(){
	var today = new Date(); 
	var x=today.getFullYear(); 
	x=x-1;
	today.setYear(x); 
	var month=today.getMonth()+1; 
	var day=today.getDate(); 
	if(month<10) sMonth="0"+month; 
	else sMonth = month; 
	if(day<10) sDay="0"+day; 
	else sDay = day; 
	r=today.getFullYear()+"-"+sMonth+"-"+sDay; 
	return r;
}