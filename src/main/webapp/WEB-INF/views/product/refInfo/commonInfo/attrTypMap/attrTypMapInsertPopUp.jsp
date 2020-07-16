<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!--��¥ �޷� ����� ��� �ʿ�-->
<script type="text/javascript" src="/scripts/nccbs/function.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$( ".date" ).datepicker();
	var date1=new Date();
	date1.setDate( date1.getDate() + 1 );
	reault_y = date1.getFullYear();
	reault_m = eval(date1.getMonth()+1);
	reault_d = date1.getDate();
	if(parseInt(reault_m)<10){reault_m="0"+reault_m;}
	if(parseInt(reault_d)<10){reault_d="0"+reault_d;}

	var date2 = null;	

	if('${lngTyp}'=='ko'){
		date2 = reault_y + "-" + reault_m + "-" + reault_d;	
	}else {
		date2 =  reault_m + "-" + reault_d + "-" +reault_y; 
	}

	$("#insActDt").val(date2);
	$("#nowDate").val(date2);

	initGrid();	//jqgrid ȣ��	

	// ����Ʈ �ڽ� �� ��������
	 $('#searchAttrTyp2').val('${attrTypMap.searchAttrTyp2}');
	 $('#searchAttrTyp2').selectmenu("refresh");

	//��ư Ŭ����
	$("#btn_search").click(function() {
		$("#page").val("1");
		goSearch();
	});

	//�Ӽ� ���� ���� �� �˻�
	$('#searchAttrTyp2').selectmenu({
	    change: function() {
			var param = new Object();
			param.searchAttrTyp =  $("#searchAttrTyp2").val();
			searchDataP1();
	    }
	});


	//그리드 재검색 
	function searchDataP1(){
		$("#attributePop").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.searchAttrTyp =  $("#searchAttrTyp2").val();
		
        $("#attributePop").setGridParam({ postData: param }).trigger("reloadGrid");
        
        $("#attributePop").clearGridData();  // 이전 데이터 삭제
        
	}

	var resultMsg = "${resultMsg}";
	if(resultMsg != null && resultMsg != "") {
		alert("${resultMsg}");
	}
});

function getParam(){
	var param = new Object();	
	param.searchAttrTyp2 =  '${attrTypMap.searchAttrTyp2}';
	return param;
}

/* Modal */
function closeModal() {
	$("#popup_dialog").remove();
}


	function initGrid() {
		
		var param = new Object();
		param.searchAttrTyp2 =  '${attrTypMap.searchAttrTyp2}';
		$("#attributePop").jqGrid({
			
		   	url:'attrTypMapInsertActionPopUp.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",	
		   	colModel: [ 
	   			{ label: '<spring:message code="LAB.M07.LAB00214" />', name: 'attrNm', width : 170},	
	   			{ label: '<spring:message code="LAB.M09.LAB00052" />', name: 'actDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 80},		
	   			{ label: '<spring:message code="LAB.M09.LAB00058" />', name: 'inactDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 80},		
	   			{ label: 'attrTyp' , name: 'attrTyp', hidden:true,width : 0},
	   			{ label: 'attrCd' , name: 'attrCd', hidden:true,width : 0},
	   			{ label: 'mstrFl' , name: 'mstrFl', hidden:true,width : 0}
	   		],

		   	rowNum:100,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#pager3',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			multiselect: true,    
			height: 140,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        	//setSelectedDate(rowId);
	        },
	        loadComplete: function(obj){
	        	$("#attributePop").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    }
		});
		
		$("#attributePop").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}



function buttonEvent(){
	var ids = jQuery("#attributePop").jqGrid('getGridParam','selarrrow');	//üũ�� Row ID �迭�� ��ȯ
	var value = "";
	for(var i=0; i<ids.length; i++){
		 value = value + $("#attributePop").getRowData(ids[i]).attrCd+",";		//Object Key���� name�� Value�� ��ȯ
	}
	$("#insAttrCd").val(value);	

	var searchAttrTyp2 =$("#searchAttrTyp2").val();
	var insActDt =  "";
	var nowDate = "";

	if('${lngTyp}'=='ko'){
		insActDt = $('#insActDt').val();
		nowDate = $('#nowDate').val();

	}else {
		insActDt = dateFormatToStringYYYYMMDD($('#insActDt').val());
		nowDate = dateFormatToStringYYYYMMDD($('#nowDate').val());
	}

	$("#insActDt").val(insActDt);	
	$("#nowDate").val(nowDate);	
	$("#searchAttrTyp2").val(searchAttrTyp2);	


	$("#frmAttrTypMapInsList").attr("action", "/product/refInfo/commonInfo/attrTypMap/attrTypMapInsertAction");
	$("#frmAttrTypMapInsList").attr("method", "post");
	$("#frmAttrTypMapInsList").submit();
}

</Script>
<!-- title -->
<div class="layer_top">
   <div class="title"><spring:message code="LAB.M07.LAB00223" /></div>
   <a href="#" class="close">Close</a>
</div>
<!--// title -->   

<form id="frmAttrTypMapInsList" name="frmAttrTypMapInsList" method="post">			
	<input id="insAttrCd" name="insAttrCd" value="${attrTypMap.insAttrCd}" type="hidden">
	<input type="hidden" name = "menuNo" value="${selectedMenu.menuNo}"/>
	<input type="hidden" name = "selectMenuNo" value="${selectedMenu.selectMenuNo}"/>
	<input type="hidden" name = "selectMenuNm" value="${selectedMenu.selectMenuNm}"/>
	<input type="hidden" name = "topMenuNo" value="${selectedMenu.topMenuNo}"/>
	<input type="hidden" name = "topMenuNm" value="${selectedMenu.topMenuNm}"/>

<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00223"/></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 30%;">
			<col style="width: 70%;">
		</colgroup>
		<thead>
			<tr>
				<th><spring:message
						code="LAB.M07.LAB00222" />
				</th>
				<td><select name="searchAttrTyp2" id="searchAttrTyp2"
					class="w180">
						<option><spring:message
								code="LAB.M07.LAB00195" /></option>
						<c:forEach items="${listCommon}" var="listCommon"
							varStatus="status">
							<option value="${listCommon.commonCd}">${listCommon.commonCdNm}</option>
						</c:forEach>
				</select></td>
			</tr>
		</thead>
	</table>
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00053"/></h4>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width:30%;">
			<col style="width:70%;">
		</colgroup>
			 <thead> 
				   <tr>
						<th><spring:message code="LAB.M09.LAB00052" /> </th>
						<td>
						   <div class="date_box">
							   <div class="inp_date w135">
								  <input  type="text"  id="insActDt" name="insActDt" value="<fmt:formatDate value="${insActDt}" pattern="yyyyMMdd" />" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
								</div>
							</div>
						</td>
					</tr>
			</thead>
	</table>
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M07.LAB00214"/></h4>
		</div>
	</div>
<div id='gridDiv'>
<table id="attributePop" class="w100p"></table>
<!--<table id="attributePop" style="width: 100%"></table> -->
	<div id="pager3"></div>
</div>			
</div>
<div class="btn_box">
<a class="blue-btn" href="javascript:buttonEvent();" id="btn_update"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<!--�ݱ�-->
<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>


	
</form>