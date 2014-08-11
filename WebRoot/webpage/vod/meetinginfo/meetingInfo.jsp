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
		layout="table" action="meetingInfoController.do?save"
		beforeSubmit="checkDg">
		<%--会议ID --%>
		<input id="id" name="id" type="hidden" value="${meetingInfoPage.id }">

		<%--预约ID --%>
		<input id="billid" name="billid" type="hidden"
			value="${meetingInfoPage.billid }">

		<input id="load" name="load" type="hidden" value="${load }" />

		<input id="tempid" name="tempid" type="hidden" />

		<input id="meetingstate" name="meetingstate" type="hidden"
			value="${meetingInfoPage.meetingstate }" />
			
		<input id="isrecord" name="isrecord" type="hidden"
			value="${meetingInfoPage.isrecord }" />
			
		<input id="appointmentdt" name="appointmentdt" type="hidden"
			value="${meetingInfoPage.appointmentdt }" />
			
		<input id="appointmentstate" name="appointmentstate" type="hidden"
			value="${meetingInfoPage.appointmentstate }" />

		<table style="width: 100%;" cellpadding="0" cellspacing="1"
			class="formtable">
			<tr>
				<td align="right" width="15%"><label class="Validform_label">
						所属类型: </label></td>
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
				<td align="right"><label class="Validform_label"> <span><font color="red">*</font></span>会议主题:
				</label></td>
				<td class="value" width="35%"><input class="inputxt"
					id="subject" name="subject" datatype="*" nullmsg=" " errormsg=" "
					sucmsg=" " value="${meetingInfoPage.subject}"> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right" width="15%"><label class="Validform_label">
						会议主持人: </label></td>
				<td class="value"><input class="inputxt" id="compere"
					name="compere" ignore="ignore" value="${meetingInfoPage.compere}">
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td align="right"><label class="Validform_label"> 会议简介:
				</label></td>
				<td class="value">
				<textarea cols="97" rows="3" style="margin:5px auto; resize:none" id="introduction"
						name="introduction" ignore="ignore" value="${meetingInfoPage.introduction}">${meetingInfoPage.introduction}</textarea> <span
					class="Validform_checktip"></span></td>
			</tr>
		</table>
		<div id="accord">
			<div id="channelInfo" title="频道信息" style="padding:10px">
				<table id="dg" class="easyui-datagrid"
					data-options="iconCls: 'icon-edit',toolbar: '#tb',
					fit:true,fitColumns:true,pagination:true,pageSize:5,pageList:[5],singleSelect:true,url:'appointmentChannelInfoController.do?datagrid',
		   queryParams:{
		           meetingid: meetingId
	               },idField : 'id',onAfterEdit: afterEdit, onBeforeLoad: initParams">
					<thead>
						<th data-options="field:'id',hidden:true"></th>
						<th data-options="field:'appoinementid',hidden:true"></th>
						<th data-options="field:'meetingid',hidden:true"></th>
						<th
							data-options="field:'codec1id',width:150,align:'center',editor:{
							type:'combobox',
							options:{
								valueField:'id',
								textField:'name',
								url:'confCodecInfoController.do?combox4UserCodec&meetingType=live',
								required:true,
								panelHeight:'auto',
								onBeforeLoad:function(param){
									getexcepts();
									param.excepts = excepts;
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
								url:'confCodecInfoController.do?combox4UserCodec&meetingType=live',
								formatter: function(row){
									var opts = $(this).combobox('options');
									return row[opts.textField];
								},
								panelHeight:'auto',
								onBeforeLoad:function(param){
									getexcepts();
									param.excepts = excepts;
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
								required:true,
								url:'authorityGroupController.do?combox',
								onSelect:function(record){
									wetheruesedterminal(record.id);
								}
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
						data-options="iconCls:'icon-remove',plain:true"
						onclick="removeit()">删除</a>
				</div>
				<input type="submit" id="submit" style="display:none" />
			</div>
			<c:if test="${(meetingInfoPage.isrecord eq 1 and meetingInfoPage.meetingstate eq 1 ) or meetingInfoPage.meetingstate eq 3}">
		<div id="appRec" title="预约录制" style="padding:10px">
			<table>
				<tr height="50px">
					<td id="tipcontent" colspan="4"><c:if
							test="${meetingInfoPage.appointmentdt ne '' and meetingInfoPage.appointmentdt ne null }">
							<span>该直播会议将在 <font color="red">${meetingInfoPage.appointmentdt}</font>
								开始录制
							</span>
						</c:if></td>
				</tr>
				<tr>
					<td width="30%"></td>
					<td colspan="2"><input id="timersetting" />
					</td>
					<td width="20%"></td>
				</tr>
				<c:choose>
					<c:when test="${meetingInfoPage.appointmentstate eq 1 }">
						<tr>
					<td width="30%"></td>
					<td align="right"><input type="button" name="btn_timerSetting" disabled  onclick="tostart()"
						id="btn_timerSetting" class="" value="启用" /></td>
					<td align="left"><input type="button" name="btn_timercancel" onclick="tocancel()"
						id="btn_timercancel" class="" value="取消" /></td>
					<td width="20%"></td>
				</tr>
					</c:when>
					<c:otherwise>
						<tr>
					<td width="30%"></td>
					<td align="right"><input type="button" name="btn_timerSetting" onclick="tostart()"
						id="btn_timerSetting" class="" value="启用" /></td>
					<td align="left"><input type="button" name="btn_timercancel" onclick="tocancel()"
						id="btn_timercancel" class="" value="取消" disabled /></td>
					<td width="20%"></td>
				</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		</c:if>
		</div>


	</t:formvalid>
	
	<div id="appRec1" style="display: none;">
			<table>
				<tr height="50px">
					<td id="tipcontent1" colspan="4"><c:if
							test="${meetingInfoPage.appointmentdt ne '' and meetingInfoPage.appointmentdt ne null }">
							<span>该直播会议将在 <font color="red">${meetingInfoPage.appointmentdt}</font>
								开始录制
							</span>
						</c:if></td>
				</tr>
				<tr>
					<td width="30%"></td>
					<td colspan="2"><input id="timersetting1" />
					</td>
					<td width="20%"></td>
				</tr>
						<tr>
					<td width="30%"></td>
					<td align="right"><input type="button" name="btn_timerSetting" onclick="tostart1()"
						id="btn_timerSetting1" class="" value="启用" /></td>
					<td align="left"><input type="button" name="btn_timercancel" onclick="tocancel1()"
						id="btn_timercancel1" class="" value="取消" disabled /></td>
					<td width="20%"></td>
				</tr>
			</table>
		</div>
	
</body>
<script type="text/javascript">
	var meetingId = $("#id").val(), excepts = "", editIndex = undefined;

	if ("" == meetingId || "null" == meetingId) {
		meetingId = new Date().getTime();
		$("#tempid").val(meetingId);
	}

	$(function() {
		$("#accord").accordion({
			width : 700,
			height : 235,
			fit : false
		});
		
		$("#timersetting").slider({
		    showTip: true,
		    width: 300,
		    value: 10,
		    max: 60,
		    tipFormatter: function(value){
		        return value + ' 分钟后开始录制';
		    }
		});
		
		$("#timersetting1").slider({
		    showTip: true,
		    width: 300,
		    value: 10,
		    max: 60,
		    tipFormatter: function(value){
		        return value + ' 分钟后开始录制';
		    }
		});
		
		if ('editlive' == $("#load").val()) {
			$(":input[type!='button']").attr("disabled","true");
			$(":select").attr("disabled","true");
		}
		
		var appointmentdt = $("#appointmentdt").val();
		var appointmentstate = $("#appointmentstate").val();
		var meetingstate = $("#meetingstate").val();
		/* if("" != appointmentdt && "" != appointmentstate){
			$('#accord').accordion('add', {
				title : '预约录制',
				content : $("#appRec").show(),
				selected : true
			});
			$("#channelInfo").toggle();
			if(1 == appointmentstate){		//当预约录制状态为启用时，禁用启用按钮，开启取消按钮
				$("#btn_timercancel").removeAttr("disabled");
				$("#btn_timerSetting").attr("disabled", "disabled");
			}else{
				$("#btn_timerSetting").removeAttr("disabled");
				$("#btn_timercancel").attr("disabled", "disabled");
			}
		} */
		
	});

	//预约录制中"启用"按钮事件
	function tostart(){
		var m = $("#timersetting").slider('getValue');
		var id = $("#id").val();
		var url = "meetingInfoController.do?beginApp&m=" + m + "&id=" + id;
		$.ajax({
			url : url,
			async: false,
			success : function(data) {
				var d = $.parseJSON(data);
				var status = d.attributes.status;
				var msg = d.msg;
				//成功后事件
				if (d.success && 'success' == status) {
					//启用按钮不可用
					$("#btn_timerSetting").attr("disabled", "disabled");
					//slider不可用
					$("#timersetting").slider('disable');
					//取消按钮可用
					$("#btn_timercancel").removeAttr("disabled");
					//选择频道信息面板
					//$("#accord").accordion('select', '频道信息');
					//收起预约录制面板
					//$("#appRec").toggle();
					//展示频道信息面板
					//$("#channelInfo").toggle();
					
					tip(msg);
					var appointmentdt = d.attributes.appointmentdt;
					var tipcontent = "<span>该直播会议将在 <font color='red'>" + appointmentdt + "</font>开始录制</span>";
					$("#tipcontent").html(tipcontent);
				} else if(d.success && 'failed' == status){
					$("#accord").accordion('select', '频道信息');
					tip(msg);
				}
			},
			cache : false
		});
	}
	
	//预约录制中"取消"按钮事件
	function tocancel(){
		var id = $("#id").val();
		var url = "meetingInfoController.do?cancelApp&id=" + id;
		$.ajax({
			url : url,
			success : function(data) {
				var d = $.parseJSON(data);
				var status = d.attributes.status;
				var msg = d.msg;
				//成功后事件
				if (d.success && 'success' == status) {
					//取消按钮不可用
					$("#btn_timercancel").attr("disabled", "disabled");
					//启用按钮可用
					$("#btn_timerSetting").removeAttr("disabled");
					//slider可用
					$("#timersetting").slider('enable');
					tip(msg);
					$("#tipcontent").html("");
				}else if(d.success && 'failed' == status){
					$("#accord").accordion('select', '频道信息');
					tip(msg);
				}
			},
			cache : false
		});
	}
	
	//预约录制中"启用"按钮事件
	function tostart1(){
		var m = $("#timersetting1").slider('getValue');
		var id = $("#id").val();
		var url = "meetingInfoController.do?beginApp&m=" + m + "&id=" + id;
		$.ajax({
			url : url,
			async: false,
			success : function(data) {
				var d = $.parseJSON(data);
				var status = d.attributes.status;
				//成功后事件
				if (d.success && 'success' == status) {
					//启用按钮不可用
					$("#btn_timerSetting1").attr("disabled", "disabled");
					//slider不可用
					$("#timersetting1").slider('disable');
					//取消按钮可用
					$("#btn_timercancel1").removeAttr("disabled");
					//选择频道信息面板
					//$("#accord").accordion('select', '频道信息');
					//收起预约录制面板
					//$("#appRec").toggle();
					//展示频道信息面板
					//$("#channelInfo").toggle();
					var msg = d.msg;
					tip(msg);
					var appointmentdt = d.attributes.appointmentdt;
					var tipcontent = "<span>该直播会议将在 <font color='red'>" + appointmentdt + "</font>开始录制</span>";
					$("#tipcontent1").html(tipcontent);
				}
			},
			cache : false
		});
	}
	
	//预约录制中"取消"按钮事件
	function tocancel1(){
		var id = $("#id").val();
		var url = "meetingInfoController.do?cancelApp&id=" + id;
		$.ajax({
			url : url,
			success : function(data) {
				var d = $.parseJSON(data);
				var status = d.attributes.status;
				var msg = d.msg;
				//成功后事件
				if (d.success && 'success' == status) {
					//取消按钮不可用
					$("#btn_timercancel1").attr("disabled", "disabled");
					//启用按钮可用
					$("#btn_timerSetting1").removeAttr("disabled");
					//slider可用
					$("#timersetting1").slider('enable');
					tip(msg);
					$("#tipcontent1").html("");
				}else if(d.success && 'failed' == status){
					$("#accord").accordion('select', '频道信息');
					tip(msg);
				}
			},
			cache : false
		});
	}
	
	function addtime(){
		$("#accord").accordion('add', '预约录制');
		$("#accord").accordion('select', '预约录制');
	}
	
	function deltime(){
		$("#accord").accordion('remove', '预约录制');
		$("#accord").accordion('select', '频道信息');
	}
	
	/*当本次请求为查看时，屏蔽datagrid的菜单按钮*/
	function initParams() {
		$("#dg").datagrid('options').queryParams['meetingid'] = meetingId;
		//当是查看请求时，则隐藏工具栏
		var load = $("#load").val();
		if ('detail' == load || 'editlive' == load) {
			$("#tb a").hide();
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

	/**检查相关------BEGIN------**/

	/* 校验会议必须设置有频道 */
	function checkDg() {
		var rows = $("#dg").datagrid("getRows");
		var rowcount = rows.length;
		if (rowcount == 0) {//记录条数等于0
			$.messager.alert('错误', '您还没有设置频道!', 'error');
			return false;
		} else if (editIndex != undefined) {
			/* for (var i = 0; i < rowcount; i++){
				var row = rows[i]; 
				if (row.editing){
					$.messager.alert('错误','第' + (i+1) + '个频道设置，请先保存一下!', 'error');
					break;
					return false;
				}
			} */
			$.messager.alert('错误', '请先保存频道信息', 'error');
			return false;
		} else
			return true;
	}

	/*
	 * 获取已被之前的频道选择的编码器ID，以便过滤当前频道可选择的编码器列表
	 */
	function getexcepts() {
		var ids = new Array();
		var index = 0;
		var rows = $("#dg").datagrid('getRows');
		var len = rows.length;
		for (var i = 0; i < len; i++) {
			ids[index] = rows[i].codec1id;
			ids[index + 1] = rows[i].codec2id;
			index = index + 2;
		}
		excepts = ids.join(",");
	}

	/*
	 * 当点击“开始直播”按钮时需要先检查是否录制，以便控制“开始录制”按钮的可用与否
	 */
	function isrecord() {
		var flag = false;
		var rows = $("#dg").datagrid("getRows");
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].isrecord1 == '1' || rows[i].isrecord2 == '1') {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	function tofixcombox1(id){
		var codec1id_ed = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'codec1id'
		});
		excepts = excepts.concat("," + $(codec1id_ed.target).combobox('getValue'));
		var url = "confCodecInfoController.do?combox4UserCodec&meetingType=live&excepts="+excepts;
		$(codec1id_ed.target).combobox('reload', url);
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
						var contentUrl = 'confCodecInfoController.do?whouesed&meetingType=live&id=' + id;
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
	
	function wetheruesedterminal(id){
		var flag = false;
		var url = "terminalInfoController.do?wetheruesedterminal&meetingType=live&id=" + id;
		$.ajax({
			url : url,
			success : function(data) {
				var d = $.parseJSON(data);
				//成功后事件
				if (d.success) {
					var msg = d.msg;
					if(msg == 'false'){
						var contentUrl = 'terminalInfoController.do?whouesed&meetingType=live&id=' + id;
						var api = frameElement.api, W = api.opener;
						W.$.dialog({title:'冲突的会议信息',content: 'url:'+contentUrl,lock:true,parent:api,width:500,height:300, cancel:true});
						var authortiyGroupCid = $('#dg').datagrid('getEditor', {
							index : editIndex,
							field : 'authortiyGroupCid'
						});
						$(authortiyGroupCid.target).combobox('setValue', '');
					}else return;
				}
			},
			cache : false
		});
		
	}
	
	function tofixcombox2(id){
		var codec2id_ed = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'codec2id'
		});
		excepts = excepts.concat("," + $(codec2id_ed.target).combobox('getValue'));
		var url = "confCodecInfoController.do?combox4UserCodec&meetingType=live&excepts="+excepts;
		$(codec2id_ed.target).combobox('reload', url);
		return false;
	}

	/**检查相关------END------**/

	/**datagrid操作相关------BEGIN------**/

	function onClickRow(index) {
		if (editIndex != index) {
			if (endEditing()) {
				/* $('#dg').datagrid('selectRow', index)
						.datagrid('beginEdit', index); */
				editIndex = index;
			} else {
				$('#dg').datagrid('selectRow', editIndex);
			}
		}
	}

	/*结束编辑前调用*/
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
			var authortiyGroupCname = $(authortiyGroupCid_ed.target).combobox(
					'getText');
			var authortiyUsergroupCname = $(authortiyUsergroupCid_ed.target)
					.combobox('getText');
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

	/*结束编辑后调用*/
	function afterEdit(index, row) {
		row.editing = false;
		var url = 'appointmentChannelInfoController.do?save&meetingId='
				+ meetingId;
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

	/*新增一行*/
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

	/*删除行，如果当前行无ID信息，则说明是新增的信息，直接删除即可；否则说明当前行信息已被保存在数据库，需要询问用户是否继续删除以便从数据库永久删除*/
	function removeit() {
		/* if (editIndex == undefined) {
			return
		} */

		var rowsData = $('#dg').datagrid('getSelections');
		if (!rowsData || rowsData.length == 0) {
			tip('请选择删除项目');
			return;
		}
		if (rowsData.length > 1) {
			tip('请选择一条记录再删除');
			return;
		}

		var id = $('#dg').datagrid('getRows')[editIndex].id;
		if ('' != id && 'null' != id && undefined != id) {
			$.messager.confirm('确认', ' 当前频道信息已被保存在数据库,是否删除?', function(r) {
				if (r) {
					$.ajax({
						url : 'appointmentChannelInfoController.do?del',
						data : {
							id : id
						},
						dataType : 'json',
						success : function(response) {
							$('#dg').datagrid('cancelEdit', editIndex)
									.datagrid('deleteRow', editIndex);
							editIndex = undefined;
							$.messager.show({
								title : '提示',
								msg : '删除成功！'
							});
						}
					});
				}
			});
		} else {

			$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow',
					editIndex);
		}
		editIndex = undefined;
	}

	/*保存已被编辑的数据*/
	function accept() {
		if (endEditing()) {
			$('#dg').datagrid('acceptChanges');
		}
	}
	function reject() {
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}

	/**datagrid操作相关------END------**/

	/**窗口按钮操作相关------BEGIN------**/

	/*
	 * 开始直播
	 */
	function save(flag) {
		var url = "meetingInfoController.do?save&" + flag;
		var id = $("#id").val();
		var load = $("#load").val();
		var tempid = $("#tempid").val();
		var typeid = $("#typeid").val();
		var subject = $("#subject").val();
		var compere = $("#compere").val();
		var introduction = $("#introduction").val();
		var billid = $("#billid").val();
		var isrecord = 0;
		var rows = $("#dg").datagrid("getRows");
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].isrecord1 == '1' || rows[i].isrecord2 == '1') {
				isrecord = 1;
				break;
			}
		}
		var jsonData = {
			id : id,
			load : load,
			tempid : tempid,
			typeid : typeid,
			subject : subject,
			compere : compere,
			introduction : introduction,
			isrecord : isrecord,
			billid : billid
		}
		$.ajax({
			url : url,
			async: false,
			data : jsonData,
			beforeSend: function(){
				//发送请求之前，开始进度条
				$.messager.progress({msg: '请稍后...'});
			},
			success : function(data) {
				//请求成功时结束进度条
				$.messager.progress('close');
				var d = $.parseJSON(data);
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					$("#id").val(d.attributes.meetingid);
				}
			},
			cache : false,
            complete:function(XHR, TS){
            	//请求完成时结束进度条
                $.messager.progress('close');
			}
		});
	}

	/*
	 * 根据直播会议ID设置开始录制
	 */
	function startRecord(id) {
		var url = "meetingInfoController.do?startRecord";
		var jsonData = {
			id : id
		};
		
		$.ajax({
			url : url,
			data : jsonData,
			beforeSend: function(){
				//发送请求之前，开始进度条
				$.messager.progress({msg: '请稍后...'});
			},
			success : function(data) {
				//请求成功时结束进度条
				$.messager.progress('close');
				var d = $.parseJSON(data);
				var api = frameElement.api;
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					if(-1 != msg.search('失败')){
						//如果开始录制失败，那么放开开始录制按钮
						api.button({
							id: 'recordBtn',
		            		disabled: false
		            	});
					}else{
						//如果开始录制成功，那么放开停止录制按钮
						api.button({
							id: 'stopRecordBtn',
		            		disabled: false
		            	});
					}
				}
			},
			cache : false,
            complete:function(XHR, TS){
                $.messager.progress('close');
             }
		});
	}

	/*
	 * 根据直播会议ID设置停止录制
	 */
	function stopRecord(id) {
		var url = "meetingInfoController.do?stopRecord&id=" + id;
		$.ajax({
			url : url,
			beforeSend: function(){
				//发送请求之前，开始进度条
				$.messager.progress({msg: '请稍后...'});
			},
			success : function(data) {
				//请求成功时结束进度条
				$.messager.progress('close');
				var d = $.parseJSON(data);
				var api = frameElement.api;
				if (d.success) {
					var msg = d.msg;
					tip(msg);
					if(-1 != msg.search('失败')){
						//如果停止录制失败，那么放开停止录制按钮
						api.button({
							id: 'stopRecordBtn',
		            		disabled: false
		            	});
					}else{
						//如果停止录制成功，那么放开开始录制按钮
						api.button({
							id: 'recordBtn',
		            		disabled: false
		            	});
						//iframe.addtime();
						//iframe.initaccord($("#id", iframe.document).val());
					}
				}
			},
			cache : false
		});
	}

	/*
	 * 根据直播会议ID设置停止直播
	 */
	function stopLive(id) {
		alert("stopLive " + id);
		var api = frameElement.api, W = api.opener;
		$.messager.confirm('确认', '确定要停止直播?', function(r) {
			if (r) {
				var url = "meetingInfoController.do?stopLive&id=" + id;
				$.ajax({
					url : url,
					beforeSend: function(){
        				//发送请求之前，开始进度条
        				$.messager.progress({msg: '请稍后...'});
        			},
					success : function(data) {
						//请求成功时结束进度条
        				$.messager.progress('close');
						var d = $.parseJSON(data);
						
						if (d.success) {
							var msg = d.msg;
							tip(msg);
							var result = d.attributes.result;
							var status = d.attributes.status;
							var isrecord = d.attributes.isrecord;
							if("success" == result){	//操作成功时
								if(2 == status){	//当对正在录制的直播进行停止操作时
									//禁用"停止录制"按钮
									api.button({
										id: 'stopRecordBtn',
	        		            		disabled: true
	        		            	});
								}else if((1 == status || 3 == status) && 1 == isrecord){	//当对正在直播且可录制的会议进行停止操作时
									//禁用"开始录制"按钮
									api.button({
										id: 'recordBtn',
	        		            		disabled: true
	        		            	});
								}
								//结束成功之后刷新列表并关闭窗口————有问题，先注释掉
								/* $("#meetingInfoList", W.document).datagrid('reload'); */
								api.close();
							}else if("failed" == result){
								//操作失败时开启"停止直播"按钮
								o.button({
									id: 'stopLiveBtn',
        		            		disabled: false
        		            	});
							}
						}
					},
					cache : false
				});
			}else{
				//开启停止直播按钮
            	api.button({
            		id: 'stopLiveBtn',
            		disabled: false
            	});
			}
		});
	}
	/**窗口按钮操作相关------END------**/

	function initaccord(id) {
		alert("initaccord: "+id);
		//当该会议没有开始录制时
		if(!hasbegin(id)){
			$('#accord').accordion('add', {
				title : '预约录制',
				content : $("#appRec1").show(),
				selected : true
			});
		}
	}
	
	function hasbegin(id) {
		//默认没开始录制
		var flag = false;
		var url = "meetingInfoController.do?hasBegin&id=" + id;
		$.ajax({
			url : url,
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					var msg = d.msg;
					if("false" != msg){
						flag = true;
					}
				}
			},
			cache : false
		});
		
		return flag;
	}
	
/* 	function hasbegin(id) {
		//默认没开始录制
		var meetingstate = $("#meetingstate").val();
		alert(meetingstate);
		if(2 == meetingstate){
			return true;
		}
		return false;
	} */
	
	function hasapp(){
		var appointmentdt = $("#appointmentdt").val();
		var appointmentstate = $("#appointmentstate").val();
		if("" != appointmentdt && "" != appointmentstate && 2 == appointmentstate){
			return true;
		}
		return false;
	}
	
</script>