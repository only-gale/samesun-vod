<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="meetingHistoryList" title="历史会议" actionUrl="meetingHistoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="VRS_ID号" field="billid" hidden="false" ></t:dgCol>
   <t:dgCol title="设备名" field="billname" hidden="false" ></t:dgCol>
   <t:dgCol title="时间" field="billstarttime" formatter="yyyy-MM-dd hh:mm:ss" align="center" query="true" queryMode="group"></t:dgCol>
   <t:dgCol title="持续时长(分钟)" field="billduration" align="center" ></t:dgCol>
   <t:dgCol title="Live类型" field="isasflive" align="center" hidden="false" ></t:dgCol>
   <t:dgCol title="是否录制" field="isrecord" align="center" replace="否_0,是_1" ></t:dgCol>
   <t:dgCol title="会议状态" field="meetingstate" align="center" hidden="false" ></t:dgCol>
   <t:dgCol title="会议有限状态机" field="fsmstate" hidden="false" ></t:dgCol>
   <t:dgCol title="预约录制时间" field="appointmentdt" hidden="false" ></t:dgCol>
   <t:dgCol title="预约状态" field="appointmentstate"  hidden="false"></t:dgCol>
   <t:dgCol title="资源URL" field="asfurl" align="center" hidden="false" ></t:dgCol>
   <t:dgCol title="会议名称" field="name" hidden="false" ></t:dgCol>
   <t:dgCol title="所属类型" field="typeid" align="center" replace="公共类_1,专题类_2,讨论类_3" hidden="false" ></t:dgCol>
   <t:dgCol title="所属类型" field="typename" align="center" ></t:dgCol>
   <t:dgCol title="终端分组" field="rightid" align="center" hidden="false" ></t:dgCol>
   <t:dgCol title="主题" field="subject" align="center" query="true" width="100" ></t:dgCol>
   <t:dgCol title="主持人" field="compere" align="center" width="50" ></t:dgCol>
   <t:dgCol title="简介" field="introduction" align="center" width="150" ></t:dgCol>
   <t:dgToolBar title="导出Excel" icon="icon-search" onclick="courseListExportXls();"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
	$(document).ready(function(){
		$("input[name='billstarttime_begin']").attr("class","easyui-datebox");
		$("input[name='billstarttime_end']").attr("class","easyui-datebox");
	});
	
	/*
	 *	excel导出
	 */
	function courseListExportXls() {
		JeecgExcelExport("meetingHistoryController.do?exportXls","meetingHistoryList");
	}
</script>