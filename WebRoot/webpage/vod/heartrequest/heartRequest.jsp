<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>心跳信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="heartRequestController.do?save">
		<input id="id" name="id" type="hidden" value="${heartRequestPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">mac地址:</label>
		      <input class="inputxt" id="macaddress" name="macaddress" 
					   value="${heartRequestPage.macaddress}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">IP地址:</label>
		      <input class="inputxt" id="ipaddress" name="ipaddress" 
					   value="${heartRequestPage.ipaddress}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">请求时间 :</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="requestDt" name="requestDt" ignore="ignore"
					     value="<fmt:formatDate value='${heartRequestPage.requestDt}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">处理状态:</label>
		      <input class="inputxt" id="state" name="state" 
					   value="${heartRequestPage.state}" datatype="*">
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>