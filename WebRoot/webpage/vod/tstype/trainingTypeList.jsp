<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="trainingTypeList" title="数据字典" actionUrl="tsTypeController.do?datagrid&groupCode=${groupCode }" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="类型编码" field="typecode" align="center" width="80" hidden="false" ></t:dgCol>
   <t:dgCol title="类型名称" field="typename" align="center" width="80" ></t:dgCol>
   <t:dgCol title="typepid" field="typepid" hidden="false" ></t:dgCol>
   <t:dgCol title="typegroupid" field="typegroupid" hidden="false" ></t:dgCol>
   <%-- <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tsTypeController.do?del&id={id}" /> --%>
   <t:dgToolBar title="录入" icon="icon-add" url="tsTypeController.do?addorupdate&gcode=${groupCode }" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tsTypeController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>