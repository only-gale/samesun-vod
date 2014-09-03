<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="vodSession4TrainingList" title="点播信息会话" actionUrl="vodSessionController.do?datagrid&rightid=2" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="会议ID" field="meetingid" hidden="false" ></t:dgCol>
   <t:dgCol title="liveSeesionID" field="liveSession" hidden="false" ></t:dgCol>
   <t:dgCol title="会议类型" field="typeid" align="center" query="true" hidden="false" dictionary="trainingTp," ></t:dgCol>
   <t:dgCol title="会议类型" field="typename" align="center"></t:dgCol>
   <t:dgCol title="会议主题" field="subject" align="center" query="true" width="60" ></t:dgCol>
   <t:dgCol title="会议主持人" field="compere" ></t:dgCol>
   <t:dgCol title="会议简介" field="introduction" width="100" ></t:dgCol>
   <t:dgCol title="录制时间" field="begindt" formatter="yyyy-MM-dd hh:mm:ss" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="结束录制时间" field="enddt" formatter="yyyy-MM-dd hh:mm:ss" hidden="false"></t:dgCol>
   <t:dgCol title="持续时间(分钟)" field="duration" align="center" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="30"></t:dgCol>
   <t:dgDelOpt title="删除" url="vodSessionController.do?del&id={id}" />
   <t:dgFunOpt title="明细" funname="details(meetingid, liveSession)" />
   <t:dgToolBar title="录入" icon="icon-add" url="vodSessionController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="vodSessionController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
		$("input[name='begindt_begin']").attr("class","easyui-datebox");
		$("input[name='begindt_end']").attr("class","easyui-datebox");
	});
 
 function details(meetingid, liveSession){
	 var url = "vodSectionRecordController.do?vodSectionRecord";
	 url = url + ("&sessionid=" + liveSession) + ("&meetingid=" + meetingid) + ("&rightid=" + 2);
	 $.dialog({
			content: 'url:' + url,
			lock : true,
			width: 900,
			height: 400,
			title: '明细',
			opacity : 0.3,
			cache: false,
		    cancelVal : '关闭',
		    cancel : true
		});
 }
</script>