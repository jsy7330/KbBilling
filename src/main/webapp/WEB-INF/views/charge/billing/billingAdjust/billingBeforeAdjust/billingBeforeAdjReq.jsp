<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<style type="text/css">
table.ui-datepicker-calendar { display:none; }
</style>
<script type="text/javascript">
$(document).ready(function() {
    setBasicInfo(); //기본정보 셋팅
    
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
	        }
		 	,
	        beforeShow : function (dateText, inst) {
	            var selectDate = $(this).val().split("-");
	            var year = Number(selectDate[0]);
	            var month = Number(selectDate[1]) - 1;
	            $(this).datepicker( "option", "defaultDate", new Date(year, month, 1) );
	            $(this).datepicker('setDate', new Date(year, month, 1));
	            
	        }
	    }
	 )
	}
	
	//$('#popApplyMonth').datepicker('setDate', new Date());
	
    var param = checkInput();

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
            { label: 'chrgItmCd', name: 'chrgItmCd', hidden:true, width:0},
            { label: 'custId', name: 'custId', hidden:true, width:0},
            { label: 'attrVal', name: 'attrVal', hidden:true, width:0},
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
            { label: '<spring:message code="LAB.M07.LAB00186" />', name: 'svcTelNo', width : 100, align:"center"},
          //  { label: '<spring:message code="LAB.M07.LAB00021" />', name: 'useYymm', formatter:dateFormatToStringYYYYMM, width : 150, align:"center"},
            { label: '<spring:message code="LAB.M07.LAB00130" />', name: 'prodNm', width : 150, align:"left"},
            { label: '서비스명', name: 'svcNm', width : 120, align:"left"},
            { label: '요금항목', name: 'chrgItmNm', width : 120, align:"left"},
            { label: '계약상태', name: 'ctrtStatNm', width : 120, align:"center"},
          	{ label: '과금개시일', name: 'rateStrtDt',  width : 120, align:"center", formatter:stringToDateformatYYYYMMDD},
          	{ label: '과금종료일', name: 'rateEndDt', width : 180, align:"center", formatter:stringToDateformatYYYYMMDD},
            { label: '조정신청금액', name: 'adjApplAmt', formatter:'integer', width : 120, align:"right" , formatter:numberAutoFormatter}
        ],
        viewrecords: true,
        shrinkToFit:false,
        height: 200,
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
           // $("#beforeAdjRegiditTable").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#beforeAdjRegiditTable").setGridWidth($('#beforeAdjRegiditGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
        },
        sortable: { update: function(permutation) {
            //$("#beforeAdjRegiditTable").setGridWidth(988,false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            $("#beforeAdjRegiditTable").setGridWidth($('#beforeAdjRegiditGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
            }
        },
        afterEditCell : function(rowid, cellname, value, iRow, iCol){
            
        },
        afterEditCell : function(rowid, cellname, value, iRow, iCol){
            $("#"+iRow+"_"+cellname).bind('blur',(function(){
                $("#beforeAdjRegiditTable").editCell(rowid, 30,false);
            }
            ));
        
        },
        afterSaveCell : function(rowid, cellname, value, iRow, iCol){
            
        },
        afterSubmitCell : function(rowid, cellname, value, iRow, iCol){
            
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
        chkValidation();
    });
    
    $('#btnCancel').on('click',function (e) {
        chkCancel();
    });
  
});

function chkCancel(){
	if('${billBeforeAdj.adjNo}' == null && '${billBeforeAdj.adjNo}' ==""){
        alert('<spring:message code="MSG.M09.MSG00066" />');
        return;
    }
	
	if('${billBeforeAdj.dcsnProcStat}' == '05'){
		alert('<spring:message code="MSG.M09.MSG00066" />');
		return;
	}
	
	var cancelAdjNo = new Object();
	
	cancelAdjNo.adjNo = '${billBeforeAdj.adjNo}';
	
	var check = confirm('<spring:message code="MSG.M10.MSG00030" />');
	
	if(check){	
		$.ajax({
		       url:'/charge/billing/billingAdjust/billingBeforeAdjust/cancelAdjList.json',
		       type:'POST',
		       data : JSON.stringify(cancelAdjNo),
		       dataType: 'json',
		       contentType : "application/json; charset=UTF-8",
		       success: function(data){
		           alert('<spring:message code="MSG.M07.MSG00084"/>');
		           
					$("#beforeAdjTable").trigger("reloadGrid");
		        	$("#btnClose").trigger('click');
		       },
		       beforeSend: function(data){
		       },
		       error : function(err){
		           alert('<spring:message code="MSG.M10.MSG00005"/>');
		       }
		   });
	}
}


