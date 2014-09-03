<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="confRtspSrvInfoList" title="点播服务器" actionUrl="confRtspSrvInfoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="RTSP服务器名称" field="name" width="80" align="center" ></t:dgCol>
   <t:dgCol title="描述" field="descript" width="150" align="center" ></t:dgCol>
   <t:dgCol title="mac地址" field="macaddress" width="80" align="center" ></t:dgCol>
   <t:dgCol title="IP地址" field="ipaddress" width="80" align="center" ></t:dgCol>
   <t:dgCol title=" Url地址" field="rtspurl" width="80" align="center" ></t:dgCol>
   <t:dgCol title="是否启用" field="disable" width="50" align="center" replace="未启用_0,已启用_1" hidden="false"></t:dgCol>
   <t:dgCol title="操作" width="50" field="opt" align="center"></t:dgCol>
   <t:dgDelOpt title="删除" url="confRtspSrvInfoController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="confRtspSrvInfoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="confRtspSrvInfoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="confRtspSrvInfoController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>