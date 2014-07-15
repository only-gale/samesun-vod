<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="meetingHistoryList" title="历史会议" actionUrl="meetingHistoryController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="VRS_ID号" field="billid" ></t:dgCol>
   <t:dgCol title="设备名" field="billname" ></t:dgCol>
   <t:dgCol title="时间" field="billstarttime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="持续时长" field="billduration" ></t:dgCol>
   <t:dgCol title="Live类型" field="isasflive" ></t:dgCol>
   <t:dgCol title="是否录音" field="isrecord" ></t:dgCol>
   <t:dgCol title="会议状态" field="meetingstate" ></t:dgCol>
   <t:dgCol title="会议有限状态机" field="fsmstate" ></t:dgCol>
   <t:dgCol title="预约录制时间" field="appointmentdt" ></t:dgCol>
   <t:dgCol title="预约状态" field="appointmentstate" ></t:dgCol>
   <t:dgCol title="资源URL" field="asfurl" ></t:dgCol>
   <t:dgCol title="会议名称" field="name" ></t:dgCol>
   <t:dgCol title="所属类型" field="typeid" ></t:dgCol>
   <t:dgCol title="终端分组" field="rightid" ></t:dgCol>
   <t:dgCol title="会议主题" field="subject" ></t:dgCol>
   <t:dgCol title="会议主持人" field="compere" ></t:dgCol>
   <t:dgCol title="会议简介" field="introduction" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="meetingHistoryController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="meetingHistoryController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="meetingHistoryController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="meetingHistoryController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>