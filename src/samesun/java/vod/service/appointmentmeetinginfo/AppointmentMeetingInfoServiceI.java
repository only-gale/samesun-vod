package vod.service.appointmentmeetinginfo;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.appointmenttraining.AppointmentTrainingEntity;

public interface AppointmentMeetingInfoServiceI extends CommonService{

	public List<AppointmentMeetingInfoEntity> getAppsByState(String state);
	
	/**
	 * 是否录制
	 * @param app
	 * @return
	 */
	public String isRecord(AppointmentMeetingInfoEntity app);
	
	public String isRecord(AppointmentTrainingEntity app);
	
	/**
	 * 检查当前预约会议是否到时
	 * @param app
	 * @return
	 */
	public boolean checkTime(AppointmentMeetingInfoEntity app);
}
