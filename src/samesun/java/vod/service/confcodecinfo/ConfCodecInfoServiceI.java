package vod.service.confcodecinfo;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;

public interface ConfCodecInfoServiceI extends CommonService{

	/**
	 *  获取记录的下拉列表
	 * @return
	 */
	
	public List<ConfCodecInfoEntity> combox(String meetingType, String excepts, String appointmentStarttime, String appointmentDuration);
	
	public List<ConfCodecInfoEntity> getAvailableCodecs(String meetingType, String appointmentStarttime, String appointmentDuration);
	
	/**
	 * 根据预约会议获取所属编码器信息
	 * @param e
	 * @return
	 */
	public List<ConfCodecInfoEntity> getCodecs(AppointmentMeetingInfoEntity e);
	
	/**
	 * 根据直播会议Id获取所属编码器信息
	 * @param e
	 * @return
	 */
	public List<ConfCodecInfoEntity> getCodecsNeed2Record(MeetingInfoEntity meeting);
}
