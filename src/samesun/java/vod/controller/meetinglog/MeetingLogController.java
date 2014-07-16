package vod.controller.meetinglog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import vod.entity.meetinglog.MeetingLogEntity;
import vod.service.meetinglog.MeetingLogServiceI;

/**   
 * @Title: Controller
 * @Description: 会议日志
 * @author zhangdaihao
 * @date 2014-06-17 16:38:41
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/meetingLogController")
public class MeetingLogController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(MeetingLogController.class);

	@Autowired
	private MeetingLogServiceI meetingLogService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 会议日志列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "meetingLog")
	public ModelAndView meetingLog(HttpServletRequest request) {
		return new ModelAndView("vod/meetinglog/meetingLogList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	public void datagrid(MeetingLogEntity meetingLog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MeetingLogEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, meetingLog, request.getParameterMap());
		this.meetingLogService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除会议日志
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(MeetingLogEntity meetingLog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		meetingLog = systemService.getEntity(MeetingLogEntity.class, meetingLog.getId());
		message = "会议日志删除成功";
		meetingLogService.delete(meetingLog);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加会议日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(MeetingLogEntity meetingLog, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(meetingLog.getId())) {
			message = "会议日志更新成功";
			MeetingLogEntity t = meetingLogService.get(MeetingLogEntity.class, meetingLog.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(meetingLog, t);
				meetingLogService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会议日志更新失败";
			}
		} else {
			message = "会议日志添加成功";
			meetingLogService.save(meetingLog);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 会议日志列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(MeetingLogEntity meetingLog, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(meetingLog.getId())) {
			meetingLog = meetingLogService.getEntity(MeetingLogEntity.class, meetingLog.getId());
			req.setAttribute("meetingLogPage", meetingLog);
		}
		return new ModelAndView("vod/meetinglog/meetingLog");
	}
}
