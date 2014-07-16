package vod.controller.meetinginfo;

import java.io.IOException;
import java.text.ParseException;
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
import org.jeecgframework.core.util.DataUtils;
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
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.samesun.util.SystemType;
import vod.service.appointmentchannelinfo.AppointmentChannelInfoServiceI;
import vod.service.confcodecinfo.ConfCodecInfoServiceI;
import vod.service.livesectionrecord.LiveSectionRecordServiceI;
import vod.service.meetinghistory.MeetingHistoryServiceI;
import vod.service.meetinginfo.MeetingInfoServiceI;
import vod.service.meetinglivesession.MeetingLiveSessionServiceI;
import vod.service.vodsectionrecord.VodSectionRecordServiceI;
import vod.service.vodsession.VodSessionServiceI;

/**   
 * @Title: Controller
 * @Description: 会议信息
 * @author zhangdaihao
 * @date 2014-06-17 16:32:22
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/meetingInfoController")
public class MeetingInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MeetingInfoController.class);

	@Autowired
	private MeetingInfoServiceI meetingInfoService;
	@Autowired
	private AppointmentChannelInfoServiceI appointmentChannelInfoService;
	@Autowired
	private ConfCodecInfoServiceI confCodecInfoService;
	@Autowired
	private MeetingLiveSessionServiceI meetingLiveSessionService;
	@Autowired
	private LiveSectionRecordServiceI liveSectionRecordService;
	@Autowired
	private MeetingHistoryServiceI meetingHistoryService;
	@Autowired
	private VodSessionServiceI vodSessionService;
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
	 * 会议信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "meetingInfo")
	public ModelAndView meetingInfo(HttpServletRequest request) {
		return new ModelAndView("vod/meetinginfo/meetingInfoList");
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
	public void datagrid(MeetingInfoEntity meetingInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//按照预约时间倒序排列
		dataGrid.setSort("billstarttime");
		dataGrid.setOrder(SortDirection.desc);
		CriteriaQuery cq = new CriteriaQuery(MeetingInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, meetingInfo, request.getParameterMap());
		this.meetingInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除会议信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(MeetingInfoEntity meetingInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		meetingInfo = systemService.getEntity(MeetingInfoEntity.class, meetingInfo.getId());
		message = "会议信息删除成功";
		//先删除频道信息
		List<AppointmentChannelInfoEntity> channels = meetingInfoService.findByProperty(AppointmentChannelInfoEntity.class, "meetingid", meetingInfo.getId());
		//释放编码器资源
		for(AppointmentChannelInfoEntity c : channels){
			String id1 = c.getCodec1id(), id2 = c.getCodec2id();
			ConfCodecInfoEntity c1 = systemService.getEntity(ConfCodecInfoEntity.class, id1);
			if(SystemType.CODEC_AVILABLE_1.equals(c1.getDisable())){
				c1.setDisable(SystemType.CODEC_AVILABLE_0);
				systemService.updateEntitie(c1);
			}
			if(StringUtil.isNotEmpty(id2) && !id2.equals(id1)){
				ConfCodecInfoEntity c2 = systemService.getEntity(ConfCodecInfoEntity.class, id2);
				if(SystemType.CODEC_AVILABLE_1.equals(c2.getDisable())){
					c2.setDisable(SystemType.CODEC_AVILABLE_0);
					systemService.updateEntitie(c2);
				}
			}
		}
		meetingInfoService.deleteAllEntitie(channels);
		
		//最后删除预约会议信息
		meetingInfoService.delete(meetingInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加会议信息
	 * 
	 * @param ids
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(MeetingInfoEntity meetingInfo, HttpServletRequest request, HttpServletResponse response, String tempid, String id, String typeid, String subject, String compere, String introduction, String isrecord) throws IOException, ParseException {
		System.out.println(request.getParameter("flag"));
		AjaxJson j = new AjaxJson();
		//记录会议ID值
		String meetingID = "";
		meetingID = meetingInfo.getId();
		if(StringUtil.isNotEmpty(meetingID)){
			meetingID = id;
		}
		meetingInfo.setCompere(compere);
		meetingInfo.setIntroduction(introduction);
		meetingInfo.setSubject(subject);
		meetingInfo.setTypeid(new Integer(typeid));
		if (StringUtil.isNotEmpty(meetingID)) {
			message = "会议信息更新成功";
			MeetingInfoEntity t = meetingInfoService.get(MeetingInfoEntity.class, meetingInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(meetingInfo, t);
				meetingInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "会议信息更新失败";
			}
		} else {
			message = "会议直播成功";
			//设置直播类型为直播
			meetingInfo.setIsasflive(new Integer(SystemType.LIVE_TYPE_1));
			//设置直播状态为直播中
			meetingInfo.setMeetingstate(new Integer(SystemType.MEETING_STATE_1));
			//设置直播开始时间为现在
			meetingInfo.setBillstarttime(DataUtils.parseDate(DataUtils.getDataString(DataUtils.datetimeFormat), DataUtils.datetimeFormat.toPattern()));
			//设置是否录制标志
			meetingInfo.setIsrecord(new Integer(isrecord));
			
			meetingInfoService.save(meetingInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		//在保存预约会议信息后获取ID值
		meetingID = meetingInfo.getId();
		//根据前台页面传回来的临时会议ID值tempid获取频道信息
		List<AppointmentChannelInfoEntity> list = new ArrayList<AppointmentChannelInfoEntity>();
		if(StringUtil.isNotEmpty(tempid)){
			list = meetingInfoService.findByProperty(AppointmentChannelInfoEntity.class, "meetingid", tempid);
			//开始更新频道信息
			for(AppointmentChannelInfoEntity e : list){
				if(!tempid.equals(meetingID)){
					//设置预约会议ID
					e.setMeetingid(meetingID);
					//启用编码器
					appointmentChannelInfoService.linkCodec(e, SystemType.CODEC_AVILABLE_1);
				}
				//删除时间置空
				e.setDelDate(null);
				meetingInfoService.updateEntitie(e);
			}
		}
		
		j.setMsg(message);
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("meetingid", meetingID);
		j.setAttributes(attrs);
//		response.sendRedirect("meetingInfoController.do?addorupdate&id=" + meetingID);
		return j;
	}

	/**
	 * 会议信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(MeetingInfoEntity meetingInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(meetingInfo.getId())) {
			meetingInfo = meetingInfoService.getEntity(MeetingInfoEntity.class, meetingInfo.getId());
			req.setAttribute("meetingInfoPage", meetingInfo);
		}
		//获取数据字典信息,用于渲染下拉框
		//会议所属类型
		List<TSType> appTypes = systemService.getTypes(systemService.getTypeGroup(SystemType.APP_MEETING_TYPE, SystemType.APP_MEETING_TYPE_NAME));
		req.setAttribute("appTypes", appTypes);
		String load = req.getParameter("load");
		if(StringUtil.isNotEmpty(load)){
			req.setAttribute("load", load);
		}
		return new ModelAndView("vod/meetinginfo/meetingInfo");
	}
	
	/**
	 * 开始直播
	 * 
	 * @return
	 */
	@RequestMapping(params = "startLive")
	public AjaxJson startLive(MeetingInfoEntity meetingInfo, HttpServletRequest req, String flag) {
		AjaxJson j = new AjaxJson();
		String meetingid = "", meetinginfoId = meetingInfo.getId(), reqId = req.getParameter("meetingid");
		if(StringUtil.isNotEmpty(meetinginfoId)){
			meetingid = meetinginfoId;
		}else if(StringUtil.isNotEmpty(reqId)){
			meetingid = reqId;
		}
		if(StringUtil.isNotEmpty(meetingid)){
			meetingInfo = meetingInfoService.getEntity(MeetingInfoEntity.class, meetingid);
		}
		message = flag;
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 开始录制
	 * 主要逻辑：设置直播会议状态为“直播并录制中”；设置录制
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(params = "startRecord")
	public AjaxJson startRecord(MeetingInfoEntity meetingInfo, HttpServletRequest req, String id) throws ParseException {
		AjaxJson j = new AjaxJson();
		if(StringUtil.isNotEmpty(id)){
			MeetingInfoEntity meeting = meetingInfoService.getEntity(MeetingInfoEntity.class, id);
			if(SystemType.MEETING_STATE_2.equals(meeting.getMeetingstate().toString())){
				message = "开始录制";
			}else if(SystemType.MEETING_STATE_3.equals(meeting.getMeetingstate().toString())){
				message = "录制失败";
			}else{
				
				logger.info("开始录制之前时刻" + DataUtils.datetimeFormat.format(DataUtils.getDate()));
				String result = liveSectionRecordService.StartChannelSectionRecord(id);
				logger.info(result);
				logger.info("开始录制之后时刻" + DataUtils.datetimeFormat.format(DataUtils.getDate()));
				message = "开始录制";
				/*if(StringUtil.isNotEmpty(result)){
					message = "开始录制";
				}else{
					message = "录制失败";
				}*/
			}
		}else{
			message = "程序发生错误,缺少直播会议ID值";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 停止录制
	 * 
	 * @return
	 */
	@RequestMapping(params = "stopRecord")
	public AjaxJson stopRecord(MeetingInfoEntity meetingInfo, HttpServletRequest req, String id) {
		AjaxJson j = new AjaxJson();
		if(StringUtil.isNotEmpty(id)){
			MeetingInfoEntity meeting = meetingInfoService.getEntity(MeetingInfoEntity.class, id);
			if(SystemType.MEETING_STATE_1.equals(meeting.getMeetingstate().toString())){
				message = "停止录制";
			}else if(SystemType.MEETING_STATE_3.equals(meeting.getMeetingstate().toString())){
				message = "停止录制失败";
			}else{
				
				logger.info(DataUtils.datetimeFormat.format(DataUtils.getDate()));
				String result = liveSectionRecordService.EndChannelSectionRecord(id);
				logger.info(result);
				logger.info(DataUtils.datetimeFormat.format(DataUtils.getDate()));
				if(StringUtil.isNotEmpty(result)){
					message = "停止录制";
				}else{
					message = "停止录制失败";
				}
			}
			j.setMsg(message);
		}else{
			message = "程序发生错误,缺少直播会议ID值";
		}
		return j;
	}
	
	/**
	 * 结束直播
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "stopLive")
	public AjaxJson stopLive(MeetingInfoEntity meetingInfo, HttpServletRequest req, String id) throws Exception {
		AjaxJson j = new AjaxJson();
		if(StringUtil.isNotEmpty(id)){
			message = "结束直播";
			MeetingInfoEntity t = meetingInfoService.get(MeetingInfoEntity.class, id);
			String state = t.getMeetingstate().toString();
			
			//改变状态
			t.setMeetingstate(new Integer(SystemType.MEETING_STATE_3));
			t.setBillduration((int) ((DataUtils.getMillis() - t.getBillstarttime().getTime()) / (60 * 1000)));
			
			//如果当前直播会议状态为“直播并录制中”，则在执行“结束直播”时要先执行“结束录制”
			if(SystemType.MEETING_STATE_2.equals(state)){
				liveSectionRecordService.EndChannelSectionRecord(id);
			}

			//生成会议日志
			meetingHistoryService.getHistoryFromLive(t);
			
			//生成点播会话信息
			vodSessionService.getSessionByMeetingId(id);
			
			//生成点播明细
			vodSectionRecordService.getVodSectionRecordByMeetingId(id);
			
			meetingInfoService.updateEntitie(t);
			
			//释放编码器
			List<AppointmentChannelInfoEntity> channels = meetingInfoService.findByProperty(AppointmentChannelInfoEntity.class, "meetingid", id);
			for(AppointmentChannelInfoEntity channel : channels){
				appointmentChannelInfoService.linkCodec(channel, SystemType.CODEC_AVILABLE_0);
			}
			
		}else{
			message = "结束直播失败,缺少直播会议ID值";
		}
		j.setMsg(message);
		return j;
	}
}
