<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';

$(document).ready(function() {
	
	$("#searchSoId").val("${session_user.soId}");
	$('#searchSoId').selectmenu("refresh");
	
	// JQGrid 호출
	initGrid();
	 
	// 조회
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	// 등록 처리
	$("#btn_insert").click(function() {		
		$.ajax({
	          type : "post",
	          url : '/charge/billing/billing/billingCycleInfo/billingCycleInfoInsertPopUp.ajax',
	          async: true,
	          success : function(data) {
	            var html = data;
	            $("#popup_dialog").css("width", 1000);
	            $("#popup_dialog").html(html);
	          },    
	          complete : function(){
	            wrapWindowByMask(); // 팝업 오픈
	          }
	        }); 
		
	});

	// 수정 처리
	$("#btn_update").click(function() {
		goModify("U");
	});
	
	
	// 전월 복사 처리
	$("#btn_copy").click(function() {
		goModify("C");
	})

	// 리사이징
	$(window).resize(function() {
		$("#BillingCycleInfoTable").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
});

function initGrid() {

	var param = new Object();
	param.searchSoId =  $("#searchSoId").val();	
	param.searchBillYymm =  dateFormatToStringYYYYMM($("#searchBillYymm").val());
	
	$("#BillingCycleInfoTable").jqGrid({

	   	url:'BillingCycleInfoListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	
	   	colModel: [ 
						{ label: '<spring:message code="LAB.M07.LAB00003" />',  name: 'soNm', width : 40, align:"center"},
						{ label: '<spring:message code="LAB.M14.LAB00044" />', name: 'setItmCd', width : 40, align:"center"},
						{ label: '<spring:message code="LAB.M07.LAB00205" />', name: 'setItmNm', width : 70},
						{ label: '<spring:message code="LAB.M13.LAB00034" />', name: 'required', width : 40, align:"center"},
						{ label: '<spring:message code="LAB.M13.LAB00028" />', name: 'fieldNature', width : 40, align:"center"},
						{ label: '<spring:message code="LAB.M13.LAB00027" />', name: 'fieldStructure', width : 40},
						{ label: '<spring:message code="LAB.M13.LAB00030" />', name: 'fieldDigit', width : 40, align:"right"},
						{ label: '<spring:message code="LAB.M07.LAB00201" />', name: 'setVal', width : 150, editable:true, sortable:false},
						{ label: 'eftBgnYymm', name: 'eftBgnYymm', hidden:true,width : 0},
						{ label: 'eftEndYymm', name: 'eftEndYymm', hidden:true,width : 0},
						{ label: 'soId'           , name: 'soId', hidden:true,width : 0},
						{ label: 'fieldNatureCd' , name: 'fieldNatureCd', hidden:true,width : 0},
						{ label: 'requiredCd' , name: 'requiredCd', hidden:true,width : 0},
						{ label: 'billYymm' , name: 'billYymm', hidden:true,width : 0}
		],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2',
		autowidth:true,
		//sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 450,					//화면 상태에 따라 크기 조절 할 것
		cellEdit:true,
		cellsubmit:'clientArray',
		afterEditCell: function(id,name,val,iRow,iCol){
			//console.log(id,name,val,iRow,iCol);
			/* 
			$("#"+iRow+"_"+name).bind('blur',function(){
				$('#BillingCycleInfoTable').restoreCell(iRow,iCol);
			});	
			 */
		},
		afterSaveCell : function(rowid,name,val,iRow,iCol) {
			//console.log(rowid,name,val,iRow,iCol);
		},
	    loadComplete: function(obj){
	    	$("#BillingCycleInfoTable").setGridWidth($("#gridDiv").width(),false);
	    	
	    	if($("#BillingCycleInfoTable").getGridParam("reccount") > 0) {
	    		btnActive("btn_copy");
	    		btnActive("btn_update");
	    	}
	    	else if($("#BillingCycleInfoTable").getGridParam("reccount") == 0) {
	    		btnActive("btn_copy");
	    		btnNonActive("btn_update");
	    	}
	    	
	    },
	});
	$("#BillingCycleInfoTable").setGridWidth($("#gridDiv").width(),false);
}

function goSearch() {
	var param = new Object();
	param.page =  $("#page").val();
	param.perPage =  $("#perPage").val();
	param.searchSoId =  $("#searchSoId").val();	
	param.searchBillYymm = dateFormatToStringYYYYMM($("#searchBillYymm").val());
	
	if(param.searchBillYymm == 'SEL'){
		alert('<spring:message code="MSG.M10.MSG00006" />');
		return false;
	}
	
	$("#BillingCycleInfoTable").clearGridData();  // 이전 데이터 삭제
	$("#BillingCycleInfoTable").setGridParam({ postData: param }).trigger("reloadGrid");
}

function goModify(type) {
	
	if($("#searchBillYymm").val() == 'SEL'){
		alert('<spring:message code="MSG.M10.MSG00006" />');
		return;
	}
	
	if(type == "C") {
		var check = confirm('<spring:message code="MSG.M03.MSG00011" />');
	}
	
	else if(type == "U") {
		var check = confirm('<spring:message code="MSG.M07.MSG00093" />');
	}
	
	if(!check){
		return;
	}
	
	var param = checkModifyValidation(type);
	
	var url = "";
	if(type == "U"){		
		url = "updateBillingCycleInfo.json";
	}else if(type == "C"){		
		url = "copyBillingCycleInfo.json";
	}
	
	if(param != false){
		if(type == "U") { // 수정
			
			$.ajax({
		        url:url,
		        type:'POST',
		        data : JSON.stringify(param),
		        dataType: 'json',
		        contentType : "application/json; charset=utf-8",
		        success : function(data) {
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						goSearch();
					}
				},
		     	beforeSend: function(data){
		     	},
		     	error : function(err){
		     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
		     			alert(err.responseJSON.exceptionMsg);
		     		}else{
		     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
		     		}
		     		
		     	}
		    });
		
		}
		else if(type == "C") { //복사
			
			btnNonActive("btn_copy");
			$.ajax({
				url : url,
				type : 'POST',
				data : param,
				dataType: 'json',
				success : function(data) {
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						goSearch();
					}
					btnActive("btn_copy");
				},
				beforeSend: function(data){
		     	},
		     	error : function(err){
		     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
		     			alert(err.responseJSON.exceptionMsg);
		     		}else{
		     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
		     		}
		     		btnActive("btn_copy");
		     		
		     	}
			});
		}
	}	
}

