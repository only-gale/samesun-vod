package vod.controller.traininginfohistory;

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

import vod.entity.traininginfohistory.TrainingInfoHistoryEntity;
import vod.service.traininginfohistory.TrainingInfoHistoryServiceI;

/**   
 * @Title: Controller
 * @Description: 培训日志
 * @author zhangdaihao
 * @date 2014-07-23 10:45:57
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/trainingInfoHistoryController")
public class TrainingInfoHistoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(TrainingInfoHistoryController.class);

	@Autowired
	private TrainingInfoHistoryServiceI trainingInfoHistoryService;
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
	 * 培训日志列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "trainingInfoHistory")
	public ModelAndView trainingInfoHistory(HttpServletRequest request) {
		return new ModelAndView("vod/traininginfohistory/trainingInfoHistoryList");
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
	public void datagrid(TrainingInfoHistoryEntity trainingInfoHistory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TrainingInfoHistoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, trainingInfoHistory, request.getParameterMap());
		this.trainingInfoHistoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除培训日志
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TrainingInfoHistoryEntity trainingInfoHistory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		trainingInfoHistory = systemService.getEntity(TrainingInfoHistoryEntity.class, trainingInfoHistory.getId());
		message = "培训日志删除成功";
		trainingInfoHistoryService.delete(trainingInfoHistory);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加培训日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TrainingInfoHistoryEntity trainingInfoHistory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(trainingInfoHistory.getId())) {
			message = "培训日志更新成功";
			TrainingInfoHistoryEntity t = trainingInfoHistoryService.get(TrainingInfoHistoryEntity.class, trainingInfoHistory.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(trainingInfoHistory, t);
				trainingInfoHistoryService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "培训日志更新失败";
			}
		} else {
			message = "培训日志添加成功";
			trainingInfoHistoryService.save(trainingInfoHistory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 培训日志列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TrainingInfoHistoryEntity trainingInfoHistory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(trainingInfoHistory.getId())) {
			trainingInfoHistory = trainingInfoHistoryService.getEntity(TrainingInfoHistoryEntity.class, trainingInfoHistory.getId());
			req.setAttribute("trainingInfoHistoryPage", trainingInfoHistory);
		}
		return new ModelAndView("vod/traininginfohistory/trainingInfoHistory");
	}
}
