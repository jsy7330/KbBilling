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


		//조회
		$("#btn_search").click(function() {
			$("#page").val("1");
			getProdWrkList();
		});

		 // 고객조회
		$('#btnWrkDefSearch').on('click',function (e) {
				if($("#btnWrkDefSearch").hasClass('not-active')){
					return;
				}
				openWrkDefSearchPopup();	
				//openPymSearchPopup();
			}
		);

		$('#prodGrp').selectmenu({
		    change: function() {
		    	prodTpCd();
		    }
		});

		$('#wrkTp').selectmenu({
		    change: function() {
		    	wrkNm();
		    }
		});

		$('#wrkName').selectmenu({
		    change: function() {
		    	wrkDefId();
		    }
		});

		//키이벤트
		$( "#searchText" ).keypress(function(event) {
	   		if(event.keyCode == 13){
	   			searchData();
	   		}
		});

		$("#btn_insert").click(function() {
			goInsert();
		});

		$("#btn_update").click(function() {
			goUpdate();
		});	

		$("#btn_delete").click(function() {
			goDelete();
		});	

	    //등록자 조회
	    $('#btnSearchUser').on('click',function (e) {
	        $("#condUserNm").val('');  //돋보기 클릭시 초기화
	           var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
	           var param = new Object();
	           param.popType = "m";            //팝업타입 m:모달 o:일반
	           param.returnId1 = "wrkRefWrkerNm";
	           param.returnId2 = "wrkRefWrker";
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
	    });
	
	});


function openWrkDefSearchPopup(){
	var param = new Object();
	var url="openWrkDefSearchPopup.ajax";
	openModal(url, param);
}

