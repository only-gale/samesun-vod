package vod.controller.tstype;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.meetinghistory.MeetingHistoryEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.entity.meetinglog.MeetingLogEntity;
import vod.entity.vodsession.VodSessionEntity;
import vod.samesun.util.SystemType;
import vod.samesun.util.TypeComparator;
import vod.service.tstype.TsTypeServiceI;

/**   
 * @Title: Controller
 * @Description: 数据字典
 * @author zhangdaihao
 * @date 2014-08-18 16:13:04
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tsTypeController")
public class TsTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(TsTypeController.class);

	@Autowired
	private TsTypeServiceI tsTypeService;
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
	 * 数据字典列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "meetingTyp")
	public ModelAndView meetingTyp(HttpServletRequest request, String groupCode) {
		request.setAttribute("groupCode", groupCode);
		return new ModelAndView("vod/tstype/meetingTypeList");
	}
	
	/**
	 * 数据字典列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "trainingTp")
	public ModelAndView trainingTp(HttpServletRequest request, String groupCode) {
		request.setAttribute("groupCode", groupCode);
		return new ModelAndView("vod/tstype/trainingTypeList");
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
	public void datagrid(TSType tsType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, String groupCode) {
		dataGrid.setSort("typecode");
		dataGrid.setOrder(SortDirection.desc);
		CriteriaQuery cq = new CriteriaQuery(TSType.class, dataGrid);
		TSTypegroup group = systemService.getTypeGroupByCode(groupCode);
		if(group != null){
			cq.eq("TSTypegroup.id", group.getId());
			cq.add();
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tsType, request.getParameterMap());
		this.tsTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除数据字典
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSType tsType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tsType = systemService.getEntity(TSType.class, tsType.getId());
		message = "数据字典删除成功";
		tsTypeService.delete(tsType);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加数据字典
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TSType tsType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tsType.getId())) {
			message = "数据字典更新成功";
			TSType t = tsTypeService.get(TSType.class, tsType.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tsType, t);
				tsTypeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "数据字典更新失败";
			}
		} else {
			message = "数据字典添加成功";
			TSTypegroup group = tsType.getTSTypegroup();
			String c = this.getMaxCode(group);
			if(StringUtil.isNotEmpty(c)){
				tsType.setTypecode(new Integer((Integer.valueOf(c) + 1)).toString());
			}
			tsTypeService.save(tsType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 数据字典列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSType tsType, HttpServletRequest req, String gcode) {
		if (StringUtil.isNotEmpty(tsType.getId())) {
			tsType = tsTypeService.getEntity(TSType.class, tsType.getId());
			req.setAttribute("tsTypePage", tsType);
		}else{
			if(SystemType.MEETING_TYPE.equals(gcode)){
				TSTypegroup group = systemService.getTypeGroup(gcode, SystemType.MEETING_TYPE_NAME);
				TSType type = new TSType();
				type.setTSTypegroup(group);
				req.setAttribute("tsTypePage", type);
			}else if(SystemType.TRAINING_TYPE.equals(gcode)){
				TSTypegroup group = systemService.getTypeGroup(gcode, SystemType.TRAINING_TYPE_NAME);
				TSType type = new TSType();
				type.setTSTypegroup(group);
				req.setAttribute("tsTypePage", type);
			}
		}
		return new ModelAndView("vod/tstype/tsType");
	}
	
	private String getMaxCode(TSTypegroup group){
		String maxCode = "";
		if(group != null){
			List<TSType> types = systemService.findByProperty(TSType.class, "TSTypegroup.id", group.getId());
			Collections.sort(types, new TypeComparator());
			Collections.reverse(types);
			maxCode = types.get(0).getTypecode();
		}
		return maxCode;
	}
}
