<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript" src="/scripts/nccbs/jQuery.MultiFile.min.js"></script>
<script type="text/javascript" src="/scripts/nccbs/jQuery.Form.min.js"></script>

<script type="text/javascript">
(function() {
		
	var lng = '${sessionLanguage}'; 
	document.write('<script type="text/javascript" src="/scripts/editor/' + lng + '/js/HuskyEZCreator.js">\x3C/script>');
		
})();

</script> 

<script type="text/javascript">
$(document).ready(function() {
	pageInit();
	
	
	
	
	$("#bulletinGrid").jqGrid({

		url : '/system/bulletin/bulletinMng/bulletinMng/getBulletinListAction.json',
		mtype : "POST",
		datatype : "json",
		postData : {
			srchTyp : $("#srchTyp").val(),
			srchNm : $('#srchNm').val()
		},
		colModel : [
			{label: 'content',name : 'content',width : 15,hidden:true},
			{label: 'userGroupId',name : 'userGroupId',width : 15,hidden:true},
			{label: 'userGroupName',name : 'userGroupName',width : 15,hidden:true},
			{label: 'fileNm',name : 'fileNm',width : 15,hidden:true},
			{label: 'popupYn',name : 'popupYn',width : 15,hidden:true},
		    {label: '<spring:message code="LAB.M06.LAB00050"/>',name : 'noticeId',width : 20,key: true},
		    {label: '<spring:message code="LAB.M09.LAB00092"/>',name : 'title',width : 110},
			{label: '<spring:message code="LAB.M01.LAB00097"/>',name : 'effectStartDt',width : 30,align:"center",formatter: stringTypeFormatterYYYYMMDD},
			{label: '<spring:message code="LAB.M01.LAB00098"/>',name : 'effectEndDt',width : 30,align:"center",formatter: stringTypeFormatterYYYYMMDD}, 
			{label: '<spring:message code="LAB.M09.LAB00161"/>',name : 'viewCnt',width : 15,align:"center"}
			
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 120,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "bulletinList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 5,
        pager: "#jqGridPager",				
        onCellSelect : function(rowid, index, contents, event){
        	setSelectedDate(rowid);
        },
        loadComplete : function (data) {
       		
       		$("#bulletinGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       	},
    	sortable: { update: function(permutation) {
    		$("#bulletinGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
	});
	$("#bulletinGrid").setGridWidth($('#gridDiv').width(),false);
	
	$(window).resize(function() {
		$("#bulletinGrid").setGridWidth($('#gridDiv').width(),false);//그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	});
	
	$("#btn_add").click(function() {
		goInsertPage();
	});
	$("#btn_search").click(function() {
		goSearch();
	});
	//검색 enter 이벤트
	$( "#srchNm" ).keypress(function(event) {
		var checkR = "<c:out value='${menuAuthR}'/>"; 
		if(checkR == 'N') return;
		
   		if(event.keyCode == 13){
   			goSearch();
   		}
	});
	//초기화 버튼 이벤트
	$('#initBtn').click(function() {
		if($("#initBtn").hasClass('not-active')){
			return;
		}
		initBtn();
	});
	$("#btn_popUp").click(function() {
		var param = new Object(); 
		
		param.userGroupId=$("#userGroupId").val();
		param.userGroupName=$("#userGroupName").val();
		
		
		var url="/system/common/common/userGroupMng/userGroupListPopup.ajax";
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
	
	 var toomuchMsg = '<spring:message code="MSG.M08.MSG00007" />';
	 var toomanyMsg = '<spring:message code="MSG.M10.MSG00013" />';
	 var toobigMsg = '<spring:message code="MSG.M08.MSG00034" />';
	 var duplicateMsg = '<spring:message code="MSG.M08.MSG00051" />';
	 
	  $('#file1').MultiFile({
		  	
	        max: 10, //업로드 최대 파일 갯수 (지정하지 않으면 무한대)
	        maxfile: 10240, //각 파일 최대 업로드 크기
	        maxsize: 102400,  //전체 파일 최대 업로드 크기
	        STRING: { //Multi-lingual support : 메시지 수정 가능
	            remove : '<img src="/images/icon/delete.gif" height="16" width="16" alt="x"/>', //추가한 파일 제거 문구, 이미태그를 사용하면 이미지사용가능
	            toomuch: toomuchMsg, 
	            toomany: toomanyMsg,
	            toobig: toobigMsg,
	            duplicate: duplicateMsg
	        },
		    list : '#fileList' ,
			onFileRemove: function(element, value, master_element) {
			},
			afterFileRemove: function(element, value, master_element) {
			},
			onFileAppend: function(element, value, master_element) {
			},
			afterFileAppend: function(element, value, master_element) {
			},
			onFileSelect: function(element, value, master_element) {
			},
			afterFileSelect: function(element, value, master_element) {
			},
			onFileInvalid: function(element, value, master_element) {
			},
			onFileDuplicate: function(element, value, master_element) {
			},
			onFileTooMany: function(element, value, master_element) {
			},
			onFileTooBig: function(element, value, master_element) {
			},
			onFileTooMuch: function(element, value, master_element) {
			}
	  
	  });
	  
	  
    $('#notice').ajaxForm({
        //cache: false,
        //dataType:"json",
        beforeSubmit: function (data, frm, opt) {
        },
        //submit이후의 처리
        success: function(data, statusText){
     	   
        	oEditors.getById["content"].exec("SET_CONTENTS", [""]); //editor 내용 지우기
          	$("#bulletinGrid").clearGridData();
        	
        	//입력부 초기화
   	      	inputInit('Y');
   	      	
   	      	//버튼 컨트롤
   	      	btnCtrl('I');
   	      	
          	$("#bulletinGrid").setGridParam({
  	   	        postData : {
  	   	     	 	srchTyp : $("#srchTyp").val(),
  	   	      		srchNm : $('#srchNm').val()
  	   			}
          	});
         	alert('<spring:message code="MSG.M09.MSG00005"/>');
          	$("#bulletinGrid").trigger("reloadGrid"); 
        },
        //ajax error
        error: function(e){
        	alert('<spring:message code="MSG.M10.MSG00005"/>');	
        }                               
 	});
	
	//추가 버튼 클릭
	$("#newBtn").click(function() {
		if($("#newBtn").hasClass('not-active')){
			return;
		}
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
		
		if($("#title").val() == "") {
			alert("<spring:message code="MSG.M09.MSG00023" />");
			$("#title").focus();
			return;
		}
		if($("#content").val() == "<p>&nbsp;</p>") {
			alert("<spring:message code="MSG.M02.MSG00008" />");
			$("#content").focus();
			return;
		}
		if($("#userGroupName").val() == "") {
			alert("<spring:message code="MSG.M01.MSG00031" />");
			$("#userGroupName").focus();
			return;
		}
        $("#effectStartDtVal").val(dateFormatToStringYYYYMMDD($('#effectStartDt').val()));
        $("#effectEndDtVal").val(dateFormatToStringYYYYMMDD($('#effectEndDt').val()));
        $("#popupYn").val($("input:radio[name=popupYnTmp]:checked").val());
        $("#notice").attr("action","/system/bulletin/bulletinMng/bulletinMng/bulletinInsertAction");
        $("#notice").submit();
	});

	
	//수정 버튼 클릭
	$("#updateBtn").click(function() {
		if($("#updateBtn").hasClass('not-active')){
			return;
		}
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
		
		if($("#title").val() == "") {
			alert("<spring:message code="MSG.M09.MSG00023" />");
			$("#title").focus();
			return;
		}
		if($("#content").val() == "<p>&nbsp;</p>") {
			alert("<spring:message code="MSG.M02.MSG00008" />");
			$("#content").focus();
			return;
		}
		if($("#userGroupName").val() == "") {
			alert("<spring:message code="MSG.M01.MSG00031" />");
			$("#userGroupName").focus();
			return;
		}
		$("#effectStartDtVal").val(dateFormatToStringYYYYMMDD($('#effectStartDt').val()));
        $("#effectEndDtVal").val(dateFormatToStringYYYYMMDD($('#effectEndDt').val()));
        $("#popupYn").val($("input:radio[name=popupYnTmp]:checked").val());
        $("#notice").attr("action","/system/bulletin/bulletinMng/bulletinMng/bulletinUpdateAction");
        $("#notice").submit();
	});
	
	$("#deleteBtn").click(function() {
		if($("#deleteBtn").hasClass('not-active')){
			return;
		}
		if(confirm('<spring:message code="MSG.M07.MSG00054"/>')==true){
			var bulletin = new FormData();
			bulletin.append("noticeId",$('#noticeId').val());
			$.ajax({
	            url:'/system/bulletin/bulletinMng/bulletinMng/bulletinDeleteAction',
	            type:'POST',
	            data : bulletin,
	            contentType: false,
	            processData: false,
	            success: function(data){
	            	oEditors.getById["content"].exec("SET_CONTENTS", [""]); //editor 내용 지우기
	   	          	$("#bulletinGrid").clearGridData();
	   	        		//입력부 초기화
	   	   	      	inputInit('Y');
	   	   	      	
	   	   	      	//버튼 컨트롤
	   	   	      	btnCtrl('I');
	   	   	      	
	   	          	$("#bulletinGrid").setGridParam({
		   	   	        postData : {
		   	   	     	 	srchTyp : $("#srchTyp").val(),
		   	   	      		srchNm : $('#srchNm').val()
		   	   			}
	   	          	});
	   	         	
	   	        	alert('<spring:message code="MSG.M07.MSG00053"/>');
	   	          	$("#bulletinGrid").trigger("reloadGrid"); 

	            },
	         	beforeSend: function(data){
	         	},
	         	error : function(err){
	         		alert('<spring:message code="MSG.M10.MSG00005"/>');
	         	}
	        });
		}
		
	});
});
	
/*
 * 화면 초기화 함수
 */
function pageInit() {
	var startDate = "<c:out value='${startDate}'/>";
	var endDate = "<c:out value='${endDate}'/>";
	
	$('#effectStartDt').datepicker();
    $('#effectStartDt').datepicker("option", "minDate", stringToDateformatYYYYMMDD(startDate));
    $('#effectStartDt').datepicker("option", "onClose", function ( selectedDate ) {
    	$("#effectEndDt").datepicker( "option", "minDate", selectedDate );
    	$("#effectEndDt").val(selectedDate);
    });
    $("#effectStartDt").val(stringToDateformatYYYYMMDD(startDate));
 
    $('#effectEndDt').datepicker();
    $('#effectEndDt').datepicker("option", "minDate", stringToDateformatYYYYMMDD(startDate));
    $('#effectEndDt').datepicker("option", "onClose", function ( selectedDate ) {
    	$("#effectStartDt").datepicker( "option", "maxDate", selectedDate );
        
    });
    $("#effectEndDt").val(stringToDateformatYYYYMMDD(endDate));
	//검색정보 초기화
	
	$('#srchTyp').val('SEL');
	$('#srchTyp').selectmenu("refresh");
	$('#srchNm').val('');
	$('#srchNm').focus();
	
	$("#authGroupGrid").clearGridData();
	
	//입력부 초기화

	inputInit('N');
}		
		
function inputInit(srch) {
	
	if (srch=='Y') {	//검색조건유지
		$('#srchTyp').val($('#srchTyp').val());
		$('#srchNm').val($('#srchNm').val());
		
	}else{//검색정보 초기화
		
		$('#srchTyp').val('SEL');
		$('#srchTyp').selectmenu("refresh");
		$('#srchNm').val('');
	}
	
	$('.MultiFile-remove').click();
	var list = document.getElementById("fileList");
	while (list.hasChildNodes()) {   
	    list.removeChild(list.firstChild);
	}
	/*
	var fileList = document.getElementById("file1_wrap");
	if(fileList != null){
		for(i=0; i < fileList.childNodes.length; i++){
			var node = fileList.childNodes[i];
			if(node.style.top == '-3000px'){
				fileList.removeChild(node);
			}
		}
	}
	*/
	$('#file1').MultiFile('reset'); //멀티파일 초기화
	
	
	
	$("#title").val('');
	$("#title").addClass('not-active');
	$("#title").attr('disabled', true);
	$("#userGroupId").val('');
	$("#userGroupName").val('');
	$("#userGroupName").addClass('not-active');
	$("#userGroupName").attr('disabled', true);
	$('input:radio[name=popupYnTmp]:input[value=Y]').attr("checked", true); 
	$('input:radio[name=popupYnTmp]').addClass('not-active');
	$('input:radio[name=popupYnTmp]').attr('disabled', true);
	$("#contentDiv").addClass('not-active');
	$("#contentDiv").attr('disabled', true);
	
	$(".btn_cal").addClass('not-active');
	$(".btn_cal").attr('disabled', true);
	$(".search").addClass('not-active');
	$(".search").attr('disabled', true);
	//$("#file1").addClass('not-active');
	//$("#file1").attr('disabled', true);
	$("#submit-btn").addClass('not-active');
	$("#submit-btn").attr('disabled', true);
	//공통 버튼 처리
	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled', true);
	
	if (srch=='Y') {
		$("#initBtn").addClass('grey-btn');
		$("#initBtn").removeClass('white-btn');
		$("#initBtn").removeClass('not-active');
		$("#initBtn").removeAttr('disabled');
	}	
}
function initBtn() {//초기화 버튼 클릭시
	//입력정보 초기화
	inputInit('Y');
	
	$('#title').removeClass('not-active');
	$('#title').removeAttr('disabled');
	$('#contentDiv').removeClass('not-active');
	$('#contentDiv').removeAttr('disabled');
	$('input:radio[name=popupYnTmp]:input[value=Y]').attr("checked", true); 
	$('input:radio[name=popupYnTmp]').removeClass('not-active');
	$('input:radio[name=popupYnTmp]').removeAttr('disabled');
	oEditors.getById["content"].exec("PASTE_HTML", [ "" ]); 
	oEditors.getById["content"].exec("SET_CONTENTS", [""]); 
	$(".btn_cal").removeClass('not-active');
	$(".btn_cal").removeAttr('disabled');
	$(".search").removeClass('not-active');
	$(".search").removeAttr('disabled');
	//$("#file1").removeClass('not-active');
	//$("#file1").removeAttr('disabled');
	$("#submit-btn").removeClass('not-active');
	$("#submit-btn").removeAttr('disabled');
	btnCtrl('N');
	$("buelletinGrid").jqGrid("resetSelection");
	
	var startDate = "<c:out value='${startDate}'/>";
	var endDate = "<c:out value='${endDate}'/>";
    $("#effectStartDt").val(stringToDateformatYYYYMMDD(startDate));
    $("#effectEndDt").val(stringToDateformatYYYYMMDD(endDate));
    
    $('#file1').MultiFile('reset'); //멀티파일 초기화
    
}

function goSearch() {
	$("#bulletinGrid").clearGridData();
	//입력부 초기화
	inputInit('Y');

	//버튼 컨트롤
	btnCtrl('I');

	$("#bulletinGrid").setGridParam({
		postData : {
			srchTyp : $("#srchTyp").val(),
			srchNm : $('#srchNm').val(),
			srchYn:"Y"
		}
	});
	
	$("#bulletinGrid").trigger("reloadGrid");
}
function btnCtrl(mode) {
	//공통 버튼 처리

	$("#commonBtn a").addClass('white-btn');
	$("#commonBtn a").removeClass('grey-btn');
	$("#commonBtn a").addClass('not-active');
	$("#commonBtn a").attr('disabled', true);

	if (mode == 'I') {

		btnEnable('initBtn');

	} else if (mode == 'N') {
		btnEnable('initBtn');
		btnEnable('newBtn');

	} else if (mode == 'U') {
		btnEnable('initBtn');
		btnEnable('updateBtn'); //updateBtn 활설화
		btnEnable('deleteBtn');
	}
}
/*
 * 버튼 비활성화 처리
 */
function btnDisable(id) {
	$('#' + id).addClass('white-btn');
	$('#' + id).removeClass('grey-btn');
	$('#' + id).addClass('not-active');
	$('#' + id).attr('disabled', true);

}

/*
 * 버튼 활성화 처리
 */
function btnEnable(id) {
	$('#' + id).addClass('grey-btn');
	$('#' + id).removeClass('white-btn');
	$('#' + id).removeClass('not-active');
	$('#' + id).removeAttr('disabled');
}
/*
 * 데이터 선택 이벤트 처리
 */
function setSelectedDate(rowId) {
	
	var data = $("#bulletinGrid").getRowData(rowId);
	
	oEditors.getById["content"].exec("SET_CONTENTS", [""]); //editor 내용 지우기
	oEditors.getById["content"].exec("PASTE_HTML", [data.content]); //그리드에서 선택한 값 가져오기
	$("#title").val(data.title);
	$("#effectStartDt").val(data.effectStartDt);
	$("#title").focus();
	
	$("#effectEndDt").val(data.effectEndDt);
    
	$("#content").val(data.content);
	$("#userGroupName").val(data.userGroupName);
	$("#userGroupId").val(data.userGroupId);
	$("#noticeId").val(data.noticeId);
	
	$("#title").removeClass('not-active');
	$("#title").removeAttr('disabled');
	$("#contentDiv").removeClass('not-active');
	$("#contentDiv").removeAttr('disabled');
	$('input:radio[name=popupYnTmp]:input[value='+data.popupYn+']').prop("checked", true); 
	$('input:radio[name=popupYnTmp]').removeClass('not-active');
	$('input:radio[name=popupYnTmp]').removeAttr('disabled');
	$(".search").removeClass('not-active');
	$(".search").removeAttr('disabled');
	$(".btn_cal").removeClass('not-active');
	$(".btn_cal").removeAttr('disabled',true);
	
	
	$("#submit-btn").removeClass('not-active');
	$("#submit-btn").removeAttr('disabled');
	$('.MultiFile-remove').click();
	var list = document.getElementById("fileList");
	while (list.hasChildNodes()) {   
	    list.removeChild(list.firstChild);
	}
	
	/*
	var fileList = document.getElementById("file1_wrap");
	if(fileList != null){
		for(i=0; i < fileList.childNodes.length; i++){
			var node = fileList.childNodes[i];
			if(node.style.top == '-3000px'){
				fileList.removeChild(node);
			}
		}
	}
	
	*/
	$('#file1').MultiFile('reset'); //멀티파일 초기화
	
	
	if(data.fileNm != null && data.fileNm != ''){
		var fileArr = data.fileNm.split(',');
		for(i = 0;i < fileArr.length; i++){
			var fileInfo = fileArr[i].split(':');
			
			var html = "<div class=\"MultiFile-label\" id=\"";
			html = html + fileInfo[1];
			html = html + "\">";
			html = html + "<a class=\"MultiFile-remove1\" onclick=\"javascript:deleteFile('";
			html = html + fileInfo[1];
			html = html + "');\"><img src=\"/images/icon/delete.gif\" height=\"16\" width=\"16\" alt=\"x\"></a>";
			html = html + "<a href=\"#\" onclick=\"javascript:downLoadFile('";
			html = html + fileInfo[1];
			html = html + "','";
			html = html + fileInfo[0];
			html = html + "');\">";
			html = html + "<span class=\"MultiFile-title\" title=\"";
			html = html + fileInfo[0];
			html = html + "\">&nbsp;";
			html = html + fileInfo[0];
			html = html + "</a>";
			$( "#fileList" ).append( html);
		}
	}
	
	btnCtrl('U');
}

function downLoadFile(uuid, fileNm){
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	if(uuid == '' || fileNm == ''){
		alert('<spring:message code="MSG.M03.MSG00003" />');	//다운받을 파일이 없습니다.
		return;
	}
	
	location.href = '/system/common/common/fileMng/getFileAction.json?uuid='+uuid+'&fileNm='+encodeURI(encodeURIComponent(fileNm));
}

function deleteFile(uuid){
	var check = confirm('<spring:message code="MSG.M14.MSG00017" />');
	if(!check){
		return;
	}
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	var rowid  = $("#bulletinGrid").jqGrid('getGridParam', 'selrow');
	var data = $("#bulletinGrid").getRowData(rowid);
	
	
	$.ajax({
        url:'/system/bulletin/bulletinMng/bulletinMng/bulletinDeleteFileAction.json',
        type:'POST',
        data : {
        	uuid : uuid,
        	noticeId : data.noticeId,
          	},
        async: true,
        success: function(data){
        	$("#" + data.uuid).remove();
        	
        	var rowid  = $("#bulletinGrid").jqGrid('getGridParam', 'selrow');
        	var rowData = $("#bulletinGrid").getRowData(rowid);
        	rowData.fileNm = data.fileList;
        	$('#bulletinGrid').jqGrid('setRowData', rowid, rowData);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		alert('<spring:message code="MSG.M10.MSG00005"/>');
     	}
    });
	
}
</script>

<div class="h3_bg">
	<h3>${menuName}</h3>
	<!-- Navigator -->
	<div class="nav">
		<a href="#" class="home"></a>
		<c:forEach items="${naviMenuList}" var="mu" varStatus="status">
			<span>&gt;</span>
			<span>${mu.menuName}</span>
		</c:forEach>
	</div>
	<!--// Navigator -->
</div>

<input id="tmp" name="tmp" type="hidden" value="${bulletin.srchTyp}"/> 
	
<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title"><spring:message code="LAB.M01.LAB00025"/></h4>
		</div>
		<div class="fr mt10">
		<a href="#" class="grey-btn" id="btn_search"><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
</div>
	<table class="writeview">
		<colgroup>
			<col style="width:10%;">
			<col style="width:90%;">
		</colgroup>
		<thead> 
			<tr>
				<th><spring:message code="LAB.M01.LAB00026"/></th>
				<td>
				 	<select name="srchTyp" id="srchTyp" class="w120" >
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<option value="title"><spring:message code="LAB.M09.LAB00092" /></option>
					<option value="content"><spring:message code="LAB.M02.LAB00034" /></option>
					</select> 
					<input id="srchNm" name="srchNm" type="text" class="w70p" value="${bulletin.srchNm}"/>
				</td>
			</tr>
		</thead>
	</table>
	<div class="main_btn_box">
		<div class="fl">
		<h4 class="sub_title"><spring:message code="LAB.M05.LAB00039" /></h4>
		</div>
	</div>
	<div id='gridDiv' class="w100p">
		<table id="bulletinGrid" class="w100p"></table>
		<div id="jqGridPager"></div>
	</div> 


<!-- 입력폼 시작 -->
<div id="bulletinInfo">
	<form id="notice" name="notice" method="post" enctype="multipart/form-data">
		<input type="hidden" id="popupYn" name="popupYn" />
		<input type="hidden" id="flag" name="flag"/>
		<input type="hidden" id="noticeId" name="noticeId"/>
		<div class="main_btn_box">
			<div class="fl">
				<h4 class="sub_title"><spring:message code="LAB.M01.LAB00096" /></h4>
				</div>
		</div>
		<table class="writeview">
			<colgroup>
				<col style="width: 10%;">
				<col style="width: 50%;">
				<col style="width: 10%;">
				<col style="width: 30%;">
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="LAB.M09.LAB00092" /><span class="dot">*</span></th>
					<td><input id="title" name="title" type="text" class="w100p" /></td>
					<th><spring:message code="LAB.M01.LAB00089" /><span class="dot">*</span></th>
					<td>
						<div class="date_box w300">
							<div class="inp_date w135">
								<input  type="text" id="effectStartDt" name="effectStart" value="" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a> 
								<input  type="text" id="effectStartDtVal" name="effectStartDt" hidden> 
							</div>
							<span class="dash">~</span>	
							<div class="inp_date w135">
								<input  type="text"  id="effectEndDt" name="effectEnd" value="" class="datepicker" readonly="readonly"><a href="#" class="btn_cal"></a>
								<input  type="text"  id="effectEndDtVal" name="effectEndDt" hidden>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th><spring:message code="LAB.M01.LAB00090" /><span class="dot">*</span></th>
					<td>
						<div class="inp_date w100p">
							<input id="userGroupName" name="userGroupName" class="w100p" type="text"></input>
							<input id="userGroupId" name="userGroupId" type="hidden" > 
							<a href="#" id="btn_popUp" class="search" ><spring:message code="LAB.M09.LAB00158"/></a>
						</div>		
					</td>
					<th><spring:message code="LAB.M13.LAB00012" /><span class="dot">*</span></th>
					<td>
						<div class="date_box">
							<input type="radio" id="popupYn-1" name="popupYnTmp" value="Y" checked="checked" /> <label for="popupYn-1">Yes</label> 
							<input type="radio" id="popupYn-2" name="popupYnTmp" value="N" /> <label for="popupYn-2">No</label>
						</div>
					</td>
				</tr>
				<tr>
					<th><spring:message code="LAB.M02.LAB00034" /><span class="dot">*</span></th>
					<td colspan="3" id="contentDiv">
						<textarea id="content" name="content" style="width:100%"></textarea>
					
						<script type="text/javascript">
							var oEditors = [];
							var lng = '${sessionLanguage}';
							nhn.husky.EZCreator.createInIFrame({
								oAppRef : oEditors,
								elPlaceHolder : "content",
								sSkinURI : "/scripts/editor/" + lng +"/SmartEditor2Skin.html",
								htParams : {
								bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음[default:true] )
								bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음[default:true])
								bUseModeChanger : true
							// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음[default:true])
							},
							//Editor 로딩이 완료된 후 처리될 내용 ex) 수정시 입력한글 내용 삽입가능
							fOnAppLoad : function() {
								//예제 코드
								oEditors.getById["content"].exec("PASTE_HTML", [ "" ]);
								oEditors.getById["content"].exec("MSG_EDITING_AREA_RESIZE_STARTED", []); //화면 리사이즈
								oEditors.getById["content"].exec("RESIZE_EDITING_AREA", [0, 150]);
								oEditors.getById["content"].exec("MSG_EDITING_AREA_RESIZE_ENDED", []); 
							},
							fCreator : "createSEditor2"
							});
						</script>
					</td>
				</tr>
				<tr>
					<th><spring:message code="LAB.M10.LAB00029" /></th>
					<td colspan="5"><input type="file" id="file1" class="fileBtnCls" name="fileName" />
						<div class="file_down">
							<ul>
								<li><span id='fileList' name="fileList"></span></li>
							</ul>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<div class="main_btn_box">
	<div id="commonBtn" class="fl">
		<%-- <ntels:auth auth="${menuAuthP}">
		<a class="grey-btn" href="#" title='<spring:message code="LAB.M10.LAB00079"/>'><span class="printer_icon"><spring:message code="LAB.M10.LAB00079"/></span></a>
		</ntels:auth> --%>
		</div>
		<div id="commonBtn" class="fr">
			<a id="initBtn" class="grey-btn" title='<spring:message code="LAB.M10.LAB00050"/>' href="#"><span class="re_icon"><spring:message code="LAB.M10.LAB00050"/></span></a>
		<ntels:auth auth="${menuAuthC}">
		<a id="newBtn" class="grey-btn" title='<spring:message code="LAB.M03.LAB00075"/>' href="#"><span class="write_icon"><spring:message code="LAB.M03.LAB00075"/></span></a>
		</ntels:auth>
		<ntels:auth auth="${menuAuthU}">
		<a id="updateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
		</ntels:auth>
		<ntels:auth auth="${menuAuthD}">
		<a id="deleteBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00082"/>' href="#"><span class="trashcan_icon"><spring:message code="LAB.M07.LAB00082"/></span></a>
		</ntels:auth> 
	</div>
</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;width:560px;" >
</div>

