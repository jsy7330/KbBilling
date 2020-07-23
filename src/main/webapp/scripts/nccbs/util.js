/*
 * Date Type을 받아 포멧팅된 String(YYYYMMDD)으로 변환
 */
function dateFormatterUsingDateYYYYMMDD(dateValue){
	if (dateValue == null) return '';
	if ( lng == 'ko'){
    	return dateValue.format("yyyy-MM-dd");
    }else if (lng = 'en'){
    	return dateValue.format("MM/dd/yyyy");
    }
}

/*
 * Date Type을 받아 포멧팅된 String(YYYYMMDD)으로 변환
 */
function dateFormatterUsingDateYYYYMMDDHH24MISS(dateValue){
	if (dateValue == null) return '';

	if ( lng == 'ko'){
    	return dateValue.format("yyyy-MM-dd hh:mm:ss");
    }else if (lng = 'en'){
    	return dateValue.format("MM/dd/yyyy hh:mm:ss");
    }
}

/*
 *  포맷팅된 날짜 String을 YYYYMM형식으로 변경
 */
function dateFormatToStringYYYYMM(value) {
	if(value == null || value == '') return '';
    if(lng =='ko'){
    	var yy = value.substr(0,4),
        mm = value.substr(5,2)
    }else if(lng ='en'){
    	var yy = value.substr(3,4),
        mm = value.substr(0,2)
    }
    return yy + mm;
}

/*
 *  포멧팅된 날짜 String을 YYYYMMDD로 변경
 */
function dateFormatToStringYYYYMMDD(value) {
	if(value == null || value == '') return '';
    if(lng =='ko'){
    	var yy = value.substr(0,4),
        mm = value.substr(5,2),
        dd = value.substr(8,2);
    }else if(lng ='en'){
    	var yy = value.substr(6,4),
        mm = value.substr(0,2),
        dd = value.substr(3,2);
    }
    return yy + mm + dd;
}


/*
 *  포멧팅된 날짜 String을 YYYYMMDDHH24MISS로 변경
 */
function dateFormatToStringYYYMMDDHHMISS(value) {
	if(value == null || value == '') return '';
    if(lng =='ko'){
    	var yy = value.substr(0,4),
        mm = value.substr(5,2),
        dd = value.substr(8,2),
        hh = value.substr(11,2),
        mi = value.substr(14,2),
        ss = value.substr(17,2);
    }else if(lng ='en'){
    	var yy = value.substr(6,4),
        mm = value.substr(0,2),
        dd = value.substr(3,2),
        hh = value.substr(11,2),
        mi = value.substr(14,2),
        ss = value.substr(17,2);
    }
    return yy + mm + dd + hh + mi + ss;
}


/*
 * String(YYYYMMDDHH24MISS)을 포멧팅된 날짜 형식의 String으로 변환
 */
function stringToDateformatYYYYMMDDHH24MISS(value) {
	if(value == null || value == '') return '';
	if(!/^(\d){12}$/.test(value)) return "invalid date";

    var yy = value.substr(0,4),
        mm = value.substr(4,2) - 1,
        dd = value.substr(6,2),
        hh = value.substr(8,2),
        mi = value.substr(10,2),
        ss = value.substr(12,2);

    var date = new Date(yy,mm,dd,hh,mi,ss,0);

    if ( lng == 'ko'){
    	return date.format("yyyy-MM-dd hh:mm:ss");
    }else if (lng = 'en'){
    	return date.format("MM/dd/yyyy hh:mm:ss");
    }
}

/*
 * String(YYYYMMDD)을 포멧팅된 날짜 형식의 String으로 변환
 */
function stringToDateformatYYYYMMDD(value) {
	if(value == null || value == '') return '';
	if(!/^(\d){8}$/.test(value)) return "invalid date";

    var yy = value.substr(0,4),
        mm = value.substr(4,2) - 1,
        dd = value.substr(6,2);

    var date = new Date(yy,mm,dd);


    if ( lng == 'ko'){
    	return date.format("yyyy-MM-dd");
    }else if (lng = 'en'){
    	return date.format("MM/dd/yyyy");
    }
}

/*
 * String(YYYYMM)을 포멧팅된 날짜 형식의 String으로 변환
 */
