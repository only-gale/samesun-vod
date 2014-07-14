<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>权限分组</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
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
							权限类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="authtype" name="authtype" 
							   value="${authorityGroupPage.authtype}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="creattype" name="creattype" 
							   value="${authorityGroupPage.creattype}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>