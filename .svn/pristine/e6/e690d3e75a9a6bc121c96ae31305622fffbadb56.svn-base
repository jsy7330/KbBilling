<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- amchart -->
<script src="/scripts/amcharts_2.4.2/amcharts.js" type="text/javascript"></script>
<script src="/scripts/amcharts_2.4.2/serial.js" type="text/javascript"></script> 
<script type="text/javascript">


$(document).ready(function() {
	

	var lng = '<%= session.getAttribute("sessionLanguage")%>';
	
	initGrid(lng);
	
	
	//속성 등록 처리
	$("#btn_insert").click(function() {
		
	});
	
	$('#btn_update').click(function() {
		goProcess("U");
	});
	
	$('#btn_delete').click(function() {
		goProcess("D");
	});
	
	$(window).resize(function() {
		$("#manageRatingDefTable").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
});

function initGrid(lng) {
	$("#manageRatingDefTable").jqGrid({
		
		//url: 'manageRateingDefListAction.json?',
		mtype: "POST",
		datatype: "json",
		postData: {},
		colModel: [ 
   			{ label: 'ifSys' , name: 'ifSys', hidden:true, width : 0 },
   			{ label: 'remark' , name: 'remark', hidden:true, width : 0 },
   		    /*{ label: '시간대코드', name: 'chrg_cd', width : 240},			//속성 코드
   		 	{ label: '시간대명', name: 'description', width : 200},		//속성명
   			{ label: '우선순위	', name: 'usg_typ', width : 100},*/		//연동구분
   			{ label: 'Time Period Code', name: 'chrg_cd', width : 240},			//속성 코드
   		 	{ label: 'Time Period Name', name: 'description', width : 200},		//속성명
   			{ label: 'Priority', name: 'usg_typ', width : 100},
   		],
   		multiselect: true,
		rowNum: 10,
		rowList: [ 5, 10, 15, 20, 25 ],
		pager: '#pager2',
		sortable: true,
		sortorder: "desc",
		viewrecords: true,
		height: 370,
		onCellStart: function(rowid, index, contents, event) {
			btnActive("btn_update");
			btnActive("btn_delete");
			setSelectedData(rowId)
		},
		loadComplete: function(obj) {
			$("#manageRatingDefTable").setGridWidth($('#gridDiv').width(),false);
			if( $("#manageRatingDefTable").getGridParam("reccount") > 0 ) {
				btnActive("btn_init");
				btnNonActive("btn_update");
				btnNonActive("btn_delete");
				btnNonActive("btn_delete");
			}
			else {
				btnActive("btn_init");
				btnNonActive("btn_insert");
				btnNonActive("btn_update");
				btnNonActive("btn_delete");
			}
		}
	});

	$(window).resize(function() {
		$("#manageRatingDefTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

function init() {
	$("#chrg_cd").val('');
	$("#description").val('');
	$("#usg_typ").val('');
	$("#usg_typ_nm").val('');
}

function setSelectedData(rowId) {
	var data = $("#manageRatingDefTable").getRowData(rowId);
	
	$("#chrg_cd").val(data.chrg_cd);
	$("#chrg_cd").selectmenu("refresh");
	$("#description").val(data.description);
	$("#description").selectmenu("refresh");
	$("#usg_typ").val(data.usg_typ);
	$("#usg_typ").selectmenu("refresh");
	$("#usg_typ_nm").val(data.usg_typ_nm);
	$("#usg_typ_nm").selectmenu("refresh");
}

function btnActive(id) {
	$('#'+id).addClass(grey-btn);
	$('#'+id).removeClass('white-btn');
	$('#'+id).removeClass('non-active');
	$('#'+id).removeClass('disabled');
}

function labelActive(id){
	$('#'+id).addClass('white-btn');
	$('#'+id).removeClass('grey-btn');
	$('#'+id).removeClass('not-active');
	$('#'+id).removeAttr('disabled');
}

function labelNonActive(id){
	$('#'+id).addClass('grey-btn');
	$('#'+id).removeClass('white-btn');
	$('#'+id).addClass('not-active');
	$('#'+id).attr('disabled',true);
}

function goProcess(type) {
	var param = checkValidation();
	var url = "";
	if( type == 'I' ) {
		url = "insertManageRatingDefInfo.json";
	} 
	else if( type == 'U' ) {
		url = "updateManageRatingDefInfo.json";
	}
	else if( type == 'D' ) {
		url = "deleteManageRatingDefInfo.json";
	}
	
	if( param != false ) {
		if( type == 'I' ) {
			var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		}
		else if(type == 'U') {
			var check = confirm('<spring:message code="MSG.M07.MSG00093" />'); //U
		}
		else if(type == 'D') {
			var check = confirm('<spring:message code="MSG.M07.MSG00054" />'); //D
		}
		
		if( !check ) {
			return;
		}
		
		$.ajax({
			url: url,
			type: 'POST',
			async: false,
			data: param,
			success: function(data) {
				if( data.result != "0" ) {
					if(type == 'I'){		
						alert('<spring:message code="MSG.M09.MSG00005" />');
					}
					else if(type == 'U') {		
						alert('<spring:message code="MSG.M09.MSG00005" />');
					}
					else if(type == 'D') {
						alert('<spring:message code="MSG.M07.MSG00053" />');
					}
					
					$("manageRatingDefTable").clearGridData();
					$("manageRatingDefTable").setGridParam({ postData: param}).trigger("reloadGrid");
					actionAfterinit();
				}
			},
			error: function(err) {
				if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
	     			alert(err.responseJSON.exceptionMsg);
	     		}else{
	     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
	     		}
			}
		});
	}
}

function checkValidation() {
	var param = new Object();
	param.chrg_cd = $("#chrg_cd").val();
	param.description = $("#description").val();
	param.usg_typ = $("#usg_typ").val();
	param.usg_typ_nm = $("#usg_typ_nm").val();
	
	return param;
}
</script>

<!--NaviGator-->
<div class="h3_bg">
	<!--<h3>시간대유형관리</h3>-->
	<h3>Add/Delete Time Period Type</h3>
</div>
	
<!--JQ Grid 리스트-->	

<div id='gridDiv'>
<table id="manageRatingDefTable" class="w100p"></table>
	<div id="pager2">
	</div>
</div>
<br>
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

<div id="popup_dialog" class="Layer_wrap" style="display:none; width:400px;" >
</div>       
