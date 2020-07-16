<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
.ui-jqgrid tr.jqgfirstrow td {padding: 0 2px 0 2px;border-right-width: 1px; border-right-style: solid;} 
  	 
</style>
<script type="text/javascript">
$(document).ready(function() {

	$("#productRelationshipListProdRelTyp").selectmenu()
	.selectmenu( "option", "width", "100%" );

	initGrid();
  	
	$("#productRelationshipListProdRelTyp").selectmenu({ //차후
		change: function( event, ui ) {
			if ($(this).val() != '') {
				var param = new Object();
				
				param.soId = $("#soId").val();
				param.prodCase = $("#productRelProdCase").val();
				param.prodRelTyp = $(this).val();
				param.basicProdFl = $("#productRelBasicProdFl").val();
				param.prodCd = $("#productRelProdCd").val();
				
				$("#productdefultRelationsListGrid").jqGrid("GridUnload"); 
			  	$("#productdefultRelationsListGrid").jqGrid({
					url:'getProductRelationshipListAll_in.json?',
				    mtype:"POST",
				   	datatype: "json",
				    cellsubmit: 'clientArray',				   	
				   	postData : param,
				   	colModel:[
						{label:'<spring:message code="LAB.M07.LAB00146" />',name:'prodTypNm', width:80, align:"center"},      
						{label:'<spring:message code="LAB.M07.LAB00156" />',name:'prodCd', width:100, align:"center"}, 
						{label:'<spring:message code="LAB.M07.LAB00127" />',name:'prodGrpNm', width:100, align:"center"},      			
						{label:'<spring:message code="LAB.M07.LAB00130" />',name:'prodNm', width:200},
 				        { 
							label:'<spring:message code="LAB.M01.LAB00204" />',
				            name: 'defYn', 
				            index: 'defYn', 
				            width: 80, 
				            editable: true, 
				            edittype: 'select', 
				            editoptions: { value: "Y:Yes;N:No" }
				        },
				        {name:'relProdCd', hidden:true},
				        {name:'basicProdFl', hidden:true},
				        {name:'prodCase', hidden:true},
				        {name:'prodRelTyp', hidden:true},
				        {name:'soId', hidden:true}
				   	],
				   	rowNum:20,		//한번에 노출되는 row 수
				   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
					sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
				    viewrecords: true,	
					height: 200,					//화면 상태에 따라 크기 조절 할 것
					jsonReader: {
						repeatitems : true,
						root : "rows",
						records : "records", //총 레코드 
						total : "total",  //총페이지수
						page : "page"          //현재 페이지			
					},
					multiselect: true,
				    cellEdit: true,
				    loadComplete : function () {
				    	$("#productdefultRelationsListGrid").setGridWidth($('#productdefultRelationsListGridDiv').width(),false);
				    }
				});
			  	$("#productdefultRelationsListGrid").setGridWidth($('#productdefultRelationsListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			} else {
				$("#productdefultRelationsListGrid").jqGrid("clearGridData"); 
				//$("#productdefultRelationsListGrid").jqGrid("GridUnload"); 
			}	
		}
	});	 
	
	
	$('#productdefultRelationsListBtnInsert').click(function() {
		insertData();
	});		
	
});	

$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});	

function insertData(){
	var params = new Array();  
	var message = "";
    var id = $("#productdefultRelationsListGrid").getGridParam('selarrrow');
    var ids = $("#productdefultRelationsListGrid").jqGrid('getDataIDs');
    
	if (id == ''){
		alert('<spring:message code="MSG.M08.MSG00036" />');
		return;
	}        

    for (var i = 0; i < ids.length; i++) {
    	
    	$('#productdefultRelationsListGrid').editCell(ids[i], 5, false);
    	
    	$("#productdefultRelationsListGrid").setCell(ids[i], "relProdCd", $("#productRelProdCd").val());  
    	$("#productdefultRelationsListGrid").setCell(ids[i], "basicProdFl", $("#productRelBasicProdFl").val()); 
    	$("#productdefultRelationsListGrid").setCell(ids[i], "prodCase", $("#productRelProdCase").val()); 
    	$("#productdefultRelationsListGrid").setCell(ids[i], "prodRelTyp", $("#productRelationshipListProdRelTyp").val() ); 
    	$("#productdefultRelationsListGrid").setCell(ids[i], "soId", $("#soId").val() ); 
    }

    for (var i = 0; i < ids.length; i++) {
        var check = false;
        $.each(id, function (index, value) {
            if (value == ids[i])
                check = true;
        });

        if (check) {
            var rowdata = $("#productdefultRelationsListGrid").getRowData(ids[i]);
           	console.log("1111: " + rowdata.defYn);
           	if (rowdata.defYn == ''){
           		alert('<spring:message code="MSG.M01.MSG00055" />');
           		return;
           	}/*
            	if (rowdata.defYn != '' && rowdata.defYn != 'Yes' && rowdata.defYn != 'No'){
           		alert('<spring:message code="MSG.M01.MSG00055" />');
           		return;
           	}   */          
            params.push(rowdata);
        }
    }
	
    var tempParam = JSON.stringify(params);   
    
	$.ajax({
		url : 'setProductRelationship.json',
		type : 'POST',
		async: false,
		data :  tempParam,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			
			if(data.result != "0" && data.result != "-1"){
				alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				$("#productRelDefresGrid").trigger("reloadGrid");
				$("#productRelDefreqGrid").trigger("reloadGrid");
				$("#productdefultRelationsListBtnClose").trigger('click');
			} else if (data.result == "-1") {
				alert('<spring:message code="MSG.M09.MSG00051" />');
				
			}  
		},
	    error: function(request,status,error){
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    },
		complete : function() {
		}
	});	

}


