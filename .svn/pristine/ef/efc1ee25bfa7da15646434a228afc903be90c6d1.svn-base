<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>


<script type="text/javascript">
$(document).ready(function () {
	
	var custInfoObj = new Object();
	
	//화면 초기화 처리
	pageInit();
	
	//조회 버튼 이벤트
	$('#searchCustBtn').on('click',function (e) {
    	if($("#searchCustBtn").hasClass('not-active')){
			return;
		}
	    $('#isUnmaskYn').val('N');
	    $("#condCustId").val('');
		searchCustInfo();
	});
	//초기화 버튼 이벤트
   	$('#initBtn').on('click',function (e) {
   		if($("#initBtn").hasClass('not-active')){
			return;
		}
   		initBtn();
	});
	
  	//신규등록 버튼 이벤트
    $('#newBtn').on('click',function (e) {
      	if($("#newBtn").hasClass('not-active')){
          return;
  		  }
    		insertNewCustInfo();
		  }
    );

    //변경 버튼 이벤트
    $('#updateBtn').on('click',function (e) {
      if($("#updateBtn").hasClass('not-active')){
        return;
      }
        updateCustInfo();
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

  	//전화번호 keyup이벤트
  	$('#inputTelNo').keyup(function(){
  		$('#inputTelNo').val(telNoAutoFormatter($('#inputTelNo').val()));
  		$('#inputTelNo').val(getMaxStr($('#inputTelNo').val(), 20));
  		}
	);
  	
  //담당자 전화번호 keyup이벤트
  	$('#inputBusiChrgTelno').keyup(function(){
  		$('#inputBusiChrgTelno').val(telNoAutoFormatter($('#inputBusiChrgTelno').val()));
  		$('#inputBusiChrgTelno').val(getMaxStr($('#inputBusiChrgTelno').val(), 20));
  		}
	);

  //팩스번호 keyup이벤트
  	$('#inputFaxNo').keyup(function(){
  		$('#inputFaxNo').val(telNoAutoFormatter($('#inputFaxNo').val()));
  		$('#inputFaxNo').val(getMaxStr($('#inputFaxNo').val(), 20));
  		}
	);
  	
  	//휴대번호 keyup이벤트
  	$('#inputCellPhoneNo').keyup(function(){
  		$('#inputCellPhoneNo').val(telNoAutoFormatter($('#inputCellPhoneNo').val()));
  		$('#inputCellPhoneNo').val(getMaxStr($('#inputCellPhoneNo').val(), 20));
  		}
	);
  	
  	//Email keyup 이벤트
  	$('#inputEmail').keyup(function(){
	  		var str = getMaxStr($('#inputEmail').val(), 50);
	  		if(str != $('#inputEmail').val()){
	  			$('#inputEmail').val(str);
	  		}
  		}
	);
  	
  	//고객명 keyup 이벤트
  	$('#inputCustNm').keyup(function(){
	  		var str = getMaxStr($('#inputCustNm').val(), 100);
	  		if(str != $('#inputCustNm').val()){
	  			$('#inputCustNm').val(str);
	  		}
  		}
	);
  	
  //우편번호 keyup 이벤트
  	$('#inputPostNo').keyup(function(){
  		if (!(event.keyCode >=37 && event.keyCode<=40)) {
	        var inputVal = $(this).val();
	        $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
	     }
	  		var str = getMaxStr($('#inputPostNo').val(), 6);
	  		if(str != $('#inputPostNo').val()){
	  			$('#inputPostNo').val(str);
	  		}
  		}
	);
  
  	//상세주소 keyup 이벤트
  	$('#inputDtlAddr').keyup(function(){
	  		var str = getMaxStr($('#inputDtlAddr').val(), 300);
	  		if(str != $('#inputDtlAddr').val()){
	  			$('#inputDtlAddr').val(str);
	  		}
  		}
	);
  	
  	//대표자명 keyup 이벤트
  	$('#inputRepNm').keyup(function(){
	  		var str = getMaxStr($('#inputRepNm').val(), 50);
	  		if(str != $('#inputRepNm').val()){
	  			$('#inputRepNm').val(str);
	  		}
  		}
	);
  	
    //주소변경 Popup
    $('#searchAddrBtn').on('click',function (e) {
        if($("#searchAddrBtn").hasClass('not-active')){
        return;
      }
          var parameter = new Object();
          parameter.zipCd = 'inputPostNo';
          parameter.baseAddr = 'inputBaseAddr';
          parameter.addrDtl = 'inputDtlAddr';
          $.ajax({
            type : "post",
            url : '/system/common/common/postMng/postPopup.ajax',
            data : parameter,
            async: true,
            success : function(data) {
              var html = data;
              $("#popup_dialog").html(html);
              
            },    
            complete : function(){
              wrapWindowByMask(); // 팝업 오픈
            }
          });
      }
    );

  	//주민번호 숫자입력만 허용
    $('#inputCorpRegNo').on('keypress',function (event) {
        if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
         event.preventDefault();
        }
        if($('#inputCorpRegNo').val().length == 6){
        	var str = $('#inputCorpRegNo').val();
        	$('#inputCorpRegNo').val(str + '-');
        }
      });

    //사업자번호 숫자입력만 허용
    $('#inputBusinessNo').on('keypress',function (event) {
        if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
          event.preventDefault();
        }
        
        if($('#inputBusinessNo').val().length == 3 || $('#inputBusinessNo').val().length == 6){
          var str = $('#inputBusinessNo').val();
          $('#inputBusinessNo').val(str + '-')
        }
      });
  	
  	//고객유형 변경 이벤트
    $('#inputCustTp').selectmenu({
        change: function() {
        	var value = $('#inputCustTp').val();
          $('#inputBusinessNo').val('');
        	if(value == 'A' || value == 'SEL'){
        		$("#inputBusinessNo").addClass('not-active');
            $("#inputBusinessNo").attr('disabled', true);
        		$("#corpInfoDiv input:text").val('');
        		$("#corpInfoDiv input:text").addClass('not-active');
        		$("#corpInfoDiv input:text").attr('disabled', true);
        		$('#inputBusiCndt').val('SEL');
        		$('#inputBusiCndt').selectmenu("refresh");
        		$('#inputBusiTp').val('SEL');
        		$('#inputBusiTp').selectmenu("refresh");
        		$("#corpInfoDiv select").selectmenu("disable");
        	}else if(value == 'B'){
            $("#inputBusinessNo").removeClass('not-active');
            $("#inputBusinessNo").removeAttr('disabled');
        		$("#corpInfoDiv input:text").val('');
        		$('#inputBusiCndt').val('SEL');
        		$('#inputBusiCndt').selectmenu("refresh");
        		$('#inputBusiTp').val('SEL');
        		$('#inputBusiTp').selectmenu("refresh");
        		$("#corpInfoDiv input:text").removeClass('not-active');
        		$("#corpInfoDiv input:text").removeAttr('disabled');
        		$("#corpInfoDiv select").selectmenu("enable");
        	}else if(value == 'C'){
            $("#inputBusinessNo").addClass('not-active');
            $("#inputBusinessNo").attr('disabled', true);
        		$("#corpInfoDiv input:text").val('');
        		$("#corpInfoDiv input:text").addClass('not-active');
        		$("#corpInfoDiv input:text").attr('disabled', true);
        		$('#inputBusiCndt').val('SEL');
        		$('#inputBusiCndt').selectmenu("refresh");
        		$('#inputBusiTp').val('SEL');
        		$('#inputBusiTp').selectmenu("refresh");
        		$("#corpInfoDiv select").selectmenu("disable");
        		
        	}else if(value == 'D'){
            $("#inputBusinessNo").removeClass('not-active');
            $("#inputBusinessNo").removeAttr('disabled');
        		$("#corpInfoDiv input:text").val('');
        		$('#inputBusiCndt').val('SEL');
        		$('#inputBusiCndt').selectmenu("refresh");
        		$('#inputBusiTp').val('SEL');
        		$('#inputBusiTp').selectmenu("refresh");
        		$("#corpInfoDiv input:text").removeClass('not-active');
        		$("#corpInfoDiv input:text").removeAttr('disabled');
        		$("#corpInfoDiv select").selectmenu("enable");
        		
        	}else if(value == 'E'){
            $("#inputBusinessNo").removeClass('not-active');
            $("#inputBusinessNo").removeAttr('disabled');
        		$("#corpInfoDiv input:text").val('');
        		$('#inputBusiCndt').val('SEL');
        		$('#inputBusiCndt').selectmenu("refresh");
        		$('#inputBusiTp').val('SEL');
        		$('#inputBusiTp').selectmenu("refresh");
        		$("#corpInfoDiv input:text").removeClass('not-active');
            $("#corpInfoDiv input:text").removeAttr('disabled');
            $("#corpInfoDiv select").selectmenu("enable");
            
        	}
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


    //고객정보 변경이력 이벤트
    $('#custInfoHistBtn').on('click',function (e) {
      if($("#custInfoHistBtn").hasClass('not-active')){
        return;
      }
     
      if($("#condSo").val() == "SEL" || $("#condSo").val().length == 0){
        alert('<spring:message code="MSG.M01.MSG00015"/>');
        return;
      }

      if($("#condCustId").val() == null || $("#condCustId").val().length == 0){
        alert('<spring:message code="MSG.M01.MSG00015"/>');
        return;
      }
        $.ajax({
          type : "post",
          url : '/customer/customer/customer/customerHistoryManagement/openCustChngHistPopup.ajax',
          data : {
            soId : $("#condSo").val(),
            custId : $("#condCustId").val(),
            isUnmaskYn : $("#isUnmaskYn").val()
          },
          async: true,
          success : function(data) {
            var html = data;
            $("#popup_dialog").html(html);
          },    
          complete : function(){
            wrapWindowByMask(); // 팝업 오픈
          }
        }); 
      }
    );

    //납부계정 조회 이벤트
    $('#searchPymBtn').on('click',function (e) {
      if($("#searchPymBtn").hasClass('not-active')){
        return;
      }

      if($("#condSo").val() == "SEL" || $("#condSo").val().length == 0){
        alert('<spring:message code="MSG.M01.MSG00015"/>');
        return;
      }

      if($("#condCustId").val() == null || $("#condCustId").val().length == 0){
        alert('<spring:message code="MSG.M01.MSG00015"/>');
        return;
      }
        $.ajax({
          type : "post",
          url : '/customer/customer/customer/customerManagement/openCustPymListPopup.ajax',
          data : {
            soId : $("#condSo").val(),
            custId : $("#condCustId").val(),
            isUnmaskYn : $("#isUnmaskYn").val()
          },
          async: true,
          success : function(data) {
            var html = data;
            $("#popup_dialog").html(html);
          },    
          complete : function(){
            wrapWindowByMask(); // 팝업 오픈
          }
        }); 
      }
    );
    
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
          		setCustomerInfo(data.custList[0]);

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
 * 입력부 초기화 함수
 */
function inputInit(){
	$('#isAuthYn').val('N');
	
	
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
	$('#inputState').val('SEL');
	$('#inputState').selectmenu("refresh");
	
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
 * 수정 모드 화면 변경
 */
function inputUpdateMode(){
	//전체 초기화	
	inputInit();
 	//고객정보 활성화
	$("#custInfoDiv input:text").removeClass('not-active');
	$("#custInfoDiv input:text").removeAttr('disabled');
	$("#inputCustNm").addClass('not-active');
	$("#inputCustNm").attr('disabled', true);
	
	$("#custInfoDiv select").selectmenu("enable");
	$("#inputCustTp").selectmenu("disable");
	
	//사업자정보활성화
	$("#corpInfoDiv input:text").removeClass('not-active');
	$("#corpInfoDiv input:text").removeAttr('disabled');
	$("#corpInfoDiv select").selectmenu("enable");
	
	//버튼컨트롤
	btnCtrl('U');
}


/*
 * 버튼 컨트롤
 */
function btnCtrl(mode){

	if(mode == 'I'){ //초기화 모드
		btnEnable('initBtn');
		btnDisable('newBtn');
		btnDisable('updateBtn');
		btnDisable('searchPymBtn');
		btnDisable('custInfoHistBtn');
		btnDisable('deleteBtn');
		btnDisable('printBtn');
    $("#searchAddrBtn").addClass('not-active');
    $('#searchAddrBtn').attr('disabled',true);
	}else if(mode == 'N'){ //신규 추가 모드
	 	btnEnable('initBtn');
		btnEnable('newBtn');
		btnDisable('updateBtn');
		btnDisable('searchPymBtn');
		btnDisable('custInfoHistBtn');
		btnDisable('deleteBtn');
		btnDisable('printBtn');
    $("#searchAddrBtn").removeClass('not-active');
    $("#searchAddrBtn").removeAttr('disabled');
	}else if(mode == 'U'){//수정모드
		btnEnable('initBtn');
		btnDisable('newBtn');
		btnEnable('updateBtn');
		btnEnable('searchPymBtn');
		btnEnable('custInfoHistBtn');
		btnDisable('deleteBtn');
		btnDisable('printBtn');
    $("#searchAddrBtn").removeClass('not-active');
    $("#searchAddrBtn").removeAttr('disabled');
	}
}

/*
 * 조회된 고객정보 세팅
 */
function setCustomerInfo(custInfo){
	//조회된 고객정보 오브젝트 작성
	custInfoObj = custInfo;
	
	//화면 수정모드 처리
	inputUpdateMode();
	
	//데이터 세팅
  $("#inputCustId").val(custInfo.custId); 
	$("#inputCustNm").val(custInfo.custNm);
	$("#inputSoId").val(custInfo.soId == null || custInfo.soId == '' ? 'SEL' : custInfo.soId);
	$('#inputSoId').selectmenu("refresh");
	$("#inputCustTp").val(custInfo.custTp == null || custInfo.custTp == '' ? 'SEL' : custInfo.custTp);
	$('#inputCustTp').selectmenu("refresh");
	$("#inputCustCl").val(custInfo.custCl == null || custInfo.custCl == '' ? 'SEL' : custInfo.custCl);
	$("#inputCustCl").selectmenu("refresh");
  $("#inputCorpRegNo").val(custInfo.corpRegNoAsMask.substr(0,6) + '-' + custInfo.corpRegNoAsMask.substr(6,7));
  if(custInfo.bizRegNo != null && custInfo.bizRegNo != ''){
    var str = custInfo.bizRegNo.substr(0,3) + '-' + custInfo.bizRegNo.substr(3,2) + '-' + custInfo.bizRegNo.substr(5,5);  
    $("#inputBusinessNo").val(str); 
  }else{
    $("#inputBusinessNo").val(custInfo.bizRegNo);
  }
	
	$("#inputPostNo").val(custInfo.zipCd);
	$("#inputBaseAddr").val(custInfo.baseAddr);
	$("#inputDtlAddr").val(custInfo.addrDtlAsMask);
	$("#inputTelNo").val(telNoAutoFormatter(custInfo.telNo));
	$("#inputFaxNo").val(telNoAutoFormatter(custInfo.faxNo));
	$("#inputCellPhoneNo").val(telNoAutoFormatter(custInfo.mtelNoAsMask));
	$("#inputEmail").val(custInfo.emlAsMask);
	$("#inputRefNm").val(custInfo.repNm);
	$("#inputBusiChrgNm").val(custInfo.busiChrgNm);
	$("#inputBusiChrgDuty").val(custInfo.busiChrgDuty);
	$("#inputBusiChrgTelno").val(telNoAutoFormatter(custInfo.busiChrgTelno));
	$("#inputBusiCndt").val(custInfo.busiCndt == null || custInfo.busiCndt == '' ? 'SEL' : custInfo.busiCndt);
	$("#inputBusiCndt").selectmenu("refresh");
	
	if(custInfo.busiCndt != null && custInfo.busiCndt != ''){
		getBusiCndtCodeList();
		$("#inputBusiTp").val(custInfo.busiTp == null || custInfo.busiTp == '' ? 'SEL' : custInfo.busiTp);
		$("#inputBusiTp").selectmenu("refresh");
	}
	
	$('#inputRegrNm').val(getNameAndId(custInfo.regrId, custInfo.regrNm));
	$('#inputRegrOrgNm').val(getNameAndId(custInfo.orgId, custInfo.orgNm));
	var regDt = new Date(custInfo.regDate);
	$('#inputRegrDt').val(dateFormatterUsingDateYYYYMMDDHH24MISS(regDt));
	$('#inputChgrNm').val(getNameAndId(custInfo.chgrId, custInfo.chgrNm));
	$('#inputChgrOrgNm').val(getNameAndId(custInfo.chgrOrgId, custInfo.chgrOrgNm));
	var chgDt = new Date(custInfo.chgDate);
	$('#inputChgrDt').val(dateFormatterUsingDateYYYYMMDDHH24MISS(chgDt));
	$('#extId').val(custInfo.extId);
	
	//비활성화 처리
	$("#inputCustId").addClass('not-active');
	$("#inputCustId").attr('disabled', true);
	$("#inputCorpRegNo").addClass('not-active');
	$("#inputCorpRegNo").attr('disabled', true);
	$("#inputSoId").selectmenu("disable");
  $("#inputPostNo").addClass('not-active');
  $("#inputPostNo").attr('disabled', true);
  $("#inputBaseAddr").addClass('not-active');
  $("#inputBaseAddr").attr('disabled', true);
	var value = $('#inputCustTp').val();

	if(value == 'A' || value == 'SEL'){
		$("#corpInfoDiv input:text").addClass('not-active');
		$("#corpInfoDiv input:text").attr('disabled', true);
		$("#corpInfoDiv select").selectmenu("disable");
    $("#inputBusinessNo").addClass('not-active');
    $("#inputBusinessNo").attr('disabled', true);
	}else if(value == 'B'){
		$("#corpInfoDiv input:text").removeClass('not-active');
		$("#corpInfoDiv input:text").removeAttr('disabled');
		$("#corpInfoDiv select").selectmenu("enable");
    $("#inputBusinessNo").removeClass('not-active');
    $("#inputBusinessNo").removeAttr('disabled');
	}else if(value == 'C'){
		$("#corpInfoDiv input:text").addClass('not-active');
		$("#corpInfoDiv input:text").attr('disabled', true);
		$("#corpInfoDiv select").selectmenu("disable");
    $("#inputBusinessNo").addClass('not-active');
    $("#inputBusinessNo").attr('disabled', true);
	}else if(value == 'D'){
		$("#corpInfoDiv input:text").removeClass('not-active');
		$("#corpInfoDiv input:text").removeAttr('disabled');
		$("#corpInfoDiv select").selectmenu("enable");
    $("#inputBusinessNo").removeClass('not-active');
    $("#inputBusinessNo").removeAttr('disabled');
	}else if(value == 'E'){
		$("#corpInfoDiv input:text").removeClass('not-active');
		$("#corpInfoDiv input:text").removeAttr('disabled');
		$("#corpInfoDiv select").selectmenu("enable");
    $("#inputBusinessNo").removeClass('not-active');
    $("#inputBusinessNo").removeAttr('disabled');
	}
}

/**
 * 신규 등록 처리
 */
function insertNewCustInfo(){
	if(checkValidation('I') == false){
		return;
	}

  $("#isUnmaskYn").val('N'); 
	
	var url = '/customer/customer/customer/customerManagement/insertNewCustomerInfoAction.json';
	var customerInfo = 'soId=' + $('#inputSoId').val();
	customerInfo = customerInfo + '&custNm=' + $('#inputCustNm').val();
	customerInfo = customerInfo + '&custTp=' + $('#inputCustTp').val();
	customerInfo = customerInfo + '&custCl=' + $('#inputCustCl').val();
	customerInfo = customerInfo + '&corpRegNo=' + getTelNo($('#inputCorpRegNo').val().substr(0,8) + '******');
  customerInfo = customerInfo + '&bizRegNo=' + getTelNo($('#inputBusinessNo').val());
	customerInfo = customerInfo + '&telNo=' + getTelNo($('#inputTelNo').val());
	customerInfo = customerInfo + '&faxNo=' + getTelNo($('#inputFaxNo').val());
	customerInfo = customerInfo + '&mtelNo=' + getTelNo($('#inputCellPhoneNo').val());
	customerInfo = customerInfo + '&eml=' + $('#inputEmail').val();
	customerInfo = customerInfo + '&zipCd=' + $('#inputPostNo').val();
	customerInfo = customerInfo + '&baseAddr=' + $('#inputBaseAddr').val();
	customerInfo = customerInfo + '&addrDtl=' + $('#inputDtlAddr').val();
	customerInfo = customerInfo + '&city=' + '';
  customerInfo = customerInfo + '&taxTp=' + "Y";
	customerInfo = customerInfo + '&state=' + '';
	customerInfo = customerInfo + '&authYn=' + $('#isAuthYn').val();
	customerInfo = customerInfo + '&repNm=' + $('#inputRefNm').val();
	customerInfo = customerInfo + '&busiCndt=' + ($('#inputBusiCndt').val() == 'SEL' ? '' : $('#inputBusiCndt').val());
  customerInfo = customerInfo + '&busiTp=' + ($('#inputBusiTp').val() == 'SEL' ? '' : $('#inputBusiTp').val());
  customerInfo = customerInfo + '&busiChrgNm=' + $('#inputBusiChrgNm').val();
	customerInfo = customerInfo + '&busiChrgDuty=' + $('#inputBusiChrgDuty').val();
	customerInfo = customerInfo + '&busiChrgTelno=' +  getTelNo($('#inputBusiChrgTelno').val());
	customerInfo = customerInfo + '&extId=' + $('#extId').val();
	
	$.ajax({
        url:url,
        type:'POST',
        data : customerInfo,
        dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	$('#condSo').val(data.custInfo.soId);
        	$('#condSo').selectmenu("refresh");
        	$('#condCustNm').val(data.custInfo.custNm);
        	$('#condCustId').val(data.custInfo.custId);
        	setCustomerInfo(data.custInfo);
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
 * 변경 처리
 */
function updateCustInfo(){
  if(checkValidation('U') == false){
    return;
  }
  

  //이메일 체크 처리
  if($('#inputEmail').val() != custInfoObj.emlAsMask){
    if(checkEmailStr($('#inputEmail').val() ) == false){
      $('#inputEmail').focus();
      alert('<spring:message code="MSG.M08.MSG00046" />');
      return false;
    }
  }
  
  var isChanged = false;
  var custInfo = 'custId=' + $('#inputCustId').val()  + '&'  + 'soId=' + $('#inputSoId').val();
  custInfo = custInfo + '&isUnmaskYn=' + $('#isUnmaskYn').val();
  custInfo = custInfo + '&extId=' + $('#extId').val();
  if($('#inputCustCl').val() != custInfoObj.custCl){
    custInfo = custInfo + '&custCl=' + $('#inputCustCl').val();
    isChanged = true;
  }

  if($('#inputCorpRegNo').val() != custInfoObj.custCl){
	    custInfo = custInfo + '&inputCorpRegNo=' + $('#inputCorpRegNo').val();
	    isChanged = true;
  }

  if(getTelNo($('#inputBusinessNo').val()) != getNullToString(custInfoObj.bizRegNo)){
    custInfo = custInfo + '&bizRegNo=' + getTelNo($('#inputBusinessNo').val());
    isChanged = true;
  }
  if($('#inputPostNo').val() != getNullToString(custInfoObj.zipCd)){
    custInfo = custInfo + '&zipCd=' +$('#inputPostNo').val();
    isChanged = true;
  }
  
  if($('#inputBaseAddr').val() != getNullToString(custInfoObj.baseAddr)){
    custInfo = custInfo + '&baseAddr=' +$('#inputBaseAddr').val();
    isChanged = true;
  }
  
  if($('#inputDtlAddr').val() != getNullToString(custInfoObj.addrDtlAsMask)){
    custInfo = custInfo + '&addrDtl=' +$('#inputDtlAddr').val();
    isChanged = true;
  }
  
  if(getTelNo($('#inputTelNo').val()) != getNullToString(custInfoObj.telNo)){
    custInfo = custInfo + '&telNo=' + getTelNo($('#inputTelNo').val());
    isChanged = true;
  }
  
  if(getTelNo($('#inputFaxNo').val()) != getNullToString(custInfoObj.faxNo)){
    custInfo = custInfo + '&faxNo=' + getTelNo($('#inputFaxNo').val());
    isChanged = true;
  }
  
  if(getTelNo($('#inputCellPhoneNo').val()) != getNullToString(custInfoObj.mtelNoAsMask)){
    custInfo = custInfo + '&mtelNo=' + getTelNo($('#inputCellPhoneNo').val());
    isChanged = true;
  }
  
  if($('#inputEmail').val() != getNullToString(custInfoObj.emlAsMask)){
    custInfo = custInfo + '&eml=' + $('#inputEmail').val();
    isChanged = true;
  }
	//대표자명
  if($('#inputRefNm').val() != getNullToString(custInfoObj.repNm)){
    custInfo = custInfo + '&repNm=' + $('#inputRefNm').val();
    isChanged = true;
  }
	//업태
  if($('#inputBusiCndt').val() != 'SEL' && $('#inputBusiCndt').val() != getNullToString(custInfoObj.busiCndt)){
    custInfo = custInfo + '&busiCndt=' + ($('#inputBusiCndt').val() == 'SEL' ? '' : $('#inputBusiCndt').val());
    isChanged = true;
  }

  if($('#inputBusiCndt').val() == 'SEL' && getNullToString(custInfoObj.busiCndt) != ''){
    custInfo = custInfo + '&busiCndt=' + ($('#inputBusiCndt').val() == 'SEL' ? '' : $('#inputBusiCndt').val());
    isChanged = true;
  }
	//업종
  if($('#inputBusiTp').val() != 'SEL' && $('#inputBusiTp').val() != getNullToString(custInfoObj.busiTp)){
    custInfo = custInfo + '&busiTp=' + ($('#inputBusiTp').val() == 'SEL' ? '' : $('#inputBusiTp').val());
    isChanged = true;
  }
	
  if($('#inputBusiTp').val() == 'SEL' && getNullToString(custInfoObj.busiTp) != ''){
    custInfo = custInfo + '&busiTp=' + ($('#inputBusiTp').val() == 'SEL' ? '' : $('#inputBusiTp').val());
    isChanged = true;
  }
  //담당자명
  if($('#inputBusiChrgNm').val() != getNullToString(custInfoObj.busiChrgNm)){
    custInfo = custInfo + '&busiChrgNm=' + $('#inputBusiChrgNm').val();
    isChanged = true;
  }
  
	//직책
  if($('#inputBusiChrgDuty').val() != getNullToString(custInfoObj.busiChrgDuty)){
    custInfo = custInfo + '&busiChrgDuty=' + $('#inputBusiChrgDuty').val();
    isChanged = true;
  }
	//전화번호
  if($('#inputBusiChrgTelno').val() != getNullToString(custInfoObj.busiChrgTelno)){
    custInfo = custInfo + '&busiChrgTelno=' + $('#inputBusiChrgTelno').val();
    isChanged = true;
  }
  if(isChanged == false){
    alert('<spring:message code="MSG.M06.MSG00026" />');
    return;
  }
 
	
  var url = '/customer/customer/customer/customerManagement/updateCustInfoAction.json';
  
  $.ajax({
        url:url,
        type:'POST',
        data : custInfo,
        dataType: 'json',
        success: function(data){
          alert('<spring:message code="MSG.M07.MSG00084"/>');
          $('#condSo').val(data.custInfo.soId);
          $('#condSo').selectmenu("refresh");
          $('#condCustNm').val(data.custInfo.custNm);
          $('#condCustId').val(data.custInfo.custId);
          setCustomerInfo(data.custInfo);
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
 * 저장 체크
 */
function checkValidation(mode){
	
	//사업ID필수 체크
	if($('#inputSoId').val()== 'SEL'){
		$('#inputSoId-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00003" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	
	//고객명 필수 체크
	var custNm = $("#inputCustNm").val();
	if(custNm == null || custNm.length == 0){
		$("#inputCustNm").focus();
		var item = '<spring:message code="LAB.M01.LAB00050" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//고객유형 필수 체크
	if($('#inputCustTp').val()== 'SEL'){
		$('#inputCustTp-button').focus();
		var item = '<spring:message code="LAB.M01.LAB00053" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}

	//고객신분 필수 체크
	if($('#inputCustCl').val()== 'SEL'){
		$('#inputCustCl-button').focus();
		var item = '<spring:message code="LAB.M01.LAB00048" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
  //주민/법인번호
  var corpRegNo = $("#inputCorpRegNo").val();
  if(corpRegNo == null || corpRegNo.length == 0 || corpRegNo.length != 14){
    $("#inputCorpRegNo").focus();
    var item = '<spring:message code="LAB.M09.LAB00188" />';
    alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
    return false;
  }


  //고객유형이 개인사업자, 법인, 공공기관 인경우 사업자 번호 필수
  if($('#inputCustTp').val() == 'B' || $('#inputCustTp').val() == 'D' || $('#inputCustTp').val() == 'E'){
    var businessNo = $("#inputBusinessNo").val();
    if(businessNo == null || businessNo.length == 0){
      $("#inputBusinessNo").focus();
      var item = '<spring:message code="LAB.M07.LAB00014" />';
      alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
      return false;
    }
  }

	
	//우편번호 체크
	var postNo = $("#inputPostNo").val();
	if(postNo == null || postNo.length == 0){
		$('#inputPostNo').focus();
		var item = '<spring:message code="LAB.M09.LAB00190" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//기본주소 체크
	var baseAddr = $("#inputBaseAddr").val();
	if(baseAddr == null || baseAddr.length == 0){
		$('#inputBaseAddr').focus();
		var item = '<spring:message code="LAB.M01.LAB00218" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//휴대번호 필수 체크
	var celTelNo = $("#inputCellPhoneNo").val();
	if(celTelNo == null || celTelNo.length == 0){
		$('#inputCellPhoneNo').focus();
		var item = '<spring:message code="LAB.M14.LAB00076" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//Email 필수 체크
	var email = $("#inputEmail").val();
	if(email == null || email.length == 0){
		$('#inputEmail').focus();
		var item = '<spring:message code="LAB.M08.LAB00119" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	// Email형식체크
	if(mode != 'U' && checkEmailStr(email) == false){
		$('#inputEmail').focus();
		alert('<spring:message code="MSG.M08.MSG00046" />');
		return false;
	}
	
	//고객유형이 개인, 개인사업자,외국인 인경우 인증체크
	// if($('#inputCustTp').val() == 'A' || $('#inputCustTp').val() == 'B' || $('#inputCustTp').val() == 'C'){
 //    if($('#isAuthYn').val() == 'N' && mode == 'I'){
	// 		$('#inputCorpRegNo').focus();
	// 		alert('<spring:message code="MSG.M07.MSG00125"/>');
	// 		return false;
	// 	}	
	// }
	//법인사업자일경우
	if($('#inputCustTp').val() == 'B' || $('#inputCustTp').val() == 'D' || $('#inputCustTp').val() == 'E' ){
		//대표자명
		var refNm = $("#inputRefNm").val();
		if(refNm == null || refNm.length == 0){
			$('#inputRefNm').focus();
			var item = '<spring:message code="LAB.M03.LAB00063" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
		//업태
		if($('#inputBusiCndt').val()== 'SEL'){
			$('#inputBusiCndt-button').focus();
			var item = '<spring:message code="LAB.M08.LAB00012" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
		
		//업종
		if($('#inputBusiTp').val()== 'SEL'){
			$('#inputBusiTp-button').focus();
			var item = '<spring:message code="LAB.M08.LAB00011" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
		
		var busiChrgNm = $("#inputBusiChrgNm").val();
		if(busiChrgNm == null || busiChrgNm.length == 0){
			$('#inputBusiChrgNm').focus();
			var item = '<spring:message code="LAB.M03.LAB00031" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
		
		var busiChrgTelno = $("#inputBusiChrgTelno").val();
		if(busiChrgTelno == null || busiChrgTelno.length == 0){
			$('#inputBusiChrgTelno').focus();
			var item = '<spring:message code="LAB.M09.LAB00065" />';
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			return false;
		}
	}
	
}


/*
 * 초기화 버튼 이벤트
 */
function initBtn(){

	//입력정보 초기화
	inputInit();
	
 	//고객정보 활성화
	$("#inputCustNm").removeClass('not-active');
	$("#inputCustNm").removeAttr('disabled');
	$("#inputSoId").selectmenu("enable");
	$("#inputCustTp").selectmenu("enable");
	$("#inputCustCl").selectmenu("enable");
	$("#inputState").selectmenu("enable");
	$("#inputCorpRegNo").removeClass('not-active');
	$("#inputCorpRegNo").removeAttr('disabled');
	$("#inputDtlAddr").removeClass('not-active');
	$("#inputDtlAddr").removeAttr('disabled');
	$("#inputTelNo").removeClass('not-active');
	$("#inputTelNo").removeAttr('disabled');
	$("#inputFaxNo").removeClass('not-active');
	$("#inputFaxNo").removeAttr('disabled');
	$("#inputCellPhoneNo").removeClass('not-active');
	$("#inputCellPhoneNo").removeAttr('disabled');
	$("#inputEmail").removeClass('not-active');
	$("#inputEmail").removeAttr('disabled');
	$('#inputSoId-button').focus();
	
	btnCtrl('N');

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
					<c:if test="${session_user.soAuthList.size() >1}">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					</c:if>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M01.LAB00050" /><span class="dot">*</span></th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w250" />
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch" href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	</thead>
	<input id="condCustId" type="text" hidden/>
</table> 


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
				<td>
        <input type="text" id="inputCustId" name="inputCustId" class="inp_date np custInfoCls" />
				</td>
			<th><spring:message code="LAB.M07.LAB00003" /><span class="dot">*</span></th> <!-- 사업 -->
			<td>
				<select id="inputSoId" name="soId" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
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
			<td><input id="inputCorpRegNo" name="corpRegNo" type="text" class="w100p" maxlength='14'/></td>
      <th><spring:message code="LAB.M07.LAB00014" /></th> <!-- 사업자번호 -->
      <td><input id="inputBusinessNo" name="businessNo" type="text" class="w100p" maxlength='12'/></td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00190" /><span class="dot">*</span></th><!-- 주소 -->
			<td colspan="7">
        <div class="date_box">
  				<div class="inp_date w100">
  					<input type="text" id="inputPostNo" name="zipCd" class="w70" placeholder='<spring:message code="LAB.M08.LAB00087"/>'> 
            <a id="searchAddrBtn" href="#" class="search" title='<spring:message code="LAB.M09.LAB00191" />' ></a>
          </div>
          <div class="inp_date w800">
  					<input type="text" id="inputBaseAddr" name="baseAddr" class="w300" placeholder='<spring:message code="LAB.M01.LAB00218"/>'>
  					<input type="text" id="inputDtlAddr" name="addrDtl" class="w330" placeholder='<spring:message code="LAB.M07.LAB00102"/>'>
          </div>
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
			<th><spring:message code="LAB.M03.LAB00063" /><span class="dot">*</span></th><!-- 대표자명 -->
			<td><input id="inputRefNm" name="refNm" type="text" class="w100p" /></td>
			<th><spring:message code="LAB.M08.LAB00012" /><span class="dot">*</span></th><!-- 업태 -->
			<td>
				<select id="inputBusiCndt" name="busiCndt" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${busiCndtCdList}" var="busiCndtCode" varStatus="status">
						<option value="${busiCndtCode.commonCd}">${busiCndtCode.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M08.LAB00011" /><span class="dot">*</span></th><!-- 업종 -->
			<td>
				<select id="inputBusiTp" name="busiTp" class="w100p">
					<option value='SEL'><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${busiTpCdList}" var="busiTpCode" varStatus="status">
						<option value="${busiTpCode.commonCd}">${busiTpCode.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>		
		</tr> 
		<tr>
			<th><spring:message code="LAB.M03.LAB00031" /><span class="dot">*</span></th><!-- 담당자명 -->
			<td><input id="inputBusiChrgNm" name="busiChrgNm" type="text" class="w100p" /></td>
			<th><spring:message code="LAB.M09.LAB00217" /></th><!-- 직책 -->
			<td>
				<input id="inputBusiChrgDuty" name="busiChrgDuty" type="text" class="w100p" />
			</td>
			<th><spring:message code="LAB.M09.LAB00065" /><span class="dot">*</span></th><!-- 담당자 전화번호 -->
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
	<div id="funcBtn" class="fl">
	<ntels:auth auth="${menuAuthR}">
		<!-- <a id="searchPymBtn" class="grey-btn" title='<spring:message code="LAB.M02.LAB00011"/>' href="#"><spring:message code="LAB.M02.LAB00011"/></a> -->
		<a id='custInfoHistBtn' class="grey-btn" title='<spring:message code="LAB.M01.LAB00055"/>' href="#"><spring:message code="LAB.M01.LAB00055"/></a>
	</ntels:auth>
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
</div>
<input id="isUnmaskYn" value='' type='text' hidden />
<input id="isAuthYn" value='' type='text' hidden />
<input id="extId" value='' type='text' hidden />

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>