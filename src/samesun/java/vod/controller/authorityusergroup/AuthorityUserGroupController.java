package vod.controller.authorityusergroup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.web.system.pojo.base.TSTerritory;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.authgroupuser.AuthGroupUserEntity;
import vod.entity.authorityusergroup.AuthorityUserGroupEntity;
import vod.page.authorityusergroup.AuthorityUserGroupPage;
import vod.samesun.util.ComboboxBean;
import vod.service.authorityusergroup.AuthorityUserGroupServiceI;

import com.alibaba.fastjson.JSON;

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
	@SuppressWarnings("unused")
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
	 * @throws Exception 
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	public void datagrid(AuthorityUserGroupEntity authorityUserGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws Exception {
		//如果有查询条件，则设置为模糊查询
		String name = authorityUserGroup.getName(), des = authorityUserGroup.getDesc();
		if(StringUtil.isNotEmpty(name) || StringUtil.isNotEmpty(des)){
			authorityUserGroup.setName("*" + name + "*");
			authorityUserGroup.setDesc("*" + des + "*");
		}
		CriteriaQuery cq = new CriteriaQuery(AuthorityUserGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, authorityUserGroup, request.getParameterMap());
		this.authorityUserGroupService.getDataGridReturn(cq, true);
		List<AuthorityUserGroupEntity> entyties = dataGrid.getResults();
		if(null != entyties && entyties.size() > 0){
			List<AuthorityUserGroupPage> pages = new ArrayList<AuthorityUserGroupPage>();
			for(AuthorityUserGroupEntity e : entyties){
				AuthorityUserGroupPage page = new AuthorityUserGroupPage();
				MyBeanUtils.copyBeanNotNull2Bean(e, page);
				String ids="", names="";
				List<AuthGroupUserEntity> users = systemService.findByProperty(AuthGroupUserEntity.class, "authid", e.getId());
				for(AuthGroupUserEntity u : users){
					ids += ("," + u.getUserid());
					TSUser user = systemService.get(TSUser.class, u.getUserid());
					if(null != user){
						names += ("、" + user.getRealName());
					}
				}
				page.setUserIDs(ids.length() > 1 ? ids.substring(1) : ids);
				page.setUserNames(names.length() > 1 ? names.substring(1) : names);
				pages.add(page);
			}
			dataGrid.setResults(pages);
		}
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
		systemService.deleteAllEntitie(systemService.findByProperty(AuthGroupUserEntity.class, "authid", authorityUserGroup.getId()));
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
	public AjaxJson save(AuthorityUserGroupPage authorityUserGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(authorityUserGroup.getId())) {
			message = "权限分组更新成功";
			AuthorityUserGroupEntity t = authorityUserGroupService.get(AuthorityUserGroupEntity.class, authorityUserGroup.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(authorityUserGroup, t);
				authorityUserGroupService.saveOrUpdate(t);
				//删除已有用户与分组的关联关系
				systemService.deleteAllEntitie(systemService.findByProperty(AuthGroupUserEntity.class, "authid", t.getId()));
				//维护新的用户与分组的关联关系
				for(String id : authorityUserGroup.getUserIDs().split(",")){
					if(StringUtil.isNotEmpty(id)){
						AuthGroupUserEntity gu = new AuthGroupUserEntity();
						gu.setAuthid(t.getId());
						gu.setUserid(id);
						systemService.save(gu);
					}
				}
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "权限分组更新失败";
			}
		} else {
			message = "权限分组添加成功";
			AuthorityUserGroupEntity entity = new AuthorityUserGroupEntity();
			try {
				entity.setName(authorityUserGroup.getName());
				entity.setDesc(authorityUserGroup.getDesc());
				authorityUserGroupService.save(entity);
				//维护用户与分组的关联关系
				for(String id : authorityUserGroup.getUserIDs().split(",")){
					if(StringUtil.isNotEmpty(id)){
						AuthGroupUserEntity gu = new AuthGroupUserEntity();
						gu.setAuthid(entity.getId());
						gu.setUserid(id);
						systemService.save(gu);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = "权限分组更新失败";
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 权限分组列表页面跳转
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AuthorityUserGroupEntity authorityUserGroup, HttpServletRequest req) throws Exception {
		if (StringUtil.isNotEmpty(authorityUserGroup.getId())) {
			authorityUserGroup = authorityUserGroupService.getEntity(AuthorityUserGroupEntity.class, authorityUserGroup.getId());
			AuthorityUserGroupPage page = new AuthorityUserGroupPage();
			MyBeanUtils.copyBeanNotNull2Bean(authorityUserGroup, page);
			List<AuthGroupUserEntity> users = systemService.findByProperty(AuthGroupUserEntity.class, "authid", page.getId());
			String ids = "";
			for(AuthGroupUserEntity u : users){
				ids += ("," + u.getUserid());
			}
			page.setUserIDs(ids.substring(1));
			
			req.setAttribute("authorityUserGroupPage", page);
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
	
	@RequestMapping(params = "getChildren")
	@ResponseBody
	public List<ComboTree> getChildren(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSTerritory.class);
		if (comboTree.getId() != null) {
			cq.eq("TSTerritory.id", comboTree.getId());
		} else {
			cq.eq("TSTerritory.id", "1");//这个是全国最高级
		}
		cq.addOrder("territoryCode", SortDirection.asc);
		cq.add();
		List<TSTerritory> list = systemService.getListByCriteriaQuery(cq, false);
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "territoryName", "TSTerritorys");
		List<ComboTree> comboTrees = systemService.ComboTreeWithUser(list, comboTreeModel, null);
		return comboTrees;

	}
}
