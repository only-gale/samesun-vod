package vod.controller.confcodecrecordsrv;

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

import vod.entity.confcodecrecordsrv.ConfCodecRecordSrvEntity;
import vod.service.confcodecrecordsrv.ConfCodecRecordSrvServiceI;

/**   
 * @Title: Controller
 * @Description: 编码器录制服务器关系
 * @author zhangdaihao
 * @date 2014-07-11 14:04:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/confCodecRecordSrvController")
public class ConfCodecRecordSrvController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ConfCodecRecordSrvController.class);

	@Autowired
	private ConfCodecRecordSrvServiceI confCodecRecordSrvService;
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
	 * 编码器录制服务器关系列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "confCodecRecordSrv")
	public ModelAndView confCodecRecordSrv(HttpServletRequest request) {
		return new ModelAndView("vod/confcodecrecordsrv/confCodecRecordSrvList");
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
	public void datagrid(ConfCodecRecordSrvEntity confCodecRecordSrv,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConfCodecRecordSrvEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, confCodecRecordSrv, request.getParameterMap());
		this.confCodecRecordSrvService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除编码器录制服务器关系
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ConfCodecRecordSrvEntity confCodecRecordSrv, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		confCodecRecordSrv = systemService.getEntity(ConfCodecRecordSrvEntity.class, confCodecRecordSrv.getId());
		message = "编码器录制服务器关系删除成功";
		confCodecRecordSrvService.delete(confCodecRecordSrv);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加编码器录制服务器关系
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ConfCodecRecordSrvEntity confCodecRecordSrv, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(confCodecRecordSrv.getId())) {
			message = "编码器录制服务器关系更新成功";
			ConfCodecRecordSrvEntity t = confCodecRecordSrvService.get(ConfCodecRecordSrvEntity.class, confCodecRecordSrv.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(confCodecRecordSrv, t);
				confCodecRecordSrvService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "编码器录制服务器关系更新失败";
			}
		} else {
			message = "编码器录制服务器关系添加成功";
			confCodecRecordSrvService.save(confCodecRecordSrv);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 编码器录制服务器关系列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ConfCodecRecordSrvEntity confCodecRecordSrv, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(confCodecRecordSrv.getId())) {
			confCodecRecordSrv = confCodecRecordSrvService.getEntity(ConfCodecRecordSrvEntity.class, confCodecRecordSrv.getId());
			req.setAttribute("confCodecRecordSrvPage", confCodecRecordSrv);
		}
		return new ModelAndView("vod/confcodecrecordsrv/confCodecRecordSrv");
	}
}
