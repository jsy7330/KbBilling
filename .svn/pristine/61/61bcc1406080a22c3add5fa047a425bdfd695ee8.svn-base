<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	var popType = "${organization.popType}";	//팝업타입
	var popType2 = "${organization.popType2}";	//팝업타입2

	$(document).ready(function() {
		
		if(popType == "o"){	//일반팝업일 경우 버튼 닫기 처리
					
			$("#btnColse1SeP").click(function() {
				window.open("about:blank", "_self").close();
			});
			
			$("#btnClose").click(function() {
				window.open("about:blank", "_self").close();
			});
			
		}
		
		$('#soIdSeP').selectmenu({});
		$('#tpDtlCdSeP').selectmenu({});
		$('#tpCdSeP').selectmenu({});
		
		if(popType2 == "LGSTI" || popType2 == "LGSTU"){		//물류센터만
			$('#tpCdSeP').val("2000");
			$("#tpCdSeP").selectmenu("disable");
			$("#tpCdSeP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			changeTpCdSeP();
		}
		
		//유형 셀렉트 박스 체인지 이벤트
		$('#tpCdSeP').selectmenu({
		    change: function() {
		    	changeTpCdSeP();
		    }
		});
		
		//조회버튼
		$("#searchOrgSeP").click(function() {
			searchSep();            
		});
		
		//선택버튼
		$("#btnSelectOrg").click(function() {
			selectOrgSep();            
		});
		
		initGrid();
		
	});
	
	
	function initGrid() {
		
		var param = new Object();
		param.soId = $("#soIdSeP").val();
		param.orgId = $("#orgIdSeP").val();
		param.tpCd = $("#tpCdSeP").val();
		
		$("#organizationSearchTable").jqGrid({
	
		   	url:'/system/common/common/organizationMng/organization2ListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colModel:[

				{label:'soId',name:'soId', width:100, hidden:true},
				{label:'appyStrtDt',name:'appyStrtDt', width:100, align:'center', hidden:true},
				{label:'appyEndDt',name:'appyEndDt', width:100, align:'center', hidden:true},
				{label:'tpCd',name:'tpCd', width:0, hidden:true},
				{label:'tpDtlCd',name:'tpDtlCd', width:0, hidden:true},
				{label:'orgLvCd',name:'orgLvCd', width:0, hidden:true},
				{label:'zipCd',name:'zipCd', width:0, hidden:true},
				{label:'addr1',name:'addr1', width:0, hidden:true},
				{label:'addr2',name:'addr2', width:0, hidden:true},
				{label:'<spring:message code="LAB.M07.LAB00003" />',name:'soNm', width:100},	//사업
				{label:'<spring:message code="LAB.M09.LAB00139" />',name:'orgId', width:100, align:'center' },	//조직ID
				{label:'<spring:message code="LAB.M09.LAB00147" />',name:'orgNm', width:100},	//조직명
				{label:'<spring:message code="LAB.M09.LAB00205" />',name:'userId', width:100},	//직원ID
				{label:'<spring:message code="LAB.M09.LAB00207" />',name:'userName', width:100},	//직원명
				{label:'<spring:message code="LAB.M08.LAB00103" />',name:'tpNm', width:100},		//유형
				{label:'<spring:message code="LAB.M08.LAB00105" />',name:'tpDtlNm', width:100},	//유형상세
				{label:'<spring:message code="LAB.M09.LAB00201" />',name:'arClNm', width:100},	//지역분류
				{label:'<spring:message code="LAB.M09.LAB00200" />',name:'arTpNm', width:100},	//지역구분
				{label:'<spring:message code="LAB.M09.LAB00160" />',name:'inqPermNm', width:100},	//조회권한
				{label:'<spring:message code="LAB.M09.LAB00146" />',name:'orgLvNm', width:100},	//조직레벨
				{label:'<spring:message code="LAB.M09.LAB00065" />',name:'telNo', width:100},	//전화번호
				{label:'<spring:message code="LAB.M14.LAB00052" />',name:'mtelNo', width:100}	//핸드폰번호
			],	   		
				   		
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#organizationSearchPager',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 200,					//화면 상태에 따라 크기 조절 할 것
		    ondblClickRow : function(id){ //ROW 클릭시 이벤트
		    	selectOrgSep();
		    }, 
		    loadComplete: function(){

				if(popType == "o"){
					$("#organizationSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#organizationSearchTable").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
		    	
		    },
	    	sortable: { update: function(permutation) {
	    		if(popType == "o"){
					$("#organizationSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}else{
					$("#organizationSearchTable").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
				}
	    		}
	    	}
		});
		
		if(popType == "o"){
		
			$("#organizationSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			
			//그리드 화면 재조정
			$(window).resize(function() {
				$("#organizationSearchTable").setGridWidth($('#gridDivPop01').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			});
		
		}else{
			$("#organizationSearchTable").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		}
		
		
	}
	
	function searchSep(){
		
		$("#organizationSearchTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.soId = $("#soIdSeP").val();
		param.orgId = $("#orgIdSeP").val();
		param.tpCd = $("#tpCdSeP").val();
		param.tpDtlCd = $("#tpDtlCdSeP").val();
		//param.arClCd = $("#arClCdSeP").val();
		//param.arTpCd = $("#arTpCdSeP").val();
		
        $("#organizationSearchTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function selectOrgSep(){
		
		var returnId1 = "${organization.returnId1}";
		var returnId2 = "${organization.returnId2}";
		
		var index  = $("#organizationSearchTable").jqGrid("getGridParam", "selrow" );
		
		if(index == null || index == ''){
			alert('<spring:message code="LAB.M09.LAB00138" /> <spring:message code="MSG.M08.MSG00042" />');
			return;
		}
		
		var returnOrgId = $("#organizationSearchTable").getRowData(index).orgId
		var returnOrgNm = $("#organizationSearchTable").getRowData(index).orgNm
		
		if(popType == "m"){
			$("#"+returnId1).val(returnOrgId);
			$("#"+returnId2).val(returnOrgNm);
			$("#btnClose").trigger('click');
			
		}else if(popType == "o"){
			$("#"+returnId1,opener.document).val(returnOrgId);
			$("#"+returnId2,opener.document).val(returnOrgNm);
			
			if(popType2 == "01"){
				$("#partTpCdInP",opener.document).val($("#organizationSearchTable").getRowData(index).tpCd);
				$("#partTpNmInP",opener.document).val($("#organizationSearchTable").getRowData(index).tpNm);
				$("#partTpDtlCdInP",opener.document).val($("#organizationSearchTable").getRowData(index).tpDtlCd);
				$("#partTpDtlNmInP",opener.document).val($("#organizationSearchTable").getRowData(index).tpDtlNm);
				$("#partOrgLvIdInP",opener.document).val($("#organizationSearchTable").getRowData(index).orgLvCd);
				$("#partOrgLvNmInP",opener.document).val($("#organizationSearchTable").getRowData(index).orgLvNm);
			}else if(popType2 == "02"){
				$("#partTpCdUpP",opener.document).val($("#organizationSearchTable").getRowData(index).tpCd);
				$("#partTpNmUpP",opener.document).val($("#organizationSearchTable").getRowData(index).tpNm);
				$("#partTpDtlCdUpP",opener.document).val($("#organizationSearchTable").getRowData(index).tpDtlCd);
				$("#partTpDtlNmUpP",opener.document).val($("#organizationSearchTable").getRowData(index).tpDtlNm);
				$("#partOrgLvIdUpP",opener.document).val($("#organizationSearchTable").getRowData(index).orgLvCd);
				$("#partOrgLvNmUpP",opener.document).val($("#organizationSearchTable").getRowData(index).orgLvNm);
			}else if(popType2 == "03"){		//유통정보관리 팝업에서 사용
				
				window.opener.setDataInP($("#organizationSearchTable").getRowData(index).appyStrtDt, $("#organizationSearchTable").getRowData(index).appyEndDt, $("#organizationSearchTable").getRowData(index).soId);
			}else if(popType2 == "LGSTI"){		//물류센터만
				
				
				$("#actncInchrgInP",opener.document).val($("#organizationSearchTable").getRowData(index).userName);
				$("#actncInchrgTelInP",opener.document).val($("#organizationSearchTable").getRowData(index).mtelNo);
				$("#dlvZipCdInP",opener.document).val($("#organizationSearchTable").getRowData(index).zipCd);
				$("#dlvBssAddrInP",opener.document).val($("#organizationSearchTable").getRowData(index).addr1);
				$("#dlvDtlAddrInP",opener.document).val($("#organizationSearchTable").getRowData(index).addr2);
				
			}
			
			window.open("about:blank", "_self").close();
			
		}
	}
	
	
	//유형 셀렉트 박스 체인지
	function changeTpCdSeP(){
		
		var grpCd = "DN00039";
		var ref1 = $("#tpCdSeP").val();
		
		var param = new Object();
		param.grpCd = grpCd;
		param.ref1 = ref1;
		
		var sHtml = '';
		sHtml = sHtml + '<option value=""><spring:message code="LAB.M07.LAB00195"/></option>';
		
		if(ref1 == ''){
			$("#tpDtlCdSeP").html("");
			$("#tpDtlCdSeP").html(sHtml);
			
			$( "#tpDtlCdSeP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			
		}else{
			
			$.ajax({
				url : '/system/common/common/organizationMng/getCommonCodeListByRef1ListAction.json',
				type : 'POST',
				data : param,
				success : function(data) {
					
					$.each(data.codeList, function(index,item){
						sHtml = sHtml + '<option value="'+item.commonCd+'">'+item.commonCdNm+'</option>';						
					}); 
					
					$("#tpDtlCdSeP").html("");
					$("#tpDtlCdSeP").html(sHtml);
					
					$("#tpDtlCdSeP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
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

<c:if test="${organization.popType eq 'o'}"> 
	<div class="Layer_wrap" style="width:800px;" >
</c:if>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00141"/><!-- 조직검색 --></div>
	<a href="#" id="btnColse1SeP" class="close">Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00151"/><!-- 조직선택 --></h4>
		</div>
		<div class="fr">
			<a href="#"class="grey-btn" id="searchOrgSeP" name="searchOrgSeP" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
						<%-- <option value=""><spring:message code="LAB.M15.LAB00002"/></option> --%>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${soAuthList.so_id eq session_user.soId}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>  
				</td>
				<th><spring:message code="LAB.M07.LAB00071"/><!-- 사용자명 --></th>
				<td>
					<input type="text" id="userNameSep" name="userNameSep" class="w133">
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00140"/><!-- 조직ID/명 --></th>
				<td colspan="3">
					<div class="inp_date w310">
						<input type="text" id="orgIdSeP" name="orgIdSeP" value="${session_user.orgId}" class="w133">
						<input type="text" id="orgNmSep" name="orgNmSep" class="w133">
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M08.LAB00103"/><!-- 유형 --></th>
				<td>
					<select id="tpCdSeP" name="tpCdSeP" class="w200">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${tpCd}" var="tpCd" varStatus="status">
							<option value="${tpCd.commonCd}">${tpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
				<th><spring:message code="LAB.M08.LAB00105"/><!-- 유형상세 --></th>
				<td>
					<select id="tpDtlCdSeP" name="tpDtlCdSeP" class="w200">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					</select>  
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M09.LAB00201"/><!-- 지역분류 --></th>
				<td>
				
					<select id="arClCdSeP" name="arClCdSeP" class="w200">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${arClCd}" var="arClCd" varStatus="status">
							<option value="${arClCd.commonCd}">${arClCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
				<th><spring:message code="LAB.M09.LAB00200"/><!-- 지역구분 --></th>
				<td>
				
					<select id="arTpCdSeP" name="arTpCdSeP" class="w200">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${arTpCd}" var="arTpCd" varStatus="status">
							<option value="${arTpCd.commonCd}">${arTpCd.commonCdNm}</option>
						</c:forEach>
					</select>  
				</td>
			</tr>
		</thead>
	</table> 
	
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00148"/><!-- 조직목록 --></h4>
		</div>
	</div>
	
	<c:if test="${organization.popType eq 'm'}"> 
	<div class="layer_box">
		<table id="organizationSearchTable" class="w100p"></table>
		<div id="organizationSearchPager"></div>
	</div>
	</c:if>
	
	<c:if test="${organization.popType eq 'o'}"> 
		<div id='gridDivPop01'>
		<!-- <div id='gridDivPop01' class="w100p"> -->
			<table id="organizationSearchTable" class="w100p"></table>
			<div id="organizationSearchPager"></div>
		</div>
	</c:if>

</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn" id="btnSelectOrg" href="#" ><span class="confirm_icon"><spring:message code="LAB.M07.LAB00195" /></span></a>
      <a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>

<c:if test="${organization.popType eq 'o'}"> 
	</div>
</c:if>
