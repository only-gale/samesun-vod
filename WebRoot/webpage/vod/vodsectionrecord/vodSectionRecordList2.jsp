<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>点播信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<input id="meetingid" type="hidden" value="${meetingid }">
	<input id="sessionid" type="hidden" value="${sessionid }">
	<table id="dg" class="easyui-datagrid"
		data-options="toolbar: '#tb', fit:true,fitColumns:true,pagination:true,pageSize:15,pageList:[15, 20, 25],singleSelect:true,url:'vodSectionRecordController.do?datagrid&rightid=${rightid }',
		   queryParams:{
		           meetingid: meetingid,
		           sessionid: sessionid
	               },idField : 'id',onAfterEdit: afterEdit, onClickRow: onClickRow">
		<thead>
			<th data-options="field:'id',hidden:true"></th>
			<th data-options="field:'channelid',hidden:true"></th>
			<th data-options="field:'codecpriorityflg',hidden:true"></th>
			<th data-options="field:'rtspsrvid',hidden:true"></th>
			<th data-options="field:'rtspsrvname',width:70, align:'center'">Rtsp服务</th>
			<th
				data-options="field:'authortiyGroupCid',width:70,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								required:true,
								url:'authorityGroupController.do?combox'
							}
						},
						formatter:function(value,row,index){
							return transName(row.authortiyGroupName);
						}">云翼设备分组</th>
			<th
				data-options="field:'authortiyUsergroupCid',width:70,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								url:'authorityUserGroupController.do?combox'
							}
						},
						formatter:function(value,row,index){
							return transName(row.authortiyUserGroupName);
						}">自定义用户权限</th>
			<th data-options="field:'recState',hidden:true"></th>
			<th data-options="field:'recStateName',width:40, align:'center'">录制状态</th>
			<th data-options="field:'recStartDt',width:60, formatter:function(value){return value.split('.')[0];}">起始时间</th>
			<th data-options="field:'recEndDt',width:60, formatter:function(value){return value.split('.')[0];}">结束时间</th>
			<th data-options="field:'billduration',width:40, align:'center'">持续时长(分钟)</th>
		</thead>
	</table>
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
	</div>
</body>
</html>
<script type="text/javascript">
 var meetingid = $("#meetingid").val();
 var sessionid = $("#sessionid").val();
 
	/*结束编辑后调用*/
	function afterEdit(index, row) {
		row.editing = false;
		var url = 'vodSectionRecordController.do?save';
		$.ajax({
			url : url,
			data : row,
			dataType : 'json',
			async : false,
			success : function(r) {
				if (r.success) {
					$('#dg').datagrid('acceptChanges');
					$.messager.show({
						msg : r.msg,
						title : '成功'
					});
					editRow = undefined;
					/* $('#dg').datagrid('reload'); */
				} else {
					/* datagrid.datagrid('rejectChanges'); */
					$('#dg').datagrid('beginEdit', editRow);
					$.messager.alert('错误', r.msg, 'error');
				}
				$('#dg').datagrid('unselectAll');
			}
		});
	}
	
	function transName(name) {
		if (name == undefined) {
			name = '';
		}
		return formatString('<span title="{0}">{1}</span>', name, name); //由后台抛过来
	}
	
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#dg').datagrid('validateRow', editIndex)){
			var authortiyGroupCid_ed = $('#dg').datagrid('getEditor', {
				index : editIndex,
				field : 'authortiyGroupCid'
			});
			var authortiyUsergroupCid_ed = $('#dg').datagrid('getEditor', {
				index : editIndex,
				field : 'authortiyUsergroupCid'
			});
			var authortiyGroupCname = $(authortiyGroupCid_ed.target).combobox(
					'getText');
			var authortiyUsergroupCname = $(authortiyUsergroupCid_ed.target)
					.combobox('getText');
			$('#dg').datagrid('getRows')[editIndex]['authortiyGroupCname'] = authortiyGroupCname;
			$('#dg').datagrid('getRows')[editIndex]['authortiyUsergroupCname'] = authortiyUsergroupCname;
			$('#dg').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow(index){
		if (editIndex != index){
			if (endEditing()){
				$('#dg').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#dg').datagrid('selectRow', editIndex);
			}
		}
	}
	function accept(){
		if (endEditing()){
			$('#dg').datagrid('acceptChanges');
		}
	}
	function reject(){
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}
 </script>
