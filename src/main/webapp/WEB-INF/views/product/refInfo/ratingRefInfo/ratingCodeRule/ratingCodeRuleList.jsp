<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

$(document).ready(function() {

	//jqgrid 호출
	initGrid();	

	$('#searchUsgTyp').selectmenu({
        change: function() {
        	$("#usgTyp").val($("#searchUsgTyp").val());
        }
	});
	
	$('#searchChrgCdSeq').selectmenu({
        change: function() {
        	$("#chrgCdSeq").val($("#searchChrgCdSeq").val());
        }
	});
	
	$('#searchSeqNo').selectmenu({
        change: function() {
        	$("#seqNo").val($("#searchSeqNo").val());
        }
	});
	
	$("#searchUsgTyp").val($("#usgTyp").val());
	$("#searchUsgTyp").selectmenu("refresh");
	$("#searchChrgCdSeq").val($("#chrgCdSeq").val());
	$("#searchChrgCdSeq").selectmenu("refresh");
	$("#searchSeqNo").val($("#seqNo").val());
	$("#searchSeqNo").selectmenu("refresh");

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
		$("#ratingCodeRuleGrid").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function goSearch() {
	var param = new Object();
	$("#ratingCodeRule").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeRule/ratingCodeRuleList");
	$("#ratingCodeRule").attr("method", "post");
	$("#ratingCodeRule").submit();
}

function goInsertPopUp() {
	var param = new Object();
	var url = "ratingCodeRuleInsertPopUp.ajax";
	$("#popup_dialog").css("width", 1500);
	openModalPopup(url, param);
}

function goUpdatePopUp() {
	var param = new Object();
	var url = "ratingCodeRuleUpdatePopUp.ajax";
	var rowId = jQuery("#ratingCodeRuleGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00096" />");
		return;
	}
	
	param.usgTyp = $("#ratingCodeRuleGrid").getRowData(rowId).usgTyp;
	param.chrgCdSeq = $("#ratingCodeRuleGrid").getRowData(rowId).chrgCdSeq;
	param.seqNo = $("#ratingCodeRuleGrid").getRowData(rowId).seqNo;
	param.branchSeq = $("#ratingCodeRuleGrid").getRowData(rowId).branchSeq;
	param.branchNm = $("#ratingCodeRuleGrid").getRowData(rowId).branchNm;
	param.funcCd = $("#ratingCodeRuleGrid").getRowData(rowId).funcCd;
	param.effDt = $("#ratingCodeRuleGrid").getRowData(rowId).effDt;

	$("#popup_dialog").css("width", 1500);
	openModalPopup(url, param);
}

function goDelete() {
	var rowId = jQuery("#ratingCodeRuleGrid").jqGrid('getGridParam', 'selrow');
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056" />");
		return;
	}	
	
	var param = new Object();
	
	$("#usgTyp").val($("#ratingCodeRuleGrid").getRowData(rowId).usgTyp);
	$("#chrgCdSeq").val($("#ratingCodeRuleGrid").getRowData(rowId).chrgCdSeq);
	$("#seqNo").val($("#ratingCodeRuleGrid").getRowData(rowId).seqNo);
	$("#branchSeq").val($("#ratingCodeRuleGrid").getRowData(rowId).branchSeq);
	$("#effDt").val($("#ratingCodeRuleGrid").getRowData(rowId).effDt);
	
	if( confirm("<spring:message code="MSG.M07.MSG00054" />") == true ) {
		$("#ratingCodeRule").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeRule/ratingCodeRuleDeleteAction");
		$("#ratingCodeRule").attr("method", "post");
		$("#ratingCodeRule").submit();
	}
}

function initGrid() {
	var param = new Object();
	param.searchUsgTyp = $("#usgTyp").val();
	param.searchChrgCdSeq = $("#chrgCdSeq").val();
	param.searchSeqNo = $("#seqNo").val();
	
	$("#ratingCodeRuleGrid").jqGrid({
		
	   	url: 'ratingCodeRuleListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	colModel: [ 
   			{ label: 'effDt' , name: 'effDt', hidden:true, width : 0},
   			{ label: 'usgTyp' , name: 'usgTyp', hidden:true, width : 0},
   		    { label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'usgTypNm', width : 100},			//속성 코드
  	 		{ label: '<spring:message code="LAB.M15.LAB00023"/>', name: 'chrgCdSeq', align:"center", width : 100},			//속성 코드
  	 		{ label: '<spring:message code="LAB.M07.LAB00288"/>', name: 'seqNo', align:"center", width : 100},			//속성 코드
  	 		{ label: '<spring:message code="LAB.M15.LAB00022"/>', name: 'branchSeq', align:"center", width : 100},			//속성 코드
  	 		{ label: '<spring:message code="LAB.M15.LAB00021"/>', name: 'branchNm', align:"center", width : 100},			//속성 코드
  	 		{ label: '<spring:message code="LAB.M15.LAB00037"/>', name: 'funcCd', align:"center", width : 100},			//속성 코드
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
        	$("#ratingCodeRuleGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        	btnNonActive("btn_update");
        	btnNonActive("btn_delete");
        },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#ratingCodeRuleGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});

	$("#ratingCodeRuleGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#ratingCodeRuleGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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
<form id="ratingCodeRule" name="ratingCodeRule" method="post">
	<input type="hidden" id="branchSeq" name="branchSeq" />
	<input type="hidden" id="usgTyp" value="${param.searchUsgTyp}" name="usgTyp" />
	<input type="hidden" id="chrgCdSeq" value="${param.searchChrgCdSeq}" name="chrgCdSeq" />
	<input type="hidden" id="seqNo" value="${param.searchSeqNo}" name="seqNo" />
	<input type="hidden" id="effDt" name="effDt" />
	
	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M01.LAB00144"/>
				</h4>
			</div>
			<div class="fr mt10">
				<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:13%;">
			<col style="width:10%;">
			<col style="width:13%;">
			<col style="width:10%;">
			<col style="width:14%;">
		</colgroup>
		 <thead> 
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00029"/>
				</th>
				<td>
					<select id="searchUsgTyp" name="searchUsgTyp" class="w270">
						<option value="" >
							<spring:message code="LAB.M15.LAB00002"/>
						</option>
						<c:forEach items="${usgTypList}" var="usgTypList" varStatus="status">
							<option value="${usgTypList.commonCd}">
								${usgTypList.commonCdNm}								
							</option>
						</c:forEach>
					</select>
				</td>
				<th>
					<spring:message code="LAB.M15.LAB00023"/>
				</th>
				<td>
					<select id="searchChrgCdSeq" name="searchChrgCdSeq"  class="w270">
						<option value="">
							ALL
						</option>
						<c:forEach items="${chrgCdseqList}" var="chrgCdseqList" varStatus="status">
						<option value="${chrgCdseqList.commonCd}">
							${chrgCdseqList.commonCdNm}
						</option>
						</c:forEach>
					</select>
				</td>
				<th>
					<spring:message code="LAB.M07.LAB00288"/>
				</th>
				<td>
					<select id="searchSeqNo" name="searchSeqNo" class="w270">
						<option value="">
							ALL
						</option>
						<c:forEach items="${seqList}" var="seqList" varStatus="status">
						<option value="${seqList.commonCd}">
							${seqList.commonCdNm}
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
<table id="ratingCodeRuleGrid" class="w100p"></table>
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
<div id="popup_dialog" class="Layer_wrap" style="display:none; width:400px;" >
</div>