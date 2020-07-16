<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>


<script type="text/javascript">
$(document).ready(function () {
	//화면 초기화 처리
	pageInit();
	
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "-7"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
	

	if($(".datepicker1").length > 0){
		$( ".datepicker1" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "0"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker1.disabled" ).datepicker( "option", "disabled", true );
	
	//그리드 선언
	$("#custHistGrid").jqGrid({
		url : '/customer/customer/customer/customerHistoryManagement/getCustChngHistAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			soId : $("#soId").val(),
			custId :$("#custId").val(),
			isUnmaskYn : $("#isUnmaskYn").val()
		},
		colModel: [ 
  		    { label: 'soId' , name: 'soId', hidden:true, width:0},
		    { label: 'custId' , name: 'custId', hidden:true, width:0},
		    { label: '자동환불여부', name: 'actDttm', width : 150, align:"center", formatter:stringTypeFormatterYYYYMMDDHH24MISS},
		    { label: '납부계정ID', name: 'custNm', width : 180, align:"left",sortable:false},
		    { label: '납부자명', name: 'custTpNm', width : 130, align:"left",sortable:false},
		    { label: '환불은행', name: 'custTpNm', width : 130, align:"left",sortable:false},
		    { label: '계좌번호', name: 'custClNm', width : 130, align:"left",sortable:false},
		    { label: '예금주명', name: 'zipCd', width : 50, align:"center",sortable:false},
		    { label: '신청자전화번호', name: 'baseAddr', width : 200, align:"left",sortable:false},
		    { label: '환불금액', name: 'addrDtlAsMask', width : 200, align:"left",sortable:false},
		    { label: '처리자ID', name: 'taxTpNm', width : 130, align:"left",sortable:false},
		    { label: '처리일시', name: 'taxTpNm', width : 130, align:"left",sortable:false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 150,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "custChngHistList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#custHistGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setCustomerInfo(rowid);
        },
        loadComplete : function () {
        	$("#custHistGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
        	$("#custHistGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
    		}
    	}
	});

	$("#custHistGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	//그리드 화면 재조정
	// $(window).resize(function() {
	// 	$("#custHistGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	// });


	$(window).bind('resize', function() {
       $("#custHistGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
     }).trigger('resize');
	
	
	//조회 버튼 이벤트
	$('#searchCustBtn').on('click',function (e) {
	    	if($("#searchCustBtn").hasClass('not-active')){
				return;
			}
		    $('#isUnmaskYn').val('N');
		    $("#condCustId").val('');
			searchCustInfo();
		}  
	);
	
	
    //고객명 키 이벤트
    $( "#condCustNm" ).keypress(function(event) {
        if(event.keyCode == 13){
          $('#isUnmaskYn').val('N');
          $("#condCustId").val('');
          searchCustInfo();
        }
      });


    
 	// 고객조회
	$('#btnCustSearch').on('click',function (e) {
			if($("#btnCustSearch").hasClass('not-active')){
				return;
			}
			openCustSearchPopup();	
		}
	);
});


/*
 * 화면 초기화 함수
 */
function pageInit(){
	
	//검색정보 초기화
	$("#condCustId").val('');
	
	//입력부 초기화
	inputInit();
	
	//버튼 컨트롤
	btnCtrl('I');
}



/*
 * 입력부 초기화 함수
 */
function inputInit(){
 	//고객정보 초기화
	$("#custInfoDiv input:text").val('');
	$("#custInfoDiv input:text").addClass('not-active');
	$("#custInfoDiv input:text").attr('disabled', true);
	$("#custInfoDiv select").selectmenu("disable");
	
	$("#inputRestricPwd").val('');
	$("#inputRestricPwd").addClass('not-active');
	$("#inputRestricPwd").attr('disabled', true);
	
	$('#inputSoId').val('SEL');
	$('#inputSoId').selectmenu("refresh");
	$('#inputCustTp').val('SEL');
	$('#inputCustTp').selectmenu("refresh");
	$('#inputCustCl').val('SEL');
	$('#inputCustCl').selectmenu("refresh");
	$('#inputTaxTp').val('SEL');
	$('#inputTaxTp').selectmenu("refresh");
	$('#inputChgResn').val('SEL');
	$('#inputChgResn').selectmenu("refresh");
	
	//사업자정보초기화
	$("#corpInfoDiv input:text").val('');
	$("#corpInfoDiv input:text").addClass('not-active');
	$("#corpInfoDiv input:text").attr('disabled', true);
	$('#inputBusiCndt').val('SEL');
	$('#inputBusiCndt').selectmenu("refresh");
	$('#inputBusiTp').val('SEL');
	$('#inputBusiTp').selectmenu("refresh");
	$("#corpInfoDiv select").selectmenu("disable");
	//등록정보 초기화
	$("#regInfo input:text").val('');
}




function customerSearchCallback(){
	searchCustInfo();
}

/*
 * 조회된 고객정보 세팅
 */
function setCustomerInfo(rowId){
	
	var custInfo = $("#custHistGrid").getRowData(rowId);
	
	//데이터 세팅
	$("#inputCustId").val(custInfo.custId);
	$("#inputCustNm").val(custInfo.custNm);
	$("#inputSoId").val(custInfo.soId == null || custInfo.soId == '' ? 'SEL' : custInfo.soId);
	$('#inputSoId').selectmenu("refresh");
	$("#inputCustTp").val(custInfo.custTp == null || custInfo.custTp == '' ? 'SEL' : custInfo.custTp);
	$('#inputCustTp').selectmenu("refresh");
	$("#inputCustCl").val(custInfo.custCl == null || custInfo.custCl == '' ? 'SEL' : custInfo.custCl);
	$("#inputCustCl").selectmenu("refresh");
	$("#inputCorpRegNo").val(custInfo.corpRegNoAsMask);
	$("#inputBusinessNo").val(custInfo.bizRegNo);
	
	$("#inputPostNo").val(custInfo.zipCd);
	$("#inputBaseAddr").val(custInfo.baseAddr);
	$("#inputDtlAddr").val(custInfo.addrDtlAsMask);
	$("#inputTelNo").val(custInfo.telNo);
	$("#inputFaxNo").val(custInfo.faxNo);
	$("#inputCellPhoneNo").val(custInfo.mtelNoAsMask);
	$("#inputEmail").val(custInfo.emlAsMask);
	$("#inputRestricPwd").val(custInfo.restricPwd);
	$("#inputTaxTp").val(custInfo.taxTp == null || custInfo.taxTp == '' ? 'SEL' : custInfo.taxTp);
	$("#inputTaxTp").selectmenu("refresh");
	$("#inputForeignerExpDt").val(custInfo.foreignerExpiratDt);
	$("#inputChgResn").val(custInfo.chngResn == null || custInfo.chngResn == '' ? 'SEL' : custInfo.chngResn);
	$("#inputChgResn").selectmenu("refresh");
	$("#inputRefNm").val(custInfo.repNm);
	$("#inputBusiCndt").val(custInfo.busiCndt == null || custInfo.busiCndt == '' ? 'SEL' : custInfo.busiCndt);
	$("#inputBusiCndt").selectmenu("refresh");
	
	if(custInfo.busiCndt != null && custInfo.busiCndt != ''){
		getBusiCndtCodeList();
		$("#inputBusiTp").val(custInfo.busiTp == null || custInfo.busiTp == '' ? 'SEL' : custInfo.busiTp);
		$("#inputBusiTp").selectmenu("refresh");
	}else{
		$('#inputBusiTp').each( function() {
        	$('#inputBusiTp option:gt(0)').remove();
        });

        $('#inputBusiTp').val('SEL');
        $('#inputBusiTp').selectmenu("refresh");
	}
	
	$('#inputRegrNm').val(getNameAndId(custInfo.regrId, custInfo.regrNm));
	$('#inputRegrOrgNm').val(getNameAndId(custInfo.orgId, custInfo.orgNm));
	$('#inputRegrDt').val(custInfo.regDate);
	$('#inputChgrNm').val(getNameAndId(custInfo.chgrId, custInfo.chgrNm));
	$('#inputChgrOrgNm').val(getNameAndId(custInfo.chgrOrgId, custInfo.chgrOrgNm));
	$('#inputChgrDt').val(custInfo.chgDate);

}

/*
 * 버튼 컨트롤
 */
function btnCtrl(mode){
	if($("#isUnmaskYn").val() != 'Y'){
		btnEnable('disableMaskBtn');
	}else{
		btnDisable('disableMaskBtn');
	}

	if(mode == 'I'){ //초기화 모드
    	btnDisable('initBtn');
		btnDisable('newBtn');
		btnDisable('updateBtn');
		btnDisable('deleteBtn');
		btnDisable('printBtn');
	}
}

/*
 * 버튼 비활성화 처리
 */
function btnDisable(id){
	$('#' + id ).addClass('white-btn');
	$('#' + id ).removeClass('grey-btn');
	$('#' + id ).addClass('not-active');
	$('#' + id ).attr('disabled',true);
	
}



/*
 * 버튼 활성화 처리
 */
function btnEnable(id){
	$('#' + id ).addClass('grey-btn');
	$('#' + id ).removeClass('white-btn');
	$('#' + id ).removeClass('not-active');
	$('#' + id ).removeAttr('disabled');
}

/*
 * 명칭 포맷팅
 */
function getNameAndId(id, name){
	if(name == '' || name == null){
		return id;
	}else{
		return name + '(' + id + ')'; 
	}
	 
}

</script>
	

<!-- 상단 메뉴명 및 Navigator 작성 -->
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

<!-- 검색부 -->
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:40%;">
		<col style="width:10%;">
		<col style="width:40%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M07.LAB00003" /></th>
			<td colspan="3">
				<select id="condSo" class="w40p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>환불신청일</th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="datepicker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
					<span class="dash">~</span>
					<div class="inp_date w130">
						<input type="text" id="searchEndDt" name="searchEndDt" class="datepicker1" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th>납부계정</th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w120" />
					<input id="condCustNm" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<!-- 고객정보 변경이력 테이블 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">환불내역</h4>
	</div>
</div>
<div id='gridDiv' class="w100p">
	<table id="custHistGrid" class="w100p"></table>
	<div id="custHistGridPager"></div>
</div>
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"></h4>
	</div>
</div>
<div id="regInfo">
	<table class="writeview">
		<colgroup>
			<col style="width: 20%;">
			<col style="width: 30%;">
			<col style="width: 20%;">
			<col style="width: 30%;">
		</colgroup>
		<tbody>
			<tr>
				<th>신청자명</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
				<th>납입자와의관계</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
			</tr>
			<tr>
				<th>신청자E-Mail</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
				<th>환불사유</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
			</tr>
			<tr>
				<th>신청자전화번호</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
				<th>환불요청금액</th>
				<td>				
					<div class="inp_date w280">
						<input id="serviceNo" type="text" class="w245" disabled/>
					</div>
				</td>
			</tr>
			<tr>
				<th>추가정보</th>
				<td colspan="3">				
					<div class="inp_date w800">
						<textarea id="rsn" name="rsn" class="w100p h80" maxlength = "1500"></textarea>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>