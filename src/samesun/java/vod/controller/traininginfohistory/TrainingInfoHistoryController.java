package vod.controller.traininginfohistory;

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
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vod.entity.meetinghistory.MeetingHistoryEntity;
import vod.entity.traininginfohistory.TrainingInfoHistoryEntity;
import vod.page.meetinghistory.MeetingHistoryPage;
import vod.samesun.util.SystemType;
import vod.service.traininginfohistory.TrainingInfoHistoryServiceI;

/**   
 * @Title: Controller
 * @Description: 培训日志
 * @author zhangdaihao
 * @date 2014-07-23 10:45:57
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/trainingInfoHistoryController")
public class TrainingInfoHistoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TrainingInfoHistoryController.class);

	@Autowired
	private TrainingInfoHistoryServiceI trainingInfoHistoryService;
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
	 * 培训日志列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "trainingInfoHistory")
	public ModelAndView trainingInfoHistory(HttpServletRequest request) {
		return new ModelAndView("vod/traininginfohistory/trainingInfoHistoryList");
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
	public void datagrid(MeetingHistoryEntity meetingHistory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MeetingHistoryEntity.class, dataGrid);
		//查询培训历史
		cq.eq("rightid", SystemType.MEETING_RIGHT_2);
		cq.add();
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, meetingHistory, request.getParameterMap());
		this.trainingInfoHistoryService.getDataGridReturn(cq, true);
		List<MeetingHistoryEntity> meetings = new ArrayList<MeetingHistoryEntity>();
		List<MeetingHistoryPage> temp = new ArrayList<MeetingHistoryPage>();
		meetings = dataGrid.getResults();
		for(MeetingHistoryEntity h : meetings){
			MeetingHistoryPage page = new MeetingHistoryPage();
			try {
				MyBeanUtils.copyBeanNotNull2Bean(h, page);
			} catch (Exception e) {
				logger.error("获取培训历史信息错误");
				e.printStackTrace();
			}
			page.setTypename(systemService.getType(page.getTypeid().toString(), SystemType.TRAINING_TYPE).getTypename());
			temp.add(page);
		}
		dataGrid.setResults(temp);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除培训日志
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TrainingInfoHistoryEntity trainingInfoHistory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		trainingInfoHistory = systemService.getEntity(TrainingInfoHistoryEntity.class, trainingInfoHistory.getId());
		message = "培训日志删除成功";
		trainingInfoHistoryService.delete(trainingInfoHistory);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加培训日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TrainingInfoHistoryEntity trainingInfoHistory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(trainingInfoHistory.getId())) {
			message = "培训日志更新成功";
			TrainingInfoHistoryEntity t = trainingInfoHistoryService.get(TrainingInfoHistoryEntity.class, trainingInfoHistory.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(trainingInfoHistory, t);
				trainingInfoHistoryService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "培训日志更新失败";
			}
		} else {
			message = "培训日志添加成功";
			trainingInfoHistoryService.save(trainingInfoHistory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 培训日志列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TrainingInfoHistoryEntity trainingInfoHistory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(trainingInfoHistory.getId())) {
			trainingInfoHistory = trainingInfoHistoryService.getEntity(TrainingInfoHistoryEntity.class, trainingInfoHistory.getId());
			req.setAttribute("trainingInfoHistoryPage", trainingInfoHistory);
		}
		return new ModelAndView("vod/traininginfohistory/trainingInfoHistory");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "exportXls")
	public void exportXls(MeetingHistoryEntity history, HttpServletRequest request, HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "培训日志";
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
			CriteriaQuery cq = new CriteriaQuery(MeetingHistoryEntity.class, dataGrid);
			//查询培训历史
			cq.eq("rightid", SystemType.MEETING_RIGHT_2);
			cq.add();
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, history, request.getParameterMap());
			
			List<MeetingHistoryEntity> courses = systemService.getListByCriteriaQuery(cq,false);
			List<MeetingHistoryPage> pages = new ArrayList<MeetingHistoryPage>();
			//转换代码为汉字
			for(MeetingHistoryEntity t : courses){
				MeetingHistoryPage p = new MeetingHistoryPage();
				MyBeanUtils.copyBeanNotNull2Bean(t, p);
				
				//是否录制
				TSType isrecord = systemService.getType(p.getIsrecord() == null ? null : p.getIsrecord().toString(), SystemType.IS_RECORD_TYPE);
				if(isrecord != null){
					p.setIsrecordname(isrecord.getTypename());
				}
				
				//所属类型
				TSType type = systemService.getType(p.getTypeid() == null ? null : p.getTypeid().toString(), SystemType.TRAINING_TYPE);
				if(type != null){
					p.setTypename(type.getTypename());
				}
				
				pages.add(p);
			}
			
			TSUser user = ResourceUtil.getSessionUserName();
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("培训日志", "导出人:" + user.getRealName(),
					"导出信息"), MeetingHistoryPage.class, pages);
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
