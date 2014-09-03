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
					<td align="right" width="30%">
						<label class="Validform_label">
							终端名:
						</label>
					</td>
					<td class="value" width="70%">
						<input class="inputxt" id="name" name="name" 
							   value="${terminalInfoPage.name}" datatype="*">
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
							   value="${terminalInfoPage.macaddress}" datatype="/[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}/"
							   errormsg="请填写正确的mac地址">
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
							   value="${terminalInfoPage.ipaddress}" datatype="ipv4" errormsg="请填写正确的ip地址">
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地理位置:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="groupid" name="groupid" datatype="*" nullmsg="请选择地理位置" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
						<textarea cols="76" rows="4" style="resize:none" id="descript" name="descript">${terminalInfoPage.descript}</textarea>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
 
<script type="text/javascript">
	$(function() {
		$("#groupid").combotree({
			width : 200,
			url : 'terminalInfoController.do?getChildren',
			onSelect : function(record){
				$("#groupid").combo("setValues",record.id).combo("setText",record.text);
				$("#groupid").val(record.id);
			},
			onLoadSuccess : function(){
				var id = "${terminalInfoPage.groupid}";
				var name = "${terminalInfoPage.groupname}";
				$("#groupid").combotree('setValue', id).combotree('setText', name);
				$("#groupid").val(id);
			}
		});
	});
</script>