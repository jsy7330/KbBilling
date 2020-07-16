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
	
	// 년월 레이어 datepicker
	$(".monthPicker").datepicker({
        dateFormat: 'yymm',
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,

        onClose: function(dateText, inst) {
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).val($.datepicker.formatDate('yymm', new Date(year, month, 1)));
        }
    });

	// 년월 레이어 focus
    $(".monthPicker").focus(function () {
        $(".ui-datepicker-calendar").hide();
        $("#ui-datepicker-div").position({
            my: "center top",
            at: "center bottom",
            of: $(this)
        });
    });
	
	// Grid 폭 조정
	$(window).resize(function() {
		$("#dunningCriteriaTable").setGridWidth($('#dunningCriteriaGrid').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	// 검색부 초기화 세팅
    $("input:checkbox[id='chkStrAll']").prop("checked", true);
	$("#inputStrAll").val("Y");
	$("input:checkbox[id='chkEndAll']").prop("checked", true);
	$("#inputEndAll").val("Y");
	$("#searchStrYymmTo").val("999912");
	$("#searchEndYymmTo").val("999912");
	
	// 상세정보 초기화 세팅
	$('#upymAmtFrom').number( true );
	$('#upymAmtTo').number( true );
	$('#upymMonthsFrom').number( true );
	$('#upymMonthsTo').number( true );
	$("#upymAmtFrom").val("0");
	$("#upymAmtTo").val("0");
	$("#upymMonthsFrom").val("0");
	$("#upymMonthsTo").val("0");
	
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
		var url="dunningCriteriaInsertPopUp.ajax";
		var param = new Object();
		
		//$("#popup_dialog").css("width", 800);
		openModalPopup(url, param);
	});
	
	//수정 버튼 click
	$("#btn_update").click(function() {
		var rowId  = $("#dunningCriteriaTable").jqGrid("getGridParam", "selrow" );
		if(rowId == null){
			alert('<spring:message code="MSG.M07.MSG00094"/>'); //수정할 기준정보를 선택해 주세요.
			return;
		}
		
		var url="dunningCriteriaUpdatePopUp.ajax";
		var param = new Object();
		
		//$("#popup_dialog").css("width", 800);
		openModalPopup(url, param);
	});
	
	//개월시작값 전체 click
	$("#chkStrAll").click(function() {
		if ($("#chkStrAll").is(":checked")) {
			$("#inputStrAll").val("Y");
		}
		else {
			$("#inputStrAll").val("N");
		}
		var vStrAll = $("#inputStrAll").val();
		setStrYymmTo(vStrAll);
	});
	
	//개월종료값 전체 click
	$("#chkEndAll").click(function() {
		if ($("#chkEndAll").is(":checked")) {
			$("#inputEndAll").val("Y");
		}
		else {
			$("#inputEndAll").val("N");
		}
		var vEndAll = $("#inputEndAll").val();
		setEndYymmTo(vEndAll);
	});

});


