<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="confRecordSrvInfoList" title="录制服务器" actionUrl="confRecordSrvInfoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="录制服务器名称" field="name" width="70" align="center" ></t:dgCol>
   <t:dgCol title="描述" field="descript" width="150" align="center" ></t:dgCol>
   <t:dgCol title="mac地址" field="macaddress" width="70" align="center" ></t:dgCol>
   <t:dgCol title="IP地址" field="ipaddress" width="70" align="center" ></t:dgCol>
   <t:dgCol title="端口" field="port" width="40" align="center" ></t:dgCol>
   <t:dgCol title="相对目录" field="path" width="40" align="center" ></t:dgCol>
   <t:dgCol title="点播服务器" field="rr" align="center" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="50" align="center"></t:dgCol>
   <t:dgDelOpt title="删除" url="confRecordSrvInfoController.do?del&id={id}" exp="rr#eq#null"/>
   <t:dgToolBar title="录入" icon="icon-add" url="confRecordSrvInfoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="confRecordSrvInfoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="confRecordSrvInfoController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>