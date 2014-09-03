<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>权限分组</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
	$(function() {
		$("#userIDs").combotree({
			width : '300',
			url : 'authorityUserGroupController.do?getChildren',
			multiple : true,
			separator : ",",
			//cascadeCheck: false
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
				$("#userIDs").combo("setValues",vv).combo("setText",ss.join(","));
				$("#userIDs").val(vv);
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
				$("#userIDs").combo("setValues",vv).combo("setText",ss.join(","));
				$("#userIDs").val(vv);
			},
			onLoadSuccess : function(){
				var ids = "${authorityUserGroupPage.userIDs}";
				var names = "${authorityUserGroupPage.userNames}";
				if(null != ids && "" != ids){
					$("#userIDs").combotree('setValues', ids.split(",")).combotree('setText', names);
					$("#userIDs").val(ids);
				}
			}
		});
	});
</script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="authorityUserGroupController.do?save">
			<input id="id" name="id" type="hidden" value="${authorityUserGroupPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right" width="30%">
						<label class="Validform_label">
							<span><font color="red">*</font></span>用户分组名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="name" name="name" 
							   value="${authorityUserGroupPage.name}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right" width="70%">
						<label class="Validform_label">
							<span><font color="red">*</font></span>用户清单:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="userIDs" name="userIDs" ignore="ignore" datatype="*" nullmsg="请选择用户" />
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
						<textarea cols="76" rows="4" style="resize:none" id="desc" name="desc">${authorityUserGroupPage.desc}</textarea>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>