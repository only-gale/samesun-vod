package vod.service.confcodecinfo;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;

public interface ConfCodecInfoServiceI extends CommonService{

	/**
	 * 获取所有编码器， 根据excepts过滤
	 * @param meetingType
	 * @param excepts	逗号分隔的已选编码器id串
	 * @param appointmentStarttime
	 * @param appointmentDuration
	 * @return
	 */
	public List<ConfCodecInfoEntity> combox(String meetingType, String excepts, String appointmentStarttime, String appointmentDuration);
	
	/**
	 * 获取当前登陆人可查看的所有编码器
	 * @param meetingType
	 * @param excepts
	 * @param appointmentStarttime
	 * @param appointmentDuration
	 * @return
	 */
	public List<ConfCodecInfoEntity> combox4UserCodec(String meetingType, String excepts, String appointmentStarttime, String appointmentDuration);
	
	/**
	 * 获取预约时段内可用的编码器，缺少组织结构过滤
	 * @param meetingType
	 * @param appointmentStarttime
	 * @param appointmentDuration
	 * @return
	 */
	public List<ConfCodecInfoEntity> getAvailableCodecs(String meetingType, String appointmentStarttime, String appointmentDuration);
	
	/**
	 * 获取预约时段内当前登陆人可查看但已被占用的编码器
	 * @param meetingType
	 * @param appointmentStarttime
	 * @param appointmentDuration
	 * @return
	 */
	public List<ConfCodecInfoEntity> getUNAvailableCodecs(String meetingType, String appointmentStarttime, String appointmentDuration);
	
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
