<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="vodSessionList" title="点播信息会话" actionUrl="vodSessionController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="会议ID" field="meetingid" ></t:dgCol>
   <t:dgCol title="会议类型" field="typeid" ></t:dgCol>
   <t:dgCol title="会议主题" field="subject" ></t:dgCol>
   <t:dgCol title="会议主持人" field="compere" ></t:dgCol>
   <t:dgCol title="会议简介" field="introduction" ></t:dgCol>
   <t:dgCol title="开始录制时间" field="begindt" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="结束录制时间" field="enddt" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="vodSessionController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="vodSessionController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="vodSessionController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="vodSessionController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>