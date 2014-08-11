package vod.service.meetinginfo;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.authoritygroup.AuthorityGroupEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
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
	
	public List<ConfCodecInfoEntity> getCodecs(MeetingInfoEntity e);
	
	/**
	 * 获取直播会议的所有终端分组(不重复)
	 * @param e
	 * @return
	 */
	public List<AuthorityGroupEntity> getGroups(MeetingInfoEntity e);
	
	/**
	 * 当预约会议转为直播会议时判断该直播会议所需资源有没有被占用
	 * 因为要在生成直播信息前验证
	 * 所以根据预约信息判断
	 * @param meeting
	 * @return
	 */
	public boolean wetherused(AppointmentMeetingInfoEntity app);
}
