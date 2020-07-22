<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {

	pageInit();

	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
	
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
		});
		
		$('#condUseYymm').datepicker('setDate', new Date());
		 
		// 년월 레이어 focus
	    $(".month-picker").focus(function () {
	        $(".ui-datepicker-calendar").hide();
	        $("#ui-datepicker-div").position({
	            my: "center top",
	            at: "center bottom",
	            of: $(this)
	        });
	    });
		
	}
	
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "-7"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );
	

	if($(".datepicker1").length > 0){
		$( ".datepicker1" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
		      regional:lng
		    }).datepicker("setDate", "0"); 
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker1.disabled" ).datepicker( "option", "disabled", true );

	//그리드 처리
	$("#workGrpGrid").jqGrid({
		url : '/product/service/serviceMgt/workGrpMng/getWorkGrpListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
			soId: $('#condSo').val() ,
  	    	useYymm : dateFormatToStringYYYYMM($('#condUseYymm').val()),
  	    	ctrtId : $('#condCtrtId').val() ,
  	    	useTyp : $('#condUseTyp').val(),
  	    	searchStDt : dateFormatToStringYYYYMMDD($('#searchStatDt').val()),
  	    	searchEndDt : dateFormatToStringYYYYMMDD($('#searchEndDt').val())
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
		    { label: '<spring:message code="LAB.M01.LAB00050"/>', name: 'SO_NM', width : 100, align:"left", sortable:false},			//고객명
		    { label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'SVC_WRK_GRP_ID', width : 100, align:"center", sortable:false}, //계약ID
		    { label: '<spring:message code="LAB.M07.LAB00130"/>', name: 'SVC_WRK_GRP_NM', width : 200, align:"left", sortable:false},	//상품명
		    { label: '<spring:message code="LAB.M07.LAB00362"/>', name: 'USE_YN_NM', width : 100, align:"center", sortable:false},		//사용일자
		    { label: '<spring:message code="LAB.M07.LAB00022"/>', name: 'CHGR_NM', width : 150, sortable:false},						//사용량
			{ label: '<spring:message code="LAB.M07.LAB00029"/>', name: 'CHGR_NM', width : 150, sortable:false},						//사용유형
		    { label: '<spring:message code="LAB.M08.LAB00218"/>', name: 'CHGR_NM', width : 150, sortable:false},	//이용금액
		    { label: '<spring:message code="LAB.M01.LAB00279"/>', name: 'CHGR_NM', width : 150, sortable:false},	//공제금액
		    { label: '<spring:message code="LAB.M14.LAB00025"/>', name: 'CHGR_NM', width : 150, sortable:false},	//할인금액
		    { label: '<spring:message code="LAB.M01.LAB00280"/>', name: 'CHGR_NM', width : 150, sortable:false},	//과금금액
		    { label: '<spring:message code="LAB.M01.LAB00149"/>', name: 'CHGR_NM', width : 150, sortable:false}		//과금항목
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 300,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "workGrpList",
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

    //조회 버튼 이벤트
    $('#searchBtn').on('click',function (e) {
	    	if($("#searchBtn").hasClass('not-active')){
				return;
			}
    		searchWorkGrpList();
		}
    );

    //신규등록 버튼 이벤트
    $('#newBtn').on('click',function (e) {
      	if($("#newBtn").hasClass('not-active')){
          return;
  		  }
    		insertBtn();
		  }
    );

    //수정 버튼 이벤트
    $('#updateBtn').on('click',function (e) {
      	if($("#updateBtn").hasClass('not-active')){
          return;
  		  }
    		updateBtn();
		  }
    );

    //삭제 버튼 이벤트
    $('#deleteBtn').on('click',function (e) {
      	if($("#deleteBtn").hasClass('not-active')){
          return;
  		  }
    		deleteBtn();
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
function searchWorkGrpList(){
	
	if($('#condSo').val() == 'SEL'){
		alert('<spring:message code="MSG.M07.MSG00002"/>');
		return;
	}
	if($('#condCtrtId').val() == ''){
		alert('<spring:message code="MSG.M01.MSG00067"/>');
		return;
	}
	
	alert("searchStatDt:" + searchStatDt);
	alert("searchEndDt: " + searchEndDt);
	$("#workGrpGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  	    	soId: $('#condSo').val() ,
  	    	useYymm : dateFormatToStringYYYYMM($('#condUseYymm').val()),
  	    	ctrtId : $('#condCtrtId').val() ,
  	    	useTyp : $('#condUseTyp').val(),
  	    	searchStDt : dateFormatToStringYYYYMMDD($('#searchStatDt').val()),
  	    	searchEndDt : dateFormatToStringYYYYMMDD($('#searchEndDt').val())
		}
	});
	
	console.info(JSON.stringify(postData));
   	$("#workGrpGrid").trigger("reloadGrid");
}

/*
 * 페이지 초기화
 */
function pageInit(){
	
	$('#condSo').val('SEL');
	$("#condSo").selectmenu('refresh');
	
	$('#condCtrtId').val('');
	
	$('#condUseTyp').val('SEL');
	$("#condUseTyp").selectmenu('refresh');

	$("#workGrpGrid").clearGridData();
	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();
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
			<th><spring:message code="LAB.M07.LAB00003" /><span class="dot">*</span></th> <!-- 사업 -->
			<td colspan="3">
				<select id="condSo" class="w40p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00021" /><span class="dot">*</span></th><!-- 사용년월 -->
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="condUseYymm" name="condUseYymm"  class="month-picker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
			<th><spring:message code="LAB.M01.LAB00032" /><span class="dot">*</span></th> <!-- 계약ID -->
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w120" />
					<input id="condCtrtId" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M07.LAB00029" /></th><!-- 사용유형 -->
			<td>
				<select id="condUseTyp" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<option value="DATAPACKET">Data Packet</option>
				</select>
			</td>
			<th><spring:message code="LAB.M07.LAB00363" /></th><!-- 사용기간 -->
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
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M08.LAB00219" /></h4><!-- 이용내역 -->
	</div>
	<div class="fr mt10">
		<a id='disableMaskBtn' class="grey-btn" href="#" title='액셀저장'><spring:message code="LAB.M08.LAB00015" /></a><!-- 엑셀다운로드 -->
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

