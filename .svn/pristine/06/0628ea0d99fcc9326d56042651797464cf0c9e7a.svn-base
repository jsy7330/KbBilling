<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">

$(document).ready(function(){
	
	//화면 초기화 처리
	pageInit();
	
	//그리드 처리
	$("#soGrid").jqGrid({
		url : '/system/so/soMng/soMng/mainListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			condSoId : $("#condSoId").val(),
			condSo:$("#condSo").val()
		},
		colModel: [
				{ label: 'inchrgNm' , name: 'inchrgNm', hidden:true,width : 0},
				{ label: 'inchrgTelNo' , name: 'inchrgTelNo', hidden:true,width : 0,formatter:telNoFormatter},
				{ label: 'inchrgFaxNo' , name: 'inchrgFaxNo', hidden:true,width : 0,formatter:telNoFormatter},
				{ label: 'zipCd' , name: 'zipCd', hidden:true,width : 0},
				{ label: 'baseAddr' , name: 'baseAddr', hidden:true,width : 0},
				{ label: 'addrDtl' , name: 'addrDtl', hidden:true,width : 0},
				{ label: 'callcenterTelNo' , name: 'callcenterTelNo', hidden:true,width : 0,formatter:telNoFormatter},
				{ label: 'cnslUrl' , name: 'cnslUrl', hidden:true,width : 0},
				{ label: 'faxFrom' , name: 'faxFrom', hidden:true,width : 0},
				{ label: 'faxTo' , name: 'faxTo', hidden:true,width : 0},
		     	{ label: '<spring:message code="LAB.M07.LAB00010"/>', name: 'soId', width : 150, align:"center"},
			    { label: '<spring:message code="LAB.M07.LAB00013"/>', name: 'soNm', width : 200},
			    { label: '<spring:message code="LAB.M07.LAB00028"/>', name: 'useYn', width : 150,align:"center"}, 
			    { label: '<spring:message code="LAB.M03.LAB00082"/>', name: 'regrId', width : 180},           
			    { label: '<spring:message code="LAB.M03.LAB00080"/>', name: 'regDate', formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 180,align:"center"},    
			    { label: '<spring:message code="LAB.M07.LAB00256"/>', name: 'chgrId', width : 180},
			    { label: '<spring:message code="LAB.M07.LAB00254"/>', name: 'chgDate',  formatter: dateTypeFormatterYYYYMMDDHH24MISS, width : 180,align:"center"},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 240,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "soList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#jqGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedDate(rowid);
        },
       	loadComplete : function (data) {
       		$("#max").val(data.max);
       		$("#soGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		},
    	sortable: { update: function(permutation) {
    		$("#soGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	$("#soGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#soGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}); 
		
	$('#searchAddrBtn').click(function() {
		if($("#searchAddrBtn").hasClass('not-active')){
			return;
		}
		searchAddrBtn();

	});

	//초기화 버튼 이벤트
	$('#initBtn').click(function() {
		if($("#initBtn").hasClass('not-active')){
			return;
		}
		initBtn();
	});

	//검색 버튼 이벤트
	$('#btn_srch').click(function() {
		$("#soGrid").clearGridData();
		//입력부 초기화
		inputInit('Y');

		//버튼 컨트롤
		btnCtrl('I');

		$("#soGrid").setGridParam({
			postData : {
				condSoId : $("#condSoId").val(),
				condSo : $("#condSo").val(),
				srchYn:"Y"
			}
		});

		$("#soGrid").trigger("reloadGrid");
		
		var selectedSo = $("#condSoId").val();
        $("#soAuthList").each(function(index, item){
			var str = '<option value="'+ item.soId+'">'+ item.soNm+'</option>';
		      
			$('#condSoId').append(str);  
		});
      	$("#condSoId").val(selectedSo);
    	$("#condSoId").selectmenu("refresh"); 
	});
		
	//추가 버튼 클릭
	$("#newBtn").click(function() {
		if($("#newBtn").hasClass('not-active')){
			return;
		}
		var infoData = infoData + '&soId=' + $('#soId').val();
		infoData = infoData + '&soNm=' + $('#soNm').val().trim();
		infoData = infoData + '&inchrgNm=' + $('#inchrgNm').val().trim();
		infoData = infoData + '&inchrgTelNo=' +getTelNo($('#inchrgTelNo').val().trim());
		infoData = infoData + '&inchrgFaxNo=' +getTelNo($('#inchrgFaxNo').val());
		infoData = infoData + '&zipCd=' + $('#zipCd').val();
		infoData = infoData + '&baseAddr=' + $('#baseAddr').val();
		infoData = infoData + '&addrDtl=' + $('#addrDtl').val().trim();
		infoData = infoData + '&callcenterTelNo=' +getTelNo($('#callcenterTelNo').val());
		infoData = infoData + '&cnslUrl=' + $('#cnslUrl').val();
		infoData = infoData + '&faxFrom=' + $('#faxFrom').val();
		infoData = infoData + '&faxTo=' + $('#faxTo').val();
		var useYn=$('#soMnInfo select').serialize();
		var so=infoData+"&"+useYn;
  			
		if($("#soNm").val().trim() == ''|| ($("#soNm").val().trim()).length==0){
			$("#soNm").focus();
			alert('<spring:message code="MSG.M07.MSG00007"/>');
			return;
		}
		if($("#inchrgNm").val().trim() == ''|| ($("#inchrgNm").val().trim()).length==0){
			$("#inchrgNm").focus();
			alert('<spring:message code="MSG.M03.MSG00009"/>');
			return;
		}
		if($("#inchrgTelNo").val().trim() == ''|| ($("#inchrgTelNo").val().trim()).length==0){
			$("#inchrgTelNo").focus();
			alert('<spring:message code="MSG.M03.MSG00007"/>');
			return;
		}
		if($("#useYn").val() == 'SEL'){
			$("#useYn-button").focus();
			alert('<spring:message code="MSG.M07.MSG00018"/>');
			return;
		}
		if($("#zipCd").val() == ''){
			alert('<spring:message code="MSG.M08.MSG00035"/>');
			return;
		}
		
		if($("#baseAddr").val().trim() == ''|| ($("#baseAddr").val().trim()).length==0){
			$("#baseAddr").focus();
			alert('<spring:message code="MSG.M01.MSG00062"/>');
			return;
		}
		
		if($("#addrDtl").val().trim() == ''|| ($("#addrDtl").val().trim()).length==0){
			$("#addrDtl").focus();
			alert('<spring:message code="MSG.M07.MSG00062"/>');
			return;
		}
		
		$.ajax({
  	            url:'/system/so/soMng/soMng/insertAction.json',
  	            type:'POST',
  	            data : so,
  	            dataType: 'json',
  	            success: function(data){
	          		$("#soGrid").clearGridData();
	        		//입력부 초기화
	 	      		inputInit('Y');	//검색조건유지
	   	   	      	
 	   	          	$("#soGrid").setGridParam({
		  	   	        postData : {
		  	   	      		condSoId : $("#condSoId").val(),
		  	   	     		condSo : $("#condSo").val()
		  	   			}
 	   	          	});
  	   	          	
	 	      		alert('<spring:message code="MSG.M09.MSG00005"/>');
  	   	          	$("#soGrid").trigger("reloadGrid"); 
  	   	          		
  	   	         	$("#soGrid").trigger("reloadGrid");
   	          		
  	   	         	var selectedSo = $('#condSoId').val();
  	   	          
			  	    $('#condSoId').each( function() {
			   			$('#condSoId option:gt(0)').remove();
					});  
			  	     
	   	          	$(data.soAuthList).each(function(index, item){
				      	var str = '<option value="'+ item.soId+'">'+ item.soNm+'</option>';
				      	$('#condSoId').append(str);  
     		  		});
	   	          
					$("#condSoId").val(selectedSo);
					$("#condSoId").selectmenu("refresh");
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
		});
	
		$("#updateBtn").click(function() {
			if($("#updateBtn").hasClass('not-active')){
				return;
			}
			
   			var infoData = infoData + '&soId=' + $('#soId').val();
   			infoData = infoData + '&soNm=' + $('#soNm').val().trim();
   			infoData = infoData + '&inchrgNm=' + $('#inchrgNm').val().trim();
   			infoData = infoData + '&inchrgTelNo=' +getTelNo($('#inchrgTelNo').val().trim());
   			infoData = infoData + '&inchrgFaxNo=' +getTelNo($('#inchrgFaxNo').val());
   			infoData = infoData + '&zipCd=' + $('#zipCd').val();
   			infoData = infoData + '&baseAddr=' + $('#baseAddr').val();
   			infoData = infoData + '&addrDtl=' + $('#addrDtl').val().trim();
   			infoData = infoData + '&callcenterTelNo=' +getTelNo($('#callcenterTelNo').val());
   			infoData = infoData + '&cnslUrl=' + $('#cnslUrl').val();
   			infoData = infoData + '&faxFrom=' + $('#faxFrom').val();
   			infoData = infoData + '&faxTo=' + $('#faxTo').val();
   			var useYn=$('#soMnInfo select').serialize();
   			var so=infoData+"&"+useYn;
   			
   			if($("#soNm").val().trim() == ''|| ($("#soNm").val().trim()).length==0){
   				$("#soNm").focus();
   				alert('<spring:message code="MSG.M07.MSG00007"/>');
   				return;
   			}
   			if($("#inchrgNm").val().trim() == ''|| ($("#inchrgNm").val().trim()).length==0){
   				$("#inchrgNm").focus();
   				alert('<spring:message code="MSG.M03.MSG00009"/>');
   				return;
   			}
   			if($("#inchrgTelNo").val().trim() == ''|| ($("#inchrgTelNo").val().trim()).length==0){
   				$("#inchrgTelNo").focus();
   				alert('<spring:message code="MSG.M03.MSG00007"/>');
   				return;
   			}
   			if($("#useYn").val() == 'SEL'){
   				$("#useYn-button").focus();
				alert('<spring:message code="MSG.M07.MSG00018"/>');
				return;
			}
   			if($("#zipCd").val() == ''){
   				alert('<spring:message code="MSG.M08.MSG00035"/>');
   				return;
   			}
   			if($("#baseAddr").val().trim() == ''|| ($("#baseAddr").val().trim()).length==0){
   				$("#baseAddr").focus();
   				alert('<spring:message code="MSG.M01.MSG00062"/>');
   				return;
   			}
   			if($("#addrDtl").val().trim() == ''|| ($("#addrDtl").val().trim()).length==0){
   				$("#addrDtl").focus();
   				alert('<spring:message code="MSG.M07.MSG00062"/>');
   				return;
   			} 
			$.ajax({
   	            url:'/system/so/soMng/soMng/updateAction.json',
   	            type:'POST',
   	            data : so,
   	            dataType: 'json',
   	            success: function(data){
   	           
   	   	          		$("#soGrid").clearGridData();
   	   	        		//입력부 초기화
		   	   	      	inputInit('Y');
		   	   	      	
		   	   	      	//버튼 컨트롤
		   	   	      	btnCtrl('I');
		   	  
   	   	          		$("#soGrid").setGridParam({
			   	   	        postData : {
				   	   	      	condSoId : $("#condSoId").val(),
			   	   	     		condSo : $("#condSo").val()
			   	   			}
   	   	          		});
   	   	       	  		alert('<spring:message code="MSG.M07.MSG00089"/>');
   	   	      
   	   	          		$("#soGrid").trigger("reloadGrid");
   	   	          		var selectedSo = $('#condSoId').val();
   	   	          		
		   	   	        $('#condSoId').each( function() {
		 			    	$('#condSoId option:gt(0)').remove();
		 			    });  
		   	   	        
	   	   	          	$(data.soAuthList).each(function(index, item){
					    	var str = '<option value="'+ item.soId+'">'+ item.soNm+'</option>';
					    	$('#condSoId').append(str);  
	        		  	});
	   	   	    
	   	   	    	 	$("#condSoId").val(selectedSo);
	   	   	    	 	$("#condSoId").selectmenu("refresh");
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
		});
		//사업자명 keyup 이벤트
	  	$('#soNm').keyup(function(){
		  	var str = getMaxStr($('#soNm').val(), 75);
		  	if(str != $('#soNm').val()){
		  		$('#soNm').val(str);
		  	}
	  	});
		
		//담당자이름 keyup 이벤트
	  	$('#inchrgNm').keyup(function(){
		  	var str = getMaxStr($('#inchrgNm').val(), 75);
		  	if(str != $('#inchrgNm').val()){
		  		$('#inchrgNm').val(str);
		  	}
	  	});
		
		//담당자 전화번호 keyup이벤트
	  	$('#inchrgTelNo').keyup(function(){
	  		$('#inchrgTelNo').val(telNoAutoFormatter($('#inchrgTelNo').val()));
	  		$('#inchrgTelNo').val(getMaxStr($('#inchrgTelNo').val(), 20));
	  	});
		
		//팩스번호 keyup이벤트
	  	$('#inchrgFaxNo').keyup(function(){
	  		$('#inchrgFaxNo').val(telNoAutoFormatter($('#inchrgFaxNo').val()));
	  		$('#inchrgFaxNo').val(getMaxStr($('#inchrgFaxNo').val(), 14));
	  		}
		);
	  	
	  //상세주소 keyup 이벤트
	  	$('#addrDtl').keyup(function(){
		  	var str = getMaxStr($('#addrDtl').val(), 750);
		  	if(str != $('#addrDtl').val()){
		  		$('#addrDtl').val(str);
			}
	  	});
	  
	  //콜센터 전화번호 keyup이벤트
	  	$('#callcenterTelNo').keyup(function(){
	  		$('#callcenterTelNo').val(telNoAutoFormatter($('#callcenterTelNo').val()));
	  		$('#callcenterTelNo').val(getMaxStr($('#callcenterTelNo').val(), 20));
	  	});
	  
	  //콜센터 전화번호 keyup이벤트
	  	$('#faxFrom').keyup(function(){
	  		$('#faxFrom').val(telNoAutoFormatter($('#faxFrom').val()));
	  		$('#faxFrom').val(getMaxStr($('#faxFrom').val(), 4));
	  	});
	  //콜센터 전화번호 keyup이벤트
	  	$('#faxTo').keyup(function(){
	  		$('#faxTo').val(telNoAutoFormatter($('#faxTo').val()));
	  		$('#faxTo').val(getMaxStr($('#faxTo').val(), 4));
	  	});
	  
});//load

/*
 * 화면 초기화 함수
 */
function pageInit() {

	//검색정보 초기화
	
	$('#condSoId').val('SEL');
	$('#condSoId').selectmenu("refresh");
	$('#condSo').val('SEL');
	$('#condSo').selectmenu("refresh");
	$("#soGrid").clearGridData();

	//입력부 초기화
	inputInit('N');

}

function initBtn() {

	//입력정보 초기화
	inputInit('Y');
	$("#soId").val($("#max").val());
	//$("#soId").val('06');
	//기본정보 활성화 처리
	$('#soId').removeClass('not-active');
	$("#soId").addClass('not-active');
	$("#soId").attr('readonly', 'true');
	$('#soNm').removeClass('not-active');
	$('#soNm').removeAttr('disabled');
	$('#soNm').focus();
	$('#inchrgNm').removeClass('not-active');
	$('#inchrgNm').removeAttr('disabled');
	$('#inchrgTelNo').removeClass('not-active');
	$('#inchrgTelNo').removeAttr('disabled');
	$('#inchrgFaxNo').removeClass('not-active');
	$('#inchrgFaxNo').removeAttr('disabled');
	$('#zipCd').removeClass('not-active');
	$("#zipCd").addClass('not-active');
	$("#zipCd").attr('readonly', 'true');
	$('#baseAddr').removeClass('not-active');
	$("#baseAddr").addClass('not-active');
	$("#baseAddr").attr('readonly', 'true');
	$('#addrDtl').removeClass('not-active');
	$('#addrDtl').removeAttr('disabled');
	$('#callcenterTelNo').removeClass('not-active');
	$('#callcenterTelNo').removeAttr('disabled');
	$('#cnslUrl').removeClass('not-active');
	$('#cnslUrl').removeAttr('disabled');
	$('#faxFrom').removeClass('not-active');
	$('#faxFrom').removeAttr('disabled');
	$('#faxTo').removeClass('not-active');
	$('#faxTo').removeAttr('disabled');

	$("#soMnInfo select").selectmenu("enable");
	$("#useYn-button").removeClass('not-active');
	$("#useYn-button").removeAttr('disabled')
	
	
	
	$(".search").removeClass('not-active');
	$(".search").removeAttr('disabled');
	//버튼 컨트롤
	btnCtrl('N');
	$("#soGrid").jqGrid("resetSelection");
}
/*
 * 입력부 초기화 함수
 */
function inputInit(srch) {
	
	//검색정보 초기화
	if (srch=='Y') {	//검색조건유지
		$('#condSoId').val($('#condSoId').val());
		$('#condSo').val($('#condSo').val());
		
	}else{
		$('#condSoId').val('SEL'); 
		$('#condSoId').selectmenu("refresh");
		$('#condSo').val('SEL');
		$('#condSo').selectmenu("refresh");
	}
	//사업자 리스트 Disable처리
	$("#soMnInfo input:text").val('');
	$("#soMnInfo input:text").addClass('not-active');
	$("#soMnInfo input:text").attr('disabled', true);

	$(".search").addClass('not-active');
	$(".search").attr('disabled', true);
	$('#useYn').val('SEL');
	$('#useYn').selectmenu("refresh"); 
	$("#soMnInfo .ui-selectmenu-button").addClass('not-active');
	$("#soMnInfo .ui-selectmenu-button").attr('disabled', 'true'); 
	$("#soMnInfo select").selectmenu("disable");

	//공통 버튼 처리
	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled', true);

	if (srch=='Y') {
		
		$("#initBtn").addClass('grey-btn');
		$("#initBtn").removeClass('white-btn');
		$("#initBtn").removeClass('not-active');
		$("#initBtn").removeAttr('disabled');
	}
}

/*
 * 데이터 선택 이벤트 처리
 */
function setSelectedDate(rowId) {
	$("#soMnInfo input:text").removeClass('not-active');
	$("#soMnInfo input:text").removeAttr('disabled');
	$("#soMnInfo .ui-selectmenu-button").removeClass('not-active');
	$("#soMnInfo .ui-selectmenu-button").removeAttr('disabled');
	$("#soId").addClass('not-active');
	$("#soId").attr('disabled', true);
	$("#soId").attr('readonly', 'true');
	/* $("#zipCd").addClass('not-active');
	$("#zipCd").attr('readonly', 'true');
	$("#zipCd").attr('disabled', true);
	$("#baseAddr").addClass('not-active');
	$("#baseAddr").attr('readonly', 'true');
	$("#baseAddr").attr('disabled', true); */
	$(".search").removeClass('not-active');
	$(".search").removeAttr('disabled');
	$("#soMnInfo select").selectmenu("enable");
	
	var data = $("#soGrid").getRowData(rowId);
	$("#soId").val(data.soId);
	$("#soNm").val(data.soNm);
	$("#inchrgNm").val(data.inchrgNm);
	$("#inchrgTelNo").val(data.inchrgTelNo);
	$("#inchrgFaxNo").val(data.inchrgFaxNo);
	$("#zipCd").val(data.zipCd);
	$("#baseAddr").val(data.baseAddr);
	$("#addrDtl").val(data.addrDtl);
	$("#faxFrom").val(data.faxFrom);
	$("#faxTo").val(data.faxTo);
	$("#callcenterTelNo").val(data.callcenterTelNo);
	$("#cnslUrl").val(data.cnslUrl);

	$('#useYn').val(data.useYn);
	$('#useYn').selectmenu("refresh");
	
	
	
	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled', true);

	btnCtrl('U');
}

function btnCtrl(mode) {
	//공통 버튼 처리

	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled',true); 

	if (mode == 'I') {
		
			$("#initBtn").addClass('grey-btn');
			$("#initBtn").removeClass('white-btn');
			$("#initBtn").removeClass('not-active');
			$("#initBtn").removeAttr('disabled');
		
	} else if (mode == 'N') {
		$("#initBtn").addClass('grey-btn');
		$("#initBtn").removeClass('white-btn');
		$("#initBtn").removeClass('not-active');
		$("#initBtn").removeAttr('disabled');

		$("#newBtn").addClass('grey-btn');
		$("#newBtn").removeClass('white-btn');
		$("#newBtn").removeClass('not-active');
		$("#newBtn").removeAttr('disabled');

	} else if (mode == 'U') {
		$("#initBtn").addClass('grey-btn');
		$("#initBtn").removeClass('white-btn');
		$("#initBtn").removeClass('not-active');
		$("#initBtn").removeAttr('disabled');

		$("#updateBtn").addClass('grey-btn');
		$("#updateBtn").removeClass('white-btn');
		$("#updateBtn").removeClass('not-active');
		$("#updateBtn").removeAttr('disabled');

		$("#deleteBtn").addClass('grey-btn');
		$("#deleteBtn").removeClass('white-btn');
		$("#deleteBtn").removeClass('not-active');
		$("#deleteBtn").removeAttr('disabled');
	}
}
function searchAddrBtn(){
	
	var parameter = new Object();
   	parameter.zipCd = 'zipCd';
   	parameter.baseAddr = 'baseAddr';
	parameter.addrDtl = 'addrDtl';
   	
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

</script>
<input type="hidden" id="max" name="max"/>
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
	<div class="main_btn_box">
			<div class="fl"></div>
			<div class="fr mt10">
				<ntels:auth auth="${menuAuthR}">
					<a href="#" id="btn_srch" class="grey-btn" title="<spring:message code="LAB.M09.LAB00158"/>"><span
						class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
				</ntels:auth>
			</div>
	</div>
	<table class="writeview">
		
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 23%;">
			<col style="width: 10%;">
			<col style="width: 23%;">
			<col style="width: 10%;">
			<col style="width: 24%;">
		</colgroup>
		<thead>
			<tr id="cond">
				<th title="<spring:message code="LAB.M07.LAB00013"/>"><spring:message code="LAB.M07.LAB00013"/></th>
				<td>
					<select id="condSoId" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.soId}">${soAuthList.soNm}</option>
					</c:forEach>
					</select>
				</td>
				<th title="<spring:message code="LAB.M07.LAB00028"/>"><spring:message code="LAB.M07.LAB00028"/></th>
				<td>
					<select id="condSo" name="condSo" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
			</tr>
		</thead>
	</table>
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00009"/></h4>
		</div>
		<div id="commonBtn" class="fr">
			<ntels:auth auth="${menuAuthP}">
			<a id="printBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00079"/>' href="#"><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
			</ntels:auth>
		</div>
	</div>
	<div id='gridDiv' class="w100p">
			<table id="soGrid" class="w100p"></table>
			<div id="jqGridPager"></div>
	</div> 

 <div id="soMnInfo">
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M07.LAB00016"/></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
			<col style="width: 10%;">
			<col style="width: 15%;">
		</colgroup>
		<tbody>
			<tr>
				<th><spring:message code="LAB.M07.LAB00010"/><span class="dot">*</span></th>
				<td><input type="text" id="soId" name="soId" class="w100p" /></td>
				<th><spring:message code="LAB.M07.LAB00013"/><span class="dot">*</span></th>
				<td colspan="5"><input type="text" id="soNm" name="soNm"class="w100p" /></td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00029"/><span class="dot">*</span></th>
				<td><input type="text" id="inchrgNm" name="inchrgNm" class="w100p" /></td>
				<th><spring:message code="LAB.M08.LAB00021"/><span class="dot">*</span></th>
				<td><input type="text" id="inchrgTelNo" name="inchrgTelNo"class="w100p" /></td>
				<th><spring:message code="LAB.M15.LAB00035"/></th>
				<td><input type="text" id="inchrgFaxNo" name="inchrgFaxNo"class="w100p" /></td>
				<th><spring:message code="LAB.M07.LAB00028"/><span class="dot">*</span></th>
				<td>
					<select id="useYn" name="useYn" class="w100p">
						<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00190"/><span class="dot">*</span></th>
				<td colspan="7">
					<div class="date_box">
						<div class="inp_date w130">
							<input type="text" id="zipCd" name="zipCd" class="w130">
							 <a id="searchAddrBtn" href="#" class="search"><spring:message code="LAB.M09.LAB00191" /></a>
						</div>
						<div class="inp_date w400">
							<input type="text" id="baseAddr" name="baseAddr" class="w400"> 
							
						</div>
						<div class="inp_date w300">
							<input type="text" id="addrDtl" name="addrDtl" class="w300">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M11.LAB00013"/></th>
				<td><input type="text" id="callcenterTelNo" name="callcenterTelNo"/></td>
				<th><spring:message code="LAB.M07.LAB00084"/></th>
				<td><input type="text" id="cnslUrl" name="cnslUrl"class="w100p" /></td>
				<th><spring:message code="LAB.M11.LAB00014"/></th>
				<td colspan="3">
					<input type="text" id="faxFrom" name="faxFrom" class="w47p"> ~
					<input type="text" id="faxTo" name="faxTo" class="w47p">
				</td>
			</tr>
		</tbody>
	</table> 
</div>		
<div class="main_btn_box">
	<div id="commonBtn" class="fr">
		<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
		<ntels:auth auth="${menuAuthC}">
		<a id="newBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>
		<ntels:auth auth="${menuAuthU}">
		<a id="updateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		 <ntels:auth auth="${menuAuthD}">
		<a class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth> 
	</div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;"></div>

