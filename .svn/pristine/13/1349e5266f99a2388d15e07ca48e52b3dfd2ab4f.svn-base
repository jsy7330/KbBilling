//* custom date. 17.02.07 javascript @nComz*/

function gnbImg() {
	var imgPlace = $('#gnb>li>a');
	imgPlace.prepend('<span></span>')
	var imgSection = $('#gnb>li>a').find('span');
	for( i = 0; i < imgSection.length; i++) {
		var j = i + 1;
		imgSection[i].className = imgSection[i].className + ' ' + 'img_' + j;
	};
};
function gnbOpenPosition() {
	var place = $('ul#gnb li ul');
	$('ul#gnb li, ul#gnb li ul').mouseenter(function() {
		if($(this).children('ul').css('display') == 'block') {
			$(this).parents('#header_wrapper').css('z-index','9999');
			$('.lnbOpen').hide();
			$('.lnbClose').stop().show();
			$('.lnb_btn').stop().animate({left:'13px'},'fast','swing');
			$('.lnb_cover').stop().css('display','block');
			$('.left').stop().animate({left:'-240px'},'fast','swing');
			$('.left').css('z-index','1');
		}
	});
	$('ul#gnb li, ul#gnb li ul').mouseleave(function() {
		if($(this).children('ul').css('display') == 'none') {
			$(this).parents('#header_wrapper').css('z-index','1');
		}
	});
};


function lnbOpeners(){
	var place = $('.left').find('#lnb');
	$('.left').prepend('<div class="lnb_cover"></div>');
	if(place.length == 1){
		$('.left').prepend('<div class="lnb_btn"><div class="lnbClose"></div><div class="lnbOpen"></div></div>');
		$('.lnbClose').click(function() {
			$(this).hide();
			$('.lnbOpen').stop().show();
			$('.lnb_btn').stop().animate({left:'240px'},'fast','swing');
			$('.lnb_cover').stop().css('display','none')
			$('.left').stop().animate({left:'0'},'fast','swing');
			$('.left').css('z-index','9999');
		});
		$('.lnbOpen').click(function() {
			$(this).hide();
			$('.lnbClose').stop().show();
			$('.lnb_btn').stop().animate({left:'13px'},'fast','swing');
			$('.lnb_cover').stop().css('display','block');
			$('.left').stop().animate({left:'-240px'},'fast','swing');
			$('.left').css('z-index','1');
		});
	};
};

function lnbScroll() {
	var positionHeight = 60;
	$(window).on('scroll',function(e){
		var defaultPosition = $(this).scrollTop();
		if(defaultPosition > positionHeight) {
			$('.left').css('position','fixed');
		} else {
			$('.left').css('position','absolute');
		};
	});
};

function dashPosition() {
	var dash = $('.dash');
	if(dash.prev().hasClass('inp_date') === true) {
		dash.appendTo(dash.prev());
		dash.css({position:'absolute',top:'0px',right:'10px'});
	}
};

/*nav home*/
function navHome() {
	var navParent = $('.nav');
	var navChild = navParent.children('.home');
	navChild.text('HOME');
};



// table_tr_length_addClass
function tableColumn() {
	var parents = $('.writeview');
	for(var i = 0; i<parents.length; i++){
		parents[i].className = parents[i].className + ' '+'line_'+i;
		var parent = parents.eq(i);
		var children = parent.find('tr');
		for(var s = 0; s<children.length; s++) {
			var k = s + 1;
			parents[i].className = children[s].className+'writeview'+' ' +'column_'+k;
		};
	};
	$('.writeview').each(function(index){
		var tableIndex = "table"+index;
		$(this).wrapAll('<div class="table_style"></div>');
	});
	$('.writeview').each(function(index){
		var tableIndex = "table"+index;

		$(this).find('tr').each(function(index){
			$(this).addClass('trLine'+' '+(index+1));
		});
		
		$(this).find('col').each(function(index) {
//			console.log(table);
		});
	});
};

// table margin
function tableMargin() {
	var parents = $('.right');
	var children = parents.find('.main_btn_box');
	$('.main_btn_box').each(function(index) {
		$(this).prev('.writeview').css('margin-bottom','0px');
	});
};


