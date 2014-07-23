package vod.controller.heartrequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.heartrequest.HeartRequestEntity;
import vod.entity.terminalinfo.TerminalInfoEntity;
import vod.service.heartrequest.HeartRequestServiceI;
import vod.service.terminalinfo.TerminalInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 心跳信息
 * @author zhangdaihao
 * @date 2014-07-21 13:06:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/heartRequestController")
public class HeartRequestController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(HeartRequestController.class);

	@Autowired
	private HeartRequestServiceI heartRequestService;
	@Autowired
	private TerminalInfoServiceI terminalInfoService;
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
	 * 心跳信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "heartRequest")
	public ModelAndView heartRequest(HttpServletRequest request) {
		return new ModelAndView("vod/heartrequest/heartRequestList");
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
	public void datagrid(HeartRequestEntity heartRequest,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HeartRequestEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, heartRequest, request.getParameterMap());
		this.heartRequestService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除心跳信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(HeartRequestEntity heartRequest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		heartRequest = systemService.getEntity(HeartRequestEntity.class, heartRequest.getId());
		message = "心跳信息删除成功";
		heartRequestService.delete(heartRequest);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加心跳信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HeartRequestEntity heartRequest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(heartRequest.getId())) {
			message = "心跳信息更新成功";
			HeartRequestEntity t = heartRequestService.get(HeartRequestEntity.class, heartRequest.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(heartRequest, t);
				heartRequestService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "心跳信息更新失败";
			}
		} else {
			message = "心跳信息添加成功";
			heartRequestService.save(heartRequest);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 心跳信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(HeartRequestEntity heartRequest, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(heartRequest.getId())) {
			heartRequest = heartRequestService.getEntity(HeartRequestEntity.class, heartRequest.getId());
			req.setAttribute("heartRequestPage", heartRequest);
		}
		return new ModelAndView("vod/heartrequest/heartRequest");
	}
	
	/**
	 * 心跳信息注册页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "toRegiste")
	public ModelAndView toRegiste(TerminalInfoEntity terminal, HttpServletRequest req) {
		req.setAttribute("terminalInfoPage", terminal);
		return new ModelAndView("vod/heartrequest/heartRequest");
	}
	
	/**
	 * 注册
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "registe")
	@ResponseBody
	public AjaxJson registe(TerminalInfoEntity terminalInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		message = "注册成功";
		String heartRequestId = terminalInfo.getId();
		terminalInfo.setId(null);
		terminalInfoService.save(terminalInfo);
		//注册成功后删除异常心跳信息
		heartRequestService.delete(heartRequestService.get(HeartRequestEntity.class, heartRequestId));
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
}
