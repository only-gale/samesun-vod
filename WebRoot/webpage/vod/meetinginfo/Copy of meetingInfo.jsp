<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>会议信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="meetingInfoController.do?save">
			<input id="id" name="id" type="hidden" value="${meetingInfoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							VRS_ID号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billid" name="billid" 
							   value="${meetingInfoPage.billid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							设备名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="billname" name="billname" ignore="ignore"
							   value="${meetingInfoPage.billname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="billstarttime" name="billstarttime" ignore="ignore"
							     value="<fmt:formatDate value='${meetingInfoPage.billstarttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
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
							   value="${meetingInfoPage.billduration}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Live类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isasflive" name="isasflive" 
							   value="${meetingInfoPage.isasflive}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否录音:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isrecord" name="isrecord" 
							   value="${meetingInfoPage.isrecord}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会议状态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="meetingstate" name="meetingstate" 
							   value="${meetingInfoPage.meetingstate}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会议有限状态机:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fsmstate" name="fsmstate" ignore="ignore"
							   value="${meetingInfoPage.fsmstate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预约录制时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="appointmentdt" name="appointmentdt" ignore="ignore"
							   value="${meetingInfoPage.appointmentdt}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预约状态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="appointmentstate" name="appointmentstate" ignore="ignore"
							   value="${meetingInfoPage.appointmentstate}" datatype="n">
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
							   value="${meetingInfoPage.asfurl}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会议名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" ignore="ignore"
							   value="${meetingInfoPage.name}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="typeid" name="typeid" ignore="ignore"
							   value="${meetingInfoPage.typeid}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							终端分组:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="rightid" name="rightid" ignore="ignore"
							   value="${meetingInfoPage.rightid}">
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
							   value="${meetingInfoPage.subject}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会议主持人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="compere" name="compere" ignore="ignore"
							   value="${meetingInfoPage.compere}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							会议简介:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="introduction" name="introduction" ignore="ignore"
							   value="${meetingInfoPage.introduction}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>