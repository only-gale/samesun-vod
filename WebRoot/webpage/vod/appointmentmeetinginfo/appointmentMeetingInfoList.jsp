<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="appointmentMeetingInfoList" title="会议预约" actionUrl="appointmentMeetingInfoController.do?datagrid" idField="id" fitColumns="true" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="种类" field="rightid" hidden="false"></t:dgCol>
   <t:dgCol title="预约时间" field="appointmentStarttime" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="预约持续时长(分钟)" field="appointmentDuration" align="center"></t:dgCol>
   <t:dgCol title="预约状态" field="appointmentState" align="center" replace="新建_0,启用_1,过期_2"></t:dgCol>
   <t:dgCol title="所属类型" field="typeid" align="center" replace="公共类_1,专题类_2,讨论类_3" hidden="false"></t:dgCol>
   <t:dgCol title="所属类型" field="typename" align="center"></t:dgCol>
   <t:dgCol title="是否录制" field="isrecord" replace="否_0,是_1" align="center" ></t:dgCol>
   <t:dgCol title="会议主题" field="subject" query="true" width="100"></t:dgCol>
   <t:dgCol title="会议主持人" field="compere" align="center"></t:dgCol>
   <t:dgCol title="会议简介" field="introduction" width="200"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="70"></t:dgCol>
   <t:dgFunOpt title="启用" funname="tostart(id, rightid)" exp="appointmentState#ne#2" />
   <t:dgDelOpt title="删除" url="appointmentMeetingInfoController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="appointmentMeetingInfoController.do?addorupdate&rightid=1" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="appointmentMeetingInfoController.do?addorupdate&rightid=1" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='appointmentStarttime_begin']").attr("class","easyui-datebox");
		$("input[name='appointmentStarttime_end']").attr("class","easyui-datebox");
	});
	
	function tostart(id, rightid) {
		var url = "appointmentMeetingInfoController.do?opa";
		var jsonData = {
				id: id,
				state: 1,
				rightid: rightid		//1会议, 2培训
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
				var result = d.attributes.result;
				if (d.success) {
					var msg = d.msg;
					if('success' == result){
						tip(msg);
						change2meeting(d.attributes.meetingid, d.attributes.meetingstate, d.attributes.isrecord);
					}else if('conflict' == result){
						var contentUrl = 'appointmentMeetingInfoController.do?whouesed&meetingType=live&id=' + id;
						//var api = frameElement.api, W = api.opener;
						$.dialog({title:'冲突的会议信息',content: 'url:'+contentUrl,lock:true,width:500,height:300, cancel:true});
					}else if('failed' == result){
						tip(msg);
						return false;
					}
				}
			},
			cache : false,
            complete:function(XHR, TS){
                $.messager.progress('close');
             }
		});
	}
	
	function change2meeting(meetingid, state, isrecord){
		var title = "编辑";
		
		var url = 'meetingInfoController.do?addorupdate';
		url += ('&load=editlive&id=' + meetingid);
		
		editmeetingwindow(title, url, state, isrecord, meetingid);
	}
	
	function editmeetingwindow(title, addurl, state, isrecord, id) {
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
			    	iframe.save("live");
			    	//控制按钮可用状态
			    	this.button({
			    			id: 'liveBtn',
		            		disabled: true
		            	},{
		            		id: 'stopLiveBtn',
		            		disabled: false
		            	});
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
			height: 420,
			title: title + '直播会议',
			opacity : 0.3,
			button: buttons,
			close: function(){
				$("#appointmentMeetingInfoList").datagrid('reload');
				return true;
			},
			cancelVal : '关闭',
		    cancel : true
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