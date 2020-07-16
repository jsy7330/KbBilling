<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<!-- amchart -->
<script src="/scripts/amcharts_2.4.2/amcharts.js" type="text/javascript"></script>
<script src="/scripts/amcharts_2.4.2/serial.js" type="text/javascript"></script>
<tiles:insertAttribute name="js" ignore="true" />
<link href="/styles/nccbs/styles.css" rel="stylesheet" type="text/css" />
<link href="/styles/jqgrid/ui.jqgrid.css" rel="stylesheet" type="text/css" />
<link href="/styles/jqgrid/searchFilter.css" rel="stylesheet" type="text/css" />
<link href="/styles/jqgrid/ui.multiselect.css" rel="stylesheet" type="text/css" />
<link href="/styles/tree/ui.dynatree.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(document).ready(function() {
		var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
		
		$("#srchTyp").val($("#tmp").val());
		
		initGrid(lng); //jqgrid 호출
		
		$("#btn_add").click(function() {
			goInsertPage();
		});
		$("#btn_search").click(function() {
			$("#page").val("1");
			goSearch();
		});

		var url= "/system/main/billingMainChart1.json";		
		$.post(url,"",function(rtnData){
			var billingMainChart1 = rtnData.billingMainChart1; 
			makeChart(billingMainChart1);
		});
		
		var vurl= "/system/main/billingMainChart2.json";		
		$.post(vurl,"",function(vData){
			var billingMainChart2 = vData.billingMainChart2; 
			makeChart2(billingMainChart2);
		});

	});

