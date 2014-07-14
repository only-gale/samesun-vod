package vod.service.appointmentmeetinginfo;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;

public interface AppointmentMeetingInfoServiceI extends CommonService{

	public List<AppointmentMeetingInfoEntity> getAppsByState(String state);
	
	/**
	 * 是否录制
	 * @param app
	 * @return
	 */
	public String isRecord(AppointmentMeetingInfoEntity app);
}
