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

	$("#btn_search").click(function() {
		goSearch();
	});
	
	$("#btn_insert").click(function() {
		goInsertPopUp();
	});
	
	$("#btn_update").click(function() {
		goUpdatePopUp();
	});

	//삭제처리
	$("#btn_delete").click(function() {
		goDelete();
	});

	$(window).resize(function() {
		$("#usageTypeRuleGrid").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function goSearch() {
	$("#usageTypeRule").attr("action", "/product/refInfo/ratingRefInfo/usageTypeRule/usageTypeRuleList");
	$("#usageTypeRule").submit();
}

function goInsertPopUp() {
	var param = new Object();
	var url = "usageTypeRuleInsertPopUp.ajax";
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goUpdatePopUp() {
	var param = new Object();
	var rowId = jQuery("#usageTypeRuleGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00096"/>");
		return;
	}
	
	param.dataNm = $("#usageTypeRuleGrid").getRowData(rowId).dataNm;
	param.seqNo = $("#usageTypeRuleGrid").getRowData(rowId).seqNo;
	param.fldSeq = $("#usageTypeRuleGrid").getRowData(rowId).fldSeq;
	param.fldNm = $("#usageTypeRuleGrid").getRowData(rowId).fldNm;
	param.effDt = $("#usageTypeRuleGrid").getRowData(rowId).effDt;
	var url = "usageTypeRuleUpdatePopUp.ajax";
	
	$("#popup_dialog").css("width", 1000);
	openModalPopup(url, param);
}

function goDelete() {
	var rowId = jQuery("#usageTypeRuleGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00056" />");
		return;
	}
	$("#delDataNm").val($("#usageTypeRuleGrid").getRowData(rowId).dataNm);
	$("#delSeqNo").val($("#usageTypeRuleGrid").getRowData(rowId).seqNo);
	$("#delFldSeq").val($("#usageTypeRuleGrid").getRowData(rowId).fldSeq);
	$("#delEffDt").val($("#usageTypeRuleGrid").getRowData(rowId).effDt);
	
	if( confirm("<spring:message code="MSG.M07.MSG00054"/>") == true ) {
		$("#usageTypeRule").attr("action", "/product/refInfo/ratingRefInfo/usageTypeRule/usageTypeRuleDeleteAction");
		$("#usageTypeRule").submit();
	}
}

function initGrid() {

	var param = new Object();
	
	param.dataNm = $("#dataNm").val();
	param.seqNo = $("#seqNo").val();
	
	$("#usageTypeRuleGrid").jqGrid({
		
	   	url:'usageTypeRuleListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   			{ label: 'effDt' , name: 'effDt', hidden:true, width : 0},
   		    { label: '<spring:message code="LAB.M03.LAB00068"/>', name: 'dataNm', width : 100 },			
  	 		{ label: '<spring:message code="LAB.M08.LAB00139"/>', name: 'seqNo', width : 100, align:"center" },	
  	 		{ label: '<spring:message code="LAB.M13.LAB00029"/>', name: 'fldSeq', width : 100 },		
  	 		{ label: '<spring:message code="LAB.M01.LAB00181"/>', name: 'fldNm', width : 100 },			
  	 		{ label: '<spring:message code="LAB.M10.LAB00056"/>', name: 'lastModBy', width : 100 },	
  	 		{ label: '<spring:message code="LAB.M10.LAB00055"/>', name: 'lastModDt', align:"center", width : 100 }
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
        	btnNonActive("btn_update");
        	btnNonActive("btn_delete");
        	$("#usageTypeRuleGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#usageTypeRuleGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#usageTypeRuleGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#usageTypeRuleGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

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
<form id="usageTypeRule" name="usgTypeRule" method="post">
	<input type="hidden" id="dataNm" name="dataNm" value="${param.dataNm }"/>
	<input type="hidden" id="seqNo" name="seqNo" value="${param.seqNo }"/>
	<input type="hidden" id="delDataNm" name="delDataNm"/>
	<input type="hidden" id="delSeqNo" name="delSeqNo"/>
	<input type="hidden" id="delFldSeq" name="delFldSeq"/>
	<input type="hidden" id="delEffDt" name="delEffDt"/>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">
				<spring:message code="LAB.M07.LAB00043"/>
			</h4>
		</div>
		<div class="fr mt10">
		<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:20%;">
			<col style="width:20%;">
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
	<table id="usageTypeRuleGrid" class="w100p"></table>
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
