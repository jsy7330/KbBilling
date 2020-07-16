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
	
	//CHANHEE(달력처리 수정 .datepicker 주석 / .month-picker 추가)
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
		}); 
		 
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
	
	$('#searchStatDt').datepicker('setDate', new Date());
	
	/* 
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
 */
	//그리드 처리
	$("#workGrpGrid").jqGrid({
		url : '/product/service/serviceMgt/workGrpMng/getWorkGrpListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
		    { label: '납부계정ID', name: 'SO_NM', width : 100, align:"left", sortable:false},
		    { label: '납부계정명', name: 'SVC_WRK_GRP_ID', width : 100, align:"center", sortable:false},
		    { label: '고객ID', name: 'SVC_WRK_GRP_NM', width : 200, align:"left", sortable:false},
		    { label: '고객명', name: 'USE_YN_NM', width : 100, align:"center", sortable:false},
		    { label: '계약ID', name: 'CHGR_NM', width : 150, sortable:false},
			{ label: '서비스번호', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '상품명', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '서비스명', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '과금항목명', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '이용금액', name: 'CHGR_NM', width : 150, sortable:false},
		    { label: '할인액', name: 'CHGR_NM', width : 150, sortable:false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "workGrpList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
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

	$("#workGrpGrid1").jqGrid({
		url : '/product/service/serviceMgt/workGrpMng/getWorkGrpListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
		    { label: '할인과금항목명', name: 'SO_NM', width : 100, align:"left", sortable:false},
		    { label: '기준일수', name: 'SVC_WRK_GRP_ID', width : 100, align:"center", sortable:false},
		    { label: '이용일수', name: 'SVC_WRK_GRP_NM', width : 200, align:"left", sortable:false},
		    { label: '이용금액', name: 'USE_YN_NM', width : 100, align:"center", sortable:false},
		    { label: '할인적용방식', name: 'CHGR_NM', width : 150, sortable:false},
			{ label: '할인적용값', name: 'CHGR_NM', width : 150, sortable:false},
			{ label: '할인금액', name: 'CHGR_NM', width : 150, sortable:false},
			{ label: '사용년월', name: 'CHGR_NM', width : 150, sortable:false}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "workGrpList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#workGrpGridPager1",
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedData(rowid);
        },
       	loadComplete : function () {
  	      	$("#workGrpGrid1").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
    	sortable: { update: function(permutation) {
    		$("#workGrpGrid1").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});



	//그리드 화면 재조정
	$(window).resize(function() {
		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#workGrpGrid1").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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


    //초기화 버튼 이벤트
   	$('#initBtn').on('click',function (e) {
	   		if($("#initBtn").hasClass('not-active')){
				return;
			}
    		initBtn();
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

/*
 * 페이지 초기화
 */
function pageInit(){

	btnEnable('initBtn');
	btnDisable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

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
 * 초기화 버튼
 */
function initBtn(){

	btnEnable('initBtn');
	btnEnable('newBtn');
	btnDisable('updateBtn');
	btnDisable('deleteBtn');
	btnDisable('addUserBtn');
	btnDisable('updateWorkUserBtn');
	btnDisable('deleteWorkUserBtn');

	$("#userGrid").clearGridData();
	$("#workGrpUserGrid").clearGridData();


	$('#workGrpIdTxt').val('');
	$('#workGrpIdTxt').addClass('not-active');
    $('#workGrpIdTxt').attr('disabled', true);
    $('#workGrpNmTxt').val('');
	$('#workGrpNmTxt').removeClass('not-active');
    $('#workGrpNmTxt').removeAttr('disabled');
    $("#workGrpSoSel").val('SEL');
    $("#workGrpSoSel").selectmenu('refresh');
	$("#workGrpSoSel").selectmenu('enable');
    $("#workGrpUseYnSel").val('SEL');
    $("#workGrpUseYnSel").selectmenu('refresh');
	$("#workGrpUseYnSel").selectmenu('enable');
	$('#workGrpSoSel-button').focus();
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
			<th><spring:message code="LAB.M07.LAB00003" /></th>
			<td>
				<select id="condSo" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
			</td>
			<th>청구년월</th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<!-- CHANHEE datepicker 에서 month-picker 로 class 수정 -->
						<input type="text" id="searchStatDt" name="searchStatDt"  class="month-picker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>납부계정</th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w120" />
					<input id="condCustNm" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
			<th>고객명</th>
			<td>
				<div class="inp_date w280">
					<input id="condCustNm" type="text" class="w120" />
					<input id="condCustNm" type="text" class="w120" disabled/>
					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch"  href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	</thead>
</table> 

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">고객별할인내역</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>

<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">할인과금항목내역</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid1" class="w100p"></table>
	<div id="workGrpGridPager1"></div>
</div>
<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

