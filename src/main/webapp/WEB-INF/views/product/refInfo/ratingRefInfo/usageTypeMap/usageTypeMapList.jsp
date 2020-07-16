<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {
	
	initGrid();	
	
	$("#searchDataNm").selectmenu({
		change: function() {
			$("#dataNm").val($("#searchDataNm").val());
		}
	});
	
	$("#searchSeqNo").selectmenu({
		change: function() {
			$("#seqNo").val($("#searchSeqNo").val());
		}
	});
	
	$("#searchDataNm").val($("#dataNm").val());
	$("#searchDataNm").selectmenu("refresh");
	$("#searchSeqNo").val($("#seqNo").val());
	$("#searchSeqNo").selectmenu("refresh");

	//조회
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	$("#btn_insert").click(function() {
		goInsertPopUp();
	});

	$("#btn_update").click(function() {
		goUpdatePopUp();
	});	

	$("#btn_delete").click(function() {
		goDelete();
	});

	$(window).resize(function() {
		$("#usageTypeMapGrid").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function goSearch() {
	$("#usageTypeMap").attr("action", "/product/refInfo/ratingRefInfo/usageTypeMap/usageTypeMapList");
	$("#usageTypeMap").submit();
}

function goInsertPopUp() {
	var param = new Object();
	var url = "usageTypeMapInsertPopUp.ajax";
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goUpdatePopUp() {
	var param = new Object();
	var url = "usageTypeMapUpdatePopUp.ajax";
	var rowId = jQuery("#usageTypeMapGrid").jqGrid('getGridParam', 'selrow');
	
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00096"/>");
		return;
	}
	
	param.modDataNm = $("#usageTypeMapGrid").getRowData(rowId).dataNm;
	param.modSeqNo = $("#usageTypeMapGrid").getRowData(rowId).seqNo;
	param.modFld1 = $("#usageTypeMapGrid").getRowData(rowId).fld1;
	param.modFld2 = $("#usageTypeMapGrid").getRowData(rowId).fld2;
	param.modFld3 = $("#usageTypeMapGrid").getRowData(rowId).fld3;
	param.modEffDt = $("#usageTypeMapGrid").getRowData(rowId).effDt;
	param.modExpDt = $("#usageTypeMapGrid").getRowData(rowId).expDt;
	param.modUsgTyp = $("#usageTypeMapGrid").getRowData(rowId).usgTyp;
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goDelete() {
	var rowId = jQuery("#usageTypeMapGrid").jqGrid('getGridParam', 'selrow');
	
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056"/>");
		return;
	}
	
	$("#delDataNm").val($("#usageTypeMapGrid").getRowData(rowId).dataNm);
	$("#delSeqNo").val($("#usageTypeMapGrid").getRowData(rowId).seqNo);
	$("#delFld1").val($("#usageTypeMapGrid").getRowData(rowId).fld1);
	$("#delFld2").val($("#usageTypeMapGrid").getRowData(rowId).fld2);
	$("#delFld3").val($("#usageTypeMapGrid").getRowData(rowId).fld3);
	$("#delEffDt").val($("#usageTypeMapGrid").getRowData(rowId).effDt);
	
	if( confirm("<spring:message code="MSG.M07.MSG00054"/>") == true ) {
		$("#usageTypeMap").attr("action", "/product/refInfo/ratingRefInfo/usageTypeMap/usageTypeMapDeleteAction");
		$("#usageTypeMap").attr("method", "post");
		$("#usageTypeMap").submit();	
	}
}

function initGrid() {
		var param = new Object();
		
		param.searchDataNm = $("#dataNm").val();
		param.searchSeqNo = $("#seqNo").val();
		
		$("#usageTypeMapGrid").jqGrid({
			
		   	url:'usageTypeMapListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
		   	colModel: [ 
		   	    { label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTyp', width : 100, hidden:true},
		   	    { label: '<spring:message code="LAB.M03.LAB00068"/>', name: 'dataNm', width : 100},		
	  	 		{ label: '<spring:message code="LAB.M08.LAB00139"/>', name: 'seqNo', width : 100},			
	  	 		{ label: '<spring:message code="LAB.M13.LAB00024"/>', name: 'fld1', width : 100},			
	  	 		{ label: '<spring:message code="LAB.M13.LAB00025"/>', name: 'fld2', width : 100},			
	  	 		{ label: '<spring:message code="LAB.M13.LAB00026"/>', name: 'fld3', width : 100},			
	  	 		{ label: '<spring:message code="LAB.M08.LAB00108"/>', name: 'effDt', width : 100},		
	  	 		{ label: '<spring:message code="LAB.M08.LAB00110"/>', name: 'expDt', width : 100},
	  	 		{ label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTypNm', width : 100},
	  	 		
	   		],

		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#pager2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 240,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
				btnActive("btn_update");
				btnActive("btn_delete");
	        },
	        loadComplete: function(obj){
	        	$("#usageTypeMapGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        	btnNonActive("btn_update");
	        	btnNonActive("btn_delete");
	        },
			loadError: function (jqXHR, textStatus, errorThrown) {
				ajaxErrorCallback(jqXHR);
				
			},
			sortable: { update: function(permutation) {
				$("#usageTypeMapGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
				}
			}
	});

	$("#usageTypeMapGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#usageTypeMapGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

}
function btnActive(id) {
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

function closeModal() {
	$("#popup_dialog").remove();
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
<form id="usageTypeMap" name="usageTypeMap" method="post">
	<input type="hidden" id="dataNm" name="dataNm" value="${param.searchDataNm }"/>
	<input type="hidden" id="seqNo" name="seqNo" value="${param.searchSeqNo }"/>
	<input type="hidden" id="delDataNm" name="delDataNm"/>
	<input type="hidden" id="delSeqNo" name="delSeqNo"/>
	<input type="hidden" id="delFld1" name="delFld1"/>
	<input type="hidden" id="delFld2" name="delFld2"/>
	<input type="hidden" id="delFld3" name="delFld3"/>
	<input type="hidden" id="delEffDt" name="delEffDt"/>
	
	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M07.LAB00047"/>
				</h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:30%;">
			<col style="width:15%;">
			<col style="width:40%;">
		</colgroup>
		 <thead> 
			   <tr>
					<th>
						<spring:message code="LAB.M03.LAB00068"/>
					</th>
					<td>
						<select id="searchDataNm" name="searchDataNm" class="w270">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${dataNmList}" var="dataNmList" varStatus="status">
								<option value="${dataNmList.commonCd}">
									${dataNmList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M08.LAB00139"/>
					</th>
					<td>
						<select id="searchSeqNo" name="searchSeqNo" class="w270">
							<option value="">
								<spring:message code="LAB.M07.LAB00195" />
							</option>
							<c:forEach items="${seqNoList}" var="seqNoList" varStatus="status">
								<option value="${seqNoList.commonCd}">
									${seqNoList.commonCdNm}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
		</thead>
	</table> 
</form>	

<!--JQ Grid 리스트-->	
<br>
<div id='gridDiv'>
<table id="usageTypeMapGrid" class="w100p"></table>
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
		<ntels:auth auth="${menuAuthD}">
		<a class="grey-btn" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
	</div>
</div>
<div id="popup_dialog" class="Layer_wrap" style="display:none; width:100px;">
</div>       



