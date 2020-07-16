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
		    { label: 'taxTp' , name: 'taxTp', hidden:true, width:0},
		    { label: 'custTp' , name: 'custTp', hidden:true, width:0},
		    { label: 'custCl' , name: 'custCl', hidden:true, width:0},
		    { label: 'restricPwd' , name: 'restricPwd', hidden:true, width:0},
		    { label: 'chgResn' , name: 'chgResn', hidden:true, width:0},
		    { label: 'busiCndt' , name: 'busiCndt', hidden:true, width:0},
		    { label: 'busiTp' , name: 'busiTp', hidden:true, width:0},
		    { label: 'chgrId' , name: 'chgrId', hidden:true, width:0},
		    { label: 'chgrOrgId' , name: 'chgrOrgId', hidden:true, width:0},
		    { label: 'chgrOrgNm' , name: 'chgrOrgNm', hidden:true, width:0},
		    { label: 'regrId' , name: 'regrId', hidden:true, width:0},
		    { label: 'orgId' , name: 'orgId', hidden:true, width:0},
		    { label: 'orgNm' , name: 'orgNm', hidden:true, width:0},
		    { label: 'chngResn' , name: 'chngResn', hidden:true, width:0},
		    { label: 'state' , name: 'state', hidden:true, width:0},
		    { label: '<spring:message code="LAB.M07.LAB00287"/>', name: 'actDttm', width : 150, align:"center", formatter:stringTypeFormatterYYYYMMDDHH24MISS},
		    { label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'custNm', width : 180, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00053"/>', name: 'custTpNm', width : 120, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00048"/>', name: 'custClNm', width : 130, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00188"/>', name: 'corpRegNoAsMask', width : 200, align:"left",sortable:false, formatter:corpRegNoFormatter},
		    { label: '<spring:message code="LAB.M08.LAB00087"/>', name: 'zipCd', width : 70, align:"center",sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00218"/>', name: 'baseAddr', width : 200, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00102"/>', name: 'addrDtlAsMask', width : 200, align:"left",sortable:false},
		    /* { label: '<spring:message code="LAB.M03.LAB00087"/>', name: 'city', width : 120, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00232"/>', name: 'stateNm', width : 150, align:"left",sortable:false}, */
		    { label: '<spring:message code="LAB.M08.LAB00119"/>', name: 'emlAsMask', width : 100, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00065"/>', name: 'telNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M15.LAB00036"/>', name: 'faxNo', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M14.LAB00076"/>', name: 'mtelNoAsMask', width : 100, align:"center",sortable:false, formatter:telNoFormatter},
		    { label: '<spring:message code="LAB.M03.LAB00063"/>', name: 'repNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00012"/>', name: 'busiCndtNm', width : 150, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00011"/>', name: 'busiTpNm', width : 180, align:"left",sortable:false},
		    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrNm', width : 150,sortable:false},           
		    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"},    
		    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrNm', width : 150,sortable:false},
		    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 160,align:"center"}
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

    
  	//업태 선택 이벤트
    $('#inputBusiCndt').selectmenu({
        change: function() {
        	if($('#inputBusiCndt').val() == 'SEL'){
        		$('#inputBusiTp').each( function() {
                	$('#inputBusiTp option:gt(0)').remove();
                });

              $('#inputBusiTp').val('SEL');
              $('#inputBusiTp').selectmenu("refresh");
        	}else{
        		
        		getBusiCndtCodeList();
        		
        	}

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
	
	$('#inputSoId').val('SEL');
	$('#inputSoId').selectmenu("refresh");
	$('#inputCustTp').val('SEL');
	$('#inputCustTp').selectmenu("refresh");
	$('#inputCustCl').val('SEL');
	$('#inputCustCl').selectmenu("refresh");
	
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
 * 고객정보조회
 */
function searchCustInfo(){
	var checkR = "<c:out value='${menuAuthR}'/>"; 
	if(checkR == 'N') return;
	
	var soId = $('#condSo').val();
  	var custNm = $('#condCustNm').val();
  	var custId = $('#condCustId').val();
	var isUnmaskYn = $('#isUnmaskYn').val();
	
	if(soId == ''){
		alert('<spring:message code="MSG.M07.MSG00002"/>');
		return;
	}else if(custNm == ''){
		alert('<spring:message code="MSG.M01.MSG00017"/>');
		return;
	}
	var url = '/customer/customer/customer/customerManagement/getCustomerInfoAction.json';
	
	$.ajax({
          url:url,
          type:'POST',
          data : {
          	soId : soId,
          	custNm : custNm,
            custId : custId,
            isUnmaskYn : isUnmaskYn
          	},
          dataType: 'json',
          success: function(data){
          	if(data.custListCnt == '0'){
          		alert('<spring:message code="MSG.M09.MSG00039"/>');	
          	}else if(data.custListCnt == 1){

				$('#condSo').val(data.custList[0].soId);
				$('#condSo').selectmenu("refresh");
				$('#condCustNm').val(data.custList[0].custNm);
				$('#condCustId').val(data.custList[0].custId);
              
				$("#custHistGrid").clearGridData();
	        	//입력부 초기화
	   	      	inputInit();
	   	      	
	   	      	//버튼 컨트롤
	   	      	btnCtrl('I');
	   	      	
	      		$("#custHistGrid").setGridParam({
	   	   	        postData : {
	   	   				soId : $("#condSo").val(),
	   	   				custId : $("#condCustId").val(),
	   	   				isUnmaskYn : $("#isUnmaskYn").val()
	   	   			}
	      		});
	   	      	
	          	$("#custHistGrid").trigger("reloadGrid"); 

          	}else{
          		//다수 존재시 팝업호출
          		openCustSearchPopup();
          	}
          	
          },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
     			alert(err.responseJSON.exceptionMsg);
     		}else{
     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
     		}
       	}
      });
}


/*
 * 고객조회팝업
 */
function openCustSearchPopup(){
	
	$.ajax({
		type : "post",
		url : '/system/common/common/customerSearch/customerSearchPopup.ajax',
		data : {
			 inputSoId : $('#condSo').val()   //input SO Id
			,inputCustNm : $('#condCustNm').val()   //input Customer Name
			,inputIsUnmaskYn : $('#isUnmaskYn').val() //마스크 처리 해제 Y
			,outputSoId : 'condSo'            //output SO ID Select
			,outputCustNm : 'condCustNm'            //output Customer Name Text
			,outputCustId : 'condCustId'            //output Customer ID Text

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
	
	$("#inputPostNo").val(custInfo.zipCd);
	$("#inputBaseAddr").val(custInfo.baseAddr);
	$("#inputDtlAddr").val(custInfo.addrDtlAsMask);
	// $("#inputCity").val(custInfo.city);
	// $("#inputState").val(custInfo.state == null || custInfo.state == '' ? 'SEL' : custInfo.state);
	// $("#inputState").selectmenu("refresh");
	$("#inputTelNo").val(custInfo.telNo);
	$("#inputFaxNo").val(custInfo.faxNo);
	$("#inputCellPhoneNo").val(custInfo.mtelNoAsMask);
	$("#inputEmail").val(custInfo.emlAsMask);
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

//업종 코드 조회
function getBusiCndtCodeList(){

	var url = '/customer/customer/customer/customerManagement/getBusiCndtAction.json';
	
	var code = $('#inputBusiCndt').val();
	$.ajax({
          url:url,
          type:'POST',
          async: false,
          data : {
        	  code : code
          	},
          dataType: 'json',
          success: function(data){
        	  $(data.busiTpCdList).each(function(index, item){
				      var str = '<option value="'+ item.commonCd+'">'+ item.commonCdNm+'</option>';
				      $('#inputBusiTp').append(str);  
        	  });
        	  
       		$('#inputBusiTp').val('SEL');
       		$('#inputBusiTp').selectmenu("refresh");
          },
       	beforeSend: function(data){
       			
       	},
       	error : function(err){
       		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
     			alert(err.responseJSON.exceptionMsg);
     		}else{
     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
     		}
       	}
      });
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
			<th><spring:message code="LAB.M01.LAB00050" /><span class="dot">*</span></th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w245" />
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	</thead>
	<input id="condCustId" type="text" hidden/>
</table> 


<!-- 고객정보 변경이력 테이블 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M01.LAB00055"/></h4>
	</div>
</div>
<div id='gridDiv' class="w100p">
	<table id="custHistGrid" class="w100p"></table>
	<div id="custHistGridPager"></div>
</div>

<!-- 고객정보표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M01.LAB00054"/></h4>
	</div>
</div>
<div id="custInfoDiv">
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:15%;">
			<col style="width:10%;">
			<col style="width:15%;">
			<col style="width:10%;">
			<col style="width:15%;">
			<col style="width:10%;">
			<col style="width:15%;">
		</colgroup>
	    <tbody> 
		<tr>
			<th><spring:message code="LAB.M01.LAB00046"/></th><!-- 고객ID -->
				<td><input id="inputCustId" name="custId" type="text" class="w100p" /></td>
			<th><spring:message code="LAB.M07.LAB00003" /><span class="dot">*</span></th> <!-- 사업 -->
				<td><select id="inputSoId" name="soId" class="w100p">
							<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
							<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
									<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
							</c:forEach>
				</select></td>
			<th><spring:message code="LAB.M01.LAB00050" /><span class="dot">*</span></th><!-- 고객명 -->
				<td colspan="3"><input id="inputCustNm" name="custNm" type="text" class="w100p" /></td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M01.LAB00053" /><span class="dot">*</span></th><!-- 고객유형 -->
			<td>
				<select id="inputCustTp" name="custTp" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${custTpCdList}" var="custTpCode" varStatus="status">
						<option value="${custTpCode.commonCd}">${custTpCode.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M01.LAB00048" /><span class="dot">*</span></th><!-- 고객구분 -->
			<td>
				<select id="inputCustCl" name="custCl" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${custClCdList}" var="custClCode" varStatus="status">
						<option value="${custClCode.commonCd}">${custClCode.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M09.LAB00188" /><span class="dot">*</span></th><!-- 주민/법인번호 -->
			<td colspan="3"><input id="inputCorpRegNo" name="corpRegNo" type="text" class="w100p" maxlength='14'/></td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00190" /><span class="dot">*</span></th><!-- 주소 -->
			<td colspan="7">
				<div class="inp_date w1050">
					<input type="text" id="inputPostNo" name="zipCd" class="w70" placeholder='<spring:message code="LAB.M08.LAB00087"/>'> 
					<input type="text" id="inputBaseAddr" name="baseAddr" class="w300" placeholder='<spring:message code="LAB.M01.LAB00218"/>'>
					<input type="text" id="inputDtlAddr" name="addrDtl" class="w330" placeholder='<spring:message code="LAB.M07.LAB00102"/>'>
					<!-- <input type="text" id="inputCity" name="city" class="w150" placeholder='<spring:message code="LAB.M03.LAB00087"/>'>
					<select id="inputState" name="state" class="w150">
						<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${stateCdList}" var="stateCode" varStatus="status">
							<option value="${stateCode.commonCd}">${stateCode.commonCdNm}</option>
						</c:forEach>
					</select> -->
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00065" /></th><!-- 전화번호 -->
			<td><input id="inputTelNo" name="inputTelNo" type="text" class="w100p" /></td>
			<th><spring:message code="LAB.M15.LAB00036" /></th><!-- FAX번호 -->
			<td><input id="inputFaxNo" name="inputFaxNo" type="text" class="w100p" /></td>
			<th><spring:message code="LAB.M14.LAB00076" /><span class="dot">*</span></th><!-- 휴대폰번호 -->
			<td><input id="inputCellPhoneNo" name="inputCellPhoneNo" type="text" class="w100p" /></td>
			<th><spring:message code="LAB.M08.LAB00119" /><span class="dot">*</span></th><!-- 이메일주소 -->
			<td><input id="inputEmail" name="eml" type="text" class="w100p" /></td>
		</tr>
		</tbody>
	</table>
</div>
<!-- 사업자 부가 정보 -->
<div id="corpAddInfo" class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00015"/></h4>
	</div>
</div>

<div id="corpInfoDiv">
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:23%;">
			<col style="width:10%;">
			<col style="width:24%;">
		</colgroup>
	    <tbody>
	    <tr>
			<th><spring:message code="LAB.M03.LAB00063" /></th><!-- 대표자명 -->
			<td><input id="inputRefNm" name="refNm" type="text" class="w100p" /></td>
			<th><spring:message code="LAB.M08.LAB00012" /></th><!-- 업태 -->
			<td>
				<select id="inputBusiCndt" name="busiCndt" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${busiCndtCdList}" var="busiCndtCode" varStatus="status">
						<option value="${busiCndtCode.commonCd}">${busiCndtCode.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M08.LAB00011" /></th><!-- 업종 -->
			<td>
				<select id="inputBusiTp" name="busiTp" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
				</select>
			</td>		
		</tr> 
		<tr>
			<th><spring:message code="LAB.M03.LAB00031" /></th><!-- 담당자명 -->
			<td><input id="inputBusiChrgNm" name="busiChrgNm" type="text" class="w100p" /></td>
			<th><spring:message code="LAB.M09.LAB00217" /></th><!-- 직책 -->
			<td>
				<input id="inputBusiChrgDuty" name="busiChrgDuty" type="text" class="w100p" />
			</td>
			<th><spring:message code="LAB.M09.LAB00065" /></th><!-- 담당자 전화번호 -->
			<td>
				<input id="inputBusiChrgTelno" name="busiChrgTelno" type="text" class="w100p" />
			</td>		
		</tr> 
		</tbody>
	</table>
</div>
<!-- 등록정보 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M03.LAB00077"/></h4>
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
				<th><spring:message code="MSG.M10.MSG00039"/></th>
				<td><input id="inputRegrNm" name="inputRegrNm" type="text" class="w100p not-active" disabled="disabled"/></td>
				<th><spring:message code="MSG.M10.MSG00040"/></th>
				<td><input id="inputRegrOrgNm" name="inputRegrOrgNm" type="text" class="w100p not-active" disabled="disabled"/></td>
				<th><spring:message code="MSG.M10.MSG00041"/></th>
				<td><input id="inputRegrDt" name="inputRegrDt" type="text" class="w100p not-active" disabled="disabled"/></td>
			</tr>
			<tr>
				<th><spring:message code="MSG.M10.MSG00042"/></th>
				<td><input id="inputChgrNm" name="inputChgrNm" type="text" class="w100p not-active" disabled="disabled" /></td>
				<th><spring:message code="MSG.M10.MSG00043"/></th>
				<td><input id="inputChgrOrgNm" name="inputChgrOrgNm" type="text" class="w100p not-active" disabled="disabled"/></td>
				<th><spring:message code="MSG.M10.MSG00044"/></th>
				<td><input id="inputChgrDt" name="inputChgrDt" type="text" class="w100p not-active" disabled="disabled"/></td>
			</tr>
		</tbody>
	</table>
</div>


<!-- 하단 버튼부 -->
<div class="main_btn_box">
	<div class="fr">
			<span id="commonBtn">
			<ntels:auth auth="${menuAuthC}">
			<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
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