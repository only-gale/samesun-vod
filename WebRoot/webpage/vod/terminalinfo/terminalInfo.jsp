<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>终端信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="terminalInfoController.do?save">
			<input id="id" name="id" type="hidden" value="${terminalInfoPage.id }">
			<input id="status" name="status" type="hidden" value="${terminalInfoPage.status}">
			<input id="nowvideo" name="nowvideo" type="hidden" value="${terminalInfoPage.nowvideo}">
			<input id="subject" name="subject" type="hidden" value="${terminalInfoPage.subject}">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							终端名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${terminalInfoPage.name}" datatype="*">
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							终端描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="descript" name="descript" 
							   value="${terminalInfoPage.descript}">
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							mac地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="macaddress" name="macaddress" 
							   value="${terminalInfoPage.macaddress}" datatype="*">
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							IP地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ipaddress" name="ipaddress" 
							   value="${terminalInfoPage.ipaddress}" datatype="*">
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地理位置:
						</label>
					</td>
					<td class="value">
						<input id="groupid" name="groupid" style="width:160px;"
							   value="${terminalInfoPage.groupid}">
					</td>
				</tr>
				<%-- <tr>
					<td align="right">
						<label class="Validform_label">
							当前状态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="status" name="status" ignore="ignore"
							   value="${terminalInfoPage.status}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							正在观看节目:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="nowvideo" name="nowvideo" ignore="ignore"
							   value="${terminalInfoPage.nowvideo}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会议主题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="subject" name="subject" ignore="ignore"
							   value="${terminalInfoPage.subject}">
						<span class="Validform_checktip"></span>
					</td>
				</tr> --%>
			</table>
		</t:formvalid>
 </body>
 
<script type="text/javascript">
	$(function() {
		$('#groupid').combotree({
			url : 'terminalInfoController.do?getChildren'
		});
	});
</script>