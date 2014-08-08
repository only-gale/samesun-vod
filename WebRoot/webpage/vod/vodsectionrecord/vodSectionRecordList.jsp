<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="vodSectionRecordList" title="点播信息" actionUrl="vodSectionRecordController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="会议ID" field="meetingid" ></t:dgCol>
   <t:dgCol title="频道ID" field="channelid" ></t:dgCol>
   <t:dgCol title="SessionID号" field="sessionid" ></t:dgCol>
   <t:dgCol title="Codec主备标志" field="codecpriorityflg" ></t:dgCol>
   <t:dgCol title="Codec服务器ID号" field="codecsrvid" ></t:dgCol>
   <t:dgCol title="录播服务器ID" field="recordsrvid" ></t:dgCol>
   <t:dgCol title="Rtsp服务器ID" field="rtspsrvid" ></t:dgCol>
   <t:dgCol title="相对目录" field="rtsprelativedir" ></t:dgCol>
   <t:dgCol title="录制的文件名称" field="filename" ></t:dgCol>
   <t:dgCol title="自定义权限组ID" field="authortiyGroupCid" ></t:dgCol>
   <t:dgCol title="分配终端权限组（匿名类）" field="authortiyTerminlgroupCid" ></t:dgCol>
   <t:dgCol title="用户自定义权限组" field="authortiyUsergroupCid" ></t:dgCol>
   <t:dgCol title="录制状态" field="recState" ></t:dgCol>
   <t:dgCol title="录制备注" field="recMessage" ></t:dgCol>
   <t:dgCol title="起始时间" field="recStartDt" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="结束时间" field="recEndDt" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="持续时长" field="billduration" ></t:dgCol>
   <t:dgCol title="资源URL" field="asfurl" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <%-- <t:dgDelOpt title="删除" url="vodSectionRecordController.do?del&id={id}" /> --%>
   <t:dgToolBar title="录入" icon="icon-add" url="vodSectionRecordController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="vodSectionRecordController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="vodSectionRecordController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>