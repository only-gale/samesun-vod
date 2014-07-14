<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>预约频道信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="appointmentChannelInfoController.do?save">
			<input id="id" name="id" type="hidden" value="${appointmentChannelInfoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							频道名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="channelname" name="channelname" ignore="ignore"
							   value="${appointmentChannelInfoPage.channelname}">
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
						<input class="inputxt" id="meetingid" name="meetingid" 
							   value="${appointmentChannelInfoPage.meetingid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							主Codec1:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="codec1id" name="codec1id" 
							   value="${appointmentChannelInfoPage.codec1id}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Codec1是否录制:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isrecord1" name="isrecord1" 
							   value="${appointmentChannelInfoPage.isrecord1}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备Codec2:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="codec2id" name="codec2id" 
							   value="${appointmentChannelInfoPage.codec2id}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							Codec2是否录制:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isrecord2" name="isrecord2" 
							   value="${appointmentChannelInfoPage.isrecord2}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							自定义权限组ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="authortiyGroupCid" name="authortiyGroupCid" 
							   value="${appointmentChannelInfoPage.authortiyGroupCid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="authortiyGroupType" name="authortiyGroupType" 
							   value="${appointmentChannelInfoPage.authortiyGroupType}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							分配终端权限组（匿名类）:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="authortiyTerminlgroupCid" name="authortiyTerminlgroupCid" ignore="ignore"
							   value="${appointmentChannelInfoPage.authortiyTerminlgroupCid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户自定义权限组:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="authortiyUsergroupCid" name="authortiyUsergroupCid" ignore="ignore"
							   value="${appointmentChannelInfoPage.authortiyUsergroupCid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>