//유효성 체크
function checkModifyValidation(type){
	if(type == 'C') { //복사
		// 전월 처리
		var BillYymmYear = dateFormatToStringYYYYMM($("#searchBillYymm").val()).substr(0,4);
		var BillYymmMonth = dateFormatToStringYYYYMM($("#searchBillYymm").val()).substr(4,2);
		
		var beforeYear = Number(BillYymmYear);
		var beforeMonth = Number(BillYymmMonth) - 1;
		
		if(beforeMonth < 1){
			BillYymmYear = beforeYear-1;
			beforeMonth = 12;
		}
		
		if(beforeMonth < 10){
			BillYymmMonth = "0"+beforeMonth;
		}
		
		BillYymmYear = ""+BillYymmYear;
		BillYymmMonth = ""+BillYymmMonth;
		
		var param = new Object();
		param.searchSoId = $("#searchSoId").val();
		param.soId = $("#searchSoId").val();
		param.searchBillYymm = dateFormatToStringYYYYMM($("#searchBillYymm").val());
		param.lastYymm = BillYymmYear+BillYymmMonth;
	}
	
	if(type == 'U') //그리드 수정
	{
		
		var param = new Array();
		
		var updateList = $("#BillingCycleInfoTable").getChangedCells('all'); //<--셀에값이 변한 줄 불러옴
		console.log(updateList);

		if(updateList == null || updateList.length == 0){
			alert('<spring:message code="MSG.M07.MSG00098"/>');		//MSG.M07.MSG00098 - 수정할 데이터가 존재하지 않습니다
			return false;
		}
		
		for(var i=0; i<updateList.length; i++){

			if(updateList[i].setVal.length == 0 || updateList[i].fieldDigit < updateList[i].setVal.length){
				var index = i+1;
				alert('<spring:message code="MSG.M07.MSG00129" arguments="' + index + '"/>');	//MSG.M07.MSG00129=정정값 필드 자릿수를 확인해 주세요.({0} 번째)
				return false;
			}
			
			var data = new Object();
			data.soId = updateList[i].soId;
			data.setItmCd = updateList[i].setItmCd;
			data.billYymm = updateList[i].billYymm;
			data.setVal = updateList[i].setVal;
			
			param.push(data);
			
		}
		
	}
	return param;
}

function btnActive(id){
	$('#'+id).addClass('grey-btn');
	$('#'+id).removeClass('white-btn');
	$('#'+id).removeClass('not-active');
	$('#'+id).removeAttr('disabled');
}

function btnNonActive(id){
	$('#'+id).addClass('white-btn');
	$('#'+id).removeClass('grey-btn');
	$('#'+id).addClass('not-active');
	$('#'+id).attr('disabled',true);
}

$(window).resize(function() {
	$("#BillingCycleInfoTable").setGridWidth($("#gridDiv").width(),false);
});



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
				<h4 class="sub_title"><spring:message code="LAB.M06.LAB00112"/></h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		 <thead> 
		<tr>
			<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
			<td>
				<select class="w150" id="searchSoId" name="searchSoId">
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>  
			<th><spring:message code="LAB.M10.LAB00033"/><!-- 청구년월 --></th>
			<td>
				<select id="searchBillYymm" name="searchBillYymm" class="w150">
					<c:forEach items="${billYymmList}" var="billYymmList" varStatus="i">
					<option value="${billYymmList}">${billYymmList}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
	</table> 
</form>	

<!--JQ Grid 리스트-->	
<div class="main_btn_box">
	<div class="fl">	
	   <h4 class="sub_title"><spring:message code="LAB.M06.LAB00111"/></h4>
	</div>
	<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_copy"><span class="edit_icon"><spring:message code="LAB.M09.LAB00233"/></span></a> 
		</div>
</div>
<div id='gridDiv'>
<table id="BillingCycleInfoTable" class="w100p"></table>
	<div id="pager2"></div>
</div>

<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">
		<!--등록-->
		<ntels:auth auth="${menuAuthC}">
			<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>
		<!--수정-->
		<ntels:auth auth="${menuAuthU}">
			<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		<!--삭제-->
		<!--
		<ntels:auth auth="${menuAuthD}">
			<a class="grey-btn" href="#" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>-->
		<!--출력-->
		<!-- 
		<ntels:auth auth="${menuAuthP}">
		<a class="white-btn not-active" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth> -->
	
  </div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>


