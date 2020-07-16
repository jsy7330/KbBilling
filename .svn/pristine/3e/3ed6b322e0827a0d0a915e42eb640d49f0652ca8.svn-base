<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">

	var popType = "${productMngVO.popType}";	//팝업타입
	var popType2 = "${productMngVO.popType2}";	//팝업타입

	$(document).ready(function() {
		
		
		if(popType == "o"){	//일반팝업일 경우 버튼 닫기 처리
					
			$("#btnColse1SeP").click(function() {
				window.open("about:blank", "_self").close();
			});
			
			$("#btnClose").click(function() {
				window.open("about:blank", "_self").close();
			});
			
			if(popType2 == "01" ){	//제조사발주관리 등록
			
				$("#soIdSeP").val($("#soIdInP", opener.document).val());
				$("#soIdSeP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				$("#mncoNmSeP").val($("#mncoNmInP", opener.document).val());
				$("#mncoIdSeP").val($("#mncoIdInP", opener.document).val());
				
				var itemTpCd = $("#itemTpCdInP", opener.document).val();
				if(itemTpCd != ''){
					$("#itemTpCdSeP").val(itemTpCd);
					$("#itemTpCdSeP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				}
				
			
			}else if(popType2 == "02" ){	//제조사발주관리 수정
				
				$("#soIdSeP").val($("#soIdUpP", opener.document).val());
				$("#soIdSeP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				$("#mncoNmSeP").val($("#mncoNmUpP", opener.document).val());
				$("#mncoIdSeP").val($("#mncoIdUpP", opener.document).val());
				
				var itemTpCd = $("#itemTpCdUpP", opener.document).val();
				if(itemTpCd != ''){
					$("#itemTpCdSeP").val(itemTpCd);
					$("#itemTpCdSeP").selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				}
				
			}
			
			
		}
		
		$('#soIdSeP').selectmenu({});
		$('#itemTpCdSeP').selectmenu({});
		$('#itemKndCdSeP').selectmenu({});
		$('#searchType').selectmenu({});
		
		//제품유형 셀렉트 박스 체인지 이벤트1
		$('#itemTpCdSeP').selectmenu({
		    change: function() {
		    	changeItemTpCd("itemTpCdSeP", "itemKndCdSeP");
		    }
		});
		
		//조회버튼
		$("#searchItemSeP").click(function() {
			searchSep();            
		});
		
		//선택버튼
		$("#btnSelectItem").click(function() {
			selectItemSep();            
		});
		
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
		
		initGrid();
		
	});
	
	
	function initGrid() {
		
		var param = new Object();
		param.soId = "${productMngVO.soId}";
		
		if(popType2 == "01" || popType2 == "02"){
			param.soId = $("#soIdSeP").val();
			param.mncoId = $("#mncoIdSeP").val();
			param.itemTpCd = $("#itemTpCdSeP").val();
		} else {
			var presetItemTpCd = "${productMngVO.presetItemTpCd}";

			if (presetItemTpCd != null && presetItemTpCd != '') {
				param.itemTpCd = presetItemTpCd;
			}
		}
		
		
		$("#productSearchTable").jqGrid({
	
		   	url:'/system/common/common/productMng/productListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[

	   			{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0},
	   			{ label: 'mncoId', name: 'mncoId', hidden:true,width : 0},
		   		{ label: '<spring:message code="LAB.M07.LAB00003" />', name: 'soNm', width : 100},				//사업
	   		 	{ label: '<spring:message code="LAB.M09.LAB00098" />', name: 'mncoNm', width : 100},				//제조사
	   			{ label: '<spring:message code="LAB.M09.LAB00106" />', name: 'itemId', width : 100},			//제품ID
	   			{ label: '<spring:message code="LAB.M09.LAB00111" />', name: 'itemNm', width : 100},			//제품명
	   			{ label: '<spring:message code="LAB.M09.LAB00121" />', name: 'itemTpNm', width : 100},			//제품유형
	   			{ label: '<spring:message code="LAB.M09.LAB00123" />', name: 'itemKndNm', width : 100},		//제품종류
	   			{ label: '<spring:message code="LAB.M05.LAB00005" />', name: 'mncoOtptUtPrc', width : 100},	//매입단가
		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#productSearchPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectItemSep();
		    }, 
		    loadComplete: function(){
		    	var popType = "${productMngVO.popType}";	//팝업타입
				
				if(popType == "o"){
					$("#productSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#productSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
		    },
	    	sortable: { update: function(permutation) {
				var popType = "${productMngVO.popType}";	//팝업타입
				
				if(popType == "o"){
					$("#productSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#productSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
	    		}
	    	}
		});
		
		var popType = "${productMngVO.popType}";	//팝업타입
		
		if(popType == "o"){
		
			$("#productSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			
			//그리드 화면 재조정
			$(window).resize(function() {
				$("#productSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			});
		
		}else{
			$("#productSearchTable").setGridWidth(780,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}
		 
	}
	
	function searchSep(){
		
		$("#productSearchTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#soIdSeP").val();
		param.mncoId = $("#mncoIdSeP").val();
		param.searchType = $("#searchType").val();
		param.searchText = $("#searchText").val();
		param.itemTpCd = $("#itemTpCdSeP").val();
		param.itemKndCd = $("#itemKndCdSeP").val();
		
        $("#productSearchTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function selectItemSep(){
		
		var popType = "${productMngVO.popType}";
		var returnId1 = "${productMngVO.returnId1}";
		var returnId2 = "${productMngVO.returnId2}";
		var returnId3 = "${productMngVO.returnId3}";
		var returnId4 = "${productMngVO.returnId4}";
		
		var index  = $("#productSearchTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00106" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}
		
		var returnItemId = $("#productSearchTable").getRowData(index).itemId
		var returnItemNm = $("#productSearchTable").getRowData(index).itemNm
		var returnMncoId = $("#productSearchTable").getRowData(index).mncoId;
		var returnMncoNm = $("#productSearchTable").getRowData(index).mncoNm;
		
		if(popType == "m"){
			$("#"+returnId1).val(returnItemId);
			$("#"+returnId2).val(returnItemNm);

			if (returnId3 != null && returnId3 != '') {
				$("#"+returnId3).val(returnMncoId);
			}

			if (returnId4 != null && returnId4 != '') {
				$("#"+returnId4).val(returnMncoNm);
			}

			$("#btnClose").trigger('click');
			
		}else if(popType == "o"){
			$("#"+returnId1,opener.document).val(returnItemId);
			$("#"+returnId2,opener.document).val(returnItemNm);

			if (returnId3 != null && returnId3 != '') {
				$("#"+returnId3,opener.document).val(returnMncoId);
			}

			if (returnId4 != null && returnId4 != '') {
				$("#"+returnId4,opener.document).val(returnMncoNm);
			}
			
			if(popType2 == "01" || popType2 == "02"){	//제조사발주관리 등록, 수정
				
				opener.parent.setItemTpCd($("#productSearchTable").getRowData(index).itemTpCd);
				
			}
			
			
			window.open("about:blank", "_self").close();
			
		}
	}
	//유형 셀렉트 박스 체인지
	function changeItemTpCd(id1, id2){
		
		var grpCd = "DN00064";
		var ref1 = $("#"+id1).val();
		var param = new Object();
		
		param.grpCd = grpCd;
		param.ref1 = ref1;
		
		var sHtml = '';
		sHtml = sHtml + '<option value=""><spring:message code="LAB.M15.LAB00002"/></option>';
		
		if(ref1 == ''){
			$("#"+id2).html("");
			$("#"+id2).html(sHtml);
			
			$("#"+id2).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			
		}else{
			
			$.ajax({
				url : '/system/common/common/organizationMng/getCommonCodeListByRef1ListAction.json',
				type : 'POST',
				data : param,
				async: false,
				success : function(data) {
					
					$.each(data.codeList, function(index,item){
						sHtml = sHtml + '<option value="'+item.commonCd+'">'+item.commonCdNm+'</option>';						
					}); 
					
					$("#"+id2).html("");
					$("#"+id2).html(sHtml);
					
					$("#"+id2 ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
			
		}
		
	}
	
</script>


<c:if test="${productMngVO.popType eq 'o'}"> 
	<div class="Layer_wrap" style="width:800px;" >
</c:if>


<div class="layer_top">
     <div class="title"><spring:message code="LAB.M09.LAB00107"/><!-- 제품검색 --></div>
     <a href="#" id="btnColse1SeP" name="btnColse1SeP" class="close"></a>
</div>

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00116"/><!-- 제품선택 --></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="searchItemSeP" name="searchItemSeP" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
				<td colspan="3">
				
					<select class="w200" id="soIdSeP" name="soIdSeP">
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00098"/><!-- 제조사 --></th>
				<td>
					<div class="inp_date w190">
						<input type="text" id="mncoNmSeP" name="mncoNmSeP" class="w150" />
						<input type="hidden" id="mncoIdSeP" name="mncoIdSeP" />
                        <a href="#" id="btnSearchMncoSeP" name="btnSearchMncoSeP" class="search">search</a>
					</div>
				</td>
				<th><spring:message code="LAB.M09.LAB00159"/><!-- 조회구분 --></th>
				<td>
					<div class="date_box">
						<select class="w100" id="searchType" name="searchType" >
							<option value="01" >Product ID</option>
							<option value="02" >Product Name</option>
						</select>                                                
						<input type="text" id="searchText" name="searchText" class="w100" />
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00121"/><!-- 제품유형 --></th>
				<td>
					<select id="itemTpCdSeP" name="itemTpCdSeP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:choose>
							<c:when test="${productMngVO.presetItemTpCd != '' and productMngVO.presetItemTpCd ne null}">
						        <c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
									<option value="${itemTpCd.commonCd}" <c:if test="${itemTpCd.commonCd eq productMngVO.presetItemTpCd}">selected="selected"</c:if>>${itemTpCd.commonCdNm}</option>
								</c:forEach>
							</c:when>
							<c:otherwise>
						        <c:forEach items="${itemTpCd}" var="itemTpCd" varStatus="status">
									<option value="${itemTpCd.commonCd}">${itemTpCd.commonCdNm}</option>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</select>                                            
				</td>
				<th><spring:message code="LAB.M09.LAB00123"/><!-- 제품종류 --></th>
				<td>
					<select id="itemKndCdSeP" name="itemKndCdSeP" class="w200">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
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
	
	<c:if test="${productMngVO.popType eq 'm'}"> 
	<div class="layer_box">
		<table id="productSearchTable" class="w100p"></table>
		<div id="productSearchPager"></div>
	</div>
	</c:if>
	
	<c:if test="${productMngVO.popType eq 'o'}"> 
		<div id='gridDivPop01'>
			<table id="productSearchTable" class="w100p"></table>
			<div id="productSearchPager"></div>
		</div>
	</c:if>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSelectItem" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<c:if test="${productMngVO.popType eq 'o'}"> 
	</div>
</c:if>