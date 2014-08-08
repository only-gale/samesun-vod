package vod.controller.vodsession;

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
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.vodsectionrecord.VodSectionRecordEntity;
import vod.entity.vodsession.VodSessionEntity;
import vod.page.vodsession.VodSessionPage;
import vod.samesun.util.SystemType;
import vod.service.vodsession.VodSessionServiceI;

/**   
 * @Title: Controller
 * @Description: 点播信息会话
 * @author zhangdaihao
 * @date 2014-07-15 17:46:55
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/vodSessionController")
public class VodSessionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(VodSessionController.class);

	@Autowired
	private VodSessionServiceI vodSessionService;
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
	 * 点播信息会话列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "vodSession")
	public ModelAndView vodSession(HttpServletRequest request) {
		return new ModelAndView("vod/vodsession/vodSessionList");
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
	public void datagrid(VodSessionEntity vodSession,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		dataGrid.setSort("begindt");
		dataGrid.setOrder(SortDirection.desc);
		CriteriaQuery cq = new CriteriaQuery(VodSessionEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, vodSession, request.getParameterMap());
		this.vodSessionService.getDataGridReturn(cq, true);
		List<VodSessionEntity> sessions = dataGrid.getResults();
		List<VodSessionPage> pages = new ArrayList<VodSessionPage>();
		if(sessions != null && sessions.size() > 0){
			for(VodSessionEntity e : sessions){
				VodSessionPage page = new VodSessionPage();
				try {
					MyBeanUtils.copyBeanNotNull2Bean(e, page);
				} catch (Exception e1) {
					logger.info("获取点播会话错误");
					e1.printStackTrace();
				}
				Date begin = page.getBegindt();
				Date end = page.getEnddt();
				if(begin != null && end != null && hasdetail(e)){
					int time = (int) ((end.getTime() - begin.getTime()) / (60 * 1000));
					page.setDuration(time);
					pages.add(page);
				}
				
			}
		}
		dataGrid.setResults(pages);
		dataGrid.setTotal(pages.size());
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除点播信息会话
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(VodSessionEntity vodSession, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		vodSession = systemService.getEntity(VodSessionEntity.class, vodSession.getId());
		message = "点播信息会话删除成功";
		vodSessionService.delete(vodSession);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加点播信息会话
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(VodSessionEntity vodSession, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(vodSession.getId())) {
			message = "点播信息会话更新成功";
			VodSessionEntity t = vodSessionService.get(VodSessionEntity.class, vodSession.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(vodSession, t);
				vodSessionService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "点播信息会话更新失败";
			}
		} else {
			message = "点播信息会话添加成功";
			vodSessionService.save(vodSession);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 点播信息会话列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(VodSessionEntity vodSession, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(vodSession.getId())) {
			vodSession = vodSessionService.getEntity(VodSessionEntity.class, vodSession.getId());
			req.setAttribute("vodSessionPage", vodSession);
		}
		return new ModelAndView("vod/vodsession/vodSession");
	}
	
	/**
	 * 判断当前会话有没有详细录制信息，只要有完全成功的详细录制信息则返回true
	 * @param e
	 * @return
	 */
	private boolean hasdetail(VodSessionEntity e){
		boolean flag = false;
		List<VodSectionRecordEntity> vods = systemService.findByProperty(VodSectionRecordEntity.class, "sessionid", e.getLiveSession());
		for(VodSectionRecordEntity v : vods){
			if(Integer.valueOf(SystemType.REC_STATE_5) == v.getRecState()){
				flag = true;
			}
		}
		return flag;
	}
}
