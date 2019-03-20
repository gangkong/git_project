<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript">
$(document).ready(function(){

  $("#home").click(function(){ location.href="<%=request.getContextPath()%>/home.html"; });

  });
</script>

<%-- <h2 style="text-align: center;"><img src="${pageContext.request.contextPath}/image/Kong HR.gif" id="home"></h2>  --%>
<h2 style="text-align: center;" id="home"><font style="font-size: 70px; font-weight: bold; color: #626262">Kong HR</font></h2>

