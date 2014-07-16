package vod.service.vodsectionrecord;

import org.jeecgframework.core.common.service.CommonService;

public interface VodSectionRecordServiceI extends CommonService{

	/**
	 * 根据直播会议id生成点播明细信息
	 * @param meetingId
	 */
	public void getVodSectionRecordByMeetingId(String meetingId);
}
