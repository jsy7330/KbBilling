<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">table.ui-datepicker-calendar { display:none; }</style>

<script type="text/javascript">


$(function(){
	tierMngGrid();
	tierMngDtlGrid();
	$('#btn_search').click(function() {
		tierMngGrid();	
	});	

	$('#btnPordCd').click(function() {
		var url="searchPopup.ajax";
		var param = new Object();
		openModal(url, param); 	
	});	

	$( ".date" ).datepicker();
	var date1=new Date();
	date1.setDate( date1.getDate() + 1 );
	reault_y = date1.getFullYear();
	reault_m = eval(date1.getMonth()+1);
	reault_d = date1.getDate();
	if(parseInt(reault_m)<10){reault_m="0"+reault_m;}
	if(parseInt(reault_d)<10){reault_d="0"+reault_d;}
	var date2 = null;	
	if(lng=='ko'){
		date2 = reault_y + "-" + reault_m ;	
	}else {
		date2 = reault_d + "-" +reault_y; 
	}
	$("#searchMon").val(date2);

	//달력처리
	if($(".month-picker").length > 0){
		if(lng == 'ko'){
			format = 'yy-mm';
		}else if (lng == 'en'){
			format = 'mm/yy';
		}
		 $('.month-picker').datepicker( {
		        changeMonth: true,
		        changeYear: true,
		        showButtonPanel: true,
		        dateFormat: format,
		        onClose: function(dateText, inst) {
		            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		            $(this).datepicker('setDate', new Date(year, month, 1));
		        }
		    }
		 );
	}



});

