package vod.controller.confrtspsrvinfo;

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

import vod.entity.confrtspsrvinfo.ConfRtspSrvInfoEntity;
import vod.service.confrtspsrvinfo.ConfRtspSrvInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 点播服务器
 * @author zhangdaihao
 * @date 2014-07-11 14:07:51
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/confRtspSrvInfoController")
public class ConfRtspSrvInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ConfRtspSrvInfoController.class);

	@Autowired
	private ConfRtspSrvInfoServiceI confRtspSrvInfoService;
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
	 * 点播服务器列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "confRtspSrvInfo")
	public ModelAndView confRtspSrvInfo(HttpServletRequest request) {
		return new ModelAndView("vod/confrtspsrvinfo/confRtspSrvInfoList");
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
	public void datagrid(ConfRtspSrvInfoEntity confRtspSrvInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConfRtspSrvInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, confRtspSrvInfo, request.getParameterMap());
		this.confRtspSrvInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除点播服务器
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ConfRtspSrvInfoEntity confRtspSrvInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		confRtspSrvInfo = systemService.getEntity(ConfRtspSrvInfoEntity.class, confRtspSrvInfo.getId());
		message = "点播服务器删除成功";
		confRtspSrvInfoService.delete(confRtspSrvInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加点播服务器
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ConfRtspSrvInfoEntity confRtspSrvInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(confRtspSrvInfo.getId())) {
			message = "点播服务器更新成功";
			ConfRtspSrvInfoEntity t = confRtspSrvInfoService.get(ConfRtspSrvInfoEntity.class, confRtspSrvInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(confRtspSrvInfo, t);
				confRtspSrvInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "点播服务器更新失败";
			}
		} else {
			message = "点播服务器添加成功";
			confRtspSrvInfoService.save(confRtspSrvInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 点播服务器列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ConfRtspSrvInfoEntity confRtspSrvInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(confRtspSrvInfo.getId())) {
			confRtspSrvInfo = confRtspSrvInfoService.getEntity(ConfRtspSrvInfoEntity.class, confRtspSrvInfo.getId());
			req.setAttribute("confRtspSrvInfoPage", confRtspSrvInfo);
		}
		return new ModelAndView("vod/confrtspsrvinfo/confRtspSrvInfo");
	}
}
