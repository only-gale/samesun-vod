package vod.controller.meetinglivesession;
import java.util.List;

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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import vod.entity.meetinglivesession.MeetingLiveSessionEntity;
import vod.service.meetinglivesession.MeetingLiveSessionServiceI;

/**   
 * @Title: Controller
 * @Description: 直播会议分表
 * @author zhangdaihao
 * @date 2014-07-11 12:37:42
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/meetingLiveSessionController")
public class MeetingLiveSessionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MeetingLiveSessionController.class);

	@Autowired
	private MeetingLiveSessionServiceI meetingLiveSessionService;
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
	 * 直播会议分表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "meetingLiveSession")
	public ModelAndView meetingLiveSession(HttpServletRequest request) {
		return new ModelAndView("vod/meetinglivesession/meetingLiveSessionList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(MeetingLiveSessionEntity meetingLiveSession,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MeetingLiveSessionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, meetingLiveSession, request.getParameterMap());
		this.meetingLiveSessionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除直播会议分表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(MeetingLiveSessionEntity meetingLiveSession, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		meetingLiveSession = systemService.getEntity(MeetingLiveSessionEntity.class, meetingLiveSession.getId());
		message = "直播会议分表删除成功";
		meetingLiveSessionService.delete(meetingLiveSession);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加直播会议分表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(MeetingLiveSessionEntity meetingLiveSession, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(meetingLiveSession.getId())) {
			message = "直播会议分表更新成功";
			MeetingLiveSessionEntity t = meetingLiveSessionService.get(MeetingLiveSessionEntity.class, meetingLiveSession.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(meetingLiveSession, t);
				meetingLiveSessionService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "直播会议分表更新失败";
			}
		} else {
			message = "直播会议分表添加成功";
			meetingLiveSessionService.save(meetingLiveSession);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 直播会议分表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(MeetingLiveSessionEntity meetingLiveSession, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(meetingLiveSession.getId())) {
			meetingLiveSession = meetingLiveSessionService.getEntity(MeetingLiveSessionEntity.class, meetingLiveSession.getId());
			req.setAttribute("meetingLiveSessionPage", meetingLiveSession);
		}
		return new ModelAndView("vod/meetinglivesession/meetingLiveSession");
	}
}
