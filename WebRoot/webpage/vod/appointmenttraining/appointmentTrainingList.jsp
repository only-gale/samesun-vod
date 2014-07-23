<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="appointmentTrainingInfoList" title="会议预约" actionUrl="appointmentTrainingController.do?datagrid" idField="id" fitColumns="true" fit="true" queryMode="group" checkbox="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="预约时间" field="appointmentStarttime" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="预约持续时长" field="appointmentDuration" align="center"></t:dgCol>
   <t:dgCol title="预约状态" field="appointmentState" align="center" replace="新建_0,启用_1,过期_2"></t:dgCol>
   <t:dgCol title="所属类型" field="typeid" align="center" replace="公共类_1,专题类_2,讨论类_3"></t:dgCol>
   <t:dgCol title="主题" field="subject" query="true"></t:dgCol>
   <t:dgCol title="主讲人" field="compere" align="center"></t:dgCol>
   <t:dgCol title="简介" field="introduction" width="250"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="70"></t:dgCol>
   <t:dgFunOpt title="启用" funname="tostart(id)" exp="appointmentState#eq#0" />
   <t:dgFunOpt title="取消" funname="tocancel(id, appointmentState)" exp="appointmentState#eq#0" />
   <t:dgDelOpt title="删除" url="appointmentTrainingController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="appointmentTrainingController.do?addorupdate&appointment" funname="add"></t:dgToolBar>
   <%-- <t:dgToolBar title="录入" icon="icon-add" onclick="addmeeting()"></t:dgToolBar> --%>
   <t:dgToolBar title="编辑" icon="icon-edit" url="appointmentTrainingController.do?addorupdate" funname="update"></t:dgToolBar>
   <%-- <t:dgToolBar title="编辑" icon="icon-edit" operationCode="appointment" onclick="editmeeting()"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="appointmentTrainingController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='appointmentStarttime_begin']").attr("class","easyui-datebox");
		$("input[name='appointmentStarttime_end']").attr("class","easyui-datebox");
	});
	
	function tostart(id){
		var url = "appointmentTrainingController.do?opa";
		var data = {
				id: id,
				state: 1
		}
		$.post(url, data, function(data){
			var d = $.parseJSON(data);
			if (d.success) {
				var msg = d.msg;
				tip(msg);
				reloadTable();
				change2meeting(d.attributes.meetingid);
			}
		});
	}
	
	function tocancel(id, appointmentState){
		if(appointmentState != 0){
			tip("只能取消新建的预约会议");
			return false;
		}
		var url = "appointmentMeetingInfoController.do?opa";
		var data = {
				id: id,
				state: 2
		}
		$.post(url, data, function(data){
			var d = $.parseJSON(data);
			if (d.success) {
				var msg = d.msg;
				tip(msg);
				reloadTable();
			}
		});
	}
	
	function addmeeting(){
		var buttons = [{
			name: '新建',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	iframe.toadd();
            },
            focus: true
		},{
			name: '启用',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	alert('启用');
				return false;
            }
		},{
			name: '取消',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	alert('取消');
				return false;
            }
		}];
		$.dialog({
			content: 'url:appointmentTrainingController.do?addorupdate',
			lock : true,
			width : 700,
			height : 400,
			title : '创建直播会议',
			opacity : 0.3,
			cache : false,
			button : buttons,
		    cancelVal : '关闭',
		    cancel : true /*为true等价于function(){}*/
		});
	}
	
	function editmeeting(){
		var url = 'appointmentTrainingController.do?addorupdate';
		var rowsData = $('#appointmentTrainingInfoList').datagrid('getSelections');
		if (!rowsData || rowsData.length==0) {
			tip('请选择编辑项目');
			return;
		}else if (rowsData.length>1) {
			tip('请选择一条记录再编辑');
			return;
		}else if(rowsData[0].appointmentState != '0'){
			tip('请注意, 只可编辑新建状态下的记录');
			return;
		}
		url += ('&id=' + rowsData[0].id);
		var buttons = [{
			name: '新建',
            callback: function(){
            	iframe = this.iframe.contentWindow;
				alert('新建');
				return false;
            },
            focus: true
		},{
			name: '启用',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	alert('启用');
				return false;
            }
		},{
			name: '取消',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	alert('取消');
				return false;
            }
		}];
		$.dialog({
			content: 'url:' + url,
			lock : true,
			width : 700,
			height : 400,
			title : '编辑直播会议',
			opacity : 0.3,
			cache : false,
			button : buttons,
		    cancelVal : '关闭',
		    cancel : true /*为true等价于function(){}*/
		});
	}
	
	function change2meeting(meetingid){
		var url = 'trainingInfoController.do?addorupdate';
		url += '&id=' + meetingid;
		var buttons = [{
			name: '开始直播',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	if(iframe.checkDg()){
			    	iframe.save("live");
			    	//控制按钮可用状态
	            	this.button({
	            		name: '开始直播',
	            		disabled: true
	            	},{
	            		name: '停止直播',
	            		disabled: false
	            	});
		    	}
				return false;
            }
		},{
			name: '开始录制',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	this.button({
            		name: '开始录制',
            		disabled: true
            	},{
            		name: '停止录制',
            		disabled: false
            	});
				return false;
            },
            disabled:true
		},{
			name: '停止录制',
            callback: function(){
            	iframe = this.iframe.contentWindow;
				return false;
            },
            disabled:true
		},{
			name: '停止直播',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	this.close();
				return false;
            },
            disabled:true
		}];
		var dia = $.dialog({
			content: 'url:' + url,
			lock : true,
			width : 700,
			height : 400,
			title : '培训',
			opacity : 0.3,
			cache : false,
			button : buttons,
		    cancelVal : '关闭',
		    cancel : true /*为true等价于function(){}*/
		});
	}
	
	function checkFields() {       
		if ( $("#typeid").combobox('getValue')==''){
			$.messager.alert('直播会议栏位校验','请选择所属类型！');   
			// alert('请选择所属类型！') ;
			return false;
		}
		    
		if ( $("#compere").val().trim()==''){
			//alert('请录入主持人 !') ; 
			$.messager.alert('直播会议栏位校验','请录入主持人 !');
			return false;
		   		
		}	   
		if ( $("#subject").val()==''){
			//alert('请录入会议主题 !') ; 
			$.messager.alert('直播会议栏位校验','请录入会议主题 !');
			return false;
		   		
		}	   	 

		if ($('#appointment_StartTime').datetimebox('getValue').trim()==''){	   	
			$.messager.alert('预约时间栏位校验','请录入预约时间!');
			return false;
		}
		   	
		if ($('#appointment_Duration').numberbox('getValue').trim()==''){
		   		  
			$.messager.alert('预约时长栏位校验','请录入预约时长 !');
			return false;
		}
		   	
		if ( $("#introduction").val().trim()=='') {
			//alert('请录入简介 !') ; 
			$.messager.alert('直播会议栏位校验','请录入简介 !');
			return false;
		}
		       		   		 
		return true;	   
	}
	
	
</script>