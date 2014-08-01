package vod.service.meetinginfo;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;

public interface MeetingInfoServiceI extends CommonService{

	/**
	 * 根据预约会议信息获取直播会议信息
	 * <br><font color="red">注: </font>当预约会议启用的时候，实际上是该预约会议已经变成了直播会议
	 * @param vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity
	 * @return
	 */
	public MeetingInfoEntity getMeetingInfoFromAppointment(AppointmentMeetingInfoEntity app);
	
	public boolean isFinish(String id);
	
	public void autoRecordTask();
}