function stringToDateformatYYYYMM(value) {
	if(value == null || value == '') return '';
	if(!/^(\d){6}$/.test(value)) return "invalid date";
	
	var yy = value.substr(0,4);
	var mm = value.substr(4,2) - 1;
	var date = new Date(yy,mm);
	
	if ( lng == 'ko'){
		return date.format("yyyy-MM");
	}else if (lng = 'en'){
		return date.format("MM/yyyy");
	}
}

/*
 *  jqgrid 그리드용 전화번호 Formatting
 */
var telNoFormatter = function(cellValue,options,rowObject) {

	if(cellValue == null || cellValue == ''){
		return '';
	}
	
	var formatNum = '';
	
	var regStr = /^(01[016789\*]{1}|02|0[3-9\*]{1}[0-9\*]{1})?-?([0-9\*]{3,4})-?([0-9\*]{4}$)/;
	
    if(cellValue.length < 9){
    	formatNum = cellValue.replace(regStr, '$2-$3');
    }else{
    	formatNum = cellValue.replace(regStr, '$1-$2-$3');
    }
    
    if(formatNum == null || formatNum == ''){
    	formatNum = cellValue;
    }
    return formatNum;
}

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}


/*
 *  jqgrid 그리드용 long date를 각 언어에 맞게 Formatting(14자리 형식)
 */
var dateTypeFormatterYYYYMMDDHH24MISS = function(cellValue,options,rowObject) {
	var date = new Date(cellValue);

	if(cellValue == null || cellValue == '') return '';
    if (date == undefined || date == null) {
        return '';
    }
    if ( lng == 'ko'){
    	return date.format("yyyy-MM-dd hh:mm:ss");
    }else if (lng = 'en'){
    	return date.format("MM/dd/yyyy hh:mm:ss");
    }
}


/*
 *  jqgrid 그리드용 long date를 각 언어에 맞게 Formatting(8자리 형식)
 */
var dateTypeFormatterYYYYMMDD = function(cellValue,options,rowObject) {
	var date = new Date(cellValue);

	if(cellValue == null || cellValue == '') return '';
	if (date == undefined || date == null) {
        return '';
    }

    if ( lng == 'ko'){
    	return date.format("yyyy-MM-dd");
    }else if (lng = 'en'){
    	return date.format("MM/dd/yyyy");
    }
}

/*
 *  jqgrid 그리드용 long date를 각 언어에 맞게 Formatting(6자리 형식)
 */
var dateTypeFormatterYYYYMM = function(cellValue,options,rowObject) {
	
	var yy = cellValue.substr(0,4);
	var mm = cellValue.substr(4,2) - 1;
	var date = new Date(yy,mm);
	
	if(cellValue == null || cellValue == '') return '';
	if (date == undefined || date == null) {
		return '';
	}
	
	if ( lng == 'ko'){
		return date.format("yyyy-MM");
	}else if (lng = 'en'){
		return date.format("MM/yyyy");
	}
}


/*
 *  jqgrid 그리드용 날짜 String(YYYYMMDDHH24MISS)를 Formatting
 */
var stringTypeFormatterYYYYMMDDHH24MISS = function(cellValue,options,rowObject) {
	if(cellValue == null || cellValue == '') return '';
	if(!/^(\d){14}$/.test(cellValue)) return "invalid date";

    var yy = cellValue.substr(0,4),
        mm = cellValue.substr(4,2) - 1,
        dd = cellValue.substr(6,2),
        hh = cellValue.substr(8,2),
        mi = cellValue.substr(10,2),
        ss = cellValue.substr(12,2);

    var date = new Date(yy,mm,dd,hh,mi,ss,0);

    if ( lng == 'ko'){
    	return date.format("yyyy-MM-dd hh:mm:ss");
    }else if (lng = 'en'){
    	return date.format("MM/dd/yyyy hh:mm:ss");
    }
}

/*
 *  jqgrid 그리드용 날짜 String(YYYYMMDD)를 Formatting
 */
