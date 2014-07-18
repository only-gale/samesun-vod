package vod.controller.livesectionrecord;

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

import vod.entity.livesectionrecord.LiveSectionRecordEntity;

/**   
 * @Title: Controller
 * @Description: 直播会议录制分表
 * @author zhangdaihao
 * @date 2014-07-11 12:39:44
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/liveSectionRecordController")
public class LiveSectionRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LiveSectionRecordController.class);

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
	 * 直播会议录制分表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "liveSectionRecord")
	public ModelAndView liveSectionRecord(HttpServletRequest request) {
		return new ModelAndView("vod/livesectionrecord/liveSectionRecordList");
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
	public void datagrid(LiveSectionRecordEntity liveSectionRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LiveSectionRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, liveSectionRecord, request.getParameterMap());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除直播会议录制分表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LiveSectionRecordEntity liveSectionRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		liveSectionRecord = systemService.getEntity(LiveSectionRecordEntity.class, liveSectionRecord.getId());
		message = "直播会议录制分表删除成功";
		systemService.delete(liveSectionRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加直播会议录制分表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LiveSectionRecordEntity liveSectionRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(liveSectionRecord.getId())) {
			message = "直播会议录制分表更新成功";
			LiveSectionRecordEntity t = systemService.get(LiveSectionRecordEntity.class, liveSectionRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(liveSectionRecord, t);
				systemService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "直播会议录制分表更新失败";
			}
		} else {
			message = "直播会议录制分表添加成功";
			systemService.save(liveSectionRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 直播会议录制分表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LiveSectionRecordEntity liveSectionRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(liveSectionRecord.getId())) {
			liveSectionRecord = systemService.getEntity(LiveSectionRecordEntity.class, liveSectionRecord.getId());
			req.setAttribute("liveSectionRecordPage", liveSectionRecord);
		}
		return new ModelAndView("vod/livesectionrecord/liveSectionRecord");
	}
}
