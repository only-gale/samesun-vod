package vod.controller.vodsession;
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

import vod.entity.vodsession.VodSessionEntity;
import vod.service.vodsession.VodSessionServiceI;

/**   
 * @Title: Controller
 * @Description: 点播信息会话
 * @author zhangdaihao
 * @date 2014-07-15 17:46:55
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/vodSessionController")
public class VodSessionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(VodSessionController.class);

	@Autowired
	private VodSessionServiceI vodSessionService;
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
	 * 点播信息会话列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "vodSession")
	public ModelAndView vodSession(HttpServletRequest request) {
		return new ModelAndView("vod/vodsession/vodSessionList");
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
	public void datagrid(VodSessionEntity vodSession,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VodSessionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, vodSession, request.getParameterMap());
		this.vodSessionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除点播信息会话
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(VodSessionEntity vodSession, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		vodSession = systemService.getEntity(VodSessionEntity.class, vodSession.getId());
		message = "点播信息会话删除成功";
		vodSessionService.delete(vodSession);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加点播信息会话
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(VodSessionEntity vodSession, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(vodSession.getId())) {
			message = "点播信息会话更新成功";
			VodSessionEntity t = vodSessionService.get(VodSessionEntity.class, vodSession.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(vodSession, t);
				vodSessionService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "点播信息会话更新失败";
			}
		} else {
			message = "点播信息会话添加成功";
			vodSessionService.save(vodSession);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 点播信息会话列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(VodSessionEntity vodSession, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(vodSession.getId())) {
			vodSession = vodSessionService.getEntity(VodSessionEntity.class, vodSession.getId());
			req.setAttribute("vodSessionPage", vodSession);
		}
		return new ModelAndView("vod/vodsession/vodSession");
	}
}
