package vod.service.meetinghistory;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.meetinginfo.MeetingInfoEntity;

public interface MeetingHistoryServiceI extends CommonService{

	/**
	 * 当结束直播时，生成会议历史信息
	 * @param live
	 */
	public void getHistoryFromLive(MeetingInfoEntity live);
}
