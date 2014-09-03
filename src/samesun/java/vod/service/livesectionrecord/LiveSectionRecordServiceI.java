package vod.service.livesectionrecord;

public interface LiveSectionRecordServiceI{

	public String StartChannelSectionRecord(Object o, String entityName) throws Exception;
	
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
	 * 结束直播前的结束录制功能(不涉及直播状态的改变)
	 * @param meetingId
	 * @return
	 */
	public String EndChannelSectionRecord4StopLive(Object o, String entityName);
	
	/**
	 * 结束录制
	 * @param meetingId
	 * @return
	 */
	public String EndChannelSectionRecord(Object o, String entityName);
}
