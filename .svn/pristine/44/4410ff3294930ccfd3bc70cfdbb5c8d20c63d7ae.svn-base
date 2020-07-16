<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<script type="text/javascript">
$(document).ready(function() {
	//Grid
	$("#excelGrid").jqGrid({
		datatype : 'local',
		colModel: [
					{ label: '사업', name: 'COL0', width : 100, align:"left",sortable:false},
					{ label: '고객ID', name: 'COL1', width : 200, align:"left",sortable:false},
					{ label: '고객명', name: 'COL2', width : 200, align:"left",sortable:false},
					{ label: '고객유형', name: 'COL3', width : 200, align:"left",sortable:false},
					{ label: '우편번호', name: 'COL4', width : 200, align:"left",sortable:false},
					{ label: '기본주소', name: 'COL5', width : 300, align:"left",sortable:false},
					{ label: '상세주소', name: 'COL6', width : 400, align:"left",sortable:false},
					{ label: '연락처', name: 'COL7', width : 200, align:"left",sortable:false, formatter:telNoFormatter},
					{ label: '전화번호', name: 'COL8', width : 200, align:"left",sortable:false, formatter:telNoFormatter},
					{ label: '휴대폰번호', name: 'COL9', width : 200, align:"left",sortable:false, formatter:telNoFormatter},
					{ label: '이메일', name: 'COL10', width : 200, align:"left",sortable:false},
					{ label: '등록일시', name: 'COL11', width : 200, align:"left",sortable:false, formatter: stringTypeFormatterYYYYMMDDHH24MISS}
			],
		data : '',
		viewrecords: true,
		shrinkToFit:false,
		scroll: 1,
		height: 500,
		sortable : false,
		cellsubmit:'clientArray',
		rowNum: -1, 
        onCellSelect : function(rowid, index, contents, event){
        	
        },
       	loadComplete : function (data) {
       		$("#excelGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       	}
	});
	$("#excelGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	
	$(window).resize(function() {
		$("#excelGrid").setGridWidth($('#gridDiv').width(), false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}); 
	
	
   	$('#btnUpload').on('click',function (e) {
   		fileUpload('file');
	});
 	
});


function fileUpload(fileId){
	
	var formData = new FormData();
	
	var file = $("#" + fileId)[0].files[0];
	
	
	$("#excelGrid").clearGridData();
	formData.append("fileName",file);
	formData.append("isUnmaskYn","Y");
	
	
	$.ajax({
		async : true,
		method : "post",
		url : '/system/sample/sample/sample/excelUpload.json',
		processData : false, 
		data : formData,
		contentType : false,
		beforeSend: function(){
			/* var maskHeight = $(document).height();  
			var maskWidth = $(window).width();
			$('#loadingMask').css({'height':maskHeight});
			$('#loadingMask').fadeTo(0,0.1);  
			$('#loadingImage').removeAttr('style'); */
		},
		success : function(data){
		 	$("#excelGrid").setGridParam({
		 		data : data.excelData,
		 		//rowNum:  data.excelData.length
		 		rowNum:  100,
		 		scroll:1
		 		
			});
			$("#excelGrid").trigger("reloadGrid");
			/* $('#loadingMask').hide();  
			$('#loadingImage').css({"display":"none"}); */
		},
     	error : function(err){
    		/* $('#loadingMask').hide();  
    		$('#loadingImage').css({"display":"none"}); */
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
			<span>&gt;</span>${mu.menuName}
		</c:forEach>
	</div>
	<!--// Navigator -->
</div>
	<div class="main_btn_box">
		<div class="fl">
			<h4 class="sub_title">엑셀 업로드 샘플 화면</h4>
		</div>
	</div>
	
	<div class="date_box">
		<div class="w100p">
			<input type="file" id="file" name="file" class="upload-hidden">
			<div class="fr">
				<a href="#" id="btnUpload" class="grey-btn" >Excel Upload</a>
			</div>	
		</div>
		
	</div>
	
	<div id='gridDiv' class='mt10'>
		<table id="excelGrid" class="w100p"></table>
	</div>

	