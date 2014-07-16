package vod.controller.authoritygroup;
import java.io.IOException;
import java.util.ArrayList;
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
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.alibaba.fastjson.JSON;

import vod.entity.authoritygroup.AuthorityGroupEntity;
import vod.samesun.util.ComboboxBean;
import vod.service.authoritygroup.AuthorityGroupServiceI;

/**   
 * @Title: Controller
 * @Description: 权限分组
 * @author zhangdaihao
 * @date 2014-06-19 10:54:05
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/authorityGroupController")
public class AuthorityGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
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
	 * 权限分组列表 页面跳转
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

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	public void datagrid(AuthorityGroupEntity authorityGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AuthorityGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, authorityGroup, request.getParameterMap());
		this.authorityGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除权限分组
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AuthorityGroupEntity authorityGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		authorityGroup = systemService.getEntity(AuthorityGroupEntity.class, authorityGroup.getId());
		message = "权限分组删除成功";
		authorityGroupService.delete(authorityGroup);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加权限分组
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AuthorityGroupEntity authorityGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(authorityGroup.getId())) {
			message = "权限分组更新成功";
			AuthorityGroupEntity t = authorityGroupService.get(AuthorityGroupEntity.class, authorityGroup.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(authorityGroup, t);
				authorityGroupService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "权限分组更新失败";
			}
		} else {
			message = "权限分组添加成功";
			authorityGroupService.save(authorityGroup);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 权限分组列表页面跳转
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
	
	/**
	 * 获得无分页的所有数据,用于填充下拉框
	 */
	@RequestMapping(params = "combox")
	public void combox(HttpServletRequest request, HttpServletResponse response, String excepts){
		try {
			List<AuthorityGroupEntity> list = authorityGroupService.loadAll(AuthorityGroupEntity.class);
			List<ComboboxBean> result = new ArrayList<ComboboxBean>();
			for(AuthorityGroupEntity c : list){
				ComboboxBean b = new ComboboxBean();
				b.setId(c.getId());
				b.setName(c.getName());
				result.add(b);
			}
			response.setContentType("text/html;charset=utf-8");
			String json = JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss");
			response.getWriter().write(json);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
