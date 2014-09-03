<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>点播服务器</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="confRtspSrvInfoController.do?save">
			<input id="id" name="id" type="hidden" value="${confRtspSrvInfoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							<span><font color="red">*</font></span>RTSP服务器名称:
						</label>
					</td>
					<td class="value" width="70%">
						<input class="inputxt" id="name" name="name" 
							   value="${confRtspSrvInfoPage.name}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<span><font color="red">*</font></span>mac地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="macaddress" name="macaddress" 
							   value="${confRtspSrvInfoPage.macaddress}" datatype="/[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}/"
							   errormsg="请填写正确的mac地址">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<span><font color="red">*</font></span>IP地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ipaddress" name="ipaddress" 
							   value="${confRtspSrvInfoPage.ipaddress}" datatype="ipv4" errormsg="请填写正确的ip地址">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							 <span><font color="red">*</font></span>Url地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rtspurl" name="rtspurl" 
							   value="${confRtspSrvInfoPage.rtspurl}" datatype="*">
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
						<textarea cols="76" rows="4" style="resize:none" id="descript" name="descript">${confRtspSrvInfoPage.descript}</textarea>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>