var stringTypeFormatterYYYYMMDD = function(cellValue,options,rowObject) {
	if(cellValue == null || cellValue == '') return '';
	if(!/^(\d){8}$/.test(cellValue)) return "invalid date";

    var yy = cellValue.substr(0,4),
        mm = cellValue.substr(4,2) - 1,
        dd = cellValue.substr(6,2);

    var date = new Date(yy,mm,dd);


    if ( lng == 'ko'){
    	return date.format("yyyy-MM-dd");
    }else if (lng = 'en'){
    	return date.format("MM/dd/yyyy");
    }
}


/*
 *  jqgrid 그리드용 날짜 String(YYYYMM)를 Formatting
 */
var stringTypeFormatterYYYYMM = function(cellValue,options,rowObject) {
	if(cellValue == null || cellValue == '') return '';
	if(!/^(\d){6}$/.test(cellValue)) return "invalid date";

    var yy = cellValue.substr(0,4),
        mm = cellValue.substr(4,2) - 1;

    var date = new Date(yy,mm,01);


    if ( lng == 'ko'){
    	return date.format("yyyy-MM");
    }else if (lng = 'en'){
    	return date.format("MM/yyyy");
    }

}


/*
 *  jqgrid 그리드용 주민번호 Formatting
 */
var corpRegNoFormatter = function(cellValue,options,rowObject) {
	if(cellValue == null || cellValue == '') return '';

	return cellValue.substr(0,6) + '-' + cellValue.substr(6,7);
}

/*
 * 주민번호 자동 Formatter(Input keyup event)
 */
