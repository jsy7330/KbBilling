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
		$("#attributeAction").attr("action", "/product/refInfo/commonInfo/attribute/deleteAction");
		$("#attributeAction").attr("method", "post");
		$("#attributeAction").submit();
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
		alert("수정버튼을 클릭하여 수정처리 하세요.");
		return;
	}
	if($("#attrNm").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00086"/>");
		$("#attrNm").focus();
		return;
	}
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
	if($("#ifSys").val() == "") {
		alert("<spring:message code="MSG.M08.MSG00012" />"); 
		$("#ifSys").focus();
		return;
	}
	if($("#refCd").val() == "") {
		alert("<spring:message code="MSG.M10.MSG00003"/>");
		return;
	}
	if($("#actDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00011"/>");
		return;
	}
	if($("#inactDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00013"/>");
		return;
	}
	if($("#actDt").val() < $("#nowDate").val() ) {
		alert("<spring:message code="MSG.M09.MSG00010"/>");
		return;
	}
	$("#attributeAction").attr("action", "/product/refInfo/commonInfo/attribute/attributeInsertAction");
	$("#attributeAction").attr("method", "post");
	$("#attributeAction").submit();
}

function goUpdate() {

	if($("#attrCd").val() == "") {
		alert("등록버튼을 클릭하여 신규처리 하세요.");
		return;
	}
	if($("#attrNm").val() == "") {
		alert("<spring:message code="MSG.M07.MSG00086"/>");
		$("#attrNm").focus();
		return;
	}
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
	if($("#ifSys").val() == "") {
		alert("<spring:message code="MSG.M08.MSG00012" />"); 
		$("#ifSys").focus();
		return;
	}
	if($("#refCd").val() == "") {
		alert("<spring:message code="MSG.M10.MSG00003"/>");
		return;
	}
	if($("#actDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00011"/>");
		return;
	}	
	if($("#actDt").val() > $("#nowDate").val() ) {
		alert("<spring:message code="MSG.M09.MSG00010"/>");
		return;
	}	
	if($("#inactDt").val() == "") {
		alert("<spring:message code="MSG.M09.MSG00013"/>");
		return;
	}


	var actDt = dateFormatToStringYYYYMMDD($('#actDt').val());
	var inactDt = dateFormatToStringYYYYMMDD($('#inactDt').val());
	var oldActDt = dateFormatToStringYYYYMMDD($('#oldActDt').val());
	$("#actDt").val(actDt);	
	$("#inactDt").val(inactDt);	
	$("#oldActDt").val(oldActDt);	

	$("#attributeAction").attr("action", "/product/refInfo/commonInfo/attribute/attributeUpdateAction");
	$("#attributeAction").attr("method", "post");
	$("#attributeAction").submit();
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
		   	datatype: "local",
		   	multiselect: true,
		   	colModel: [ 
	   			{ label: 'ifSys' , name: 'ifSys', hidden:true,width : 0},
	   			{ label: 'remark' , name: 'remark', hidden:true,width : 0},
	   		    /*{ label: '캘린더정의식별자', name: 'attrCd', width : 100},			//속성 코드
	  	 		{ label: '특수일명', name: 'attrCd', width : 100},			//속성 코드
	  	 		{ label: '특수일자', name: 'attrCd', width : 100},			//속성 코드
	  	 		{ label: '일자유형', name: 'attrCd', width : 100},*/			//속성 코드
	   			{ label: 'Calendar Definition Identifier', name: 'attrCd', width : 100},			//속성 코드
	  	 		{ label: 'Special Day Name', name: 'attrCd', width : 100},			//속성 코드
	  	 		{ label: 'Special Day', name: 'attrCd', width : 100},			//속성 코드
	  	 		{ label: 'Day Type', name: 'attrCd', width : 100},
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
				<!-- <h4 class="sub_title">특수일관리</h4>-->
				<h4 class="sub_title">Special Day</h4>
			</div>
			<div class="fr mt10">
			<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
			</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:30%;">
			<col style="width:40%;">
		</colgroup>
		 <thead> 
			   <tr>
					<!--<th>캘린더정의식별자</th>-->
					<th>Calendar Definition Identifier</th>
					<td>
						<select id="sel1" name="sel1" class="w270">
							<option></option>
						</select>
					</td>
				</tr>
		</thead>
	</table> 
</form>	

<!--JQ Grid 리스트-->	
<br>
<div id='gridDiv'>
<table id="attributeTable" class="w100p"></table>
	<div id="pager2"></div>
</div>


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



