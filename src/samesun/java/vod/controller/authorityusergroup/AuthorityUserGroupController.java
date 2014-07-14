package vod.controller.authorityusergroup;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.alibaba.fastjson.JSON;

import vod.entity.authorityusergroup.AuthorityUserGroupEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.samesun.util.ComboboxBean;
import vod.service.authorityusergroup.AuthorityUserGroupServiceI;

/**   
 * @Title: Controller
 * @Description: 权限分组
 * @author zhangdaihao
 * @date 2014-06-19 10:54:43
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/authorityUserGroupController")
public class AuthorityUserGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AuthorityUserGroupController.class);

	@Autowired
	private AuthorityUserGroupServiceI authorityUserGroupService;
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
	@RequestMapping(params = "authorityUserGroup")
	public ModelAndView authorityUserGroup(HttpServletRequest request) {
		return new ModelAndView("vod/authorityusergroup/authorityUserGroupList");
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
	public void datagrid(AuthorityUserGroupEntity authorityUserGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AuthorityUserGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, authorityUserGroup, request.getParameterMap());
		this.authorityUserGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除权限分组
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AuthorityUserGroupEntity authorityUserGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		authorityUserGroup = systemService.getEntity(AuthorityUserGroupEntity.class, authorityUserGroup.getId());
		message = "权限分组删除成功";
		authorityUserGroupService.delete(authorityUserGroup);
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
	public AjaxJson save(AuthorityUserGroupEntity authorityUserGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(authorityUserGroup.getId())) {
			message = "权限分组更新成功";
			AuthorityUserGroupEntity t = authorityUserGroupService.get(AuthorityUserGroupEntity.class, authorityUserGroup.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(authorityUserGroup, t);
				authorityUserGroupService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "权限分组更新失败";
			}
		} else {
			message = "权限分组添加成功";
			authorityUserGroupService.save(authorityUserGroup);
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
	public ModelAndView addorupdate(AuthorityUserGroupEntity authorityUserGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(authorityUserGroup.getId())) {
			authorityUserGroup = authorityUserGroupService.getEntity(AuthorityUserGroupEntity.class, authorityUserGroup.getId());
			req.setAttribute("authorityUserGroupPage", authorityUserGroup);
		}
		return new ModelAndView("vod/authorityusergroup/authorityUserGroup");
	}
	
	/**
	 * 获得无分页的所有数据,用于填充下拉框
	 */
	@RequestMapping(params = "combox")
	public void combox(HttpServletRequest request, HttpServletResponse response, String excepts){
		try {
			List<AuthorityUserGroupEntity> list = authorityUserGroupService.loadAll(AuthorityUserGroupEntity.class);
			List<ComboboxBean> result = new ArrayList<ComboboxBean>();
			for(AuthorityUserGroupEntity c : list){
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
