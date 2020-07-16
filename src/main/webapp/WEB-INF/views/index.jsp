<form name="frm"></form>
<script type="text/javascript">
	document.frm.action="<%=request.getContextPath()%>/system/login/login";
	document.frm.method="post";
	document.frm.submit();
</script>