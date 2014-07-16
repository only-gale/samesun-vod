package vod.controller.vodsectionrecord;

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

import vod.entity.vodsectionrecord.VodSectionRecordEntity;
import vod.service.vodsectionrecord.VodSectionRecordServiceI;

/**   
 * @Title: Controller
 * @Description: 点播信息
 * @author zhangdaihao
 * @date 2014-07-15 17:48:17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/vodSectionRecordController")
public class VodSectionRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(VodSectionRecordController.class);

	@Autowired
	private VodSectionRecordServiceI vodSectionRecordService;
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
	 * 点播信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "vodSectionRecord")
	public ModelAndView vodSectionRecord(HttpServletRequest request) {
		return new ModelAndView("vod/vodsectionrecord/vodSectionRecordList");
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
	public void datagrid(VodSectionRecordEntity vodSectionRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VodSectionRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, vodSectionRecord, request.getParameterMap());
		this.vodSectionRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除点播信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(VodSectionRecordEntity vodSectionRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		vodSectionRecord = systemService.getEntity(VodSectionRecordEntity.class, vodSectionRecord.getId());
		message = "点播信息删除成功";
		vodSectionRecordService.delete(vodSectionRecord);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加点播信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(VodSectionRecordEntity vodSectionRecord, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(vodSectionRecord.getId())) {
			message = "点播信息更新成功";
			VodSectionRecordEntity t = vodSectionRecordService.get(VodSectionRecordEntity.class, vodSectionRecord.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(vodSectionRecord, t);
				vodSectionRecordService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "点播信息更新失败";
			}
		} else {
			message = "点播信息添加成功";
			vodSectionRecordService.save(vodSectionRecord);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 点播信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(VodSectionRecordEntity vodSectionRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(vodSectionRecord.getId())) {
			vodSectionRecord = vodSectionRecordService.getEntity(VodSectionRecordEntity.class, vodSectionRecord.getId());
			req.setAttribute("vodSectionRecordPage", vodSectionRecord);
		}
		return new ModelAndView("vod/vodsectionrecord/vodSectionRecord");
	}
}
