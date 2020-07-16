var lng = '<%= session.getAttribute( "sessionLanguage" ) %>';

$("document").ready(function(){
	$(document).on("click", "[href='#']", function(){
		return false;
	});
	

	//ajax Load
	$( document ).ajaxStart(function(e) {
		//화면의 높이와 너비를 구한다.
		var maskHeight = $(document).height();  
		var maskWidth = $(window).width();  
		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
		$('#loadingMask').css({'height':maskHeight, 'top': '0px', 'left' : '0px'});
		$('#loadingMask').fadeTo(0,0.1);  
		$('#loadingImage').removeAttr('style');
		e.preventDefault();
	});
	$( document ).ajaxStop(function(e) {
		e.preventDefault();
		 $('#loadingMask').hide();  
		 $('#loadingImage').css({"display":"none"});
		
	});
	
// select //////////////////////////////////////////////////////
	$('select').selectmenu();
	$('select').each(function(index){
		var selClass = $(this).attr('class');
		$(this).next('.ui-selectmenu-button').addClass(selClass);
		//$(".ui-selectmenu-menu").eq(index).addClass(selClass);
	});
	function toggleMenu($target) {
		$target.click(function(){
			var $subLnb = $target.next("ul");
			var $clickSubLnb = $(this).next("ul");
			var $clickList = $(this).parent("li");
			$subLnb.stop().animate({"height" : "0"}, 200);
			$target.parent().removeClass("on");
			if ($clickSubLnb.height()==0){
				$clickSubLnb.css({"height" : "auto"});
				var subLnbH = $clickSubLnb.height();
				$clickSubLnb.css({
					"height" : "0"
				});
				$clickList.addClass("on");
				$clickSubLnb.stop().animate({"height" : subLnbH+"px"}, 400);
			}else{
				$clickList.removeClass("on");
				$clickSubLnb.stop().animate({"height" : "0"}, 200);
			}
			if ($clickList.hasClass("sub_none")){
				$clickList.addClass("on");
			}
		});

// datepicker/////////////////////////////////////////////////
	if($(".datepicker").length > 0){
		$( ".datepicker" ).datepicker({
		      changeMonth: true,
		      changeYear: true,
			  yearSuffix:'',//custom_20170307
		      regional:lng
		    });
	}
	$('.inp_date .btn_cal').click(function(e){e.preventDefault();$(this).prev().focus();});
	$( ".datepicker.disabled" ).datepicker( "option", "disabled", true );

	}
	toggleMenu($("#lnb > ul > li > a"));
});
// end //////////////////////////////////////////////////////
// tab //////////////////////////////////////////////////////
// delete and new create_custom.js
//$(function () {
//
//    $(".tab_content").hide();
//    $(".tab_content:first").show();
//
//    $("ul.tabs li").click(function () {
//		$("ul.tabs li").removeClass("active").css('color','#8b8d90');
//		$(this).addClass("active").css('color','#2c7ed9');
////		 custom_color_20170227
////        $("ul.tabs li").removeClass("active").css("color", "#b2b2b2");
////        $(this).addClass("active").css({"color": "#8ab700","font-weight": ""});
//        //$(this).addClass("active").css("color", "#8ab700");
//        $(".tab_content").hide()
//        var activeTab = $(this).attr("rel");
//        $("#" + activeTab).fadeIn()
//    });
//});




/////////////////////////////////////////////////////////////
// 레이어 팝업 ///////////////////////////////////////////////
	function wrapWindowByMask(){
		//화면의 높이와 너비를 구한다.
		var maskHeight = $(document).height();  
		var maskWidth = $(window).width();  

		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
		//$('#mask').css({'height':maskHeight});  

		//애니메이션 효과 - 일단 1초동안 까맣게 됐다가 80% 불투명도로 간다.
		$('#mask').fadeIn(100);      
		$('#mask').fadeTo("slow",0.5);    

		//윈도우 같은 거 띄운다.
		$('.Layer_wrap').show();
		layerW();
		popupMaxheight();
	};
	$(document).ready(function(){
		//검은 막 띄우기
		$('.openMask').click(function(e){
			
			e.preventDefault();
			wrapWindowByMask();
		});

		//닫기 버튼을 눌렀을 때
		$('.Layer_wrap ').on('click','.close',function (e) {
		    //링크 기본동작은 작동하지 않도록 한다.
		    e.preventDefault();  
		    $('#mask, .Layer_wrap').hide();
			$('.Layer_wrap').width('');//custom_170309 @lee
		});

		//검은 막을 눌렀을 때
		$('#mask').click(function () {  
		    $(this).hide();   
		    $('.Layer_wrap').hide();
		    $('.Layer_wrap').width('');//custom_170309 @lee
		});      
	});

