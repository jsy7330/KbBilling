<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
$(function(){
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
	
	$("#procGbNm, #procGbNmEdit").addClass('not-active'); 
	$("#procGbNm, #procGbNmEdit").attr('disabled', true);		
	
	$("select").selectmenu()
	.selectmenu( "option", "width", "100%" );	
	
	transOperList();
	
 	$('#btn_search').click(function() {
 		$("#procGbNm").val("");
 		$("#procGbNmEdit").val("");
 		transOperList();	
	});	
	 
 	$('#btnTransOper').click(function() {
		var returnCon = confirm('<spring:message code="MSG.M09.MSG00008" />');
		
		if(returnCon){
 		
			var rowId = $("#transOperListGrid").getGridParam("selrow");
			
			if (rowId == null){
				alert('<spring:message code="MSG.M07.MSG00112" />');
				return;
			}	
			
/* 			var dvlpStatus = $("#transOperListGrid").getRowData(rowId).dvlpStatus;
			if (dvlpStatus != 3) {
				alert('<spring:message code="MSG.M07.MSG00111" />');
				return;
			} */
			
			var param = new Object();
			param.prodCd = $("#transOperListGrid").getRowData(rowId).prodCd;
			param.dvlpTyp = $("#transOperListGrid").getRowData(rowId).dvlpTyp;			
			param.modTyp = $("#transOperListGrid").getRowData(rowId).modTyp;	
			param.prodDvlpDttm = $("#transOperListGrid").getRowData(rowId).prodDvlpDttm;
			param.transferApplyDt = dateFormatToStringYYYYMMDD($('#actDt').val());
			param.soId = $("#transOperListGrid").getRowData(rowId).soId;
		
			if(param != false){
				
				$.ajax({
					url : 'transfer.json',
					type : 'POST',
					async: false,
					data : param,
					success : function(data) {
						
						if(data.result != "-1"){
							alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
							
							$("#transOperListGrid").trigger("reloadGrid");
						} 
					},
				    error: function(request,status,error){
				    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    },
					complete : function() {
					}
				});
			}
			
		}
		
 	});
	
});

$(window).resize(function() {
	$("#transOperListGrid").setGridWidth($("#transOperListGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});

function transOperList() {
	var param = new Object();
	if ($("#soId").val() != "") {
		param.soId = $("#soId").val();
	} 	
	
	if ($("#dvlpStatus").val() != "") {
		param.dvlpStatus = $("#dvlpStatus").val();
	} 
	
 	$("#transOperListGrid").jqGrid("GridUnload"); 
  	$("#transOperListGrid").jqGrid({
		url:'getTransOperList.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{name:'dvlpStatus', hidden:true},
			{name:'prodCd', hidden:true},
			{name:'dvlpTyp', hidden:true},
			{name:'modTyp', hidden:true},
			{name:'prodDvlpDttm', hidden:true},
			{name:'soId', hidden:true},
			{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100, align:"center"},       
			{label:'<spring:message code="LAB.M07.LAB00130" />',name:'prodNm', width:150}, 
			{label:'<spring:message code="LAB.M07.LAB00271" />',name:'confReqDttm', width:200, align:"center", formatter:stringTypeFormatterYYYYMMDDHH24MISS},			
			{label:'<spring:message code="LAB.M01.LAB00014" />',name:'dvlpTypNm', width:150, align:"center"}, 			
			{label:'<spring:message code="LAB.M09.LAB00009" />',name:'modTypNm', width:100, align:"center"}, 
			{label:'<spring:message code="LAB.M08.LAB00078" />',name:'confReqCnt', width:80, align:"center"},			
			{label:'<spring:message code="LAB.M05.LAB00050" />',name:'confrNoneCnt', width:80, align:"center"},	
			{label:'<spring:message code="LAB.M07.LAB00260" />',name:'confrOkCnt', width:80, align:"center"},				
			{label:'<spring:message code="LAB.M06.LAB00018" />',name:'confrReturnCnt', width:80, align:"center"}
	   	],
	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#transOperListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 485,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		 onCellSelect: function(rowid,icol,cellcontent,e){
			 $("#procGbNm").val($(this).jqGrid('getCell', rowid, 'dvlpTypNm' ));
			 $("#procGbNmEdit").val($(this).jqGrid('getCell', rowid, 'modTypNm' ));
		},
	    loadComplete: function(obj){
	    	$("#transOperListGrid").setGridWidth($("#transOperListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#transOperListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}	
	});   
	$("#transOperListGrid").setGridWidth($("#transOperListGridDiv").width(),false);
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

	<div class="main_btn_box">
			<div class="fl">
				<!-- <h4 class="sub_title"><spring:message code="LAB.M08.LAB00053"/></h4>-->
			</div>
			<div class="fr mt10">
				<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
				<th><spring:message code="LAB.M07.LAB00003"/></th>
				<td>
					<select id="soId" name="soId" class="w300">
						<option value=""><spring:message code="LAB.M09.LAB00063"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>					
					</select>
				</td>
				<th><spring:message code="LAB.M01.LAB00013"/></th>
				<td>
					<select id="dvlpStatus" name="dvlpStatus" class="w300">
						<option value=""><spring:message code="LAB.M09.LAB00063"/></option>
						<c:forEach items="${dvlpStatus}" var="dvlpStatus" varStatus="status">
							<option value="${dvlpStatus.commonCd}">${dvlpStatus.commonCdNm}</option>
						</c:forEach>					
					</select>
				</td>
			</tr>			
		</thead>
	</table> 
</form>	
<div class="main_btn_box">
	
</div>



<div id="transOperListGridDiv">
	<table id="transOperListGrid" class="w100p"></table>
	<div id="transOperListGridPager"></div>
</div>
<div class="main_btn_box">
	
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
				<th><spring:message code="LAB.M01.LAB00012"/></th>
	            <td>
	            	<input type="text" id="procGbNm" name="modRateItmFctrInitFctrNo" class="w150">&nbsp;
	            	<input type="text" id="procGbNmEdit" name="modRateItmFctrInitFctrNo" class="w150">                                       
				</td>
				<th><spring:message code="LAB.M09.LAB00056"/></th>
				<td>
					<div class="date_box">
						<div class="inp_date w150">
				  <input  type="text"  id="actDt" name="actDt" value="<fmt:formatDate value="<%= new java.util.Date()%>" pattern="${dateToStrFormat4}"/>" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
						</div>
						&nbsp;
						<a id="btnTransOper" class="grey-btn" title='<spring:message code="LAB.M08.LAB00089"/>' href="#"><span><spring:message code="LAB.M08.LAB00089"/></span></a>													
					</div>
				</td>
			</tr>			
		</thead>
	</table> 
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:400px;" >
</div>