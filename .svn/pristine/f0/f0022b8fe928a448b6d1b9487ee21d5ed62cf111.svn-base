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
	
	$('#usgTyp').selectmenu({
        change: function() {
        	$("#searchUsgTyp").val($("#usgTyp").val());
        }
	});
	
	$('#chrgCdSeq').selectmenu({
        change: function() {
        	$("#searchChrgCdSeq").val($("#chrgCdSeq").val());
        }
	});
	
	$('#seqNo').selectmenu({
        change: function() {
        	$("#searchSeqNo").val($("#seqNo").val());
        }
	});
	
	$("#usgTyp").val($("#searchUsgTyp").val());
	$("#usgTyp").selectmenu("refresh");
	$("#chrgCdSeq").val($("#searchChrgCdSeq").val());
	$("#chrgCdSeq").selectmenu("refresh");
	$("#seqNo").val($("#searchSeqNo").val());
	$("#seqNo").selectmenu("refresh");

	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});
	
	$("#btn_insert").click(function() {
		goInsert();
	});
	
	$("#btn_update").click(function() {
		goUpdate();
	});
	
	//삭제처리
	$("#btn_delete").click(function() {
		goDelete();
	});

	$(window).resize(function() {
		$("#ratingCodeMapGrid").setGridWidth($('#gridDiv').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	} 
});

function goSearch() {
	var param = new Object();
	
	$("#ratingCodeMap").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeMap/ratingCodeMapList");
	$("#ratingCodeMap").attr("method", "post");
	$("#ratingCodeMap").submit();
}

function goInsert() {
	var param = new Object();
	var url = "ratingCodeMapInsertPopUp.ajax";
	$("#popup_dialog").css("width", 1500);
	openModalPopup(url, param);
}

function goUpdate() {
	var rowId = jQuery("#ratingCodeMapGrid").jqGrid('getGridParam', 'selrow');	

	if( rowId == null ){
		alert("<spring:message code="MSG.M07.MSG00096" />");
		return;
	}
		
	var param = new Object();
	param.usgTyp = $("#ratingCodeMapGrid").getRowData(rowId).usgTyp;
	param.chrgCdSeq = $("#ratingCodeMapGrid").getRowData(rowId).chrgCdSeq;
	param.seqNo = $("#ratingCodeMapGrid").getRowData(rowId).seqNo;
	param.branch1 = $("#ratingCodeMapGrid").getRowData(rowId).branch1;
	param.branch2 = $("#ratingCodeMapGrid").getRowData(rowId).branch2;
	param.branch3 = $("#ratingCodeMapGrid").getRowData(rowId).branch3;
	param.branch4 = $("#ratingCodeMapGrid").getRowData(rowId).branch4;
	param.branch5 = $("#ratingCodeMapGrid").getRowData(rowId).branch5;
	param.effDt = $("#ratingCodeMapGrid").getRowData(rowId).effDt;
	param.expDt = $("#ratingCodeMapGrid").getRowData(rowId).expDt;
	param.chrgCd = $("#ratingCodeMapGrid").getRowData(rowId).chrgCd;
	param.billFlag = $("#ratingCodeMapGrid").getRowData(rowId).billFlag;
	
	var url = "ratingCodeMapUpdatePopUp.ajax";
	$("#popup_dialog").css("width", 1500);
	openModalPopup(url, param);
}

function goDelete() {
	
	var rowId = jQuery("#ratingCodeMapGrid").jqGrid('getGridParam', 'selrow');
	if( rowId == null ) {
		alert("<spring:message code="MSG.M07.MSG00056"/>");
		return;
	}	
	
	$("#delUsgTyp").val($("#ratingCodeMapGrid").getRowData(rowId).usgTyp);
	$("#delChrgCdSeq").val($("#ratingCodeMapGrid").getRowData(rowId).chrgCdSeq);
	$("#delSeqNo").val($("#ratingCodeMapGrid").getRowData(rowId).seqNo);
	$("#delBranch1").val($("#ratingCodeMapGrid").getRowData(rowId).branch1);
	$("#delBranch2").val($("#ratingCodeMapGrid").getRowData(rowId).branch2);
	$("#delBranch3").val($("#ratingCodeMapGrid").getRowData(rowId).branch3);
	$("#delBranch4").val($("#ratingCodeMapGrid").getRowData(rowId).branch4);
	$("#delBranch5").val($("#ratingCodeMapGrid").getRowData(rowId).branch5);
	$("#delEffDt").val($("#ratingCodeMapGrid").getRowData(rowId).effDt);

	
	if( confirm("<spring:message code="MSG.M07.MSG00054" />") == true ) {
		$("#ratingCodeMap").attr("action", "/product/refInfo/ratingRefInfo/ratingCodeMap/ratingCodeMapDeleteAction");
		$("#ratingCodeMap").attr("method", "post");
		$("#ratingCodeMap").submit();
	}
	
}

