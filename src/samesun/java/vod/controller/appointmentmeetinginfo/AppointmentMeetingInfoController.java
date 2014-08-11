package vod.controller.appointmentmeetinginfo;

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
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.samesun.util.CommandUtil;
import vod.samesun.util.SystemType;
import vod.service.appointmentchannelinfo.AppointmentChannelInfoServiceI;
import vod.service.appointmentmeetinginfo.AppointmentMeetingInfoServiceI;
import vod.service.confcodecinfo.ConfCodecInfoServiceI;
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
@RequestMapping("/appointmentMeetingInfoController")
public class AppointmentMeetingInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AppointmentMeetingInfoController.class);

	@Autowired
	private MeetingInfoServiceI meetingInfoService;
	@Autowired
	private AppointmentMeetingInfoServiceI appointmentMeetingInfoService;
	@Autowired
	private AppointmentChannelInfoServiceI appointmentChannelInfoService;
	@Autowired
	private ConfCodecInfoServiceI confCodecInfoService;
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
	@RequestMapping(params = "appointmentMeetingInfo")
	public ModelAndView appointmentMeetingInfo(HttpServletRequest request) {
		return new ModelAndView("vod/appointmentmeetinginfo/appointmentMeetingInfoList");
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
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, appointmentMeetingInfo, request.getParameterMap());
		this.appointmentMeetingInfoService.getDataGridReturn(cq, true);
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
	public AjaxJson opa(AppointmentMeetingInfoEntity appointmentMeetingInfo, HttpServletRequest request, String state) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attrs = new HashMap<String, Object>();
		appointmentMeetingInfo = systemService.getEntity(AppointmentMeetingInfoEntity.class, appointmentMeetingInfo.getId());
		String meetingid = "";
		if(null != appointmentMeetingInfo && appointmentMeetingInfoService.checkTime(appointmentMeetingInfo)){
			if(meetingInfoService.wetherused(appointmentMeetingInfo)){
				//编码器冲突
				attrs.put("result", "conflict");
			}else{
				//可以开始直播
				attrs.put("result", "success");
				
				message = "会议预约启用成功";
				
				//复制预约信息到直播信息
				MeetingInfoEntity meeting = meetingInfoService.getMeetingInfoFromAppointment(appointmentMeetingInfo);
				meetingInfoService.save(meeting);
				
				//记录ID
				meetingid = meeting.getId();
				attrs.put("meetingid", meetingid);
				//记录状态
				attrs.put("meetingstate", meeting.getMeetingstate());
				//记录可否录制
				attrs.put("isrecord", meeting.getIsrecord());
				
				//关联频道信息
				appointmentChannelInfoService.linkChannel(appointmentMeetingInfo, meeting);
				
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		}else{
			message = "会议预约启用失败,还未到预约时间";
			
			//时间未到  不可直播
			attrs.put("result", "failed");
		}
		
		j.setMsg(message);
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
		message = "会议预约删除成功";
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
			message = "会议预约更新成功";
			AppointmentMeetingInfoEntity t = appointmentMeetingInfoService.get(AppointmentMeetingInfoEntity.class, appointmentMeetingInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(appointmentMeetingInfo, t);
				appointmentMeetingInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会议预约更新失败";
			}
		} else {
			message = "会议预约添加成功";
			appointmentMeetingInfoService.save(appointmentMeetingInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		//在保存预约会议信息后获取ID值
		meetingID = appointmentMeetingInfo.getId();
		//根据前台页面表单传回来的临时会议ID值tempid获取频道信息
		List<AppointmentChannelInfoEntity> list = new ArrayList<AppointmentChannelInfoEntity>();
		if(StringUtil.isNotEmpty(tempid)){
			list = appointmentChannelInfoService.findByProperty(AppointmentChannelInfoEntity.class, "appointmentid", tempid);
			//开始更新频道信息
			for(AppointmentChannelInfoEntity e : list){
				if(!tempid.equals(meetingID)){
					//设置预约会议ID
					e.setAppointmentid(meetingID);
				}
				//删除时间置空
				e.setDelDate(null);
				appointmentChannelInfoService.updateEntitie(e);
			}
		}
		
		//设置是否录制
		appointmentMeetingInfo.setIsRecord(new Integer(appointmentMeetingInfoService.isRecord(appointmentMeetingInfo)));
		appointmentMeetingInfoService.updateEntitie(appointmentMeetingInfo);
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 会议预约列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AppointmentMeetingInfoEntity appointmentMeetingInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(appointmentMeetingInfo.getId())) {
			appointmentMeetingInfo = appointmentMeetingInfoService.getEntity(AppointmentMeetingInfoEntity.class, appointmentMeetingInfo.getId());
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
		return new ModelAndView("vod/appointmentmeetinginfo/appointmentMeetingInfo");
	}
	
	@RequestMapping(params = "whouesed")
	public ModelAndView whouesed(AppointmentMeetingInfoEntity meeting, HttpServletRequest req, String meetingType) throws Exception {
		List<MeetingInfoEntity> conflicts = new ArrayList<MeetingInfoEntity>();
		if (StringUtil.isNotEmpty(meeting.getId())) {
			meeting = systemService.get(AppointmentMeetingInfoEntity.class, meeting.getId());
			if(null != meeting){
				//获取当前会议的所有编码器
				List<ConfCodecInfoEntity> theCodecs = confCodecInfoService.getCodecs(meeting);
				//获取所有会议
				List<MeetingInfoEntity> meetings = systemService.loadAll(MeetingInfoEntity.class);
				
				for(MeetingInfoEntity m : meetings){
					//获取冲突会议的状态,用于判断是否和当前会议冲突
					Integer state = m.getMeetingstate();
					//当有直播会议冲突时
					if(!meeting.getId().equals(m.getId()) && (state == Integer.valueOf(SystemType.MEETING_STATE_1) ||
							state == Integer.valueOf(SystemType.MEETING_STATE_2) ||
							state == Integer.valueOf(SystemType.MEETING_STATE_3))){
						//查询冲突编码器BEGIN
						//获取每个冲突会议的所有编码器
						List<ConfCodecInfoEntity> codecs = meetingInfoService.getCodecs(m);
						//当该会议占用当前被选择的编码器时记录该会议
						for(ConfCodecInfoEntity e : theCodecs){
							if(codecs.contains(e) && !conflicts.contains(m)){
								conflicts.add(m);
							}
						}
						req.setAttribute("conflictMeetings", conflicts);
						//查询冲突编码器END
					}
				}
				
			}
		}
		
		return new ModelAndView("vod/meetinginfo/conflictMeetingInfo");
	}
	
}