function corpRegNoAutoFormatter(value){

	if (value == null || value.length == 0) return '';
	var str = value.replace(/[^0-9/*]/g, '');
	var str = value.replace(/[^0-9/*]/g, '');

	var tmp = '';	
	if(str.length <= 6){
		tmp = str.substr(0,6);
	}
	else if(str.length > 6){
		tmp = str.substr(0,6) + '-' + str.substr(6,7);
	}
	
	return tmp;
}

/*
 * 13자리 주민번호 String(0000000000000)을 포멧팅된 주민번호 형태(000000-0000000)로 반환
 */
function stringToCorpRegNo(value){
	if(value == null || value == '') return '';
	return value.substr(0,6) + '-' + value.substr(6,7);
	//return value;
}

/*
 *  jqgrid 그리드용 사업자번호 Formatting
 */
var bizRegNoFormatter = function(cellValue,options,rowObject) {
	if(cellValue == null || cellValue == '') return '';

	return cellValue.substr(0,3) + '-' +cellValue.substr(3,2) + '-' + cellValue.substr(5,5);
}

/*
 *  사업자번호 자동 Formatter(Input keyup event)
 */
function bizRegNoAutoFormatter(value){
	
	if (value == null || value.length == 0) return '';
	str = value.replace(/[^0-9/*]/g, '');

	var tmp = '';	
	if(str.length <= 3){
		tmp = str.substr(0,3);
	}
	else if(str.length > 3 && str.length <= 5){
		tmp = str.substr(0,3) + '-' +str.substr(3,2);
	}
	else if(str.length > 5){
		tmp = str.substr(0,3) + '-' +str.substr(3,2) + '-' + str.substr(5,5);
	}
	
	return tmp;
}

/*
 * 10자리 사업자 번호 String(0000000000)을 포멧팅된 사업자번호 형태(00-000-00000)로 반환
 */
function stringToBizRegNo(value){
	if(value == null || value == '') return '';
	return value.substr(0,3) + '-' +value.substr(3,2) + '-' + value.substr(5,5);
}

/*
 *  주민번호, 사업자번호 - 빼고 값 가져오기 
 */
function getRegNo(value) {
	if(value == null || value == '') return '';
    //return value.replace(/\-/g,'');
    return value;
}

/*
 * 전화번호 자동 Formatter(Input keyup event)
 */
function telNoAutoFormatter(value){
	if (value == null || value.length == 0) return '';
	
	str = value.replace(/[^0-9/*]/g, '');
	var tmp = '';

	if(str.length > 11){
		return str;
	}

	var regStr = /^(01[016789\*]{1}|02|0[3-9\*]{1}[0-9\*]{1})?-?([0-9\*]{3,4})-?([0-9\*]{4}$)/;
	
    if(str.length < 9){
    	tmp = str.replace(regStr, '$2-$3');
    }else{
    	tmp = str.replace(regStr, '$1-$2-$3');
    }
    
    if(tmp == null || tmp == ''){
    	tmp = str;
    }
    
	return tmp;
}


/*
 * String을 포멧팅된 전화번호 형태로 반환 
 */
function stringToTelNo(value){
	if(value == null){
		return '';
	}

	var formatNum = '';

    if(value.length==11){
    	formatNum = value.replace(/(\d{3}|[*]{3})(\d{4}|[*]{4})(\d{4}|[*]{4})/, '$1-$2-$3');
    }else if(value.length==8){
        formatNum = value.replace(/(\d{4}|[*]{4})(\d{4}|[*]{4})/, '$1-$2');
    }else{
        if(value.indexOf('02')==0){
        	formatNum = value.replace(/(\d{2}|[*]{2})(\d{4}|[*]{4})(\d{4}|[*]{4})/, '$1-$2-$3');
        }else{
        	formatNum = value.replace(/(\d{3}|[*]{3})(\d{3}|[*]{3})(\d{4}|[*]{4})/, '$1-$2-$3');
        }
    }
    return formatNum;
}

/*
 * 문자열 입력 길이로 변경
 *  str : 변경 문자열
 *  maxLength :최대값
 *
 * 문자열과 최대길이를 받아, 최대길이 초과시에 최대 길이 허용만큼만 문자열 반환(UTF-8 기준)
 */
function getMaxStr(str, maxLength, separator){
	if (str == null || str.length == 0) return str;
	
	if( typeof(separator) != "undefined" && separator != null && separator != "" ){
		var reg = eval("/" +separator+ "/g");
		str = str.replace(reg, "");
	}
	
	var stringByteLength = (function(s,b,i,c){
	    for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
	    return b
	})(str);

	if(stringByteLength > maxLength){
		var i,size = 0;
		var charCode,chr = null;
		var index = 0;
		for(i=0;i<str.length;i++)
		{
			chr = str.charAt(i);
			charCode = chr.charCodeAt(0);
			if (charCode <= 0x00007F) size += 1; else
			if (charCode <= 0x0007FF) size += 2; else
			if (charCode <= 0x00FFFF) size += 3;
			else size += 4;

			if(size > maxLength){
				index = i;
				break;
			}
		}
		return str.substr(0,index);

	}else{
		return str;
	}
}




/*
 * 문자열 입력 길이 반환(UTF-8)
 */
function getStrLength(str){
	if (str == null || str.length == 0) return 0;
	var stringByteLength = (function(s,b,i,c){
	    for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
	    return b
	})(str);
	return stringByteLength;
}

/*
 * 이메일 체크
 */
function checkEmailStr(str){
	if(str == null || str.length == 0){
		return false;
	}
	var regExp = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return regExp.test(str);

}


/*
 *  전화번호 formatting
 */
function getTelNo(value) {
	if(value == null || value == '') return '';
    //return value.replace(/\-/g,'');
    return value.replace(/\-/g,'');
}

/*
 *  null을 Stirng으로 변경
 */
function getNullToString(value) {
	if(value == null || value == '') return '';
	else return value;
}

//바이트 길이 체크후 값 리턴
function byteCheckKeyEvent(){

	$("[checkbyte]").each(function(index, tag){
		//키이벤트
		$("#"+tag.id).keyup(function(){
			var str = $("#"+tag.id).val();
			var len = $("#"+tag.id).attr('checkbyte');
			var count = 0;
			for (var i=0; i<str.length; i++) {
				count += (str.charCodeAt(i) > 128) ? 2 : 1;
				if (count > len) return $("#"+tag.id).val(str.substring(0,i));  ;
			}
		});
	});
}

//달력셋팅
function settingDatepicker(){
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
			changeMonth: true,
			changeYear: true,
			regional:lng
		});
	}
	$(".inp_date .btn_cal").click(function(e){e.preventDefault();$(this).prev().focus();});
	$(".datepicker.disabled").datepicker( "option", "disabled", true );
}

//모달팝업
function openModalPopup(url, param) {

	$.ajax({
		type : "post",
		url : url,
		data : param,
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").html(html);
		},
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
		},
 		error : function( err ){
			ajaxErrorCallback( err );
		}
	});

}

jQuery.download = function(url, data, method){
    // url과 data를 입력받음
    if( url && data ){
        // data 는  string 또는 array/object 를 파라미터로 받는다.
        data = typeof data == 'string' ? data : jQuery.param(data);
        // 파라미터를 form의  input으로 만든다.
        var inputs = '';
        jQuery.each(data.split('&'), function(){
            var pair = this.split('=');
            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />';
        });
        // request를 보낸다.
        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>').appendTo('body').submit().remove();
    };
};

/*
 *  null 또는 공백 인지 반환
 */
function isEmpty( value ){

	if( value == null || value == "" ){
		return true;
	}
	else{
		return false;
	}
}

/*
 *  number 자동 Formatting(천단위 "," 추가)(Input keyup event)
 */
function numberAutoFormatter( value ){
	
	if( value == null || value.length == 0 ){
		return "";
	}
	
	var number = "";
	
	if( value.toString().indexOf("-") == 0 ){
		number = "-" + value.toString().substr(1).replace(/[^0-9]/g, "");
	}
	else{
		number = value.toString().replace(/[^0-9]/g, "");
	}
	
	return number.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

/*
 * jqgrid용  number 자동 Formatting(천단위 "," 추가)(Input keyup event)
 */
var numberAutoFormatter = function (cellValue,options,rowObject) {
	
	if( cellValue == null || cellValue.length == 0 ){
		return "";
	}
	
	var number = "";
	
	if( cellValue.toString().indexOf("-") == 0 ){
		number = "-" + cellValue.toString().substr(1).replace(/[^0-9]/g, "");
	}
	else{
		number = cellValue.toString().replace(/[^0-9]/g, "");
	}
	
	return number.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

/*
 * number 자동 Formatting(천단위 "," 추가) + 길이체크
 * obj : object
 * max : 최대 길이
 */
function numberAutoFormatterAndMaxLength( obj, max ){
	var value = obj.val();
	if( value == null || value.length == 0 ){
		return "";
	}
	
	var str = getMaxStr(value, max, ",");
	if(str != value){
		obj.val(str);
	}
	
	obj.val( numberAutoFormatter(str) );
}

/*
숫자만 입력 + 길이 체크
- 입력 항목에 숫자만 입력가능. 최대 길이 이상 입력하지 못하도록 함
id : element id
event : key event
max : 최대 길이
*/
function onlyNumber(id, event, max){
	var obj = $('#' + id);
	if (!(event.keyCode >=37 && event.keyCode<=40)) {
		var inputVal = obj.val();
		obj.val(inputVal.replace(/[^0-9]/gi,''));
		var str = getMaxStr(inputVal, max);
  	    if(str != inputVal){
  	    	obj.val(str);
  	    }
	}
}

/*
 길이 체크 
 - 입력 항목에 최대 길이 이상 입력하지 못하도록 함 
 id : element id
 max : 최대 길이
*/ 
function lengthCheck(id, max){
	var obj = $('#' + id);
	var inputVal = obj.val();
	var str = getMaxStr(inputVal, max);
    if(str != inputVal){
    	obj.val(str);
    }
}

/*
숫자만 입력 + 길이 체크 Grid
- 입력 항목에 숫자만 입력가능. 최대 길이 이상 입력하지 못하도록 함
obj : grid object
event : key event
max : 최대 길이
*/
function onlyNumberGrid(obj, event, max){
	if (!(event.keyCode >=37 && event.keyCode<=40)) {
		var inputVal = obj.val();
		obj.val(inputVal.replace(/[^0-9]/gi,''));
		var str = getMaxStr(inputVal, max);
  	    if(str != inputVal){
  	    	obj.val(str);
  	    }
	}
}

/*
길이 체크 Grid 
- 입력 항목에 최대 길이 이상 입력하지 못하도록 함 
obj : grid object
max : 최대 길이
*/ 
function lengthCheckGrid(obj, max){
	var inputVal = obj.val();
	var str = getMaxStr(inputVal, max);
	    if(str != inputVal){
	    	obj.val(str);
	    }
}

/*
 *  년월일(YYYYMMDD) 자동 Formatting(Input keyup event)
 */
function autoFormatterYYYYMMDD( value ){
	
	if( value == null || value.length == 0 ){
		return "";
	}
	
	var sValue = getMaxStr(value, 8, "-");
	var sDate = "";
//	console.log( "sValue = " +sValue );
	
	if( sValue.length == 8 ){
		sDate = stringToDateformatYYYYMMDD( sValue );
	}
	else{
		sDate = sValue;
	}
	
	return sDate;
}

/*
 *  년월(YYYYMM) 자동 Formatting(Input keyup event)
 */
function autoFormatterYYYYMM( value ){
	
	if( value == null || value.length == 0 ){
		return "";
	}
	
	var sValue = getMaxStr(value, 6, "-");
	var sDate = "";
//	console.log( "sValue = " +sValue );
	
	if( sValue.length == 6 ){
		sDate = stringToDateformatYYYYMM( sValue );
	}
	else{
		sDate = sValue;
	}
	
	return sDate;
}

/*
 * 해당 날짜의 지정된 일/주/월 의 이전/이후 날짜를 반환
 */
function getGapDate( sDate, sDivi, nGap ){
	var nYear = parseInt( sDate.substr(0, 4) );	//기준년
	var nMonth = parseInt( sDate.substr(4, 2) );	//기준월
	var nDay = parseInt( sDate.substr(6, 2) );	//기준일
	var oToday  = new Date( nYear, nMonth - 1, nDay );
	var sCalDate = "";	//결과
//	console.log( "getGapDate() : sDate = " +sDate+ ", sDivi = " +sDivi+ ", nGap = " +nGap );
//	console.log( "getGapDate() : 지정된 year = " +oToday.getFullYear()+ ", month = " +(oToday.getMonth() + 1)+ ", day = " +oToday.getDate() );

	// 일
	if( sDivi == "D" ){
		oToday.setDate(oToday.getDate() + nGap);
//		console.log( "getGapDate() : " +nGap+ "일 year = " +oToday.getFullYear()+ ", month = " +(oToday.getMonth() + 1)+ ", day = " +oToday.getDate() );
	}
	// 주
	else if( sDivi == "W" ){
		oToday.setDate(oToday.getDate() + (nGap * 7));
//		console.log( "getGapDate() : " +nGap+ "주 year = " +oToday.getFullYear()+ ", month = " +(oToday.getMonth() + 1)+ ", day = " +oToday.getDate() );
	}
	// 월
	else if( sDivi == "M" ){
		var nSum = nMonth + nGap;
		var nDivi = nSum / 12;
		var nRemain = nSum % 12;	//나머지
		var nInteger = Math.abs( Math.floor( nDivi ) );	//정수만 추출
		var nCalYear = nYear;	//계산된 년
		var nCalMonth = nSum;	//계산된 월
		var nCalDay = 0;	//계산된 일
		var nLastDay = 0;	//추출된 말일

		// 양수면(이후면)
		if( nGap > 0 ){
			
			if( nDivi > 1 ){
				nCalYear = nRemain == 0 ? nYear + nInteger - 1 : nYear + nInteger;
				nCalMonth = (nSum - ( 12 * nInteger ));
				nCalMonth = nCalMonth == 0 ? 12 : nCalMonth;
			}
		}
		// 음수면(이전이면)
		else{
			
			// 기준월에 nGap만큼 뺀것이 0이면 항상 12월임
			if( nSum == 0 ){
				nCalYear = nYear - 1;
				nCalMonth = 12;
			}
			
			if( nInteger > 0 ){
				nCalYear = nRemain == 0 ? nYear - nInteger - 1 : nYear - nInteger;
				nCalMonth = 12 + (nSum + (12 * (nInteger - 1)));
				nCalMonth = nCalMonth == 0 ? 12 : nCalMonth;
			}
		}

		oToday = new Date( nCalYear, nCalMonth - 1, 1 );
		nLastDay = 32 - ( new Date(oToday.getFullYear(), oToday.getMonth(), 32).getDate() );	//해당월 말일 추출
		nCalDay = nDay > nLastDay ? nLastDay : nDay;
//		console.log( "getGapDate() : nCalYear = " +nCalYear+ ", nCalMonth = " +nCalMonth+ ", nCalDay = " +nCalDay+ ", nLastDay = " +nLastDay );
		
		// 1을 더 빼줌
		oToday = new Date( nCalYear, nCalMonth - 1, nCalDay );
		oToday.setDate(oToday.getDate() - 1);
		nCalYear = oToday.getFullYear();
		nCalMonth = oToday.getMonth() + 1;
		nCalDay = oToday.getDate();
//		console.log( "getGapDate() : nCalYear = " +nCalYear+ ", nCalMonth = " +nCalMonth+ ", nCalDay = " +nCalDay );
	}

	var sYear = "";
	var sMonth = "";
	var sDay = "";
	
	if( sDivi == "M" ){
		sYear = "" +nCalYear;
		sMonth = nCalMonth.toString().length==2 ? nCalMonth : "0" + nCalMonth;
		sDay = nCalDay.toString().length==2 ? nCalDay : "0" + nCalDay;
	}
	else{
		sYear = "" +oToday.getFullYear();
		sMonth = oToday.getMonth() + 1;
		sMonth = sMonth.toString().length==2 ? sMonth : "0" + sMonth;
		sDay = oToday.getDate().toString().length==2 ? oToday.getDate() : "0" + oToday.getDate();
	}

//	console.log( "getGapDate() : nGap = " +nGap+ ", sYear = " +sYear+ ", sMonth = " +sMonth+ ", sDay = " +sDay );
	sCalDate = sYear+ "" +sMonth+ "" +sDay;
	
	return sCalDate;
}


// 그리드Row 단일 이동 - 선택한 from 그리드 Row를 to 그리드로 이동 (2016.11.17  YSLEE)
// parameter 
//		from : Row 넘길 그리드ID
//		to		  : Row 받을 그리드ID 
function gridMoveSingle(from, to){
	var selectedRowId = $('#' + from).jqGrid('getGridParam','selrow');
	var selOrgListCnt = $('#' + to).getGridParam("records");
	
	if(selectedRowId == null){
		return;
	}
	
	$('#' + to).addRowData(selOrgListCnt+1,$('#' + from).getRowData(selectedRowId));
	$('#' + from).delRowData(selectedRowId);
}

// 그리드Row 전체 이동 - from 그리드 전체 Row를 to그리드로 이동 (2016.11.17  YSLEE)
// parameter 
//		from : Row 넘길 그리드ID
//		to	 	  : Row 받을 그리드ID 
function gridMoveAll(from, to){
	var selOrgListCnt = $('#' + to).getGridParam("records");
	var allOrgListCnt = $('#' + from).getGridParam("records");
	
	if(allOrgListCnt == 0){
		return;
	}
		
	var rowList = $('#' + from).getRowData();
	var setRow = 1;
	var ids = $('#' + from).jqGrid('getDataIDs');

	for (var i = 0; i < ids.length; i++) {
	    var rowId = ids[i];
	    $('#' + to).addRowData(parseInt(selOrgListCnt) + parseInt(setRow),$('#' + from).getRowData(rowId));
	    $('#' + from).delRowData(rowId);
 		setRow++;
	}
}

//월달력세팅
function settingMonthpicker() { 
	if($(".monthpicker").length > 0){
		if(lng == 'ko'){
			format = 'yy-mm';
		}else if (lng == 'en'){
			format = 'mm/yy';
		}
		
		$(".monthpicker").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: format,
        showButtonPanel: true,
        
        onClose: function(dateText, inst) {
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            
            	$(this).val($.datepicker.formatDate(format, new Date(year, month, 1)));
        }
    });
	$(".monthpicker").blur(function() {
		$(this).val('');
	});

    $(".monthpicker").focus(function () {
        $(".ui-datepicker-calendar").hide();
        $("#ui-datepicker-div").position({
            my: "center top",
            at: "center bottom",
            of: $(this)
        });
    });
	}
	$(".inp_date .btn_cal").click(function(e){e.preventDefault();$(this).prev().focus();});
}

/*
 *  jqgrid 그리드용 숫자 콤마찍기
 */
var stringTypeFormatComma = function(cellValue,options,rowObject) {
	if(cellValue == null || cellValue == '') return '';
	
	var input  = cellValue;
	var output = "";
	
	for(var i=input.length; i>=0; i--){
		if((input.length-i)%3==1 && output.length!=0 && input.charAt(i) != "-"){
			 output = "," + output;
		}
		output = input.charAt(i) + output;
	}
	return output;
} 