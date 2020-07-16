/**
 * @fileoverview combobox 옵션 목록 추출
 * @author smyun@ntels.com
 * @version 0.1
 * @since 2011.10.01
 */

/**
 * select tag 의 옵션 목록
 *
 * @param target 목록적용 select id
 * @param command 목록 종류
 * @param option option항목 (A=all, S=select, N=null)
 */
function getOptionItem(target,command,option, code, lng_typ){
	var param = 'command='+command+'&code='+code;
	
	var nlen = arguments.length;
	var i = 3;
	for(i; i < nlen; i++){
		if(arguments[i].value!='')param += "&"+arguments[i].id+'='+arguments[i].value;
	}
	if(lng_typ != null){
		if('A'==option)$(target).html('<option value="all">'+ lng_typ +'</option>');
		if('S'==option)$(target).html('<option value="">' + lng_typ + '</option>');
		if('N'==option)$(target).html('');
	}
	if(lng_typ == null){
		if('A'==option)$(target).html('<option value="all"> 전체 </option>');
		if('S'==option)$(target).html('<option value=""> 선택 </option>');
		if('N'==option)$(target).html('');
	}

	if(''==command){
		return;
	}

	$.post("/cm/common/list", param, function(data) {
		//if(data.error!=null)alert(data.error);
		var array = data;
		var option;

		$.each(array,function(index,appObj) {
			if(command != "user") {
				option = $('<option value="'+appObj.ID+'">'+appObj.NAME+'</option>');
			} else {
				//option = $('<option value="'+appObj.ID+'">['+ appObj.ID + ":" + appObj.NAME+']</option>');
				option = $('<option value="'+appObj.ID+'">'+ appObj.ID + " / " + appObj.NAME+'</option>');
			}
			$(target).append(option);
		});
	});
}

/**
 *	goSearch 위한 함수
 */
function getOptionItemSearch(target, command, option, func){
	var param = 'command='+command;

	var nlen = arguments.length;
	var i = 3;
	for(i; i < nlen; i++){
		if(arguments[i].value!='')param += "&"+arguments[i].id+'='+arguments[i].value;
	}
	if('A'==option)$(target).html('<option value="all">전체</option>');
	if('S'==option)$(target).html('<option value="">선택</option>');
	if('N'==option)$(target).html('');

	if(''==command){
		return;
	}

	$.post("/cm/common/list", param, function(data) {
		//if(data.error!=null)alert(data.error);
		var array = data;
		var option;

		$.each(array,function(index,appObj) {
			if(command != "user") {
				option = $('<option value="'+appObj.ID+'">'+appObj.NAME+'</option>');
			} else {
				//option = $('<option value="'+appObj.ID+'">['+ appObj.ID + ":" + appObj.NAME+']</option>');
				option = $('<option value="'+appObj.ID+'">'+ appObj.ID + " / " + appObj.NAME+'</option>');
			}
			$(target).append(option);
		});
		func.call();
	});
}

function getOptionItemWithDefault(target, command, option, code, defaultValue){
	var param = 'command='+command+'&code='+code;
	
	var nlen = arguments.length;
	var i = 4;
	for(i; i < nlen; i++){
		if(arguments[i]!='')param += "&"+arguments[i];
	}
	if('A'==option)$(target).html('<option value="all">전체</option>');
	if('S'==option)$(target).html('<option value="">선택</option>');
	if('N'==option)$(target).html('');
	
	if(''==command){
		return;
	}

	$.post("/cm/common/list", param, function(data) {
		//if(data.error!=null)alert(data.error);
		var array = data;
		var option;
		
		$.each(array,function(index,appObj) {
			if(command != "user") {
				option = $('<option value="'+appObj.ID+'">'+appObj.NAME+'</option>');
			} else {
				//option = $('<option value="'+appObj.ID+'">['+ appObj.ID + ":" + appObj.NAME+']</option>');
				option = $('<option value="'+appObj.ID+'">'+ appObj.ID + " / " + appObj.NAME+'</option>');
			}
			$(target).append(option);
		});
		
		if(defaultValue!=null && defaultValue!=''){
			$(target).val(defaultValue);
		}
	});
}

/**
 * 상담내역 대부류>중분류>소분류를 위해 추가 생성 
 */
function getOptionItemPart(target,option, code, refcode){
	var param = 'code='+code+'&refcode='+refcode;

	var nlen = arguments.length;
	var i = 3;
	for(i; i < nlen; i++){
		if(arguments[i].value!='')param += "&"+arguments[i].id+'='+arguments[i].value;
	}
	if('A'==option)$(target).html('<option value="all">All</option>');
	if('S'==option)$(target).html('<option value="">Select</option>');
	if('N'==option)$(target).html('');

	if(code==""){
		$(target).html('<option value="">Select</option>');

	}else{
		$.post("/cm/common/detailList", param, function(data) {
			var array = data;
			var option;
			
			$.each(array,function(index,appObj) {
				option = $('<option value="'+appObj.ID+'">'+appObj.NAME+'</option>');
				$(target).append(option);
			});
		});
	}
	
}
