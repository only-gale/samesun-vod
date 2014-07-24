<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="authorityUserGroupList" title="权限分组" actionUrl="authorityUserGroupController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="自定义权限组名" field="name" ></t:dgCol>
   <t:dgCol title="描述" field="desc" width="150" ></t:dgCol>
   <t:dgCol title="权限类型" field="authtype" hidden="false" ></t:dgCol>
   <t:dgCol title="创建类型" field="creattype" hidden="false" ></t:dgCol>
   <t:dgCol title="用户清单ID" field="userIDs" hidden="false" ></t:dgCol>
   <t:dgCol title="用户清单" field="userNames" width="300" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="50"></t:dgCol>
   <t:dgDelOpt title="删除" url="authorityUserGroupController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="authorityUserGroupController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="authorityUserGroupController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="authorityUserGroupController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>