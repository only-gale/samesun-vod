package vod.controller.appointmentchannelinfo;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.authoritygroup.AuthorityGroupEntity;
import vod.entity.authorityusergroup.AuthorityUserGroupEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.page.appointmentchannelinfo.AppointmentChannelInfoPage;
import vod.service.appointmentchannelinfo.AppointmentChannelInfoServiceI;
import vod.service.authoritygroup.AuthorityGroupServiceI;
import vod.service.authorityusergroup.AuthorityUserGroupServiceI;
import vod.service.confcodecinfo.ConfCodecInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 预约频道信息
 * @author zhangdaihao
 * @date 2014-06-20 12:00:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/appointmentChannelInfoController")
public class AppointmentChannelInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AppointmentChannelInfoController.class);

	@Autowired
	private AppointmentChannelInfoServiceI appointmentChannelInfoService;
	@Autowired
	private ConfCodecInfoServiceI confCodecInfoService;
	@Autowired
	private AuthorityGroupServiceI authorityGroupService;
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
	 * 预约频道信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "appointmentChannelInfo")
	public ModelAndView appointmentChannelInfo(HttpServletRequest request) {
		return new ModelAndView("vod/appointmentchannelinfo/appointmentChannelInfoList");
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
	public void datagrid(AppointmentChannelInfoEntity appointmentChannelInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) throws Exception {
		//先删除临时数据(即创建时间和删除时间相同)
		appointmentChannelInfoService.delAllNullMeetingId();
		
		dataGrid.setSort("createDate");
		
		String appointmentid = request.getParameter("appointmentid"), meetingid = request.getParameter("meetingid");
		if(StringUtil.isNotEmpty(meetingid)){
			appointmentChannelInfo.setMeetingid(meetingid);
		}
		if(StringUtil.isNotEmpty(appointmentid)){
			appointmentChannelInfo.setAppointmentid(appointmentid);
		}
		AppointmentChannelInfoPage page = new AppointmentChannelInfoPage();
		MyBeanUtils.copyBeanNotNull2Bean(appointmentChannelInfo, page);
		
		//设置dataGrid需要显示的字段
		Field[] fields = AppointmentChannelInfoPage.class.getDeclaredFields();
		String fieldStr = "";
		for(Field f : fields){
			fieldStr += ("," + f.getName());
		}
		dataGrid.setField(fieldStr.substring(1));
		
		CriteriaQuery cq = new CriteriaQuery(AppointmentChannelInfoEntity.class, dataGrid);
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, appointmentChannelInfo, request.getParameterMap());
		this.appointmentChannelInfoService.getDataGridReturn(cq, true);
		List<AppointmentChannelInfoEntity> list = dataGrid.getResults();
		List<AppointmentChannelInfoPage> result = new ArrayList<AppointmentChannelInfoPage>();
		for(AppointmentChannelInfoEntity e : list){
			//预约id
			String appid = e.getAppointmentid();
			//会议id
			String id = e.getMeetingid();
			//至少有一个不为空才算有效频道,事实上,不会同时为空,所以不会有丢失数据的情况
			if((StringUtil.isNotEmpty(appid) && appid.equals(appointmentid)) || (StringUtil.isNotEmpty(id) && id.equals(meetingid))){
				AppointmentChannelInfoPage p = new AppointmentChannelInfoPage();
				MyBeanUtils.copyBean2Bean(p, e);
				String codec1id = e.getCodec1id(), codec2id = e.getCodec2id(), authortiyGroupCid = e.getAuthortiyGroupCid(), authortiyUsergroupCid = e.getAuthortiyUsergroupCid();
				if(StringUtil.isNotEmpty(codec1id)){
					p.setCodec1name(((ConfCodecInfoEntity)confCodecInfoService.getEntity(ConfCodecInfoEntity.class, codec1id)).getName());
				}
				if(StringUtil.isNotEmpty(codec2id)){
					p.setCodec2name(((ConfCodecInfoEntity)confCodecInfoService.getEntity(ConfCodecInfoEntity.class, codec2id)).getName());
				}
				if(StringUtil.isNotEmpty(authortiyGroupCid)){
					
					p.setAuthortiyGroupCname(((AuthorityGroupEntity)authorityGroupService.getEntity(AuthorityGroupEntity.class, authortiyGroupCid)).getName());
				}
				if(StringUtil.isNotEmpty(authortiyUsergroupCid)){
					
					p.setAuthortiyUsergroupCname(((AuthorityUserGroupEntity)authorityUserGroupService.getEntity(AuthorityUserGroupEntity.class, authortiyUsergroupCid)).getName());
				}
				result.add(p);
			}
		}
		dataGrid.setResults(result);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除预约频道信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AppointmentChannelInfoEntity appointmentChannelInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		appointmentChannelInfo = systemService.getEntity(AppointmentChannelInfoEntity.class, appointmentChannelInfo.getId());
		message = "预约频道信息删除成功";
		appointmentChannelInfoService.delete(appointmentChannelInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加预约频道信息
	 * 
	 * @param ids
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AppointmentChannelInfoEntity appointmentChannelInfo, HttpServletRequest request) throws ParseException {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(appointmentChannelInfo.getId())) {
			message = "预约频道信息更新成功";
			logger.info(message);
			AppointmentChannelInfoEntity t = appointmentChannelInfoService.get(AppointmentChannelInfoEntity.class, appointmentChannelInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(appointmentChannelInfo, t);
				appointmentChannelInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "预约频道信息更新失败";
				logger.info(message);
			}
		} else {
			message = "预约频道信息添加成功";
			logger.info(message);
			appointmentChannelInfo.setMeetingid(request.getParameter("meetingId"));
			appointmentChannelInfo.setAppointmentid(request.getParameter("appointmentId"));
			Date createDate = DataUtils.datetimeFormat.parse(DataUtils.getDataString(DataUtils.datetimeFormat));
			appointmentChannelInfo.setCreateDate(createDate);
			//设置删除时间与创建时间相同，以表示增加的是一个临时频道
			appointmentChannelInfo.setDelDate(createDate);
			
			appointmentChannelInfoService.save(appointmentChannelInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 预约频道信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AppointmentChannelInfoEntity appointmentChannelInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(appointmentChannelInfo.getId())) {
			appointmentChannelInfo = appointmentChannelInfoService.getEntity(AppointmentChannelInfoEntity.class, appointmentChannelInfo.getId());
			req.setAttribute("appointmentChannelInfoPage", appointmentChannelInfo);
		}
		return new ModelAndView("vod/appointmentchannelinfo/appointmentChannelInfo");
	}
}
