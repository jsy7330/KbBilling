<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">
$(document).ready(function() {
	var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';
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

	//��ȸ
	$("#btn_search").click(function() {
		$("#page").val("1");
		getRcptTabList();
	});

	
	//��� Tab �׸���
	$("#rcptTabGrid").jqGrid({
		datatype : 'local',
		colModel: [
			{ label: 'reqDesc', name: 'reqDesc', hidden:true,width : 0},
			{ label: 'procDesc', name: 'procDesc', hidden:true,width : 0},
			{ label: 'custRelNm', name: 'custRelNm', hidden:true,width : 0},
			{ label: 'reqNm', name: 'reqNm', hidden:true,width : 0},
			{ label: 'reqTelNo', name: 'reqTelNo', hidden:true,width : 0},
			{ label: 'colorFl', name: 'colorFl', hidden:true,width : 0},
		    { label: '<spring:message code="LAB.M09.LAB00071"/>', name: 'rcptId', width : 80, align:"center", key:true},
		    { label: '<spring:message code="LAB.M01.LAB00032"/>', name: 'ctrtId', width : 80, align:"center", key:true},
		    { label: '<spring:message code="LAB.M09.LAB00075"/>', name: 'rcptTpNm', width : 100, align:"left"}, 
		    { label: '<spring:message code="LAB.M07.LAB00087"/>', name: 'cnslStatNm', width : 100, align:"left"},
		    { label: '<spring:message code="LAB.M09.LAB00060"/>', name: 'tranStatNm', width : 100, align:"left"},
		    { label: '<spring:message code="LAB.M09.LAB00077"/>', name: 'rcptUsrInfo', width : 150, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M09.LAB00076"/>', name: 'rcptDttm', width : 120, align:"center" , sortable:false, formatter:stringTypeFormatterYYYYMMDDHH24MISS},
		    { label: '<spring:message code="LAB.M10.LAB00024"/>', name: 'procUsrInfo', width : 150, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M10.LAB00021"/>', name: 'procDttm', width : 120, align:"center", sortable:false, formatter:stringTypeFormatterYYYYMMDDHH24MISS},
		    { label: '<spring:message code="LAB.M08.LAB00046"/>', name: 'cmplUsrInfo', width : 150, align:"left", sortable:false},
		    { label: '<spring:message code="LAB.M08.LAB00045"/>', name: 'cmplDttm', width : 120, align:"center", sortable:false, formatter:stringTypeFormatterYYYYMMDDHH24MISS},
		    { label: '<spring:message code="LAB.M03.LAB00045"/>', name: 'cnslMstClNm', width : 200, sortable:false,align:"left"},
		    { label: '<spring:message code="LAB.M09.LAB00196"/>', name: 'cnslMidClNm', width : 200, sortable:false,align:"left"},
		    { label: '<spring:message code="LAB.M07.LAB00211"/>', name: 'cnslSlvClNm', width : 200, sortable:false,align:"left"},
		],
		viewrecords: true,
		shrinkToFit:false,
		height: 190,
		sortable : true,
		jsonReader: {
			repeatitems : true,
			root : "rcptList",
			records : "totalCount", //�� ���ڵ� 
			total : "totalPages",  //����������
			page : "page"          //���� ������
		},
		rowList:[5,10,20,30,50],	//���ý� ����Ǵ� row ��
        rowNum: 10,
        pager: "#rcptTabGridPager",
        ondblClickRow : function(rowid) { //ROW Ŭ���� �̺�Ʈ
        	//getRcptInfo(rowid);
			setSelectedData(rowid);
		},
       	loadComplete : function () {

			var ids = $('#rcptTabGrid').jqGrid('getDataIDs');	    
			var gridData = $("#rcptTabGrid").jqGrid('getRowData');

			for (var i = 0; i < gridData.length; i++) {
			    if (gridData[i].colorFl == "R") {
			        $('#rcptTabGrid tr[id=' + ids[i] + ']').addClass('grid-color1');
			   }else if (gridData[i].colorFl == 'B') {
			        $('#rcptTabGrid tr[id=' + ids[i] + ']').addClass('grid-color2');
			   }else if (gridData[i].colorFl == 'G') {
			        $('#rcptTabGrid tr[id=' + ids[i] + ']').addClass('grid-color3');
			   }   
			};


  	      	$("#rcptTabGrid").setGridWidth($('#rcptTabDiv').width(),false); 
        },
    	sortable: { update: function(permutation) {
    		$("#rcptTabGrid").setGridWidth($('#rcptTabDiv').width(),false);
    		}
    	}
	});


  	//��з� ���� �̺�Ʈ
    $('#selRcptLvl1').selectmenu({
        change: function() {
    	   $('#selRcptLvl2').each( function() {
            	$('#selRcptLvl2 option:gt(0)').remove();
            });
    	   $('#selRcptLvl3').each( function() {
            	$('#selRcptLvl3 option:gt(0)').remove();
            });
           $('#selRcptLvl2').val('SEL');
           $('#selRcptLvl2').selectmenu("refresh");
           $('#selRcptLvl3').val('SEL');
           $('#selRcptLvl3').selectmenu("refresh");
           getRcptLvl2CodeList();
       	}
    });
	
  	//�ߺз� ���� �̺�Ʈ
    $('#selRcptLvl2').selectmenu({
        change: function() {
        	$('#selRcptLvl3').each( function() {
            	$('#selRcptLvl3 option:gt(0)').remove();
            });
          	$('#selRcptLvl3').val('SEL');
          	$('#selRcptLvl3').selectmenu("refresh");
          	getRcptLvl3CodeList();
       	}
    });

    // ����ȸ
	$('#btnCustSearch').on('click',function (e) {
			if($("#btnCustSearch").hasClass('not-active')){
				return;
			}
			openCustSearchPopup();	
			//openPymSearchPopup();
		}
	);

});

/*
 * ����ߺз� ��ȸ
 */
function getRcptLvl2CodeList(){

	var url = '/customer/contract/contract/contractManagement/getRcptLvl2CodeListAction.json';
	var code = $('#selRcptLvl1').val();
	$.ajax({
        url:url,
        type:'POST',
        async: false,
        data : {
      	  code : code
        	},
        dataType: 'json',
        success: function(data){
      	  $(data.rcptLvl2CodeList).each(function(index, item){
			     var str = '<option value="'+ item.commonCd+'">'+ item.commonCdNm+'</option>';
			     $('#selRcptLvl2').append(str);  
      	  });
      	  
     		$('#selRcptLvl2').val('SEL');
     		$('#selRcptLvl2').selectmenu("refresh");
        },
     	beforeSend: function(data){
     			
     	},
     	error : function(err){
     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
   			alert(err.responseJSON.exceptionMsg);
   		}else{
   			alert('<spring:message code="MSG.M10.MSG00005"/>');	
   		}
     	}
    });
}

/*
 * ���Һз� ��ȸ
 */
function getRcptLvl3CodeList(){

	var url = '/customer/contract/contract/contractManagement/getRcptLvl3CodeListAction.json';
	
	var code = $('#selRcptLvl2').val();
	$.ajax({
        url:url,
        type:'POST',
        async: false,
        data : {
      	  code : code
        	},
        dataType: 'json',
        success: function(data){
      	  $(data.rcptLvl3CodeList).each(function(index, item){
				var str = '<option value="'+ item.commonCd+'">'+ item.commonCdNm+'</option>';
				$('#selRcptLvl3').append(str);  
      	  });
      	  
     		$('#selRcptLvl3').val('SEL');
     		$('#selRcptLvl3').selectmenu("refresh");
        },
     	beforeSend: function(data){
     			
     	},
     	error : function(err){
     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
   			alert(err.responseJSON.exceptionMsg);
   		}else{
   			alert('<spring:message code="MSG.M10.MSG00005"/>');	
   		}
     	}
    });
}

