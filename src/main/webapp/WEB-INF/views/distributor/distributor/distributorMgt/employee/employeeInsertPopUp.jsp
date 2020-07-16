<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	
	var checkId = "N";		//ID중복체크
	var checkIdValue = ""	//체크한 ID값
	
	$(document).ready(function() {
	
		byteCheckKeyEvent();	//바이트체크
		settingDatepicker();	//팝업달력 셋팅
		
		var dt = new Date();
		dt.setDate(dt.getDate() + 30);
		var yyyymmdd = $.datepicker.formatDate('yymmdd', dt);
		$("#passwordDueDateInP").val(stringToDateformatYYYYMMDD(yyyymmdd));
		
		$('#userGroupIdInP').selectmenu({});
		$('#crrTpInP').selectmenu({});
		$('#useYnInP').selectmenu({});
		$('#passwordChangePeriodInP').selectmenu({});
		$('#accountLockInP').selectmenu({});
		$('#passwordChangePeriodInP').val("30");
		$("#passwordChangePeriodInP" ).selectmenu( "refresh" );	//셀렉트 박스 수정후 리플레쉬
		$("#passwordChangePeriodInP").selectmenu("disable");
		
		//조직검색 팝업
		$("#btnOrgPopInP").click(function() {
			
			var url="/system/common/common/organizationMng/organizationSearchPopup.ajax";
			url = url + "?orgId=" + "" + "&popType=o&returnId1=orgIdInP&returnId2=orgNmInP";
			
			var width = 810;
			var height = 550;
			var LeftPosition=(screen.width-width)/2;
			var TopPosition=(screen.height-height)/2;
			
			window.open(url,"","width="+width+", height="+height+",left="+LeftPosition+",top="+TopPosition+",scrollbars=no,location=no");
			
		});
		
		//ID중복체크 팝업
		$("#btnCheckIdInP").click(function() {
			checkUserId();
		});
		
		//저장버튼
		$("#btnInsert").click(function() {
			insertData();            
		});
		
		//ip 체크
		$('#ipBandwidthInP').focusout(function(){
			 checkIP($('#ipBandwidthInP').val());
		});
		
		//pw주기 변경 이벤트
	    $('#passwordChangePeriodInP').selectmenu({
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
				url : 'insertEmployee.json',
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
		
		var orgIdInP = $.trim( $("#orgIdInP").val() );	//조직
		if(orgIdInP == ''){
			alert('<spring:message code="LAB.M09.LAB00138" /> <spring:message code="MSG.M08.MSG00042" />');	//사업을 선택하세요
			return false;
		}

		var userIdInP = $("#userIdInP").val();		//사용자ID
		if(checkId == 'N' || userIdInP != checkIdValue){
			alert('<spring:message code="MSG.M08.MSG00003" />');	//아이디 중복 체크를 해주세요.
			return false;
		}
		
		var userNameInP = $.trim( $("#userNameInP").val() );		//사용자명
		if(userNameInP == ''){
			// 사용자명을 입력해 주세요
			alert('<spring:message code="LAB.M07.LAB00071" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var userGroupIdInP = $.trim( $("#userGroupIdInP").val() );		//기본그룹
		if(userGroupIdInP == ''){
			// 기본그룹을 입력해 주세요
			alert('<spring:message code="LAB.M01.LAB00202" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var crrTpInP = $("#crrTpInP").val();		//사용자유형
		if(crrTpInP == ''){
			// 유형상세를 선택해 주세요
			alert('<spring:message code="LAB.M07.LAB00074" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var useYnInP = $("#useYnInP").val();		//사용유무
		if(useYnInP == ''){
			// 사용유무를 선택해 주세요
			alert('<spring:message code="LAB.M07.LAB00028" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var empNoInP = $("#empNoInP").val();		//사번
		var telNoInP = $("#telNoInP").val();		//전화번호
		
		var mtelNoInP = $.trim( $("#mtelNoInP").val() );		//핸드폰번호
		if(mtelNoInP == ''){
			// 핸드폰번호를 입력해 주세요
			alert('<spring:message code="LAB.M14.LAB00052" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var eMailInP = $.trim( $("#eMailInP").val() );		//이메일
		if(eMailInP == ''){
			// 이메일을 입력해 주세요
			alert('<spring:message code="LAB.M08.LAB00119" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		// Email형식체크
		if(checkEmailStr(eMailInP) == false){
			$('#eMailInP').focus();
			alert('<spring:message code="MSG.M08.MSG00046" />');
			return false;
		}
		
		var ipBandwidthInP = $.trim( $("#ipBandwidthInP").val() );		//허용IP대역
		if(ipBandwidthInP == ''){
			// 허용IP대역를 입력해 주세요
			alert('<spring:message code="LAB.M14.LAB00053" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var passwordDueDateInP = $.trim( $("#passwordDueDateInP").val() );		//PW변경예정일
		if(passwordDueDateInP == ''){
			// PW변경예정일을  입력해 주세요
			alert('<spring:message code="LAB.M14.LAB00053" /> <spring:message code="MSG.M08.MSG00043" />');
			return false;
		}
		
		var passwordChangePeriodInP = $("#passwordChangePeriodInP").val();		//PW주기
		if(passwordChangePeriodInP == ''){
			// PW주기를 선택해 주세요
			alert('<spring:message code="LAB.M15.LAB00076" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		var accountLockInP = $("#accountLockInP").val();		//잠김여부
		if(accountLockInP == ''){
			//잠김여부를 선택해 주세요
			alert('<spring:message code="LAB.M09.LAB00041" /> <spring:message code="MSG.M08.MSG00042" />');
			return false;
		}
		
		param.orgId = orgIdInP;				//조직
		param.userId = userIdInP;			//사용자ID
		param.userName = userNameInP;		//사용자명
		param.password = eMailInP;			//패스워드 - 이메일
		param.userGroupId = userGroupIdInP;	//기본그룹
		param.crrTp = crrTpInP;				//사용자유형
		param.useYn = useYnInP;				//사용유무
		param.empNo = empNoInP;				//사번
		param.telNo = telNoInP;				//전화번호
		param.mtelNo = mtelNoInP;			//핸드폰번호
		param.eMail = eMailInP;				//이메일
		param.ipBandwidth = ipBandwidthInP;	//허용IP대역
		param.passwordDueDate = dateFormatToStringYYYYMMDD(passwordDueDateInP);		//PW변경예정일
		param.passwordChangePeriod = passwordChangePeriodInP;		//PW주기
		param.accountLock = accountLockInP;	//잠김여부
		
		return param;
	}
	
	//아이디 중복체크
	function checkUserId(){
		
		var param = new Object();
		param.userId = $.trim( $("#userIdInP").val() );
		var idCount = ""; 
		
		/* 
		if(param.userId.length < 8){
			alert("ID를 8자리 이상 입력해 주세요");
			return;
		}
		 */
		
		$.ajax({
			url : 'checkUserId.json',
			type : 'POST',
			async: false,
			data : param,
			success : function(data) {
				console.log(data);
				idCount = data.idCount;
			},
		    error: function(e){
		        alert(e.reponseText);
		    },
			complete : function() {
			}
		});
		
		if(idCount != "0"){	//중복
			checkId = "N";
			alert("<spring:message code='MSG.M15.MSG00024' />");	//ID가 중복 되었습니다.
		}else{
			checkId = "Y";
			checkIdValue = $.trim( $("#userIdInP").val() );
			alert("<spring:message code='MSG.M07.MSG00010' />");	//사용가능한 ID 입니다.
		}
	}
	
	function checkIP(strIP) {	//IP체크
		var strIP=strIP.split(".");
		if(strIP.length !=4){
			alert('<spring:message code="MSG.M15.MSG00027"/>');
			$('#ipBandwidthInP').val('');
		   	$('#ipBandwidthInP').focus();
			return;
		}
		if(( (0<=strIP[0]&&strIP[0]<=255) || strIP[0]=="*" ) && 
				((0<=strIP[1]&&strIP[1]<=255) || strIP[1]=="*") && 
					((0<=strIP[2]&&strIP[2]<=255) || strIP[2]=="*") &&
						((0<=strIP[3]&&strIP[3]<=255) || strIP[3]=="*")){
			
		}else{
			alert('<spring:message code="MSG.M15.MSG00027"/>');
		   	$('#ipBandwidthInP').val('');
		   	$('#ipBandwidthInP').focus();
			return;
		}

	}

	function changePeriod(){	//pw주기 선택시 현재날짜에서 pw주기만큼 증가
		var d = new Date();
		 if($('#passwordChangePeriodInP').val() == '30'){
			 var dt=new Date(d.getFullYear(),(d.getMonth()),d.getDate()+30);
		} 
		if($('#passwordChangePeriodInP').val() == '60'){
			var dt=new Date(d.getFullYear(),(d.getMonth()),d.getDate()+60);
		} 
		if($('#passwordChangePeriodInP').val() == '90'){
			var dt=new Date(d.getFullYear(),(d.getMonth()),d.getDate()+90);
		}  
	 
		$('#passwordChangePeriodInP').val(dateFormatterUsingDateYYYYMMDD(dt));

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
							<input type="text" id="orgIdInP" name="orgIdInP" disabled="disabled" class="w150 not-active">
						</div>
						<div class="inp_date p100">
							<input type="text" id="orgNmInP" name="orgNmInP" disabled="disabled" class="p100 not-active">
							<a href="#" id="btnOrgPopInP" class="search">search</a>
						</div>
					</div>
				</td>
				
				
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00067"/><!-- 사용자ID --><span class="dot">*</span></th>
				<td>
					<input type="text" id="userIdInP" name="userIdInP" class="w170" checkbyte="20" />
					<a href="#" id="btnCheckIdInP" name="btnCheckIdInP" class="td-grey-btn"><spring:message code="LAB.M09.LAB00193"/><!-- 중복체크 --></a>
				</td>
				<th><spring:message code="LAB.M07.LAB00071"/><!-- 직원명 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="userNameInP" name="userNameInP" class="w200" checkbyte="30" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M13.LAB00013"/><!-- 패스워드 --><span class="dot">*</span></th>
				<td>
					<input type="password" id="passwordInP" name="passwordInP" class="w200" disabled="disabled" />  
				</td>
				<th><spring:message code="LAB.M01.LAB00202"/><!-- 기본그룹 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
					<!-- 
						<input type="text" id="userGroupIdInP" name="userGroupIdInP" value="ADMIN" class="w160" />팝업만들어 지면 hidden으로 바꿈
						<input type="text" id="userGroupNmInP" name="userGroupNmInP" value="ADMIN" class="w160" />
						<a href="#" id="btnUserGroupInP" class="search">search</a>
						 -->
						 
						<select id="userGroupIdInP" name="userGroupIdInP" class="w150">
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
					<select id="crrTpInP" name="crrTpInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
				        <c:forEach items="${crrTp}" var="crrTp" varStatus="status">
							<option value="${crrTp.commonCd}">${crrTp.commonCdNm}</option>
						</c:forEach>
					</select>
				</td>
				<th><spring:message code="LAB.M07.LAB00028"/><!-- 사용유무 --><span class="dot">*</span></th>
				<td>
					<select id="useYnInP" name="useYnInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M07.LAB00001"/><!-- 사번 --></th>
				<td>
					<input type="text" id="empNoInP" name="empNoInP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M09.LAB00065"/><!-- 전화번호 --></th>
				<td>
					<input type="text" id="telNoInP" name="telNoInP" class="w200" checkbyte="20" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00052"/><!-- 핸드폰번호 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="mtelNoInP" name="mtelNoInP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M08.LAB00119"/><!-- 이메일 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="eMailInP" name="eMailInP" class="w200" checkbyte="50" />
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M14.LAB00053"/><!-- 허용IP대역 --><span class="dot">*</span></th>
				<td>
					<input type="text" id="ipBandwidthInP" name="ipBandwidthInP" class="w200" checkbyte="20" />
				</td>
				<th><spring:message code="LAB.M15.LAB00075"/><!-- PW변경예정일 --><span class="dot">*</span></th>
				<td>
					<div class="inp_date w200">
						<input type="text" id="passwordDueDateInP" name="passwordDueDateInP" class="datepicker" disabled="disabled"/>
						<a href="#" class="btn_cal"></a> 
					</div>
				</td>
			</tr>
			<tr>
				<th><spring:message code="LAB.M15.LAB00076"/><!-- PW주기 --><span class="dot">*</span></th>
				<td>
					<select id="passwordChangePeriodInP" name="passwordChangePeriodInP" class="w150">
						<option value=""><spring:message code="LAB.M07.LAB00195"/></option>
						<option value="30">30</option>
						<option value="60">60</option>
						<option value="90">90</option>
					</select>
				</td>
				<th><spring:message code="LAB.M09.LAB00041"/><!-- 잠김여부 --><span class="dot">*</span></th>
				<td>
					<select id="accountLockInP" name="accountLockInP" class="w150">
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