<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

/* 페이지 로딩시 화면 세팅 */
$(document).ready(function() {
	
	// 검색부 초기화 세팅
    $("input:checkbox[id='chkStrAll']").prop("checked", false);
	$("#inputStrAll").val("N");
	$("input:checkbox[id='chkEndAll']").prop("checked", true);
	$("#inputEndAll").val("Y");
	var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
	$("#searchExcpStrtYymmddFrom").val(stringToDateformatYYYYMMDD(yyyymmdd));
	$("#searchExcpStrtYymmddTo").val(stringToDateformatYYYYMMDD(yyyymmdd));
	$("#searchExcpEndYymmddFrom").val(stringToDateformatYYYYMMDD(yyyymmdd));
	$("#searchExcpEndYymmddTo").val(stringToDateformatYYYYMMDD("99991231"));
		
	btnNonActive("btn_update"); //수정버튼 비활성화
	btnNonActive("btn_delete"); //삭제버튼 비활성화
	
	//jqgrid 호출
	initGrid();	
	
	//조회 click
	$("#btn_search").click(function() {
		searchData();
		inputClear();
	});
	
	// Grid 폭 조정
	$(window).resize(function() {
		$("#dunningExemptionListTable").setGridWidth($('#dunningExemptionListGrid').width()); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	//수정 버튼 click
	$("#btn_update").click(function() {
		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		
		if(!check){
			return;
		}
		
		var param = checkValidation();
		var url = "";
		url = "updateDunningExemptionListInfo.json";
		
		if(param != false){
			
			$.ajax({
				url : url,
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.resule != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();//부모창 새로고침
						inputClear();
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		}
	});
	
	//삭제 버튼 click
	$("#btn_delete").click(function() {
		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');
		
		if(!check){
			return;
		}
		
		var param = checkValidation();
		var url = "";
		url = "deleteDunningExemptionListInfo.json";
		
		if(param != false){
			
			$.ajax({
				url : url,
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.resule != "0"){
						alert('<spring:message code="MSG.M07.MSG00053" />');	//삭제되었습니다.
						
						searchData();//부모창 새로고침
						inputClear();
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		}
	});
	
	// 고객조회
	$('#btnCustSearch').on('click',function (e) {
		if($("#btnCustSearch").hasClass('not-active')){
			return;
		}
		openCustSearchPopup();	
	});
	
	//시작값 전체 click
	$("#chkStrAll").click(function() {
		if ($("#chkStrAll").is(":checked")) {
			$("#inputStrAll").val("Y");
		}
		else {
			$("#inputStrAll").val("N");
		}
		var vStrAll = $("#inputStrAll").val();
		setStrYymmddTo(vStrAll);
	});
	
	//종료값 전체 click
	$("#chkEndAll").click(function() {
		if ($("#chkEndAll").is(":checked")) {
			$("#inputEndAll").val("Y");
		}
		else {
			$("#inputEndAll").val("N");
		}
		var vEndAll = $("#inputEndAll").val();
		setEndYymmddTo(vEndAll);
	});

});

//밸리데이션 체크
function checkValidation(){
	var param = new Object();

	param.soId = $("#soId").val();
	param.upymMngTp = $("#upymMngTp").val();
	param.upymExcpRsnCd = $("#upymExcpRsnCd").val();
	param.custId = $("#custId").val();
	param.ctrtId = $("#ctrtId").val();
	param.excpStrtYymmddNew = dateFormatToStringYYYYMMDD($("#excpStrtYymmddNew").val());
	param.excpEndYymmddNew = dateFormatToStringYYYYMMDD($("#excpEndYymmddNew").val());
	param.excpStrtYymmdd = $("#excpStrtYymmdd").val();
	param.excpEndYymmdd = $("#excpEndYymmdd").val();
	param.excpCt = $("#excpCt").val();
	
	return param;
}


/* 페이지로딩시 jqgrid 호출 및 초기데이터 조회 세팅 */
function initGrid() {
	
	var param = new Object();
	param.searchSoId = $("#searchSoId").val();
	param.searchCustId = $("#searchCustId").val();
	
	param.searchExcpStrtYymmddFrom = dateFormatToStringYYYYMMDD($("#searchExcpStrtYymmddFrom").val());
	param.searchExcpStrtYymmddTo = dateFormatToStringYYYYMMDD($("#searchExcpStrtYymmddTo").val());
	param.searchExcpEndYymmddFrom = dateFormatToStringYYYYMMDD($("#searchExcpEndYymmddFrom").val());
	param.searchExcpEndYymmddTo = dateFormatToStringYYYYMMDD($("#searchExcpEndYymmddTo").val());
	
	param.searchUpymMngTp = $("#searchUpymMngTp").val();
	param.searchUpymExcpRsnCd = $("#searchUpymExcpRsnCd").val();
	
	$("#dunningExemptionListTable").jqGrid({
		
	   	url:'dunningExemptionListAction.json?',
	   	postData : param,
	    mtype:"POST",
	   	datatype: "json",
	   	
	   	colModel: [ 
   			{ label: '<spring:message code="LAB.M01.LAB00046"/>', name: 'custId', align:'center', width : 80}, //고객ID
   			{ label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'custNm', width : 200}, //고객명
   			{ label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'ctrtId', align:'center', width : 80}, //계약ID
   			{ label: '<spring:message code="LAB.M14.LAB00067"/>', name: 'svcTelNo', align:'center', width : 80}, //회선번호
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
	   	pager: '#dunningExemptionListPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 240,					//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowid, index, contents, event){
			setSelected(rowid);
        },
        loadComplete: function(obj){
	    	$("#dunningExemptionListTable").setGridWidth($('#dunningExemptionListGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        }
	});
	
	$("#dunningExemptionListTable").setGridWidth($('#dunningExemptionListGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#dunningExemptionListTable").setGridWidth($('#dunningExemptionListGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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
	
	$("#dunningExemptionListTable").clearGridData();  // 이전 데이터 삭제
	var param = new Object();
	param.searchSoId = $("#searchSoId").val();
	param.searchCustId = $("#searchCustId").val();
	param.searchExcpStrtYymmddFrom = dateFormatToStringYYYYMMDD($("#searchExcpStrtYymmddFrom").val());
	param.searchExcpStrtYymmddTo = dateFormatToStringYYYYMMDD($("#searchExcpStrtYymmddTo").val());
	param.searchExcpEndYymmddFrom = dateFormatToStringYYYYMMDD($("#searchExcpEndYymmddFrom").val());
	param.searchExcpEndYymmddTo = dateFormatToStringYYYYMMDD($("#searchExcpEndYymmddTo").val());
	param.searchUpymMngTp = $("#searchUpymMngTp").val();
	param.searchUpymExcpRsnCd = $("#searchUpymExcpRsnCd").val();
	
    $("#dunningExemptionListTable").setGridParam({ postData: param }).trigger("reloadGrid");
    
	btnNonActive("btn_update");	//수정버튼 비활성화
	btnNonActive("btn_delete"); //삭제버튼 비활성화
	$("#excpStrtYymmddNew").attr('disabled',true);
	$("#excpEndYymmddNew").attr('disabled',true);
}

/* 데이터 reloadGrid trigger시 상세정보 초기화 */
function inputClear(){
	$("#areaView input").val("");
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
function setStrYymmddTo(val) {
	if (val == "Y") {
		$("#searchExcpStrtYymmddTo").val(stringToDateformatYYYYMMDD("99991231"));
	} else {
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#searchExcpStrtYymmddTo").val(stringToDateformatYYYYMMDD(yyyymmdd));
	}
}

/* 개월종료값 변경 Control */
function setEndYymmddTo(val) {
	if (val == "Y") {
		$("#searchExcpEndYymmddTo").val(stringToDateformatYYYYMMDD("99991231"));
	} else {
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#searchExcpEndYymmddTo").val(stringToDateformatYYYYMMDD(yyyymmdd));
	}
}

/* 기준정보Grid row 클릭시  상세정보 세팅 */
function setSelected(rowId) {	
	var data = $("#dunningExemptionListTable").getRowData(rowId);
	
	$("#soId").val(data.soId);
	$("#upymMngTp").val(data.upymMngTp);
	$("#upymExcpRsnCd").val(data.upymExcpRsnCd);
	$("#custId").val(data.custId);
	$("#ctrtId").val(data.ctrtId);
	$("#excpStrtYymmddNew").attr('disabled',false);
	$("#excpStrtYymmddNew").val(stringToDateformatYYYYMMDD(data.excpStrtYymmdd));
	$("#excpEndYymmddNew").attr('disabled',false);
	$("#excpEndYymmddNew").val(stringToDateformatYYYYMMDD(data.excpEndYymmdd));
	$("#excpCt").val(data.excpCt);
	$("#excpStrtYymmdd").val(data.excpStrtYymmdd);
	$("#excpEndYymmdd").val(data.excpEndYymmdd);
	
	btnActive("btn_update"); //수정버튼 활성화
	btnActive("btn_delete"); //삭제버튼 활성화
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
	
	<input type="hidden" id="soId" name="soId" />
	<input type="hidden" id="upymMngTp" name="upymMngTp" />
	<input type="hidden" id="upymExcpRsnCd" name="upymExcpRsnCd" />
	<input type="hidden" id="custId" name="custId" />
	<input type="hidden" id="ctrtId" name="ctrtId" />
	<input type="hidden" id="excpStrtYymmdd" name="excpStrtYymmdd" />
	<input type="hidden" id="excpEndYymmdd" name="excpEndYymmdd" />
	
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
			<tr>
				<th><spring:message code="LAB.M09.LAB00096"/><!-- 제외시작일 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w135">
							<input type="text" id="searchExcpStrtYymmddFrom" name="searchExcpStrtYymmddFrom" class="datepicker" />
							<a href="#" class="btn_cal"></a>
						</div>
						<span class="dash">~</span>
						<div class="inp_date w135">
							<input type="text" id="searchExcpStrtYymmddTo" name="searchExcpStrtYymmddTo" class="datepicker" />
							<a href="#" class="btn_cal"></a>
						</div>
						<div class="inp_date w135">
							<!-- 전체 -->
							<input type="checkbox" id="chkStrAll" name="chkStrAll" /> <label for="chkStrAll"><spring:message code="LAB.M09.LAB00063" /></label>
							<input type="hidden" id="inputStrAll" name="inputStrAll" class="w50" />
						</div>
					</div>
			    </td>
			    <th><spring:message code="LAB.M09.LAB00097"/><!-- 제외종료일 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w135">
							<input type="text" id="searchExcpEndYymmddFrom" name="searchExcpEndYymmddFrom" class="datepicker" />
							<a href="#" class="btn_cal"></a>
						</div>
						<span class="dash">~</span>
						<div class="inp_date w135">
							<input type="text" id="searchExcpEndYymmddTo" name="searchExcpEndYymmddTo" class="datepicker" />
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
			<tr>
				<th><spring:message code="LAB.M03.LAB00057"/><!-- 다응유형--></th>
				<td>
					<select id="searchUpymMngTp" name="searchUpymMngTp" class="w180">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${dunningTpCd}" var="dunningTpCd" varStatus="status">
							<option value="${dunningTpCd.commonCd}">${dunningTpCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M09.LAB00095"/><!-- 제외사유 --></th>
				<td>
					<select id="searchUpymExcpRsnCd" name="searchUpymExcpRsnCd" class="w180">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${dunningTpDtlCd}" var="dunningTpDtlCd" varStatus="status">
							<option value="${dunningTpDtlCd.commonCd}">${dunningTpDtlCd.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</thead>
	</table>
	
	<div class="main_btn_box">
		<h4 class="sub_title"><spring:message code="LAB.M09.LAB00156"/><!-- 조치제외내역 --></h4>
	</div>
	
	<div id="dunningExemptionListGrid">
		<table id="dunningExemptionListTable" class="w100p"></table>
		<div id="dunningExemptionListPager"></div>
	</div>
	
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00101"/><!-- 상세내역 --></h4>
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
				<th><spring:message code="LAB.M09.LAB00094"/><!-- 제외내역상세 --></th>
				<td colspan="3">
					<input type="text" id="excpCt" name="excpCt" class="w1200" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00096"/><!-- 제외시작일 --></th>
				<td>
					<div class="date_box">
						<div class="inp_date w100">
							<input type="text" id="excpStrtYymmddNew" name="excpStrtYymmddNew" class="datepicker" disabled="disabled" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00097"/><!-- 제외종료일 --></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date w100">
							<input type="text" id="excpEndYymmddNew" name="excpEndYymmddNew" class="datepicker" disabled="disabled" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	
	<!--버튼-->	
	<div class="main_btn_box">
		<div class="fr">
			<!--수정-->
			<ntels:auth auth="${menuAuthU}">
				<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
			</ntels:auth>
			<!--삭제-->
			<ntels:auth auth="${menuAuthD}">
				<a class="grey-btn" href="#"  id="btn_delete" ><span class="write_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</ntels:auth>
		</div>
	</div>
<input id="isUnmaskYn" value='' type='text' hidden />	
</form>	

<div id="pop_layer" style="height:600px;width:500px;overflow-x:none;overflow-y:auto;display:none;"></div>       

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>



