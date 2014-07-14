<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>录制服务器</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="confRecordSrvInfoController.do?save">
			<input id="id" name="id" type="hidden" value="${confRecordSrvInfoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							录制服务器名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${confRecordSrvInfoPage.name}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							描述:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="descript" name="descript" 
							   value="${confRecordSrvInfoPage.descript}" datatype="*">
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
							   value="${confRecordSrvInfoPage.macaddress}" datatype="*">
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
							   value="${confRecordSrvInfoPage.ipaddress}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							端口:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="port" name="port" 
							   value="${confRecordSrvInfoPage.port}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							相对目录:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="path" name="path" 
							   value="${confRecordSrvInfoPage.path}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否启用:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="disable" name="disable" 
							   value="${confRecordSrvInfoPage.disable}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="crtuser" name="crtuser" ignore="ignore"
							   value="${confRecordSrvInfoPage.crtuser}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建人名字:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="crtuserName" name="crtuserName" ignore="ignore"
							   value="${confRecordSrvInfoPage.crtuserName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							创建时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createDt" name="createDt" ignore="ignore"
							     value="<fmt:formatDate value='${confRecordSrvInfoPage.createDt}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							修改人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="modifier" name="modifier" ignore="ignore"
							   value="${confRecordSrvInfoPage.modifier}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							修改人名字:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="modifierName" name="modifierName" ignore="ignore"
							   value="${confRecordSrvInfoPage.modifierName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							修改时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="modifyDt" name="modifyDt" ignore="ignore"
							     value="<fmt:formatDate value='${confRecordSrvInfoPage.modifyDt}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							删除时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="delDt" name="delDt" ignore="ignore"
							     value="<fmt:formatDate value='${confRecordSrvInfoPage.delDt}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>