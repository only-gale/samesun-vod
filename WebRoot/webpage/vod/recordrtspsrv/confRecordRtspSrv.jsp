<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>录制点播服务器关系</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="confRecordRtspSrvController.do?save">
			<input id="id" name="id" type="hidden" value="${confRecordRtspSrvPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							录播服务器主键:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="recordsrvid" name="recordsrvid" ignore="ignore"
							   value="${confRecordRtspSrvPage.recordsrvid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							RTSP服务器主键:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rtspsrvid" name="rtspsrvid" ignore="ignore"
							   value="${confRecordRtspSrvPage.rtspsrvid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>