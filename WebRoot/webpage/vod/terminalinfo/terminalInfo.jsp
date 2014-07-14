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
						<span class="Validform_checktip"></span>
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
							   value="${terminalInfoPage.descript}" datatype="*">
						<span class="Validform_checktip"></span>
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
						<span class="Validform_checktip"></span>
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
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地理位置:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="groupid" name="groupid" 
							   value="${terminalInfoPage.groupid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当前状态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="state" name="state" ignore="ignore"
							   value="${terminalInfoPage.state}" datatype="n">
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
				</tr>
			</table>
		</t:formvalid>
 </body>