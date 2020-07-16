<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	var tpDtlCd = "${organization.tpDtlCd}";
	
	$(document).ready(function() {
	
		byteCheckKeyEvent();	//바이트체크
		settingDatepicker();	//팝업달력 셋팅
		
		$("#appyStrtDtUpP").val(stringToDateformatYYYYMMDD("${organization.appyStrtDt}"));
		$("#appyEndDtUpP").val(stringToDateformatYYYYMMDD("${organization.appyEndDt}"));
		
		$('#soIdUpP').selectmenu({});
		$('#soIdUpP').selectmenu("disable");
		
		$('#tpDtlCdUpP').selectmenu({});
		$('#arClCdUpP').selectmenu({});
		$('#arTpCdUpP').selectmenu({});
		$('#inqPermCdUpP').selectmenu({});
		$('#orgStatCdUpP').selectmenu({});
		$('#orgLvCdUpP').selectmenu({});
		$('#privTpCdUpP').selectmenu({});
		
		changeTpCdUpP();
		
		//유형 셀렉트 박스 체인지 이벤트
		$('#tpCdUpP').selectmenu({
		    change: function() {
		    	changeTpCdUpP();
		    }
		});
		
		//저장버튼
		$("#btnInsert").click(function() {
			updateData();            
		});
	});
	
	function updateData(){
		
		var param = checkValidation();
		
		if(param != false){
			
			console.log(param);
			
			$.ajax({
				url : 'updateOrganization.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						var index  = $("#organizationTable").jqGrid("getGridParam", "selrow" );
						ajaxDetail($("#organizationTable").getRowData(index).orgId);
						
						$("#btnClose").trigger('click');
					}
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
		}
	}
	
	function checkValidation(){
		var param = new Object();
		
		var soIdUpP = $.trim( $("#soIdUpP").val() );	//사업
		if(soIdUpP == ''){
			alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}

		var tpCdUpP = $("#tpCdUpP").val();		//유형
		if(tpCdUpP == ''){
			// 유형을 선택해 주세요
			alert('<spring:message code="LAB.M08.LAB00103" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var orgIdUpP = $.trim( $("#orgIdUpP").val() );		//조직아이디

		
		var orgNmUpP = $.trim( $("#orgNmUpP").val() );		//조직명
		if(orgNmUpP == ''){
			// 조직명을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00147" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var tpDtlCdUpP = $("#tpDtlCdUpP").val();		//유형상세
		if(tpDtlCdUpP == ''){
			// 유형상세를 선택해 주세요
			alert('<spring:message code="LAB.M08.LAB00105" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var inqPermCdUpP = $("#inqPermCdUpP").val();		//조회권한
		if(inqPermCdUpP == ''){
			//조회권한을 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00160" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var appyStrtDtUpP = $.trim( $("#appyStrtDtUpP").val() );		//적용시작일
		if(appyStrtDtUpP == ''){
			//적용시작일을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var appyEndDtUpP = $.trim( $("#appyEndDtUpP").val() );			//적용종료일
		if(appyEndDtUpP == ''){
			//적용종료일을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00058" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var orgStatCdUpP = $("#orgStatCdUpP").val();		//조직상태
		if(orgStatCdUpP == ''){
			// 조직상태를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00150" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var orgLvCdUpP = $("#orgLvCdUpP").val();		//조직레벨
		if(orgLvCdUpP == ''){
			// 조직레벨을 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00146" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var privTpCdUpP = $("#privTpCdUpP").val();		//자사구분
		if(privTpCdUpP == ''){
			// 자사구분을 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var arClCdUpP = $("#arClCdUpP").val();		//지역분류
		var arTpCdUpP = $("#arTpCdUpP").val();		//지역구분
		
		param.soId = soIdUpP;			//사업
		param.orgId = orgIdUpP;			//조직아이디
		param.orgNm = orgNmUpP;			//조직명
		param.tpCd = tpCdUpP;			//유형
		
		param.tpDtlCd = tpDtlCdUpP;		//유형상세
		param.arClCd = arClCdUpP;		//지역분류
		param.arTpCd = arTpCdUpP;		//지역구분
		param.inqPermCd = inqPermCdUpP;	//조회권한
		param.appyStrtDt = dateFormatToStringYYYYMMDD(appyStrtDtUpP);		//적용시작일
		param.appyEndDt = dateFormatToStringYYYYMMDD(appyEndDtUpP);			//적용종료일
		param.orgStatCd = orgStatCdUpP;	//조직상태
		param.orgLvCd = orgLvCdUpP;		//조직레벨
		param.privTpCd = privTpCdUpP;	//자사구분
		
		param.orgStatChgDt = param.appyStrtDt;		//조직상태변경일 - 적용시작일
		 
		var permLoginUpP = $("input:checkbox[id='permLoginUpP']").is(":checked") == true ?"1":"0";	//로그인
		var permPymUpP = $("input:checkbox[id='permPymUpP']").is(":checked") == true ?"1":"0";		//수납
		var permSubsUpP = $("input:checkbox[id='permSubsUpP']").is(":checked") == true ?"1":"0";	//개통
		var permChgUpP = $("input:checkbox[id='permChgUpP']").is(":checked") == true ?"1":"0";		//기변
		var permTermUpP = $("input:checkbox[id='permTermUpP']").is(":checked") == true ?"1":"0";	//해지
		
		var loanGvFlgUpP = $("input:checkbox[id='loanGvFlgUpP']").is(":checked") == true ?"1":"0";	//여신부여여부
		var onlineClCdUpP = $("input:checkbox[id='onlineClCdUpP']").is(":checked") == true ?"1":"0";	//온라인구분
		var tbiFlgUpP = $("input:checkbox[id='tbiFlgUpP']").is(":checked") == true ?"1":"0";	//세금계산서발행여부
		var feePvFlgUpP = $("input:checkbox[id='feePvFlgUpP']").is(":checked") == true ?"1":"0";	//수수료지급여부
		
		param.permLogin = permLoginUpP;	//로그인
		param.permPym = permPymUpP;		//수납
		param.permSubs = permSubsUpP;	//개통
		param.permChg = permChgUpP;		//기변
		param.permTerm = permTermUpP;	//해지

		param.loanGvFlg = loanGvFlgUpP;	//여신부여여부
		param.onlineClCd = onlineClCdUpP;	//온라인구분
		param.tbiFlg = tbiFlgUpP;	//세금계산서발행여부
		param.feePvFlg = feePvFlgUpP;	//수수료지급여부
		
		return param;
	}
	
	
	//유형 셀렉트 박스 체인지
	function changeTpCdUpP(){
		
		var grpCd = "DN00039";
		var ref1 = $("#tpCdUpP").val();
		
		var param = new Object();
		param.grpCd = grpCd;
		param.ref1 = ref1;
		
		var sHtml = '';
		sHtml = sHtml + '<option value=""><spring:message code="LAB.M07.LAB00195"/></option>';
		
		if(ref1 == ''){
			$("#tpDtlCdUpP").html("");
			$("#tpDtlCdUpP").html(sHtml);
			
			$( "#tpDtlCdUpP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			
		}else{
			
			$.ajax({
				url : 'getCommonCodeListByRef1ListAction.json',
				type : 'POST',
				data : param,
				success : function(data) {
					
					$.each(data.codeList, function(index,item){
						sHtml = sHtml + '<option value="'+item.commonCd+'">'+item.commonCdNm+'</option>';						
					}); 
					
					$("#tpDtlCdUpP").html("");
					$("#tpDtlCdUpP").html(sHtml);
					
					$( "#tpDtlCdUpP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
					
					if(tpDtlCd != null && tpDtlCd != ''){
						$("#tpDtlCdUpP").val(tpDtlCd);
						$('#tpDtlCdUpP').selectmenu("refresh");
						tpDtlCd = "";	//초기화
					}
				
				},
			    error: function(e){
			        alert(e.reponseText);
			    },
				complete : function() {
				}
			});
			
		}
		
	}
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00152"/><!-- 조직수정 --></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
                                               
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00152"/><!-- 조직수정 --></h4>
		</div>
	</div>
                        
	<table class="writeview">
		<colgroup>
			<col style="width:18%;">
			<col style="width:33%;">
			<col style="width:18%;">
			<col style="width:33%;">
		</colgroup>
		<thead> 
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00003"/><!-- 사업 --><span class="dot">*</span>
				</th>
	            <td colspan="3">
					<select class="w150" id="soIdUpP" name="soIdUpP"  disabled="disabled" >
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}" <c:if test="${organization.soId eq soAuthList.so_id}"> selected="selected" </c:if> >${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M09.LAB00139"/><!-- 조직ID --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<input type="text" id="orgIdUpP" name="orgIdUpP" value="${organization.orgId }" class="w150 not-active" disabled="disabled">                                            
	     		</td>
	 		</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00147"/><!-- 조직명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<input type="text" id="orgNmUpP" name="orgNmUpP" value="${organization.orgNm }" class="w305" checkbyte="450">
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00104"/><!-- 유형/유형상세 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id="tpCdUpP" name="tpCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${tpCd}" var="tpCd" varStatus="status">
							<option value="${tpCd.commonCd}" <c:if test="${organization.tpCd eq tpCd.commonCd}"> selected="selected" </c:if> >${tpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
					<select id="tpDtlCdUpP" name="tpDtlCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00202"/><!-- 지역분류/구분 -->
				</th>
				<td colspan="3">
					<select id="arClCdUpP" name="arClCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${arClCd}" var="arClCd" varStatus="status">
							<option value="${arClCd.commonCd}" <c:if test="${organization.arClCd eq arClCd.commonCd}"> selected="selected" </c:if> >${arClCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
					<select id="arTpCdUpP" name="arTpCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${arTpCd}" var="arTpCd" varStatus="status">
							<option value="${arTpCd.commonCd}" <c:if test="${organization.arTpCd eq arTpCd.commonCd}"> selected="selected" </c:if> >${arTpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<%-- 
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00108"/><!-- 상위조직ID/명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="partOrgIdUpP" name="partOrgIdUpP" value="${organization.partOrgId }" class="w150">
						</div>
						<div class="inp_date w190">
							<input type="text" id="partOrgNmUpP" name="partOrgNmUpP" value="${organization.partOrgNm }" class="w150">
							<a href="" class="search">search</a>
						</div>
					</div>
				</td>
			</tr>
			 --%>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00010"/><!-- 업무권한 -->
				</th>
				<td colspan="3">
					<input type="checkbox" id="permLoginUpP" name="permLoginUpP"  <c:if test="${organization.permLogin eq '1'}"> checked="checked" </c:if>   />
					<label for="permLoginUpP"><spring:message code="LAB.M04.LAB00006"/><!-- 로그인 --></label>
					<input type="checkbox" id="permPymUpP" name="permPymUpP" <c:if test="${organization.permPym eq '1'}"> checked="checked" </c:if> />
					<label for="permPymUpP"><spring:message code="LAB.M07.LAB00235"/><!-- 수납 --></label>
					<input type="checkbox" id="permSubsUpP" name="permSubsUpP" <c:if test="${organization.permSubs eq '1'}"> checked="checked" </c:if> />
					<label for="permSubsUpP"><spring:message code="LAB.M01.LAB00019"/><!-- 개통 --></label>
					<input type="checkbox" id="permChgUpP" name="permChgUpP" <c:if test="${organization.permChg eq '1'}"> checked="checked" </c:if> />
					<label for="permChgUpP"><spring:message code="LAB.M01.LAB00199"/><!-- 기변 --></label>
					<input type="checkbox" id="permTermUpP" name="permTermUpP" <c:if test="${organization.permTerm eq '1'}"> checked="checked" </c:if> />
					<label for="permTermUpP"><spring:message code="LAB.M14.LAB00045"/><!-- 해지 --></label></td>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00160"/><!-- 조회권한 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id="inqPermCdUpP" name="inqPermCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${inqPermCd}" var="inqPermCd" varStatus="status">
							<option value="${inqPermCd.commonCd}" <c:if test="${organization.inqPermCd eq inqPermCd.commonCd}"> selected="selected" </c:if> >${inqPermCd.commonCdNm}</option>
						</c:forEach>
					</select>                                  
				</td>
			</tr>
			
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00056"/><!-- 적용일자 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="appyStrtDtUpP" name="appyStrtDtUpP" class="datepicker not-active" value="${organization.appyStrtDt }" readonly="readonly" />
							<a href="#" class="btn_cal"></a>
						</div>
						<span class="dash">~</span>
						<div class="inp_date w150">
							<input type="text" id="appyEndDtUpP" name="appyEndDtUpP" class="datepicker not-active" value="${organization.appyEndDt }" readonly="readonly" />
							<a href="#" class="btn_cal"></a>
						</div>
					</div>
				</td>
			</tr>
			
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00150"/><!-- 조직상태 --><span class="dot">*</span><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id="orgStatCdUpP" name="orgStatCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${orgStatCd}" var="orgStatCd" varStatus="status">
							<option value="${orgStatCd.commonCd}" <c:if test="${organization.orgStatCd eq orgStatCd.commonCd}"> selected="selected" </c:if> >${orgStatCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00146"/><!-- 조직레벨 --><span class="dot">*</span><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id="orgLvCdUpP" name="orgLvCdUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${orgLvCd}" var="orgLvCd" varStatus="status">
							<option value="${orgLvCd.commonCd}" <c:if test="${organization.orgLvCd eq orgLvCd.commonCd}"> selected="selected" </c:if> >${orgLvCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00003"/><!-- 자사구분 --><span class="dot">*</span><span class="dot">*</span>
				</th>
				<td colspan="3">
		       		<select id="privTpCdUpP" name="privTpCdUpP" class="w150">
		       			<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${privTpCd}" var="privTpCd" varStatus="status">
							<option value="${privTpCd.commonCd}" <c:if test="${organization.privTpCd eq privTpCd.commonCd}"> selected="selected" </c:if> >${privTpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
	     		</td>
	     	</tr>
	     	<tr>
				<th>
					<spring:message code="LAB.M08.LAB00018"/><!-- 여신부여여부 -->
				</th>
				<td>
					<input type="checkbox" id="loanGvFlgUpP" name="loanGvFlgUpP" <c:if test="${organization.loanGvFlg eq '1'}"> checked="checked" </c:if> />
					<label for="loanGvFlg"></label>
				</td>
				<th>
					<spring:message code="LAB.M08.LAB00041"/><!-- 온라인구분 -->
				</th>
				<td>
					<input type="checkbox" id="onlineClCdUpP" name="onlineClCdUpP" <c:if test="${organization.onlineClCd eq '1'}"> checked="checked" </c:if> />
					<label for="onlineClCd"></label>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00209"/><!-- 세금계산서발행여부 -->
				</th>
				<td>
					<input type="checkbox" id="tbiFlgUpP" name="tbiFlgUpP" <c:if test="${organization.tbiFlg eq '1'}"> checked="checked" </c:if> />
					<label for="tbiFlg"></label>
				</td>
				<th>
					<spring:message code="LAB.M07.LAB00251"/><!-- 수수료지급여부 -->
				</th>
				<td>
					<input type="checkbox" id="feePvFlgUpP" name="feePvFlgUpP" <c:if test="${organization.feePvFlg eq '1'}"> checked="checked" </c:if> />
					<label for="feePvFlg"></label>
				</td>
				</tr>
		</thead>
	</table>
</div>
<!--// center -->
<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>	