<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>冲突的节目信息</title>
<t:base type="jquery"></t:base>
<style type="text/css">
table.altrowstable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
.oddrowcolor{
	background-color:#d4e3e5;
}
.evenrowcolor{
	background-color:#c3dde0;
}
</style>
</head>
<body>
<table class="altrowstable" width="100%">
<thead>
<th>主题</th>
<th>类别</th>
<th>创建人</th>
</thead>
<c:forEach var="meeting" items="${conflictMeetings }">
	<tr>
		<td align="center">${meeting.subject }</td>
		<td align="center"><c:choose> <c:when test="${meeting.rightid eq 1 }">会议</c:when> <c:otherwise>培训</c:otherwise> </c:choose> </td>
		<td align="center">${meeting.createBy }</td>
	</tr>
</c:forEach>
</table>
</body>
<script type="text/javascript">
$(function(){
	var rows = $("tr");
	for(i = 0; i < rows.length; i++){          
		if(i % 2 == 0){
			rows[i].className = "evenrowcolor";
		}else{
			rows[i].className = "oddrowcolor";
		}      
	}
});

</script>