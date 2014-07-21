<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会议日志</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="meetingLogController.do?save">
			<input id="id" name="id" type="hidden" value="${meetingLogPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							终端MAC:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="edgemac" name="edgemac" 
							   value="${meetingLogPage.edgemac}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							终端名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="edgename" name="edgename" ignore="ignore"
							   value="${meetingLogPage.edgename}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会议ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="meetingid" name="meetingid" ignore="ignore"
							   value="${meetingLogPage.meetingid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否是直播:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isliveflag" name="isliveflag" ignore="ignore"
							   value="${meetingLogPage.isliveflag}">
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
							   value="${meetingLogPage.subject}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							日期:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="date" name="date" 
							     value="<fmt:formatDate value='${meetingLogPage.date}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							观看时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="times" name="times" ignore="ignore"
							   value="${meetingLogPage.times}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>