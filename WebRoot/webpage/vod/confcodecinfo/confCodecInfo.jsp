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
					<td align="right" width="30%">
						<label class="Validform_label">
							<span><font color="red">*</font></span>Codec名称:
						</label>
					</td>
					<td class="value" width="70%">
						<input class="inputxt" id="name" name="name" 
							   value="${confCodecInfoPage.name}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<span><font color="red">*</font></span>地理位置:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="groupid" name="groupid" datatype="*" nullmsg="请选择地理位置">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<span><font color="red">*</font></span>mac地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="macaddress" name="macaddress" 
							   value="${confCodecInfoPage.macaddress}" datatype="/[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}/"
							   errormsg="请填写正确的mac地址">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<span><font color="red">*</font></span>IP地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ipaddress" name="ipaddress" 
							   value="${confCodecInfoPage.ipaddress}" datatype="ipv4" errormsg="请填写正确的ip地址">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							<span><font color="red">*</font></span>直播URL:
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
							<span><font color="red">*</font></span>录制服务器:
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
				<tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
						<textarea cols="76" rows="4" style="resize:none" id="descript" name="descript">${confCodecInfoPage.descript}</textarea>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
<script type="text/javascript">
	$(function() {
		$('#groupid').combotree({
			url : 'terminalInfoController.do?getChildren',
			width : 200,
			onSelect : function(record){
					$("#groupid").combo("setValues",record.id).combo("setText",record.text);
					$("#groupid").val(record.id);
			},
			onLoadSuccess : function(){
				 var id = "${confCodecInfoPage.groupid}";
				 var name = "${confCodecInfoPage.groupname}";
				 $("#groupid").combotree("setValue",id).combotree("setText",name);
				 $("#groupid").val(id);
			}
		});
	});
</script>