function initGrid(lng) {
	
	$("#productWorkMngTable").jqGrid({

		url : 'prodWorkListAction.json?',
		mtype : "POST",
		datatype : "json",
		postData : {},
		jsonReader: {
			repeatitems : true,
			root : "rows",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		colModel : [ 

	   			{ label: 'prodCd' , name: 'prodCd', hidden:true,width : 0},
	   			{ label: 'soId' , name: 'soId', hidden:true,width : 0},
	   			{ label: 'orderTp' , name: 'orderTp', hidden:true,width : 0},
	   			{ label: 'wrkRefWrkerNm' , name: 'wrkRefWrkerNm', hidden:true,width : 0},
	   			{ label: 'wrkDftWrkerNm' , name: 'wrkDftWrkerNm', hidden:true,width : 0},
				{label: '<spring:message code="LAB.M10.LAB00047" />',name : 'orderTpNm',width : 50},
				{label: '<spring:message code="LAB.M08.LAB00139" />',name : 'wrkSeqNo',width : 50},
				{label: '<spring:message code="LAB.M09.LAB00035" />',name : 'wrkTp',width : 50},
				{label: '<spring:message code="LAB.M09.LAB00007" />',name : 'wrkDefId',width : 50},
				{label: '<spring:message code="LAB.M09.LAB00025" />',name : 'wrkName',width : 50},
				{label: '<spring:message code="LAB.M09.LAB00001" />',name : 'wrkAutoCmtYn',width : 50},
				{label: '<spring:message code="LAB.M01.LAB00216" />',name : 'wrkDftWrker',width : 50},
				{label: '<spring:message code="LAB.M10.LAB00011" />',name : 'wrkRefWrker',width : 50}
		],
		rowNum:25,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#jqGridPager',
		autowidth:true,
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		sortorder: "desc",
	    viewrecords: true,	
		height: 250,					//화면 상태에 따라 크기 조절 할 것
		ondblClickRow : function(rowid) { //ROW 클릭시 이벤트
			setSelectedDate(rowid);
		}
	});

	$("#productWorkMngTable").setGridWidth($('#gridDiv').width(),false);

	$(window).resize(function() {
		$("#productWorkMngTable").setGridWidth($('#gridDiv').width(),false);//그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

}
	function prodTpCd(){
		
		var soId = $("#searchSoId").val();
		var prodGrp = $("#prodGrp").val();

		var param = new Object();
		param.soId = soId;
		param.prodGrp = prodGrp;
		
		var sHtml = '';
		sHtml = sHtml + '<option value=""><spring:message code="LAB.M07.LAB00195"/></option>';
			
			$.ajax({
				url : 'getProdListAction.json',
				type : 'POST',
				data : param,
				success : function(data) {
					
					$.each(data.prodList, function(index,item){
						sHtml = sHtml + '<option value="'+item.prodCd+'">'+item.prodNm+'</option>';						
					}); 
					
					$("#prodCd").html("");
					$("#prodCd").html(sHtml);
					$( "#prodCd" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
	}
	function wrkNm(wrkDefId){
		var wrkTp = $("#wrkTp").val();

		var param = new Object();
		param.wrkTp = wrkTp;

		var sHtml = '';
		sHtml = sHtml + '<option value=""><spring:message code="LAB.M07.LAB00195"/></option>';
			
			$.ajax({
				url : 'getWrkDefList.json',
				type : 'POST',
				data : param,
				success : function(data) {

					$.each(data.wrkDefList, function(index,item){
						sHtml = sHtml + '<option value="'+item.wrkDefId+'">'+item.wrkName+'</option>';		
					}); 
					
					$("#wrkName").html("");
					$("#wrkName").html(sHtml);
					$( "#wrkName" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
	}

function wrkDefId(){
	$("#wrkDefId").val($("#wrkName").val());

}
	function getProdWrkList(){
		var soId = $("#searchSoId").val();
		var orderTp = $("#orderTp").val();
		var prodCd = $("#prodCd").val();

	if(soId == "") {

		alert("<spring:message code="MSG.M07.MSG00001"/>");
		$("#searchSoId").focus();
		return;
	}
	if(prodCd == "") {
		alert("<spring:message code="MSG.M07.MSG00064"/>");
		$("#prodCd").focus();
		return;
	}
	if(orderTp == "") {
		alert("<spring:message code="MSG.M10.MSG00007"/>");
		$("#orderTp").focus();
		return;
	}

	$("#productWorkMngTable").setGridParam({
		sortable : true,
		url : 'prodWorkListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			orderTp : orderTp,
  			soId : soId,
  			prodCd : prodCd
		}
	});
	      	
   	$("#productWorkMngTable").trigger("reloadGrid");

}
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

function goInsert() {
		var param = new Object();
		param.prodCd = $("#prodCd").val();
		param.orderTp = $("#orderTp").val();
		param.soId = $("#searchSoId").val();
		param.wrkDefId = $("#wrkDefId").val();
		param.wrkDftWrker = $("#wrkDftWrker").val();
		param.wrkRefWrker = $("#wrkRefWrker").val();
		param.wrkAutoCmtYn = $("#wrkAutoCmtYn").val();
		param. atrtYn = "Y";

		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		$.ajax({
			url : 'insertProdWrk.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				
				if(data.result != "0"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				searchData();
				}
			},
			error: function(e){
				alert(e.reponseText);
			},
			complete : function() {
			}
		});
}
	//그리드 재검색 
	function searchData(){
		
		$("#productWorkMngTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.itemId = $("#itemId").val();
		param.wrkTp = $("#wrkTp").val();
		param.prodGrp = $("#prodGrp").val();
		param.prodCd = $("#prodCd").val();
		param.orderTp = $("#orderTp").val();
        $("#productWorkMngTable").setGridParam({ postData: param }).trigger("reloadGrid");
	}
	//상세정보 
	function setSelectedDate(rowId){		
		var data = $("#productWorkMngTable").getRowData(rowId);

		$("#oldProdCd").val(data.prodCd);
		$("#oldSoId").val(data.soId);
		$("#oldOrderTp").val(data.orderTp);
		$("#orderTpNm").val(data.orderTpNm);
		$("#wrkTp").val(data.wrkTp);


		$("#orderTpNm").val(data.orderTpNm);
		$("#wrkSeqNo").val(data.wrkSeqNo);
		$("#oldWrkSeqNo").val(data.wrkSeqNo);
		
		$("#wrkDefId").val(data.wrkDefId);

		$("#wrkDftWrker").val(data.wrkDftWrker);		
		$("#wrkRefWrker").val(data.wrkRefWrker);		
		$("#wrkDftWrkerNm").val(data.wrkDftWrkerNm);
		
		$("#wrkRefWrkerNm").val(data.wrkRefWrkerNm);

		$("#wrkAutoCmtYn").val(data.wrkAutoCmtYn);
		$("#wrkAutoCmtYn").selectmenu("refresh");

		$("#wrkTp").val(data.wrkTp);
		$("#wrkTp").selectmenu("refresh");
		
		$("#wrkName").val(data.wrkName);
		$("#wrkName").selectmenu("refresh");
	}	
function init() {
	//화면 처음 들어 왔을 때 입력부분 비활성화 처리
	$('#wrkTp').selectmenu("refresh");
	$("#wrkTp").val("");
	$('#wrkName').selectmenu("refresh");
	$("#wrkName").val("");

	$("#wrkDefId").val("");
	$("#wrkDftWrkerNm").val("");
	$("#wrkDftWrker").val("");
	$("#wrkRefWrkerNm").val("");
	$('#wrkRefWrker').val("");
	$("#oldProdCd").val("");			
	$("#oldSoId").val("");
	$("#oldOrderTp").val("");
	$("#oldWrkSeqNo").val("");	
} 
function goUpdate() {
		var param = new Object();
		param.oldProdCd = $("#oldProdCd").val();

		param.oldOrderTp = $("#oldOrderTp").val();
		param.oldSoId = $("#oldSoId").val();
		param.oldWrkSeqNo = $("#oldWrkSeqNo").val();

		param.wrkDefId = $("#wrkDefId").val();
		param.wrkDftWrker = $("#wrkDftWrker").val();
		param.wrkRefWrker = $("#wrkRefWrker").val();
		param.wrkAutoCmtYn = $("#wrkAutoCmtYn").val();
		param. atrtYn = "Y";

		var check = confirm('<spring:message code="MSG.M09.MSG00008" />');
		if(!check){
			return;
		}
		$.ajax({
			url : 'updateProdWrk.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				
				if(data.result != "0"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				searchData();
				}
			},
			error: function(e){
				alert(e.reponseText);
			},
			complete : function() {
			}
		});
}
function goDelete() {


		var param = new Object();
		param.oldProdCd = $("#oldProdCd").val();

		param.oldOrderTp = $("#oldOrderTp").val();
		param.oldSoId = $("#oldSoId").val();
		param.oldWrkSeqNo = $("#oldWrkSeqNo").val();

		param.wrkDefId = $("#wrkDefId").val();
		param.wrkDftWrker = $("#wrkDftWrker").val();
		param.wrkRefWrker = $("#wrkRefWrker").val();
		param.wrkAutoCmtYn = $("#wrkAutoCmtYn").val();
		param. atrtYn = "Y";

		var check = confirm('<spring:message code="MSG.M07.MSG00054" />');
		if(!check){
			return;
		}
		$.ajax({
			url : 'deleteProdWrk.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				
				if(data.result != "0"){
					alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
				searchData();
				}
			},
			error: function(e){
				alert(e.reponseText);
			},
			complete : function() {
			}
		});
}
</script>
<input id="oldProdCd"" type="hidden" class="w110" >
<input id="oldSoId" type="hidden" class="w110" >
<input id="oldOrderTp" type="hidden" class="w110" >
<input id="oldWrkSeqNo" type="hidden" class="w110" >
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
			<th title="latest" ><spring:message code="LAB.M07.LAB00003"/></th>
			<td>
				<select class="w150" id="searchSoId" name="searchSoId">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M07.LAB00114"/></th>
			<td>
				<div class="date_box">
					<select id="prodGrp" name="prodGrp" class="w170">
						<option value=""><spring:message code="LAB.M15.LAB00002"/>
						<c:forEach items="${prodGrp}" var="prodGrp" varStatus="status">
						<option value="${prodGrp.commonCd}">${prodGrp.commonCdNm}</option>
						</c:forEach>
					</select>
				<select id="prodCd" name="prodCd" class="w150">
					<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				</select>
				</div>
			</td>
			<th><spring:message code="LAB.M10.LAB00047"/></th>
			<td>
				<select id="orderTp" name="orderTp" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/>
					<c:forEach items="${ctrtTyp}" var="ctrtTyp" varStatus="status">
					<option value="${ctrtTyp.commonCd}">${ctrtTyp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>	
	</thead>
</table> 

<div class="main_btn_box">
	<h4 class="sub_title"><spring:message code="LAB.M09.LAB00032"/></h4>
</div>
<div id='gridDiv' class="w100p">
	<table id="productWorkMngTable" class="w100p"></table>
	<div id="jqGridPager"></div>
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
				<th><spring:message code="LAB.M09.LAB00005"/><span class="dot">*</span></th>
				<td  colspan="3">
				<select id="wrkTp" name="wrkTp" class="w270">
					<option value=""><spring:message code="LAB.M15.LAB00002"/>
					<c:forEach items="${workTyp}" var="workTyp" varStatus="status">
					<option value="${workTyp.commonCd}">${workTyp.commonCdNm}</option>
					</c:forEach>
				</select>
				<select id="wrkName" name="wrkName" class="w150">
					<option value=""><spring:message code="LAB.M15.LAB00002"/>
				</select>
					<input id="wrkDefId" name="wrkDefId" value="" type="text"  class="w270">
				</td>
			</tr>
			<tr>	
			   <th><spring:message code="LAB.M09.LAB00010"/></th>
			   <td>
				<div class="inp_date w270">
					<input id="wrkDftWrkerNm" type="text" class="w110" />
					<ntels:auth auth="${menuAuthR}">
						<a id="btnWrkDefSearch" href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
					<input id="wrkDftWrker" type="text" class="w110" >
				</div>
			   </td>
			   <th><spring:message code="LAB.M10.LAB00011"/></th>
			   <td>
				<div class="inp_date w270">
					<input id="wrkRefWrkerNm" type="text" class="w150" />
					<ntels:auth auth="${menuAuthR}">
						<a id="btnSearchUser" href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
					<input id="wrkRefWrker" type="text" class="w70" >
				</div>
			   </td>
			</tr>
			<tr>
			   <th><spring:message code="LAB.M09.LAB00001"/></th>
			   <td colspan="3">
				<select id="wrkAutoCmtYn" name="wrkAutoCmtYn" class="w150">
					<option value="Y">Used</option>
					<option value="N">Not Used</option>		
				</select>
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
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:380px;" >
</div>

