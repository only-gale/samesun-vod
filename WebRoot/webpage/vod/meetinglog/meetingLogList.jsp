<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="meetingLogList" title="会议日志" actionUrl="meetingLogController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="终端ID" field="edgeid" hidden="false" ></t:dgCol>
   <t:dgCol title="终端名称" field="edgename" align="center" width="100" query="true" ></t:dgCol>
   <t:dgCol title="终端MAC" field="edgemac" hidden="false"></t:dgCol>
   <t:dgCol title="当前状态" field="state" align="center" hidden="false" ></t:dgCol>
   <t:dgCol title="会议" field="meetingid" align="center" width="150" hidden="false"></t:dgCol>
   <t:dgCol title="是否是直播" field="isliveflag" align="center" replace="点播_2,直播_1" ></t:dgCol>
   <t:dgCol title="会议主题" field="subject" align="center" width="200" query="true"></t:dgCol>
   <t:dgCol title="日期" field="date" formatter="yyyy-MM-dd hh:mm:ss" width="120" align="center" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="观看时间" field="times" align="center" hidden="false" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100" align="center"></t:dgCol>
   <t:dgDelOpt title="删除" url="meetingLogController.do?del&id={id}" />
   <%-- <t:dgToolBar title="录入" icon="icon-add" url="meetingLogController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="meetingLogController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="meetingLogController.do?addorupdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>