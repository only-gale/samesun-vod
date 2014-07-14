package vod.controller.recordrtspsrv;
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

import vod.entity.recordrtspsrv.ConfRecordRtspSrvEntity;
import vod.service.recordrtspsrv.ConfRecordRtspSrvServiceI;

/**   
 * @Title: Controller
 * @Description: 录制点播服务器关系
 * @author zhangdaihao
 * @date 2014-07-11 14:09:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/confRecordRtspSrvController")
public class ConfRecordRtspSrvController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConfRecordRtspSrvController.class);

	@Autowired
	private ConfRecordRtspSrvServiceI confRecordRtspSrvService;
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
	 * 录制点播服务器关系列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "confRecordRtspSrv")
	public ModelAndView confRecordRtspSrv(HttpServletRequest request) {
		return new ModelAndView("vod/recordrtspsrv/confRecordRtspSrvList");
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
	public void datagrid(ConfRecordRtspSrvEntity confRecordRtspSrv,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConfRecordRtspSrvEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, confRecordRtspSrv, request.getParameterMap());
		this.confRecordRtspSrvService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除录制点播服务器关系
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ConfRecordRtspSrvEntity confRecordRtspSrv, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		confRecordRtspSrv = systemService.getEntity(ConfRecordRtspSrvEntity.class, confRecordRtspSrv.getId());
		message = "录制点播服务器关系删除成功";
		confRecordRtspSrvService.delete(confRecordRtspSrv);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加录制点播服务器关系
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ConfRecordRtspSrvEntity confRecordRtspSrv, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(confRecordRtspSrv.getId())) {
			message = "录制点播服务器关系更新成功";
			ConfRecordRtspSrvEntity t = confRecordRtspSrvService.get(ConfRecordRtspSrvEntity.class, confRecordRtspSrv.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(confRecordRtspSrv, t);
				confRecordRtspSrvService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "录制点播服务器关系更新失败";
			}
		} else {
			message = "录制点播服务器关系添加成功";
			confRecordRtspSrvService.save(confRecordRtspSrv);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 录制点播服务器关系列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ConfRecordRtspSrvEntity confRecordRtspSrv, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(confRecordRtspSrv.getId())) {
			confRecordRtspSrv = confRecordRtspSrvService.getEntity(ConfRecordRtspSrvEntity.class, confRecordRtspSrv.getId());
			req.setAttribute("confRecordRtspSrvPage", confRecordRtspSrv);
		}
		return new ModelAndView("vod/recordrtspsrv/confRecordRtspSrv");
	}
}
