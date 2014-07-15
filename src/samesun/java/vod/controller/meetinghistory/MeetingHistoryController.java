package vod.controller.meetinghistory;
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

import vod.entity.meetinghistory.MeetingHistoryEntity;
import vod.service.meetinghistory.MeetingHistoryServiceI;

/**   
 * @Title: Controller
 * @Description: 历史会议
 * @author zhangdaihao
 * @date 2014-07-15 17:45:07
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/meetingHistoryController")
public class MeetingHistoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MeetingHistoryController.class);

	@Autowired
	private MeetingHistoryServiceI meetingHistoryService;
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
	 * 历史会议列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "meetingHistory")
	public ModelAndView meetingHistory(HttpServletRequest request) {
		return new ModelAndView("vod/meetinghistory/meetingHistoryList");
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
	public void datagrid(MeetingHistoryEntity meetingHistory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MeetingHistoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, meetingHistory, request.getParameterMap());
		this.meetingHistoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除历史会议
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(MeetingHistoryEntity meetingHistory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		meetingHistory = systemService.getEntity(MeetingHistoryEntity.class, meetingHistory.getId());
		message = "历史会议删除成功";
		meetingHistoryService.delete(meetingHistory);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加历史会议
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(MeetingHistoryEntity meetingHistory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(meetingHistory.getId())) {
			message = "历史会议更新成功";
			MeetingHistoryEntity t = meetingHistoryService.get(MeetingHistoryEntity.class, meetingHistory.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(meetingHistory, t);
				meetingHistoryService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "历史会议更新失败";
			}
		} else {
			message = "历史会议添加成功";
			meetingHistoryService.save(meetingHistory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 历史会议列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(MeetingHistoryEntity meetingHistory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(meetingHistory.getId())) {
			meetingHistory = meetingHistoryService.getEntity(MeetingHistoryEntity.class, meetingHistory.getId());
			req.setAttribute("meetingHistoryPage", meetingHistory);
		}
		return new ModelAndView("vod/meetinghistory/meetingHistory");
	}
}
