var editRow = undefined;
var newplayid = new Date().getTime();
var editIndex = undefined;
var datagrid;
$(function() {
	datagrid = $('#dg').datagrid({
//			width:760,
//			height:200,
			iconCls: 'icon-edit',
			toolbar: '#tb',
			fit: true,
			fitColumns: true,
			pagination: true,
			pageSize: 5,
			pageList: [5],
			singleSelect: true,
			url: 'appointmentChannelInfoController.do?datagrid',
			queryParams:{
				meetingid: newplayid
			},
			idField : 'id',
			columns : [[
			            {field: 'id', hidden: true},
			            
			            {field: 'channelname', title: '频道名', align: 'center', sortable: false, hidden: true,
			            	formatter:function(value,row,index){
			            		return row.channelname;
			            	}
			            },

			            {field:'codec1id',title:'主编码器',align:'center',sortable:false,width :150,
			            		editor : {
				            		type : 'combobox',
				            		options : {
				            			required:true,
				            			url:'confCodecInfoController.do?combox',
				            			valueField : 'id',
				            			textField : 'name',
				            			panelHeight: "auto", // 设置容器高度自动增长
				            			onChange:function(newValue, oldValue){
											// 自己写你要做的事
											// alert('新值'+newValue+',旧值'+oldValue+'---index==
											// editRow =='+editRow);
	//			            				var codec2editor= $('#datagrid').datagrid('getEditor',{index:editRow,field:'codec2id'});
	//			            				var value2= codec2editor.target.combobox('getValue');  
				            				// $('#cc').combobox('reload','get_data.php');
				            				// //使用新的URL重新加载列表数据
	//			            				if (newValue!=oldValue){
	//			            					codec2editor.target.combobox('clear');	// 清除选项
	//			            					codec2editor.target.combobox('reload','confCodecInfoAction!combox.action?codeid='+newValue); // 为了测试，先从其它地方拿数据
	//			            				}
				            			}
				            		}
			            		},
			            		formatter:function(value,row,index){
			            			return transName(row.codec1Name==undefined?"":row.codec1Name);
			            		}
			            },

			            {field:'isrecord1',title:'是否录制',align:'center',sortable:false,width : 60,
			            	editor : {
			            		type : 'checkbox',
			            		options: {
			            			on: 1,
			            			off: 0
			            		}
			            	},
			            	formatter:function(value,row,index){
			            		return transCheckBox(value,row,index);
			            	}
			            },

			            {field:'codec2id',title:'备Codec',align:'center',sortable:false,width : 150,
			            	editor : {
			            		type : 'combobox',
			            		options : {
			            			url:'confCodecInfoController.do?combox'
			            			valueField : 'id',
			            			textField : 'name',
			            			panelHeight:"auto", // 设置容器高度自动增长
			            			onChange:function(newValue, oldValue){
			            				// 自己写你要做的事
			            				// alert('新值'+newValue+',旧值'+oldValue);
			            				// editRow
			            				// $('#datagrid').datagrid('getEditor',{index:rowIndex,field:''
			            				// });
			            			} 						  
			            		}
			            	},
			            	formatter:function(value,row,index){
			            		return transName(row.codec2Name);
			            	}
			            },

			            {field:'isrecord2',title:'是否录制',align:'center',sortable:false,width :60,
			            	editor : {
			            		type : 'checkbox',
			            		options: {
			            			on: 1,
			            			off: 0
			            		}
			            	},
			            	formatter:function(value,row,index){
			            		return transCheckBox(value,row,index);
			            	}
			            },
			            
			            {field:'authortiyGroupCid',title:'云翼设备分组',align:'center',sortable:false,width :150,			  	  
			            	editor : {
			            		type : 'combobox',
			            		options : {
			            			required:true,
			            			url:'authorityGroupController.do?combox'
			            			valueField : 'id',
			            			textField : 'name',
			            			panelHeight:"auto" // 设置容器高度自动增长
			            		},
			            		onSelect:function(rowIndex, rowData){
			            			
			            		}
			            	},

			            	formatter:function(value,row,index){
			            		return transName(row.authortiyTerminlgroupCname);
			            	}
			            },
			            // 自定义用户权限组ID
			            {field:'authortiyUsergroupCid',title:'自定义用户权限组',align:'center',sortable:false,width : 150,			  	 
			            	editor : {
			            		type : 'combobox',
			            		options : {
			            			url:'authorityUserGroupController.do?combox'
			            			valueField : 'id',
			            			textField : 'name',
			            			panelHeight:"auto" // 设置容器高度自动增长
			            		}
			            	},
			            	formatter:function(value,row,index){
			            		return transName(row.authortiyUsergroupCname);
			            	}
			            }
			            ]],

			            onLoadSuccess:function(index,row){

			            },
			            onBeforeEdit:function(index,row){
			            	row.editing = true;
			            	updateActions();
			            },
			            onAfterEdit:function(index,row){
			            	row.editing = false;
			            	updateActions(); // 这一行必须要调用；
			            	var inserted = datagrid.datagrid('getChanges', 'inserted');
			            	var updated = datagrid.datagrid('getChanges', 'updated');
			            	if (inserted.length < 1 && updated.length < 1) {	    
			            		editRow = undefined;
			            		datagrid.datagrid('unselectAll');
			            		return;
			            	}
			            	var url = 'appointmentChannelInfoController.do?save';
			            	$.ajax({
			            		url : url,
			            		data : row,
			            		dataType : 'json',
			            		success : function(r) {
			            			if (r.success) {
			            				datagrid.datagrid('acceptChanges');
			            				$.messager.show({
			            					msg : r.msg,
			            					title : '成功'
			            				});
			            				editRow = undefined;
			            				datagrid.datagrid('reload');
			            			} else {
			            				/* datagrid.datagrid('rejectChanges'); */
			            				datagrid.datagrid('beginEdit', editRow);
			            				$.messager.alert('错误', r.msg, 'error');
			            			}
			            			datagrid.datagrid('unselectAll');
			            		}
			            	});
			            },
			            onCancelEdit:function(index,row){
			            	row.editing = false;
			            	updateActions();
			            }
	});
});

function updateActions(){
	var rowcount = $('#dg').datagrid('getRows').length;
	for(var i=0; i<rowcount; i++){
		$('#dg').datagrid('updateRow',{
			index:i,
			row:{action:''}
		});
	}
}
	
function endEditing() {
	if (editIndex == undefined) {
		return true
	}
	if ($('#dg').datagrid('validateRow', editIndex)) {
		var ed = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'codec1id'
		});
		var name = $(ed.target).combobox('getText');
		$('#dg').datagrid('getRows')[editIndex]['name'] = name;
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