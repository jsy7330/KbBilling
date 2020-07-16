<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><spring:message code="LAB.M15.LAB00014" /></title>

	<tiles:insertAttribute name="js" ignore="true" />
	<link href="/styles/jqgrid/searchFilter.css" rel="stylesheet" type="text/css" />
	<link href="/styles/jqgrid/ui.multiselect.css" rel="stylesheet" type="text/css" />
	<link href="/styles/jqgrid/ui.jqgrid.css" rel="stylesheet" type="text/css" />
	<link href="/styles/tree/ui.dynatree.css" rel="stylesheet" type="text/css" />
	<link href="/styles/nccbs/styles.css" rel="stylesheet" type="text/css" />
	
</head>

<body class="">
	<form name="frmMenuHandle"></form>
	
		<div id="mask"></div>
		<div id="loadingMask"></div>
		<div id="container_wrap">
			<form name="frmLanguage">
				<tiles:insertAttribute name="header" ignore="true" /><!-- Header 부분 -->
			</form>
			<div class="home_wrap"><!-- Body 부분 -->
				<div id="home_right" class="right">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
		<%-- <div id="footer_wrap">
			<tiles:insertAttribute name="footer" />
		</div> --%>
		<div id="loadingImage" class='Loading_wrap' style="display:none;">
		  <p><img src="/images/icon/ajax-loader.gif"/></p>
		</div>
	
</body>
</html>
