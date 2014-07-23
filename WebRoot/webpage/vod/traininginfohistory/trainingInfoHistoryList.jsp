<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="trainingInfoHistoryList" title="培训日志" actionUrl="trainingInfoHistoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="VRS_ID号" field="billid" hidden="false" ></t:dgCol>
   <t:dgCol title="设备名" field="billname" hidden="false" ></t:dgCol>
   <t:dgCol title="时间" field="billstarttime" formatter="yyyy-MM-dd hh:mm:ss" align="center" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="持续时长(分钟)" field="billduration" align="center" ></t:dgCol>
   <t:dgCol title="Live类型" field="isasflive" replace="直播_1,录播_2" align="center" ></t:dgCol>
   <t:dgCol title="是否录音" field="isrecord" replace="否_0,是_1" align="center" ></t:dgCol>
   <t:dgCol title="培训状态" field="meetingstate" replace="直播中_1,直播并录制中_2,已结束_3,已延时_4" align="center" ></t:dgCol>
   <t:dgCol title="培训有限状态机" field="fsmstate" hidden="false" ></t:dgCol>
   <t:dgCol title="预约录制时间" field="appointmentdt" hidden="false" ></t:dgCol>
   <t:dgCol title="预约状态" field="appointmentstate" hidden="false" ></t:dgCol>
   <t:dgCol title="资源URL" field="asfurl" hidden="false" ></t:dgCol>
   <t:dgCol title="培训名称" field="name" hidden="false" ></t:dgCol>
   <t:dgCol title="所属类型" field="typeid" align="center" replace="公共类_1,专题类_2,讨论类_3" ></t:dgCol>
   <t:dgCol title="终端分组" field="rightid" align="center" ></t:dgCol>
   <t:dgCol title="培训主题" field="subject" align="center" query="true" ></t:dgCol>
   <t:dgCol title="培训主讲人" field="compere" align="center" ></t:dgCol>
   <t:dgCol title="培训简介" field="introduction" align="center" width="150" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="trainingInfoHistoryController.do?del&id={id}" />
   <%-- <t:dgToolBar title="录入" icon="icon-add" url="trainingInfoHistoryController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="trainingInfoHistoryController.do?addorupdate" funname="update"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="trainingInfoHistoryController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='billstarttime_begin']").attr("class","easyui-datebox");
		$("input[name='billstarttime_end']").attr("class","easyui-datebox");
	});
</script>