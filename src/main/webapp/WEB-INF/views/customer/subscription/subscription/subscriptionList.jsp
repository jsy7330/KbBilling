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

		//조회
		$("#btn_popUp").click(function() {
			goUpdatePage();
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
	
	$("#subscriptionTable").jqGrid({

		url : 'subscriptionAction.json?',
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
		            {label: '접수대기',name : '',width : 50,key: true},
		 		    {label: '작업접수',name : '',width : 50},
		 		    {label: '작업진행',name : '',width : 50},
		 		    {label: '작업완료',name : '',width : 50},
		 		    {label: '작업취소',name : '',width : 50},
		 		    {label: '합계',name : '',width : 50}
		],
		rowNum:25,		//한번에 노출되는 row 수
	   	rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
	   	pager: '#jqGridPager',
		autowidth:true,
		sortable : true,				//드래그로 컬럼간의 위치 변경 가능 여부
		sortorder: "desc",
	    viewrecords: true,	
		height: 20,					//화면 상태에 따라 크기 조절 할 것
		ondblClickRow : function(rowid) { //ROW 클릭시 이벤트
			goUpdatePage(rowid);
		},
        loadComplete : function () {
        	$("#subscriptionTable").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
        	$("#subscriptionTable").setGridWidth($('#gridDiv').width(),false); //컬럼 위치 변경 후 재조정
    		}
    	}
	});

	$("#subscriptionTable1").jqGrid({

		url : 'subscriptionListAction.json?',
		mtype : "POST",
		datatype : "json",
		postData : {},
		jsonReader: {
			repeatitems : true,
			root : "subscriptionList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		colModel : [ 
		            {label: '사업',name : '',width : 50,key: true},
		 		    {label: '계약ID',name : '',width : 50},
		 		    {label: '오더ID',name : '',width : 50},
		 		    {label: '이동전화번호',name : '',width : 50},
		 		    {label: '고객명',name : '',width : 50},
		 		    {label: '소품',name : '',width : 50},
		 		    {label: '작업번호',name : '',width : 50},
		 		    {label: '작업명',name : '',width : 50},
					{label: '작업상태',name : '',width : 50},
		 		    {label: '오더접수일',name : '',width : 80},
		 		    {label: '작업희망일',name : '',width : 50},
		 		    {label: '작업실행일',name : '',width : 80},
		 		    {label: '작업완료일',name : '',width : 80},
		 		    {label: '작업그룹',name : '',width : 80},
		 		    {label: '수정자',name : '',width : 80},
		 		    {label: '수정일시',name : '',width : 80},
		 		    {label: '등록자',name : '',width : 80},
		 		    {label: '등록일시',name : '',width : 80}
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
        	$("#subscriptionTable1").setGridWidth($('#gridDiv1').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
        	$("#subscriptionTable1").setGridWidth($('#gridDiv1').width(),false); //컬럼 위치 변경 후 재조정
    		}
    	}
	});

	$("#subscriptionTable").setGridWidth($('#gridDiv').width(),false);
	$("#subscriptionTable1").setGridWidth($('#gridDiv1').width(),false);

	$(window).resize(function() {
		$("#subscriptionTable").setGridWidth($('#gridDiv').width(),false);//그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	$(window).resize(function() {
		$("#subscriptionTable1").setGridWidth($('#gridDiv1').width(),false);//그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});	
}
function goUpdatePage(rowid) {
	 
	 $('#popup_dialog').css('height', '760px');
	 var data = $("#attributeTable").getRowData(rowid)
	 var url="/customer/subscription/subscription/subscriptionDetailPopup.ajax";
    	var param = new Object();
    	
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
			<a id='btn_popUp' href="#"class="grey-btn" title='<spring:message code="LAB.M01.LAB00047"/>'>팝업</a> 
		</div>
	</ntels:auth>
</div>


<table class="writeview">
	<colgroup>
		<col style="width:5%;">
		<col style="width:20%;">
		<col style="width:5%;">
		<col style="width:30%;">
		<col style="width:5%;">
		<col style="width:20%;">
		<col style="width:5%;">
		<col style="width:20%;">
	</colgroup>
	<thead> 
		<tr>
			<th title="latest" >사업</th>
			<td>
				<select class="w150" id="searchSoId" name="searchSoId">
					<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
						<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th>작업요청일</th>
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
			<th>서비스번호</th>
			<td>
				<div class="inp_date w220">
					<input id="svcTelNo" type="text" class="w200" />
				</div>
			</td>
			<th>작업자ID</th>
			<td>
				<div class="inp_date w220">
					<input id="wrkDftWrker" type="text" value="${wrkDftWrker}" class="w200" />
				</div>
			</td>
		</tr>
		<tr>
			<th>오더유형</th>
			<td>
				<select id="orderTp" name="orderTp" class="w150">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${orderTpList}" var="orderTpList" varStatus="status">
					<option value="${orderTpList.commonCd}">${orderTpList.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th>작업상태</th>
			<td>
				<select id="wrkStat" name="wrkStat" class="w150">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${workStatList}" var="workStatList" varStatus="status">
					<option value="${workStatList.commonCd}">${workStatList.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th>고객</th>
			<td colspan="3">
				<div class="inp_date w220">
					<input id="condCustNm" type="text" class="w200" />
					<input id="condCustId" type="text" hidden >

					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch" href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<h4 class="sub_title">작업전체현황</h4>
</div>
	<div id='gridDiv' class="w100p">
		<table id="subscriptionTable" class="w100p"></table>
		<div id="jqGridPager"></div>
	</div>
<div class="main_btn_box">
	<h4 class="sub_title">작업리스트</h4>
</div>
	<div id='gridDiv1' class="w100p">
		<table id="subscriptionTable1" class="w100p"></table>
		<div id="jqGridPager"></div>
	</div>
</div>


<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

