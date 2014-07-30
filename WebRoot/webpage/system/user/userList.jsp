<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="userList" title="用户管理" actionUrl="userController.do?datagrid" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="用户名" sortable="false" field="userName" query="true" align="center" width="80"></t:dgCol>
	<t:dgCol title="真实姓名" field="realName" query="true" align="center" width="80"></t:dgCol>
	<t:dgCol title="组织机构ID" field="TSTerritory_id" hidden="false"></t:dgCol>
	<t:dgCol title="组织机构" field="TSTerritory_territoryName" query="true" align="center" width="120"></t:dgCol>
	<t:dgCol title="部门" field="TSDepart_id" query="true" replace="${departsReplace}" align="center" width="80"></t:dgCol>
	<t:dgCol title="电话" field="mobilePhone" align="center" width="60"></t:dgCol>
	<t:dgCol title="邮箱" field="email" align="center" width="80"></t:dgCol>
	<t:dgCol title="状态" sortable="true" field="status" replace="正常_1,禁用_0,超级管理员_-1" align="center" width="60"></t:dgCol>
	<t:dgCol title="操作" field="opt" align="center" width="50"></t:dgCol>
	<t:dgDelOpt title="删除" url="userController.do?del&id={id}&userName={userName}" />
	<t:dgToolBar title="用户录入" icon="icon-add" url="userController.do?addorupdate" funname="add"></t:dgToolBar>
	<t:dgToolBar title="用户编辑" icon="icon-edit" url="userController.do?addorupdate" funname="update"></t:dgToolBar>
</t:datagrid>
