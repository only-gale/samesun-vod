package vod.controller.heartrequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.heartrequest.HeartRequestEntity;
import vod.entity.terminalinfo.TerminalInfoEntity;
import vod.page.terminalinfo.TerminalInfoPage;
import vod.samesun.util.SystemType;
import vod.service.heartrequest.HeartRequestServiceI;
import vod.service.meetinginfo.MeetingInfoServiceI;
import vod.service.terminalinfo.TerminalInfoServiceI;

/**   
 * @Title: Controller
 * @Description: 心跳信息
 * @author zhangdaihao
 * @date 2014-07-21 13:06:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/heartRequestController")
public class HeartRequestController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HeartRequestController.class);

	@Autowired
	private HeartRequestServiceI heartRequestService;
	@Autowired
	private TerminalInfoServiceI terminalInfoService;
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
	 * 心跳信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "heartRequest")
	public ModelAndView heartRequest(HttpServletRequest request) {
		return new ModelAndView("vod/heartrequest/heartRequestList");
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
	public void datagrid(HeartRequestEntity heartRequest,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HeartRequestEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, heartRequest, request.getParameterMap());
		this.heartRequestService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除心跳信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(HeartRequestEntity heartRequest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		heartRequest = systemService.getEntity(HeartRequestEntity.class, heartRequest.getId());
		message = "心跳信息删除成功";
		heartRequestService.delete(heartRequest);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加心跳信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HeartRequestEntity heartRequest, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(heartRequest.getId())) {
			message = "心跳信息更新成功";
			HeartRequestEntity t = heartRequestService.get(HeartRequestEntity.class, heartRequest.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(heartRequest, t);
				heartRequestService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "心跳信息更新失败";
			}
		} else {
			message = "心跳信息添加成功";
			heartRequestService.save(heartRequest);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 心跳信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(HeartRequestEntity heartRequest, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(heartRequest.getId())) {
			heartRequest = heartRequestService.getEntity(HeartRequestEntity.class, heartRequest.getId());
			req.setAttribute("heartRequestPage", heartRequest);
		}
		return new ModelAndView("vod/heartrequest/heartRequest");
	}
	
	/**
	 * 心跳信息注册页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "toRegiste")
	public ModelAndView toRegiste(TerminalInfoEntity terminal, HttpServletRequest req) {
		TerminalInfoPage page = new TerminalInfoPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(terminal, page);
		} catch (Exception e) {
			logger.error("获取心跳信息错误");
			e.printStackTrace();
		}
		
		req.setAttribute("terminalInfoPage", page);
		return new ModelAndView("vod/heartrequest/heartRequest");
	}
	
	/**
	 * 注册
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "registe")
	@ResponseBody
	public AjaxJson registe(TerminalInfoEntity terminalInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		message = "注册成功";
		//获取异常心跳信息id
		String heartRequestId = terminalInfo.getId();
		terminalInfo.setId(null);
		terminalInfoService.save(terminalInfo);
		//注册成功后删除异常心跳信息
		heartRequestService.delete(heartRequestService.get(HeartRequestEntity.class, heartRequestId));
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(params = "heartBeat")
	public void heartBeat(HeartRequestEntity heartRequest, HttpServletRequest request, HttpServletResponse response,String ip, String mac, String playid, String live){
		PrintWriter write = null;
		try {
			heartRequest.setIpaddress(ip);
			heartRequest.setMacaddress(mac);
			response.setContentType("text/xml;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String strXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> ";
			if(StringUtil.isEmpty(playid)){
				playid = "";
			}
			String meetingid = playid.split("_")[0];
			if(heartRequestService.hearBeatTask(heartRequest, meetingid, live)){
				strXML +="<state>success</state>";
			}else{
				strXML +="<state>fault</state>";
			}
			
			//当直播结束时
			if(SystemType.LIVE_TYPE_1.equals(live) && meetingInfoService.isFinish(meetingid)){
				strXML += "<state>finish</state>";
			}
			
			write = response.getWriter();
			write.write(strXML);
			write.flush();
			
		} catch (UnsupportedEncodingException e) {
			logger.info("不支持UTF-8编码格式,请联系技术支持人员");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("获取输出流失败,请联系技术支持人员");
			e.printStackTrace();
		} finally{
			if(null != write){
				write.close();
			}
		}
		
	}
	
	@RequestMapping(params = "epg")
	public void getEPG(HeartRequestEntity heartRequest, HttpServletRequest request, HttpServletResponse response, String mac){
		PrintWriter write = null;
		try {
			response.setContentType("text/xml;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String epg = "";
			if(StringUtil.isNotEmpty(mac)){
				epg = heartRequestService.getEPG(mac);
			}
			write = response.getWriter();
			write.write(epg);
			write.flush();
		} catch (UnsupportedEncodingException e) {
			logger.info("不支持UTF-8编码格式,请联系技术支持人员");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("获取输出流失败,请联系技术支持人员");
			e.printStackTrace();
		}finally{
			if(null != write){
				write.close();
			}
		}
	}
}
