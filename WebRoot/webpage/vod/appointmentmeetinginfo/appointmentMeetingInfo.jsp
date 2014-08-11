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

<!-- 隐藏域 -->
<input id="commond" name="commond" type="hidden"
			value="${commond }">

	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="appointmentMeetingInfoController.do?save">
		<%--会议ID --%>
		<input id="id" name="id" type="hidden"
			value="${appointmentMeetingInfoPage.id }">

		<input id="load" name="load" type="hidden" value="${load }" />

		<input id="tempid" name="tempid" type="hidden" />

		<table style="width: 100%;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right" width="13%"><label class="Validform_label">
						<span><font color="red">*</font></span>预约时间: </label></td>
				<td class="value" width="37%"><input class="Wdate" datatype="*" nullmsg=" " errormsg=" " sucmsg=" " readonly
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					style="width: 150px" id="appointmentStarttime"
					name="appointmentStarttime"
					value="<fmt:formatDate value='${appointmentMeetingInfoPage.appointmentStarttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
					<span class="Validform_checktip"></span></td>
				<td align="right" width="13%"><label class="Validform_label">
						<span><font color="red">*</font></span>预约持续时长: </label></td>
				<td class="value" width="37%"><input class="easyui-numberbox" data-options="min:0" datatype="*" nullmsg=" " errormsg=" " sucmsg=" "
					id="appointmentDuration" name="appointmentDuration"
					value="${appointmentMeetingInfoPage.appointmentDuration}"
					datatype="*"><span><font color="red">(分钟)</font></span> <span style="width:30px"
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<%-- <td align="right"><label class="Validform_label"> 预约状态:
				</label></td>
				<td class="value">
					<c:choose>
					<c:when test="${appointmentMeetingInfoPage.appointmentState eq null}">${state }
					<input type="hidden" id="appointmentState" name="appointmentState" value="${statecode }"/>
					</c:when>
					<c:otherwise>${state }
					<input type="hidden" id="appointmentState" name="appointmentState" value="${appointmentMeetingInfoPage.appointmentState }"/>
					</c:otherwise>
					</c:choose>
					<select id="appointmentState" name="appointmentState" class="easyui-combobox">
						<c:forEach items="${states}" var="state">
							<option value="${state.typecode }"
								<c:if test="${appointmentMeetingInfoPage.appointmentState eq state.typecode}"> selected='selected'</c:if>>${state.typename }</option>
						</c:forEach>
				</select> <span class="Validform_checktip"></span>
				</td> --%>
				
				<td align="right"><label class="Validform_label"> <span><font color="red">*</font></span>会议主题:
				</label></td>
				<td class="value"><input class="inputxt" id="subject" datatype="*"
					name="subject" nullmsg=" " errormsg=" " sucmsg=" "
					value="${appointmentMeetingInfoPage.subject}"> <span
					class="Validform_checktip"></span></td>
				
				<td align="right"><label class="Validform_label"> 所属类型:
				</label></td>
				<td class="value">
					<%-- <input class="inputxt" id="typeid"
					name="typeid" ignore="ignore"
					value="${appointmentMeetingInfoPage.typeid}" datatype="n"> --%> <select
					id="typeid" name="typeid" class="easyui-combobox" data-options="panelHeight:'auto'">
						<c:forEach items="${appTypes}" var="type">
							<option value="${type.typecode }"
								<c:if test="${appointmentMeetingInfoPage.typeid eq type.typecode}"> selected='selected'</c:if>>${type.typename }</option>
						</c:forEach>
				</select> <span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				
				<td align="right"><label class="Validform_label">
						会议主持人: </label></td>
				<td class="value"><input class="inputxt" id="compere"
					name="compere" ignore="ignore"
					value="${appointmentMeetingInfoPage.compere}"> <span
					class="Validform_checktip"></span></td>
					
				<td align="right"></td>
				<td class="value"></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 会议简介:
				</label></td>
				<td class="value" colspan="3">
				<textarea cols="96" style="margin:5px auto; resize:none" id="introduction" name="introduction" value="${appointmentMeetingInfoPage.introduction}">${appointmentMeetingInfoPage.introduction}</textarea>
				<span class="Validform_checktip"></span></td>
			</tr>
			<%-- <tr>
				<td align="right"><label class="Validform_label"> 会议简介:
				</label></td>
				<td class="value" colspan="3">
				<input class="inputxt" id="introduction"
					name="introduction" ignore="ignore"
					value="${appointmentMeetingInfoPage.introduction}">
				<span class="Validform_checktip"></span></td>
			</tr> --%>
		</table>
		<div class="easyui-accordion" style="width:700px;height:230px;">
			<div title="频道信息" style="padding:10px"
				data-options="
				selected:true">
				<table id="dg" class="easyui-datagrid"
					data-options="iconCls: 'icon-edit',toolbar: '#tb',
					fit:true,fitColumns:true,pagination:true,pageSize:5,pageList:[5],singleSelect:true,url:'appointmentChannelInfoController.do?datagrid',
		   queryParams:{
		           appointmentid: meetingId
	               },idField : 'id',onAfterEdit:afterEdit, onBeforeLoad: initParams">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true"></th>
							<th data-options="field:'appoinementid',hidden:true"></th>
							<th data-options="field:'meetingid',hidden:true"></th>
							<th
								data-options="field:'codec1id',width:150,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								url:'confCodecInfoController.do?combox&meetingType=appointment',
								required:true,
								panelHeight:'auto',
								onBeforeLoad:function(param){
									if(check()){
										param.appointmentStarttime = $('#appointmentStarttime').val();
										param.appointmentDuration = $('#appointmentDuration').val();
										param.excepts = excepts;
									}
								},
								onSelect:function(record){
									wetheruesed(record.id, 'codec1id');
									tofixcombox2(record.id);
								}
							}
						},
						formatter:function(value,row,index){
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
								url:'confCodecInfoController.do?combox&meetingType=appointment',
								panelHeight:'auto',
								onBeforeLoad:function(param){
									if(check()){
										param.appointmentStarttime = $('#appointmentStarttime').val();
										param.appointmentDuration = $('#appointmentDuration').val();
										param.excepts = excepts;
									}
								},
								onSelect:function(record){
									wetheruesed(record.id, 'codec2id');
									tofixcombox1(record.id);
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
								url:'authorityGroupController.do?combox'
							}
						},
						formatter:function(value,row,index){
							return transName(row.authortiyGroupCname);
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
						data-options="iconCls:'icon-undo',plain:true" onclick="reject()">取消</a>
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
	var meetingId = $("#id").val(), excepts = "";
	
	if("" == meetingId || "null" == meetingId){
		meetingId = new Date().getTime();
		$("#tempid").val(meetingId);
	}
	
	function initParams(){
		$("#dg").datagrid('options').queryParams['meetingid'] = meetingId;
		if('detail' == $("#load").val()){
			//当是查看请求时，则隐藏工具栏
			$("#tb a").hide();
		}
	}
	
	var editIndex = undefined;
	
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
	
	function endEditing() {
		if (editIndex == undefined) {
			return check();
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
			return false;
		}
	}

	function append() {
		if (endEditing()) {
			$('#dg').datagrid('appendRow', {
				isrecord1 : 1	//主编码器默认录制
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
		var id = $('#dg').datagrid('getRows')[editIndex][id];
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
    	var url = 'appointmentChannelInfoController.do?save&appointmentId=' + meetingId;
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
	
	function check(){
		var appointmentStarttime = $("#appointmentStarttime").val();
		var appointmentDuration = $("#appointmentDuration").val();
		if('' == appointmentStarttime || '' == appointmentDuration){
			tip('请填写预约时间和预约持续时间');
			return false
		}else{
			//获取当前频道之外已被选择的编码器id值并以逗号分隔，用于过滤当前频道可选择的编码器
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
			return true;
		}
	}
	
	function tofixcombox1(id){
		var codec1id_ed = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'codec1id'
		});
		excepts = excepts.concat("," + id);
		var url = "confCodecInfoController.do?combox&meetingType=live&excepts="+excepts;
		$(codec1id_ed.target).combobox('reload', url);
		return false;
	}

	function tofixcombox2(id){
		var codec2id_ed = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'codec2id'
		});
		excepts = excepts.concat("," + id);
		var url = "confCodecInfoController.do?combox&meetingType=live&excepts="+excepts;
		$(codec2id_ed.target).combobox('reload', url);
		return false;
	}
	
	function wetheruesed(id, field){
		var flag = false;
		var url = "confCodecInfoController.do?wetheruesed&meetingType=live&id=" + id;
		$.ajax({
			async: false,
			url : url,
			success : function(data) {
				var d = $.parseJSON(data);
				//成功后事件
				if (d.success) {
					var msg = d.msg;
					if(msg == 'false'){
						var contentUrl = 'confCodecInfoController.do?whouesed&meetingType=appointment&id=' + id;
						var api = frameElement.api, W = api.opener;
						W.$.dialog({title:'冲突的会议信息',content: 'url:'+contentUrl,lock:true,parent:api,width:500,height:300, cancel:true});
						var codec = $('#dg').datagrid('getEditor', {
							index : editIndex,
							field : field
						});
						$(codec.target).combobox('setValue', '');
					}else{
						excepts = excepts.concat("," + id);
					};
				}
			},
			cache : false
		});
		
	}
</script>