<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="appointmentChannelInfoList" title="预约频道信息" actionUrl="appointmentChannelInfoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="频道名" field="channelname" ></t:dgCol>
   <t:dgCol title="会议ID" field="meetingid" ></t:dgCol>
   <t:dgCol title="主Codec1" field="codec1id" ></t:dgCol>
   <t:dgCol title="Codec1是否录制" field="isrecord1" ></t:dgCol>
   <t:dgCol title="备Codec2" field="codec2id" ></t:dgCol>
   <t:dgCol title="Codec2是否录制" field="isrecord2" ></t:dgCol>
   <t:dgCol title="自定义权限组ID" field="authortiyGroupCid" ></t:dgCol>
   <t:dgCol title="类型" field="authortiyGroupType" ></t:dgCol>
   <t:dgCol title="分配终端权限组（匿名类）" field="authortiyTerminlgroupCid" ></t:dgCol>
   <t:dgCol title="用户自定义权限组" field="authortiyUsergroupCid" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="appointmentChannelInfoController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="appointmentChannelInfoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="appointmentChannelInfoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="appointmentChannelInfoController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>