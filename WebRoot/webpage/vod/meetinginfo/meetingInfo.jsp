<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>会议信息</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="meetingInfoController.do?save" beforeSubmit="checkDg">
		<%--会议ID --%>
		<input id="id" name="id" type="hidden"
			value="${meetingInfoPage.id }">
			
		<%--预约ID --%>
		<input id="billid" name="billid" type="hidden"
			value="${meetingInfoPage.billid }">

		<input id="load" name="load" type="hidden" value="${load }" />

		<input id="tempid" name="tempid" type="hidden" />

		<table style="width: 100%;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right" width="15%"><label class="Validform_label"> 所属类型:
				</label></td>
				<td class="value" colspan="3">
					<%-- <input class="inputxt" id="typeid"
					name="typeid" ignore="ignore"
					value="${appointmentMeetingInfoPage.typeid}" datatype="n"> --%> <select
					id="typeid" name="typeid" style="widht:50%">
						<c:forEach items="${appTypes}" var="type">
							<option value="${type.typecode }"
								<c:if test="${meetingInfoPage.typeid eq type.typecode}"> selected='selected'</c:if>>${type.typename }</option>
						</c:forEach>
				</select> <span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 会议主题:
				</label></td>
				<td class="value"><input class="inputxt" id="subject"
					name="subject" datatype="*" nullmsg=" " errormsg=" " sucmsg=" "
					value="${meetingInfoPage.subject}"> <span
					class="Validform_checktip"></span></td>
				
				<td align="right" width="15%"><label class="Validform_label">
						会议主持人: </label></td>
				<td class="value"><input class="inputxt" id="compere"
					name="compere" ignore="ignore"
					value="${meetingInfoPage.compere}"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 会议简介:
				</label></td>
				<td class="value" colspan="3">
				<textarea cols="97" rows="4" style="margin:5px auto; resize:none" id="introduction" name="introduction" ignore="ignore" value="${meetingInfoPage.introduction}"></textarea>
				<span class="Validform_checktip"></span></td>
			</tr>
		</table>
		<div class="easyui-accordion" style="width:700px;height:230px">
			<div title="频道信息" style="padding:10px" data-options="selected:true">
				<c:choose>
				<c:when test="${load eq 'detail' }">
				<table id="dg" class="easyui-datagrid"
					data-options="iconCls: 'icon-edit',toolbar: '#tb',
					fit:true,fitColumns:true,singleSelect:true,url:'appointmentChannelInfoController.do?datagrid',
		   queryParams:{
		           meetingid: meetingId
	               },idField : 'id',onAfterEdit:afterEdit, onBeforeLoad: initParams, onClickRow: onClickRow">
	               </c:when>
	               <c:otherwise>
	               <table id="dg" class="easyui-datagrid"
					data-options="iconCls: 'icon-edit',toolbar: '#tb',
					fit:true,fitColumns:true,pagination:true,pageSize:5,pageList:[5],singleSelect:true,url:'appointmentChannelInfoController.do?datagrid',
		   queryParams:{
		           meetingid: meetingId
	               },idField : 'id',onAfterEdit: afterEdit, onBeforeLoad: initParams, onClickRow: onClickRow">
	               </c:otherwise>
	               </c:choose>
					<thead>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'appoinementid',hidden:true"></th>
						<th data-options="field:'meetingid',hidden:true"></th>
						<th data-options="field:'codec1id',width:150,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								url:'confCodecInfoController.do?combox&meetingType=live',
								required:true,
								panelHeight:'auto',
								onBeforeLoad:function(param){
									getexcepts();
									param.excepts = excepts;
								}
							}
						},
						formatter:function(value,row,index){
							return transName(row.codec1name);
						}">主编码器</th>
						<th data-options="field:'isrecord1',width:60,align:'center',editor : {
							type : 'checkbox',
							options: {  
								on: 1,  
								off: 0  
							}  
						},
						formatter:function(value,row,index){		 
							return transCheckBox(value,row,index);     
						}">是否录制</th>
						<th data-options="field:'codec2id',width:150,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								url:'confCodecInfoController.do?combox&meetingType=live',
								formatter: function(row){
									var opts = $(this).combobox('options');
									return row[opts.textField];
								},
								panelHeight:'auto',
								onBeforeLoad:function(param){
									getexcepts();
									param.excepts = excepts;
								}
							}
						},
						formatter:function(value,row,index){
							return transName(row.codec2name);
						}">备编码器</th>
						<th data-options="field:'isrecord2',width:60,align:'center',editor : {
							type : 'checkbox',
							options: {
								on: 1,  
								off: 0  
                              }  
						},
						formatter:function(value,row,index){
							return transCheckBox(value,row,index);
						}">是否录制</th>
						<th data-options="field:'authortiyGroupCid',width:150,align:'center',editor:{
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
							return transName(row.authortiyGroupCname);
						}">云翼设备分组</th>
						<th data-options="field:'authortiyUsergroupCid',width:150,align:'center',editor:{
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
					</thead>
				</table>
				<div id="tb" style="height:auto">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
					<!-- <a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a> -->
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
				</div>
			</div>
		</div>

	</t:formvalid>

