<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>培训日志</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="trainingInfoHistoryController.do?save">
		<input id="id" name="id" type="hidden" value="${trainingInfoHistoryPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">VRS_ID号:</label>
		      <input class="inputxt" id="billid" name="billid" 
					   value="${trainingInfoHistoryPage.billid}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">设备名:</label>
		      <input class="inputxt" id="billname" name="billname" ignore="ignore"
					   value="${trainingInfoHistoryPage.billname}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="billstarttime" name="billstarttime" ignore="ignore"
					     value="<fmt:formatDate value='${trainingInfoHistoryPage.billstarttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">持续时长:</label>
		      <input class="inputxt" id="billduration" name="billduration" ignore="ignore"
					   value="${trainingInfoHistoryPage.billduration}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">Live类型:</label>
		      <input class="inputxt" id="isasflive" name="isasflive" 
					   value="${trainingInfoHistoryPage.isasflive}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">是否录音:</label>
		      <input class="inputxt" id="isrecord" name="isrecord" 
					   value="${trainingInfoHistoryPage.isrecord}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">培训状态:</label>
		      <input class="inputxt" id="meetingstate" name="meetingstate" 
					   value="${trainingInfoHistoryPage.meetingstate}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">培训有限状态机:</label>
		      <input class="inputxt" id="fsmstate" name="fsmstate" ignore="ignore"
					   value="${trainingInfoHistoryPage.fsmstate}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">预约录制时间:</label>
		      <input class="inputxt" id="appointmentdt" name="appointmentdt" ignore="ignore"
					   value="${trainingInfoHistoryPage.appointmentdt}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">预约状态:</label>
		      <input class="inputxt" id="appointmentstate" name="appointmentstate" ignore="ignore"
					   value="${trainingInfoHistoryPage.appointmentstate}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">资源URL:</label>
		      <input class="inputxt" id="asfurl" name="asfurl" ignore="ignore"
					   value="${trainingInfoHistoryPage.asfurl}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">培训名称:</label>
		      <input class="inputxt" id="name" name="name" ignore="ignore"
					   value="${trainingInfoHistoryPage.name}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">所属类型:</label>
		      <input class="inputxt" id="typeid" name="typeid" ignore="ignore"
					   value="${trainingInfoHistoryPage.typeid}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">终端分组:</label>
		      <input class="inputxt" id="rightid" name="rightid" ignore="ignore"
					   value="${trainingInfoHistoryPage.rightid}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">培训主题:</label>
		      <input class="inputxt" id="subject" name="subject" ignore="ignore"
					   value="${trainingInfoHistoryPage.subject}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">培训主讲人:</label>
		      <input class="inputxt" id="compere" name="compere" ignore="ignore"
					   value="${trainingInfoHistoryPage.compere}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">培训简介:</label>
		      <input class="inputxt" id="introduction" name="introduction" ignore="ignore"
					   value="${trainingInfoHistoryPage.introduction}">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>