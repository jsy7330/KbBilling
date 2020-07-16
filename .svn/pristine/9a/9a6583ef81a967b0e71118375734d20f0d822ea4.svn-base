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
		    }).datepicker("setDate", "-1"); 
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
		    { label: '번호', name: 'actDttm', width : 70, align:"center", formatter:stringTypeFormatterYYYYMMDDHH24MISS},
		    { label: '고객ID', name: 'custNm', width : 180, align:"left",sortable:false},
		    { label: '고객명', name: 'custTpNm', width : 130, align:"left",sortable:false},
		    { label: '변경일자', name: 'custClNm', width : 130, align:"left",sortable:false},
		    { label: '변경전납부방법', name: 'corpRegNoAsMask', width : 130, align:"left",sortable:false, formatter:corpRegNoFormatter},
		    { label: '현재납부방법', name: 'mtelNoAsMask', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '납부계정ID', name: 'chngResnNm', width : 150, align:"left",sortable:false},
		    { label: '변경자', name: 'repNm', width : 150, align:"left",sortable:false},
		    { label: '승인상태', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '유통망', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '유통사원', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '우편청구', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '이메일청구', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: 'SMS청구', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '은행카드사', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '카드계좌번호', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '예금소유주명', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '수정자', name: 'mtelNoAsMask', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '수정일시', name: 'chngResnNm', width : 150, align:"left",sortable:false},
		    { label: '등록자', name: 'repNm', width : 150, align:"left",sortable:false},
		    { label: '등록일시', name: 'busiCndtNm', width : 150, align:"left",sortable:false}
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

<!-- 검색 버튼 -->
<div class="main_btn_box">
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='searchCustBtn' href="#"class="grey-btn" title='<spring:message code="LAB.M01.LAB00047"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
			</td>
			<th>현재결제방법</th>
			<td>
				<select id="inputPymMthd" name="pymMthd" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${pymMthdCodeList}" var="pymMthdCode" varStatus="status">
						<option value="${pymMthdCode.commonCd}">${pymMthdCode.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>변경전결제방법</th>
			<td>
				<select id="inputPymMthd" name="pymMthd" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${pymMthdCodeList}" var="pymMthdCode" varStatus="status">
						<option value="${pymMthdCode.commonCd}">${pymMthdCode.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th>접수일</th>
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
		</tr>
		<tr>
			<th>유통망코드</th>
			<td colspan="3">
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
		<h4 class="sub_title">가입자별결제방법리스트</h4>
	</div>
</div>
<div id='gridDiv' class="w100p">
	<table id="custHistGrid" class="w100p"></table>
	<div id="custHistGridPager"></div>
</div>
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
			<ntels:auth auth="${menuAuthP}">
			<a id="printBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
			</ntels:auth>
		</span>
	</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>