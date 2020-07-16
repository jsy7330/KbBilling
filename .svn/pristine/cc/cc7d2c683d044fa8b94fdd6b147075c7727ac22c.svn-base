<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<!-- amchart -->
<script src="/scripts/amcharts_2.4.2/amcharts.js" type="text/javascript"></script>
<script src="/scripts/amcharts_2.4.2/serial.js" type="text/javascript"></script>

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

		var url= "/system/main/customerMainChart1.json";		
		$.post(url,"",function(rtnData){
			var customerMainChart1 = rtnData.customerMainChart1; 
			makeChart(customerMainChart1);
		});
		
		var vurl= "/system/main/customerMainChart2.json";		
		$.post(vurl,"",function(vData){
			var customerMainChart2 = vData.customerMainChart2; 
			makeChart2(customerMainChart2);
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
			dataarray = [{'mM':0, 'tCnt': 0, 'aCnt': 0, 'dCnt': 0}];
		}

		var chart = AmCharts.makeChart("chartdiv", {
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
			"balloonText": "[[category]]: <spring:message code="LAB.M09.LAB00236"/><b>[[value]]</b>",
			"fillAlphas": 1,
			"fillColors": "#FF0F00",
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "tCnt",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M07.LAB00321"/><b>[[value]]</b>",
			"fillColors": "#F8FF01",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "aCnt",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M14.LAB00085"/><b>[[value]]</b>",
			"fillColors": "#04D215",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "dCnt",
			"columnWidth": 0.5
		}],
		"categoryField": "mM",
		 "export": {
    		"enabled": true
		}
		});
	}
	
	function makeChart2(dataarray){
		if( dataarray === 'undefined' || dataarray == null || dataarray.length ==0  ){
			dataarray = [{'mM':0, 'tCnt': 0, 'bCnt': 0, 'vCnt': 0, 'eCnt': 0, 'pCnt': 0}];
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
			"balloonText": "[[category]]: <spring:message code="LAB.M09.LAB00234"/><b>[[value]]</b>",
			"fillAlphas": 1,
			"fillColors": "#FF0F00",
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "tCnt",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M01.LAB00248"/><b>[[value]]</b>",
			"fillColors": "#F8FF01",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "bCnt",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M06.LAB00131"/><b>[[value]]</b>",
			"fillColors": "#04D215",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "vCnt",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M09.LAB00235"/><b>[[value]]</b>",
			"fillColors": "#0D8ECF",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "eCnt",
			"columnWidth": 0.5
		},{
			"balloonText": "[[category]]: <spring:message code="LAB.M13.LAB00040"/>[[value]]</b>",
			"fillColors": "#8A0CCF",
			"fillAlphas": 0.8,
			"labelText": "[[value]]",
			"lineAlpha": 0.2,
			"type": "column",
			"valueField": "pCnt",
			"columnWidth": 0.5
		}],
		"categoryField": "mM",
		 "export": {
    		"enabled": true
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
<div class="h3_bg">
	<h3><spring:message code="LAB.M12.LAB00010"/></h3>
</div>

<table class="writelist mt10">
        <colgroup>
            <col style="width:49%;">
            <col style="width:1%;">
            <col style="width:49%;">
        </colgroup>
   <tbody> 
           <tr>
     		<td>
                 <p class="txt_title"><spring:message code="LAB.M01.LAB00247"/></p>
                <div class="sgra">
					<img src='/images/common/red.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M09.LAB00236"/>&nbsp;&nbsp;
					<img src='/images/common/yellow.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M07.LAB00321"/>&nbsp;&nbsp;
					<img src='/images/common/green.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M14.LAB00085"/>&nbsp;&nbsp;
					<div id="chartdiv"  style="height: 250px; width: 100%"></div>
                </div>
            </td>
			<td></td>
     		<td>
                 <p class="txt_title"><spring:message code="LAB.M07.LAB00316"/></p>

                <div class="sgra">												
					<img src='/images/common/red.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M09.LAB00063"/>&nbsp;&nbsp;
					<img src='/images/common/yellow.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M01.LAB00210"/>&nbsp;&nbsp;
					<img src='/images/common/green.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M06.LAB00083"/>&nbsp;&nbsp;
					<img src='/images/common/blue.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M09.LAB00042"/>&nbsp;&nbsp;
					<img src='/images/common/puple.gif'width="20" height="15" valign="middle">&nbsp;<spring:message code="LAB.M13.LAB00039"/>&nbsp;
					
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