/*
 * ����ȸ�˾�
 */
function openCustSearchPopup(){
	
	$.ajax({
		type : "post",
		url : '/system/common/common/customerSearch/customerSearchPopup.ajax',
		data : {
			 inputSoId : $('#condCustSoId').val()   //input SO Id
			,inputCustNm : $('#condCustNm').val()   //input Customer Name
			,inputIsUnmaskYn : $('#isUnmaskYn').val() //����ũ ó�� ���� Y
			,outputSoId : 'condCustSoId'            //output SO ID Select
			,outputCustNm : 'condCustNm'            //output Customer Name Text
			,outputCustId : 'condCustId'            //output Customer ID Text

		},
		async: true,
		success : function(data) {
			var html = data;
			$("#popup_dialog").html(html);
		},		
		complete : function(){
			wrapWindowByMask(); // �˾� ����
			$("#txtCustSearchCustNm").focus(); //���� �� focus��ġ
		}
	}); 
}

/**
 * ���Tab��ȸ
 */
function getRcptTabList(){


	var searchStatDt = $("#searchStatDt").val();
	var searchEndDt = $("#searchEndDt").val();
	var cnslStat = $("#cnslStat").val();
	var condCustNm = $("#condCustNm").val();
	var condCustId = $("#condCustId").val();
	var selRcptLvl1 = $("#selRcptLvl1").val();
	var selRcptLvl2 = $("#selRcptLvl2").val();
	var selRcptLvl3 = $("#selRcptLvl3").val();
	var rcptYn = $("input[name=rcptYn]:checked").val();
	var elapse = $("input[name=elapse]:checked").val();
	var orgId = $("#orgId").val();
	if(selRcptLvl1 =='SEL'){
		selRcptLvl1 ="";
	}
	if(selRcptLvl2 =='SEL'){
		selRcptLvl2 ="";
	}
	if(selRcptLvl3 =='SEL'){
		selRcptLvl3 ="";
	}
	if(cnslStat =='SEL'){
		cnslStat ="";
	}
	searchStatDt = dateFormatToStringYYYYMMDD(searchStatDt);
	searchEndDt = dateFormatToStringYYYYMMDD(searchEndDt);

	$("#rcptTabGrid").setGridParam({
		url : '/customer/contract/receipt/receiptCounsel/getRcptTabListAction.json',
		datatype : 'json',
		mtype: 'POST',
  	    postData : {
  			searchStatDt : searchStatDt,
  			searchEndDt : searchEndDt,
  			cnslStat : cnslStat,
  			condCustId : condCustId,
  			selRcptLvl1 : selRcptLvl1,
  			selRcptLvl2 : selRcptLvl2,
  			selRcptLvl3 : selRcptLvl3,
  			rcptYn : rcptYn,
  			elapse : elapse,
  			orgId : orgId
		}
	});
	      	
   	$("#rcptTabGrid").trigger("reloadGrid");
}

