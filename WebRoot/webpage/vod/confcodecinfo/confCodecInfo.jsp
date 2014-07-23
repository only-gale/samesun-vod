<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>编码器配置信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="confCodecInfoController.do?save">
			<input id="id" name="id" type="hidden" value="${confCodecInfoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							Codec名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${confCodecInfoPage.name}" datatype="*">
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
						<input class="inputxt" id="groupid" name="groupid" ignore="ignore" style="width:160px;"
							   value="${confCodecInfoPage.groupid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<%-- <tr>
					<td align="right">
						<label class="Validform_label">
							路数占用情况:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="resources" name="resources" ignore="ignore"
							   value="${confCodecInfoPage.resources}">
						<span class="Validform_checktip"></span>
					</td>
				</tr> --%>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="descript" name="descript" 
							   value="${confCodecInfoPage.descript}" datatype="*">
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
							   value="${confCodecInfoPage.macaddress}" datatype="*">
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
							   value="${confCodecInfoPage.ipaddress}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							直播URL:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="codecurl" name="codecurl" 
							   value="${confCodecInfoPage.codecurl}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							录制服务器:
						</label>
					</td>
					<td class="value">
						<select id="record" name="record" class="easyui-combobox" data-options="panelHeight:'auto'" datatype="*">
						<c:forEach items="${records}" var="record">
							<option value="${record.id }"
								<c:if test="${confCodecInfoPage.record eq record.id}"> selected='selected'</c:if>>${record.name }</option>
						</c:forEach>
				</select>
				</td>
				<%-- <tr>
					<td align="right">
						<label class="Validform_label">
							是否启用:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="disable" name="disable" 
							   value="${confCodecInfoPage.disable}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr> --%>
			</table>
		</t:formvalid>
 </body>
<script type="text/javascript">
	$(function() {
		$('#groupid').combotree({
			url : 'terminalInfoController.do?getChildren'
		});
	});
</script>