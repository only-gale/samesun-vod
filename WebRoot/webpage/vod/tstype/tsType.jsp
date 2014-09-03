<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>数据字典</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tsTypeController.do?save">
			<input id="id" name="id" type="hidden" value="${tsTypePage.id }">
			<%-- <input id="TSType.id" name="TSType.id" type="hidden" value="${tsTypePage.TSType.id }"> --%>
			<input id="TSTypegroup.id" name="TSTypegroup.id" type="hidden" value="${tsTypePage.TSTypegroup.id }">
			<input id="TSTypegroup.typegroupname" name="TSTypegroup.typegroupname" type="hidden" value="${tsTypePage.TSTypegroup.typegroupname }">
			<input id="TSTypegroup.typegroupcode" name="TSTypegroup.typegroupcode" type="hidden" value="${tsTypePage.TSTypegroup.typegroupcode }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<%-- <tr>
					<td align="right">
						<label class="Validform_label">
							类型编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="typecode" name="typecode" ignore="ignore"
							   value="${tsTypePage.typecode}">
						<span class="Validform_checktip"></span>
					</td>
				</tr> --%>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="typename" name="typename" ignore="ignore"
							   value="${tsTypePage.typename}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>