package vod.controller.terminalinfo;
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

import vod.entity.terminalinfo.TerminalInfoEntity;
import vod.service.terminalinfo.TerminalInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 终端信息
 * @author zhangdaihao
 * @date 2014-07-11 18:52:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/terminalInfoController")
public class TerminalInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(TerminalInfoController.class);

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
	 * 终端信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "terminalInfo")
	public ModelAndView terminalInfo(HttpServletRequest request) {
		return new ModelAndView("vod/terminalinfo/terminalInfoList");
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
	public void datagrid(TerminalInfoEntity terminalInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TerminalInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, terminalInfo, request.getParameterMap());
		this.terminalInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除终端信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TerminalInfoEntity terminalInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		terminalInfo = systemService.getEntity(TerminalInfoEntity.class, terminalInfo.getId());
		message = "终端信息删除成功";
		terminalInfoService.delete(terminalInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加终端信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TerminalInfoEntity terminalInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(terminalInfo.getId())) {
			message = "终端信息更新成功";
			TerminalInfoEntity t = terminalInfoService.get(TerminalInfoEntity.class, terminalInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(terminalInfo, t);
				terminalInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "终端信息更新失败";
			}
		} else {
			message = "终端信息添加成功";
			terminalInfoService.save(terminalInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 终端信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TerminalInfoEntity terminalInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(terminalInfo.getId())) {
			terminalInfo = terminalInfoService.getEntity(TerminalInfoEntity.class, terminalInfo.getId());
			req.setAttribute("terminalInfoPage", terminalInfo);
		}
		return new ModelAndView("vod/terminalinfo/terminalInfo");
	}
}
