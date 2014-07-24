package vod.controller.usergroup;
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
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSTerritory;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import vod.entity.usergroup.UserGroupEntity;
import vod.service.usergroup.UserGroupServiceI;

/**   
 * @Title: Controller
 * @Description: 用户分组
 * @author zhangdaihao
 * @date 2014-07-24 11:14:11
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/userGroupController")
public class UserGroupController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserGroupController.class);

	@Autowired
	private UserGroupServiceI userGroupService;
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
	 * 用户分组列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "userGroup")
	public ModelAndView userGroup(HttpServletRequest request) {
		return new ModelAndView("vod/usergroup/userGroupList");
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
	public void datagrid(UserGroupEntity userGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(UserGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, userGroup, request.getParameterMap());
		this.userGroupService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除用户分组
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(UserGroupEntity userGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		userGroup = systemService.getEntity(UserGroupEntity.class, userGroup.getId());
		message = "用户分组删除成功";
		userGroupService.delete(userGroup);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加用户分组
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(UserGroupEntity userGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(userGroup.getId())) {
			message = "用户分组更新成功";
			UserGroupEntity t = userGroupService.get(UserGroupEntity.class, userGroup.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(userGroup, t);
				userGroupService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "用户分组更新失败";
			}
		} else {
			message = "用户分组添加成功";
			userGroupService.save(userGroup);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 用户分组列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(UserGroupEntity userGroup, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(userGroup.getId())) {
			userGroup = userGroupService.getEntity(UserGroupEntity.class, userGroup.getId());
			req.setAttribute("userGroupPage", userGroup);
		}
		return new ModelAndView("vod/usergroup/userGroup");
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
