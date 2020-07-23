<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<style type="text/css">
table.ui-datepicker-calendar { display:none; }
#dimMask {
position:absolute;
z-index:9000;
background-color:#000;
display:none;
left:0;
top:0;
}
.window{
display: none;
position:absolute;
left:100px;
top:100px;
z-index:10000;
}

</style>

<script type="text/javascript">
$(document).ready(function() {

	pageInit();

	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';

	//달력처리
	if($(".month-picker").length > 0){
	      if(lng == 'ko'){
	         format = 'yy-mm';
	      }else if (lng == 'en'){
	         format = 'mm/yy';
	      }
	      $('.month-picker').datepicker( {
	         changeMonth: true,
	         changeYear: true,
	         showButtonPanel: true,
	         dateFormat: format,
	         onClose: function(dateText, inst) {
	            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
	            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	            $(this).datepicker('setDate', new Date(year, month, 1));
	         },
	         beforeShow : function (dateText, inst) {
	                 
	            var selectDate = $(this).val().split("-");
	            var year = Number(selectDate[0]);
	            var month = Number(selectDate[1]) - 1;
	            $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
	            $(this).datepicker('setDate', new Date(year, month, 1));
	                  
	         }
	      }).datepicker("setDate", new Date());  
	   }
	
	$('#searchStatDt').datepicker('setDate', new Date());

	//그리드 처리
	$("#workGrpGrid").jqGrid({
		url : '/charge/charge/calculationSearch/calculationSearchMng/getChargePersonCountList.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
			soId: $('#condSo').val() ,
  	    	billYymm : dateFormatToStringYYYYMM($('#searchStatDt').val())
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'soNm', name: 'SO_NM', width : 100, align:"center", hidden:true},
		    { label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'PROD_NM', width : 200, sortable:false},
		    { label: '<spring:message code="LAB.M01.LAB00275"/>', name: 'CUST_CNT',  align:"right", sortable:false, formatter:numberAutoFormatter},
		    { label: '<spring:message code="LAB.M01.LAB00267"/>', name: 'CTRT_CNT',  align:"right", sortable:false, formatter:numberAutoFormatter},
		    { label: '<spring:message code="LAB.M01.LAB00276"/>', name: 'PROD_CMPS_CNT',  align:"right", sortable:false, formatter:numberAutoFormatter},
		    { label: '<spring:message code="LAB.M01.LAB00277"/>', name: 'SVC_CMPS_CNT',  align:"right", sortable:false, formatter:numberAutoFormatter},
			{ label: '<spring:message code="LAB.M07.LAB00022"/>', name: 'USE_QTY',  align:"right", sortable:false, formatter:numberAutoFormatter},
		    { label: '<spring:message code="LAB.M07.LAB00361"/>', name: 'USE_AMT',  align:"right" ,sortable:false, formatter:numberAutoFormatter},
		    { label: '<spring:message code="LAB.M07.LAB00018"/>', name: 'USE_CNT',  hidden:true, align:"right", formatter:numberAutoFormatter}
			
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 300,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "charPersonCntList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 20,
        pager: "#workGrpGridPager",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});

	//작업명 조회 이벤트
	$( "#condWorkGrpNm" ).keypress(function(event) {
		if($("#searchBtn").hasClass('not-active')){
			return;
		}
      if(event.keyCode == 13){
        searchWorkGrpList();
      }
    });

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
	    	if($("#searchBtn").hasClass('not-active')){
				return;
			}
    		searchWorkGrpList();
		}
    );

    //사용자 추가 버튼
    $('#addUserBtn').on('click',function (e) {
      	if($("#addUserBtn").hasClass('not-active')){
          return;
  		  }
    		addUserBtn();
		  }
    );

    //사용자수정버튼
    $('#updateWorkUserBtn').on('click',function (e) {
      	if($("#updateWorkUserBtn").hasClass('not-active')){
          return;
  		  }
    		updateWorkUserBtn();
		  }
    );

    //사용자삭제버튼
    $('#deleteWorkUserBtn').on('click',function (e) {
      	if($("#deleteWorkUserBtn").hasClass('not-active')){
          return;
  		  }
    		deleteWorkUserBtn();
		  }
    );

	$('#workGrpNmTxt').keyup(function(){
	  		var str = getMaxStr($('#workGrpNmTxt').val(), 30);
	  		if(str != $('#workGrpNmTxt').val()){
	  			$('#workGrpNmTxt').val(str);
	  		}
  		}
	);
});

/**
 * 조회
 */
function searchWorkGrpList() {
	
	if($('#condSo').val() == 'SEL'){
		alert('<spring:message code="MSG.M07.MSG00002"/>');
		return;
	}
	
	$("#workGrpGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  	    	soId: $('#condSo').val() ,
  	    	billYymm : dateFormatToStringYYYYMM($('#searchStatDt').val())
		}
	});
	
   	$("#workGrpGrid").trigger("reloadGrid");	

}


/*
 * 페이지 초기화
 */
function pageInit(){

	$("#workGrpGrid").clearGridData();
	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();

	$('#workGrpIdTxt').val('');
	$('#workGrpIdTxt').addClass('not-active');
    $('#workGrpIdTxt').attr('disabled', true);
    $('#workGrpNmTxt').val('');
	$('#workGrpNmTxt').addClass('not-active');
    $('#workGrpNmTxt').attr('disabled', true);
    $("#workGrpSoSel").val('SEL');
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('disable');
    $("#workGrpUseYnSel").val('SEL');
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('disable');
}

/*
 * 버튼 비활성화 처리
 */
function btnDisable(id){
	$('#' + id ).addClass('white-btn');
	$('#' + id ).removeClass('grey-btn');
	$('#' + id ).addClass('not-active');
	$('#' + id ).attr('disabled',true);
	
}

/*
 * 버튼 활성화 처리
 */
function btnEnable(id){
	$('#' + id ).addClass('grey-btn');
	$('#' + id ).removeClass('white-btn');
	$('#' + id ).removeClass('not-active');
	$('#' + id ).removeAttr('disabled');
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


<!-- 검색 버튼 -->
<div class="main_btn_box">
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='searchBtn' href="#" class="grey-btn" title='<spring:message code="LAB.M09.LAB00158"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</ntels:auth>
</div>


<!-- 검색부 -->
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:40%;">
		<col style="width:10%;">
		<col style="width:40%;">
	</colgroup>
	<thead>
		<tr>
			<th><spring:message code="LAB.M07.LAB00003" /><span class="dot">*</span></th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th><spring:message code="LAB.M10.LAB00033" /><span class="dot">*</span></th> <!-- 청구년월 -->
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="month-picker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<!--작업그룹표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M01.LAB00278" /></h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

