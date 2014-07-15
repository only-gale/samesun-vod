package vod.service.livesectionrecord;

import org.jeecgframework.core.common.service.CommonService;

public interface LiveSectionRecordServiceI extends CommonService{

	public String StartChannelSectionRecord(String meetingId);
	
	/**
	 * 生成录制记录
	 * @param meetingId
	 * @param channelID
	 * @param sessionID
	 * @param Codec
	 * @param Priority
	 * @param fileName
	 * @return
	 */
	public String SectionRecord4DB(String meetingId, String channelID, String sessionID, String Codec, String Priority, String fileName);
	
	/**
	 * 结束录制
	 * @param meetingId
	 * @return
	 */
	public String EndChannelSectionRecord(String meetingId);
}