function initGrid() {
		var param = new Object();
		param.searchUsgTyp = $("#searchUsgTyp").val();
		param.searchChrgCdSeq = $("#searchChrgCdSeq").val();
		param.searchSeqNo = $("#searchSeqNo").val();

		$("#ratingCodeMapGrid").jqGrid({
		   	url:'ratingCodeMapListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
		   	colModel: [
				{ label: '<spring:message code="LAB.M01.LAB00141" />', name: 'chrgCd', width : 100, hidden:true },	
	   		    { label: '<spring:message code="LAB.M07.LAB00029" />', name: 'usgTyp', align:"center", width : 100, hidden:true },	
	   		 	{ label: '<spring:message code="LAB.M07.LAB00029" />', name: 'usgTypNm', align:"center", width : 100},		
	  	 		{ label: '<spring:message code="LAB.M15.LAB00023" />', name: 'chrgCdSeq', align:"center", width : 100},
	  	 		{ label: '<spring:message code="LAB.M07.LAB00288" />', name: 'seqNo', align:"center", width : 100},		
	  	 		{ label: '<spring:message code="LAB.M15.LAB00016" />', name: 'branch1', width : 80},	
	  	 		{ label: '<spring:message code="LAB.M15.LAB00017" />', name: 'branch2', width : 80},	
	  	 		{ label: '<spring:message code="LAB.M15.LAB00018" />', name: 'branch3', width : 80},	
	  	 		{ label: '<spring:message code="LAB.M15.LAB00019" />', name: 'branch4', width : 80},	
	  	 		{ label: '<spring:message code="LAB.M15.LAB00020" />', name: 'branch5', width : 80},	
	  	 		{ label: '<spring:message code="LAB.M08.LAB00108" />', name: 'effDt', align:"center", width : 100},		
	  	 		{ label: '<spring:message code="LAB.M08.LAB00110" />', name: 'expDt', align:"center", width : 100},		
	  	 		{ label: '<spring:message code="LAB.M01.LAB00141" />', name: 'description', align:"center", width : 120},			
	  	 		{ label: '<spring:message code="LAB.M01.LAB00117" />', name: 'billFlag', align:"center", width : 100}			
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
	        	setSelectedDate(rowId);
	        },
	        loadComplete: function(obj){
	        	$("#ratingCodeMapGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        	btnNonActive("btn_update");
	        	btnNonActive("btn_delete");
	        },
			loadError: function (jqXHR, textStatus, errorThrown) {
				ajaxErrorCallback(jqXHR);
				
			},
			sortable: { update: function(permutation) {
				$("#ratingCodeMapGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
				}
			}
	});

	$("#ratingCodeMapGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#ratingCodeMapGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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

//상세정보 
function setSelectedDate(rowId){		
	var data = $("#ratingCodeMapGrid").getRowData(rowId);
	
	$("#delUsgTyp").val(data.usgTyp);
	$("#delChrgCdSeq").val(data.chrgCdSeq);
	$("#delSeqNo").val(data.seqNo);
	$("#delBranch1").val(data.branch1);
	$("#delBranch2").val(data.branch2);
	$("#delBranch3").val(data.branch3);
	$("#delBranch4").val(data.branch4);
	$("#delBranch5").val(data.branch5);
	$("#delEffDt").val(data.effDt);
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
<form id="ratingCodeMap" name="ratingCodeMap" method="post">
	<input type="hidden" id="searchUsgTyp" name="searchUsgTyp" value="${param.searchUsgTyp }" />
	<input type="hidden" id="searchChrgCdSeq" name="searchChrgCdSeq" value="${param.searchChrgCdSeq }" />
	<input type="hidden" id="searchSeqNo" name="searchSeqNo" value="${param.searchSeqNo }" />
	<input type="hidden" id="delUsgTyp" name="delUsgTyp" />
	<input type="hidden" id="delChrgCdSeq" name="delChrgCdSeq" />
	<input type="hidden" id="delSeqNo" name="delSeqNo" />
	<input type="hidden" id="delBranch1" name="delBranch1" />
	<input type="hidden" id="delBranch2" name="delBranch2" />
	<input type="hidden" id="delBranch3" name="delBranch3" />
	<input type="hidden" id="delBranch4" name="delBranch4" />
	<input type="hidden" id="delBranch5" name="delBranch5" />
	<input type="hidden" id="delEffDt" name="delEffDt" />

	<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title">
					<spring:message code="LAB.M01.LAB00145" />
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
						<spring:message code="LAB.M07.LAB00029" />
					</th>
					<td>
						<select id="usgTyp" name="usgTyp" class="w270">
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
						<spring:message code="LAB.M15.LAB00023" />
					</th>
					<td>
						<select id="chrgCdSeq" name="chrgCdSeq" class="w270">
							<option value="" >
							<spring:message code="LAB.M15.LAB00002"/>
							</option>
							<c:forEach items="${seqList}" var="seqList" varStatus="status">
								<option value="${seqList.commonCd}">
									${seqList.commonCdNm}								
								</option>
							</c:forEach>
						</select>
					</td>
					<th>
						<spring:message code="LAB.M07.LAB00288" />
					</th>
					<td>
						<select id="seqNo" name="seqNo" class="w270">
							<option value="" >
							<spring:message code="LAB.M15.LAB00002"/>
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
<table id="ratingCodeMapGrid" class="w100p"></table>
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
		<ntels:auth auth="${menuAuthC}">
		<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>'><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>		
		<!--삭제-->
		<ntels:auth auth="${menuAuthD}">
		<a class="grey-btn" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
	</div>
</div>
<div id="popup_dialog" class="Layer_wrap" style="display:none; width:400px;" >
</div>       



