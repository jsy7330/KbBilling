<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<style type="text/css">table.ui-datepicker-calendar { display:none; }</style>
<script type="text/javascript">

$(document).ready(function(){
	
	//화면 초기화 처리
	pageInit();
	
	//그리드 처리
	$("#paymentGrid").jqGrid({
		//url : '/customer/customer/payment/paymentManagement/getPymAcntInfoListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			soId : $("#condSoId").val(),
			custId : $("#condCustId").val(),
			isUnmaskYn : $("#isUnmaskYn").val()
		},
		colModel: [
		    { label: '오더ID', name: 'pymAcntId', width : 150, align:"center"},
		    { label: '사업', name: 'acntNm', width : 200},
		    { label: '서비스명', name: 'pymMthdNm', width : 150}, 
		    { label: '오더유형', name: 'chgrNm', width : 150},           
		    { label: '정지여부', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},    
		    { label: '등록자', name: 'regrNm', width : 150},
		    { label: '등록일', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},
		    { label: '수정자', name: 'regrNm', width : 150},
		    { label: '수정일', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},
		],
		viewrecords: true,
		/* autowidth: true, */
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "pymAcntInfoList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#paymentGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
       		
       		//입력부 초기화
        	inputInit();
        	
	   	    //버튼 컨트롤
  	      	btnCtrl('I');
	   	    
  	      	$("#paymentGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#paymentGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 처리
	$("#serviceAttrList").jqGrid({
		//url : '/customer/customer/payment/paymentManagement/getPymAcntInfoListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			soId : $("#condSoId").val(),
			custId : $("#condCustId").val(),
			isUnmaskYn : $("#isUnmaskYn").val()
		},
		colModel: [
		    { label: '오더ID', name: 'pymAcntId', width : 150, align:"center"},
		    { label: '오더속성ID', name: 'acntNm', width : 200},
		    { label: '오더속성명', name: 'pymMthdNm', width : 150}, 
		    { label: '오더속성값', name: 'chgrNm', width : 150},           
		    { label: '오더속성유형', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},    
		    { label: '등록자', name: 'regrNm', width : 150},
		    { label: '등록일', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},
		    { label: '수정자', name: 'regrNm', width : 150},
		    { label: '수정일', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},
		],
		viewrecords: true,
		/* autowidth: true, */
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "pymAcntInfoList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#serviceAttrListGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
       		
       		//입력부 초기화
        	inputInit();
        	
	   	    //버튼 컨트롤
  	      	btnCtrl('I');
	   	    
  	      	$("#serviceAttrList").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#serviceAttrList").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
});


/*
 * 화면 초기화 함수
 */
function pageInit(){
	
	//검색정보 초기화
	$("#condSoId").val('');
	$("#condCustId").val('');
	$("#condCustNm").val('');
	$("#condZipCd").val('');
	$("#condBaseAddr").val('');
	$("#condAddrDtl").val('');
	$("#condTelNo").val('');
	$("#condFaxNo").val('');
	$("#condMtelNo").val('');
	$("#condEml").val('');
	$('#condSo').val('SEL');
	$('#condSo').selectmenu("refresh");
	$("#paymentGrid").clearGridData();
	
	//입력부 초기화
	inputInit();
	
	//버튼 컨트롤
	btnCtrl('I');
}

/*
 * 입력부 초기화 함수
 */
function inputInit(){
 	//납부계정정보 Disable처리
	$("#pymAcntBasicInfo input:text").val('');
	$("#pymAcntBasicInfo input:text").addClass('not-active');
	$("#pymAcntBasicInfo input:text").attr('disabled', true);
	
	$('#inputSoId').val('SEL');
	$('#inputSoId').selectmenu("refresh");
	$('#inputPymMthd').val('SEL');
	$('#inputPymMthd').selectmenu("refresh");
	$('#inputchgResn').val('SEL');
	$('#inputchgResn').selectmenu("refresh");
	$("#pymAcntBasicInfo select").selectmenu("disable");
	
	$("#pymAcntBasicInfo input:checkbox").removeAttr("checked");
	$("#pymAcntBasicInfo input:checkbox").addClass('not-active');
	$("#pymAcntBasicInfo input:checkbox").attr('disabled',true);
	$("#pymAcntBasicInfo label").addClass('not-active');
	$("#pymAcntBasicInfo label").attr('disabled',true);

	btnDisable('disableMaskBtn');
	
	//청구서 정보 Disable 처리 invoiceInfo
	$("#invoiceInfo input:text").val('');
	$("#invoiceInfo input:text").addClass('not-active');
	$("#invoiceInfo input:text").attr('disabled', true);
	
	$("#searchAddrBtn").addClass('not-active');
	$('#searchAddrBtn').attr('disabled',true);
	
	$('#inputPaperInvoice').val('SEL');
	$('#inputPaperInvoice').selectmenu("refresh");
	$('#inputEmailInvoice').val('SEL');
	$('#inputEmailInvoice').selectmenu("refresh");
	$('#inputSmsInvoice').val('SEL');
	$('#inputSmsInvoice').selectmenu("refresh");
	$("#invoiceInfo select").selectmenu("disable");
	
	//카드/은행정보 Disable 처리
	
	$("#cardRegInfo input:text").val('');
	$("#cardRegInfo input:text").addClass('not-active');
	$("#cardRegInfo input:text").attr('disabled', true);
	
	$('#inputCardBankCd').val('SEL');
	$('#inputCardBankCd').selectmenu("refresh");
	$("#cardRegInfo select").selectmenu("disable");
	
	$("#cardRegInfo").hide();
	
	btnDisable('authBtn');
	
	//등록정보 초기화
	$("#regInfo input:text").val('');
}


/*
 * 버튼처리
 */
function btnCtrl(mode){
	//공통 버튼 처리
	btnDisable('funcBtn a');
	btnDisable('copyCustInfo');
	btnDisable('commonBtn a');
	
	if($("#isUnmaskYn").val() != 'Y'){
		btnEnable('disableMaskBtn');
		
	}else{
		btnDisable('disableMaskBtn');
	}
	if(mode == 'I'){
		//고객정보 존재시에 초기화 버튼 활성화
		if($("#condSoId").val() != '' && $("#condCustId").val() != ''){
			btnEnable('initBtn');
		} 
		btnDisable('disableMaskBtn');
	}else if(mode =='N'){
		btnEnable('initBtn');
		btnEnable('newBtn');
		btnEnable('copyCustInfo');
		
	}else if(mode =='U'){
		btnEnable('initBtn');
		btnEnable('updateBtn');
		btnEnable('deleteBtn');
		btnEnable('acntChngHistBtn');
		btnEnable('virInfoBtn');
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
	
<!-- 페이지 작성 -->


<!-- 검색 버튼 -->
<div class="main_btn_box">
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='searchPymBtn' href="#"class="grey-btn" title='<spring:message code="LAB.M02.LAB00011"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</ntels:auth>
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
			<td>
				<select id="condSo" class="w270">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
				<input id="condSoId" type="text" hidden />
			</td>
			<th>서비스명</th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w245" />
				</div>
			</td>
		</tr>
		<tr>
			<th>오더유형</th>
			<td>
				<select id="condSo" class="w270">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
				</select>
				<input id="condSoId" type="text" hidden />
			</td>
			<th>오더정의ID</th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w245" />
				</div>
			</td>
		</tr>
	</thead>
</table> 

<!-- 서비스리스트 테이블 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">서비스리스트</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="paymentGrid" class="w100p"></table>
	<div id="paymentGridPager"></div>
</div>

<!-- 기본정보 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">기본정보</h4>
	</div>
</div>
<div id="regInfo">
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 23%;">
			<col style="width: 10%;">
			<col style="width: 23%;">
			<col style="width: 10%;">
			<col style="width: 24%;">
		</colgroup>
		<tbody>
			<tr>
				<th>사업</th>
				<td>				
					<select id="condSo" class="w270">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</select>
				</td>
				<th>서비스명</th>
				<td>				
					<select id="condSo" class="w270">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</select>
				</td>
				<th>오더유형</th>
				<td>				
					<select id="condSo" class="w270">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</select>
				</td>
			</tr>
			<tr>
				<th>오더상세</th>
				<td colspan="3"><textarea  class="w100p h50" maxlength = "1500"></textarea></td>
				<th>정지여부</th>
				<td>
					<select id="condSo" class="w270">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
</div>
<!-- 하단 버튼부 -->
<div class="main_btn_box">
	<div class="fr">
			<span id="commonBtn">
			<ntels:auth auth="${menuAuthR}">
			<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthC}">
			<a id="newBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthU}">
			<a id="updateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthD}">
			<a id="deleteBtn"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</ntels:auth>
		</span>
	</div>
</div>

<!-- 서비스리스트 테이블 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">속성정보</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="serviceAttrList" class="w100p"></table>
	<div id="serviceAttrListGridPager"></div>
</div>
<!-- 하단 버튼부 -->
<div class="main_btn_box">
	<div class="fr">
			<span id="commonBtn">
			<ntels:auth auth="${menuAuthR}">
			<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthC}">
			<a id="newBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthU}">
			<a id="updateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthD}">
			<a id="deleteBtn"  class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
			</ntels:auth>
		</span>
	</div>
</div>
<input id="isUnmaskYn" value='' type='text' hidden />
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

