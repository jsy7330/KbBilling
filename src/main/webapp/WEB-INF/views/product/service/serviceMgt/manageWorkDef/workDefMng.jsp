<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- amchart -->
<script src="/scripts/amcharts_2.4.2/amcharts.js" type="text/javascript"></script>
<script src="/scripts/amcharts_2.4.2/serial.js" type="text/javascript"></script> 
<script type="text/javascript">

	$(document).ready(function() {
		
		$("#searchSoId").val("${session_user.soId}");
		$('#searchSoId').selectmenu("refresh");		
		var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
		
		initGrid(lng); //jqgrid 호출
	
		$('#btn_insert').click(function(){
			goProcess("I");
		});
		
		$('#btn_update').click(function(){
			goProcess("U");
		});
		
		$('#btn_delete').click(function(){
			goProcess("D");
		});
		
	});

function initGrid(lng) {
	
	$("#workDefMngTable").jqGrid({

		url : 'workDefMngListAction.json?',
		mtype : "POST",
		datatype : "json",
		postData : {},
	   	colModel: [ 
			{ label: '<spring:message code="LAB.M09.LAB00022" />',  name: 'wrkTypNm', width : 20, align:"left"},
			{ label: '<spring:message code="LAB.M09.LAB00007" />', name: 'wrkId', width : 20, align:"left"},
			{ label: '<spring:message code="LAB.M09.LAB00017" />', name: 'wrkNm', width : 20, align:"left"},
			{ label: '<spring:message code="LAB.M09.LAB00019" />', name: 'wrkDesc', width : 70, align:"left"},
			{ label: '<spring:message code="LAB.M07.LAB00026" />', name: 'useYnNm', width : 20, align:"left"},
			{ label: 'wrkTyp' , name: 'wrkTyp', hidden:true,width : 0},
			{ label: 'wrkId' , name: 'wrkId', hidden:true,width : 0},
			{ label: 'useYn' , name: 'useYn', hidden:true,width : 0},
		],
		rowNum:25,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#jqGridPager',
		//autowidth:true,
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		sortorder: "desc",
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		onCellSelect : function(rowid, index, contents, event){
			btnActive("btn_update");
	    	btnActive("btn_delete");
        	setSelectedData(rowid);
		},
		loadComplete: function(obj){
			if($("#workDefMngTable").getGridParam("reccount") > 0) {
				btnActive("btn_init");
				btnNonActive("btn_insert");
		    	btnNonActive("btn_update");
		    	btnNonActive("btn_delete");
	        }
	        else 
	        {
	        	btnActive("btn_init");
				btnNonActive("btn_insert");
		    	btnNonActive("btn_update")
		    	btnNonActive("btn_delete");
	        }
		}
	});
	
	$("#workDefMngTable").setGridWidth($('#gridDiv').width(),false);
	$(window).resize(function() {
		$("#workDefMngTable").setGridWidth($('#gridDiv').width(),false);//그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

function init() {
	
	$("#wrkTyp").val('');
	$("#wrkTyp").selectmenu("refresh");
	$("#wrkTyp").focus();
	$("#useYn").val('');
	$("#useYn").selectmenu("refresh");
	$("#wrkNm").val('');
	$("#wrkDesc").val('');
	
	btnActive("btn_insert");
	btnNonActive("btn_update");
	btnNonActive("btn_delete");
	
	
}

function actionAfterinit() {
	$("#wrkTyp").val('');
	$("#wrkTyp").selectmenu("refresh");
	$("#useYn").val('');
	$("#useYn").selectmenu("refresh");
	$("#wrkNm").val('');
	$("#wrkDesc").val('');
}

function setSelectedData(rowId) {	
	var data = $("#workDefMngTable").getRowData(rowId);
	
	$("#wrkTyp").val(data.wrkTyp);
	$("#wrkTyp").selectmenu("refresh");
	$("#useYn").val(data.useYn);
	$("#useYn").selectmenu("refresh");
	$("#wrkNm").val(data.wrkNm);
	$("#wrkDesc").val(data.wrkDesc);
	$("#wrkId").val(data.wrkId);
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

function labelActive(id){
	$('#'+id).addClass('white-btn');
	$('#'+id).removeClass('grey-btn');
	$('#'+id).removeClass('not-active');
	$('#'+id).removeAttr('disabled');
}

function labelNonActive(id){
	$('#'+id).addClass('grey-btn');
	$('#'+id).removeClass('white-btn');
	$('#'+id).addClass('not-active');
	$('#'+id).attr('disabled',true);
}

function goProcess(type) {
	var param = checkValidation();
	var url = "";
	if(type == 'I'){		
		url = "insertWorkDefMngInfo.json";
	}else if(type == 'U'){		
		url = "updateWorkDefMngInfo.json";
	}else if(type == 'D'){
		url = "deleteWorkDefMngInfo.json";
	}
	
	if(param != false){
		if(type == 'I') {
			var check = confirm('<spring:message code="MSG.M09.MSG00008" />');   //I
		}
		else if(type == 'U') {
			var check = confirm('<spring:message code="MSG.M07.MSG00093" />'); //U
		}
		else if(type == 'D') {
			var check = confirm('<spring:message code="MSG.M07.MSG00054" />'); //D
		}
		
		if(!check){
			return;
		}
		
		$.ajax({
			url : url,
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				if(data.result != "0"){
					if(type == 'I'){		
						alert('<spring:message code="MSG.M09.MSG00005" />');
					}else if(type == 'U'){		
						alert('<spring:message code="MSG.M09.MSG00005" />');
					}else if(type == 'D'){
						alert('<spring:message code="MSG.M07.MSG00053" />');
					}
					
					$("#workDefMngTable").clearGridData();  // 이전 데이터 삭제
					$("#workDefMngTable").setGridParam({ postData: param }).trigger("reloadGrid");
					actionAfterinit();
				}
			},
		    error: function(err){
		    	if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
	     			alert(err.responseJSON.exceptionMsg);
	     		}else{
	     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
	     		}
		    }
		});
	}
	
	
}

//유효성 체크
function checkValidation(){
	var param = new Object();
	param.wrkId  = $("#wrkId").val();
	param.wrkTyp = $("#wrkTyp").val();
	param.useYn  = $("#useYn").val();
	param.wrkNm  = $("#wrkNm").val();
	param.wrkDesc = $("#wrkDesc").val();
	
	if(param.wrkTyp == ''){
		$("#wrkTyp-button").focus();
		alert('<spring:message code="LAB.M09.LAB00023"/>'); //작업타입을 선택해 주세요
		return false;
	}
	
	if(param.wrkNm == ''){
		$("#wrkNm").focus();
		alert('<spring:message code="LAB.M09.LAB00026"/>'); //작업명을 입력해 주세요
		return false;
	}
	
	if(param.useYn == ''){
		$("#useYn-button").focus();
		alert('<spring:message code="LAB.M09.LAB00026"/>'); //사용여부를 선택해 주세요
		return false;
	}
	
	param.searchYymm = dateFormatToStringYYYYMM($("#searchYymm").val()); //기준년월
	return param;
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

<div id='gridDiv' class="w100p">
	<table id="workDefMngTable" class="w100p"></table>
	<div id="jqGridPager"></div>
</div>

	<div class="main_btn_box">
		<div class="fl">

		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:40%;">
			<col style="width:10%;">
			<col style="width:40%;">
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M09.LAB00022"/><span class="dot">*</span></th>
				<td>
					<select id="wrkTyp" name="wrkTyp" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/>
						<c:forEach items="${wrkTyp}" var="wrkTyp" varStatus="status">
						<option value="${wrkTyp.commonCd}">${wrkTyp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M07.LAB00026"/><span class="dot">*</span></th>
				<td>
					<select id="useYn" name="useYn" class="w270">
						<option value=""><spring:message code="LAB.M15.LAB00002"/>
						<c:forEach items="${useYn}" var="useYn" varStatus="status">
						<option value="${useYn.commonCd}">${useYn.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>	
			   <th><spring:message code="LAB.M09.LAB00017"/><span class="dot">*</span></th>
			   <td>
				  <input id="wrkNm" name="wrkNm" type="text" class="w270" maxLength="10">
			   </td>
			   <th><spring:message code="LAB.M09.LAB00019"/></th>
			   <td>
				  <input id="wrkDesc" name="wrkDesc" type="text" class="w270" maxLength="190">
			   </td>
			</tr>
		</tbody>
	</table> 
<!--버튼-->	
<div class="main_btn_box">
	<div class="fr">
		<!--초기화-->		
		<a class="grey-btn"  href="javascript:init();"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>		
		<!--등록-->
		<ntels:auth auth="${menuAuthC}">
		<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>		
		<!--수정-->
		<ntels:auth auth="${menuAuthU}">
		<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		<!--삭제-->
		<ntels:auth auth="${menuAuthD}">
		<a class="grey-btn" id="btn_delete" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth>
	</div>
  </div>
</div>
<input id="wrkId" value='0' type='text' hidden />

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

