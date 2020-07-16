/**
 * @fileoverview 메뉴 (url, topmenu, leftmenu) 이동
 * @author smyun@ntels.com
 * @version 0.1
 * @since 2011.10.01  
 */

/**
 * url을 이용한 페이지 이동
 * 
 * @param url
 */
function goMenuPage(url) {
	if(url==null || url=='')return;
	var f = makeform(url);
	
	f.submit();
}

/**
 * top메뉴 클릭 이동
 * 
 * @param menuNo 레벨 2 메뉴번호
 * @param url 이동 URL
 */
function goTopMenu(menuNo, url, selectMenuNo, selectMenuNm, topMenuNo, topMenuNm) {
	
	var f = makeform(url);
	
	f.appendChild(AddData('menuNo', menuNo));
	f.appendChild(AddData('selectMenuNo', selectMenuNo));
	f.appendChild(AddData('selectMenuNm', selectMenuNm));
	f.appendChild(AddData('topMenuNo', topMenuNo));
	f.appendChild(AddData('topMenuNm', topMenuNm));
	f.submit();
}

/**
 * left 메뉴 클릭 이동
 * 
 * @param url 이동 url
 * @param menuNo 메뉴번호
 */

function goLeftMenuPage(menuNo, url, selectMenuNo, selectMenuNm, topMenuNo, topMenuNm) {
	if(url==null || url=='')return;
	
	var f = makeform(url);
	f.appendChild(AddData('menuNo', menuNo));
	f.appendChild(AddData('selectMenuNo', selectMenuNo));
	f.appendChild(AddData('selectMenuNm', selectMenuNm));
	f.appendChild(AddData('topMenuNo', topMenuNo));
	f.appendChild(AddData('topMenuNm', topMenuNm));
	f.submit();
}

/**
 * menu 이동 form을 참조하여 return
 * 
 * @param ActionURL 이동 url
 * @returns document.frmMenuHandle
 */
function makeform(ActionURL) {
	var f = document.frmMenuHandle;
	
	while (f.hasChildNodes()) {   
	    f.removeChild(f.firstChild);
	}
	
	f.action=ActionURL;
	f.method="post";
	f.target="";
	return f;
}

/**
 * input 태그 생성하여 return
 * 
 * @param name name 
 * @param value 변수값
 * @returns var i = document.createElement("input")
 */
function AddData(name, value) {
	var i = document.createElement("input");
	i.setAttribute("type", "hidden");
	i.setAttribute("name", name);
	i.setAttribute("value", value);
	return i;
}


/**
 * 페이지 이동  (get 방식)
 *  
 * @param target	결과가 출력될 div id
 * @param ur	호출 url
 * @param page	이동 페이지
 * @param perPage	페이지당 출력 article 수
 * @param perSize	페이지 이동 버튼 갯수
 */
function goPage(target,url,page,perPage,perSize){
	var param = '';
	param+="page="+page;
	if(perPage!=null)param+="&perPage="+perPage;
	if(perSize!=null)param+="&perSize="+perSize;
	
	var nlen = arguments.length;
	var i = 5;
	for(i; i < nlen; i++){
		param += "&"+arguments[i].id+'='+arguments[i].value;
	}
	$.get(url, param, function(data) {
		$(target).html(data);
	});
}

/**
 * 페이지 이동 (port 방식)
 * 
 * @param target	결과가 출력될 div id
 * @param ur	호출 url
 * @param page	이동 페이지
 * @param perPage	페이지당 출력 article 수
 * @param perSize	페이지 이동 버튼 갯수
 */
function goPostPage(target,url,page,perPage,perSize){
	var param = '';
	param+="page="+page;
	if(perPage!=null)param+="&perPage="+perPage;
	if(perSize!=null)param+="&perSize="+perSize;
	
	var nlen = arguments.length;
	var i = 5;
	for(i; i < nlen; i++){
		param += "&"+arguments[i].id+'='+arguments[i].value;
	}
	$.post(url, param, function(data) {
		$(target).html(data);
	});
}