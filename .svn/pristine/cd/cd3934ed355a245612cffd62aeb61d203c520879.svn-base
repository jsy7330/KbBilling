<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

$(document).ready(function() {
	//화면 초기화 처리
	pageInit();
	
	
	//언어정보 그리드
	$("#lngGridPopup").jqGrid({
		datatype : 'local',
		colModel: [
				{ label: 'language_code' , name: 'language_code', hidden:true,width : 0},
				{ label: '<spring:message code="LAB.M08.LAB00004"/>', name: 'language_name', width : 100, align:"left",sortable:false},
				{ label: '<spring:message code="LAB.M03.LAB00001"/>', name: 'code_nm', width : 200, align:"left",sortable:false, editable:true},
				
		],
 		data : '',
		viewrecords: true,
		shrinkToFit:false,
		height: 77,
		width: 700,
		sortable : false,
		cellEdit : true,
		cellsubmit:'clientArray',
		rowNum: -1,  
        onCellSelect : function(rowid, index, contents, event){
        	
        },
       	loadComplete : function (data) {
       		$("#lngGridPopup").setGridWidth($("#gridLngDivPopup").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       	},
    	sortable: { update: function(permutation) {
       		$("#lngGridPopup").setGridWidth($("#gridLngDivPopup").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	
	$("#lngGridPopup").setGridWidth($("#gridLngDivPopup").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	
  	//코드 그룹 신규등록 버튼 이벤트
    $('#grpNewBtnPopup').on('click',function (e) {
    	if($("#grpNewBtnPopup").hasClass('not-active')){
			return;
		}
    		insertNewCodeGrp();
		}
    );
	
	//코드 그룹 수정 버튼 이벤트
	$('#grpUpdateBtnPopup').on('click',function (e) {
    	if($("#grpUpdateBtnPopup").hasClass('not-active')){
			return;
		}
    		insertUpdateCodeGrp();
		}
    );
	
  	//코드영문 숫자만 입력
    $("#inputCodeGrpPopup").keyup(function(event){ 
	 	   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		 	    var inputVal = $(this).val();
		 	    $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
		 	    
		  	    var str = getMaxStr($('#inputCodeGrpPopup').val(), 10);
		  	    if(str != $('#inputCodeGrpPopup').val()){
		  			$('#inputCodeGrpPopup').val(str);
		  	    }
	 	   }
	});
  	
  	
  	//코드명 keyup 이벤트
  	$('#inputCodeGrpNmPopup').keyup(function(){
	  		var str = getMaxStr($('#inputCodeGrpNmPopup').val(), 70);
	  		if(str != $('#inputCodeGrpNmPopup').val()){
	  			$('#inputCodeGrpNmPopup').val(str);
	  		}
  		}
	);
  	
  	//순서 숫자만 입력
    $("#inputLengthPopup").keyup(function(event){ 
	 	   if (!(event.keyCode >=37 && event.keyCode<=40)) {
		 	    var inputVal = $(this).val();
		 	    $(this).val(inputVal.replace(/[^0-9]/gi,''));
		 	    
		  	    var str = getMaxStr($('#inputLengthPopup').val(), 2);
		  	    if(str != $('#inputLengthPopup').val()){
		  			$('#inputLengthPopup').val(str);
		  	    }
	 	   }
	});
  	
  	//Ref1 keyup 이벤트
  	$('#inputRef1Popup').keyup(function(){
	  		var str = getMaxStr($('#inputRef1Popup').val(), 400);
	  		if(str != $('#inputRef1Popup').val()){
	  			$('#inputRef1Popup').val(str);
	  		}
  		}
	);
  	
  	//Ref2 keyup 이벤트
  	$('#inputRef2Popup').keyup(function(){
	  		var str = getMaxStr($('#inputRef2Popup').val(), 400);
	  		if(str != $('#inputRef2Popup').val()){
	  			$('#inputRef2Popup').val(str);
	  		}
  		}
	);
  	
  	//Ref3 keyup 이벤트
  	$('#inputRef3Popup').keyup(function(){
	  		var str = getMaxStr($('#inputRef3Popup').val(), 400);
	  		if(str != $('#inputRef3Popup').val()){
	  			$('#inputRef3Popup').val(str);
	  		}
  		}
	);
  	
  	//Rmrk keyup 이벤트
  	$('#inputRmrkPopup').keyup(function(){
	  		var str = getMaxStr($('#inputRmrkPopup').val(), 750);
	  		if(str != $('#inputRmrkPopup').val()){
	  			$('#inputRmrkPopup').val(str);
	  		}
  		}
	);
});


/*
 * 화면 초기화 함수
 */
function pageInit(){
	$("#commonCodeGrpPopupBody input:text").addClass('not-active');
	$("#commonCodeGrpPopupBody input:text").attr('disabled', true);
	
	$('#selectUseYnPupup').selectmenu();
    $('#selectUseYnPupup').val('SEL');
    $('#selectUseYnPupup').selectmenu("refresh");
	
	var mode = "<c:out value='${mode}'/>";
	var systemId = "<c:out value='${systemId}'/>";
	var systemNm = "<c:out value='${systemNm}'/>";
	var codeGrp = "<c:out value='${codeGrp}'/>";
	
	$('#inputSystemNmPopup').val(systemNm);
	$('#inputSystemIdPopup').val(systemId);
	if(mode == 'I'){
		$("#commonCodeGrpPopupBody .type_iu").removeClass('not-active'); 
		$("#commonCodeGrpPopupBody .type_iu").removeAttr('disabled');
		$("#grpUpdateBtnPopup").hide();
		var url = "/system/configuration/codeMng/commonCodeMng/getLngListAction.json"
		 	$.ajax({
		        url:url,
		        type:'POST',
		        dataType: 'json',
		        success: function(data){
		        	$("#lngGridPopup").clearGridData();
		         	$("#lngGridPopup").setGridParam({
		         		data : data.lngList,
		         		rowNum: data.lngList.length
		        	});
		        	$("#lngGridPopup").trigger("reloadGrid");
		        	$("#inputCodeGrpPopup").focus();
		        },
		     	beforeSend: function(data){
		     	},
		     	error : function(err){
		     	}
		    });
		
		
	}else if(mode == 'U'){
		$("#commonCodeGrpPopupBody .type_u").removeClass('not-active'); 
		$("#commonCodeGrpPopupBody .type_u").removeAttr('disabled');
		$("#grpNewBtnPopup").hide();
		var parameter = "codeGrp=" + codeGrp;
		var url = "/system/configuration/codeMng/commonCodeMng/getCommonGrpInfoAction.json"
		 	$.ajax({
		        url:url,
		        type:'POST',
		        data : parameter,
		        dataType: 'json',
		        success: function(data){
		        	
		        	$('#inputCodeGrpPopup').val(data.codeGrp.commonGrp);
		        	$('#inputCodeGrpNmPopup').val(data.codeGrp.commonGrpNm);
		        	$('#inputLengthPopup').val(data.codeGrp.length);
		        	$('#selectUseYnPupup').val(data.codeGrp.useYn);
		        	$('#selectUseYnPupup').selectmenu("refresh");
		        	$('#inputRef1Popup').val(data.codeGrp.refCode);
		        	$('#inputRef2Popup').val(data.codeGrp.refCode2);
		        	$('#inputRef3Popup').val(data.codeGrp.refCode3);
		        	$('#inputRmrkPopup').val(data.codeGrp.rmrk);
		        	$("#lngGridPopup").clearGridData();
		         	$("#lngGridPopup").setGridParam({
		         		data : data.codeGrp.codeLngList,
		         		rowNum: data.codeGrp.codeLngList.length
		        	});
		        	$("#lngGridPopup").trigger("reloadGrid");
		        },
		     	beforeSend: function(data){
		     	},
		     	error : function(err){
		     	}
		    });
	}
}

/*
 * 신규등록
 */
function insertNewCodeGrp(){
	if(checkValidationCodeGrp() == false){
		return;
	}

	var url = "/system/configuration/codeMng/commonCodeMng/insertCodeGrpAction.json"
	
	var commonCode = new Object();
	commonCode.codeGrp = $('#inputCodeGrpPopup').val();
	commonCode.codeGrpNm = $('#inputCodeGrpNmPopup').val();
	commonCode.systemId = $('#inputSystemIdPopup').val();
	commonCode.length = $('#inputLengthPopup').val();
	commonCode.useYn = $('#selectUseYnPupup').val();
	commonCode.refCode = $('#inputRef1Popup').val();
	commonCode.refCode2 = $('#inputRef2Popup').val();
	commonCode.refCode3 = $('#inputRef3Popup').val();
	commonCode.rmrk = $('#inputRmrkPopup').val();
	commonCode.codeGrpLngList = $("#lngGridPopup").getRowData();
	
	$.ajax({
        url:url,
        type:'POST',
        data : JSON.stringify(commonCode),
        dataType: 'json',
        contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
    		$('#mask, .Layer_wrap').hide();
    		reloadCodeGrp($('#inputCodeGrpPopup').val());
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

/**
 * 수정
 */
function insertUpdateCodeGrp(){
	if(checkValidationCodeGrp() == false){
		return;
	}
	
	var url = "/system/configuration/codeMng/commonCodeMng/updateCodeGrpAction.json"
		
	var commonCode = new Object();
	commonCode.codeGrp = $('#inputCodeGrpPopup').val();
	commonCode.codeGrpNm = $('#inputCodeGrpNmPopup').val();
	commonCode.systemId = $('#inputSystemIdPopup').val();
	commonCode.length = $('#inputLengthPopup').val();
	commonCode.useYn = $('#selectUseYnPupup').val();
	commonCode.refCode = $('#inputRef1Popup').val();
	commonCode.refCode2 = $('#inputRef2Popup').val();
	commonCode.refCode3 = $('#inputRef3Popup').val();
	commonCode.rmrk = $('#inputRmrkPopup').val();
	commonCode.codeGrpLngList = $("#lngGridPopup").getRowData();
	
	$.ajax({
        url:url,
        type:'POST',
        data : JSON.stringify(commonCode),
        dataType: 'json',
        contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
    		$('#mask, .Layer_wrap').hide();
    		reloadCodeGrp($('#inputCodeGrpPopup').val());
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
function checkValidationCodeGrp(){
	//그룹 필수 체크
	var code = $("#inputCodeGrpPopup").val();
	if(code == null || code.length == 0){
		$("#inputCodeGrpPopup").focus();
		var item = '<spring:message code="LAB.M11.LAB00007" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//그룹명 필수 체크
	var codeNm = $("#inputCodeGrpNmPopup").val();
	if(codeNm == null || codeNm.length == 0){
		$("#inputCodeGrpNmPopup").focus();
		var item = '<spring:message code="LAB.M11.LAB00008" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//시스템명 필수 체크
	var systemNm = $("#inputSystemNmPopup").val();
	if(systemNm == null || systemNm.length == 0){
		var item = '<spring:message code="LAB.M07.LAB00285" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//사용유무 필수체크
	if($('#selectUseYnPupup').val()== 'SEL'){
		$('#selectUseYnPupup-button').focus();
		var item = '<spring:message code="LAB.M07.LAB00026" />';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return false;
	}
	
	//언어 필수 체크
	var data = $("#lngGridPopup").getRowData();
	var checkGrid = true;
	$.each(data, function(index, value){
		$("#lngGridPopup").editCell(index,1,false);
		var row = $("#lngGridPopup").jqGrid('getRowData', index);
		if(row.code_nm == ''){
			var item = '<spring:message code="LAB.M03.LAB00001" />' + ':' + row.language_name;
			alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
			checkGrid = false;
			return false;
		}
	});
	
	if(checkGrid == false){
		return false;
	}
}




</script>

		<!-- title -->
		 <div class="layer_top">
		     <div class="title"><spring:message code="LAB.M11.LAB00007"/></div>
		     <a href="#" class="close"></a>
		</div>
		<!--// title -->
		                                         
		<!-- center -->
		<div id='commonCodeGrpPopupBody' class="layer_box">
			<table class="writeview">
				<colgroup>
					<col style="width: 16%;">
					<col style="width: 17%;">
					<col style="width: 16%;">
					<col style="width: 17%;">
					<col style="width: 16%;">
					<col style="width: 18%;">
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="LAB.M11.LAB00007"/><span class="dot">*</span></th>
						<td><input type="text" id="inputCodeGrpPopup" name="commonGrp" class="w100p not-active type_iu" disabled="disabled"></td>
						<th><spring:message code="LAB.M11.LAB00008"/><span class="dot">*</span></th>
						<td colspan='3'><input type="text" id="inputCodeGrpNmPopup" name="commonGrpNm" class="w100p not-active type_iu type_u" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00285"/><span class="dot">*</span></th>
						<td><input type="text" id="inputSystemNmPopup" name="systemNm" class="w100p not-active" disabled="disabled">
						    <input type="text" id="inputSystemIdPopup" name="systemId" hidden>
						</td>
						<th><spring:message code="LAB.M01.LAB00228"/></th>
						<td><input type="text" id="inputLengthPopup" name="length" class="w100p not-active type_iu type_u" disabled="disabled"></td>
						<th><spring:message code="LAB.M07.LAB00026"/><span class="dot">*</span></th>
						<td><select id="selectUseYnPupup" class="w100">
								<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
								<option value="Y"><spring:message code="LAB.M15.LAB00103"/></option>
								<option value="N"><spring:message code="LAB.M15.LAB00063"/></option>
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M10.LAB00003"/></th>
						<td colspan='5'><input type="text" id="inputRef1Popup" name="refCode" class="w100p not-active type_iu type_u" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M10.LAB00004"/></th>
						<td colspan='5'><input type="text" id="inputRef2Popup" name="refCode2" class="w100p not-active type_iu type_u" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M10.LAB00005"/></th>
						<td colspan='5'><input type="text" id="inputRef3Popup" name="refCode3" class="w100p not-active type_iu type_u" disabled="disabled"></td>
					</tr>
					<tr>
						<th><spring:message code="LAB.M07.LAB00200"/></th>
						<td colspan='5'><input type="text" id="inputRmrkPopup" name="rmrk" class="w100p not-active type_iu type_u" disabled="disabled"></td>
					</tr>
				</tbody>
			</table>
			<div id='gridLngDivPopup' class='mt10'>
				<table id="lngGridPopup" class="w100p"></table>
			</div>
			 <div class="btn_box">
			    <span id="commonBtn">
					<a id="grpNewBtnPopup" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
					<a id="grpUpdateBtnPopup" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
					<a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
				</span>
			</div>
		</div>
		<!-- footer -->

