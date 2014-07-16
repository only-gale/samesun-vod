package vod.controller.confrecordsrvinfo;

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

import vod.entity.confrecordsrvinfo.ConfRecordSrvInfoEntity;
import vod.service.confrecordsrvinfo.ConfRecordSrvInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 录制服务器
 * @author zhangdaihao
 * @date 2014-07-11 14:06:46
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/confRecordSrvInfoController")
public class ConfRecordSrvInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ConfRecordSrvInfoController.class);

	@Autowired
	private ConfRecordSrvInfoServiceI confRecordSrvInfoService;
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
	 * 录制服务器列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "confRecordSrvInfo")
	public ModelAndView confRecordSrvInfo(HttpServletRequest request) {
		return new ModelAndView("vod/confrecordsrvinfo/confRecordSrvInfoList");
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
	public void datagrid(ConfRecordSrvInfoEntity confRecordSrvInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConfRecordSrvInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, confRecordSrvInfo, request.getParameterMap());
		this.confRecordSrvInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除录制服务器
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ConfRecordSrvInfoEntity confRecordSrvInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		confRecordSrvInfo = systemService.getEntity(ConfRecordSrvInfoEntity.class, confRecordSrvInfo.getId());
		message = "录制服务器删除成功";
		confRecordSrvInfoService.delete(confRecordSrvInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加录制服务器
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ConfRecordSrvInfoEntity confRecordSrvInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(confRecordSrvInfo.getId())) {
			message = "录制服务器更新成功";
			ConfRecordSrvInfoEntity t = confRecordSrvInfoService.get(ConfRecordSrvInfoEntity.class, confRecordSrvInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(confRecordSrvInfo, t);
				confRecordSrvInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "录制服务器更新失败";
			}
		} else {
			message = "录制服务器添加成功";
			confRecordSrvInfoService.save(confRecordSrvInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 录制服务器列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ConfRecordSrvInfoEntity confRecordSrvInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(confRecordSrvInfo.getId())) {
			confRecordSrvInfo = confRecordSrvInfoService.getEntity(ConfRecordSrvInfoEntity.class, confRecordSrvInfo.getId());
			req.setAttribute("confRecordSrvInfoPage", confRecordSrvInfo);
		}
		return new ModelAndView("vod/confrecordsrvinfo/confRecordSrvInfo");
	}
}
