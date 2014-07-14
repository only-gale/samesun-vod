<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="confCodecInfoList" title="编码器配置信息" actionUrl="confCodecInfoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="Codec名称" field="name" ></t:dgCol>
   <t:dgCol title="地理位置" field="groupid" ></t:dgCol>
   <t:dgCol title="路数占用情况" field="resources" ></t:dgCol>
   <t:dgCol title="描述" field="descript" ></t:dgCol>
   <t:dgCol title="mac地址" field="macaddress" ></t:dgCol>
   <t:dgCol title="IP地址" field="ipaddress" ></t:dgCol>
   <t:dgCol title="直播URL" field="codecurl" ></t:dgCol>
   <t:dgCol title="是否启用" field="disable" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="confCodecInfoController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="confCodecInfoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="confCodecInfoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="confCodecInfoController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>