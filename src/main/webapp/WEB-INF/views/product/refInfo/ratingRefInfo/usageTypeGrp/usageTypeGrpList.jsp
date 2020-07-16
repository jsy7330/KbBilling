<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	
	$("#usgTypGrpCd").selectmenu({
		change: function() {
			$("#searchUsgTypGrpCd").val($("#usgTypGrpCd").val());
			goSearch();
		}
	})
	
	$("#usgTypGrpCd").val($("#searchUsgTypGrpCd").val());
	$("#usgTypGrpCd").selectmenu("refresh");
	
	//jqgrid 호출
	initGrid();	
	
	$("#lay_next_btn").click(function() {
		goInsert();
	});
	
	$("#lay_prev_btn").click(function() {
		goDelete();
	});
	
	$("#btn_save").click(function() {
		goSave();
	});
	
	$("#btn_delete").click(function() {
		goDelete();
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function initGrid() {

	var param = new Object();
	param.usgTypGrpCd = $("#searchUsgTypGrpCd").val();
	$("#usgTypGrid").jqGrid({
		
	   	url: 'usageTypeListAction.json?',
	   	postData: param,
	    mtype: "POST",
	   	datatype: "json",
	   	colModel: [ 
   		    { label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTyp', align: "center", width: 120},			
   		 	{ label: '<spring:message code="LAB.M07.LAB00050"/>', name: 'usgTypNm', align: "center", width: 120},
   		 	{ label: 'effDt', name: 'effDt', hidden:true, width: 120}
   		],

	   	rowNum: 10,					//한번에 노출되는 row 수
	   	rowList: [5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2',
		sortable: true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect: function(rowId, index, contents, event){

		},
        loadComplete: function(obj){
        	$("#usgTypGrid").setGridWidth($('#usgTypGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#usgTypGrid").setGridWidth($('#usgTypGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#usgTypGrpGrid").jqGrid({
		
	   	url:'usageTypeGrpListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   		    { label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTyp', align: "center", width : 100},	
   			{ label: '<spring:message code="LAB.M07.LAB00050"/>', name: 'usgTypNm', align: "center", width : 100},			
   			{ label: '사용유형그룹코드', name: 'usgTypGrpCd', hidden:true, width : 100},			
   		 	{ label: 'effDt', name: 'effDt', hidden:true, width: 120},
   			{ label: 'chrgCd', name: 'chrgCd', hidden:true, width: 120}

   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager3',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        },
        loadComplete: function(obj){
        	$("#usgTypGrpGrid").setGridWidth($('#usgTypGrpGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {
			$("#usgTypGrpGrid").setGridWidth($('#usgTypGrpGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#usgTypGrid").setGridWidth($('#usgTypGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#usgTypGrpGrid").setGridWidth($('#usgTypGrpGridDiv').width(),false);
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#usgTypGrid").setGridWidth($('#usgTypGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#usgTypGrpGrid").setGridWidth($('#usgTypGrpGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

}

function goSearch() {
	$("#usageTypeGrp").attr("action", "/product/refInfo/ratingRefInfo/usageTypeGrp/usageTypeGrpList");
	$("#usageTypeGrp").submit();
}

function goInsert() {
	if($("#usgTypGrpCd").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00022"/>");
		return;
	}
	
	var param = new Object();
	var rowId = jQuery("#usgTypGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00096"/>");
		return;
	}
	param.usgTyp = $("#usgTypGrid").getRowData(rowId).usgTyp;
	param.usgTypNm = $("#usgTypGrid").getRowData(rowId).usgTypNm;
	param.effDt = $("#usgTypGrid").getRowData(rowId).effDt;
	param.usgTypGrpCd = $("#searchUsgTypGrpCd").val();
	
	$("#usgTypGrpGrid").jqGrid('addRowData', $("#usgTypGrpGrid").getDataIDs().length+1, param);
}

function goSave() {
	var param = new Object();
	param.updateSetValList = $("#usgTypGrpGrid").getRowData();
	
	$.ajax({
        url : 'usageTypeGrpInsertAction.json',
        type :'POST',
        data : JSON.stringify(param),
        dataType : 'json',
        contentType : "application/json; charset=UTF-8",
        success : function(data) {
			if(data.result != "0"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				goSearch();
			}
		},
     	beforeSend : function(data){
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

function goDelete() {
	var rowId = jQuery("#usgTypGrpGrid").jqGrid('getGridParam', 'selrow');
	
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056"/>");
		return;
	}
	
	$("#delUsgTyp").val($("#usgTypGrpGrid").getRowData(rowId).usgTyp);
	$("#delChrgCd").val($("#usgTypGrpGrid").getRowData(rowId).chrgCd);
	$("#delUsgTypGrpCd").val($("#usgTypGrpGrid").getRowData(rowId).usgTypGrpCd);
	$("#delEffDt").val($("#usgTypGrpGrid").getRowData(rowId).effDt);
	
	if( confirm("<spring:message code="MSG.M07.MSG00054"/>") == true ) {
		$("#usageTypeGrp").attr("action", "/product/refInfo/ratingRefInfo/usageTypeGrp/usageTypeGrpDeleteAction");
		$("#usageTypeGrp").submit();
	}
}

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
<form id="usageTypeGrp" name="usageTypeGrp" method="post">
	<input type="hidden" id="searchUsgTypGrpCd" name="searchUsgTypGrpCd" value="${param.usgTypGrpCd }"/>
	<input type="hidden" id="delUsgTyp" name="delUsgTyp" />
	<input type="hidden" id="delChrgCd" name="delChrgCd" />
	<input type="hidden" id="delUsgTypGrpCd" name="delUsgTypGrpCd" />
	<input type="hidden" id="delEffDt" name="delEffDt" />
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M07.LAB00038"/>
			</h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:10%;">
			<col style="width:80%;">
		</colgroup>
		 <thead> 
				<tr>
					<th>
						<spring:message code="LAB.M07.LAB00039"/>
					</th>
					<td colspan='2'>
						<select id="usgTypGrpCd" name="usgTypGrpCd" class="w270">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${usgTypGrpList}" var="usgTypGrpList" varStatus="status">
								<option value="${usgTypGrpList.commonCd }">
									${usgTypGrpList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
		</thead>
	</table> 
</form>	

<!--사용자부 -->
<div class="lay_box2">
	<div class="lay_left2">
		<div class="main_btn_box">
			<div class="fl">
	            <h4 class="sub_title">
	            	<spring:message code="LAB.M07.LAB00029"/>
	            </h4>
			</div>
		</div>
		<div id="usgTypGridDiv">
			<table id="usgTypGrid" class="w100p"></table>
		</div>
	</div>
	
	<img id='lay_prev_btn' class='lay_prev_icon' src='/images/icon/ico_cal_prev.png' width="25" height="20">
	<img id='lay_next_btn' class='lay_next_icon' src='/images/icon/ico_cal_next.png' width="25" height="20">

	<div class="lay_right2">
		<div class="main_btn_box">
			<div class="fl">
	            <h4 class="sub_title">
	            	<spring:message code="LAB.M01.LAB00187"/>
	            </h4>
			</div>
		</div>
		<div id='usgTypGrpGridDiv'>
			<table id="usgTypGrpGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fr">
				<span id="workUserBtn">
					<ntels:auth auth="${menuAuthU}">
					<a id="btn_save" class="grey-btn" title='<spring:message code="LAB.M09.LAB00048"/>' href="#"><span class="save_icon"><spring:message code="LAB.M09.LAB00048"/></span></a>
					</ntels:auth>
					<ntels:auth auth="${menuAuthD}">
					<a id="btn_delete"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
					</ntels:auth>
				</span>
			</div>
		</div>
	</div>
</div>
