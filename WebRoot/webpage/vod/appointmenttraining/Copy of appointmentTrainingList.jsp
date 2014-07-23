<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="appointmentTrainingList" title="预约培训" actionUrl="appointmentTrainingController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="预约时间" field="appointmentStarttime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="预约持续时长" field="appointmentDuration" ></t:dgCol>
   <t:dgCol title="预约状态" field="appointmentState" ></t:dgCol>
   <t:dgCol title="所属类型" field="typeid" ></t:dgCol>
   <t:dgCol title="培训主题" field="subject" ></t:dgCol>
   <t:dgCol title="培训主讲人" field="compere" ></t:dgCol>
   <t:dgCol title="培训简介" field="introduction" ></t:dgCol>
   <t:dgCol title="liveTime" field="liveTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="delayTime" field="delayTime" ></t:dgCol>
   <t:dgCol title="isrecord" field="isrecord" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="appointmentTrainingController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="appointmentTrainingController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="appointmentTrainingController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="appointmentTrainingController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>