function groupBoxHeight() {
	var wrap = $('.group_box').children();
	for(var i = 0; i< wrap.length; i++) {
		var heightSection = wrap[i].children;
		wrap[i].className = wrap[i].className+' '+'sameHeight';
		var maxHeight = -1;
		$('.sameHeight').each(function() {
			maxHeight = maxHeight > $(this).height() ? maxHeight : $(this).height();
		});
		$('.sameHeight').each(function() {
			$(this).height(maxHeight);
		});
	};
};

function colBoxMainBtnHeight() {
	var maxHeight = 0;
	$('.table_col_box .main_btn_box').each(function() {
		maxHeight = Math.max(maxHeight, $(this).height());
	});
	$('.table_col_box .main_btn_box').css({height:maxHeight + 'px'});
};

function selectLanguage() {
	var defaultLanguage = $('#useLanguage-menu');
	var parent = defaultLanguage.parent('.ui-selectmenu-menu');
	parent.addClass('topPosition');
};

function lnbHeight() {
	var htmlHeight = $('html').height();
	var headerHeight = $('#header_wrapper').height();
	var minHeight = htmlHeight - headerHeight;
	var lnbHeight = $('.left').css('min-height',minHeight);
};

function popupMaxheight() {
	var htmlHeight = $(window).outerHeight();
	var activeHeight = htmlHeight-50+'px';
	var _parent = $('.Layer_wrap');
		_parent.css('max-height',activeHeight);

	var allH = _parent.height();
	var topH = _parent.children('.layer_top').height();
	var resultH = allH - topH;
	var _layerB = _parent.children('.layer_box');

	_parent.css('overflow','hidden');
	if(_parent.css('max-height') <= _parent.css('height')) {
		_layerB.outerHeight(resultH);
		_layerB.css({'overflow-y':'scroll','margin':'0px 0px 1px 0px','padding':'20px 20px 1px 20px'});
		_parent.children('form').css('height',resultH);
		_parent.children('form').css({'overflow-y':'scroll'});
		_parent.children('form').find('>.layer_box').css('margin','0px 0px 1px 0px');
	};
	if(_parent.css('max-height') > _parent.css('height')) {
		_layerB.height('');
		_layerB.outerHeight('');
		_layerB.css({'overflow-y':'auto','margin':'0px 0px 1px 0px','padding':'15px 15px 1px 15px'});
		_parent.children('form').height('');
		_parent.children('form').outerHeight('');
		_parent.children('form').css({'overflow-y':'auto'});
		_parent.children('form').find('>.layer_box').css({'margin':'0px 0px 1px 0px'});

		var reResultH = _parent.height() - _parent.children('.layer_top').height();
		_layerB.outerHeight(reResultH);
		_parent.children('form').outerHeight(reResultH);
	};
};

function tabCustomClick() {
	$(".tab_content").hide();
	var _thisParent = $('ul.tabs').next('.tab_box');
	var _thisFirst = _thisParent.find('.tab_content:first');
	_thisFirst.show();
    $("ul.tabs li").click(function () {
		var _this = $(this);
		var _parent = _this.parent('ul.tabs');
		_parent.find('li').removeClass('active').css('color','#8b8d90');
		$(this).addClass("active").css('color','#2c7ed9');
        var _targetDiv = _parent.next('.tab_box');
		var _targetC = _targetDiv.find('.tab_content');
		_targetC.hide();
        var activeTab = $(this).attr("rel");
        _targetDiv.find("#" + activeTab).fadeIn();
    });

};

function tableReload() {
	$('select').selectmenu();
	$('select').each(function(index){
		var selClass = $(this).attr('class');
		$(this).next('.ui-selectmenu-button').addClass(selClass);
	});
};

function errorPage() {
	var _parent = $('.right');
	var _child = $('.error_default');
	var _height = _parent.height() - _child.height();
	_child.css('margin-top',_height/2);
};

$(document).ready(function(){
	gnbImg();
	lnbOpeners();
	navHome();
	lnbScroll();
	selectLanguage();
	tableMargin();
	colBoxMainBtnHeight();
	lnbHeight();
	gnbOpenPosition();
	popupMaxheight();
	tabCustomClick();
	errorPage();
});

$(window).on('load',function() {
	tableReload();
});

$(window).resize(function() {
	popupMaxheight();
	lnbHeight();
	$('select').selectmenu("close");
	
});