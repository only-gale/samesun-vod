package vod.controller.appointmenttraining;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.appointmenttraining.AppointmentTrainingEntity;
import vod.entity.traininginfo.TrainingInfoEntity;
import vod.samesun.util.CommandUtil;
import vod.samesun.util.SystemType;
import vod.service.appointmentchannelinfo.AppointmentChannelInfoServiceI;
import vod.service.appointmentmeetinginfo.AppointmentMeetingInfoServiceI;
import vod.service.traininginfo.TrainingInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 会议预约
 * @author zhangdaihao
 * @date 2014-06-18 17:06:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/appointmentTrainingController")
public class AppointmentTrainingController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AppointmentTrainingController.class);

	@Autowired
	private TrainingInfoServiceI trainingInfoService;
	@Autowired
	private AppointmentMeetingInfoServiceI appointmentMeetingInfoService;
	@Autowired
	private AppointmentChannelInfoServiceI appointmentChannelInfoService;
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
	 * 会议预约列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "appointmentTraining")
	public ModelAndView appointmentTraining(HttpServletRequest request) {
		return new ModelAndView("vod/appointmenttraining/appointmentTrainingList");
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
	public void datagrid(AppointmentTrainingEntity appointmentMeetingInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//按照预约时间倒序排列
		dataGrid.setSort("appointmentStarttime");
		dataGrid.setOrder(SortDirection.desc);
		
		CriteriaQuery cq = new CriteriaQuery(AppointmentTrainingEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, appointmentMeetingInfo, request.getParameterMap());
		systemService.getDataGridReturn(cq, true);
		//转换AppointmentMeetingInfoEntity到AppointmentMeetingInfoPage
		
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除会议预约
	 * 
	 * @return
	 */
	@RequestMapping(params = "opa")
	@ResponseBody
	public AjaxJson opa(AppointmentTrainingEntity appointmentMeetingInfo, HttpServletRequest request, String state) {
		AjaxJson j = new AjaxJson();
		appointmentMeetingInfo = systemService.getEntity(AppointmentTrainingEntity.class, appointmentMeetingInfo.getId());
		//再删除预约会议信息
		if(StringUtil.isNotEmpty(state)){
			if(SystemType.APP_MEETING_STATE_2.equals(state)){
				message = "培训预约启用成功";
			}else if(SystemType.APP_MEETING_STATE_3.equals(state)){
				message = "培训预约取消成功";
			}
			appointmentMeetingInfo.setAppointmentState(new Integer(state));
		}
		appointmentMeetingInfoService.updateEntitie(appointmentMeetingInfo);
		TrainingInfoEntity meeting = trainingInfoService.getTrainingInfoFromAppointment(appointmentMeetingInfo);
		systemService.save(meeting);
		
		//关联频道信息
		appointmentChannelInfoService.linkChannel(appointmentMeetingInfo, meeting);
		
		systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("meetingid", meeting.getId());
		j.setAttributes(attrs);
		return j;
	}
	
	/**
	 * 删除会议预约
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AppointmentTrainingEntity appointmentMeetingInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		appointmentMeetingInfo = systemService.getEntity(AppointmentTrainingEntity.class, appointmentMeetingInfo.getId());
		message = "培训预约删除成功";
		//先删除频道信息
		List<AppointmentChannelInfoEntity> channels = appointmentChannelInfoService.findByProperty(AppointmentChannelInfoEntity.class, "meetingid", appointmentMeetingInfo.getId());
		appointmentChannelInfoService.deleteAllEntitie(channels);
		//再删除预约会议信息
		appointmentMeetingInfoService.delete(appointmentMeetingInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加会议预约
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AppointmentTrainingEntity appointmentMeetingInfo, HttpServletRequest request, String tempid) {
		AjaxJson j = new AjaxJson();
		//记录会议ID值
		String meetingID = "";
		
		if (StringUtil.isNotEmpty(appointmentMeetingInfo.getId())) {
			message = "培训预约更新成功";
			AppointmentTrainingEntity t = systemService.get(AppointmentTrainingEntity.class, appointmentMeetingInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(appointmentMeetingInfo, t);
				systemService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "培训预约更新失败";
			}
		} else {
			message = "培训预约添加成功";
			systemService.save(appointmentMeetingInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		//设置是否录制
		appointmentMeetingInfo.setIsRecord(new Integer(appointmentMeetingInfoService.isRecord(appointmentMeetingInfo)));
		systemService.updateEntitie(appointmentMeetingInfo);
		//在保存预约会议信息后获取ID值
		meetingID = appointmentMeetingInfo.getId();
		//根据前台页面表单传回来的临时会议ID值tempid获取频道信息
		List<AppointmentChannelInfoEntity> list = new ArrayList<AppointmentChannelInfoEntity>();
		if(StringUtil.isNotEmpty(tempid)){
			list = systemService.findByProperty(AppointmentChannelInfoEntity.class, "appointmentid", tempid);
			//开始更新频道信息
			for(AppointmentChannelInfoEntity e : list){
				if(!tempid.equals(meetingID)){
					//设置预约会议ID
					e.setAppointmentid(meetingID);
				}
				//删除时间置空
				e.setDelDate(null);
				systemService.updateEntitie(e);
			}
		}
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 会议预约列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AppointmentTrainingEntity appointmentMeetingInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(appointmentMeetingInfo.getId())) {
			appointmentMeetingInfo = systemService.getEntity(AppointmentTrainingEntity.class, appointmentMeetingInfo.getId());
			req.setAttribute("appointmentMeetingInfoPage", appointmentMeetingInfo);
			req.setAttribute("commond", CommandUtil.CMD_APPOINTMENT_MEETING_UPDATE);
			req.setAttribute("state", systemService.getType(appointmentMeetingInfo.getAppointmentState().toString(), SystemType.APP_MEETING_STATE).getTypename());
		}else{
			String create = systemService.getType(SystemType.APP_MEETING_STATE_1, SystemType.APP_MEETING_STATE).getTypename();
			req.setAttribute("state", create);
			req.setAttribute("statecode", SystemType.APP_MEETING_STATE_1);
			req.setAttribute("commond", CommandUtil.CMD_APPOINTMENT_MEETING_NEW);
		}
		//获取数据字典信息,用于渲染下拉框
		//预约会议状态
		List<TSType> states = systemService.getTypes(systemService.getTypeGroup(SystemType.APP_MEETING_STATE, SystemType.APP_MEETING_STATE_NAME));
		//会议所属类型
		List<TSType> appTypes = systemService.getTypes(systemService.getTypeGroup(SystemType.APP_MEETING_TYPE, SystemType.APP_MEETING_TYPE_NAME));
		req.setAttribute("states", states);
		req.setAttribute("appTypes", appTypes);
		String load = req.getParameter("load");
		if(StringUtil.isNotEmpty(load)){
			req.setAttribute("load", load);
		}
		return new ModelAndView("vod/appointmenttraining/appointmentTraining");
	}
	
}
