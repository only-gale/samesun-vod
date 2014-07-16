package vod.controller.confcodecinfo;
import java.io.IOException;
import java.util.ArrayList;
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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.samesun.util.ComboboxBean;
import vod.service.confcodecinfo.ConfCodecInfoServiceI;

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
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ConfCodecInfoController.class);

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
		CriteriaQuery cq = new CriteriaQuery(ConfCodecInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, confCodecInfo, request.getParameterMap());
		this.confCodecInfoService.getDataGridReturn(cq, true);
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
	public AjaxJson save(ConfCodecInfoEntity confCodecInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(confCodecInfo.getId())) {
			message = "编码器配置信息更新成功";
			ConfCodecInfoEntity t = confCodecInfoService.get(ConfCodecInfoEntity.class, confCodecInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(confCodecInfo, t);
				confCodecInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "编码器配置信息更新失败";
			}
		} else {
			message = "编码器配置信息添加成功";
			confCodecInfoService.save(confCodecInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 编码器配置信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ConfCodecInfoEntity confCodecInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(confCodecInfo.getId())) {
			confCodecInfo = confCodecInfoService.getEntity(ConfCodecInfoEntity.class, confCodecInfo.getId());
			req.setAttribute("confCodecInfoPage", confCodecInfo);
		}
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
			response.getWriter().write(json);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
