package vod.controller.terminalinfo;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.web.system.pojo.base.TSTerritory;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.authgroupterminal.AuthGroupTerminalEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.entity.terminalinfo.TerminalInfoEntity;
import vod.page.terminalinfo.TerminalInfoPage;
import vod.samesun.util.SystemType;
import vod.service.terminalinfo.TerminalInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 终端信息
 * @author zhangdaihao
 * @date 2014-07-11 18:52:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/terminalInfoController")
public class TerminalInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(TerminalInfoController.class);

	@Autowired
	private TerminalInfoServiceI terminalInfoService;
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
	 * 终端信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "terminalInfo")
	public ModelAndView terminalInfo(HttpServletRequest request) {
		return new ModelAndView("vod/terminalinfo/terminalInfoList");
	}
	
	/**
	 * 终端信息列表 页面跳转
	 * 查看状态
	 * @return
	 */
	@RequestMapping(params = "terminalStatus")
	public ModelAndView terminalStatus(HttpServletRequest request) {
		return new ModelAndView("vod/terminalinfo/terminalStatus");
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
	public void datagrid(TerminalInfoEntity terminalInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, String groupname) throws Exception {
		CriteriaQuery cq = new CriteriaQuery(TerminalInfoEntity.class, dataGrid);
		//查询条件组装器,fuzzy search
		String name = terminalInfo.getName();
		if(StringUtil.isNotEmpty(name)){
			terminalInfo.setName("*"+ name +"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, terminalInfo, request.getParameterMap());
		this.terminalInfoService.getDataGridReturn(cq, true);
		
		List<TerminalInfoEntity> terminals = dataGrid.getResults();
		List<TerminalInfoPage> results = new ArrayList<TerminalInfoPage>();
		//添加地理位置
		for(TerminalInfoEntity t : terminals){
			if(StringUtil.isNotEmpty(t.getGroupid())){
				List<TSTerritory> territories = systemService.findByProperty(TSTerritory.class, "id", t.getGroupid());
				TerminalInfoPage page = new TerminalInfoPage();
				MyBeanUtils.copyBeanNotNull2Bean(t, page);
				if(territories != null && territories.size() > 0){
					TSTerritory territofy = territories.get(0);
					page.setGroupname(territofy.getTerritoryName());
				}
				results.add(page);
			}
		}
		dataGrid.setResults(results);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除终端信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TerminalInfoEntity terminalInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		terminalInfo = systemService.getEntity(TerminalInfoEntity.class, terminalInfo.getId());
		message = "终端信息删除成功";
		terminalInfoService.delete(terminalInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加终端信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TerminalInfoEntity terminalInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(terminalInfo.getId())) {
			message = "终端信息更新成功";
			TerminalInfoEntity t = terminalInfoService.get(TerminalInfoEntity.class, terminalInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(terminalInfo, t);
				terminalInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "终端信息更新失败";
			}
		} else {
			message = "终端信息添加成功";
			terminalInfoService.save(terminalInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 终端信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TerminalInfoEntity terminalInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(terminalInfo.getId())) {
			terminalInfo = terminalInfoService.getEntity(TerminalInfoEntity.class, terminalInfo.getId());
			req.setAttribute("terminalInfoPage", terminalInfo);
		}
		return new ModelAndView("vod/terminalinfo/terminalInfo");
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
		List<ComboTree> comboTrees = systemService.ComboTree(list, comboTreeModel, null);
		return comboTrees;

	}
	
	/**
	 * 
	 * @param terminalInfo
	 * @param request
	 * @param response
	 * @param meetingType
	 * @param excepts
	 * @param appointmentStarttime
	 * @param appointmentDuration
	 * @return
	 */
	@RequestMapping(params = "wetheruesedterminal")
	@ResponseBody
	public AjaxJson wetheruesedterminal(String id, String meetingType, String excepts, String appointmentStarttime, String appointmentDuration) {

		AjaxJson j = new AjaxJson();
		//当前选择的终端分组可用
		message = "true";
		if (StringUtil.isNotEmpty(id)) {
			List<TerminalInfoEntity> terminals = terminalInfoService.getConflicts(id, meetingType, appointmentStarttime, appointmentDuration);
			if(null != terminals && terminals.size() > 0){
				//当前选择的终端分组不可用
				message = "false";
			}
		}
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params = "whouesed")
	public ModelAndView whouesed(HttpServletRequest req, String id, String meetingType, String excepts, String appointmentStarttime, String appointmentDuration){
		//获取改组当中所有冲突的终端列表
		List<TerminalInfoEntity> terminals = terminalInfoService.getConflicts(id, meetingType, appointmentStarttime, appointmentDuration);
		//直播会议
		if(SystemType.APP_MEETING_TYPE_1.equals(meetingType)){
			List<MeetingInfoEntity> meetings = new ArrayList<MeetingInfoEntity>();
			for(TerminalInfoEntity t : terminals){
				List<AuthGroupTerminalEntity> gts = systemService.findByProperty(AuthGroupTerminalEntity.class, "terminalid", t.getId());
				for(AuthGroupTerminalEntity agt : gts){
					//获取分组id
					String groupid = agt.getAuthid();
					if(StringUtil.isNotEmpty(groupid)){
						List<AppointmentChannelInfoEntity> channels = systemService.findByProperty(AppointmentChannelInfoEntity.class, "authortiyGroupCid", groupid);
						for(AppointmentChannelInfoEntity c : channels){
							String meetingid = c.getMeetingid();
							MeetingInfoEntity meeting = systemService.get(MeetingInfoEntity.class, meetingid);
							if(null != meeting && !meetings.contains(meeting) && Integer.valueOf(SystemType.MEETING_STATE_4) != meeting.getMeetingstate()){
								meetings.add(meeting);
							}else continue;
						}
					}else continue;
				}
			}
			req.setAttribute("conflictMeetings", meetings);
			
		}else if(SystemType.APP_MEETING_TYPE_3.equals(meetingType) && StringUtil.isNotEmpty(appointmentStarttime) && StringUtil.isNotEmpty(appointmentDuration)){		//预约会议
			List<AppointmentMeetingInfoEntity> meetings = new ArrayList<AppointmentMeetingInfoEntity>();
			for(TerminalInfoEntity t : terminals){
				List<AuthGroupTerminalEntity> gts = systemService.findByProperty(AuthGroupTerminalEntity.class, "terminalid", t.getId());
				for(AuthGroupTerminalEntity agt : gts){
					//获取分组id
					String groupid = agt.getAuthid();
					if(StringUtil.isNotEmpty(groupid)){
						List<AppointmentChannelInfoEntity> channels = systemService.findByProperty(AppointmentChannelInfoEntity.class, "authortiyGroupCid", groupid);
						for(AppointmentChannelInfoEntity c : channels){
							String meetingid = c.getAppointmentid();
							AppointmentMeetingInfoEntity meeting = systemService.get(AppointmentMeetingInfoEntity.class, meetingid);
							if(null != meeting && !meetings.contains(meeting)){
								meetings.add(meeting);
							}else continue;
						}
					}else continue;
				}
			}
			req.setAttribute("conflictMeetings", meetings);
		}
		return new ModelAndView("vod/meetinginfo/conflictMeetingInfo");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "exportXls")
	public void exportXls(TerminalInfoEntity terminalInfo, HttpServletRequest request, HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "终端信息";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(TerminalInfoEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, terminalInfo, request.getParameterMap());
			
			List<TerminalInfoEntity> courses = systemService.getListByCriteriaQuery(cq,false);
			TSUser user = ResourceUtil.getSessionUserName();
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("终端状态", "导出人:" + user.getRealName(),
					"导出信息"), TerminalInfoEntity.class, courses);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	
}