</body>
<script type="text/javascript">
var meetingId = $("#id").val(), excepts = "", editIndex = undefined;

if("" == meetingId || "null" == meetingId){
	meetingId = new Date().getTime();
	$("#tempid").val(meetingId);
}

function afterEdit(index,row){
	row.editing = false;
	var url = 'appointmentChannelInfoController.do?save&meetingId=' + meetingId;
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

function initParams(){
	$("#dg").datagrid('options').queryParams['meetingid'] = meetingId;
	//当是查看请求时，则隐藏工具栏
	if('detail' == $("#load").val()){
		$("#tb a").hide();
	}
}

function onClickRow(index){
	if (editIndex != index){
		if (endEditing()){
			/* $('#dg').datagrid('selectRow', index)
					.datagrid('beginEdit', index); */
			editIndex = index;
		} else {
			$('#dg').datagrid('selectRow', editIndex);
		}
	}
}

function transName(name) {
	if (name == undefined) {
		name = '';
	}
	return formatString('<span title="{0}">{1}</span>', name, name); //由后台抛过来
}

function transCheckBox(value, row, index) {
	if (value == '1') {
		return formatString('<input type="checkbox" {0} disabled/>',
				'checked="checked"');
	} else if (String(value) == '0') {
		return formatString('<input type="checkbox" {0} disabled />', '');
	}
}

function afterEdit(index,row){
	row.editing = false;
	var url = 'appointmentChannelInfoController.do?save&meetingId=' + meetingId;
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

/**检查相关------BEGIN------**/

/* 校验会议必须设置有频道 */
function checkDg(){
	var rows = $("#dg").datagrid("getRows");
	var rowcount = rows.length;
	if (rowcount == 0){//记录条数等于0
		$.messager.alert('错误','您还没有设置频道!', 'error');
		return false;
	}else if(editIndex != undefined){
		/* for (var i = 0; i < rowcount; i++){
			var row = rows[i]; 
			if (row.editing){
				$.messager.alert('错误','第' + (i+1) + '个频道设置，请先保存一下!', 'error');
				break;
				return false;
			}
		} */
		$.messager.alert('错误','请先保存频道信息', 'error');
		return false;
	}else return true;
}

/**
* 获取已被之前的频道选择的编码器ID
*/
function getexcepts(){
	var ids = new Array();
	var index = 0;
	var rows = $("#dg").datagrid('getRows');
	var len = rows.length;
	for(var i = 0; i < len; i++){
		ids[index] = rows[i].codec1id;
		ids[index + 1] = rows[i].codec2id;
		index = index + 2;
	}
	excepts = ids.join(",");
}

/**
* 检查是否录制
*/
function isrecord(){
	var flag = false;
	var rows = $("#dg").datagrid("getRows");
	for(var i = 0; i < rows.length; i++){
		if(rows[i].isrecord1 == '1' || rows[i].isrecord2 == '1'){
			flag = true;
			break;
		}
	}
	return flag;
}

/**检查相关------END------**/

/**datagrid菜单按钮操作相关------BEGIN------**/

function endEditing() {
	if (editIndex == undefined) {
		return true
	}
	if ($('#dg').datagrid('validateRow', editIndex)) {
		var codec1id_ed = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'codec1id'
		});
		var codec2id_ed = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'codec2id'
		});
		var authortiyGroupCid_ed = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'authortiyGroupCid'
		});
		var authortiyUsergroupCid_ed = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'authortiyUsergroupCid'
		});
		var codec1name = $(codec1id_ed.target).combobox('getText');
		var codec2name = $(codec2id_ed.target).combobox('getText');
		var authortiyGroupCname = $(authortiyGroupCid_ed.target).combobox('getText');
		var authortiyUsergroupCname = $(authortiyUsergroupCid_ed.target).combobox('getText');
		$('#dg').datagrid('getRows')[editIndex]['codec1name'] = codec1name;
		$('#dg').datagrid('getRows')[editIndex]['codec2name'] = codec2name;
		$('#dg').datagrid('getRows')[editIndex]['authortiyGroupCname'] = authortiyGroupCname;
		$('#dg').datagrid('getRows')[editIndex]['authortiyUsergroupCname'] = authortiyUsergroupCname;
		$('#dg').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		$.messager.alert('错误', '请填写正确的频道信息', 'error');
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

