<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		initGridP1();
		initGridP2();
		initGridP3();
		initGridP4();
		
		
		//조회버튼
		$("#searchSeP").click(function() {
			
			var tpType = $("#tpType").val();
			if(tpType == "01"){
				
				$("#typeP1").show();
				$("#typeP2").show();
				$("#typeP3").hide();
				$("#typeP4").hide();
				
				searchDataP1();
			}else if(tpType == "02"){
				$("#typeP1").hide();
				$("#typeP2").hide();
				$("#typeP3").show();
				$("#typeP4").show();
				
				searchDataP3();
			}
		});
		
		
		$('#soIdSeP').selectmenu({});
		$('#itemTpCdSeP').selectmenu({});
		$('#tpType').selectmenu({});
		
		
		//제조사검색 팝업
		$("#btnSearchMncoSeP").click(function() {
			
			var url="/system/common/common/manufacturerMng/manufacturerSearchPopup.ajax";
			url = url + "?soId=" + $("#soIdSeP").val() + "&popType=o&returnId1=mncoIdSeP&returnId2=mncoNmSeP";
			
			var width = 510;
			var height = 430;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		//제품검색 팝업
		$("#btnSearchItemSeP").click(function() {
			
			var url="/system/common/common/productMng/productSearchPopup.ajax";
			url = url + "?soId=" + $("#soIdSeP").val() + "&popType=o&returnId1=itemIdSeP&returnId2=itemNmSeP";
			
			var width = 815;
			var height = 510;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
	});
	
	
	function initGridP1() {
		
		var param = new Object();
		
		$("#purchaseUnitPriceTableP1").jqGrid({
			
		   	url:'/distributor/logistics/referenceInfo/purchaseUnitPrice/purchaseUnitPriceListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},

	   		 	{ label: '<spring:message code="LAB.M09.LAB00098" />', name: 'mncoNm', width : 100},				//제조사
	   			{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},			//제품유형
	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			{ label: '<spring:message code="LAB.M14.LAB00073" />', name: 'eftStrtDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력시작일자
	   			{ label: '<spring:message code="LAB.M14.LAB00075" />', name: 'eftEndDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력종료일자
	   			{ label: '<spring:message code="LAB.M05.LAB00005" />', name: 'mncoOtptUtPrc', formatter: 'integer', align:'right', width : 100},	//매입단가
	   			{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'note', width : 100},		//비고
	   			
			    
			    
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#purchaseUnitPricePagerP1',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 140,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
				searchDataP2(rowId)
	        },
	        loadComplete: function(obj){
	        	$("#purchaseUnitPriceTableP1").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    sortable: { update: function(permutation) {
		    	$("#purchaseUnitPriceTableP1").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#purchaseUnitPriceTableP1").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}
	
	//그리드 재검색 
	function searchDataP1(){
		$("#purchaseUnitPriceTableP1").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#soIdSeP").val();
		param.mncoId = $("#mncoIdSeP").val();
		param.itemId = $("#itemIdSeP").val();
		param.itemTpCd = $("#itemTpCdSeP").val();
		
        $("#purchaseUnitPriceTableP1").setGridParam({ postData: param }).trigger("reloadGrid");
        
        $("#purchaseUnitPriceTableP2").clearGridData();  // 이전 데이터 삭제
        
	}
	
	function initGridP2() {
		
		var param = new Object();
		param.itemId = "0000000000000000000000";
		
		$("#purchaseUnitPriceTableP2").jqGrid({
			
		   	url:'/distributor/logistics/referenceInfo/purchaseUnitPrice/mncoUtPrcDtlListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
				{ label: 'prcDtlSeq' , name: 'prcDtlSeq', hidden:true,width : 0},
				
	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			{ label: '<spring:message code="LAB.M14.LAB00073" />', name: 'eftStrtDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력시작일자
	   			{ label: '<spring:message code="LAB.M14.LAB00075" />', name: 'eftEndDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력종료일자
	   			{ label: '<spring:message code="LAB.M05.LAB00005" />', name: 'mncoOtptUtPrc', formatter: 'integer', align:'right', width : 100},	//매입단가
	   			
	   			
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#purchaseUnitPricePagerP2',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 140,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        	//setSelectedDate(rowId);
	        },
	        loadComplete: function(obj){
	        	$("#purchaseUnitPriceTableP2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    sortable: { update: function(permutation) {
		    	$("#purchaseUnitPriceTableP2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#purchaseUnitPriceTableP2").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	//그리드 재검색 
	function searchDataP2(rowId){
		
		var data = $("#purchaseUnitPriceTableP1").getRowData(rowId);
		
		$("#purchaseUnitPriceTableP2").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		
		if(rowId =! 0){
			param.itemId = data.itemId;
		}else{
			param.itemId = "0000000000000000000000";
		}
		
        $("#purchaseUnitPriceTableP2").setGridParam({ postData: param }).trigger("reloadGrid");
	}
	
	
	function initGridP3() {
		
		var param = new Object();
		
		$("#distributionUnitPriceTableP3").jqGrid({
			
		   	url:'/distributor/logistics/referenceInfo/distributionUnitPrice/distributionUnitPriceListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'soId' , name: 'soId', hidden:true,width : 0},
				{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},

	   		 	{ label: '<spring:message code="LAB.M09.LAB00098" />', name: 'mncoNm', width : 100},				//제조사
	   			{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},			//제품유형
	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			{ label: '<spring:message code="LAB.M14.LAB00073" />', name: 'eftStrtDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력시작일자
	   			{ label: '<spring:message code="LAB.M14.LAB00075" />', name: 'eftEndDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력종료일자
	   			{ label: '<spring:message code="LAB.M08.LAB00095" />', name: 'dstrbUtPrc', formatter: 'integer', align:'right', width : 100},	//유통단가
	   			{ label: '<spring:message code="LAB.M06.LAB00093" />', name: 'note', width : 100},		//비고
	   			
			    
			    
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#distributionUnitPricePagerP3',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 140,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
				searchDataP4(rowId)
	        },
	        loadComplete: function(obj){
	        	$("#distributionUnitPriceTableP3").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#distributionUnitPriceTableP3").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		    
		});
		
		$("#distributionUnitPriceTableP3").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	//그리드 재검색 
	function searchDataP3(){
		
		$("#distributionUnitPriceTableP3").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#soIdSeP").val();
		param.mncoId = $("#mncoIdSeP").val();
		param.itemId = $("#itemIdSeP").val();
		param.itemTpCd = $("#itemTpCdSeP").val();
		
        $("#distributionUnitPriceTableP3").setGridParam({ postData: param }).trigger("reloadGrid");
        
        $("#distributionUnitPriceTableP4").clearGridData();  // 이전 데이터 삭제
        
	}
	
	function initGridP4() {
		
		var param = new Object();
		param.itemId = "0000000000000000000000";
		
		$("#distributionUnitPriceTableP4").jqGrid({
			
		   	url:'/distributor/logistics/referenceInfo/distributionUnitPrice/dstrbUtPrcDtlListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel: [ 
		   	           
				{ label: 'prcDtlSeq' , name: 'prcDtlSeq', hidden:true,width : 0},
				
	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			{ label: '<spring:message code="LAB.M14.LAB00073" />', name: 'eftStrtDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력시작일자
	   			{ label: '<spring:message code="LAB.M14.LAB00075" />', name: 'eftEndDt', formatter: stringTypeFormatterYYYYMMDD, align:'center', width : 100},		//효력종료일자
	   			{ label: '<spring:message code="LAB.M08.LAB00095" />', name: 'dstrbUtPrc', formatter: 'integer', align:'right', width : 100},	//유통단가
	   			
	   			
	   		],
		   		
		   	rowNum:10,					//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#distributionUnitPricePagerP4',
			sortable : true,			//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 140,				//화면 상태에 따라 크기 조절 할 것
			onCellSelect : function(rowId, index, contents, event){
	        	//setSelectedDate(rowId);
	        },
	        loadComplete: function(obj){
	        	$("#distributionUnitPriceTableP4").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		    },
		    
		    sortable: { update: function(permutation) {
		    	$("#distributionUnitPriceTableP4").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    		}
	    	}
		});
		
		$("#distributionUnitPriceTableP4").setGridWidth(980,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	//그리드 재검색 
	function searchDataP4(rowId){
		
		var data = $("#distributionUnitPriceTableP3").getRowData(rowId);
		
		$("#distributionUnitPriceTableP4").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		
		if(rowId =! 0){
			param.itemId = data.itemId;
		}else{
			param.itemId = "0000000000000000000000";
		}
		
        $("#distributionUnitPriceTableP4").setGridParam({ postData: param }).trigger("reloadGrid");
        
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M03.LAB00010"/><!-- 단가변경이력조회 --></div>
	<a href="#" id="btnColse1SeP" class="close">Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M03.LAB00010"/><!-- 단가변경이력조회 --></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="searchSeP" name="searchSeP" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</div>            
	                
	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M07.LAB00003"/><!-- 사업 --></th>
				<td>
				
					<select class="w200" id="soIdSeP" name="soIdSeP">
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
				<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --></th>
				<td>
					<div class="inp_date w190">
						<input type="text" id="mncoNmSeP" name="mncoNmSeP" class="w150" disabled="disabled" />
						<input type="hidden" id="mncoIdSeP" name="mncoIdSeP" />
                        <a href="#" id="btnSearchMncoSeP" name="btnSearchMncoSeP" class="search">search</a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
				<td>
					<select id="itemTpCdSeP" name="itemTpCdSeP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
				        <c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
							<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
				<th><spring:message code="LAB.M09.LAB00111"/><!-- 제품명 --></th>
				<td>
					<div class="inp_date w190">
						<input type="text" id="itemNmSeP" name="itemNmSeP" class="w150" disabled="disabled" />
						<input type="hidden" id="itemIdSeP" name="itemIdSeP" />
                        <a href="#" id="btnSearchItemSeP" name="btnSearchItemSeP" class="search">search</a>
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M03.LAB00007"/><!-- 단가구분 --></th>
				<td colspan="3">
					<select id="tpType" name="tpType" class="w200">
						<option value="01"><spring:message code="LAB.M05.LAB00005"/></option>
						<option value="02"><spring:message code="LAB.M08.LAB00095"/></option>
					</select>                                            
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00112"/><!-- 제품목록 --></h4>
		</div>
	</div>
	
	<div class="layer_box" id="typeP1" name="typeP1" >
		<table id="purchaseUnitPriceTableP1" class="w100p"></table>
		<div id="purchaseUnitPricePagerP1"></div>
	</div>
	
	<div class="layer_box" id="typeP3" name="typeP3" style="display:none;">
		<table id="distributionUnitPriceTableP3" class="w100p"></table>
		<div id="distributionUnitPricePagerP3"></div>
	</div>


	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M03.LAB00009"/><!-- 단가변경이력 --></h4>
		</div>
	</div>
	
	<div class="layer_box" id="typeP2" name="typeP2">
		<table id="purchaseUnitPriceTableP2" class="w100p"></table>
		<div id="purchaseUnitPricePagerP2"></div>
	</div>

	<div class="layer_box" id="typeP4" name="typeP4" style="display:none;">
		<table id="distributionUnitPriceTableP4" class="w100p"></table>
		<div id="distributionUnitPricePagerP4"></div>
	</div>
	
</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
	
	