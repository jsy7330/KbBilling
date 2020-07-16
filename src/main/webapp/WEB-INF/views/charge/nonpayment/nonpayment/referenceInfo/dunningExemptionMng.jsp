<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

/* 페이지 로딩시 화면 세팅 */
$(document).ready(function() {
	
	//jqgrid 호출
	initGrid();	
	
	//조회 click
	$("#btn_search").click(function() {
		searchData();
		inputClear();
	});
	
	// Grid 폭 조정
	$(window).resize(function() {
		$("#dunningExemptionTable").setGridWidth($('#dunningExemptionGrid').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	btnActive("btn_init"); //초기화버튼 활성화
	btnNonActive("btn_insert"); //등록버튼 비활성화
	btnNonActive("btn_update"); //수정버튼 비활성화
	
	//초기화 버튼 이벤트
	$('#btn_init').click(function() {
		if($("#btn_init").hasClass('not-active')){
			return;
		}
		reset();
	});
	
	//등록 버튼 click
	$("#btn_insert").click(function() {
		var url="dunningExemptionInsertPopUp.ajax";
		var param = new Object();
		
		//$("#popup_dialog").css("width", 800);
		openModalPopup(url, param);
	});
	
	//수정 버튼 click
	$("#btn_update").click(function() {
		var rowId  = $("#dunningExemptionTable").jqGrid("getGridParam", "selrow" );
		if(rowId == null){
			alert('<spring:message code="MSG.M07.MSG00107"/>'); //수정할 조치제외정보를 선택해 주세요.
			return;
		}
		
		var url="dunningExemptionUpdatePopUp.ajax";
		var param = new Object();
		
		//$("#popup_dialog").css("width", 800);
		openModalPopup(url, param);
	});
	
	// 고객조회
	$('#btnCustSearch').on('click',function (e) {
		if($("#btnCustSearch").hasClass('not-active')){
			return;
		}
		openCustSearchPopup();	
	});

});


/* 페이지로딩시 jqgrid 호출 및 초기데이터 조회 세팅 */
function initGrid() {
	
	var param = new Object();
	param.searchSoId = $("#searchSoId").val();
	param.searchCustId = $("#searchCustId").val();
	
	$("#dunningExemptionTable").jqGrid({
		
	   	url:'dunningExemptionListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	
	   	colModel: [ 
   		    { label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'ctrtId', align:'center', width : 80}, //계약ID
   		 	{ label: '<spring:message code="LAB.M14.LAB00067"/>', name: 'svcTelNo', align:'center', width : 80}, //회선번호
   			{ label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'custId', align:'center', width : 80}, //고객ID
   			{ label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'custNm', width : 200}, //고객명
   			{ label: '<spring:message code="LAB.M03.LAB00057"/>', name: 'upymMngTpNm', width : 100}, //대응유형
   			{ label: '<spring:message code="LAB.M09.LAB00096"/>', name: 'excpStrtYymmdd', align:'center', width : 110}, //제외시작일
   			{ label: '<spring:message code="LAB.M09.LAB00097"/>', name: 'excpEndYymmdd', align:'center', width : 110}, //제외종료일
   			{ label: '<spring:message code="LAB.M09.LAB00095"/>', name: 'upymExcpRsnNm', width : 100}, //제외사유
   			{ label: '<spring:message code="LAB.M09.LAB00094"/>', name: 'excpCt', hidden:true,width : 0}, //제외내역
   			{ label: '<spring:message code="LAB.M03.LAB00079"/>', name: 'regDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, align:'center', width : 100}, //등록일
   			{ label: '<spring:message code="LAB.M03.LAB00083"/>', name: 'regId', width : 80}, //등록자ID
   			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
   			{ label: 'soNm' , name: 'soNm', hidden:true,width : 0},
   			{ label: 'pymCustId' , name: 'pymCustId', hidden:true,width : 0},
   			{ label: 'pymAcntId' , name: 'pymAcntId', hidden:true,width : 0},
		    { label: 'upymMngTp' , name: 'upymMngTp', hidden:true,width : 0},
		    { label: 'upymExcpRsnCd' , name: 'upymExcpRsnCd', hidden:true,width : 0},
		    { label: 'chgId' , name: 'chgId', hidden:true,width : 0},
		    { label: 'chgDate' , name: 'regDate', hidden:true,width : 0},
   		],
   		
	   	rowNum:10,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#dunningExemptionPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 240,					//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowid, index, contents, event){
			setSelected(rowid);
        },
        loadComplete: function(obj){
	    	$("#dunningExemptionTable").setGridWidth($('#dunningExemptionGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        }
	});
	
	$("#dunningExemptionTable").setGridWidth($('#dunningExemptionGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#dunningExemptionTable").setGridWidth($('#dunningExemptionGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

/* 데이터 reloadGrid trigger */
function searchData() {
	var vSoId = $("#searchSoId").val();
	var vCustId = $("#searchCustId").val();

	if(vSoId == ''){
		alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}
	
	$("#dunningExemptionTable").clearGridData();  // 이전 데이터 삭제
	var param = new Object();
	param.searchSoId = $("#searchSoId").val();
	param.searchCustId = $("#searchCustId").val();
	
    $("#dunningExemptionTable").setGridParam({ postData: param }).trigger("reloadGrid");
    
    btnNonActive("btn_insert");	//등록버튼 비활성화
	btnNonActive("btn_update");	//수정버튼 비활성화
}

/* 데이터 reloadGrid trigger시 상세정보 초기화 */
function inputClear(){
	$("#areaView input").val("");
}

/* 초기화 Control */
function reset(){
	$("#soNm").val('');
	$("#soId").val('');
	$("#upymMngTpNm").val('');
	$("#upymMngTp").val('');
	$("#custId").val('');
	$("#custNm").val('');
	$("#ctrtId").val('');
	$("#pymCustId").val('');
	$("#pymAcntId").val('');
	$("#upymExcpRsnNm").val('');
	$("#upymExcpRsnCd").val('');
	$("#excpCt").val('');
	$("#excpStrtYymmdd").val('');
	$("#excpEndYymmdd").val('');

	btnActive("btn_insert");	//등록버튼 활성화
	btnNonActive("btn_update");	//수정버튼 비활성화
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

/* 기준정보Grid row 클릭시  상세정보 세팅 */
function setSelected(rowId) {	
	var data = $("#dunningExemptionTable").getRowData(rowId);
	
	$("#soNm").val(data.soNm);
	$("#soId").val(data.soId);
	$("#upymMngTpNm").val(data.upymMngTpNm);
	$("#upymMngTp").val(data.upymMngTp);
	$("#upymExcpRsnNm").val(data.upymExcpRsnNm);
	$("#upymExcpRsnCd").val(data.upymExcpRsnCd);
	$("#custId").val(data.custId);
	$("#custNm").val(data.custNm);
	$("#ctrtId").val(data.ctrtId);
	$("#pymCustId").val(data.pymCustId);
	$("#pymAcntId").val(data.pymAcntId);
	$("#excpStrtYymmdd").val(data.excpStrtYymmdd);
	$("#excpEndYymmdd").val(data.excpEndYymmdd);
	$("#excpCt").val(data.excpCt);
	
	btnNonActive("btn_insert");	//등록버튼 비활성화
	btnActive("btn_update"); //수정버튼 활성화
}

/*
 * 고객조회팝업
 */
function openCustSearchPopup(){
	
	$.ajax({
		type : "post",
		url : '/system/common/common/customerSearch/customerSearchPopup.ajax',
		data : {
			 inputSoId : $('#searchSoId').val()       //input SO Id
			,inputCustNm : $('#searchCustNm').val()   //input Customer Name
			,inputIsUnmaskYn : $('#isUnmaskYn').val() //마스크 처리 해제 Y
			,outputSoId : 'searchSoId'                //output SO ID Select
			,outputCustNm : 'searchCustNm'            //output Customer Name Text
			,outputCustId : 'searchCustId'            //output Customer ID Text

		},
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // 팝업 오픈
			$("#txtCustSearchCustNm").focus(); //오픈 후 focus위치
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
<form id="attribute" name="attribute" method="post">
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>

	<div class="main_btn_box">
		<div class="fr mt10">
			<a href="#" id="btn_search" class="grey-btn " ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
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
				<th><spring:message code="LAB.M01.LAB00045"/><!-- 고객 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w130">
							<input id="searchCustId" type="text" class="w100" />
							<ntels:auth auth="${menuAuthR}">
								<a id="btnCustSearch" href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
							</ntels:auth>
						</div>
						<div class="inp_date w230">
							<input id="searchCustNm" type="text" class="w200" />
						</div>
					</div>
				</td>
			</tr>
		</thead>
	</table>
	
	<div class="main_btn_box">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00156"/><!-- 조치제외내역 --></h4>
	</div>
	
	<div id="dunningExemptionGrid">
		<table id="dunningExemptionTable" class="w100p"></table>
		<div id="dunningExemptionPager"></div>
	</div>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00101"/><!-- 상세내역 --></h4>
		</div>
	</div>
	
	<table class="writeview" id="areaView" >
		<colgroup>
			<col style="width:12%;" />
			<col style="width:20%;" />
			<col style="width:12%;" />
			<col style="width:20%;" />
			<col style="width:12%;" />
			<col style="width:24%;" />
		</colgroup>
		<tbody>
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --> </th>
				<td colspan="5">
					<input type="text" id="soNm" name="soNm" class="w200" disabled="disabled" />
					<input type="hidden" id="soId" name="soId" class="w100" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00057"/><!-- 다응유형--></th>
				<td>
					<input type="text" id="upymMngTpNm" name="upymMngTpNm" class="w270" disabled="disabled" />
					<input type="hidden" id="upymMngTp" name="upymMngTp" class="w150" />
				</td>
				<th><spring:message code="LAB.M01.LAB00045"/><!-- 고객 --></th>
				<td colspan="3">
					<input type="text" id="custNm" name="custNm" class="w270" disabled="disabled" />
					<input type="hidden" id="custId" name="custId" class="w100" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00095"/><!-- 제외사유 --></th>
				<td>
					<input type="text" id="upymExcpRsnNm" name="upymExcpRsnNm" class="w270" disabled="disabled" />
					<input type="hidden" id="upymExcpRsnCd" name="upymExcpRsnCd" class="w100" />
				</td>
				<th><spring:message code="LAB.M01.LAB00032"/><!-- 계약ID --></th>
				<td>
					<input type="text" id="ctrtId" name="ctrtId" class="w100" disabled="disabled" />
				</td>
				<th><spring:message code="LAB.M02.LAB00012"/><!-- 납부고객/계정ID --></th>
				<td>
					<input type="text" id="pymCustId" name="pymCustId" class="w100" disabled="disabled" />
					/
					<input type="text" id="pymAcntId" name="pymAcntId" class="w100" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00096"/><!-- 제외시작일 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w100">
							<input type="text" id="excpStrtYymmdd" name="excpStrtYymmdd" class="datepicker" disabled="disabled" />
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00097"/><!-- 제외종료일 --></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date w100">
							<input type="text" id="excpEndYymmdd" name="excpEndYymmdd" class="datepicker" disabled="disabled" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00094"/><!-- 제외내역상세 --></th>
				<td colspan="5">
					<input type="text" id="excpCt" name="excpCt" class="w800" disabled="disabled" />
				</td>
			</tr>
		</tbody>
	</table>
	
	<!--버튼-->	
	<div class="main_btn_box">
		<div class="fr">
			<!--초기화-->		
			<a class="grey-btn" href="#" id="btn_init"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
			<!--등록-->
			<ntels:auth auth="${menuAuthC}">
				<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>		
			<!--수정-->
			<ntels:auth auth="${menuAuthU}">
				<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
			</ntels:auth>
		</div>
	</div>
<input id="isUnmaskYn" value='' type='text' hidden />	
</form>	

<div id="pop_layer" style="height:600px;width:500px;overflow-x:none;overflow-y:auto;display:none;"></div>       

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>