function goUpdatePage(rowid) {
	 
	 $('#popup_dialog').css('height', '460px');
	 var data = $("#attributeTable").getRowData(rowid)
	 var url="/system/main/bulletinDetailPopup.ajax";
    	var param = new Object();
    	param.popType = "m";			//팝업타입 m:모달 o:일반
    	param.noticeId = data.noticeId;
    	param.effectStartDt = data.effectStartDt;
    	param.effectEndDt = data.effectEndDt;
    	
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


	function makeChart(dataarray){

		if( dataarray === 'undefined' || dataarray == null || dataarray.length ==0  ){
			dataarray = [{'billYymm':0, 'billAmt': 0}];
		}

		var chart = AmCharts.makeChart("chartdiv", {
			"type"  : "serial",
			"theme" : "light",
	        "dataProvider": dataarray,
	        "valueAxes": [{
				"gridColor":"#FFFFFF",
				"stackType": "regular",
	            "gridAlpha": 0,
	            "dashLength": 0
	        }],
			"gridAboveGraphs": true,
	        "startDuration": 1,
	        "graphs": [{
				"balloonText": "[[category]]: 청구현황<b>[[value]]</b>",
	            "fillAlphas": 1,
	            "fillColors": "#fd8331",
	            "labelText": "[[value]]",
	            "lineAlpha": 0.2,
	            "type": "column",
	            "valueField": "billAmt",
	            "columnWidth": 0.5
			}],
	        "chartCursor": {
	        	"categoryBalloonEnabled": false,
	            "cursorAlpha": 0,
	            "zoomable": false
				},
			"categoryField": "billYymm",
	        "categoryAxis": {
	        	"gridPosition": "start",
	            "gridAlpha": 0,
	            "tickPosition":"start",
	            "tickLength":20
			},
	        "exportConfig":{
	        	"menuTop": 0,
	           	"menuItems": [{
	            	"icon": '/lib/3/images/export.png',
	            	"format": 'png'     
	        	}]  
	        }
		});
	}
	
	function makeChart2(dataarray){
		if( dataarray === 'undefined' || dataarray == null || dataarray.length ==0  ){
			dataarray = [{'payYymm':0, 'rcptAmt': 0}];
		}

		var chart = AmCharts.makeChart("chartdiv2", {
			"type"  : "serial",
			"theme" : "light",
	        "dataProvider": dataarray,
	        "valueAxes": [{
				"gridColor":"#FFFFFF",
				"stackType": "regular",
	            "gridAlpha": 0,
	            "dashLength": 0
	        }],
			"gridAboveGraphs": true,
	        "startDuration": 1,
	        "graphs": [{
				"balloonText": "[[category]]: 수납금액<b>[[value]]</b>",
	            "fillAlphas": 1,
	            "fillColors": "#fd8331",
	            "labelText": "[[value]]",
	            "lineAlpha": 0.2,
	            "type": "column",
	            "valueField": "rcptAmt",
	            "columnWidth": 0.5
			}],
	        "chartCursor": {
	        	"categoryBalloonEnabled": false,
	            "cursorAlpha": 0,
	            "zoomable": false
				},
			"categoryField": "payYymm",
	        "categoryAxis": {
	        	"gridPosition": "start",
	            "gridAlpha": 0,
	            "tickPosition":"start",
	            "tickLength":20
			},
	        "exportConfig":{
	        	"menuTop": 0,
	           	"menuItems": [{
	            	"icon": '/lib/3/images/export.png',
	            	"format": 'png'     
	        	}]  
	        }
		});
		
	}
function initGrid(lng) {
	
	$("#attributeTable").jqGrid({

		url : 'mainAction.json?',
		mtype : "POST",
		datatype : "json",
		postData : {},
		jsonReader: {
			repeatitems : true,
			root : "bulletinList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		colModel : [ 
		            {label: '<spring:message code="LAB.M06.LAB00050"/>',name : 'noticeId',width : 20,key: true},
		 		    {label: '<spring:message code="LAB.M09.LAB00092"/>',name : 'title',width : 110},
					{label: '<spring:message code="LAB.M01.LAB00097"/>',name : 'effectStartDt',width : 30,align:"center",formatter: stringTypeFormatterYYYYMMDD},
					{label: '<spring:message code="LAB.M01.LAB00098"/>',name : 'effectEndDt',width : 30,align:"center",formatter: stringTypeFormatterYYYYMMDD}, 
					{label: '<spring:message code="LAB.M09.LAB00161"/>',name : 'viewCnt',width : 15,align:"center"}
		],
		rowNum:25,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#jqGridPager',
		autowidth:true,
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		sortorder: "desc",
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		ondblClickRow : function(rowid) { //ROW 클릭시 이벤트
			goUpdatePage(rowid);
		},
        loadComplete : function () {
        	$("#attributeTable").setGridWidth($('#gridDiv').width(),false);
        },
    	sortable: { update: function(permutation) {
    		$("#attributeTable").setGridWidth($('#gridDiv').width(),false);
    		}
    	}
	});
	$("#attributeTable").setGridWidth($('#gridDiv').width(),false);
	
	$(window).resize(function() {
		$("#attributeTable").setGridWidth($('#gridDiv').width(),false);//그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
}
</script>
				<input id="noticeId" name="noticeId" type="hidden"/> 
				<input id="tmp" name="tmp" type="hidden" value="${bulletin.srchTyp}"/> 
					<div style="color:#000; font-weight:bold;font-size:18px;margin:30px 0 10px 10px;">통계사항 </div>
                               <table class="writelist">
                                        <colgroup>
                                            <col style="width:49%;">
                                            <col style="width:1%;">
                                            <col style="width:49%;">
                                        </colgroup>
                                   <tbody> 
 		                               <tr>
                                     		<td>
                                                 <p class="txt_title">청구현황(3개월)</ p>
                                                <div class="sgra">
													<div id="chartdiv"  style="height: 250px; width: 100%"></div>
                                                </div>
                                            </td>
											<td></td>
                                     		<td>
                                                 <p class="txt_title">수납현황(3개월)</ p>
                                                <div class="sgra">
													<div id="chartdiv2"  style="height: 250px; width: 100%"></div>
                                                </div>                                            
                                            </td>
                                       </tr>
	                                </tbody>
                                </table>

				 <div class="main_btn_box">
					<h4 class="sub_title"><spring:message code="LAB.M01.LAB00092"/></h4>
				</div>
				<div id='gridDiv' class="w100p">
					<table id="attributeTable" class="w100p"></table>
					<div id="jqGridPager"></div>
				</div>
              <!--// right -->
<!-- 팝업참조 -->
<!-- <div id="popup_dialog" class="Layer_wrap" style="display:none;width:950px;height: 400px;" > -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;width:950px;height: 450px;" >
</div>