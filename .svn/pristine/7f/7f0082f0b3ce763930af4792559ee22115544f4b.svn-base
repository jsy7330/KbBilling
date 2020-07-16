<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	
	$("#searchChrgGrpCd").selectmenu({
		change: function() {
			$("#chrgGrpCd").val($("#searchChrgGrpCd").val());
			goSearch();
		}
	});
	
	$("#searchChrgGrpCd").val($("#chrgGrpCd").val());
	$("#searchChrgGrpCd").selectmenu("refresh");
	
	initGrid();	
	
	//조회
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
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
	
	$(window).resize(function() {
		$("#userGrid").setGridWidth($('#userGridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});


function goSearch() {
	$("#ratingCodeGrp").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeGrp/ratingCodeGrpList");
	$("#ratingCodeGrp").submit();
}

function goInsert() {
	if($("#searchChrgGrpCd").val() == "") {
		alert("<spring:message code="MSG.M01.MSG00038"/>");
		return;
	}
	
	var param = new Object();
	var rowId = jQuery("#ratingDesGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00096"/>");
		return;
	}

	param.usgTypGrpNm = $("#searchChrgGrpCd").val();
	if( $("#searchChrgGrpCd").val() == '0000000002' ) {
		param.usgTypGrpNm = '망내';
	}
	else if( $("#searchChrgGrpCd").val() == '0000000003' ) {
		param.usgTypGrpNm = '데이터로밍';
	}
	param.usgTyp = $("#ratingDesGrid").getRowData(rowId).usgTyp;
	param.chrgCd = $("#ratingDesGrid").getRowData(rowId).chrgCd;
	param.chrgGrpCd = $("#searchChrgGrpCd").val();
	param.usgTypNm = $("#ratingDesGrid").getRowData(rowId).usgTypNm;
	param.description = $("#ratingDesGrid").getRowData(rowId).description;
	param.effDt = $("#ratingDesGrid").getRowData(rowId).effDt;
	param.expDt = $("#ratingDesGrid").getRowData(rowId).expDt;
	param.lmtInclude = 'Y';
	
	$("#ratingCodeGrpGrid").jqGrid('addRowData', $("#ratingCodeGrpGrid").getDataIDs().length+1, param);
}

function goSave() {
	var param = new Object();
	param.updateSetValList = $("#ratingCodeGrpGrid").getRowData();
	
	$.ajax({
        url : 'ratingCodeGrpInsertAction.json',
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
	var rowId = jQuery("#ratingCodeGrpGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056"/>");
		return;
	}
	
	$("#delUsgTyp").val($("#ratingCodeGrpGrid").getRowData(rowId).usgTyp);
	$("#delChrgCd").val($("#ratingCodeGrpGrid").getRowData(rowId).chrgCd);
	$("#delChrgGrpCd").val($("#ratingCodeGrpGrid").getRowData(rowId).chrgGrpCd);
	$("#delEffDt").val($("#ratingCodeGrpGrid").getRowData(rowId).effDt);
	
	if( confirm("<spring:message code="MSG.M07.MSG00054"/>") == true ) {	
		$("#ratingCodeGrp").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeGrp/ratingCodeGrpDeleteAction");
		$("#ratingCodeGrp").attr("method", "post");
		$("#ratingCodeGrp").submit();
	}
}

function initGrid() {

	var param = new Object();
	param.searchChrgCd = $("#searchChrgCd").val();
	param.searchDescription = $("#searchDescription").val();
	param.searchChrgGrpCd = $("#chrgGrpCd").val();

	$("#ratingDesGrid").jqGrid({
		
	   	url:'ratingCodeDescriptionAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   			{ label: '사용유형' , name: 'usgTyp', hidden:true, width : 0},
   			{ label: '사용유형명' , name: 'usgTypNm', hidden:true, width : 0},
   			{ label: '과금코드' , name: 'chrgCd', hidden:true, width : 0},
   			{ label: '사용유형그룹코드' , name: 'chrgGrpCd', hidden:true, width : 0},
   			{ label: '사용유형그룹명' , name: 'usgTypGrpNm', hidden:true, width : 0},
   			{ label: '적용시작일' , name: 'effDt', hidden:true, width : 0},
   		    { label: '적용종료일', name: 'expDt', hidden:true, width: 0},
   		 	{ label: '과금코드' , name: 'chrgCd', hidden:true, width : 0},
   			{ label: '한도' , name: 'lmtInclude', hidden:true, width : 0},
   		 	{ label: '<spring:message code="LAB.M07.LAB00200"/>' , name: 'description', width : 120}
   		],

	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager2',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	setSelectedDate(rowId);
        },
        loadComplete: function(obj){
        	$("#ratingDesGrid").setGridWidth($('#ratingDesGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#ratingDesGrid").setGridWidth($('#ratingDesGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	
	$("#ratingCodeGrpGrid").jqGrid({
		
	   	url:'ratingCodeGrpListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   			{ label: 'ifSys' , name: 'ifSys', hidden:true, align:"center", width : 0},
   			{ label: 'remark' , name: 'remark', hidden:true, align:"center", width : 0},
   			{ label: '과금코드', name: 'chrgCd', hidden:true, align:"center", width : 0},
   			{ label: '사용유형그룹코드', name: 'chrgGrpCd', hidden:true, align:"center", width : 100},
   			{ label: 'usgTyp', name: 'usgTyp', hidden:true, align:"center", width : 100},
   			{ label: '적용시작일' , name: 'effDt', hidden:true, width : 0},
   		    { label: '적용종료일', name: 'expDt', hidden:true, width: 0},
   			{ label: '<spring:message code="LAB.M07.LAB00037"/>', name: 'usgTypGrpNm', align:"center", width : 100},
  	 		{ label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTypNm', align:"center", width : 100},			
  	 		{ label: '<spring:message code="LAB.M01.LAB00133"/>', name: 'description', align:"center", width : 100},			
  	 		{ label: '<spring:message code="LAB.M14.LAB00004"/>',name:'lmtInclude', align:"center", 
					width:90, editable: true, edittype:"select",  formatoptions: {disabled : false},
					editoptions:{value:{Y:'Y', 0:'0'}} }
   		],
		cellEdit:true,
		cellsubmit:'clientArray',
   	   	rowNum:10,					//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#pager3',
		sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 250,				//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowId, index, contents, event){
        	setSelectedDate(rowId);
        },
        loadComplete: function(obj){
        	$("#ratingCodeGrpGrid").setGridWidth($('#ratingCodeGrpGridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
		},
		sortable: { update: function(permutation) {  0
			$("#ratingCodeGrpGrid").setGridWidth($('#ratingCodeGrpGridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#ratingDesGridDiv").setGridWidth($('#ratingDesGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	$("#ratingCodeGrpGrid").setGridWidth($('#ratingCodeGrpGridDiv').width(),false);
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#ratingDesGrid").setGridWidth($('#ratingDesGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#ratingCodeGrpGrid").setGridWidth($('#ratingCodeGrpGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

//상세정보 
function setSelectedDate(rowId){		
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
<form id="ratingCodeGrp" name="ratingCodeGrp" method="post">
	<input type="hidden" id="insertUsgTyp" name="insertUsgTyp" />
	<input type="hidden" id="insertChrgCd" name="insertChrgCd" />
	<input type="hidden" id="insertchrgGrpCd" name="insertchrgGrpCd" />
	<input type="hidden" id="insertEffDt" name="insertEffDt" />
	<input type="hidden" id="insertExpDt" name="insertExpDt" />
	<input type="hidden" id="insertLmtInclude" name="insertLmtInclude" />
	<input type="hidden" id="delUsgTyp" name="delUsgTyp" />
	<input type="hidden" id="delChrgCd" name="delChrgCd" />
	<input type="hidden" id="delChrgGrpCd" name="delChrgGrpCd" />
	<input type="hidden" id="delEffDt" name="delEffDt" />
	<input type="hidden" id="chrgGrpCd" name="chrgGrpCd" value="${param.searchChrgGrpCd }"/>	
	
	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M01.LAB00143"/>
				</h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:10%;">
			<col style="width:10%;">
			<col style="width:70%;">
		</colgroup>
		 <thead> 
			   <tr>
					<th>
						<spring:message code="LAB.M01.LAB00135"/>
					</th>
					<td colspan='3'>
						<select name="searchChrgGrpCd" id="searchChrgGrpCd" class="w270">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${chrgCdGrpList}" var="chrgCdGrpList" varStatus="status">
								<option value="${chrgCdGrpList.commonCd}">
									${chrgCdGrpList.commonCdNm }
								</option>
							</c:forEach>
							
						</select>
					</td>
				</tr>
				<tr>
	
					<th>
						<spring:message code="LAB.M01.LAB00141"/>
					</th>
					<td>
						<input type="text" id="searchChrgCd" name="searchChrgCd" value="${param.searchChrgCd }" class="w270"/>
					</td> 
					<th>
						<spring:message code="LAB.M01.LAB00115"/>
					</th>
					<td colspan='2'>
						<input type="text" id="searchDescription" name="searchDescription" value="${param.searchDescription }" class="w400" />
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
		            	<spring:message code="LAB.M03.LAB00055"/>
		            </h4>
			</div>
		</div>
		<div id='ratingDesGridDiv'>
			<table id="ratingDesGrid" class="w100p"></table>
		</div>
	</div>
	
	<img id='lay_prev_btn' class='lay_prev_icon' src='/images/icon/ico_cal_prev.png' width="25" height="20">
	<img id='lay_next_btn' class='lay_next_icon' src='/images/icon/ico_cal_next.png' width="25" height="20">

	<div class="lay_right2">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title">
		            	<spring:message code="LAB.M03.LAB00075"/>
		            </h4>
			</div>
		</div>
		<div id='ratingCodeGrpGridDiv'>
			<table id="ratingCodeGrpGrid" class="w100p"></table>
		</div>
		<div class="main_btn_box">
			<div class="fr">
				<span id="workUserBtn">
					<ntels:auth auth="${menuAuthU}">
					<a id="btn_save" class="grey-btn" title='<spring:message code="LAB.M09.LAB00048"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M09.LAB00048"/></span></a>
					</ntels:auth>
					<ntels:auth auth="${menuAuthD}">
					<a id="btn_delete"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
					</ntels:auth>
				</span>
			</div>
		</div>
	</div>
</div>

<div id="pop_layer" style="height:600px;width:500px;overflow-x:none;overflow-y:auto;display:none;"></div>       



