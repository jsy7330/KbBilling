<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {

	// 달력(월) 처리
    settingMonthpicker();
	$('#startYymm').datepicker('setDate', new Date());
    $('#endYymm').datepicker('setDate', new Date());
	
	setBasicInfo(); //기본정보 셋팅
	
	var param = checkInput();
	
	var check = new Object(); //청구마감월 Check를 위해

    //그리드 처리
    $("#beforeAdjRegiditTable").jqGrid({
        url : '/charge/billing/billingAdjust/billingBeforeAdjust/getAdjTgtList.json?',
        datatype : 'json',
        mtype: 'POST',
        postData : param,
        colModel: [
            { label: 'soId' , name: 'soId', hidden:true, width:0},
            { label: 'gubun', name: 'gubun', hidden:true, width:0},
            { label: 'adjNo', name: 'adjNo', hidden:true, width:0},
            { label: 'useYymm', name: 'useYymm', hidden:true, width:0},
            { label: 'prodCmpsId', name: 'prodCmpsId', hidden:true, width:0},
            { label: 'svcCmpsId', name: 'svcCmpsId', hidden:true, width:0},
            { label: 'chrgItmCd', name: 'chrgItmCd', hidden:true, width:0, key:true},
            { label: 'custId', name: 'custId', hidden:true, width:0},
            { label: 'custNm', name: 'custNm', hidden:true, width:0},
            { label: 'adjPrvBillAmt', name: 'adjPrvBillAmt', hidden:true, width:0},
            { label: 'adjAmt', name: 'adjAmt', hidden:true, width:0},
            { label: 'adjAftBillAmt', name: 'adjAftBillAmt', hidden:true, width:0},
            { label: 'adjPt', name: 'adjPt', hidden:true, width:0},
            { label: 'prodCd', name: 'prodCd', hidden:true, width:0},
            { label: 'svcCd', name: 'svcCd', hidden:true, width:0},
            { label: 'exrate', name: 'exrate', hidden:true, width:0},
            { label: 'crncyCd', name: 'crncyCd', hidden:true, width:0},
            { label: 'exrateAplyDt', name: 'exrateAplyDt', hidden:true, width:0},
            { label: 'attrVal', name: 'attrVal', hidden:true, width:0},
            { label: 'ctrtStat', name: 'ctrtStat', hidden:true, width:0},
            { label: 'mnsFlag', name: 'mnsFlag', hidden:true, width:0},
            { label: 'orgAdjApplAmt', name: 'orgAdjApplAmt', hidden:true, width:0},
            { label: 'totAdjApplAmt', name: 'totAdjApplAmt', hidden:true, width:0},
            { label: '<spring:message code="LAB.M01.LAB00050" />', name: 'custNm', width : 120, align:"left"},
            { label: '<spring:message code="LAB.M01.LAB00032" />', name: 'ctrtId', width : 80, align:"center"},
            { label: '<spring:message code="LAB.M07.LAB00186" />', name: 'svcTelNo', width : 80, align:"center"},
            { label: '<spring:message code="LAB.M07.LAB00130" />', name: 'prodNm', width : 120, align:"left"},
            { label: '<spring:message code="LAB.M08.LAB00058" />', name: 'chrgItmNm', width : 120, align:"left"},
            { label: '<spring:message code="LAB.M01.LAB00037" />', name: 'ctrtStatNm', width : 120, align:"center"},
            { label: '<spring:message code="LAB.M01.LAB00235" />', name: 'rateStrtDt', formatter:stringTypeFormatterYYYYMMDD, width : 90, align:"center"},
            { label: '<spring:message code="LAB.M01.LAB00236" />', name: 'termDt', formatter:stringTypeFormatterYYYYMMDD, width : 90, align:"center"},
            { label: '<spring:message code="LAB.M09.LAB00228" />', name: 'adjApplAmt', formatter:"integer", width : 80, align:"right", editable:true
            	,editoptions:{//숫자와 '-'만 입력받을수 있게 처리
                    dataInit: function(element) {
                          $(element).keyup(function(event){
                                var keyValue = event.key; //jquery로 눌려진 값을 가져온다.
                                var str = element.value; // 현재 input태그에 입력된 문자열을 가져온다
                                
                                var regex =  /^[-]?\d*(\.?\d*)$/g;
                                var totalStr = str.replace(/^[-]?\d*(\.?\d*)$/g, ""); // concat
                                
                                if(event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 46){
                                    return true;
                                }else{
                                    if(regex.test(totalStr)){
                                        return;
                                    }else{
                                        alert('<spring:message code="MSG.M07.MSG00134"/>');
                                        element.value = '';
                                    }
                                }
                           })
                    }
                 }
        }
        ],
        viewrecords: true,
        shrinkToFit:false,
        height: 400,
        sortable : false,
        cellEdit : true,
        cellsubmit:'clientArray',
        jsonReader: {
            repeatitems : true,
            root : "adjTgtList"
        },
        rowNum: -1,
        
        onCellSelect : function(rowid, index, contents, event){
        },
        loadComplete : function (data) {
            $("#beforeAdjRegiditTable").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            
            check = data.billClsInfo[0];
        },
        sortable: { update: function(permutation) {
            $("#beforeAdjRegiditTable").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            }
        },
        beforeEditCell : function(rowid, cellname, value, iRow, iCol){
        	
        },
        afterEditCell : function(rowid, cellname, value, iRow, iCol){
        	$("#"+iRow+"_"+cellname).bind('blur',(function(){
        	    $("#beforeAdjRegiditTable").editCell(rowid, 30,false);
        	}
        	));
        
        },
        beforeSaveCell : function(rowid, cellname, value, iRow, iCol){
        	
        },
        beforeSubmitCell : function(rowid, cellname, value, iRow, iCol){
        	
        },
        afterSaveCell : function(rowid, cellname, value, iRow, iCol){
        	
        },
        onSelectCell : function(rowid, celname, value, iRow, iCol){
        	
        }
    });

    //그리드 화면 재조정
    $(window).resize(function() {
        $("#beforeAdjRegiditTable").setGridWidth($('#beforeAdjRegiditGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
    });
    
    //초기화 (재조회)
    $('#popInitBtn').on('click',function (e) {
    	
    	$("#beforeAdjRegiditTable").trigger('reloadGrid');
    });
    
    $('#popUpdateBtn').on('click',function (e) {
    	//유효성 검사
        chkValidation(check);
    });
  
});

function chkValidation(check){
// 	var hopeStart = dateFormatToStringYYYYMM($("#startYymm").val());
// 	var hopeEnd = dateFormatToStringYYYYMM($("#endYymm").val());
	
// 	if(parseInt(hopeStart) > parseInt(hopeEnd)){
// 		alert("시작월이 종료일보다 큽니다.");
// 		$("#startYymm").focus();
// 		return;
// 	}
	
//     if(parseInt(hopeStart) <= parseInt(check.billYymm)){
//     	alert(($("#startYymm").val() + "은 이미 청구되었습니다."));
//     	$("#startYymm").focus();
//     	return;
//     }
    
//     if($("#popAdjRsn").val() == "SEL"){
//     	alert("조정사유를 선택하세요");
//     	$("#popAdjRsn").focus();
//     	return;
//     }
    
//     if($("#popAdjApprover").val() == "SEL"){
//     	alert("승인자를 선택하세요");
//     	$("#popAdjApprover").focus();
//     	return;
//     }

    var gubun = $('#popAdjNo').val();
    alert(gubun);
    var reqAdjList = [];
    var reqAdjIds = $('#beforeAdjRegiditTable').getDataIDs();
    var reqIdx = 0;
    var cnt = 0;
    $.each(reqAdjIds, function(index, value){
        var confValue = $('#beforeAdjRegiditTable').getRowData(value);
        if(confValue.adjApplAmt == confValue.orgAdjApplAmt){
            return true;
        }
        reqAdjList[reqIdx++] = confValue;
        cnt++;
     });
    
    if(cnt == 0){
    	alert("변경사항이 없습니다."); return;
    }
    
    
}

function setBasicInfo(){
	$('#popPymAcnt').val('${billBefAdj.custNm}');
    $('#popApplDttm').val(dateFormatToStringYYYMMDDHHMISS('${billBefAdj.applDttm}'));
    $('#popdcsnProcStat').val('${billBefAdj.dcsnProcStatNm}');
    $('#popAdjReq').val('${billBefAdj.adjApplConts}');
    
	if('${billBefAdj.adjNo}' != null && '${billBefAdj.adjNo}' != ""){
		
		$('#popAdjRsn').val('${billBefAdj.adjResnCd}');
        $('#popAdjRsn').selectmenu();
        $('#popAdjRsn').selectmenu("refresh");
        
        var value = '${billBefAdj.hopeAplyYymm}';
        var yy = value.substr(0,4);
        var mm = value.substr(4,2);
        var yyyymm = yy+"-"+mm+"-"+"01";
        
        $('#startYymm').datepicker('setDate', new Date(yyyymm));
	    $('#endYymm').datepicker('setDate', new Date(yyyymm));
	}
	
	if('${billBefAdj.dcsnProcStat}' == '05' || '${billBefAdj.adjNo}' == null || '${billBefAdj.adjNo}' == ""){
        btnDisable("btnCancel");
    }
}

function checkInput(){
    var param = new Object();
    
    param.adjNo = '${billBefAdj.adjNo}';
    param.adjPt = '${billBefAdj.adjPt}';
    param.clsTskCl = '${billBefAdj.clsTskCl}';
    param.billCycl = '${billBefAdj.billCycl}';
    param.soId = '${billBefAdj.soId}';
    param.pymAcntId = '${billBefAdj.pymAcntId}';
    
    return param;
}

</script>
<!-- 검색부 -->
<div style="width:1000px;" >
	<!-- title -->
	<div class="layer_top">
	    <div class="title"><spring:message code="LAB.M07.LAB00307"/></div>
	     <a href="#" class="close"></a>
	</div>
	<!--// title -->
	<table class="writeview">
	    <colgroup>
	        <col style="width:15%;">
	        <col style="width:35%;">
	        <col style="width:15%;">
	        <col style="width:35%;">
	    </colgroup>
	    <tbody>
	        <tr>
	            <th><spring:message code="LAB.M07.LAB00308"/></th><!-- 신청자 -->
	            <td><input id="popPymAcnt" name="pymAcnt" type="text" class="w245" disabled /></td>
	            
	            <th><spring:message code="LAB.M07.LAB00304"/></th><!-- 신청일시 -->
	            <td><input id="popApplDttm" name="applDttm" type="text" class="w245" disabled /></td>
	        </tr>
	        <tr>
	            <th><spring:message code="LAB.M09.LAB00220"/><span class="dot">*</span></th><!-- 적용월 -->
	            <td>
	                <div class="date_box">
	                    <div class="inp_date w150">
	                        <input type="text" id="startYymm" name="startYymm"  class="monthpicker"/>
	                        <a href="#" class="btn_cal"></a>
	                    </div>
	                    <span class="dash">~</span>
	                    <div class="inp_date w150">
	                        <input type="text" id="endYymm" name="endYymm" class="monthpicker"/>
	                        <a href="#" class="btn_cal"></a>
	                    </div>
	                </div>
	            </td>
	            
	            <th><spring:message code="LAB.M09.LAB00136"/><span class="dot">*</span></th><!-- 조정사유코드 -->
	            <td>
	                <select id="popAdjRsn" class="w100p">
	                    <option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
	                    <c:forEach items="${adjRsn}" var="adjRsn" varStatus="status">
	                        <option value="${adjRsn.commonCd}">${adjRsn.commonCdNm}</option>
	                    </c:forEach>
	                </select>
	            </td>
	        </tr>
	        <tr>
	            <th><spring:message code="LAB.M09.LAB00215"/></th><!-- 진행상태 -->
	            <td><input id="popdcsnProcStat" name="dcsnProcStat" type="text" class="w245" disabled /></td>
	            
	            <%-- <th><spring:message code="LAB.M07.LAB00309"/><span class="dot">*</span></th><!-- 승인자 -->
	            <td>
	                <select id="popAdjApprover" class="w100p">
	                    <option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
	                    <c:forEach items="${adjApprover}" var="adjApprover" varStatus="status">
	                        <option value="${adjApprover.commonCd}">${adjApprover.commonCdNm}</option>
	                    </c:forEach>
	                </select>
	            </td> --%>
	            <th><spring:message code="LAB.M08.LAB00184"/><span class="dot">*</span></th><!-- 조정사유 -->
                <td colspan="3"><input id="popAdjReq" name="adjRsn" type="text" class="W245" /></td>
	        </tr>
	    </tbody>
	</table> 
	<div class="main_btn_box">
	    <div class="fl">
	        <h4 class="sub_title"><spring:message code="LAB.M09.LAB00219"/></h4>
	    </div>
	</div>
	
	<div id='beforeAdjRegiditGrid' class="layer_box">
	    <table id="beforeAdjRegiditTable" class="w100p"></table>
	    <div id="beforeAdjRegiditPager"></div>
	</div>
<!-- <div style="width:700px;" > -->
<!--         title -->
<!--          <div class="layer_top"> -->
<%--              <div class="title"><spring:message code="LAB.M01.LAB00002"/></div> --%>
<!--              <a href="#" class="close"></a> -->
<!--         </div> -->
<!--         // title -->
                                                 
<!--         center -->
<!--         <div class="layer_box"> -->
<!--                 <table id="virAcntGrid" class="w100p"></table> -->
<!--                 <div id="virAcntGridPager"></div> -->
<!--          </div> -->
<!--         footer -->
<!--          <div class="btn_box"> -->
<%--                <ntels:auth auth="${menuAuthU}"> --%>
<%--                <a id='virChngBtn' class="grey-btn" href="#" ><span class="edit_icon"><spring:message code="LAB.M07.LAB00252" /></span></a> --%>
<%--                </ntels:auth> --%>
<%--                <a class="grey-btn close" href="#" ><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a> --%>
<!--         </div> -->
<!-- </div> -->
	<div class="btn_box">
	    <div class="fr">
	        <span id="commonBtn">
                <a href="#" class="grey-btn" id="btnCancel" title='<spring:message code="LAB.M07.LAB00320"/>'><spring:message code="LAB.M07.LAB00320"/></a>
                <ntels:auth auth="${menuAuthU}">
                <a id="popUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
                </ntels:auth>
            </span>
	    </div>
	</div>
</div>'${billBefAdj.adjNo}'
<input id="clsBillYymm" type='text' value="${billClsInfo[0].billYymm}"  hidden />
<input id="popAdjNo" type='text' value="${billBefAdj.adjNo}"  hidden />