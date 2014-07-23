package vod.service.appointmentchannelinfo;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.appointmenttraining.AppointmentTrainingEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.entity.traininginfo.TrainingInfoEntity;

public interface AppointmentChannelInfoServiceI extends CommonService{

	/**
	 * 删除所有meetingId为空的预约频道信息
	 */
	public void delAllNullMeetingId();
	
	/**
	 * 用于当预约会议启用时将相关频道信息关联到直播会议信息中
	 * @param app
	 * @param meeting
	 */
	public void linkChannel(AppointmentMeetingInfoEntity from, MeetingInfoEntity to);
	
	public void linkChannel(AppointmentTrainingEntity from,
			TrainingInfoEntity to);
	
	/**
	 * 设置指定频道主备编码器是否启用
	 * @param state
	 */
	public void linkCodec(AppointmentChannelInfoEntity channel, String state);
}
