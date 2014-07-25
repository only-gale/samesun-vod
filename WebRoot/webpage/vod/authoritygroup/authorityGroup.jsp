<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>终端分组</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
	$(function() {
		$("#terminalIDs").combotree({
			width : '200',
			url : 'authorityGroupController.do?getChildren',
			multiple : true,
			separator : ",",
			cascadeCheck: false
		});
		var str = "${authorityGroupPage.terminalIDs}";
		if(null != str && "" != str){
			$("#terminalIDs").combotree('setValues', str.split(","));
		}
	});
</script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="authorityGroupController.do?save">
			<input id="id" name="id" type="hidden" value="${authorityGroupPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							自定义权限组名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${authorityGroupPage.name}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="desc" name="desc" ignore="ignore"
							   value="${authorityGroupPage.desc}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							终端清单:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="terminalIDs" name="terminalIDs">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>