function initGrid() {
	var param = new Object();
	
	param.soId = $("#soId").val();
	param.prodCase = $("#productRelProdCase").val();
	param.prodRelTyp = $("#productRelationshipListProdRelTyp").val();
	param.basicProdFl =$("#productRelBasicProdFl").val();
	param.prodCd = $("#productRelProdCd").val();
	
	$("#productdefultRelationsListGrid").jqGrid("GridUnload"); 
  	$("#productdefultRelationsListGrid").jqGrid({
		//url:'getProductRelationshipListAll_in.json?',
	    mtype:"POST",
	   	datatype: "json",
	   	postData : param,
	   	colModel:[
			{label:'<spring:message code="LAB.M07.LAB00146" />',name:'prodTypNm', width:80, align:"center"},      
			{label:'<spring:message code="LAB.M07.LAB00156" />',name:'prodCd', width:100, align:"center"}, 
			{label:'<spring:message code="LAB.M07.LAB00146" />',name:'prodTypNm', width:100, align:"center"},      
			{label:'<spring:message code="LAB.M07.LAB00127" />',name:'prodGrpNm', width:130},			
			{label:'<spring:message code="LAB.M07.LAB00130" />',name:'prodNm', width:200},
			{label:'<spring:message code="LAB.M01.LAB00204" />',name:'defYn', index:'defYn', width:80, editable: true,edittype:"select",editoptions:{value:":선택;Y:Yes;N:No"}}
	   	],
	   	rowNum:20,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
	    viewrecords: true,	
		height: 200,					//화면 상태에 따라 크기 조절 할 것
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "records", //총 레코드 
			total : "total",  //총페이지수
			page : "page"          //현재 페이지			
		},
		multiselect: true,
	});
	$("#gbox_productdefultRelationsListGrid").css('border', '0px');
  	
  	$("#productdefultRelationsListGrid").setGridWidth($('#productdefultRelationsListGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
}
</script>
	
<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M07.LAB00123"/></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h3 class="ly_title"><spring:message code="LAB.M07.LAB00121"/></h3>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">			
		</colgroup>
		<thead>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00120"/><!-- 상품명 -->
				</th>								                                        
	     		<td>   
					<select class="w100p" id="productRelationshipListProdRelTyp" name="productRelationshipListProdRelTyp">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${reltyp}" var="reltyp" varStatus="status">
							<c:if test="${productDevMgt.prodCase == '1' && productDevMgt.basicProdFl == 'B'}">
								<c:if test="${reltyp.commonCd == 'C' || reltyp.commonCd == 'M'}">
									<option value="${reltyp.commonCd}">${reltyp.commonCdNm}</option>
								</c:if>
							</c:if>
							<c:if test="${productDevMgt.prodCase == '1' && (productDevMgt.basicProdFl == 'V' || productDevMgt.basicProdFl == 'E')}">
								<c:if test="${reltyp.commonCd == 'B' || reltyp.commonCd == 'P' || reltyp.commonCd == 'X'}">
									<option value="${reltyp.commonCd}">${reltyp.commonCdNm}</option>
								</c:if>
							</c:if>	
							<c:if test="${productDevMgt.prodCase == '1' && (productDevMgt.basicProdFl == 'P')}">
								<c:if test="${reltyp.commonCd == 'S'}">
									<option value="${reltyp.commonCd}">${reltyp.commonCdNm}</option>
								</c:if>
							</c:if>
							
							<c:if test="${productDevMgt.prodCase == '2' && productDevMgt.basicProdFl == 'B'}">
								<c:if test="${reltyp.commonCd == 'C' || reltyp.commonCd == 'M'|| reltyp.commonCd == 'P' || reltyp.commonCd == 'S'}">
									<option value="${reltyp.commonCd}">${reltyp.commonCdNm}</option>
								</c:if>
							</c:if>
							<c:if test="${productDevMgt.prodCase == '2' && (productDevMgt.basicProdFl == 'V' || productDevMgt.basicProdFl == 'E')}">
								<c:if test="${reltyp.commonCd == 'B' || reltyp.commonCd == 'X'}">
									<option value="${reltyp.commonCd}">${reltyp.commonCdNm}</option>
								</c:if>
							</c:if>	
							<c:if test="${productDevMgt.prodCase == '2' && (productDevMgt.basicProdFl == 'P')}">

							</c:if>																					
						</c:forEach>
					</select>          				                                       
	     		</td>					
			</tr>														 		
		</thead>
	</table>	

	<div class="layer_box">
		<table id="productdefultRelationsListGrid" class="w100p"></table>
		<div id="productdefultRelationsListGridDiv"></div>
	</div>


</div>
<!--// center -->
<div class="btn_box">
	<a class="blue-btn" href="#" id="productdefultRelationsListBtnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="productdefultRelationsListBtnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

