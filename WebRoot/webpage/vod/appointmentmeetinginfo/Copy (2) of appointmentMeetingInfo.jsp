<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>会议预约</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="webpage/vod/appointmentmeetinginfo/appointmentMeetingInfo.js" charset="utf-8"></script>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="appointmentMeetingInfoController.do?save">
		<input id="id" name="id" type="hidden"
			value="${appointmentMeetingInfoPage.id }">
		<table style="width: 100%;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right" width="15%"><label class="Validform_label">
						预约时间: </label></td>
				<td class="value"><input class="Wdate"
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					style="width: 150px" id="appointmentStarttime"
					name="appointmentStarttime" ignore="ignore"
					value="<fmt:formatDate value='${appointmentMeetingInfoPage.appointmentStarttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
					<span class="Validform_checktip"></span></td>
				<td align="right" width="15%"><label class="Validform_label">
						预约持续时长: </label></td>
				<td class="value"><input class="inputxt"
					id="appointmentDuration" name="appointmentDuration" ignore="ignore"
					value="${appointmentMeetingInfoPage.appointmentDuration}"
					datatype="n"> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 预约状态:
				</label></td>
				<td class="value"><input class="inputxt" id="appointmentState"
					name="appointmentState"
					value="${appointmentMeetingInfoPage.appointmentState}" datatype="n">
					<span class="Validform_checktip"></span></td>
				<td align="right"><label class="Validform_label"> 所属类型:
				</label></td>
				<td class="value"><input class="inputxt" id="typeid"
					name="typeid" ignore="ignore"
					value="${appointmentMeetingInfoPage.typeid}" datatype="n">
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 会议主题:
				</label></td>
				<td class="value"><input class="inputxt" id="subject"
					name="subject" ignore="ignore"
					value="${appointmentMeetingInfoPage.subject}"> <span
					class="Validform_checktip"></span></td>
				<td align="right"><label class="Validform_label">
						会议主持人: </label></td>
				<td class="value"><input class="inputxt" id="compere"
					name="compere" ignore="ignore"
					value="${appointmentMeetingInfoPage.compere}"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 会议简介:
				</label></td>
				<td class="value" colspan="3"><textarea cols="96"
						style="margin:5px auto" id="introduction" name="introduction"
						ignore="ignore" value="${appointmentMeetingInfoPage.introduction}">
						</textarea> <span class="Validform_checktip"></span></td>
			</tr>
		</table>
		<div class="easyui-accordion" style="width:700px;height:230px;">
			<div title="频道信息" style="padding:10px" data-options="selected:true">

				<table id="dg"></table>
				<div id="tb" style="height:auto">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-remove',plain:true"
						onclick="removeit()">取消</a> <a href="javascript:void(0)"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-undo',plain:true" onclick="reject()">删除</a>
				</div>


			</div>

		</div>

	</t:formvalid>
</body>