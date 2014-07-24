<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="authorityGroupList" title="终端分组" actionUrl="authorityGroupController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="自定义权限组名" field="name" ></t:dgCol>
   <t:dgCol title="描述" field="desc" ></t:dgCol>
   <t:dgCol title="权限类型" field="authtype" ></t:dgCol>
   <t:dgCol title="创建类型" field="creattype" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="authorityGroupController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="authorityGroupController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="authorityGroupController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="authorityGroupController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>