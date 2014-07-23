<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>预约培训</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="appointmentTrainingController.do?save">
			<input id="id" name="id" type="hidden" value="${appointmentTrainingPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							预约时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="appointmentStarttime" name="appointmentStarttime" ignore="ignore"
							     value="<fmt:formatDate value='${appointmentTrainingPage.appointmentStarttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							预约持续时长:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="appointmentDuration" name="appointmentDuration" ignore="ignore"
							   value="${appointmentTrainingPage.appointmentDuration}" datatype="n">
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
						<input class="inputxt" id="appointmentState" name="appointmentState" ignore="ignore"
							   value="${appointmentTrainingPage.appointmentState}" datatype="n">
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
							   value="${appointmentTrainingPage.typeid}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							培训主题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="subject" name="subject" ignore="ignore"
							   value="${appointmentTrainingPage.subject}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							培训主讲人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="compere" name="compere" ignore="ignore"
							   value="${appointmentTrainingPage.compere}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							培训简介:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="introduction" name="introduction" ignore="ignore"
							   value="${appointmentTrainingPage.introduction}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							liveTime:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="liveTime" name="liveTime" ignore="ignore"
							     value="<fmt:formatDate value='${appointmentTrainingPage.liveTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							delayTime:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="delayTime" name="delayTime" ignore="ignore"
							   value="${appointmentTrainingPage.delayTime}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							isrecord:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isrecord" name="isrecord" ignore="ignore"
							   value="${appointmentTrainingPage.isrecord}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>