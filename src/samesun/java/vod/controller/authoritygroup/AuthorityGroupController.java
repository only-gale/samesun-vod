package vod.controller.authoritygroup;
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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.authgroupterminal.AuthGroupTerminalEntity;
import vod.entity.authoritygroup.AuthorityGroupEntity;
import vod.entity.terminalinfo.TerminalInfoEntity;
import vod.page.authoritygroup.AuthorityGroupPage;
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
	 * @throws Exception 
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	public void datagrid(AuthorityGroupEntity authorityGroup,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws Exception {
		//如果有查询条件，则设置为模糊查询
		String name = authorityGroup.getName(), des = authorityGroup.getDesc();
		if(StringUtil.isNotEmpty(name) || StringUtil.isNotEmpty(des)){
			authorityGroup.setName("*" + name + "*");
			authorityGroup.setDesc("*" + des + "*");
		}
		CriteriaQuery cq = new CriteriaQuery(AuthorityGroupEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, authorityGroup, request.getParameterMap());
		this.authorityGroupService.getDataGridReturn(cq, true);
		List<AuthorityGroupEntity> entyties = dataGrid.getResults();
		if(null != entyties && entyties.size() > 0){
			List<AuthorityGroupPage> pages = new ArrayList<AuthorityGroupPage>();
			for(AuthorityGroupEntity e : entyties){
				AuthorityGroupPage page = new AuthorityGroupPage();
				MyBeanUtils.copyBeanNotNull2Bean(e, page);
				String ids="", names="";
				List<AuthGroupTerminalEntity> users = systemService.findByProperty(AuthGroupTerminalEntity.class, "authid", e.getId());
				for(AuthGroupTerminalEntity u : users){
					ids += ("," + u.getTerminalid());
					TerminalInfoEntity t = systemService.get(TerminalInfoEntity.class, u.getTerminalid());
					if(null != t){
						names += ("、" + t.getName());
					}
				}
				page.setTerminalIDs(ids.length() > 1 ? ids.substring(1) : ids);
				page.setTerminalNames(names.length() > 1 ? names.substring(1) : names);
				pages.add(page);
			}
			dataGrid.setResults(pages);
		}
		
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
		systemService.deleteAllEntitie(systemService.findByProperty(AuthGroupTerminalEntity.class, "authid", authorityGroup.getId()));
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
	public AjaxJson save(AuthorityGroupPage authorityGroup, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(authorityGroup.getId())) {
			message = "终端分组更新成功";
			AuthorityGroupEntity t = authorityGroupService.get(AuthorityGroupEntity.class, authorityGroup.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(authorityGroup, t);
				authorityGroupService.saveOrUpdate(t);
				
				//删除已有终端与分组的关联关系
				systemService.deleteAllEntitie(systemService.findByProperty(AuthGroupTerminalEntity.class, "authid", t.getId()));
				//维护新的终端与分组的关联关系
				for(String id : authorityGroup.getTerminalIDs().split(",")){
					if(StringUtil.isNotEmpty(id)){
						AuthGroupTerminalEntity gu = new AuthGroupTerminalEntity();
						gu.setAuthid(t.getId());
						gu.setTerminalid(id);
						systemService.save(gu);
					}
				}
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "终端分组更新失败";
			}
		} else {
			message = "终端分组添加成功";
			AuthorityGroupEntity entity = new AuthorityGroupEntity();
			entity.setName(authorityGroup.getName());
			entity.setDesc(authorityGroup.getDesc());
			authorityGroupService.save(entity);
			//维护终端与分组的关联关系
			for(String id : authorityGroup.getTerminalIDs().split(",")){
				if(StringUtil.isNotEmpty(id)){
					AuthGroupTerminalEntity gt = new AuthGroupTerminalEntity();
					gt.setAuthid(entity.getId());
					gt.setTerminalid(id);
					systemService.save(gt);
				}
			}
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
		try {
			if (StringUtil.isNotEmpty(authorityGroup.getId())) {
				authorityGroup = authorityGroupService.getEntity(AuthorityGroupEntity.class, authorityGroup.getId());
				AuthorityGroupPage page = new AuthorityGroupPage();
				MyBeanUtils.copyBeanNotNull2Bean(authorityGroup, page);
				List<AuthGroupTerminalEntity> ts = systemService.findByProperty(AuthGroupTerminalEntity.class, "authid", page.getId());
				String ids = "";
				for(AuthGroupTerminalEntity u : ts){
					ids += ("," + u.getTerminalid());
				}
				page.setTerminalIDs(ids.substring(1));
				req.setAttribute("authorityGroupPage", page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("vod/authoritygroup/authorityGroup");
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
		List<ComboTree> comboTrees = systemService.ComboTreeFull(list, comboTreeModel, null, "terminal");
		return comboTrees;

	}
}
