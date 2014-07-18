package vod.service.livesectionrecord;

public interface LiveSectionRecordServiceI{

	public String StartChannelSectionRecord(String meetingId) throws Exception;
	
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
