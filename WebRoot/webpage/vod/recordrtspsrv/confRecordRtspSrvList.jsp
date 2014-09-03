<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="confRecordRtspSrvList" title="录制点播服务器关系" actionUrl="confRecordRtspSrvController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="录播服务器主键" field="recordsrvid" ></t:dgCol>
   <t:dgCol title="RTSP服务器主键" field="rtspsrvid" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="confRecordRtspSrvController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="confRecordRtspSrvController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="confRecordRtspSrvController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="confRecordRtspSrvController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>