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
		})
	}
	
	$('#searchStatDt').datepicker('setDate', new Date());

	//그리드 처리
	$("#workGrpGrid").jqGrid({
		url : '/charge/charge/batch/batchjobMng/finishInfoMngListAction.json',
		datatype : 'local',
		mtype: 'POST',
		postData : {
		},
		colModel: [
		    { label: 'soId', name: 'SO_ID', width : 100, align:"center", hidden:true},
		    { label: 'useYn', name: 'USE_YN', width : 100, align:"center", hidden:true},
		    { label: 'billCycl', name: 'BILL_CYCL', width : 100, align:"center", hidden:true},
		    { label: 'clsTskCl', name: 'CLS_TSK_CL', width : 100, align:"center", hidden:true},
		    { label: '청구년월', name: 'BILL_YYMM', width : 100, align:"center", sortable:false, formatter:stringTypeFormatterYYYYMM},
		    { label: '마감업무구분', name: 'CLS_TSK_CL_NM', width : 100, align:"center", sortable:false},
		    { label: '마감상태', name: 'CLS_YN', width : 200, align:"center", sortable:false},
		    { label: '마감일자', name: 'CLS_DT', width : 100, align:"left", sortable:false},
		    { label: '등록자', name: 'REGR_NM', width : 150, sortable:false, align:"center"},
		    { label: '등록일', name: 'REG_DATE', align:"center", width : 150, sortable:false, formatter: dateTypeFormatterYYYYMMDDHH24MISS},
		    { label: '변경자', name: 'CHGR_NM', width : 150, sortable:false, align:"center"},
		    { label: '변경일자', name: 'CHG_DATE', align:"center", width : 150, sortable:false, formatter: dateTypeFormatterYYYYMMDDHH24MISS}
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "finishInfo",
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

	//그리드 화면 재조정
	$(window).resize(function() {
		$("#workGrpGrid").setGridWidth($('#gridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#userGrid").setGridWidth($('#userGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		$("#workGrpUserGrid").setGridWidth($('#workGrpUserGridDiv').width()-3,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
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

    //수정 버튼 이벤트
    $('#updateBtn').on('click',function (e) {
      	if($("#updateBtn").hasClass('not-active')){
          return;
  		  }
    		updateBtn();
		  }
    );
});

/*
 * 페이지 초기화
 */
function pageInit(){

	btnEnable('initBtn');
	btnDisable('updateBtn');

	$("#workGrpGrid").clearGridData();

	$('#soId').val('SEL');
	$('#soId').addClass('not-active');
	$("#soId").attr('disabled',true);
	$("#soId").selectmenu('refresh');
	
	$('#clsTskCl').val('SEL');
	$('#clsTskCl').addClass('not-active');
	$("#clsTskCl").attr('disabled',true);
	$("#clsTskCl").selectmenu('refresh');
	
	$('#billYymm').val('');
	$('#billYymm').attr('disabled',true);
	
	$("#clsDt").val('');
	$('#clsDt').attr('disabled',true);

	$("input:radio[name='mstrFl']:radio[value='1']").prop('checked', true);
	$("input[name='mstrFl']").attr('disabled',true);
}

/*
 * 작업그룹조회
 */
function searchWorkGrpList(){

	pageInit();
	if($("#condSo").val() == "SEL"){
		alert("사업을 선택하세요");
		return;
		
	}else if($("#searchStatDt").val() == ""){
		alert("청구년월을 입력해 주세요");
		return;
		
	}
	
	$("#workGrpGrid").setGridParam({
		mtype: 'POST',
		datatype : 'json',
  	    postData : {
  			comdSoId : $('#condSo').val(),
        	condBillYymm : $('#searchStatDt').val().replace('-',''),
        	condEndCd : $('#condEndCd').val()
		}
	});
	      
   	$("#workGrpGrid").trigger("reloadGrid");	
}

/*
 * 작업그룹 선택 이벤트
 */

function setSelectedData(rowId){
	var data = $("#workGrpGrid").getRowData(rowId);

	$('#soId').val(data.SO_ID);
	$("#soId").selectmenu('refresh');
	
	$('#clsTskCl').val(data.CLS_TSK_CL);
	$("#clsTskCl").selectmenu('refresh');
	
	$('#billYymm').val(data.BILL_YYMM);
	$("#clsDt").val(data.CLS_DT);

	$('#clsDt').removeAttr('disabled');
	$("#billCycl").val(data.BILL_CYCL);
	
	$("input[name='mstrFl']").attr('disabled',false);
	
	if(data.CLS_YN == 'N'){
		$("input:radio[name='mstrFl']:radio[value='1']").prop('checked', true);
	}else{
		$("input:radio[name='mstrFl']:radio[value='2']").prop('checked', true);
	} 
	
	$("#orgMstrFl").val(data.CLS_YN);
	
	$("#mstrFl").removeAttr('disabled');
	$("#mstrFl2").attr('disabled');
	
	btnEnable('initBtn');
	btnEnable('updateBtn');
}

/*
 * 초기화 버튼
 */
function initBtn(){

	btnEnable('initBtn');
	btnDisable('updateBtn');

	
	$('#soId').val('SEL');
	$('#soId').addClass('not-active');
	$("#soId").attr('disabled');
	$("#soId").selectmenu('refresh');
	
	$('#clsTskCl').val('SEL');
	$('#clsTskCl').addClass('not-active');
	$("#clsTskCl").attr('disabled');
	$("#clsTskCl").selectmenu('refresh');
	
	$('#billYymm').val('');
	$("#clsDt").val('');
	
	$("#billYymm").attr('disabled');
	$("#clsDt").attr('disabled');

	
	$("input:radio[name='mstrFl']:radio[value='1']").prop('checked', true);
	$("input[name='mstrFl']").attr('disabled',true);
	
}

/*
 * 수정처리
 */
function updateBtn(){

	var mstrFl = $('input[name="mstrFl"]:checked').val();
	var orgMstrFl = $("#orgMstrFl").val();
	var clsYnVal = "";
	
	if(mstrFl == null || mstrFl == ""){
		$("input[name='mstrFl']").focus();
		var item = '마감상태';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
	}else{
		if(orgMstrFl == 'Y' ){
			if(mstrFl == '1'){
				$("input[name='mstrFl']").focus();
				var item = '마감후에는 마감전 상태로 변경이 불가합니다.';
				alert(item);
				return;
			}else{
				$("input[name='mstrFl']").focus();
				var item = '마감후에는 청구년월 변경이 불가합니다.';
				alert(item);
				return;
			}
		}	
		if(mstrFl == '1'){
			clsYnVal ="N";
		}else{
			clsYnVal ="Y";
		}
	}
	
	//마감일자
	var clsDt = $("#clsDt").val();
	if(clsDt == null || clsDt.length == 0){
		$("#clsDt").focus();
		var item = '마감일자 ';
		alert('<spring:message code="MSG.M13.MSG00025" arguments="' + item + '"/>');
		return;
	}

	
	console.log(clsYnVal+"마감구분");
    var url = '/charge/charge/batch/batchjobMng/updatefinishInfoMng.json';
	$.ajax({
		url:url,
		type:'POST',
		data : {
			 clsDt : $('#clsDt').val()
		 	,billYymm : $("#billYymm").val().replace('-','')
		 	,billCycl : $("#billCycl").val()
		 	,soId : $("#soId").val()
		 	,clsYn : clsYnVal
		 	,clsTskCl : $("#clsTskCl").val() 
			},
		dataType: 'json',
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	searchWorkGrpList();
        },
       	beforeSend: function(data){
       	},
       	error : function(err){
       		ajaxErrorCallback(err);
       	}
    });
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
			<th>청구년월<span class="dot">*</span></th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="searchStatDt" name="searchStatDt"  class="month-picker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>마감구분</th>
			<td colspan="3">
				<select id="condEndCd" class="w40p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${finishTp}" var="finishTp" varStatus="status">
						<option value="${finishTp.commonCd}">${finishTp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</thead>
</table> 

<!--작업그룹표시부 -->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">마감정보</h4>
	</div>
</div>
<div id='gridDiv'>
	<table id="workGrpGrid" class="w100p"></table>
	<div id="workGrpGridPager"></div>
</div>

<!--작업그룹상세-->
<div class="main_btn_box">
	<div class="fl">
		<h4 class="sub_title">마감정보설정</h4>
	</div>
</div>
<table class="writeview">
	<colgroup>
		<col style="width:20%;">
		<col style="width:30%;">
		<col style="width:20%;">
		<col style="width:30%;">
	</colgroup>
	<thead>
		<tr>
			<th>사업<span class="dot">*</span></th>
			<td>
				<select id="soId" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
					</c:forEach>
				</select>
				<input type="hidden" id="billCycl" name="billCycl"/>
			</td>
			<th>청구년월<span class="dot">*</span></th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="billYymm" name="billYymm"  class="month-picker" readonly="readonly" />
						<!-- <a href="#" class="btn_cal"></a> -->
					</div>
				</div>			
			</td>
		</tr>
		<tr>
			<th>마감구분<span class="dot">*</span></th>
			<td>
				<select id="clsTskCl" class="w100p">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${finishTp}" var="finishTp" varStatus="status">
						<option value="${finishTp.commonCd}">${finishTp.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<th>마감일자<span class="dot">*</span></th>
			<td>
				<div class="date_box">
					<div class="inp_date w130">
						<input type="text" id="clsDt" name="condEndDt"  class="month-picker" readonly="readonly" />
						<a href="#" class="btn_cal"></a>
					</div>
				</div>	
			</td>
		</tr>
		<tr>
			<th>마감상태<span class="dot">*</span></th>
			<td colspan="3">
				<div class="inp_date w280">
					<div class="date_box">
						<input type="radio" id="mstrFl" name="mstrFl" value="1"checked="checked" />
							<label for="mstrFl">마감전</label>
						<input type="radio" id="mstrFl2" name="mstrFl" value="2" /> 
						<label for="mstrFl2"> 마감후</label>
						<input type="hidden" id="orgMstrFl" name="orgMstrFl"/>
					</div>
				</div>
			</td>
		</tr>
	</thead>
</table> 
<div class="main_btn_box">
	<div class="fr">
		<span id="commonBtn">
			<ntels:auth auth="${menuAuthR}">
			<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
			</ntels:auth>
			<ntels:auth auth="${menuAuthU}">
			<a id="updateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
			</ntels:auth>
		</span>
	</div>
</div>



<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>

