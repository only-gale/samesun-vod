<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>会议预约</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="appointmentMeetingInfoController.do?save">
		<%--会议ID --%>
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
			<div title="频道信息" style="padding:10px"
				data-options="
				selected:true">

				<table id="dg" class="easyui-datagrid"
					data-options="iconCls: 'icon-edit',toolbar: '#tb',
					fit:true,fitColumns:true,pagination:true,pageSize:5,pageList:[5],singleSelect:true,url:'appointmentChannelInfoController.do?datagrid',
		   queryParams:{
		           meetingid: meetingId
	               },idField : 'id',onAfterEdit:afterEdit, onBeforeLoad: initChannel, onLoadSuccess: initParam">
					<thead>
						<tr>
						<th data-options="field:'id',hidden:true"></th>
							<th
								data-options="field:'codec1id',width:150,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								url:'confCodecInfoController.do?combox',
								required:true
							}
						},
						formatter:function(value,row,index){
							var r = row;
							alert(r.codec1name);
							return transName(row.codec1name);
						}">主编码器</th>
							<th
								data-options="field:'isrecord1',width:60,align:'center',editor : {
							type : 'checkbox',
							 options: {  
                                   on: 1,  
                                   off: 0  
                              }  
					},
					formatter:function(value,row,index){		 
						                return transCheckBox(value,row,index);     
					}">是否录制</th>
							<th
								data-options="field:'codec2id',width:150,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								url:'confCodecInfoController.do?combox',
								formatter: function(row){
									var opts = $(this).combobox('options');
									return row[opts.textField];
								}
							}
						},
						formatter:function(value,row,index){
							return transName(row.codec2name);
						}">备编码器</th>
							<th
								data-options="field:'isrecord2',width:60,align:'center',editor : {
							type : 'checkbox',
							 options: {  
                                   on: 1,  
                                   off: 0  
                              }  
					},
						formatter:function(value,row,index){
							return transCheckBox(value,row,index);
						}">是否录制</th>
							<th
								data-options="field:'authortiyGroupCid',width:150,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								url:'authorityGroupController.do?combox',
								onBeforeLoad: function(param){
									param.excepts = excepts;
								}
							}
						},
						formatter:function(value,row,index){
							return transName(row.authortiyTerminlgroupCname);
						}">云翼设备分组</th>
							<th
								data-options="field:'authortiyUsergroupCid',width:150,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								url:'authorityUserGroupController.do?combox'
							}
						},
						formatter:function(value,row,index){
							return transName(row.authortiyUsergroupCname);
						}">自定义用户权限</th>
						</tr>
					</thead>
				</table>
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
<script type="text/javascript">
	var meetingId = "", excepts = "";
	meetingId = $("#id").val();
	if("" == meetingId || "null" == meetingId){
		meetingId = new Date().getTime();
	}
	var editIndex = undefined;
	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		if ($('#dg').datagrid('validateRow', editIndex)) {
			var codec1ed = $('#dg').datagrid('getEditor', {
				index : editIndex,
				field : 'codec1id'
			});
			var codec2ed = $('#dg').datagrid('getEditor', {
				index : editIndex,
				field : 'codec2id'
			});
			var codec1name = $(codec1ed.target).combobox('getText');
			var codec2name = $(codec1ed.target).combobox('getText');
			$('#dg').datagrid('getRows')[editIndex]['codec1name'] = codec1name;
			$('#dg').datagrid('getRows')[editIndex]['codec2name'] = codec2name;
			$('#dg').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}

	function append() {
		if (endEditing()) {
			$('#dg').datagrid('appendRow', {
				isrecord1 : 1
			});
			editIndex = $('#dg').datagrid('getRows').length - 1;
			$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',
					editIndex);
		}
	}
	function removeit() {
		if (editIndex == undefined) {
			return
		}
		$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow',
				editIndex);
		editIndex = undefined;
	}
	function accept() {
		if (endEditing()) {
			$('#dg').datagrid('endEdit', editIndex);
		}
	}
	function reject() {
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}
	function transCheckBox(value, row, index) {
		if (value == '1') {
			return formatString('<input type="checkbox" {0} disabled/>',
					'checked="checked"');
		} else if (String(value) == '0') {
			return formatString('<input type="checkbox" {0} disabled />', '');
		}
	}

	function transName(name) {
		if (name == undefined) {
			name = '';
		}
		return formatString('<span title="{0}">{1}</span>', name, name); //由后台抛过来
	}
	
	function afterEdit(index,row){
		row.editing = false;
    	//updateActions(); // 这一行必须要调用；
    	var inserted = $('#dg').datagrid('getChanges', 'inserted');
    	var updated = $('#dg').datagrid('getChanges', 'updated');
    	if (inserted.length < 1 && updated.length < 1) {	    
    		editRow = undefined;
    		$('#dg').datagrid('unselectAll');
    		return;
    	}
    	var url = 'appointmentChannelInfoController.do?save&meetingId='+meetingId;
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
    				$('#dg').datagrid('reload');
    			} else {
    				/* datagrid.datagrid('rejectChanges'); */
    				$('#dg').datagrid('beginEdit', editRow);
    				$.messager.alert('错误', r.msg, 'error');
    			}
    			$('#dg').datagrid('unselectAll');
    		}
    	});
	}

	function initParam(data){
		var r = data;
		excepts = <%=request.getAttribute("sb")%>;
	}
	
	function initChannel(){
		/* var url = 'appointmentChannelInfoController.do?initchannel';
		$.post(url); */
	}
	
	function cancelEdit(index,row){
		row.editing = false;
    	updateActions();
	}
	
	function updateActions(){
		var rowcount = $('#dg').datagrid('getRows').length;
		for(var i=0; i<rowcount; i++){
			$('#dg').datagrid('updateRow',{
				index:i,
				row:{action:''}
			});
		}
	}
</script>