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

		if($(".datepicker").length > 0){
			$( ".datepicker" ).datepicker({
				  changeMonth: true,
				  changeYear: true,
				  regional:lng
				}).datepicker("setDate", "-30"); //시스템 날짜 출력
		}
		$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
		$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
		

		if($(".datepicker1").length > 0){
			$( ".datepicker1" ).datepicker({
				  changeMonth: true,
				  changeYear: true,
				  regional:lng
				}).datepicker("setDate", "0"); //시스템 날짜 출력
		}
		$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
		$( ".datepicker1.disabled" ).datepicker( "option", "disabled", true );

		//조회
		$("#btn_search").click(function() {
			$("#page").val("1");
			if($("#searchSoId").val() == "") {
				alert("사업을 선택하세요.");
				return;
			}
			getorderStatisticesList();
		});

		 // 고객조회
		$('#btnCustSearch').on('click',function (e) {
				if($("#btnCustSearch").hasClass('not-active')){
					return;
				}
				openCustSearchPopup();	
				//openPymSearchPopup();
			}
		);

		//셀렉트 박스 체인지 이벤트
		$('#searchSoId').selectmenu({
		    change: function() {
		    	searchData();
		    }
		});
		
		//키이벤트
		$( "#searchText" ).keypress(function(event) {
	   		if(event.keyCode == 13){
	   			searchData();
	   		}
		});

		var vurl= "/customer/statistics/orderStatistics/orderStatisticsChart.json";		
		$.post(vurl,"",function(vData){
			var orderStatisticsChart = vData.orderStatisticsChart; 
			makeChart2(orderStatisticsChart);
		});
	
	});
/*
 * 고객조회팝업
 */