/* 페이지로딩시 jqgrid 호출 및 초기데이터 조회 세팅 */
function initGrid() {
	
	var param = new Object();
	param.searchSoId = $("#searchSoId").val();
	param.searchTpCd = $("#searchTpCd").val();
	param.searchStrYymmFrom = $("#searchStrYymmFrom").val();
	param.searchStrYymmTo = $("#searchStrYymmTo").val();
	param.searchEndYymmFrom = $("#searchEndYymmFrom").val();
	param.searchEndYymmTo = $("#searchEndYymmTo").val();
	
	$("#dunningCriteriaTable").jqGrid({
		
	   	url:'dunningCriteriaListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",

	   	colModel: [ 
   		    { label: '<spring:message code="LAB.M03.LAB00057"/>', name: 'upymMngTpNm', width : 100}, //대응유형
   		 	{ label: '<spring:message code="LAB.M01.LAB00222"/>', name: 'eftBgnYymm', align:'center', width : 110}, //기준시작년월
   			{ label: '<spring:message code="LAB.M01.LAB00224"/>', name: 'eftEndYymm', align:'center', width : 110}, //기준종료년월
   			{ label: '<spring:message code="LAB.M03.LAB00058"/>', name: 'upymMngBsTpNm', width : 100}, //대응유형기준
   			{ label: '<spring:message code="LAB.M01.LAB00017"/>', name: 'upymMonthsFrom', align:'right', width : 100}, //개월시작값
   			{ label: '<spring:message code="LAB.M01.LAB00018"/>', name: 'upymMonthsTo', align:'right', width : 100}, //개월종료값
   			{ label: '<spring:message code="LAB.M01.LAB00194"/>', name: 'upymAmtFrom', align:'right', width : 100}, //금액[율]시작값
   			{ label: '<spring:message code="LAB.M01.LAB00195"/>', name: 'upymAmtTo', align:'right', width : 100}, //금액[율]종료값
   			{ label: '<spring:message code="LAB.M06.LAB00093"/>', name: 'upymMngDesc', width : 100}, //비고
   			{ label: '<spring:message code="LAB.M03.LAB00083"/>', name: 'regrId', width : 80}, //등록자ID
   			{ label: '<spring:message code="LAB.M06.LAB00061"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, align:'center', width : 100}, //변경일자
   			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
   			{ label: 'soNm' , name: 'soNm', hidden:true,width : 0},
		    { label: 'upymMngTp' , name: 'upymMngTp', hidden:true,width : 0},
		    { label: 'upymMngBsTp' , name: 'upymMngBsTp', hidden:true,width : 0},
		    { label: 'chgrId' , name: 'chgrId', hidden:true,width : 0},
		    { label: 'chgDate' , name: 'regDate', hidden:true,width : 0},
   		],
   		
	   	rowNum:10,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#dunningCriteriaPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 240,					//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowid, index, contents, event){
			setSelected(rowid);
        },
        loadComplete: function(obj){
	    	$("#dunningCriteriaTable").setGridWidth($('#dunningCriteriaGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        }
	});
	
	$("#dunningCriteriaTable").setGridWidth($('#dunningCriteriaGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#dunningCriteriaTable").setGridWidth($('#dunningCriteriaGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

/* 데이터 reloadGrid trigger */
function searchData() {
	var vStrYymmTo = $("#searchStrYymmTo").val();
	var vEndYymmTo = $("#searchEndYymmTo").val();

	if(vStrYymmTo == ''){
		alert('<spring:message code="LAB.M01.LAB00017" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}
	
	if(vEndYymmTo == ''){
		alert('<spring:message code="LAB.M01.LAB00018" /> <spring:message code="MSG.M08.MSG00043" />');
		return false;
	}
	
	$("#dunningCriteriaTable").clearGridData();  // 이전 데이터 삭제
	var param = new Object();
	param.searchSoId = $("#searchSoId").val();
	param.searchTpCd = $("#searchTpCd").val();
	param.searchStrYymmFrom = $("#searchStrYymmFrom").val();
	param.searchStrYymmTo = $("#searchStrYymmTo").val();
	param.searchEndYymmFrom = $("#searchEndYymmFrom").val();
	param.searchEndYymmTo = $("#searchEndYymmTo").val();
	
    $("#dunningCriteriaTable").setGridParam({ postData: param }).trigger("reloadGrid");
    
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
	$("#upymMngBsTpNm").val('');
	$("#upymMngBsTp").val('');
	$("#upymAmtFrom").val('');
	$("#upymAmtTo").val('');
	$("#upymMonthsFrom").val('');
	$("#upymMonthsTo").val('');
	$("#eftBgnYymm").val('');
	$("#eftEndYymm").val('');

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

/* 개월시작값 변경 Control */
function setStrYymmTo(val) {
	if (val == "Y") {
		$("#searchStrYymmTo").val('999912');
	} else {
		$("#searchStrYymmTo").val('');
	}
}

/* 개월종료값 변경 Control */
function setEndYymmTo(val) {
	if (val == "Y") {
		$("#searchEndYymmTo").val('999912');
	} else {
		$("#searchEndYymmTo").val('');
	}
}

/* 기준정보Grid row 클릭시  상세정보 세팅 */
function setSelected(rowId) {	
	var data = $("#dunningCriteriaTable").getRowData(rowId);
	
	$("#soNm").val(data.soNm);
	$("#soId").val(data.soId);
	$("#upymMngTpNm").val(data.upymMngTpNm);
	$("#upymMngTp").val(data.upymMngTp);
	$("#upymMngBsTpNm").val(data.upymMngBsTpNm);
	$("#upymMngBsTp").val(data.upymMngBsTp);
	$("#upymAmtFrom").val(data.upymAmtFrom);
	$("#upymAmtTo").val(data.upymAmtTo);
	$("#upymMonthsFrom").val(data.upymMonthsFrom);
	$("#upymMonthsTo").val(data.upymMonthsTo);
	$("#eftBgnYymm").val(data.eftBgnYymm);
	$("#eftEndYymm").val(data.eftEndYymm);
	
	btnNonActive("btn_insert");	//등록버튼 비활성화
	btnActive("btn_update"); //수정버튼 활성화
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
				<th><spring:message code="LAB.M03.LAB00057"/><!-- 대응유형 --></th>
				<td>
					<select id="searchTpCd" name="searchTpCd" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${dunningTpCd}" var="dunningTpCd" varStatus="status">
							<option value="${dunningTpCd.commonCd}">${dunningTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00222"/><!-- 개월시작값 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w135">
							<input type="text" id="searchStrYymmFrom" name="searchStrYymmFrom" class="monthPicker" />
							<a href="#" class="btn_cal"></a>
						</div>
						<span class="dash">~</span>
						<div class="inp_date w135">
							<input type="text" id="searchStrYymmTo" name="searchStrYymmTo" class="monthPicker" />
							<a href="#" class="btn_cal"></a>
						</div>
						<div class="inp_date w135">
							<!-- 전체 -->
							<input type="checkbox" id="chkStrAll" name="chkStrAll" /> <label for="chkStrAll"><spring:message code="LAB.M09.LAB00063" /></label>
							<input type="hidden" id="inputStrAll" name="inputStrAll" class="w50" />
						</div>
					</div>
			    </td>
			    <th><spring:message code="LAB.M01.LAB00224"/><!-- 개월종료값 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w135">
							<input type="text" id="searchEndYymmFrom" name="searchEndYymmFrom" class="monthPicker" />
							<a href="#" class="btn_cal"></a>
						</div>
						<span class="dash">~</span>
						<div class="inp_date w135">
							<input type="text" id="searchEndYymmTo" name="searchEndYymmTo" class="monthPicker" />
							<a href="#" class="btn_cal"></a>
						</div>
						<div class="inp_date w135">
							<!-- 전체 -->
							<input type="checkbox" id="chkEndAll" name="chkEndAll" /> <label for="chkEndAll"><spring:message code="LAB.M09.LAB00063" /></label>
							<input type="hidden" id="inputEndAll" name="inputEndAll" class="w50" />
						</div>
					</div>
			    </td>
			</tr>
		</thead>
	</table>
	
	<div class="main_btn_box">
		<h4 class="sub_title"><spring:message code="LAB.M01.LAB00223"/><!-- 기준정보 --></h4>
	</div>
	
	<div id="dunningCriteriaGrid">
		<table id="dunningCriteriaTable" class="w100p"></table>
		<div id="dunningCriteriaPager"></div>
	</div>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00101"/><!-- 상세정보 --></h4>
		</div>
	</div>
	
	<table class="writeview" id="areaView" >
		<colgroup>
			<col style="width:15%;" />
			<col style="width:35%;" />
			<col style="width:15%;" />
			<col style="width:35%;" />
		</colgroup>
		<tbody>
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --> </th>
				<td colspan="3">
					<input type="text" id="soNm" name="soNm" class="w200" disabled="disabled" />
					<input type="hidden" id="soId" name="soId" class="w100" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00057"/><!-- 다응유형--></th>
				<td>
					<input type="text" id="upymMngTpNm" name="upymMngTpNm" class="w270" disabled="disabled" />
					<input type="hidden" id="upymMngTp" name="upymMngTp" class="w100" />
				</td>
				<th><spring:message code="LAB.M03.LAB00058"/><!-- 대응유형기준 --></th>
				<td>
					<input type="text" id="upymMngBsTpNm" name="upymMngBsTpNm" class="w270" disabled="disabled" />
					<input type="hidden" id="upymMngBsTp" name="upymMngBsTp" class="w100" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00193"/><!-- 금액[율] --></th>
				<td>
					<input type="text" id="upymAmtFrom" name="upymAmtFrom" maxlength="9" class="w100" disabled="disabled" />
					<span class="dash">~</span>
					<input type="text" id="upymAmtTo" name="upymAmtTo" maxlength="9" class="w100" disabled="disabled" />
				</td>
				<th><spring:message code="LAB.M01.LAB00016"/><!-- 개월 --></th>
				<td>
					<input type="text" id="upymMonthsFrom" name="upymMonthsFrom" maxlength="3" class="w100" disabled="disabled" />
					<span class="dash">~</span>
					<input type="text" id="upymMonthsTo" name="upymMonthsTo" maxlength="3" class="w100" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M01.LAB00222"/><!-- 기준시작년월 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w100">
							<input type="text" id="eftBgnYymm" name="eftBgnYymm" class="monthPicker" disabled="disabled" />
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M01.LAB00224"/><!-- 기준종료년월 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w100">
							<input type="text" id="eftEndYymm" name="eftEndYymm" class="monthPicker" disabled="disabled" />
						</div>
					</div>
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
	
</form>	

<div id="pop_layer" style="height:600px;width:500px;overflow-x:none;overflow-y:auto;display:none;"></div>       

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>