/**
	��� ��ȸ
*/
function getRcptInfo(rcptId){
	var url = '/customer/contract/receipt/receiptCounsel/getRcptInfoAction.json';
	var rcptInfo = new Object();
	rcptInfo.rcptId = rcptId;
	$.ajax({
        url:url,
        type:'POST',
        data : rcptInfo,
        dataType: 'json',
        success: function(data){
        	//�ش�� ����
        	setRcptInfo(data.resRcptInfo, data.rcptLvl1CodeList, data.rcptLvl2CodeList, data.rcptLvl3CodeList);
        },
     	beforeSend: function(data){
     	},
     	error : function(err){
     		if(err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
     			alert(err.responseJSON.exceptionMsg);
     		}else{
     			alert('<spring:message code="MSG.M10.MSG00005"/>');	
     		}
     		
     	}
    });

}
	//������ 
	function setSelectedData(rowId){		
		var data = $("#rcptTabGrid").getRowData(rowId);
		$("#rcptId").val(data.rcptId);
		$("#cnslMstClNm").val(data.cnslMstClNm);
		$("#cnslMidClNm").val(data.cnslMidClNm);
		$("#cnslSlvClNm").val(data.cnslSlvClNm);
		$("#rcptTpNm").val(data.rcptTpNm);
		$("#cnslStatNm").val(data.cnslStatNm);
		$("#rcptUsrInfo").val(data.rcptUsrInfo);
		$("#procUsrInfo").val(data.procUsrInfo);
		$("#rcptDttm").val(data.rcptDttm);
		$("#procDttm").val(data.procDttm);
		$("#custRelNm").val(data.custRelNm);
		$("#reqNm").val(data.reqNm);
		$("#reqTelNo").val(data.reqTelNo);

		$("#rcptRcptDt").val(data.rcptRcptDt);
		$("#rcptProcDt").val(data.rcptProcDt);
		$("#reqDesc").val(data.reqDesc);
		$("#procDesc").val(data.procDesc);

	}

</script>

<!-- ��� �޴��� �� Navigator �ۼ� -->
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

