package vod.service.meetinglivesession;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.meetinglivesession.MeetingLiveSessionEntity;

public interface MeetingLiveSessionServiceI extends CommonService{

	/**
	 * 根据直播会议ID创建Session并返回
	 * @param meetingId
	 * @return
	 */
	public MeetingLiveSessionEntity create(String meetingId);
}
