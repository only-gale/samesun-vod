<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="heartRequestList" title="心跳信息" actionUrl="heartRequestController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="mac地址" field="macaddress" align="center" width="120" ></t:dgCol>
   <t:dgCol title="IP地址" field="ipaddress" align="center" width="120" ></t:dgCol>
   <t:dgCol title="请求时间 " field="requestDt" formatter="yyyy-MM-dd hh:mm:ss" align="center" width="150"></t:dgCol>
   <t:dgCol title="处理状态" field="state" hidden="false" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100" align="center"></t:dgCol>
   <t:dgDelOpt title="删除" url="heartRequestController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="heartRequestController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="heartRequestController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="heartRequestController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>