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
import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.page.traininginfo.AppointmentMeetingInfoPage;
import vod.samesun.util.SystemType;
import vod.service.appointmentchannelinfo.AppointmentChannelInfoServiceI;
import vod.service.appointmentmeetinginfo.AppointmentMeetingInfoServiceI;
import vod.service.meetinginfo.MeetingInfoServiceI;

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
	private static final Logger logger = Logger.getLogger(AppointmentTrainingController.class);

	@Autowired
	private MeetingInfoServiceI meetingInfoService;
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
	public void datagrid(AppointmentMeetingInfoEntity appointmentMeetingInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//按照预约时间倒序排列
		dataGrid.setSort("appointmentStarttime");
		dataGrid.setOrder(SortDirection.desc);
		
		CriteriaQuery cq = new CriteriaQuery(AppointmentMeetingInfoEntity.class, dataGrid);
		
		//查询预约培训
		cq.eq("rightid", SystemType.MEETING_RIGHT_2);
		cq.add();
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, appointmentMeetingInfo, request.getParameterMap());
		systemService.getDataGridReturn(cq, true);
		
		List<AppointmentMeetingInfoEntity> apps = new ArrayList<AppointmentMeetingInfoEntity>();
		List<AppointmentMeetingInfoPage> temp = new ArrayList<AppointmentMeetingInfoPage>();
		
		apps = dataGrid.getResults();
		for(AppointmentMeetingInfoEntity e : apps){
			//预约状态为新建，但预约时间已过期，则设置预约状态为过期
			if(SystemType.APP_MEETING_STATE_1.equals(e.getAppointmentState().toString()) && appointmentMeetingInfoService.checkPastTime(e)){
				e.setAppointmentState(new Integer(SystemType.APP_MEETING_STATE_3));
			}
			AppointmentMeetingInfoPage page = new AppointmentMeetingInfoPage();
			try {
				MyBeanUtils.copyBeanNotNull2Bean(e, page);
			} catch (Exception e1) {
				logger.error("获取会议预约信息错误");
				e1.printStackTrace();
			}
			//设置培训类型名称
			page.setTypename(systemService.getType(page.getTypeid().toString(), SystemType.TRAINING_TYPE).getTypename());
			temp.add(page);
		}
		dataGrid.setResults(temp);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除会议预约
	 * 
	 * @return
	 */
	@RequestMapping(params = "opa")
	@ResponseBody
	public AjaxJson opa(AppointmentMeetingInfoEntity appointmentMeetingInfo, HttpServletRequest request, String state) {
		AjaxJson j = new AjaxJson();
		appointmentMeetingInfo = systemService.getEntity(AppointmentMeetingInfoEntity.class, appointmentMeetingInfo.getId());
		//再删除预约会议信息
		if(StringUtil.isNotEmpty(state)){
			if(SystemType.APP_MEETING_STATE_2.equals(state)){
				message = "培训预约启用成功";
			}else if(SystemType.APP_MEETING_STATE_3.equals(state)){
				message = "培训预约取消成功";
			}
			Integer stateI = Integer.parseInt(state);
			appointmentMeetingInfo.setAppointmentState(stateI);
		}
		appointmentMeetingInfoService.updateEntitie(appointmentMeetingInfo);
		MeetingInfoEntity meeting = meetingInfoService.getMeetingInfoFromAppointment(appointmentMeetingInfo);
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
	public AjaxJson del(AppointmentMeetingInfoEntity appointmentMeetingInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		appointmentMeetingInfo = systemService.getEntity(AppointmentMeetingInfoEntity.class, appointmentMeetingInfo.getId());
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
	public AjaxJson save(AppointmentMeetingInfoEntity appointmentMeetingInfo, HttpServletRequest request, String tempid) {
		AjaxJson j = new AjaxJson();
		//记录会议ID值
		String meetingID = "";
		
		if (StringUtil.isNotEmpty(appointmentMeetingInfo.getId())) {
			message = "培训预约更新成功";
			AppointmentMeetingInfoEntity t = systemService.get(AppointmentMeetingInfoEntity.class, appointmentMeetingInfo.getId());
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
			appointmentMeetingInfo.setAppointmentState(new Integer(SystemType.APP_MEETING_STATE_1));
			appointmentMeetingInfo.setRightid(SystemType.MEETING_RIGHT_2);
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
	public ModelAndView addorupdate(AppointmentMeetingInfoEntity appointmentMeetingInfo, HttpServletRequest req, String rightid) {
		if (StringUtil.isNotEmpty(appointmentMeetingInfo.getId())) {
			appointmentMeetingInfo = systemService.getEntity(AppointmentMeetingInfoEntity.class, appointmentMeetingInfo.getId());
			req.setAttribute("appointmentMeetingInfoPage", appointmentMeetingInfo);
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
		if(StringUtil.isNotEmpty(rightid)){
			req.setAttribute("rightid", rightid);
		}
		return new ModelAndView("vod/appointmenttraining/appointmentTraining");
	}
	
}