function chkValidation(){
	
	if('${pymRcpt[0].pymRcptAmt}' > 0){
        alert('<spring:message code="MSG.M07.MSG00129" />'); return;
    }
	
	if($('#popAdjRsn').val() == 'SEL'){
        alert('조정사유를 선택하세요'); return;
    }
    
    if($('#popAdjReq').val() == '' || $('#popAdjReq').val() == null){
        alert('조정신청내역를 입력하세요'); 
        $('#popAdjReq').focus();
        return;
    }
    
    //조정 데이터
    var adjustVO = new Object();
    var adj = new Object();
    adj.adjNo     = '${billBeforeAdj.adjNo}';
    adj.billYymm  = '${billBeforeAdj.billYymm}';
    adj.billCycl  = '${billBeforeAdj.billCycl}';
    adj.billDt    = '${billBeforeAdj.billDt}';
    adj.billSeqNo = '${billBeforeAdj.billSeqNo}';
    adj.pymAcntId = '${billBeforeAdj.pymAcntId}';
    adj.pymAcntNm = '${billBeforeAdj.pymAcntNm}';
    adj.soId      = '${billBeforeAdj.soId}';
    adj.adjPt     = '2'; //청구후 요금조정
    adj.adjResnCd = $('#popAdjRsn').val();
    adj.saleAplyYn = 'N';
    adj.adjApplConts = $('#popAdjReq').val();
    
    adjustVO.adj = adj;
    
    //조정상세 데이터
    var reqAdjDtlList = [];
    var reqAdjIds = $('#beforeAdjRegiditTable').getDataIDs();
    var reqIdx = 0;
    
    var isChanged = 0;
    $.each(reqAdjIds, function(index, value){
        var confValue = $('#beforeAdjRegiditTable').getRowData(value);
        //0인놈은 제외        
        if(confValue.adjApplAmt ==  0){
        	return true;
        }
        
        confValue.useYymm = dateFormatToStringYYYYMM(confValue.useYymm);
        reqAdjDtlList[reqIdx++] = confValue;
        
        //기존 금액과 조정금액 비교
        if(confValue.adjApplAmt != confValue.orgAdjApplAmt){
        	isChanged++;
        }
        
     });
    
    if($('#popAdjReq').val() != '${billBeforeAdj.adjApplConts}'){
    	isChanged++;
    }
    
    if($('#popAdjRsn').val() != '${billBeforeAdj.adjResnCd}'){
    	isChanged++;
    }
    //alert(isChanged); return;
    if(isChanged == 0){
        alert('<spring:message code="MSG.M06.MSG00026"/>'); return;
    }
    
    adjustVO.adjDtl = reqAdjDtlList;
    
    if('${billBeforeAdj.adjNo}' == '' || '${billBeforeAdj.adjNo}' == null){
    	adjustVO.gubun = 'I';
    }else{
    	adjustVO.gubun = 'U';
    }
    
    $.ajax({
        url:'/charge/billing/billingAdjust/billingBeforeAdjust/modAdjTgtList.json',
        type:'POST',
        data : JSON.stringify(adjustVO),
        dataType: 'json',
        contentType : "application/json; charset=UTF-8",
        success: function(data){
        	alert('<spring:message code="MSG.M07.MSG00084"/>');
        	
        	$("#beforeAdjTable").trigger("reloadGrid");
        	$("#btnClose").trigger('click');
        	
        },
        beforeSend: function(data){
        },
        error : function(err){
        	alert('<spring:message code="MSG.M10.MSG00005"/>');
        }
    });
      
}

function setBasicInfo(){
    $('#popPymAcnt').val('${billBeforeAdj.pymAcntNm}');
    $('#popApplDttm').val('${billBeforeAdj.applDttm}');
    $('#popApplyMonth').val(stringToDateformatYYYYMM('${billBeforeAdj.billYymm}'));
    $('#popdcsnProcStat').val('${billBeforeAdj.dcsnProcStatNm}');
    $('#popAdjReq').val('${billBeforeAdj.adjApplConts}');
    //수납내역체크
    if('${pymRcpt[0].pymRcptAmt}' > 0){
    	$('#popPay').val('Y');
    	
    	//btnDisable("popUpdateBtn");
    }else{
    	$('#popPay').val('N');
    }
    
    if('${billBeforeAdj.adjNo}' != null && '${billBeforeAdj.adjNo}' != ""){
    	//alert(1);
    	$('#popAdjRsn').val('${billBeforeAdj.adjResnCd}');
    	$('#popAdjRsn').selectmenu();
        $('#popAdjRsn').selectmenu("refresh");
    }
    
    if('${billBeforeAdj.dcsnProcStat}' == '05' || '${billBeforeAdj.adjNo}' == null || '${billBeforeAdj.adjNo}' == ""){
    	btnDisable("btnCancel");
    }
}

