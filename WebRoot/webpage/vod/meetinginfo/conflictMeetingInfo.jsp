<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>冲突的会议信息</title>
</head>
<body style="overflow-y: hidden" scroll="no">
<table>
<thead>
<th>会议主题</th>
<th>创建人</th>
</thead>
<c:forEach var="meeting" items="${conflictMeetings }">
	<tr>
		<td>${meeting.subject }</td>
		<td>${meeting.createBy }</td>
	</tr>
</c:forEach>
</table>
</body>
<script type="text/javascript">
</script>