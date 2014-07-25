package vod.controller.traininginfo;

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

import vod.entity.traininginfo.TrainingInfoEntity;
import vod.service.traininginfo.TrainingInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 培训信息
 * @author zhangdaihao
 * @date 2014-07-23 15:35:12
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/trainingInfoControllers")
public class CopyOfTrainingInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(CopyOfTrainingInfoController.class);

	@Autowired
	private TrainingInfoServiceI trainingInfoService;
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
	 * 培训信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "trainingInfo")
	public ModelAndView trainingInfo(HttpServletRequest request) {
		return new ModelAndView("vod/traininginfo/trainingInfoList");
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
	public void datagrid(TrainingInfoEntity trainingInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TrainingInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, trainingInfo, request.getParameterMap());
		this.trainingInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除培训信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TrainingInfoEntity trainingInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		trainingInfo = systemService.getEntity(TrainingInfoEntity.class, trainingInfo.getId());
		message = "培训信息删除成功";
		trainingInfoService.delete(trainingInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加培训信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TrainingInfoEntity trainingInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(trainingInfo.getId())) {
			message = "培训信息更新成功";
			TrainingInfoEntity t = trainingInfoService.get(TrainingInfoEntity.class, trainingInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(trainingInfo, t);
				trainingInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "培训信息更新失败";
			}
		} else {
			message = "培训信息添加成功";
			trainingInfoService.save(trainingInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 培训信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TrainingInfoEntity trainingInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(trainingInfo.getId())) {
			trainingInfo = trainingInfoService.getEntity(TrainingInfoEntity.class, trainingInfo.getId());
			req.setAttribute("trainingInfoPage", trainingInfo);
		}
		return new ModelAndView("vod/traininginfo/trainingInfo");
	}
}
