<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tag/ntels.tld" prefix="ntels" %>

<script type="text/javascript">

</script>

<!-- title -->
<div class="layer_top">
	<div class="title"><spring:message code="LAB.M15.LAB00106"/>&nbsp;<spring:message code="LAB.M08.LAB00204"/></div>
	<a href="#" id="btnCustSearchClose" class="close">Close</a>
</div>
<!--// title -->

<!-- center -->
<div class="layer_box">
	<div class="ly_btn_box">
		<div class="fl">
			<h4 class="ly_title"><spring:message code="LAB.M15.LAB00106"/>&nbsp;<spring:message code="LAB.M08.LAB00204"/></h4>
		</div>
	</div>            
	               
	<table class="writeview">
			<tr>
				<td>
					<textarea id="stmt" name="stmt" class="w800 h300">
							SELECT
								 A.MM,
								 SUM(A.T_CNT) AS "T_CNT",
								 SUM(A.B_CNT) AS "B_CNT",
								 SUM(A.V_CNT) AS "V_CNT",
								 SUM(A.E_CNT) AS "E_CNT",
								 SUM(A.P_CNT) AS "P_CNT"
							FROM (
								 SELECT
								 TO_CHAR(ADD_MONTHS(TO_DATE( :SEARCH_DD,'YYYYMMDD'),-2),'YYYYMM') AS  MM,
								 1 T_CNT,
								 CASE WHEN BASIC_PROD_FL = 'B' THEN 1
									 ELSE 0
								 END   B_CNT,
								 CASE WHEN BASIC_PROD_FL = 'V' THEN 1
									 ELSE 0
								 END   V_CNT,
								 CASE WHEN BASIC_PROD_FL = 'E' THEN 1
									 ELSE 0
								 END   E_CNT,
								 CASE WHEN BASIC_PROD_FL = 'P' THEN 1
									 ELSE 0
								 END   P_CNT
								 FROM  TPMPD_PROD
								 WHERE 1=1
								 AND   SO_ID  = '00'
								 AND   INACT_DT= '99991231'
								 AND   ACT_DT<=TO_CHAR(ADD_MONTHS(TO_DATE( :SEARCH_DD,'YYYYMMDD'),-2),'YYYYMM')||'31'
								 UNION ALL
								 SELECT
								 TO_CHAR(ADD_MONTHS(TO_DATE( :SEARCH_DD,'YYYYMMDD'),-1),'YYYYMM') AS  MM,
								 1 T_CNT,
								 CASE WHEN BASIC_PROD_FL = 'B' THEN 1
									 ELSE 0
								 END   B_CNT,
								 CASE WHEN BASIC_PROD_FL = 'V' THEN 1
									 ELSE 0
								 END   V_CNT,
								 CASE WHEN BASIC_PROD_FL = 'E' THEN 1
									 ELSE 0
								 END   E_CNT,
								 CASE WHEN BASIC_PROD_FL = 'P' THEN 1
									 ELSE 0
								 END   P_CNT
								 FROM  TPMPD_PROD
								 WHERE 1=1
								 AND   SO_ID  = '00'
								 AND   INACT_DT= '99991231'
								 AND   ACT_DT<= TO_CHAR(ADD_MONTHS(TO_DATE( :SEARCH_DD,'YYYYMMDD'),-1),'YYYYMM')||'31'
								 UNION ALL
								 SELECT
								 SUBSTR( :SEARCH_DD,1,6) AS  MM,
								 1 T_CNT,
								 CASE WHEN BASIC_PROD_FL = 'B' THEN 1
									 ELSE 0
								 END   B_CNT,
								 CASE WHEN BASIC_PROD_FL = 'V' THEN 1
									 ELSE 0
								 END   V_CNT,
								 CASE WHEN BASIC_PROD_FL = 'E' THEN 1
									 ELSE 0
								 END   E_CNT,
								 CASE WHEN BASIC_PROD_FL = 'P' THEN 1
									 ELSE 0
								 END   P_CNT
								 FROM  TPMPD_PROD
								 WHERE 1=1
								 AND   SO_ID  = '00'
								 AND   INACT_DT= '99991231'
								 AND   ACT_DT<= :SEARCH_DD
							) A GROUP BY A.MM ORDER BY A.MM ASC

					</textarea>
				</td>
			</tr>
		</thead>
	</table> 
</div>
<!--// center -->
<div class="btn_box">
      <a class="grey-btn close" id="btnCustSearchClose" href="#"><span class="cancel_icon"><spring:message code="LAB.M03.LAB00027" /></span></a>
</div>
