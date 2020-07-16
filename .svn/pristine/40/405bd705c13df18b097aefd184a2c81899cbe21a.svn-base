/* custom_login date. 17.02.07 javascript @nComz*/

//중앙 맞추기
function loginCenter() {
	var children = $('#contents').children();
	if(children.hasClass('login_box') === true) {
		children.parent().addClass('loginWrap');
	};
};
function wrapBackground() {
	var loginBox = $('body').find('.login_box');
	if(loginBox.length == 1) {
//		alert('ture');
		$('html').css('background','#edf2f6');
	} else {
//		alert('false')
	};
	var parent = $('#contents').parent();
	parent.css('overflow','hidden');
	//overflow적용
};

function loginPlaceholder() {
	var idP = $('#loginUserId');
	idP.attr('placeholder','ID');
	var pwP = $('#loginUserPw');
	pwP.attr('placeholder','Password');
};

$(document).ready(function(){
	 loginCenter();
	 wrapBackground();
	 loginPlaceholder();
});