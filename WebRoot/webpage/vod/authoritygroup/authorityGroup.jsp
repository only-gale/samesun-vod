<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>终端分组</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript">
	$(function() {
		$("#terminalIDs").combotree({
			width : '300',
			url : 'authorityGroupController.do?getChildren',
			multiple : true,
			separator : ",",
			//cascadeCheck: false,
			onCheck : function(record){
				var n = $(this).tree('getChecked');
				var vv=[],ss=[];
				for(var i=0;i<n.length;i++){
					var v = n[i];
					if("tree-file" == v.iconCls){
						vv.push(v.id);
						ss.push(v.text);
					}
				}
				$("#terminalIDs").combo("setValues",vv).combo("setText",ss.join(","));
				$("#terminalIDs").val(vv);
			},
			onHidePanel : function(){
				var n = $(this).tree('getChecked');
				var vv=[],ss=[];
				for(var i=0;i<n.length;i++){
					var v = n[i];
					if("tree-file" == v.iconCls){
						vv.push(v.id);
						ss.push(v.text);
					}
				}
				$("#terminalIDs").combo("setValues",vv).combo("setText",ss.join(","));
				$("#terminalIDs").val(vv);
			},
			onLoadSuccess : function(){
				 var ids = "${authorityGroupPage.terminalIDs}";
				 var names = "${authorityGroupPage.terminalNames}";
				 $("#terminalIDs").combotree("setValues",ids.split(",")).combotree("setText",names);
				 $("#terminalIDs").val(ids);
			}
		});
	});
</script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="authorityGroupController.do?save">
			<input id="id" name="id" type="hidden" value="${authorityGroupPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							<span><font color="red">*</font></span>设备分组名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${authorityGroupPage.name}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right" width="70%">
						<label class="Validform_label">
							<span><font color="red">*</font></span>终端清单:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="terminalIDs" name="terminalIDs" datatype="*" nullmsg="请选择设备"/>
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
						<textarea cols="76" rows="4" style="resize:none" id="desc" name="desc">${authorityGroupPage.desc}</textarea>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>