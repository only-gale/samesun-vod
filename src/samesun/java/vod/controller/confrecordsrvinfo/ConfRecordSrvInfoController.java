package vod.controller.confrecordsrvinfo;

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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.confrecordsrvinfo.ConfRecordSrvInfoEntity;
import vod.entity.confrtspsrvinfo.ConfRtspSrvInfoEntity;
import vod.entity.recordrtspsrv.ConfRecordRtspSrvEntity;
import vod.page.confrecordsrvinfo.ConfRecordSrvInfoPage;
import vod.samesun.util.RTSPComparator;

/**   
 * @Title: Controller
 * @Description: 录制服务器
 * @author zhangdaihao
 * @date 2014-07-11 14:06:46
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/confRecordSrvInfoController")
public class ConfRecordSrvInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ConfRecordSrvInfoController.class);

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
	 * 录制服务器列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "confRecordSrvInfo")
	public ModelAndView confRecordSrvInfo(HttpServletRequest request) {
		return new ModelAndView("vod/confrecordsrvinfo/confRecordSrvInfoList");
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
	public void datagrid(ConfRecordSrvInfoEntity confRecordSrvInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConfRecordSrvInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, confRecordSrvInfo, request.getParameterMap());
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除录制服务器
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ConfRecordSrvInfoEntity confRecordSrvInfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		confRecordSrvInfo = systemService.getEntity(ConfRecordSrvInfoEntity.class, confRecordSrvInfo.getId());
		message = "录制服务器删除成功";
		systemService.delete(confRecordSrvInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加录制服务器
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ConfRecordSrvInfoEntity confRecordSrvInfo, HttpServletRequest request, String rtsp) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(confRecordSrvInfo.getId())) {
			message = "录制服务器更新成功";
			ConfRecordSrvInfoEntity t = systemService.get(ConfRecordSrvInfoEntity.class, confRecordSrvInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(confRecordSrvInfo, t);
				systemService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				
				ConfRecordRtspSrvEntity crr = systemService.get(ConfRecordRtspSrvEntity.class, t.getRr());
				if(crr != null && StringUtil.isNotEmpty(rtsp) && !rtsp.equals(crr.getRtspsrvid())){
					crr.setRtspsrvid(rtsp);
					systemService.updateEntitie(crr);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				message = "录制服务器更新失败";
			}
		} else {
			message = "录制服务器添加成功";
			systemService.save(confRecordSrvInfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			
			ConfRecordRtspSrvEntity rr = new ConfRecordRtspSrvEntity();
			rr.setRtspsrvid(rtsp);
			rr.setRecordsrvid(confRecordSrvInfo.getId());
			systemService.save(rr);
			
			confRecordSrvInfo.setRr(rr.getId());
			systemService.updateEntitie(confRecordSrvInfo);
		}
		
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 录制服务器列表页面跳转
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ConfRecordSrvInfoEntity confRecordSrvInfo, HttpServletRequest req) throws Exception {
		if (StringUtil.isNotEmpty(confRecordSrvInfo.getId())) {
			confRecordSrvInfo = systemService.getEntity(ConfRecordSrvInfoEntity.class, confRecordSrvInfo.getId());
			ConfRecordSrvInfoPage page = new ConfRecordSrvInfoPage();
			MyBeanUtils.copyBeanNotNull2Bean(confRecordSrvInfo, page);
			ConfRecordRtspSrvEntity rr = systemService.get(ConfRecordRtspSrvEntity.class, confRecordSrvInfo.getRr());
			page.setRtsp(rr.getRtspsrvid());
			req.setAttribute("confRecordSrvInfoPage", page);
		}
		List<ConfRtspSrvInfoEntity> rtsps = systemService.loadAll(ConfRtspSrvInfoEntity.class);
		Collections.sort(rtsps, new RTSPComparator());
		req.setAttribute("rtsps", rtsps);
		return new ModelAndView("vod/confrecordsrvinfo/confRecordSrvInfo");
	}
}
