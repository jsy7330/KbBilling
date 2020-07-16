<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	

	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';

	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$( ".date" ).datepicker();
	var date1=new Date();
	date1.setDate( date1.getDate() + 1 );
	reault_y = date1.getFullYear();
	reault_m = eval(date1.getMonth()+1);
	reault_d = date1.getDate();
	if(parseInt(reault_m)<10){reault_m="0"+reault_m;}
	if(parseInt(reault_d)<10){reault_d="0"+reault_d;}

	var date2 = null;	

	if(lng=='ko'){
		date2 = reault_y + "-" + reault_m + "-" + reault_d;	
	}else {
		date2 =  reault_m + "-" + reault_d + "-" +reault_y; 
	}

	$("#insActDt").val(date2);
	$("#nowDate").val(date2);


	//jqgrid 호출
	initGrid();	

	// 셀렉트 박스 값 가져오기
	var ifSys = "${attribute.ifSys}";
	 $('#ifSys').val(ifSys);
	 $('#ifSys').selectmenu("refresh");

	//조회
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	//등록 페이지 이동
	$("#btn_add").click(function() {
		goInsertPage();
	});

	//속성 등록 처리
	$("#btn_insert").click(function() {
		goInsert();
	});

	//속성 수정 처리
	$("#btn_update").click(function() {
		goUpdate();
	});	

	//참조코드 팝업
	$("#btn_popUp").click(function() {
		var param = new Object();
		var url="commListPopUp.ajax";
		openModal(url, param);
	});

	//삭제처리
	$("#btn_delete").click(function() {
		if (!confirm('<spring:message code="MSG.M07.MSG00052" />')){
			return;
		}

		var attribute = new Object();
		attribute.actDt = dateFormatToStringYYYYMMDD($('#nowDate').val());
		attribute.inactDt = dateFormatToStringYYYYMMDD($('#inactDt').val());
		attribute.oldActDt = dateFormatToStringYYYYMMDD($('#oldActDt').val());
		attribute.attrNm = $("#attrNm").val();
		attribute.attrStrtVal = $("#attrStrtVal").val();
		attribute.attrEndVal = $("#attrEndVal").val();
		attribute.ifSys = $("#ifSys").val();
		attribute.remark = $("#remark").val();
		attribute.refCd = $("#refCd").val();
		attribute.attrCd = $("#attrCd").val();

		console.log(attribute);
		var url = '/product/refInfo/commonInfo/attribute/deleteAction.json';
		$.ajax({
			type : "POST",
			url : url,
			data : JSON.stringify(attribute),
			dataType: 'json',
			contentType : "application/json; charset=UTF-8",
			success : function(data) {
				if(data.result != "0"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
					window.location.reload();
				}

			},		
			beforeSend: function(data){
			},
			error : function(err){
				ajaxErrorCallback(err);
			}
		});


	});

	$(window).resize(function() {
		$("#attributeTable").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function goInsert() {

	if($("#attrCd").val() != "") {
		alert("<spring:message code="MSG.M07.MSG00090"/>");
		return;
	}
	if($("#attrNm").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00086"/>");
		$("#attrNm").focus();
		return;
	}
	/*
	if($("#attrStrtVal").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00122"/>");
		$("#attrStrtVal").focus();
		return;
	}
	if($("#attrEndVal").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00042"/>");
		$("#attrEndVal").focus();
		return;
	}
	*/
	if($("#ifSys").val() == "") {
		alert("<spring:message code="MSG.M08.MSG00012" />"); 
		$("#ifSys").focus();
		return;
	}
	/*
	if($("#refCd").val() == "") {
		alert("<spring:message code="MSG.M10.MSG00003"/>");
		return;
	}
	*/
	if($("#actDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00011"/>");
		return;
	}
	if($("#inactDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00013"/>");
		return;
	}

	if($("#actDt").val() >= $("#nowDate").val() ) {
		alert("<spring:message code="MSG.M09.MSG00010"/>");
		return;
	}

	var attribute = new Object();
	attribute.actDt = dateFormatToStringYYYYMMDD($('#nowDate').val());
	attribute.inactDt = dateFormatToStringYYYYMMDD($('#inactDt').val());
	attribute.attrNm = $("#attrNm").val();
	attribute.attrStrtVal = $("#attrStrtVal").val();
	attribute.attrEndVal = $("#attrEndVal").val();
	attribute.ifSys = $("#ifSys").val();
	attribute.remark = $("#remark").val();
	attribute.refCd = $("#refCd").val();


	console.log(attribute);
	var url = '/product/refInfo/commonInfo/attribute/attributeInsertAction.json';
	$.ajax({
		type : "POST",
		url : url,
		data : JSON.stringify(attribute),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			if(data.result != "0"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				window.location.reload();
			}

		},		
		beforeSend: function(data){
   		},
		error : function(err){
     		ajaxErrorCallback(err);
     	}
	}); 
}

function goUpdate() {

	if($("#attrCd").val() == "") {
		alert("<spring:message code="MSG.M03.MSG00026"/>");
		return;
	}
	if($("#attrNm").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00086"/>");
		$("#attrNm").focus();
		return;
	}
	/*
	if($("#attrStrtVal").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00122"/>");
		$("#attrStrtVal").focus();
		return;
	}
	if($("#attrEndVal").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00042"/>");
		$("#attrEndVal").focus();
		return;
	}
	*/
	if($("#ifSys").val() == "") {
		alert("<spring:message code="MSG.M08.MSG00012" />"); 
		$("#ifSys").focus();
		return;
	}
	/*
	if($("#refCd").val() == "") {
		alert("<spring:message code="MSG.M10.MSG00003"/>");
		return;
	}
	*/

	if($("#actDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00011"/>");
		return;
	}	

	if(dateFormatToStringYYYYMMDD($("#actDt").val()) > dateFormatToStringYYYYMMDD($("#nowDate").val()) ) {
		alert("<spring:message code="MSG.M09.MSG00010"/>");
		return;
	}	
	if($("#inactDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00013"/>");
		return;
	}

	var attribute = new Object();
	attribute.actDt = dateFormatToStringYYYYMMDD($('#nowDate').val());
	attribute.inactDt = dateFormatToStringYYYYMMDD($('#inactDt').val());
	attribute.oldActDt = dateFormatToStringYYYYMMDD($('#oldActDt').val());
	attribute.attrCd = $("#attrCd").val();
	attribute.attrNm = $("#attrNm").val();
	attribute.attrStrtVal = $("#attrStrtVal").val();
	attribute.attrEndVal = $("#attrEndVal").val();
	attribute.ifSys = $("#ifSys").val();
	attribute.remark = $("#remark").val();
	attribute.refCd = $("#refCd").val();


	console.log(attribute);
	var url = '/product/refInfo/commonInfo/attribute/attributeUpdateAction.json';
	$.ajax({
		type : "POST",
		url : url,
		data : JSON.stringify(attribute),
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		success : function(data) {
			if(data.result != "0"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				window.location.reload();
			}

		},		
		beforeSend: function(data){
   		},
		error : function(err){
     		ajaxErrorCallback(err);
     	}
	});


}

function getParam(){
	var param = new Object();	
	param.searchAttrNm =  $("#searchAttrNm").val();
	return param;
}

function initGrid() {

		var param = new Object();
		param.searchAttrNm =  $("#searchAttrNm").val();
		$("#attributeTable").jqGrid({
			
		   	url:'attributeListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
		   	colModel: [ 
	   			{ label: 'ifSys' , name: 'ifSys', hidden:true,width : 0},
	   			{ label: 'remark' , name: 'remark', hidden:true,width : 0},
	   		    { label: '<spring:message code="LAB.M07.LAB00232" />', name: 'attrCd', width : 100},			//속성 코드
	   		 	{ label: '<spring:message code="LAB.M07.LAB00220" />', name: 'attrNm', width : 100},		//속성명
	   			{ label: '<spring:message code="LAB.M08.LAB00020" />', name: 'ifSysNm', width : 100},		//연동구분
	   			{ label: '<spring:message code="LAB.M10.LAB00013" />', name: 'refCd', width : 100},			//참조코드
	   			{ label: '<spring:message code="LAB.M10.LAB00014" />', name: 'refCdNm', width : 100},			//참조코드명
	   			{ label: '<spring:message code="LAB.M07.LAB00286" />', name: 'attrStrtVal', width : 100},	//시작값
	   			{ label: '<spring:message code="LAB.M09.LAB00166" />', name: 'attrEndVal', width : 100},	//종료값
	   			{ label: '<spring:message code="LAB.M09.LAB00052" />', name: 'actDt', formatter:stringTypeFormatterYYYYMMDD, align:"center", width : 100},		//시작일	   			
				{ label: '<spring:message code="LAB.M09.LAB00058" />', name: 'inactDt', formatter:stringTypeFormatterYYYYMMDD, align:"center", width : 100}//종료일

	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#pager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 240,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        	setSelectedDate(rowId);
	        },
	        loadComplete: function(obj){
	        	$("#attributeTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        },
			loadError: function (jqXHR, textStatus, errorThrown) {
				ajaxErrorCallback(jqXHR);
				
			},
			sortable: { update: function(permutation) {
				$("#attributeTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
				}
			}
	});

		$("#attributeTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#attributeTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});

}

	//상세정보 
	function setSelectedDate(rowId){		
		var data = $("#attributeTable").getRowData(rowId);

		$("#oldActDt").val(data.actDt);
		$("#actDt").val(data.actDt);
		$("#inactDtt").val(data.inactDt);
		$("#attrCd").val(data.attrCd);
		$("#attrNm").val(data.attrNm);
		$("#ifSysNm").val(data.ifSysNm);
		$("#refCdNm").val(data.refCdNm);
		$("#attrStrtVal").val(data.attrStrtVal);
		$("#attrEndVal").val(data.attrEndVal);
		$("#ifSys").val(data.ifSys);
		$("#ifSys").selectmenu("refresh");
		$("#refCd").val(data.refCd);
		$("#remark").val(data.remark);		
	}

function goSearch() {
	var param = new Object();
	param.page =  $("#page").val();
	param.perPage =  $("#perPage").val();
	param.searchAttrNm =  $("#searchAttrNm").val();	
	$("#attribute").attr("action", "/product/refInfo/commonInfo/attribute/attributeList");
	$("#attribute").submit();
}
/*
	//그리드 재검색 
	function goSearch(){
		
		$("#attribute").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.searchAttrNm =  $("#searchAttrNm").val();	
        $("#attribute").setGridParam({ postData: param }).trigger("reloadGrid");

	}
*/
function goInsertPage(){
	var param = new Object();
	$("#attribute").attr("action", "/product/refInfo/commonInfo/attribute/attributeInsert");
	$("#attribute").submit();
}


//공통 코드 조회 POPUP에서 검색 했을 경우 POPUP을 닫고 조회 결과를 다시 POPUP으로 Open
function openPop(commonGrpNm){
	var param = new Object();
	param.commonGrpNm =  commonGrpNm;
	var url="commListPopUp.ajax";
	openModal(url, param);
}

function closeModal() {
	$("#popup_dialog").remove();
}

//팝업창 modal로 열기
function openModal(url, param) {
	$.ajax({
		type : "post",
		url : url,
		data : param,
		async: true,
		success : function(data) {
			var html = data;
			console.log("data = " + data)
			$('body').append("<div id=\"popup_dialog\"></div>");			
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			var ewidth = $("#popup_dialog .pop_layer").width();
			var eheight = $("#popup_dialog .pop_layer").height();			
			$("#popup_dialog .pop_layer").css({"top":"0" , "left":"0"});	
	  		$('#popup_dialog').dialog({				
				height: 600 ,
				width: 600,
				modal: true,
				resizable:true,				
			}); 			 
		}
	}); 
}   

function init() {
	//화면 처음 들어 왔을 때 입력부분 비활성화 처리
	$("#attrNm").val("");
	$("#attrStrtVal").val("");
	$("#attrEndVal").val("");
	$("#mstrFl").val("");
	$('#ifSys').val("");
	$('#ifSys').selectmenu("refresh");
	$("#refCdNm").val("");			
	$("#actDt").val("");
	$("#inactDt").val("");
	$("#refCd").val("");
	$("#remark").val("");
	$("#attrCd").val("");
	$("#oldActDt").val("");
	
} 

//-->
</script>

<!--NaviGator-->
<div class="h3_bg">
	<h3>${menuName}</h3>
	<!-- Navigator -->
		<div class="nav">                                        
		   <a href="#" class="home"></a>
			<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
			<span>&gt;</span>${mu.menuName}
		</c:forEach>
		</div>                               
   <!--// Navigator -->
</div>

<!--검색-->	
<form id="attribute" name="attribute" method="post">
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>

	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M07.LAB00231"/></h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:90%;">
		</colgroup>
		 <thead> 
			   <tr>
					<th><spring:message code="LAB.M07.LAB00220"/></th>
					<td>
						 <input id="searchAttrNm" name="searchAttrNm" value= "${param.searchAttrNm}" type="text"  class="w100p" >
					</td>
				</tr>
		</thead>
	</table> 
</form>	

<!--JQ Grid 리스트-->	
<div class="main_btn_box">
	<h4 class="sub_title"><spring:message code="LAB.M07.LAB00231"/></h4>
</div>
<div id='gridDiv'>
<table id="attributeTable" class="w100p"></table>
	<div id="pager2"></div>
</div>

<!--등록 수정-->	

	<input id="attrCd" name="attrCd" value="${attribute.attrCd}" type="hidden">
	<input id="oldActDt" name="oldActDt"  type="hidden">
   <fmt:parseDate value="${attribute.actDt}" pattern="${dateFormat4}" var="nowDate"/>
   <input  type="hidden"  id="nowDate" name="nowDate" value="<fmt:formatDate value="<%= new java.util.Date() %>" pattern="${dateToStrFormat4}"/>">
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>
        
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00229"/></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<tbody> 
			<tr>
				<!--속성명-->
				<th><spring:message code="LAB.M07.LAB00220"/><span class="dot">*</span></th>
				<td colspan="3">
					<input id="attrNm" name="attrNm" value="${attribute.attrNm}" type="text" class="w270">
				</td>
			</tr>
			<tr>	
				<!--시작값-->
			   <th><spring:message code="LAB.M07.LAB00286" /></th>
			   <td>
				  <input id="attrStrtVal" name="attrStrtVal" value="${attribute.attrStrtVal}" type="text"  class="w270">
			   </td>
				<!--종료값-->
			   <th><spring:message code="LAB.M09.LAB00166" /></th>
			   <td>
				  <input id="attrEndVal" name="attrEndVal" value="${attribute.attrEndVal}" type="text"  class="w270">
			   </td>
			</tr>
			<tr>
				<!--참조코드-->
				<th><spring:message code="LAB.M10.LAB00013" /></th>
				<td>
					<div class="date_box">
						<div class="inp_date w220">
							<input id="refCdNm" name="refCdNm" value="${attribute.refCdNm}" type="text" class="w180 not-active" disable>
							<input id="refCd" name="refCd" value="${attribute.refCd}" type="hidden" class="w180" > 
							<a href="#" id="btn_popUp" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>		
						</div>	
					</div>
				</td>
				<!--연동구분 PP00111-->
			   <th><spring:message code="LAB.M08.LAB00020" /><span class="dot">*</span></th>
			   <td>
					<select id="ifSys" name="ifSys" class="w270">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
						<c:forEach items="${listIfSys}" var="listIfSys" varStatus="status">
						<option value="${listIfSys.commonCd}">${listIfSys.commonCdNm}</option>
						</c:forEach>
					</select>
			   </td>
			</tr>
			<tr>
				<!--속성설명-->
			   <th><spring:message code="LAB.M07.LAB00221" /></th>
			   <td colspan="3">
				  <textarea id="remark" name="remark" class="w100p h100">${attribute.remark}</textarea>
			   </td>
			</tr>
			<tr>
				<!--적용시작일-->
			   <th><spring:message code="LAB.M09.LAB00052" /><span class="dot">*</span></th>
			   <td>
			   <div class="date_box">
				   <div class="inp_date w135">
				  <input  type="text"  id="actDt" name="actDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
					</div>
				</div>
			   </td>
				<!--적용종료일-->
			   <th><spring:message code="LAB.M09.LAB00058" /></th>
			   <td>
			   <div class="date_box">
			   <div class="inp_date w135">
				  <fmt:parseDate value="99991231" var="inactDt" pattern="${dateFormat4}"/>
				  <input  type="text" id="inactDt" name="inactDt" value="<fmt:formatDate value="${inactDt}" pattern="${dateToStrFormat4}" />" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
				</div>
				</div>
			   </td>
			</tr>
		</tbody>
	</table> 



<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">
		<!--초기화-->		
		<a class="grey-btn"  href="javascript:init();"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
		<!--등록-->
		<ntels:auth auth="${menuAuthC}">
		<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>		
		<!--수정-->
		<ntels:auth auth="${menuAuthU}">
		<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		<!--삭제-->
		<ntels:auth auth="${menuAuthD}">
		<a class="grey-btn" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
		<!--출력-->
		<ntels:auth auth="${menuAuthP}">
		<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth>
	
	</div>
  </div>
</div>
<div id="pop_layer" style="height:600px;width:500px;overflow-x:none;overflow-y:auto;display:none;"></div>       



