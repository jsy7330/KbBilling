<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	$(document).ready(function() {
	
		byteCheckKeyEvent();	//바이트체크
		settingDatepicker();	//팝업달력 셋팅
		
		//날짜 세팅
		$("#appyEndDtInP").val(stringToDateformatYYYYMMDD("99991231"));
		var yyyymmdd = $.datepicker.formatDate('yymmdd', new Date());
		$("#appyStrtDtInP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		
		//셀렉트 박스 세팅
		$('#soIdInP').selectmenu({});
		$('#tpDtlCdInP').selectmenu({});
		$('#arClCdInP').selectmenu({});
		$('#arTpCdInP').selectmenu({});
		$('#inqPermCdInP').selectmenu({});
		$('#orgStatCdInP').selectmenu({});
		$('#orgLvCdInP').selectmenu({});
		$('#privTpCdInP').selectmenu({});
		
		//유형 셀렉트 박스 체인지 이벤트
		$('#tpCdInP').selectmenu({
		    change: function() {
		    	changeTpCdInP();
		    }
		});
		
		//저장버튼
		$("#btnInsert").click(function() {
			insertData();            
		});
		
		//조직검색 팝업
		$("#btnOrgPopInp").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=partOrgIdInP&returnId2=partOrgNmInP";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
	});
	
	//등록
	function insertData(){
		
		var param = checkValidation();
		
		if(param != false){
			
			$.ajax({
				url : 'insertOrganization.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						searchData();//부모창 새로고침
						
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
	
	//밸리데이션 체크
	function checkValidation(){
		var param = new Object();
		
		var soIdInP = $.trim( $("#soIdInP").val() );	//사업
		if(soIdInP == ''){
			alert('<spring:message code="LAB.M07.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}

		var tpCdInP = $("#tpCdInP").val();		//유형
		if(tpCdInP == ''){
			alert('<spring:message code="LAB.M08.LAB00103" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var orgIdInP = $.trim( $("#orgIdInP").val() );		//조직아이디
		if(tpCdInP == "1000" || tpCdInP == "2000" || tpCdInP == "3000"){
			if(orgIdInP == ''){
				
				alert('<spring:message code="LAB.M09.LAB00139" /> <spring:message code="MSG.M08.MSG00043" />');
				return false;
			}
			
			if(getExistOrganizationInfo(orgIdInP)){
				//조직아이디가 중복 되었습니다.
				alert('<spring:message code="MSG.M09.MSG00036" />');
				return false;
			}
		}else{
			//조직유형코드가  직영점,대리점,접점 인 경우 조직순번을 취득한다. 
			//조직아이디를 인서트시 채번함......
		}
		
		var orgNmInP = $.trim( $("#orgNmInP").val() );		//조직명
		if(orgNmInP == ''){
			// 조직명을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00147" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var tpDtlCdInP = $("#tpDtlCdInP").val();		//유형상세
		if(tpDtlCdInP == ''){
			// 유형상세를 선택해 주세요
			alert('<spring:message code="LAB.M08.LAB00105" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var partOrgIdInP = $("#partOrgIdInP").val();		//상위조직아이디
		if(!(tpCdInP == "1000" && tpDtlCdInP == "1001")){
			if(partOrgIdInP == ''){
				// 상위조직을 등록해 주세요
				alert('<spring:message code="LAB.M07.LAB00097" /> <spring:message code="MSG.M08.MSG00043" />');
				return false;
			}
		}
		
		var inqPermCdInP = $("#inqPermCdInP").val();		//조회권한
		if(inqPermCdInP == ''){
			//조회권한을 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00160" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var appyStrtDtInP = $.trim( $("#appyStrtDtInP").val() );		//적용시작일
		if(appyStrtDtInP == ''){
			//적용시작일을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var appyEndDtInP = $.trim( $("#appyEndDtInP").val() );			//적용종료일
		if(appyEndDtInP == ''){
			//적용종료일을 입력해 주세요
			alert('<spring:message code="LAB.M09.LAB00058" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var orgStatCdInP = $("#orgStatCdInP").val();		//조직상태
		if(orgStatCdInP == ''){
			// 조직상태를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00150" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var orgLvCdInP = $("#orgLvCdInP").val();		//조직레벨
		if(orgLvCdInP == ''){
			// 조직레벨을 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00146" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var privTpCdInP = $("#privTpCdInP").val();		//자사구분
		if(privTpCdInP == ''){
			// 자사구분을 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00003" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var arClCdInP = $("#arClCdInP").val();		//지역분류
		var arTpCdInP = $("#arTpCdInP").val();		//지역구분
		var partOrgNmInP = $("#partOrgNmInP").val();		//상위조직명
		
		param.soId = soIdInP;			//사업
		param.orgId = orgIdInP;			//조직아이디
		param.orgNm = orgNmInP;			//조직명
		param.tpCd = tpCdInP;			//유형
		
		param.tpDtlCd = tpDtlCdInP;		//유형상세
		param.arClCd = arClCdInP;		//지역분류
		param.arTpCd = arTpCdInP;		//지역구분
		param.partOrgId = partOrgIdInP;	//상위조직아이디
		param.partOrgNm = partOrgNmInP;	//상위조직명
		param.inqPermCd = inqPermCdInP;	//조회권한
		param.appyStrtDt = dateFormatToStringYYYYMMDD(appyStrtDtInP);		//적용시작일
		param.appyEndDt = dateFormatToStringYYYYMMDD(appyEndDtInP);			//적용종료일
		param.orgStatCd = orgStatCdInP;	//조직상태
		param.orgLvCd = orgLvCdInP;		//조직레벨
		param.privTpCd = privTpCdInP;	//자사구분
		
		param.orgStatChgDt = param.appyStrtDt;		//조직상태변경일 - 적용시작일
		 
		var permLoginInP = $("input:checkbox[id='permLoginInP']").is(":checked") == true ?"1":"0";	//로그인
		var permPymInP = $("input:checkbox[id='permPymInP']").is(":checked") == true ?"1":"0";		//수납
		var permSubsInP = $("input:checkbox[id='permSubsInP']").is(":checked") == true ?"1":"0";	//개통
		var permChgInP = $("input:checkbox[id='permChgInP']").is(":checked") == true ?"1":"0";		//기변
		var permTermInP = $("input:checkbox[id='permTermInP']").is(":checked") == true ?"1":"0";	//해지
		
		var loanGvFlgInP = $("input:checkbox[id='loanGvFlgInP']").is(":checked") == true ?"1":"0";	//여신부여여부
		var onlineClCdInP = $("input:checkbox[id='onlineClCdInP']").is(":checked") == true ?"1":"0";	//온라인구분
		var tbiFlgInP = $("input:checkbox[id='tbiFlgInP']").is(":checked") == true ?"1":"0";	//세금계산서발행여부
		var feePvFlgInP = $("input:checkbox[id='feePvFlgInP']").is(":checked") == true ?"1":"0";	//수수료지급여부
		
		param.permLogin = permLoginInP;	//로그인
		param.permPym = permPymInP;		//수납
		param.permSubs = permSubsInP;	//개통
		param.permChg = permChgInP;		//기변
		param.permTerm = permTermInP;	//해지

		param.loanGvFlg = loanGvFlgInP;	//여신부여여부
		param.onlineClCd = onlineClCdInP;	//온라인구분
		param.tbiFlg = tbiFlgInP;	//세금계산서발행여부
		param.feePvFlg = feePvFlgInP;	//수수료지급여부
		
		return param;
	}
	
	//조직아이디 중복 체크 
	function getExistOrganizationInfo(orgIdInP){
		
		var param = new Object();
		param.orgId = orgIdInP;
		var orgIdCount = ""; 
		
		$.ajax({
			url : 'getExistOrganizationInfo.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				orgIdCount = data.orgIdCount;
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
		if(orgIdCount != "0"){	//중복
			return true;	//조직 아이디 중복
		}else{
			return false;
		}
		
	}
	
	//유형 셀렉트 박스 체인지
	function changeTpCdInP(){
		
		var grpCd = "DN00039";
		var ref1 = $("#tpCdInP").val();
		
		var param = new Object();
		param.grpCd = grpCd;
		param.ref1 = ref1;
		
		var sHtml = '';
		sHtml = sHtml + '<option value=""><spring:message code="LAB.M07.LAB00195"/></option>';
		
		if(ref1 == ''){
			$("#tpDtlCdInP").html("");
			$("#tpDtlCdInP").html(sHtml);
			
			$( "#tpDtlCdInP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
			
		}else{
			
			$.ajax({
				url : 'getCommonCodeListByRef1ListAction.json',
				type : 'POST',
				data : param,
				success : function(data) {
					
					$.each(data.codeList, function(index,item){
						sHtml = sHtml + '<option value="'+item.commonCd+'">'+item.commonCdNm+'</option>';						
					}); 
					
					$("#tpDtlCdInP").html("");
					$("#tpDtlCdInP").html(sHtml);
					
					$( "#tpDtlCdInP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
				
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
	<div class="title"><spring:message code="LAB.M09.LAB00145"/><!-- 조직등록 --></div>
	<a href="#" class="close">Close</a>
</div>
<!--// title -->
                                               
<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00145"/><!-- 조직등록 --></h4>
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
					<select class="w150" id="soIdInP" name="soIdInP">
						<option value=""><spring:message code="LAB.M15.LAB00002"/></option>
						<c:forEach items="${session_user.soAuthList}" var="soAuthList" varStatus="status">
							<option value="${soAuthList.so_id}">${soAuthList.so_nm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>		
				<th>
					<spring:message code="LAB.M09.LAB00139"/><!-- 조직ID --><span class="dot">*</span>
				</th>
				<td colspan="3">
	       			<input type="text" id="orgIdInP" name="orgIdInP" class="w150" checkbyte="10" >                                            
	     		</td>
	 		</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00147"/><!-- 조직명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<input type="text" id="orgNmInP" name="orgNmInP" class="w305" checkbyte="450" >
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00104"/><!-- 유형/유형상세 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id="tpCdInP" name="tpCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${tpCd}" var="tpCd" varStatus="status">
							<option value="${tpCd.commonCd}">${tpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
					<select id="tpDtlCdInP" name="tpDtlCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00202"/><!-- 지역분류/구분 -->
				</th>
				<td colspan="3">
					<select id="arClCdInP" name="arClCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${arClCd}" var="arClCd" varStatus="status">
							<option value="${arClCd.commonCd}">${arClCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
					<select id="arTpCdInP" name="arTpCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${arTpCd}" var="arTpCd" varStatus="status">
							<option value="${arTpCd.commonCd}">${arTpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00108"/><!-- 상위조직ID/명 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="partOrgIdInP" name="partOrgIdInP" disabled="disabled" class="w150 not-active">
						</div>
						<div class="inp_date p100">
							<input type="text" id="partOrgNmInP" name="partOrgNmInP" disabled="disabled" class="p100 not-active">
							<a href="#" id="btnOrgPopInp" class="search">search</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M08.LAB00010"/><!-- 업무권한 -->
				</th>
				<td colspan="3">
					<input type="checkbox" id="permLoginInP" name="permLoginInP" />
					<label for="permLoginInP"><spring:message code="LAB.M04.LAB00006"/><!-- 로그인 --></label>
					<input type="checkbox" id="permPymInP" name="permPymInP" />
					<label for="permPymInP"><spring:message code="LAB.M07.LAB00235"/><!-- 수납 --></label>
					<input type="checkbox" id="permSubsInP" name="permSubsInP" />
					<label for="permSubsInP"><spring:message code="LAB.M01.LAB00019"/><!-- 개통 --></label>
					<input type="checkbox" id="permChgInP" name="permChgInP" />
					<label for="permChgInP"><spring:message code="LAB.M01.LAB00199"/><!-- 기변 --></label>
					<input type="checkbox" id="permTermInP" name="permTermInP" />
					<label for="permTermInP"><spring:message code="LAB.M14.LAB00045"/><!-- 해지 --></label></td>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00160"/><!-- 조회권한 --><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id="inqPermCdInP" name="inqPermCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${inqPermCd}" var="inqPermCd" varStatus="status">
							<option value="${inqPermCd.commonCd}">${inqPermCd.commonCdNm}</option>
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
							<input type="text" id="appyStrtDtInP" name="appyStrtDtInP" class="datepicker not-active" value="" readonly="readonly" />
							<a href="#" class="btn_cal"></a>
						</div>
						<span class="dash">~</span>
						<div class="inp_date w150">
							<input type="text" id="appyEndDtInP" name="appyEndDtInP" class="datepicker not-active" value="" readonly="readonly" />
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
					<select id="orgStatCdInP" name="orgStatCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${orgStatCd}" var="orgStatCd" varStatus="status">
							<option value="${orgStatCd.commonCd}">${orgStatCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00146"/><!-- 조직레벨 --><span class="dot">*</span><span class="dot">*</span>
				</th>
				<td colspan="3">
					<select id="orgLvCdInP" name="orgLvCdInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${orgLvCd}" var="orgLvCd" varStatus="status">
							<option value="${orgLvCd.commonCd}">${orgLvCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M09.LAB00003"/><!-- 자사구분 --><span class="dot">*</span><span class="dot">*</span>
				</th>
				<td colspan="3">
		       		<select id="privTpCdInP" name="privTpCdInP" class="w150">
		       			<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${privTpCd}" var="privTpCd" varStatus="status">
							<option value="${privTpCd.commonCd}">${privTpCd.commonCdNm}</option>
						</c:forEach>
					</select>                                            
	     		</td>
	     	</tr>
	     	<tr>
				<th>
					<spring:message code="LAB.M08.LAB00018"/><!-- 여신부여여부 -->
				</th>
				<td>
					<input type="checkbox" id="loanGvFlgInP" name="loanGvFlgInP" />
					<label for="loanGvFlg"></label>
				</td>
				<th>
					<spring:message code="LAB.M08.LAB00041"/><!-- 온라인구분 -->
				</th>
				<td>
					<input type="checkbox" id="onlineClCdInP" name="onlineClCdInP" />
					<label for="onlineClCd"></label>
				</td>
			</tr>
			<tr>
				<th>
					<spring:message code="LAB.M07.LAB00209"/><!-- 세금계산서발행여부 -->
				</th>
				<td>
					<input type="checkbox" id="tbiFlgInP" name="tbiFlgInP" />
					<label for="tbiFlg"></label>
				</td>
				<th>
					<spring:message code="LAB.M07.LAB00251"/><!-- 수수료지급여부 -->
				</th>
				<td>
					<input type="checkbox" id="feePvFlgInP" name="feePvFlgInP" />
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