//////////////////////////////////////////////////////////////
//tooltip/////////////////////////////////////////////////////
//         $(function() {
//            $("input").tooltip();
//            $("a").tooltip();
//            $("th").tooltip();
//            $("td").tooltip();
//            $("ul.tabs li").tooltip();
//         });
///tooltip end////////////////////////////////////////////////
///file-upload////////////////////////////////////////////////
$(document).ready(function(){
  var fileTarget = $('.filebox .upload-hidden');

  fileTarget.on('change', function(){  // 값이 변경되면
    if(window.FileReader){  // modern browser
      var filename = $(this)[0].files[0].name;
    } 
    else {  // old IE
      var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
    }
    
    // 추출한 파일명 삽입
    $(this).siblings('.upload-name').val(filename);
  });
});
/////////////////////////////////////////////////////////////
///버튼 비활성화/////////////////////////////////////////////
 $(function() {
 
$('.not-active').click (function () {

return false; 
});
});
/////////////////////////////////////////////////////////////

 
function ajaxErrorCallback(err){
	if(err.hasOwnProperty('responseJSON') && err.responseJSON.exceptionMsg != null && err.responseJSON.exceptionMsg != ''){
		alert(err.responseJSON.exceptionMsg);
	}else{
		location.href = "/system/login/login";
	}
}

/**
 * Window Popup Callback
 */
var RunCallbackFunction = function() { }; 

/*
	jqgrid 데이터 조회
*/
function findRow(fGrid, fColid, fData, fStRow){ 
    var ret = fGrid.jqGrid('getRowData'); 
    var rowCnt = fGrid.jqGrid('getGridParam', 'reccount'); 
    var idx = 0; 
    if(!(fStRow == undefined || fStRow == null)){ 
        idx = fStRow*1; 
    } 
    var data = ""; 
    for(idx; idx<rowCnt; idx++){ 
        data = ret[idx][fColid]; 
        if(data.indexOf(fData) > -1){ 
            return (idx+1)+""; 
        } 
    } 
}

/*
 * 버튼 비활성화 처리
 */
function btnDisable(id){
	$('#' + id ).addClass('white-btn');
	$('#' + id ).removeClass('grey-btn');
	$('#' + id ).addClass('not-active');
	$('#' + id ).attr('disabled',true);
}

/*
 * 버튼 활성화 처리
 */
function btnEnable(id){
	$('#' + id ).addClass('grey-btn');
	$('#' + id ).removeClass('white-btn');
	$('#' + id ).removeClass('not-active');
	$('#' + id ).removeAttr('disabled');
}

//custom_170309 @lee
function layerW(){
	var _parent = document.getElementById('popup_dialog');
	var _parent_clientW = _parent.clientWidth;
	var _parent_offsetW = _parent.offsetWidth;
	var _parent_blank = _parent_offsetW - _parent_clientW;
	var _parent_Allw = _parent_clientW + _parent_blank;
//	console.log(_parent_clientW);
//	console.log(_parent_offsetW);
//	console.log(_parent_blank);
//	console.log(_parent_Allw);

	var _parent_clientH = _parent.clientHeight;
	var _parent_offsetH = _parent.offsetHeight;
	var _parent_scrollH = _parent.scrollHeight;
//height값으로 다시
	var $_parent = $('.Layer_wrap');
	var $_children = $_parent.children('.layer_top');
	$.fn.hasScrollbar = function() {
		return this.get(0).scrollHeight > this.get(0).clientHeight;
	};
	if(_parent.scrollHeight > _parent.clientHeight) {
		$('.Layer_wrap').width('');
		$('.Layer_wrap').outerWidth(_parent_Allw);
	};
	if(_parent.scrollHeight == _parent.clientHeight) {
		$('.Layer_wrap').width('');
		$('.Layer_wrap').outerWidth(_parent_clientW);
	}
};

//$(window).resize(function() {
//	var _parent = document.getElementById('popup_dialog');
//	var _parent_clientW = _parent.clientWidth;
//	var _parent_offsetW = _parent.offsetWidth;
//	var _parent_blank = _parent_offsetW - _parent_clientW;
//	var _parent_Allw = _parent_clientW + _parent_blank;
//	if(_parent.scrollHeight > _parent.clientHeight || _parent.scrollHeight == _parent.clientHeight) {
//	$('.Layer_wrap').location.css();
//	return;
//	};
//});


/*
	jqgrid 데이터 조회
*/
function findRow(fGrid, fColid, fData, fStRow){ 
    var ret = fGrid.jqGrid('getRowData'); 
    var rowCnt = fGrid.jqGrid('getGridParam', 'reccount'); 
    var idx = 0; 
    if(!(fStRow == undefined || fStRow == null)){ 
        idx = fStRow*1; 
    } 
    var data = ""; 
    for(idx; idx<rowCnt; idx++){ 
        data = ret[idx][fColid]; 
        if(data.indexOf(fData) > -1){ 
            return (idx+1)+""; 
        } 
    } 
}