function openCustSearchPopup(){
	
	$.ajax({
		type : "post",
		url : '/system/common/common/customerSearch/customerSearchPopup.ajax',
		data : {
			 inputSoId : $('#condCustSoId').val()   //input SO Id
			,inputCustNm : $('#condCustNm').val()   //input Customer Name
			,inputIsUnmaskYn : $('#isUnmaskYn').val() //마스크 처리 해제 Y
			,outputSoId : 'condCustSoId'            //output SO ID Select
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

function initGrid(lng) {
	
	$("#orderTable").jqGrid({

		url : 'orderStatisticsListAction.json?',
		mtype : "POST",
		datatype : "json",
		postData : {},
		jsonReader: {
			repeatitems : true,
			root : "orderStatisticsList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		colModel : [ 
		            {label: '<spring:message code="LAB.M07.LAB00003" />',name : 'soNm',width : 50,key: true},
		 		    {label: '<spring:message code="LAB.M01.LAB00051" />',name : 'custId',width : 50},
		 		    {label: '<spring:message code="LAB.M01.LAB00050" />',name : 'custNm',width : 50},
		 		    {label: '<spring:message code="LAB.M08.LAB00033" />',name : 'orderId',width : 50},
		 		    {label: '<spring:message code="LAB.M08.LAB00035" />',name : 'orderStatNm',width : 50},
		 		    {label: '<spring:message code="LAB.M08.LAB00036" />',name : 'orderTpNm',width : 50},
		 		    {label: '<spring:message code="LAB.M07.LAB00186" />',name : 'svcTelNo',width : 50},
		 		    {label: '<spring:message code="LAB.M01.LAB00035" />',name : 'ctrtId',width : 50},
					{label: '<spring:message code="LAB.M08.LAB00037" />',name : 'openDd',width : 50,align:"center",formatter: stringTypeFormatterYYYYMMDD},
		 		    {label: '<spring:message code="LAB.M07.LAB00130" />',name : 'prodNm',width : 80},
		 		    {label: '<spring:message code="LAB.M01.LAB00037" />',name : 'ctrtStatNm',width : 50},
		 		    {label: '<spring:message code="LAB.M10.LAB00028" />',name : 'orgNm',width : 80},
		 		    {label: '<spring:message code="LAB.M10.LAB00024" />',name : 'usrNm',width : 80}
		],
		rowNum:25,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#jqGridPager',
		autowidth:true,
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		sortorder: "desc",
	    viewrecords: true,	
		height: 150,					//화면 상태에 따라 크기 조절 할 것
		ondblClickRow : function(rowid) { //ROW 클릭시 이벤트
			goUpdatePage(rowid);
		},
        loadComplete : function () {
        	$("#orderTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
        	$("#orderTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
    		}
    	}
	});
	$("#orderTable").setGridWidth($('#gridDiv').width(),false);
	
	$(window).resize(function() {
		$("#orderTable").setGridWidth($('#gridDiv').width(),false);//그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
}
	function makeChart2(dataarray){
		if( dataarray === 'undefined' || dataarray == null || dataarray.length ==0  ){
			dataarray = [{'orderTpNm':0, 'statCntTotal': 0, 'statCnt01': 0, 'statCnt02': 0, 'statCnt03': 0, 'statCnt04': 0}];
		}

		var chart = AmCharts.makeChart("chartdiv2", {
		"type": "serial",
		"theme": "light", 
		"dataProvider": dataarray,
		"valueAxes": [
			{
				"id": "ValueAxis-1",
				"position": "top",
				"axisAlpha": 0
			}
		],
		"graphs": [{
			"balloonText": "[[category]]: <spring:message code="LAB.M09.LAB00063" /><b>[[value]]</b>",
			"fillAlphas": 1,
			"fillColors": "#FF0F00",
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "statCntTotal",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M09.LAB00070" /><b>[[value]]</b>",
			"fillColors": "#F8FF01",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "statCnt01",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M10.LAB00018" /><b>[[value]]</b>",
			"fillColors": "#04D215",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "statCnt02",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M08.LAB00013" /><b>[[value]]</b>",
			"fillColors": "#0D8ECF",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "statCnt03",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M08.LAB00044" />[[value]]</b>",
			"fillColors": "#8A0CCF",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "statCnt04",
			"columnWidth": 0.5
		}],
		"categoryField": "orderTpNm",
		 "export": {
    		"enabled": true
		}
		});
	}
/**
 * 상담Tab조회
 */
function getorderStatisticesList(){


	var searchStatDt = $("#searchStatDt").val();
	var searchEndDt = $("#searchEndDt").val();
	var orderStat = $("#orderStat").val();
	var condCustNm = $("#condCustNm").val();
	var condCustId = $("#condCustId").val();
	var orderTp = $("#orderTp").val();
	var searchSoId = $("#searchSoId").val();
	searchStatDt = dateFormatToStringYYYYMMDD(searchStatDt);
	searchEndDt = dateFormatToStringYYYYMMDD(searchEndDt);




	$("#orderTable").setGridParam({
		sortable : true,
		url : 'orderStatisticsListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			searchStatDt : searchStatDt,
  			searchEndDt : searchEndDt,
  			orderStat : orderStat,
  			condCustId : condCustId,
  			orderTp : orderTp,
  			searchSoId : searchSoId
		}
	});
	      	
   	$("#orderTable").trigger("reloadGrid");



	var vurl= "/customer/statistics/orderStatistics/orderStatisticsChart.json";	
	var params="searchStatDt="+searchStatDt+"searchEndDt="+searchEndDt+"searchSoId="+searchSoId;  

	$.ajax({      
		type:"POST",  
		url:vurl,      
		data:params,      
		success:function(vData){   
			var orderStatisticsChart = vData.orderStatisticsChart; 
			makeChart2(orderStatisticsChart);   
		},   
		error:function(e){  
				alert(e.responseText);  
		}  
	});


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
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='btn_search' href="#"class="grey-btn" title='<spring:message code="LAB.M01.LAB00047"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</ntels:auth>
</div>


<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:20%;">
		<col style="width:10%;">
		<col style="width:30%;">
		<col style="width:10%;">
		<col style="width:20%;">
	</colgroup>
	<thead> 
		<tr>
			<th title="latest" ><spring:message code="LAB.M07.LAB00003" /></th>
			<td>
				<select class="w150" id="searchSoId" name="searchSoId">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M01.LAB00196" /></th>
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
			<th><spring:message code="LAB.M01.LAB00045" /></th>
			<td>
				<div class="inp_date w220">
					<input id="condCustNm" type="text" class="w200" />
					<input id="condCustId" type="text" hidden >

					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch" href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
		<tr>
			<th title="latest" ><spring:message code="LAB.M08.LAB00035" /></th>
			<td>
				<select id="orderStat" name="orderStat" class="w150">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${orderStatList}" var="orderStatList" varStatus="status">
					<option value="${orderStatList.commonCd}">${orderStatList.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M08.LAB00036" /></th>
			<td>
				<select id="orderTp" name="orderTp" class="w150">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${orderTpList}" var="orderTpList" varStatus="status">
					<option value="${orderTpList.commonCd}">${orderTpList.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>

		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<h4 class="sub_title"><spring:message code="LAB.M08.LAB00039" /></h4>
</div>
<table class="writelist">
   <tbody> 
	   <tr>
			<td>
				<div class="sgra">												
					<img src='/images/common/red.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M09.LAB00063" />&nbsp;&nbsp;
					<img src='/images/common/yellow.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M09.LAB00070" />&nbsp;&nbsp;
					<img src='/images/common/green.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M10.LAB00018" />&nbsp;&nbsp;
					<img src='/images/common/blue.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M08.LAB00013" />&nbsp;&nbsp;
					<img src='/images/common/puple.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M08.LAB00044" />&nbsp;
					<div id="chartdiv2"  class="w100p" style="height: 150px;"></div>
				</div>                                            
			</td>
	   </tr>
	</tbody>
</table>

<div class="main_btn_box">
	<h4 class="sub_title"><spring:message code="LAB.M08.LAB00034" /></h4>
</div>
	<div id='gridDiv' class="w100p">
		<table id="orderTable" class="w100p"></table>
		<div id="jqGridPager"></div>
	</div>
</div>
<!-- 팝업참조 -->
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

