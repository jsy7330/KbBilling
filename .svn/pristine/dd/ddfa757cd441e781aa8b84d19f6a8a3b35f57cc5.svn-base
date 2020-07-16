<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

$(document).ready(function() {
	
	var mode = "${mode}";
	var paramZipCd = "${paramZipCd}";	
	var paramBaseAddr = "${paramBaseAddr}";
	var paramAddrDtl = "${paramAddrDtl}";
	
	if(mode == 'o'){
		$("#btnColse1SeP").click(function() {
			window.open("about:blank", "_self").close();
		});
		
		$("#btnClose").click(function() {
			window.open("about:blank", "_self").close();
		});
	}
	
	//그리드 처리
	$("#postGrid").jqGrid({
		url : '/system/common/common/postMng/postListAction.json',
		datatype : 'json',
		mtype: 'POST',
		postData : {
			srchNm : $("#srchNm").val(),
			srchBldg :$("#srchBldg").val(),
			str1:$("#str1").val(),
			str2:$("#str2").val(),
			str3:$("#str3").val(),
			str4:$("#str4").val(),
			str5:$("#str5").val(),
			str6:$("#str6").val(),
			str7:$("#str7").val(),
			mode:mode
		},
		colModel: [ 
		     	{ label: '<spring:message code="LAB.M03.LAB00071" />', name: 'address', width : 200, align:"left",sortable:false},
			    { label: '<spring:message code="LAB.M08.LAB00087" />', name: 'zipCd', width : 40, align:"center",sortable:false}
		],
		viewrecords: true,
		//shrinkToFit:false,
		height: 240,
		sortable:true,
		jsonReader: {
			repeatitems : true,
			root : "postList",
			records : "totalCount", //총 레코드 
			total : "totalPages",  //총페이지수
			page : "page"          //현재 페이지
		},
		rowList:[5,10,20,30,50],	//선택시 노출되는 row 수
        rowNum: 10,
        pager: "#postGridPager",
        ondblClickRow : function(id){ 
        	if(mode == "m"){
    		   $("#"+paramZipCd).val($("#postGrid").getRowData(id).zipCd);
    		   $("#"+paramBaseAddr).val($("#postGrid").getRowData(id).address);
    		   $("#"+paramAddrDtl).focus();	//클릭했을 경우 dtl에 포커스
    		   $('#mask, .Layer_wrap').hide();
				$('.Layer_wrap').width('');//custom_170309 @lee
        	}else if(mode == "o"){
    		   $("#"+paramZipCd, opener.document).val($("#postGrid").getRowData(id).zipCd);
    		   $("#"+paramBaseAddr,opener.document).val($("#postGrid").getRowData(id).address);
    		   $("#"+paramAddrDtl).focus();	//클릭했을 경우 dtl에 포커스
    		   window.open("about:blank", "_self").close();
    		   $("#"+paramAddrDtl).focus();	//클릭했을 경우 dtl에 포커스
        	}
        	
 
		  
		},
       	loadComplete : function (data) {
       		$("#postGrid").setGridWidth($("#postGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
       		$("#srchNm").focus();
		},
		sortable: { update: function(permutation) {
    		$("#postGrid").setGridWidth($('#postGridDiv').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    		}
    	}
    });
	if(mode == "o"){
		$("#postGrid").setGridWidth($("#postGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		//그리드 화면 재조정
		$(window).resize(function() {
			$("#postGrid").setGridWidth($("#postGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
		});
	}else{
		$("#postGrid").setGridWidth($("#postGridDiv").width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	}

	
	$( "#srchNm" ).keypress(function(event) {
   		if(event.keyCode == 13){
   			searchCount();
   		}
   	});
		
	$( "#srchBldg" ).keypress(function(event) {
   		if(event.keyCode == 13){
   			searchCount();
   		}
   	});
	
});
function search(){
	searchCount();

}

function searchCount(){
	var mode = "${mode}";
	//trim 해서  length 체크 추가
	 var rtnStr = $.trim($('#srchNm').val());

	if(rtnStr.length==0){
		alert('<spring:message code="MSG.M03.MSG00024"/>');
		return;
	}
	var postMng='srchNm=' + $('#srchNm').val();
	postMng=postMng + '&srchBldg=' + $('#srchBldg').val();
	
	$.ajax({
           url:'/system/common/common/postMng/getSearchCountAction.json',
           type:'POST',
           data : postMng,
           dataType: 'json',
           success: function(data){
        	   	$("#postGrid").clearGridData();
        	    $("#str1").val(data.str1);
          		$("#str2").val(data.str2);
          		$("#str3").val(data.str3);
          		$("#str4").val(data.str4);
          		$("#str5").val(data.str5); 
          		$("#str5").val(data.str5); 
          		$("#str5").val(data.str5); 
          		$("#str6").val(data.str6); 
          		$("#str7").val(data.str7); 
          		if(data.str1 == '' && data.str2 == '' && data.str3 == '' && data.str4 == '' && data.str5 == '' && data.str6 == '' && data.str7 == ''){
          			alert('<spring:message code="MSG.M07.MSG00113"/>');
      				return;
           		}
          		$("#postGrid").setGridParam({
          			postData : {
          				srchNm : $("#srchNm").val(),
          				srchBldg :$("#srchBldg").val(),
          				str1:$("#str1").val(),
          				str2:$("#str2").val(),
          				str3:$("#str3").val(),
          				str4:$("#str4").val(),
          				str5:$("#str5").val(),
          				str6:$("#str6").val(),
          				str7:$("#str7").val(),
          				mode:mode
          			}
          		});
          		$("#page").val("1");
          		$("#postGrid").trigger("reloadGrid");
            },
        	beforeSend: function(data){
        	},
        	error : function(err){
        		alert('<spring:message code="MSG.M09.MSG00020"/>');
        	}
       });
	
}
</script>
<input type="hidden" id="str1" name="str1"/>
<input type="hidden" id="str2" name="str2"/>
<input type="hidden" id="str3" name="str3"/>
<input type="hidden" id="str4" name="str4"/>
<input type="hidden" id="str5" name="str5"/>
<input type="hidden" id="str6" name="str6"/>
<input type="hidden" id="str7" name="str7"/>
<!-- title -->
<div class="layer_top">
     <div class="title"><spring:message code="LAB.M09.LAB00192"/></div>
     <a href="#" id="btnColse1SeP" class="close"></a>
</div>
<div class="layer_box">
	<div class="btn_box">
		<div class="fr mt10">
			<a href="javascript:search();" id="btn_srch" class="grey-btn" title='<spring:message code="LAB.M09.LAB00158"/>'>
			<span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a>
		</div>
	</div>
	<table class="writeview">
		<colgroup>
			<col style="width: 20%;">
			<col style="width: 40%;">
			<col style="width: 20%;">
			<col style="width: 20%;">
		</colgroup>
		<thead>
			<tr id="cond">
				<th title='<spring:message code="LAB.M03.LAB00070"/>'><spring:message code="LAB.M03.LAB00070"/><span class="dot">*</span></th>
				<td>
					<input type="text" id="srchNm" name="srchNm" class="w100p" placeholder="서울시 종로구 세종대로 175 또는 세종문화회관"/>
				</td> 
				<th title="<spring:message code='LAB.M01.LAB00021'/>"><spring:message code="LAB.M01.LAB00021"/></th>
				<td>
					<input type="text" id="srchBldg" name="srchBldg" class="w100p" placeholder="세종문화회관"/>
				</td> 
			</tr>
		</thead>
	</table>
	<!-- center -->
	<div id="postGridDiv" class="w755">
		<table id="postGrid"  class="w100p"></table>
		<div id="postGridPager"></div>
	</div>
	<!-- footer -->
	 <div class="btn_box">
	       <a  id="btnClose" class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
	</div>
</div>