function checkInput(){
    var param = new Object();
    
    param.adjNo = '${billBeforeAdj.adjNo}';
    param.adjPt = '${billBeforeAdj.adjPt}';;
    param.soId = '${billBeforeAdj.soId}';
    param.pymAcntId = '${billBeforeAdj.pymAcntId}';
    param.billSeqNo = '${billBeforeAdj.billSeqNo}';
    param.billYymm = '${billBeforeAdj.billYymm}';
    
    return param;
}

</script>
<!-- 검색부 -->
<div style="width:1510px;" >

    <!-- title -->
    <div class="layer_top">
        <div class="title"><spring:message code="LAB.M07.LAB00346"/></div>
         <a href="#" id="btnClose"  class="close"></a>
    </div>
	   
	<div class="layer_box">
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
	                <th><spring:message code="LAB.M07.LAB00347"/></th><!-- 신청자 -->
	                <td><input id="popPymAcnt" name="pymAcnt" type="text" class="w245" disabled /></td>
	                
	                <th><spring:message code="LAB.M07.LAB00348"/></th><!-- 신청일시 -->
	                <td><input id="popApplDttm" name="applDttm" type="text" class="w245" disabled /></td>
	            </tr>
	            <tr>
	                <th>적용월</th><!-- 적용월 -->
	                <td>
	                	<div class="date_box">
							<div class="inp_date w130">
								<input type="text" id="popApplyMonth" name="popApplyMonth"  class="month-picker" readonly="readonly" />
								<a href="#" class="btn_cal"></a>
							</div>
						</div>
					</td>                
	                <th><spring:message code="LAB.M09.LAB00136"/><span class="dot">*</span></th><!-- 조정사유코드 -->
	                <td>
	                    <select id="popAdjRsn" class="w100p">
	                        <option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
	                        <c:forEach items="${adjRsnCd}" var="adjRsnCd" varStatus="status">
	                            <option value="${adjRsnCd.commonCd}">${adjRsnCd.commonCdNm}</option>
	                        </c:forEach>
	                    </select>
	                </td>
	            </tr>
	            <tr>               
	                <%-- <th><spring:message code="LAB.M07.LAB00309"/><span class="dot">*</span></th><!-- 승인자 -->
	                <td>
	                    <select id="popAdjApprover" class="w100p">
	                        <option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
	                        <c:forEach items="${adjApprover}" var="adjApprover" varStatus="status">
	                            <option value="${adjApprover.commonCd}">${adjApprover.commonCdNm}</option>
	                        </c:forEach>
	                    </select>
	                </td> --%>
	                <th>신청사유내역<span class="dot">*</span></th><!-- 조정사유 -->
	                <td colspan="3"><input id="popAdjReq" name="adjRsn" type="text" class="W850" /></td>
	            </tr>
	        </tbody>
	    </table> 
	    
	    
	    
	    <div class="main_btn_box">
	        <div class="fl">
	            <h4 class="sub_title"><spring:message code="LAB.M09.LAB00245"/></h4>
	        </div>
	    </div>
	    
	    <div id='beforeAdjRegiditGrid'>
	        <table id="beforeAdjRegiditTable" class="w100p"></table>
	        <div id="beforeAdjRegiditPager"></div>
	    </div>
    
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
                <a href="#" class="grey-btn" id="btnCancel" title='<spring:message code="LAB.M07.LAB00349"/>'><spring:message code="LAB.M07.LAB00349"/></a>
                <ntels:auth auth="${menuAuthU}">
                <a id="popUpdateBtn" class="grey-btn" title='<spring:message code="LAB.M07.LAB00252"/>' href="#"><span class="edit_icon"><spring:message code="LAB.M07.LAB00252"/></span></a>
                </ntels:auth>
            </span>
        </div>
    </div>
 

</div>


<input id="clsBillYymm" type='text' value="${billClsInfo[0].billYymm}"  hidden />
<input id="popAdjNo" type='text' value="${billBefAdj.adjNo}"  hidden />