function openModal(url, param) {
	
	$.ajax({
		type : "post",
		url : url,
		data : param,
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

$(window).resize(function() {
	$("#tierMngListGrid").setGridWidth($("#tierMngListGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});
$(window).resize(function() {
	$("#tierMngDtlListGrid").setGridWidth($("#tierMngDtlListGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
});

function tierMngGrid() {

	var param = new Object();
	
	if ($("#searchProdCd").val().length > 0) {
		param.searchProdCd = $("#searchProdCd").val();
	}	
	
	if ($("#searchSoId").val().length > 0) {
		param.searchSoId = $("#searchSoId").val();
	}	
	
	if ($("#searchMon").val().length > 0) {
		param.searchMon = dateFormatToStringYYYYMM($('#searchMon').val());
		
	}

	$("#tierMngListGrid").jqGrid("GridUnload"); 
	$("#tierMngListGrid").jqGrid({
		url:'tierMngListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'TIER_SET_CD',name:'tierSetCd', hidden:true},	
			{label:'<spring:message code="LAB.M07.LAB00003"/>',name:'soNm', width:100, align:"left"},      
			{label:'<spring:message code="LAB.M07.LAB00130"/>',name:'prodNm', width:200, align:"left"},      
			{label:'<spring:message code="LAB.M08.LAB00206"/>',name:'rateItmNm', width:200, align:"left"}, 
			{label:'단가적용방법',name:'priceApplyNm', width:50, align:"left"},  	  			
			{label:'<spring:message code="LAB.M06.LAB00142"/>',name:'ver', width:50, align:"center"},  	
			{label:'<spring:message code="LAB.M09.LAB00251"/>',name:'actDt', width:80, align:"center"}  				
	   	],

	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#tierMngListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 495,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		onCellSelect : function(rowId, index, contents, event){
			setSelectedDate(rowId);
		},
	    loadComplete: function(obj){
	    	$("#tierMngListGrid").setGridWidth($("#tierMngListGridDiv").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#tierMngListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	$("#tierMngListGrid").setGridWidth($("#tierMngListGridDiv").width(),false);

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#tierMngListGrid").setGridWidth($('#tierMngListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
}

function tierMngDtlGrid(tierSetCd) {
	var param = new Object();
	if (tierSetCd != null) {
		param.tierSetCd = tierSetCd;
	}else{
		param.tierSetCd = "0";
	}

	$("#tierMngDtlListGrid").jqGrid("GridUnload"); 
	$("#tierMngDtlListGrid").jqGrid({
		url:'tierMngDtlListAction.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M07.LAB00359"/>',name:'tierSegmtNo', width:40, align:"center"}, 
			{label:'<spring:message code="LAB.M01.LAB00270"/>',name:'tierStrtVal', width:50, align:"center"}, 
			{label:'<spring:message code="LAB.M01.LAB00271"/>',name:'tierEndVal', width:50, align:"center"},			
			{label:'<spring:message code="LAB.M04.LAB00001"/>',name:'commonCdNm', width:100, align:"center"},			
			{label:'<spring:message code="LAB.M03.LAB00089"/>',name:'unitAppYn', width:60, align:"center"},
			{label:'<spring:message code="LAB.M08.LAB00207"/>',name:'rate', width:80, align:"right",formatter:stringTypeFormatComma},
			{label:'<spring:message code="LAB.M03.LAB00090"/>',name:'unitVal', width:80, align:"left"},
			//{label:'단위요율',name:'unitRate', width:80, align:"left"}

	   	],

	   	shrinkToFit:false,
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#tierMngDtlListGridPager',
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 495,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},

	    loadComplete: function(obj){
	    	$("#tierMngDtlListGrid").setGridWidth($("#tierMngDtlListGrid").width(),false);
	    },
		loadError: function (jqXHR, textStatus, errorThrown) {
			ajaxErrorCallback(jqXHR);
			
		},
		sortable: { update: function(permutation) {
			$("#tierMngDtlListGrid").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
			}
		}
	});
	$("#tierMngDtlListGrid").setGridWidth($("#tierMngDtlListGridDiv").width(),false);
	//그리드 화면 재조정
	$(window).resize(function() {
		$("#tierMngDtlListGrid").setGridWidth($('#tierMngDtlListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
}

//상세정보 
function setSelectedDate(rowId){		
	var data = $("#tierMngListGrid").getRowData(rowId);
	tierMngDtlGrid(data.tierSetCd) ;
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
				<!--SO_ID-->
				<th><spring:message code="LAB.M07.LAB00003"/></th>
				<td>
					<select id="searchSoId" name="searchSoId" class="w300">
						<option value=""><spring:message code="LAB.M09.LAB00063"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>					
					</select>
				</td>
				<!--적용월-->
				<th><spring:message code="LAB.M09.LAB00244"/></th>
				<td>
					<div class="date_box">
						<div class="inp_date w135">
							<input  type="text"  id="searchMon" name="searchMon" class="month-picker">
							<a href="#" id='btnCal1' class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<!--상품명, 상품코드-->
				<th><spring:message code="LAB.M07.LAB00130" /></th>
				<td colspan="3">
					<input id="searchProdNm" name="searchProdNm" type="text" class="w300" readonly>
					<input id="searchProdCd" name="searchProdCd" type="hidden" class="w200">
					<a id="btnPordCd" class="grey-btn" title='<spring:message code="LAB.M09.LAB00158" />' href="#"><span><spring:message code="LAB.M09.LAB00158" /></span></a>
				</td>
			</tr>
		</thead>
	</table> 


<!--JQ Grid 리스트-->	
<div class="lay_box2">
	<div class="lay_left2">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title">
		            	<spring:message code="LAB.M07.LAB00149" />
		            </h4>
			</div>
		</div>
		<div id='tierMngListGridDiv'>
			<table id="tierMngListGrid" class="w100p"></table>
			<div id="tierMngListGridPager"></div>
		</div>
	</div>

	<div class="lay_right2">
		<div class="main_btn_box">
			<div class="fl">
		            <h4 class="sub_title">
		            	<spring:message code="LAB.M01.LAB00269" />
		            </h4>
			</div>
		</div>
		<div id='tierMngDtlListGridDiv'>
			<table id="tierMngDtlListGrid" class="w100p"></table>
			<div id="tierMngDtlListGridPager"></div>
		</div>
	</div>
</div>

<!--버튼	
<div class="main_btn_box">
	<div class="fr">	
		<!--등록
		<ntels:auth auth="${menuAuthC}">
		<a class="grey-btn" href="#"  id="btn_insert" ><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>		
		<!--수정
		<ntels:auth auth="${menuAuthU}">
		<a class="grey-btn" href="#" id="btn_update" title='<spring:message code="LAB.M07.LAB00252"/>' ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>	
	</div>
  </div>-->
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:800px;" >
</div>