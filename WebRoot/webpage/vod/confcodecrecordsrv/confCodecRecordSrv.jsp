<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>编码器录制服务器关系</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="confCodecRecordSrvController.do?save">
			<input id="id" name="id" type="hidden" value="${confCodecRecordSrvPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							编码器ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="codecid" name="codecid" ignore="ignore"
							   value="${confCodecRecordSrvPage.codecid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							录制服务器ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="recordsrvid" name="recordsrvid" ignore="ignore"
							   value="${confCodecRecordSrvPage.recordsrvid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>