/**删除行，如果当前行无ID信息，则说明是新增的信息，直接删除即可；否则说明当前行信息已被保存在数据库，需要询问用户是否继续删除以便从数据库永久删除**/
function removeit() {
	/* if (editIndex == undefined) {
		return
	} */
	
	var rowsData = $('#dg').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择删除项目');
		return;
	}
	if (rowsData.length>1) {
		tip('请选择一条记录再删除');
		return;
	}
	
	var id = $('#dg').datagrid('getRows')[editIndex].id;
	if('' != id && 'null' != id && undefined != id){
		$.messager.confirm('确认',' 当前频道信息已被保存在数据库,是否删除?',function(r){
			if(r){
				$.ajax({
					url : 'appointmentChannelInfoController.do?del',
					data : {
						id : id
					},
					dataType : 'json',
					success : function(response) {
						$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow',editIndex);
						editIndex = undefined;
						$.messager.show({
							title : '提示',
							msg : '删除成功！'
						});
					}
				});
			}
		});
	}
	else{
		
		$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow',editIndex);
	}
	editIndex = undefined;
}

/**保存已被编辑的数据**/
function accept() {
	if (endEditing()) {
		$('#dg').datagrid('acceptChanges');
	}
}
function reject() {
	$('#dg').datagrid('rejectChanges');
	editIndex = undefined;
}

/**datagrid菜单按钮操作相关------END------**/

/**窗口按钮操作相关------BEGIN------**/

/**
* 开始直播
*/
function save(flag){
	var url = "meetingInfoController.do?save&"+flag;
	var id = $("#id").val();
	var load = $("#load").val();
	var tempid = $("#tempid").val();
	var typeid = $("#typeid").val();
	var subject = $("#subject").val();
	var compere = $("#compere").val();
	var introduction = $("#introduction").val();
	var jsonData = {
			id: id,
			load: load,
			tempid: tempid,
			typeid: typeid,
			subject: subject,
			compere: compere,
			introduction: introduction
	}
	$.ajax({
		url: url,
		data: jsonData,
		success: function(data){
			var d = $.parseJSON(data);
			if (d.success) {
				var msg = d.msg;
				tip(msg);
				$("#id").val(d.attributes.meetingid);
			}
		},
		cache: false
	});
}

/**
* 根据直播会议ID设置开始录制
*/
function startRecord(id){
	var url = "meetingInfoController.do?startRecord&id="+id;
	$.ajax({
		url: url,
		success: function(data){
			var d = $.parseJSON(data);
			if (d.success) {
				var msg = d.msg;
				tip(msg);
			}
		},
		cache: false
	});
}

/**
* 根据直播会议ID设置停止直播
*/
function stopLive(id){
	$.messager.confirm('确认','确定要停止直播?',function(r){
		if(r){
			var url = "meetingInfoController.do?stopLive&id="+id;
			$.ajax({
				url: url,
				success: function(data){
					var d = $.parseJSON(data);
					if (d.success) {
						var msg = d.msg;
						tip(msg);
					}
				},
				cache: false
			});
		}
	});
}
/**窗口按钮操作相关------END------**/
</script>