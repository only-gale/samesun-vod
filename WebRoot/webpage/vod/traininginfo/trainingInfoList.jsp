<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="trainingInfoList" title="培训信息" actionUrl="trainingInfoController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="VRS_ID号" field="billid" hidden="false" ></t:dgCol>
   <t:dgCol title="设备名" field="billname" hidden="false" ></t:dgCol>
   <t:dgCol title="开始时间" field="billstarttime" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="持续时长" field="billduration" align="center" ></t:dgCol>
   <t:dgCol title="Live类型" field="isasflive" replace="直播_1,录播_2" align="center" hidden="false" ></t:dgCol>
   <t:dgCol title="是否录制" field="isrecord" replace="否_0,是_1" align="center" ></t:dgCol>
   <t:dgCol title="培训状态" field="meetingstate" replace="直播中_1,直播并录制中_2,停止录制_3,已结束_4,待直播_5" align="center" ></t:dgCol>
   <t:dgCol title="培训有限状态机" field="fsmstate" hidden="false" ></t:dgCol>
   <t:dgCol title="预约录制时间" field="appointmentdt" hidden="false" ></t:dgCol>
   <t:dgCol title="预约状态" field="appointmentstate" hidden="false" ></t:dgCol>
   <t:dgCol title="资源URL" field="asfurl" hidden="false" ></t:dgCol>
   <t:dgCol title="培训名称" field="name" hidden="false" ></t:dgCol>
   <t:dgCol title="所属类型" field="typeid" align="center" replace="公共类_1,专题类_2,讨论类_3" hidden="false" ></t:dgCol>
   <t:dgCol title="所属类型" field="typename" align="center" ></t:dgCol>
   <t:dgCol title="终端分组" field="rightid" align="center" hidden="false" ></t:dgCol>
   <t:dgCol title="培训主题" field="subject" align="center" query="true" width="100" ></t:dgCol>
   <t:dgCol title="培训主讲人" field="compere" align="center" ></t:dgCol>
   <t:dgCol title="培训简介" field="introduction" width="300" ></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="trainingInfoController.do?del&id={id}" /> --%>
   <t:dgToolBar title="录入" icon="icon-add" funname="addtraining"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" funname="edittraining"></t:dgToolBar>
   <%-- <t:dgToolBar title="查看" icon="icon-search" url="trainingInfoController.do?addorupdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
	$(document).ready(function(){
		$("input[name='billstarttime_begin']").attr("class","easyui-datebox");
		$("input[name='billstarttime_end']").attr("class","easyui-datebox");
	});
	
	function addtraining(){
		var title = "创建";
		var addurl = "meetingInfoController.do?addorupdateT";
		livetrainingwindow(title, addurl)
	}
	
	function edittraining(){
		var title = "编辑";
		var addurl = "meetingInfoController.do?addorupdateT";
		var rowsData = $('#trainingInfoList').datagrid('getSelections');
		if (!rowsData || rowsData.length==0) {
			tip('请选择编辑项目');
			return;
		}else if (rowsData.length>1) {
			tip('请选择一条记录再编辑');
			return;
		}else if (rowsData[0].meetingstate == 4) {
			tip('该培训已经结束');
			return;
		}
		addurl += ('&load=editlive&id='+rowsData[0].id);
		edittrainingwindow(title, addurl, rowsData[0].meetingstate, rowsData[0].isrecord, rowsData[0].id);
	}
	
	/**
	 * 创建添加或编辑窗口
	 * 
	 * @param title
	 * @param addurl
	 * @param saveurl
	 */
	function livetrainingwindow(title, addurl) {
		
		var buttons = [{
			id: 'liveBtn',
			name: '开始直播',
            callback: function(){
            	iframe = this.iframe.contentWindow;
		    	if(iframe.checkDg()){
			    	iframe.save("live");
			    	//控制按钮可用状态
			    	this.button({
			    			id: 'liveBtn',
		            		disabled: true
		            	},{
		            		id: 'stopLiveBtn',
		            		disabled: false
		            	});
			    	if(iframe.isrecord()){
			    		this.button({
			    			id: 'recordBtn',
		            		disabled: false
		            	});
			    		iframe.initaccord($("#id", iframe.document).val());
			    	}
		    	}
				return false;
            }
		},{
			id: 'recordBtn',
			name: '开始录制',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	//禁用开始录制按钮
            	this.button({
            		id: 'recordBtn',
            		disabled: true
            	});
        		iframe.startRecord($("#id", iframe.document).val());
				return false;
            },
            disabled: true
		},{
			id: 'stopRecordBtn',
			name: '停止录制',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	//禁用停止录制按钮
            	this.button({
            		id: 'stopRecordBtn',
            		disabled: true
            	});
        		iframe.stopRecord($("#id", iframe.document).val());
				return false;
            },
            disabled: true
		},{
			id: 'stopLiveBtn',
			name: '停止直播',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	//禁用停止直播按钮
            	this.button({
            		id: 'stopLiveBtn',
            		disabled: true
            	});
				iframe.stopLive($("#id", iframe.document).val());
				return false;
            },
            disabled: true
		}];
		
		$.dialog({
			content: 'url:'+addurl,
			lock : true,
			width: 700,
			height: 430,
			title: title + '直播培训',
			opacity : 0.3,
			cache: false,
			button: buttons,
			close: function(){
				$("#trainingInfoList").datagrid('reload');
				return true;
			},
		    cancelVal : '关闭',
		    cancel : true
		});
		
	}
	
	function edittrainingwindow(title, addurl, state, isrecord, id) {
		var btnstate = new Array(4);
		//直播中
		if(1 == state){
			//可录制
			if(1 == isrecord){
				btnstate = new Array(true, false, true, false);
			}else{
				btnstate = new Array(true, true, true, false);
			}
		}else if(2 == state){	//直播并录制中
			btnstate = new Array(true, true, false, false);
		}else if(3 == state){	//停止录制
			btnstate = new Array(true, false, true, false);
		}else if(4 == state){	//已结束
			btnstate = new Array(true, true, true, true);
		}else if(5 == state){	//已延时
			btnstate = new Array(false, true, true, true);
		}
		
		var buttons = [{
			id: 'liveBtn',
			name: '开始直播',
            callback: function(){
            	iframe = this.iframe.contentWindow;
		    	if(iframe.checkDg()){
			    	iframe.save();
			    	//控制按钮可用状态
			    	this.button({
			    			id: 'liveBtn',
		            		disabled: true
		            	},{
		            		id: 'stopLiveBtn',
		            		disabled: false
		            	});
			    	return false;
			    	
			    	if(1 == isrecord){
			    		this.button({
			    			id: 'recordBtn',
		            		disabled: false
		            	});
			    		iframe.initaccord(id);
			    	}
		    	}
				return false;
            },
            disabled: btnstate[0]
		},{
			id: 'recordBtn',
			name: '开始录制',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	//禁用开始录制按钮
            	this.button({
            		id: 'recordBtn',
            		disabled: true
            	});
        		
        		iframe.startRecord(id);
				return false;
            },
            disabled: btnstate[1]
		},{
			id: 'stopRecordBtn',
			name: '停止录制',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	//禁用停止录制按钮
            	this.button({
            		id: 'stopRecordBtn',
            		disabled: true
            	});
            	iframe.stopRecord(id);
				return false;
            },
            disabled: btnstate[2]
		},{
			id: 'stopLiveBtn',
			name: '停止直播',
            callback: function(){
            	iframe = this.iframe.contentWindow;
            	//禁用停止直播按钮
            	this.button({
            		id: 'stopLiveBtn',
            		disabled: true
            	});
				iframe.stopLive(id);
				return false;
            },
            disabled: btnstate[3]
		}];
		
		$.dialog({
			content: 'url:'+addurl,
			lock : true,
			width: 700,
			height: 430,
			title: title + '直播会议',
			opacity : 0.3,
			button: buttons,
			close: function(){
				$("#trainingInfoList").datagrid('reload');
				return true;
			},
			cancelVal : '关闭',
		    cancel : true
		});
	}
</script>