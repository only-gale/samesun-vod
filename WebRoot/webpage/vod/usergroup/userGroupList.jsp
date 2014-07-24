<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="userGroupList" title="用户分组" actionUrl="userGroupController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="自定义权限组名" field="name" align="center" query="true" ></t:dgCol>
   <t:dgCol title="描述" field="desc" align="center" query="true" ></t:dgCol>
   <t:dgCol title="权限类型" field="authtype" align="center" ></t:dgCol>
   <t:dgCol title="用户清单ID" field="userIDs" hidden="false" ></t:dgCol>
   <t:dgCol title="用户清单" field="userNames" ></t:dgCol>
   <t:dgCol title="创建类型" field="creattype" hidden="false" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="userGroupController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="userGroupController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="userGroupController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="userGroupController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>