<!-- �˻� ��ư -->
<div class="main_btn_box">
	<ntels:auth auth="${menuAuthR}">
		<div class="fr mt10">
			<a id='btn_search' href="#"class="grey-btn" title='<spring:message code="LAB.M01.LAB00047"/>'><span class="search_icon"><spring:message code="LAB.M09.LAB00158"/></span></a> 
		</div>
	</ntels:auth>
</div>

<!-- �˻��� -->
<table class="writeview">
	<colgroup>
		<col style="width:10%;">
		<col style="width:30%;">
		<col style="width:10%;">
		<col style="width:20%;">
		<col style="width:10%;">
		<col style="width:20%;">
	</colgroup>
	<thead>
	<!--�˻�1Line-->
		<tr>
			<!--�����Ⱓ-->
			<th><spring:message code="LAB.M09.LAB00072" /></th>
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
			<!--������-->
			<th><spring:message code="LAB.M07.LAB00087" /></th>
			<td>
				<select id="cnslStat" name="cnslStat" class="w170">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${cnslStatList}" var="cnslStatList" varStatus="status">
					<option value="${cnslStatList.commonCd}">${cnslStatList.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<!--����-->
			<th><spring:message code="LAB.M01.LAB00050" /></th>
			<td>
				<div class="inp_date w220">
					<input id="condCustNm" type="text" class="w200" />
					<input id="condCustId" type="text" hidden >

					<ntels:auth auth="${menuAuthR}">
						<a id="btnCustSearch" href="#" title='<spring:message code="LAB.M01.LAB00047"/>' class="search"></a>
					</ntels:auth>
				</div>
			</td>
		</tr>
	<!--�˻�2Line-->		
		<tr>
			<th><spring:message code="LAB.M07.LAB00076" /></th>
			<td>
				<div class="inp_date w220">
					<input id="userInfo" type="text" value="${session_user.userName}(${session_user.userId})" class="w200" disabled/>
				</div>
			</td>
			<th><spring:message code="LAB.M09.LAB00155" /></th>
			<td colspan="3">
				<div class="date_box">
					<input id="orgInfo" type="text" value="${session_user.orgNm}(${session_user.orgId})" class="w200" disabled/>
					<input id="orgId" type="hidden" value="${session_user.orgId}"/>
					<input type="radio" id="rcptYn" name="rcptYn" value="R" checked="checked" />
						<label for="rcptYn"><spring:message code="LAB.M09.LAB00073" /></label> 
					
					<input type="radio" id="rcptYn1" name="rcptYn" value="T" /> 
						<label for="rcptYn1">	<spring:message code="LAB.M10.LAB00020" /></label>
				</div>
			</td>
		</tr>
	<!--�˻�3Line-->	
		<tr>
			<!--��з�-->
			<th><spring:message code="LAB.M07.LAB00085" /></th>
			<td>
				<select id="selRcptLvl1" name="selRcptLvl1" class="w270"><!--��з�-->
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
					<c:forEach items="${rcptLvl1CodeList}" var="rcptLvl1CodeList" varStatus="status">
					<option value="${rcptLvl1CodeList.commonCd}">${rcptLvl1CodeList.commonCdNm}</option>
					</c:forEach>
				</select>
			</td>
			<!--�ߺз�-->
			<th><spring:message code="LAB.M07.LAB00091" /></th>
			<td>
				<select id="selRcptLvl2" class="w100p rcptInfoSelCls">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
				</select>   
			</td>
			<!--�Һз�-->
			<th><spring:message code="LAB.M07.LAB00088" /></th>
			<td>
				<select id="selRcptLvl3" class="w100p rcptInfoSelCls">
					<option value="SEL"><spring:message code="LAB.M15.LAB00002"/></option>
				</select>  
			</td>
		</tr>
	<!--�˻�4Line-->	
		<tr>
			<!--����ð�-->
			<th><spring:message code="LAB.M01.LAB00031" /></th>
			<td colspan="3">
				<div class="date_box">
					<input type="radio" id="elapse" name="elapse" value="TL" checked="checked" /> 
						<label for="popupYn-1"><spring:message code="LAB.M09.LAB00063" /></label> 
					<input type="radio" id="elapse" name="elapse" value="TW" /> 
						<label for="popupYn-2"  class="grid-color3">&nbsp;12<spring:message code="LAB.M07.LAB00281" />&nbsp;</label>
					<input type="radio" id="elapse" name="elapse" value="TF" /> 
						<label for="popupYn-2"  class="grid-color2"><font color="white">&nbsp;24<spring:message code="LAB.M07.LAB00281" />&nbsp;<font></label>
					<input type="radio" id="elapse" name="elapse" value="SD" /> 
						<label for="popupYn-2"  class="grid-color1"><font color="white">&nbsp;7<spring:message code="LAB.M08.LAB00143" />&nbsp;<font></label>
				</div>
			</td>
		</tr>

	</thead>

</table> 

<!-- ��㸮��Ʈ -->
<div class="main_btn_box">  
	<div class="fl">   
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00086"/></h4>
	</div>
</div>

<div id='gridDiv'>
	<div id="rcptTabDiv" class="tab_content" ><!-- ��� �׸��� -->
		<table id="rcptTabGrid" class="w100p"></table>
		<div id="rcptTabGridPager"></div>
	</div>
</div>

<!-- ������� -->
<div class="main_btn_box">  
	<div class="fl">   
		<h4 class="sub_title"><spring:message code="LAB.M07.LAB00090"/></h4>
	</div>
</div>
<table class="writeview">
	<colgroup>
		<col style="width:9%;">
		<col style="width:16%;">
		<col style="width:9%;">
		<col style="width:16%;">
		<col style="width:9%;">
		<col style="width:16%;">
		<col style="width:9%;">
		<col style="width:16%;">
	</colgroup>
	<thead> 

		<tr>
			<th><spring:message code="LAB.M09.LAB00071"/></th>
			<td>
				<input id="rcptId" type="text" class="w100p" disabled />

			</td>
			<th><spring:message code="LAB.M03.LAB00045"/></th>
			<td>
				<input id="cnslMstClNm" type="text" class="w100p" disabled /> 
			</td>
			<th><spring:message code="LAB.M09.LAB00196"/></th>
			<td>
				<input id="cnslMidClNm" type="text" class="w100p" disabled />                                   
			</td>
			<th><spring:message code="LAB.M07.LAB00211"/></th>
			<td>
				<input id="cnslSlvClNm" type="text" class="w100p" disabled />         
			</td>
		</tr>
		<tr>
			<th><spring:message code="LAB.M09.LAB00075"/></th>
			<td>
				<input id="rcptTpNm" type="text" class="w100p" disabled />   
			</td>
			<th><spring:message code="LAB.M07.LAB00087"/></th>
			<td>
				<input id="cnslStatNm" type="text" class="w100p" disabled />
			</td>
			<th><spring:message code="LAB.M09.LAB00077"/></th>
			<td>
				<input id="rcptUsrInfo" type="text"  class="w100p" disabled />
			</td>
			<th><spring:message code="LAB.M10.LAB00025"/></th>
			<td>
				<div class="inp_date w100p" >
					<input id="procUsrInfo" type="text" class="w100p" disabled/>
					<a id="btnRcptDetail" class="detail rcptTdBtnCls"></a>
				</div>
			</td>
		</tr>


		<tr>
			<th><spring:message code="LAB.M08.LAB00079"/></th>
			<td>
				<div class="sel w100p" >
					<input id="custRelNm" type="text" class="w47p" disabled/>
					<input id="reqNm" type="text" class="w49p" disabled/>
				</div>
			</td>
			<th><spring:message code="LAB.M08.LAB00081"/></th>
			<td>
				<input id="reqTelNo" type="text"  class="w100p rcptInfoCls not-active" disabled/>
			</td>
			<th><spring:message code="LAB.M09.LAB00076"/></th>
			<td>
				<input id="rcptDttm" type="text" class="w100p" disabled/>
			</td>
			<th><spring:message code="LAB.M10.LAB00022"/></th>
			<td>
				<input id="procDttm" type="text" class="" disabled/>
			</td>
		</tr>

		<tr>
			<th><spring:message code="LAB.M08.LAB00080"/></th>
				<td colspan="3">
					<textarea id="reqDesc" class="w100p h100" disabled></textarea>
				</td>
			<th><spring:message code="LAB.M10.LAB00019"/></th>
				<td colspan="3">
					<textarea id="procDesc" class="w100p h100 rcptInfoCls not-active" disabled></textarea>
				</td>
		</tr>
	</thead>
</table> 

<!-- �˾����� -->
<div id="popup_dialog" class="Layer_wrap" style="display:none;"></div>