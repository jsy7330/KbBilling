<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	$(document).ready(function() {
	
		//조회버튼
		$("#btnSearchP2").click(function() {
			search();            
		});
		
		//키이벤트
		$("#searchOrgIdP2").keyup(function(){
			var inputLength = $(this).val().length;
			if(inputLength == 10){
				getOrgNmReP();
			}
		});
		
		$("#btnOrgPopRel").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + $("#searchOrgIdP2").val() + "&popType=o&returnId1=searchOrgIdP2&returnId2=searchOrgNmP2";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			 
		});
		
		//그리드 시작
		initGrid();
		//조직명 검색
		getOrgNmReP();
		
	});

	
	function initGrid() {
		
		var param = new Object();
		param.orgId = $("#searchOrgIdP2").val();
		
		$("#organizationHisTable").jqGrid({
	
		   	url:'organizationHisListAction.json?',
		   	postData : param,
		    mtype:"POST",
		   	datatype: "json",
	
		   	colNames:[
				'<spring:message code="LAB.M06.LAB00060" />',	//변경일시
				'<spring:message code="LAB.M07.LAB00003" />',		//사업
				'<spring:message code="LAB.M08.LAB00103" />',	//유형
				'<spring:message code="LAB.M08.LAB00105" />',	//유형상세
				'<spring:message code="LAB.M09.LAB00160" />',	//조회권한
				'<spring:message code="LAB.M08.LAB00010" />',	//업무권한
				'<spring:message code="LAB.M09.LAB00150" />',	//조직상태
				'<spring:message code="LAB.M09.LAB00052" />',	//적용시작일
				'<spring:message code="LAB.M09.LAB00058" />',	//적용종료일
				'<spring:message code="LAB.M09.LAB00146" />',	//조직레벨
				'<spring:message code="LAB.M09.LAB00003" />',	//자사구분
				'<spring:message code="LAB.M09.LAB00201" />',	//지역분류
				'<spring:message code="LAB.M09.LAB00200" />',	//지역구분
				'<spring:message code="LAB.M08.LAB00018" />',	//여신부여여부
				'<spring:message code="LAB.M07.LAB00209" />',	//세금계산서발행여부
				'<spring:message code="LAB.M07.LAB00251" />',	//수수료지급여부
			],
		   	colModel:[
		   		
		   		{label:'chgDate',name:'chgDate', width:130, align: 'center', formatter: dateTypeFormatterYYYYMMDDHH24MISS},
		   		{label:'soNm',name:'soNm', width:100},
		   		{label:'tpNm',name:'tpNm', width:100},
		   		{label:'tpDtlNm',name:'tpDtlNm', width:100},
		   		{label:'inqPermNm',name:'inqPermNm', width:100, align: 'center'},
		   		{label:'perm',name:'perm', width:100},
		   		{label:'orgStatNm',name:'orgStatNm', width:100, align: 'center'},
		   		{label:'appyStrtDt',name:'appyStrtDt', width:100, align: 'center', formatter: stringTypeFormatterYYYYMMDD},
		   		{label:'appyEndDt',name:'appyEndDt', width:100, align: 'center', formatter: stringTypeFormatterYYYYMMDD},
		   		{label:'orgLvNm',name:'orgLvNm', width:100},
		   		{label:'privTpNm',name:'privTpNm', width:100, align: 'center'},
		   		{label:'arClCd',name:'arClCd', width:100},
		   		{label:'arTpCd',name:'arTpCd', width:100},
		   		{label:'blLoanGvFlg',name:'blLoanGvFlg', width: 80, align: 'center', formatter: 'checkbox', edittype: 'checkbox', editoptions:{value:'1:0', defaultValue:'0'}},
		   		{label:'blTbiFlg',name:'blTbiFlg', width: 100, align: 'center', formatter: 'checkbox', editoptions:{value:'1:0', defaultValue:'0'}},
		   		{label:'blFeePvFlg',name:'blFeePvFlg', width: 80, align: 'center', formatter: 'checkbox', editoptions:{value:'1:0', defaultValue:'0'}}
		   	],
		   	rowNum:10,		//한번에 노출되는 row 수
		   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
		   	pager: '#pager11',
			sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		    viewrecords: true,	
			height: 300,					//화면 상태에 따라 크기 조절 할 것
			loadComplete: function(obj){
				$("#organizationHisTable").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
			},
			
			sortable: { update: function(permutation) {
	        	$("#organizationHisTable").setGridWidth(988,false); //컬럼 위치 변경 후 재조정
	    		}
	    	}
			
		});
		
		$("#organizationHisTable").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		
	}
	
	function search(){
		
		$("#organizationHisTable").clearGridData();  // 이전 데이터 삭제
		var param = new Object();
		param.orgId = $("#searchOrgIdP2").val();
		
        $("#organizationHisTable").setGridParam({ postData: param }).trigger("reloadGrid"); 
        
	}
	
	function getOrgNmReP(){
		var param = new Object();
		param.orgId = $("#searchOrgIdP2").val();
		
		$.ajax({
			url : 'getOrganizationListAction.json',
			type : 'POST',
			data : param,
			success : function(data) {
				
				if(data.organizationData != null){
					$("#searchOrgNmP2").val(data.organizationData.orgNm);	
				}else{
					$("#searchOrgNmP2").val("");
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

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00153"/><!-- 조직이력조회 --></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00153"/><!-- 조직이력조회 --></h4>
		</div>
		<div class="fr">
			<%-- <a href="#" class="sea-btn " title="tooltip"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> --%>
			<a href="#" class="grey-btn" id="btnSearchP2"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
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
				<th  title="latest" ><spring:message code="LAB.M09.LAB00147"/><!-- 조직명 --></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date p100">
							<input type="text" id="searchOrgIdP2" name="searchOrgIdP2" value="${organization.orgId}" class="p100" />
							<a href="#" id="btnOrgPopRel" class="search">search</a>
						</div>
						<div class="inp_date w370">
							<input type="text" id="searchOrgNmP2" name="searchOrgNmP2" class="w370 not-active" disabled="disabled"/>
						</div>
					</div>                            
				</td>
			</tr>
		</thead>
	</table> 

	<div class="layer_box">
		<table id="organizationHisTable" class="w100p"></table>
		<div id="pager11"></div>
	</div>

</div>
<!--// center -->

<div class="btn_box">
	<a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>