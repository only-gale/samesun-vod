package vod.controller.authoritygroup;
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

import vod.entity.authoritygroup.AuthorityGroupEntity;
import vod.service.authoritygroup.AuthorityGroupServiceI;

/**   
 * @Title: Controller
 * @Description: 终端分组
 * @author zhangdaihao
 * @date 2014-07-24 11:15:22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/authorityGroupController")
public class AuthorityGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AuthorityGroupController.class);

	@Autowired
	private AuthorityGroupServiceI authorityGroupService;
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
	 * 终端分组列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "authorityGroup")
	public ModelAndView authorityGroup(HttpServletRequest request) {
		return new ModelAndView("vod/authoritygroup/authorityGroupList");
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
	public void datagrid(AuthorityGroupEntity authorityGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AuthorityGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, authorityGroup, request.getParameterMap());
		this.authorityGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除终端分组
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AuthorityGroupEntity authorityGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		authorityGroup = systemService.getEntity(AuthorityGroupEntity.class, authorityGroup.getId());
		message = "终端分组删除成功";
		authorityGroupService.delete(authorityGroup);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加终端分组
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AuthorityGroupEntity authorityGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(authorityGroup.getId())) {
			message = "终端分组更新成功";
			AuthorityGroupEntity t = authorityGroupService.get(AuthorityGroupEntity.class, authorityGroup.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(authorityGroup, t);
				authorityGroupService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "终端分组更新失败";
			}
		} else {
			message = "终端分组添加成功";
			authorityGroupService.save(authorityGroup);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 终端分组列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AuthorityGroupEntity authorityGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(authorityGroup.getId())) {
			authorityGroup = authorityGroupService.getEntity(AuthorityGroupEntity.class, authorityGroup.getId());
			req.setAttribute("authorityGroupPage", authorityGroup);
		}
		return new ModelAndView("vod/authoritygroup/authorityGroup");
	}
}
