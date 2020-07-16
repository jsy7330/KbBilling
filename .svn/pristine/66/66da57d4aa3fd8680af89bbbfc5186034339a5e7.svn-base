<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
    $(document).ready(function() {
    	
    	//pageInit();   //여신 확인
    	
	    var param = new Object();
        param.searchSoId =  $("#searchSoId").val(); 
    	//그리드1
	    $("#recStatisticsTable").jqGrid({
	    	    url:'recStatisticsListAction.json?',
	    	    datatype: "local",
	    	    mtype:"POST",
	            postData: {soId : $("#searchSoId").val()},
	    
	            colModel: [
	                //차후 안쓰는 필드는 hidden처리
	                { label: '<spring:message code="LAB.M01.LAB00164" />', name: 'divDpst', width : 100, align:"center"},      //구분
                    { label: '<spring:message code="LAB.M09.LAB00198" />', name: 'rcpt01', width : 200, align:"right"},       //지급최소액
                    { label: '<spring:message code="LAB.M09.LAB00002" />', name: 'rcpt02', width : 150, align:"right"},        //자동이체
                    { label: '<spring:message code="LAB.M01.LAB00001" />', name: 'rcpt04', width : 100, align:"right"},          //가상계좌
                    { label: '<spring:message code="LAB.M07.LAB00292" />', name: 'rcpt05', width : 100, align:"right"},         //신용카드
                    { label: '<spring:message code="LAB.M15.LAB00013" />', name: 'rcpt08', width : 100, align:"right"},         //A장표
                    { label: '<spring:message code="LAB.M05.LAB00041" />', name: 'rcpt09', width : 150, align:"right"},       //무통장
                    { label: '<spring:message code="LAB.M14.LAB00054" />', name: 'rcpt11', width : 100, align:"right"},            //현금
                    { label: '<spring:message code="LAB.M07.LAB00192" />', name: 'rcpt16', width : 150, align:"right"}, //선수금대체
                    { label: '<spring:message code="LAB.M05.LAB00054" />', name: 'rcpt17', width : 200, align:"right"},      //미확인대체
                    { label: '<spring:message code="LAB.M06.LAB00051" />', name: 'rcpt30', width : 110, align:"right"},         //번호이동
                    { label: '<spring:message code="LAB.M02.LAB00019" />', name: 'rcpt31', width : 100, align:"right"},        //납부주장
                    { label: '<spring:message code="LAB.M08.LAB00091" />', name: 'rcpt40', width : 100, align:"right"},        //원포인트
                    { label: '<spring:message code="LAB.M14.LAB00040" />', name: 'tot', width : 100, align:"right"},              //합계
                    { label: '<spring:message code="LAB.M08.LAB00176" />', name: 'cncl', width : 100, align:"right"}          //입금취소
	                
	                //{ label: 'soId' , name: 'soId', hidden:true,width : 0},
	                //{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0}
	                
	            ],
	            viewrecords: true,
	            height: 65,                //화면 상태에 따라 크기 조절 할 것
	            shrinkToFit:false,
	            /*    
	            rowNum:3,                   //한번에 노출되는 row 수
	            rowList:[5,10,20,30,50],    //선택시 노출되는 row 수
	            pager: '#recStatisticsPager',
	            sortable : true,            //드래그로 컬럼간의 위치 변경 가능 여부
	            viewrecords: true,  
	            height: 70,                //화면 상태에 따라 크기 조절 할 것
	            */
	            onCellSelect : function(rowId, index, contents, event){
	            	getPymList(index);
	            },
	            
	            loadComplete: function(obj){
	                $("#recStatisticsTable").setGridWidth($('#recStatisticsGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	            }
	        });
	        
	        $("#recStatisticsTable").setGridWidth($('#recStatisticsGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        
	        //그리드 화면 재조정
	        $(window).resize(function() {
	            $("#recStatisticsTable").setGridWidth($('#recStatisticsGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    });
	    
	    //그리드2
	    $("#recDetailsTable").jqGrid({
	            
	            url:'recDetailsAction.json?',
	            datatype: "local",
                mtype:"POST",
                postData: {soId : $("#searchSoId").val()},
	    
	            colModel: [ 
                    //차후 안쓰는 필드는 hidden처리
	                { label: '<spring:message code="LAB.M02.LAB00006" />', name: 'pymAcntId', width : 100, align:"center"},                                           //납부계정ID
	                { label: '<spring:message code="LAB.M02.LAB00018" />', name: 'acntNm', width : 100, align:"center"},                                              //납부계정명
	                { label: '<spring:message code="LAB.M08.LAB00166" />', name: 'dpstClNm', width : 100, align:"center"},                                               //입금구분
	                { label: '<spring:message code="LAB.M07.LAB00236" />', name: 'payTpNm', width : 100, align:"center"},                                                 //수납구분
	                { label: '<spring:message code="LAB.M08.LAB00173" />', name: 'dpstDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},           //입금일자
	                { label: '<spring:message code="LAB.M08.LAB00128" />', name: 'transDt', formatter:stringTypeFormatterYYYYMMDD, width : 100, align:"center"},        //이체일
	                { label: '<spring:message code="LAB.M07.LAB00243" />', name: 'payProcDt', formatter:stringTypeFormatterYYYYMMDD, width : 180, align:"center"}, //수납처리일자
	                { label: '<spring:message code="LAB.M10.LAB00033" />', name: 'billYymm', formatter:stringTypeFormatterYYYYMM, width : 100, align:"center"},          //청구년월
	                { label: '<spring:message code="LAB.M10.LAB00031" />', name: 'billAmt', formatter:stringTypeFormatComma, width : 100, align:"right"},               //청구금액
	                { label: '<spring:message code="LAB.M08.LAB00125" />', name: 'preRcptAmt', formatter:stringTypeFormatComma, width : 150, align:"right"},          //이전수납금액
	                { label: '<spring:message code="LAB.M07.LAB00237" />', name: 'rcptAmt', formatter:stringTypeFormatComma, width : 100, align:"right"},                //수납금액
	                { label: '<spring:message code="LAB.M07.LAB00244" />', name: 'cnclYn', width : 100, align:"center"},                                              //수납취소
	                { label: '<spring:message code="LAB.M03.LAB00083" />', name: 'regrId', width : 100, align:"center"},                                                 //등록자ID
	                { label: '<spring:message code="LAB.M03.LAB00082" />', name: 'usrNm', width : 100, align:"center"},                                                  //등록자
	                { label: '<spring:message code="LAB.M03.LAB00079" />', name: 'regDate', formatter:dateTypeFormatterYYYYMMDDHH24MISS, width : 150, align:"center"},    //등록일
	                { label: '<spring:message code="LAB.M02.LAB00013" />', name: 'custId', width : 120, align:"center"},                                                //납부고객ID
	                { label: '<spring:message code="LAB.M02.LAB00014" />', name: 'custNm', width : 100, align:"center"},                                                //납부고객명
	                { label: '<spring:message code="LAB.M10.LAB00040" />', name: 'billDt', formatter:stringTypeFormatterYYYYMMDD, width : 150, align:"center"},          //청구일자
	                { label: '<spring:message code="LAB.M07.LAB00240" />', name: 'pymSeqNo', width : 150, align:"center"},                                             //수납일련번호
	                { label: '<spring:message code="LAB.M08.LAB00172" />', name: 'dpstSeqNo', width : 150, align:"center"},                                            //입금일련번호
	                { label: '<spring:message code="LAB.M07.LAB00193" />', name: 'prepayTransSeqNo', width : 250, align:"center"},                         //선수금대체일련번호
	                { label: '<spring:message code="LAB.M06.LAB00092" />', name: 'ambgTransSeqNo', width : 210, align:"center"},                           //불명금대체일련번호
	                { label: '<spring:message code="LAB.M14.LAB00023" />', name: 'allotPrd', width : 120, align:"center"}                                          //할부기간
	                
	                //{ label: 'soId' , name: 'soId', hidden:true,width : 0},
	                //{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0}
	                
	            ],
	            
	            viewrecords: true,
                shrinkToFit:false,
                
                onCellSelect : function(rowid, index, contents, event){
                	getPymDtlList(rowid, index);
                },
	            
	            loadComplete: function(obj){
	                $("#recDetailsTable").setGridWidth($('#recDetailsGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	                
	                //조회 후 결과값 count
	                var cnt = $("#recDetailsTable").getGridParam("reccount");
	                //수납내역 조회 후 해당 건에 대해서 상세 조회
	                getPymDtlList(0, cnt);
	            }
	        });
	        
	        $("#recDetailsTable").setGridWidth($('#recDetailsGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        
	        //그리드 화면 재조정
	        $(window).resize(function() {
	            $("#recDetailsTable").setGridWidth($('#recDetailsGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    });
	    
	    //그리드3
	    $("#recDetailsPerChgItmTable").jqGrid({
	            
	            url:'recDetailsPerChgItmAction.json?',
	            datatype: "local",
                mtype:"POST",
                postData: {soId : $("#searchSoId").val()},
	    
	            colModel: [ 
                        //차후 안쓰는 필드는 hidden처리
	                    { label: '<spring:message code="LAB.M01.LAB00050" />', name: 'custNm', width : 100, align:"center"},                                         //고객명
	                    { label: '<spring:message code="LAB.M07.LAB00186" />', name: 'svcTelNo', width : 100, align:"center"},                                     //서비스번호
	                    { label: '<spring:message code="LAB.M10.LAB00033" />', name: 'billYymm', formatter:stringTypeFormatterYYYYMM, width : 100, align:"center"},  //청구년월
	                    { label: '<spring:message code="LAB.M07.LAB00021" />', name: 'useYymm', formatter:stringTypeFormatterYYYYMM, width : 100, align:"center"},    //사용년월
	                    { label: '<spring:message code="LAB.M07.LAB00130" />', name: 'prodNm', width : 100, align:"center"},                                         //상품명
	                    { label: '<spring:message code="LAB.M07.LAB00185" />', name: 'svcNm', width : 100, align:"center"},                                           //서비스명
	                    { label: '<spring:message code="LAB.M08.LAB00058" />', name: 'chgItmNm', width : 130},                                                     //요금항목명
	                    { label: '<spring:message code="LAB.M10.LAB00031" />', name: 'billAmt', formatter:stringTypeFormatComma, width : 100, align:"right"},       //청구금액
	                    //{ label: '<spring:message code="LAB.M10.LAB00031" />', name: 'billAmt', index: 'billAmt',formatter:'integer', formatoptions:{thousandsSeparator:","}, summaryType:'sum', width : 100, align:"right"},       //청구금액
	                    { label: '<spring:message code="LAB.M08.LAB00125" />', name: 'preRcptAmt', formatter:stringTypeFormatComma, width : 150, align:"right"},  //이전수납금액
	                    { label: '<spring:message code="LAB.M07.LAB00237" />', name: 'rcptAmt', formatter:stringTypeFormatComma, width : 110, align:"right"},        //수납금액
	                    { label: '<spring:message code="LAB.M01.LAB00046" />', name: 'custId', width : 100, align:"center"},                                         //고객ID
	                    { label: '<spring:message code="LAB.M01.LAB00032" />', name: 'ctrtId', width : 100, align:"center"},                                         //계약ID
	                    { label: '<spring:message code="LAB.M07.LAB00240" />', name: 'pymSeqNo', width : 130, align:"center"},                                     //수납일련번호
	                    { label: '<spring:message code="LAB.M07.LAB00125" />', name: 'prodCmpsId', width : 100, align:"center"},                               //상품구성ID
	                    { label: '<spring:message code="LAB.M07.LAB00182" />', name: 'svcCmpsId', width : 100, align:"center"},                                 //서비스구성ID
	                    { label: '<spring:message code="LAB.M08.LAB00061" />', name: 'chgItmCd', width : 100, align:"center"},                                     //요금항목코드
	                    { label: '<spring:message code="LAB.M10.LAB00039" />', name: 'billSeqNo', width : 100, align:"center"}                                    //청구일련번호
	                
	                //{ label: 'soId' , name: 'soId', hidden:true,width : 0},
	                //{ label: 'itemTpCd' , name: 'itemTpCd', hidden:true,width : 0}
	                
	            ],
	            //footterrow: true,
	            //userDataOnFooter: true,
	            
	            loadComplete: function(obj){
	            	//var moneySum = $("#recDetailsPerChgItmGrid").jqGrid('getCol','billAmt',false,'sum');
	            	//$("#recDetailsPerChgItmGrid").jqGrid('footerData','set',{createName:'Total',billAmt:moneySum});
	                $("#recDetailsPerChgItmTable").setGridWidth($('#recDetailsPerChgItmGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	            }
	        });
	        
	        $("#recDetailsPerChgItmTable").setGridWidth($('#recDetailsPerChgItmGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	        
	        //그리드 화면 재조정
	        $(window).resize(function() {
	            $("#recDetailsPerChgItmTable").setGridWidth($('#recDetailsPerChgItmGrid').width(),false); //그리드 테이블을 DIV(widht 100% : w100p)로 감싸서 처리
	    });
	        
	    //사업 셀렉트 박스 체인지
	    $('#searchSoId').selectmenu({
	        change: function() {
	            $("#areaSearch input").val("");
	        }
	    });
	        
	    //등록자 조회
	    $('#btnSearchUser').on('click',function (e) {
	        $("#condUserNm").val('');  //돋보기 클릭시 초기화
	           var url="/system/common/common/userSearchMng/userSearchPopup.ajax";
	           var param = new Object();
	           param.popType = "m";            //팝업타입 m:모달 o:일반
	           param.returnId1 = "condUserNm";
	           param.returnId2 = "condUserId";
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
	        
	    //납부자 조회
	    $('#btnSearchPym').on('click',function (e) {
	        $.ajax({
	            type : "post",
	            url : '/system/common/common/pymAcntSearch/pymAcntSearchPopup.ajax',
	            data : {
	                 inputSoId : $('#condCustSoId').val()   //input SO Id
	                ,inputCustNm : $('#condCustNm').val()   //input Customer Name
	                ,inputPymAcntId : $('#condPymAcntId').val()
	                ,inputIsUnmaskYn : $('#isUnmaskYn').val() //마스크 처리 해제 Y
	                ,outputSoId : 'condCustSoId'            //output SO ID Select
	                ,outputCustNm : 'condCustNm'            //output Customer Name Text
	                ,outputCustId : 'condCustId'            //output Customer ID Text
	                ,outputPymAcntId : 'condPymAcntId'      //output Payment ID Text
	                ,outputPymAcntNm : 'condPymAcntNm'      //output Payment Nm Text
	            },
	            async: true,
	            success : function(data) {
	                var html = data;
	                $("#popup_dialog").html(html);
	            },      
	            complete : function(){
	                wrapWindowByMask(); // 팝업 오픈
	                $("#txtPymSearchCustNm").focus(); //오픈 후 focus위치
	            }
	        }); 
	    });
	       
	    //조회버튼
	    $("#btnSearch").click(function() {
	    	var startDt = $("#strtDt").val();
	    	var endDt   = $("#endDt").val();
	    	
	    	if(startDt == '' || endDt == ''){
	    		alert('<spring:message code="MSG.M01.MSG00007"/>');
	            return;
	    	}
	    	if(startDt > endDt){
	    		alert('<spring:message code="MSG.M01.MSG00005"/>');
                return;
	    	}
	    	
	        $("#recStatisticsTable").setGridParam({
	        	datatype: "json",
	        	postData : {
                    soId : $("#searchSoId").val(),
                    usrId : $("#condUserId").val(),
                    pymAcntId : $("#condPymAcntId").val(),
                    pymDtTp : $('#searchDateTpCd').val(),
                    strtDt : dateFormatToStringYYYYMMDD($("#strtDt").val()),
                    endDt : dateFormatToStringYYYYMMDD($("#endDt").val())
                }                
            });
            
            $("#recStatisticsTable").trigger("reloadGrid");
	    });
    });
    
    function pageInit(){
    	//여신 확인 : 수납권한 및 여신금액 존재여부 확인
    	//수납조회 화면에서는 권한만 확인하면 됌
    	//현재 여신에 대한 개발이 되지 않아 딜레이
    	/*
    	var orgId = "<c:out value='${session_user.orgId}'/>";
    	
    	var url = "<c:out value='${session_user.mainView}'/>";
    	var mainUrl = url + '?noSelectMenu=Y';
        goMenuPage(mainUrl);
        */
    }
    
    function getPymList(index){
    	
    	var sPayGb  = "";
    	var sCnclYn = "";
    	
    	if ( index == '0' )    return;
    	
        if      ( index == '1'  )   sPayGb      = '01'; // 지로
        else if ( index == '2'  )   sPayGb      = '02'; // 자동이체
        else if ( index == '3'  )   sPayGb      = '04'; // 가상계좌
        else if ( index == '4'  )   sPayGb      = '05'; // 신용카드
        else if ( index == '5'  )   sPayGb      = '08'; // A장표
        else if ( index == '6'  )   sPayGb      = '09'; // 무통장입금
        else if ( index == '7'  )   sPayGb      = '11'; // 현금
        else if ( index == '8'  )   sPayGb      = '16'; // 선수금대체
        else if ( index == '9'  )   sPayGb      = '17'; // 미확인대체
        else if ( index == '10' )   sPayGb      = '30'; // 번호이동입금
        else if ( index == '11' )   sPayGb      = '31'; // 납부주장입금
        else if ( index == '12' )   sPayGb      = '40'; // 원포인트입금
        else if ( index == '14' )   sCnclYn     = 'Y';  // 입금취소
        
        $("#recDetailsTable").setGridParam({
            datatype: "json",
            postData : {
                soId : $("#searchSoId").val(),
                usrId : $("#condUserId").val(),
                pymAcntId : $("#condPymAcntId").val(),
                pymDtTp : $('#searchDateTpCd').val(),
                strtDt : dateFormatToStringYYYYMMDD($("#strtDt").val()),
                endDt : dateFormatToStringYYYYMMDD($("#endDt").val()),
                dpstCl : sPayGb,
                cnclYn : sCnclYn
            },
        });
        
        $("#recDetailsTable").trigger("reloadGrid");
    }
    
	function getPymDtlList(rowid, index){
		//최초 화면 로딩시 return
		if (rowid == 0 && index == 0){
			return;
		}
		
		//조회후 첫번째 그리드에서 클릭이벤트를 타서 두번재그리드의 데이터 존재할경우
		//자동으로 두번재그리드의 첫번째 데이터로 세번째 그리드의 데이터를  조회
		if (rowid == 0 && index != 0){
			rowid = 1;
		}
		
		var data = $("#recDetailsTable").getRowData(rowid);
	    
	    $("#recDetailsPerChgItmTable").setGridParam({
	        datatype: "json",
	        postData : {
	        	pymSeqNo : data.pymSeqNo
	        }
	    });
	    
	    $("#recDetailsPerChgItmTable").trigger("reloadGrid");
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

<div class="main_btn_box">
    <div class="fr mt10">
        <a href="#" id="btnSearch" class="grey-btn" ><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
    </div>
</div>


<table class="writeview" id="areaSearch" >
    <colgroup>
        <col style="width:10%;">
        <col style="width:40%;">
        <col style="width:10%;">
        <col style="width:40%;">
    </colgroup>
    <thead> 
        <tr>
            <th><spring:message code="LAB.M07.LAB00003"/><span class="dot">*</span><!-- 사업 --></th>
            <td>
                <select class="w150" id="searchSoId" name="searchSoId">
                    <spring:message code="LAB.M15.LAB00002"/>
                    <c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
                        <option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
                    </c:forEach>
                </select>                                           
            </td>
            <th><spring:message code="LAB.M03.LAB00083"/><!-- 등록자 --></th>
            <td>
                <div class="inp_date w280">
                    <input type="text" id="condUserNm" name="condUserNm" class="w250" disabled="disabled" />
                    <input type="hidden" id="condUserId" name="condUserId" />
                    <a href="#" id="btnSearchUser" name="btnSearchUser" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
                </div> 
            </td>
        </tr>
        <tr>
            <th><spring:message code="LAB.M02.LAB00005"/><!-- 납부계정 --></th>
            <td>
                <div class="inp_date w280">
                    <input type="text" id="condPymAcntNm" name="condPymAcntNm" class="w250" disabled="disabled" />
                    <input type="hidden" id="condPymAcntId" name="condPymAcntId" />
                    <a href="#" id="btnSearchPym" name="btnSearchPym" class="search"><spring:message code="LAB.M09.LAB00158"/></a>
                </div> 
            </td>
            <th><select id="searchDateTpCd" name="searchDateTpCd" class="w130">
                    <!-- <option value=""><spring:message code="LAB.M15.LAB00002"/></option> -->
                    <spring:message code="LAB.M15.LAB00002"/>
                    <c:forEach items="${dateTp}" var="dateTp" varStatus="status">
                        <option value="${dateTp.commonCd}">${dateTp.commonCdNm}</option>
                    </c:forEach>
                </select>
                <span class="dot">*</span>
            </th>
            <td>
                <div class="date_box">
                    <div class="inp_date w150">
                        <input type="text" id="strtDt" name="appyStrtDt" class="datepicker"/>
                        <a href="#" class="btn_cal"></a>
                    </div>
                    <span class="dash">~</span>
                    <div class="inp_date w150">
                        <input type="text" id="endDt" name="appyEndDt" class="datepicker"/>
                        <a href="#" class="btn_cal"></a>
                    </div>
                </div>
            </td>
        </tr>
    </thead>
</table> 

	<div class="main_btn_box">
	    <div class="fl">
	        <h4 class="sub_title"><spring:message code="LAB.M07.LAB00246"/><!-- 수납현황 --></h4>
	    </div>
	</div>
	
	<div id="recStatisticsGrid">
	    <table id="recStatisticsTable" class="w100p"></table>
	</div>
	
	<div class="main_btn_box">
	    <div class="fl">
	        <h4 class="sub_title"><spring:message code="LAB.M07.LAB00238"/><!-- 수납내역 --></h4>
	    </div>
	</div>
	
	<div id="recDetailsGrid">
	    <table id="recDetailsTable" class="w100p"></table>
	</div>
	
	<div class="main_btn_box">
	    <div class="fl">
	        <h4 class="sub_title"><spring:message code="LAB.M07.LAB00239"/><!-- 수납상세내역 --></h4>
	    </div>
	</div>
	
	<div id="recDetailsPerChgItmGrid">
	    <table id="recDetailsPerChgItmTable" class="w100p"></table>
	</div>

</div>

<!-- 팝업참조 -->
<div id="popup_dialog" class="Layer_wrap" style="display: none;" >
</div>
    