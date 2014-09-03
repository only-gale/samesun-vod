package vod.controller.confcodecinfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import org.jeecgframework.web.system.pojo.base.TSTerritory;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.confcodecrecordsrv.ConfCodecRecordSrvEntity;
import vod.entity.confrecordsrvinfo.ConfRecordSrvInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.page.confcodecinfo.ConfCodecInfoPage;
import vod.samesun.util.ComboboxBean;
import vod.samesun.util.RECORDComparator;
import vod.samesun.util.SystemType;
import vod.service.confcodecinfo.ConfCodecInfoServiceI;
import vod.service.meetinginfo.MeetingInfoServiceI;

import com.alibaba.fastjson.JSON;

/**   
 * @Title: Controller
 * @Description: 编码器配置信息
 * @author zhangdaihao
 * @date 2014-06-19 10:40:44
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/confCodecInfoController")
public class ConfCodecInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConfCodecInfoController.class);

	@Autowired
	private ConfCodecInfoServiceI confCodecInfoService;
	@Autowired
	private MeetingInfoServiceI meetingInfoService;
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
	 * 编码器配置信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "confCodecInfo")
	public ModelAndView confCodecInfo(HttpServletRequest request) {
		return new ModelAndView("vod/confcodecinfo/confCodecInfoList");
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
	public void datagrid(ConfCodecInfoEntity confCodecInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		dataGrid.setSort("createDate");
		CriteriaQuery cq = new CriteriaQuery(ConfCodecInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, confCodecInfo, request.getParameterMap());
		this.confCodecInfoService.getDataGridReturn(cq, true);
		List<ConfCodecInfoEntity> codecs = dataGrid.getResults();
		List<ConfCodecInfoPage> pages = new ArrayList<ConfCodecInfoPage>();
		try {
			for(ConfCodecInfoEntity c : codecs){
				ConfCodecInfoPage page = new ConfCodecInfoPage();
				MyBeanUtils.copyBeanNotNull2Bean(c, page);
				TSTerritory self = systemService.get(TSTerritory.class, c.getGroupid());
				String name = "";
				List<TSTerritory> ts = new ArrayList<TSTerritory>();
				TSTerritory parent = self.getTSTerritory();
				ts.add(self);
				String pid = parent.getId();
				//当父组织机构不为根机构时，查找父机构
				while(!"1".equals(pid)){
					parent = systemService.get(TSTerritory.class, pid);
					ts.add(parent);
					parent = parent.getTSTerritory();
					pid = parent.getId();
				}
				//按照添加顺序逆序
				Collections.reverse(ts);
				for(TSTerritory t : ts){
					name += "-" + t.getTerritoryName(); 
				}
				page.setGroupname(name.substring(1));
				
				ConfCodecRecordSrvEntity cr = systemService.get(ConfCodecRecordSrvEntity.class, page.getCr());
				if(cr != null){
					ConfRecordSrvInfoEntity rs = systemService.get(ConfRecordSrvInfoEntity.class, cr.getRecordsrvid());
					if(rs != null){
						page.setRecord(rs.getName());
					}
				}
				pages.add(page);
			}
		} catch (Exception e) {
			logger.error("获取编码器信息错误");
			e.printStackTrace();
		}
		dataGrid.setResults(pages);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除编码器配置信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ConfCodecInfoEntity confCodecInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		confCodecInfo = systemService.getEntity(ConfCodecInfoEntity.class, confCodecInfo.getId());
		message = "编码器配置信息删除成功";
		confCodecInfoService.delete(confCodecInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加编码器配置信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ConfCodecInfoEntity confCodecInfo, HttpServletRequest request, String record) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(confCodecInfo.getId())) {
			message = "编码器配置信息更新成功";
			ConfCodecInfoEntity t = confCodecInfoService.get(ConfCodecInfoEntity.class, confCodecInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(confCodecInfo, t);
				confCodecInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				
				ConfCodecRecordSrvEntity ccr = confCodecInfoService.get(ConfCodecRecordSrvEntity.class, t.getCr());
				if(ccr != null && StringUtil.isNotEmpty(record) && !record.equals(ccr.getRecordsrvid())){
					ccr.setRecordsrvid(record);
					confCodecInfoService.updateEntitie(ccr);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				message = "编码器配置信息更新失败";
			}
		} else {
			message = "编码器配置信息添加成功";
			confCodecInfoService.save(confCodecInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			
			ConfCodecRecordSrvEntity cr = new ConfCodecRecordSrvEntity();
			cr.setCodecid(confCodecInfo.getId());
			cr.setRecordsrvid(record);
			confCodecInfoService.save(cr);
			
			confCodecInfo.setCr(cr.getId());
			confCodecInfoService.updateEntitie(confCodecInfo);
		}
		
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 编码器配置信息列表页面跳转
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ConfCodecInfoEntity confCodecInfo, HttpServletRequest req) throws Exception {
		if (StringUtil.isNotEmpty(confCodecInfo.getId())) {
			confCodecInfo = confCodecInfoService.getEntity(ConfCodecInfoEntity.class, confCodecInfo.getId());
			ConfCodecInfoPage page = new ConfCodecInfoPage();
			MyBeanUtils.copyBeanNotNull2Bean(confCodecInfo, page);
			ConfCodecRecordSrvEntity cr = confCodecInfoService.get(ConfCodecRecordSrvEntity.class, confCodecInfo.getCr());
			page.setRecord(cr.getRecordsrvid());
			
			TSTerritory self = systemService.get(TSTerritory.class, page.getGroupid());
			String name = "";
			List<TSTerritory> ts = new ArrayList<TSTerritory>();
			TSTerritory parent = self.getTSTerritory();
			ts.add(self);
			String pid = parent.getId();
			//当父组织机构不为根机构时，查找父机构
			while(!"1".equals(pid)){
				parent = systemService.get(TSTerritory.class, pid);
				ts.add(parent);
				parent = parent.getTSTerritory();
				pid = parent.getId();
			}
			//按照添加顺序逆序
			Collections.reverse(ts);
			for(TSTerritory t : ts){
				name += "-" + t.getTerritoryName(); 
			}
			page.setGroupname(name.substring(1));
			
			req.setAttribute("confCodecInfoPage", page);
		}
		
		List<ConfRecordSrvInfoEntity> records = confCodecInfoService.loadAll(ConfRecordSrvInfoEntity.class);
		Collections.sort(records, new RECORDComparator());
		req.setAttribute("records", records);
		
		return new ModelAndView("vod/confcodecinfo/confCodecInfo");
	}
	
	/**
	 * 获得无分页的所有数据,用于填充下拉框
	 * @param meetingType 指定会议类型:直播OR预约
	 * @param excepts 指定需要在结果集中排除的以逗号分隔的编码器id
	 * @param appointmentStarttime 预约开始时间,当meetingType为预约时,该参数必定有值(校验工作由前台页面完成)
	 * @param appointmentDuration 预约持续时间,当meetingType为预约时,该参数必定有值(校验工作由前台页面完成)
	 */
	@RequestMapping(params = "combox")
	public void combox(HttpServletRequest request, HttpServletResponse response, String meetingType, String excepts, String appointmentStarttime, String appointmentDuration){
		PrintWriter pw = null;
		try {
			List<ConfCodecInfoEntity> list = confCodecInfoService.combox(meetingType, excepts, appointmentStarttime, appointmentDuration);
			List<ComboboxBean> result = new ArrayList<ComboboxBean>();
			for(ConfCodecInfoEntity c : list){
				ComboboxBean b = new ComboboxBean();
				b.setId(c.getId());
				b.setName(c.getName());
				result.add(b);
			}
			response.setContentType("text/html;charset=utf-8");
			String json = JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss");
			pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null != pw){
				pw.close();
			}
		}
	}
	
	/**
	 * 获得无分页的所有数据,用于填充下拉框
	 * @param meetingType 指定会议类型:直播OR预约
	 * @param excepts 指定需要在结果集中排除的以逗号分隔的编码器id
	 * @param appointmentStarttime 预约开始时间,当meetingType为预约时,该参数必定有值(校验工作由前台页面完成)
	 * @param appointmentDuration 预约持续时间,当meetingType为预约时,该参数必定有值(校验工作由前台页面完成)
	 */
	@RequestMapping(params = "combox4UserCodec")
	public void combox4UserCodec(HttpServletRequest request, HttpServletResponse response, String meetingType, String excepts, String appointmentStarttime, String appointmentDuration){
		PrintWriter pw = null;
		try {
			List<ConfCodecInfoEntity> list = confCodecInfoService.combox4UserCodec(meetingType, excepts, appointmentStarttime, appointmentDuration);
			List<ComboboxBean> result = new ArrayList<ComboboxBean>();
			for(ConfCodecInfoEntity c : list){
				ComboboxBean b = new ComboboxBean();
				b.setId(c.getId());
				b.setName(c.getName());
				result.add(b);
			}
			response.setContentType("text/html;charset=utf-8");
			String json = JSON.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss");
			pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null != pw){
				pw.close();
			}
		}
	}
	
	@RequestMapping(params = "wetheruesed")
	@ResponseBody
	public AjaxJson wetheruesed(ConfCodecInfoEntity confCodecInfo,HttpServletRequest request, HttpServletResponse response, String meetingType, String excepts, String appointmentStarttime, String appointmentDuration) {

		AjaxJson j = new AjaxJson();
		//当前选择的编码器可用
		message = "true";
		if (StringUtil.isNotEmpty(confCodecInfo.getId())) {
			confCodecInfo = confCodecInfoService.getEntity(ConfCodecInfoEntity.class, confCodecInfo.getId());
			List<ConfCodecInfoEntity> unavailable = confCodecInfoService.getUNAvailableCodecs(meetingType, appointmentStarttime, appointmentDuration);
			if(null != unavailable && unavailable.size() > 0 && unavailable.contains(confCodecInfo)){
				//当前选择的编码器不可用
				message = "false";
			}
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 谁用了当前编码器
	 * @param confCodecInfo
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "whouesed")
	public ModelAndView whouesed(ConfCodecInfoEntity confCodecInfo, HttpServletRequest req, String meetingType, String excepts, String appointmentStarttime, String appointmentDuration) throws Exception {
		if (StringUtil.isNotEmpty(confCodecInfo.getId())) {
			//当前选择的编码器
			confCodecInfo = confCodecInfoService.getEntity(ConfCodecInfoEntity.class, confCodecInfo.getId());
			//按照会议类型查询所有冲突的编码器
//			List<ConfCodecInfoEntity> allUnAvailable = confCodecInfoService.getUNAvailableCodecs(meetingType, appointmentStarttime, appointmentDuration);
			//按照会议类型查询所有冲突的会议
			if(SystemType.APP_MEETING_TYPE_1.equals(meetingType)){
				//------直播会议部分
				List<MeetingInfoEntity> meetings = systemService.loadAll(MeetingInfoEntity.class);
				List<MeetingInfoEntity> result = new ArrayList<MeetingInfoEntity>();
				for(MeetingInfoEntity m : meetings){
					Integer state = m.getMeetingstate();
					//当有直播会议冲突时
					if(state.toString().equals(SystemType.MEETING_STATE_1) ||
							state.toString().equals(SystemType.MEETING_STATE_2) ||
							state.toString().equals(SystemType.MEETING_STATE_3)){
						//获取该会议的所有编码器
						List<ConfCodecInfoEntity> codecs = meetingInfoService.getCodecs(m);
						//当该会议占用当前被选择的编码器时记录该会议
						if(codecs.contains(confCodecInfo)){
							result.add(m);
						}
						req.setAttribute("conflictMeetings", result);
					}
				}
				//------直播培训部分
				
				
			}else if(SystemType.APP_MEETING_TYPE_3.equals(meetingType) && StringUtil.isNotEmpty(appointmentStarttime) && StringUtil.isNotEmpty(appointmentDuration)){
				//------预约会议部分
				List<AppointmentMeetingInfoEntity> result = new ArrayList<AppointmentMeetingInfoEntity>();
				//所有启用的预约会议
				List<AppointmentMeetingInfoEntity> apps = systemService.findByProperty(AppointmentMeetingInfoEntity.class, "appointmentState", Integer.valueOf(SystemType.APP_MEETING_STATE_1));
				List<AppointmentMeetingInfoEntity> temp = new ArrayList<AppointmentMeetingInfoEntity>();
				Date appointmentbegintime = DataUtils.str2Date(appointmentStarttime, DataUtils.datetimeFormat);
				//当前预约会议结束时间
				Date appointmentendtime = DataUtils.getDate(appointmentbegintime.getTime() + new Integer(appointmentDuration).intValue() * 60 * 1000);
				for(AppointmentMeetingInfoEntity a : apps){
					Date start = a.getAppointmentStarttime(), end = DataUtils.getDate(a.getAppointmentStarttime().getTime() + (a.getAppointmentDuration()) * 60 * 1000);
					if(start.after(appointmentendtime) || end.before(appointmentbegintime)){
						temp.add(a);
					}
				}
				apps.removeAll(temp);
				//循环查找所有冲突的预约会议
				for(AppointmentMeetingInfoEntity a : apps){
					//获取当前循次预约会议的所有编码器
					List<ConfCodecInfoEntity> codecs = confCodecInfoService.getCodecs(a);
					if(codecs.contains(confCodecInfo)){
						result.add(a);
					}
				}
				
				//------预约培训部分
				
				req.setAttribute("conflictMeetings", result);
			}
		}
		
		return new ModelAndView("vod/meetinginfo/conflictMeetingInfo");
	}
}
