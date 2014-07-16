package vod.service.vodsession;

import org.jeecgframework.core.common.service.CommonService;

public interface VodSessionServiceI extends CommonService{

	/**
	 * 根据直播会议id生成点播session信息
	 * @param meetingId
	 */
	public void getSessionByMeetingId(String meetingId);
}
