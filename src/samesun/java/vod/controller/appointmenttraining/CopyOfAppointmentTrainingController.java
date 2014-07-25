package vod.controller.appointmenttraining;

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

import vod.entity.appointmenttraining.AppointmentTrainingEntity;
import vod.service.appointmenttraining.AppointmentTrainingServiceI;

/**   
 * @Title: Controller
 * @Description: 预约培训
 * @author zhangdaihao
 * @date 2014-07-23 15:34:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/appointmentTrainingControllers")
public class CopyOfAppointmentTrainingController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(CopyOfAppointmentTrainingController.class);

	@Autowired
	private AppointmentTrainingServiceI appointmentTrainingService;
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
	 * 预约培训列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "appointmentTraining")
	public ModelAndView appointmentTraining(HttpServletRequest request) {
		return new ModelAndView("vod/appointmenttraining/appointmentTrainingList");
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
	public void datagrid(AppointmentTrainingEntity appointmentTraining,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AppointmentTrainingEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, appointmentTraining, request.getParameterMap());
		this.appointmentTrainingService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除预约培训
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AppointmentTrainingEntity appointmentTraining, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		appointmentTraining = systemService.getEntity(AppointmentTrainingEntity.class, appointmentTraining.getId());
		message = "预约培训删除成功";
		appointmentTrainingService.delete(appointmentTraining);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加预约培训
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AppointmentTrainingEntity appointmentTraining, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(appointmentTraining.getId())) {
			message = "预约培训更新成功";
			AppointmentTrainingEntity t = appointmentTrainingService.get(AppointmentTrainingEntity.class, appointmentTraining.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(appointmentTraining, t);
				appointmentTrainingService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "预约培训更新失败";
			}
		} else {
			message = "预约培训添加成功";
			appointmentTrainingService.save(appointmentTraining);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 预约培训列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AppointmentTrainingEntity appointmentTraining, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(appointmentTraining.getId())) {
			appointmentTraining = appointmentTrainingService.getEntity(AppointmentTrainingEntity.class, appointmentTraining.getId());
			req.setAttribute("appointmentTrainingPage", appointmentTraining);
		}
		return new ModelAndView("vod/appointmenttraining/appointmentTraining");
	}
}
