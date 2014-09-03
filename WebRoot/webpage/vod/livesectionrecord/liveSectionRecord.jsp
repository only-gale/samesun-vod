<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>直播会议录制分表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="liveSectionRecordController.do?save">
			<input id="id" name="id" type="hidden" value="${liveSectionRecordPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							会议ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="meetingid" name="meetingid" 
							   value="${liveSectionRecordPage.meetingid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							频道ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="channelid" name="channelid" 
							   value="${liveSectionRecordPage.channelid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							SessionID号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sessionid" name="sessionid" ignore="ignore"
							   value="${liveSectionRecordPage.sessionid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Codec主备标志:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="codecpriorityflg" name="codecpriorityflg" ignore="ignore"
							   value="${liveSectionRecordPage.codecpriorityflg}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Codec服务器ID号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="codecsrvid" name="codecsrvid" ignore="ignore"
							   value="${liveSectionRecordPage.codecsrvid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							录播服务器ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="recordsrvid" name="recordsrvid" 
							   value="${liveSectionRecordPage.recordsrvid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Rtsp服务器ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rtspsrvid" name="rtspsrvid" 
							   value="${liveSectionRecordPage.rtspsrvid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Rtsp服务器地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rtsprelativedir" name="rtsprelativedir" ignore="ignore"
							   value="${liveSectionRecordPage.rtsprelativedir}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							录制的文件名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="filename" name="filename" ignore="ignore"
							   value="${liveSectionRecordPage.filename}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							录制状态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="recState" name="recState" ignore="ignore"
							   value="${liveSectionRecordPage.recState}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							录制备注:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="recMessage" name="recMessage" ignore="ignore"
							   value="${liveSectionRecordPage.recMessage}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							起始时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="recStartDt" name="recStartDt" 
							     value="<fmt:formatDate value='${liveSectionRecordPage.recStartDt}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							结束时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="recEndDt" name="recEndDt" ignore="ignore"
							     value="<fmt:formatDate value='${liveSectionRecordPage.recEndDt}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							持续时长:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billduration" name="billduration" ignore="ignore"
							   value="${liveSectionRecordPage.billduration}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							资源URL:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="asfurl" name="asfurl" ignore="ignore"
							   value="${liveSectionRecordPage.asfurl}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>