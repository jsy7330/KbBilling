<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript" src="/scripts/nccbs/jQuery.MultiFile.min.js"></script>
<script type="text/javascript" src="/scripts/nccbs/jQuery.Form.min.js"></script>
<script type="text/javascript"src="/scripts/editor/en/js/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
var popType = "${bulletin.popType}";	//팝업타입

$(document).ready(function() {
	$("#userSearchPop").setGridWidth(988,false);
	var st = $("#popupYn").val();
	$('input:radio[name=popupYnTmp]:input[value=' + st + ']').attr("checked", true); 
	$('input:radio[name=popupYnTmp]').addClass('not-active');
	$('input:radio[name=popupYnTmp]').attr('disabled', true);
	
	$(".btn_cal").addClass('not-active');
	$(".btn_cal").attr('disabled', true);
	$(".search").addClass('not-active');
	$(".search").attr('disabled', true);
	$("#file1").addClass('not-active');
	$("#file1").attr('disabled', true);
	
	var list = document.getElementById("fileList");
	while (list.hasChildNodes()) {   
	    list.removeChild(list.firstChild);
	}
	$('#file1').MultiFile('reset'); //멀티파일 초기화
	
	
	if($('#fileNm').val() != null && $('#fileNm').val() != ''){
		var fileArr = $('#fileNm').val().split(',');
		for(i = 0;i < fileArr.length; i++){
			var fileInfo = fileArr[i].split(':');
			
			var html = "<div class=\"MultiFile-label\" id=\"";
			html = html + fileInfo[1];
			html = html + "\">";
			/* html = html + "<a class=\"MultiFile-remove1\" onclick=\"javascript:deleteFile('";
			html = html + fileInfo[1];
			html = html + "');\"><img src=\"/images/icon/delete.gif\" height=\"16\" width=\"16\" alt=\"x\"></a>"; */
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
	
	
	if(popType == "o"){	//일반팝업일 경우 버튼 닫기 처리
		$("#btnColse1SeP").click(function() {
			window.open("about:blank", "_self").close();
		});
		
		$("#btnClose").click(function() {
			
			window.open("about:blank", "_self").close();
		});
		
	}else{
		$("#btnColse1SeP").click(function() {
			$("#attributeTable").trigger("reloadGrid"); //view_cnt 증가 리로드
		});
		$("#btnClose").click(function() {
			$("#attributeTable").trigger("reloadGrid"); //부모창(main.jsp) view_cnt 증가 리로드
		});
	}
});
function downLoadFile(uuid, fileNm){
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	if(uuid == '' || fileNm == ''){
		alert('<spring:message code="MSG.M03.MSG00003" />');	//다운받을 파일이 없습니다.
		return;
	}
	
	location.href = '/system/common/common/fileMng/getFileAction.json?uuid='+uuid+'&fileNm='+encodeURI(encodeURIComponent(fileNm));
}
</script>
<input type="hidden" id="popupYn" name="popupYn" value="${bulletin.popupYn}"/> 
<input type="hidden" id="fileNm" name="fileNm" value="${bulletin.fileNm}"/> 
<c:if test="${bulletin.popType eq 'o'}"> 
	<div class="Layer_wrap" style="width:930px;" >
</c:if>
<c:if test="${bulletin.popType ne 'o'}"> 
	<div class="w100p" >
</c:if>
<div class="layer_top layer-minw900">
	<div class="title">
		<spring:message code="LAB.M01.LAB00096" />
	</div>
	<a href="#" id="btnColse1SeP" class="close"><spring:message code="LAB.M03.LAB00027" /></a>
</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 45%;">
			<col style="width: 10%;">
			<col style="width: 35%;">
		</colgroup>
	<tbody>
		<tr>
		<th><spring:message code="LAB.M09.LAB00092" /><span class="dot">*</span></th>
		<td><input id="title" name="title" type="text" class="w100p" value="${bulletin.title}"  disabled="disabled"/></td>
		
		<th><spring:message code="LAB.M01.LAB00089" /><span class="dot">*</span></th>
		<td>
			<input  type="text" id="effectStartDt" name="effectStartDt" value="${effectStartDt}" style="width:90px;"readonly="readonly">
			~
		 	<input  type="text" id="effectEndDt" name="effectEndDt" value="${effectEndDt}"style="width:90px;"  readonly="readonly">
		</td>
	</tr>
	<!--
	<tr>
		<th><spring:message code="LAB.M01.LAB00090" /><span class="dot">*</span></th>
		<td>
			<div class="inp_date w100p">
				<input id="userGroupName" name="userGroupName" class="w100p" type="text" disabled="disabled" value="${bulletin.userGroupName}" ></input>
				<input id="userGroupId" name="userGroupId" type="hidden" class="w270" value="${bulletin.userGroupId}" > 
				
			</div>		
		</td>
		<th><spring:message code="LAB.M13.LAB00012" /><span class="dot">*</span></th>
		<td>
			<div class="date_box">
				<input type="radio" id="popupYn-1" name="popupYnTmp" value="Y"checked="checked" /> <label for="popupYn-1">Yes</label> 
				<input type="radio" id="popupYn-2" name="popupYnTmp" value="N" /> <labelfor="popupYn-2">No</label>
			</div>
		</td>
	</tr>
	-->
	<tr>
		<th><spring:message code="LAB.M02.LAB00034" /><span class="dot">*</span></th>
		<td colspan="3" id="contentDiv"><textarea id="content" name="content"  rows="10" style="width:100%; height: 150px;">${bulletin.content}</textarea>
		
		<script type="text/javascript">
			var oEditors = [];
			var lng = '${sessionLanguage}';
			nhn.husky.EZCreator.createInIFrame({
				oAppRef : oEditors,
				elPlaceHolder : "content",
				sSkinURI : "/scripts/editor/" + lng + "/SmartEditor2Skin.html",
				htParams : {
				bUseToolbar : false, // 툴바 사용 여부 (true:사용/ false:사용하지 않음[default:true] )
				bUseVerticalResizer : false, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음[default:true])
				bUseModeChanger : false
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음[default:true])
			},
			//Editor 로딩이 완료된 후 처리될 내용 ex) 수정시 입력한글 내용 삽입가능
			fOnAppLoad : function() {
				//예제 코드
				oEditors.getById["content"].exec("PASTE_HTML", [ "" ]); 
				oEditors.getById["content"].exec("MSG_EDITING_AREA_RESIZE_STARTED", []); //화면 리사이즈
				oEditors.getById["content"].exec("RESIZE_EDITING_AREA_BY", [0, 10]); //화면 리사이즈
				oEditors.getById["content"].exec("MSG_EDITING_AREA_RESIZE_ENDED", []);
				oEditors.getById["content"].exec("DISABLE_WYSIWYG", []);
				
				$('#popup_dialog').css('height', 'auto');
			},
			fCreator : "createSEditor2"
			});
		</script></td>
	</tr>
	<tr>
		<th><spring:message code="LAB.M10.LAB00029" /></th>
		<td colspan="5" rows="10"><!-- <input type="file" id="file1" class="fileBtnCls" name="fileName" /> -->
			<div class="file_down">
				<ul>
					<li><span id='fileList' name="fileList"></span></li>
				</ul>
			</div>
		</td>
	</tr>
</tbody>
</table>
<div class="btn_box">
	<a class="grey-btn close" id="btnClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
<c:if test="${bulletin.popType eq 'o'}"> 
	</div>
</c:if>
<c:if test="${bulletin.popType ne 'o'}"> 
	</div>
</c:if>