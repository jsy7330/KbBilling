<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	$(document).ready(function() {
		
		setData();
		
		byteCheckKeyEvent();	//바이트체크
		settingDatepicker();	//팝업달력 셋팅
		
		$('#userGroupIdUpP').selectmenu({});
		$('#crrTpUpP').selectmenu({});
		$('#useYnUpP').selectmenu({});
		$('#accountLockUpP').selectmenu({});
		
		//조직검색 팝업
		$("#btnOrgPopUpP").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=orgIdUpP&returnId2=orgNmUpP";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		//저장버튼
		$("#btnInsert").click(function() {
			insertData();            
		});
		
		//ip 체크
		$('#ipBandwidthUpP').focusout(function(){
			 checkIP($('#ipBandwidthUpP').val());
		});
		
		//pw주기 변경 이벤트
	    $('#passwordChangePeriodUpP').selectmenu({
	        change: function() {
	        	changePeriod();
	        }
	    });
		
	});
	
	//등록
	function insertData(){
		
		var param = checkValidation();
		
		if(param != false){
			
			$.ajax({
				url : 'updateEmployee.json',
				type : 'POST',
				async: false,
				data : param,
				success : function(data) {
					console.log(data);
					if(data.result != "0"){
						alert('<spring:message code="MSG.M09.MSG00005" />');	//저장되었습니다.
						
						search();//부모창 새로고침
						inputClear();
						
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
		
		var orgIdUpP = $.trim( $("#orgIdUpP").val() );	//조직
		if(orgIdUpP == ''){
			alert('<spring:message code="LAB.M09.LAB00138" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}

		var userIdUpP = $("#userIdUpP").val();		//사용자ID
				
		var userNameUpP = $.trim( $("#userNameUpP").val() );		//사용자명
		if(userNameUpP == ''){
			// 사용자명을 입력해 주세요
			alert('<spring:message code="LAB.M07.LAB00071" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var userGroupIdUpP = $.trim( $("#userGroupIdUpP").val() );		//기본그룹
		if(userGroupIdUpP == ''){
			// 기본그룹을 입력해 주세요
			alert('<spring:message code="LAB.M01.LAB00202" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var crrTpUpP = $("#crrTpUpP").val();		//사용자유형
		if(crrTpUpP == ''){
			// 유형상세를 선택해 주세요
			alert('<spring:message code="LAB.M07.LAB00074" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var useYnUpP = $("#useYnUpP").val();		//사용유무
		if(useYnUpP == ''){
			// 사용유무를 선택해 주세요
			alert('<spring:message code="LAB.M07.LAB00028" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var empNoUpP = $("#empNoUpP").val();		//사번
		var telNoUpP = $("#telNoUpP").val();		//전화번호
		
		var mtelNoUpP = $.trim( $("#mtelNoUpP").val() );		//핸드폰번호
		if(mtelNoUpP == ''){
			// 핸드폰번호를 입력해 주세요
			alert('<spring:message code="LAB.M14.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var eMailUpP = $.trim( $("#eMailUpP").val() );		//이메일
		if(eMailUpP == ''){
			// 이메일을 입력해 주세요
			alert('<spring:message code="LAB.M08.LAB00119" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		// Email형식체크
		if(checkEmailStr(eMailUpP) == false){
			$('#eMailUpP').focus();
			alert('<spring:message code="MSG.M08.MSG00046" />');
			return false;
		}
		
		var ipBandwidthUpP = $.trim( $("#ipBandwidthUpP").val() );		//허용IP대역
		if(ipBandwidthUpP == ''){
			// 허용IP대역를 입력해 주세요
			alert('<spring:message code="LAB.M14.LAB00053" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var passwordDueDateUpP = $.trim( $("#passwordDueDateUpP").val() );		//PW변경예정일
		if(passwordDueDateUpP == ''){
			// PW변경예정일을  입력해 주세요
			alert('<spring:message code="LAB.M14.LAB00053" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var passwordChangePeriodUpP = $("#passwordChangePeriodUpP").val();		//PW주기
		if(passwordChangePeriodUpP == ''){
			// PW주기를 선택해 주세요
			alert('<spring:message code="LAB.M15.LAB00076" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var accountLockUpP = $("#accountLockUpP").val();		//잠김여부
		if(accountLockUpP == ''){
			//잠김여부를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00041" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.orgId = orgIdUpP;				//조직
		param.userId = userIdUpP;			//사용자ID
		param.userName = userNameUpP;		//사용자명
		param.password = eMailUpP;			//패스워드 - 이메일
		param.userGroupId = userGroupIdUpP;	//기본그룹
		param.crrTp = crrTpUpP;				//사용자유형
		param.useYn = useYnUpP;				//사용유무
		param.empNo = empNoUpP;				//사번
		param.telNo = telNoUpP;				//전화번호
		param.mtelNo = mtelNoUpP;			//핸드폰번호
		param.eMail = eMailUpP;				//이메일
		param.ipBandwidth = ipBandwidthUpP;	//허용IP대역
		param.passwordDueDate = dateFormatToStringYYYYMMDD(passwordDueDateUpP);		//PW변경예정일
		param.passwordChangePeriod = passwordChangePeriodUpP;		//PW주기
		param.accountLock = accountLockUpP;	//잠김여부
		
		
		return param;
	}
	
	function checkIP(strIP) {	//IP체크
		var strIP=strIP.split(".");
		if(strIP.length !=4){
			alert('<spring:message code="MSG.M15.MSG00027"/>');
			$('#ipBandwidthUpP').val('');
		   	$('#ipBandwidthUpP').focus();
			return;
		}
		if(( (0<=strIP[0]&&strIP[0]<=255) || strIP[0]=="*" ) && 
				((0<=strIP[1]&&strIP[1]<=255) || strIP[1]=="*") && 
					((0<=strIP[2]&&strIP[2]<=255) || strIP[2]=="*") &&
						((0<=strIP[3]&&strIP[3]<=255) || strIP[3]=="*")){
			
		}else{
			alert('<spring:message code="MSG.M15.MSG00027"/>');
		   	$('#ipBandwidthUpP').val('');
		   	$('#ipBandwidthUpP').focus();
			return;
		}

	}

	function changePeriod(){	//pw주기 선택시 현재날짜에서 pw주기만큼 증가
		var d = new Date();
		 if($('#passwordChangePeriodUpP').val() == '30'){
			 var dt=new Date(d.getFullYear(),(d.getMonth()),d.getDate()+30);
		} 
		if($('#passwordChangePeriodUpP').val() == '60'){
			var dt=new Date(d.getFullYear(),(d.getMonth()),d.getDate()+60);
		} 
		if($('#passwordChangePeriodUpP').val() == '90'){
			var dt=new Date(d.getFullYear(),(d.getMonth()),d.getDate()+90);
		}  
		$('#passwordDueDateUpP').val(dateFormatterUsingDateYYYYMMDD(dt));

	}
	
	function setData(){
		
		$("#orgIdUpP").val($("#orgId").val());	//조직
		$("#orgNmUpP").val($("#orgNm").val());	//조직
		$("#userIdUpP").val($("#userId").val());		//사용자ID
		$("#userNameUpP").val($("#userName").val());		//사용자명
		/* 
		$("#userGroupIdUpP").val($("#userGroupId").val());		//기본그룹
		$("#userGroupNmUpP").val($("#userGroupNm").val());		//기본그룹명
		 */
		 $("#userGroupIdUpP").val($("#userGroupId").val());		//기본그룹
		 
		$("#crrTpUpP").val($("#crrTp").val());		//사용자유형
		$("#useYnUpP").val($("#useYn").val());		//사용유무
		$("#empNoUpP").val($("#empNo").val());		//사번
		$("#telNoUpP").val($("#telNo").val());		//전화번호
		$("#mtelNoUpP").val($("#mtelNo").val());		//핸드폰번호
		$("#eMailUpP").val($("#eMail").val());		//이메일
		// Email형식체크
		$("#ipBandwidthUpP").val($("#ipBandwidth").val());		//허용IP대역
		$("#passwordDueDateUpP").val($("#passwordDueDate").val());		//PW변경예정일
		$("#passwordChangePeriodUpP").val($("#passwordChangePeriod").val());		//PW주기
		$("#accountLockUpP").val($("#accountLock").val());		//잠김여부
		
	}
	
	
</script>


<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M09.LAB00206"/><!-- 직원등록 --></div>
	<a href="#" class="close" >Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M09.LAB00206"/><!-- 직원등록 --></h4>
		</div>
	</div>

	<table class="writeview">
		<colgroup>
			<col style="width:15%;">
			<col style="width:35%;">
			<col style="width:15%;">
			<col style="width:35%;">
		</colgroup>
		<tbody> 
			<tr>
				<th><spring:message code="LAB.M09.LAB00138"/><!-- 조직 --><span class="dot">*</span></th>
				<td colspan="3">
					<div class="date_box">
						<div class="inp_date w150">
							<input type="text" id="orgIdUpP" name="orgIdUpP" disabled="disabled" class="w150 not-active">
						</div>
						<div class="inp_date p100">
							<input type="text" id="orgNmUpP" name="orgNmUpP" disabled="disabled" class="p100 not-active">
							<a href="#" id="btnOrgPopUpP" class="search">search</a>
						</div>
					</div>
				</td>
				
				
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00067"/><!-- 사용자ID --><span class="dot">*</span></th>
				<td>
					<input type="text" id="userIdUpP" name="userIdUpP" class="w170 not-active" disabled="disabled">
					<%-- <a href="#" id="btnCheckIdUpP" name="btnCheckIdUpP" class="td-btn"><spring:message code="LAB.M09.LAB00193"/><!-- 중복체크 --></a> --%>
				</td>
				<th><spring:message code="LAB.M07.LAB00071"/><!-- 직원명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="userNameUpP" name="userNameUpP" class="w200"checkbyte="30" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M13.LAB00013"/><!-- 패스워드 --><span class="dot">*</span></th>
				<td>
					<input type="password" id="passwordUpP" name="passwordUpP" class="w200" disabled="disabled" />  
				</td>
				<th><spring:message code="LAB.M01.LAB00202"/><!-- 기본그룹 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
					<!-- 
						<input type="text" id="userGroupIdUpP" name="userGroupIdUpP" value="" class="w160" />팝업만들어 지면 hidden으로 바꿈
						<input type="text" id="userGroupNmUpP" name="userGroupNmUpP" value="" class="w160" />
						<a href="#" id="btnUserGroupUpP" class="search">search</a>
						 -->
						<select id="userGroupIdUpP" name="userGroupIdUpP" class="w150">
							<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
					        <c:forEach items="${userGroupList}" var="userGroupList" varStatus="status">
								<option value="${userGroupList.userGroupId}">${userGroupList.userGroupName}</option>
							</c:forEach>
						</select>	
						
					</div>                                          
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00074"/><!-- 사용자유형 --><span class="dot">*</span></th>
				<td>
					<select id="crrTpUpP" name="crrTpUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${crrTp}" var="crrTp" varStatus="status">
							<option value="${crrTp.commonCd}">${crrTp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M07.LAB00028"/><!-- 사용유무 --><span class="dot">*</span></th>
				<td>
					<select id="useYnUpP" name="useYnUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00001"/><!-- 사번 --></th>
				<td>
					<input type="text" id="empNoUpP" name="empNoUpP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M09.LAB00065"/><!-- 전화번호 --></th>
				<td>
					<input type="text" id="telNoUpP" name="telNoUpP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00052"/><!-- 핸드폰번호 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="mtelNoUpP" name="mtelNoUpP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M08.LAB00119"/><!-- 이메일 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="eMailUpP" name="eMailUpP" class="w200" checkbyte="50" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00053"/><!-- 허용IP대역 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="ipBandwidthUpP" name="ipBandwidthUpP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M15.LAB00075"/><!-- PW변경예정일 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="passwordDueDateUpP" name="passwordDueDateUpP" class="datepicker" disabled="disabled"/>
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M15.LAB00076"/><!-- PW주기 --><span class="dot">*</span></th>
				<td>
					<select id="passwordChangePeriodUpP" name="passwordChangePeriodUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
						<option value="30">30</option>
						<option value="60">60</option>
						<option value="90">90</option>
					</select>
				</td>
				<th><spring:message code="LAB.M09.LAB00041"/><!-- 잠김여부 --><span class="dot">*</span></th>
				<td>
					<select id="accountLockUpP" name="accountLockUpP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>

</div>
<!--// center -->

<div class="btn_box">
	<a class="grey-btn" href="#" id="btnInsert"><span class="confirm_icon"><spring:message code="LAB.M09.LAB00048" /></span></a>
	<a class="grey-btn close" href="#" id="btnClose"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>