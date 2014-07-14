package vod.service.livesectionrecord;

import org.jeecgframework.core.common.service.CommonService;

public interface LiveSectionRecordServiceI extends CommonService{

	public String StartChannelSectionRecord(String meetingId);
	
	public String SectionRecord4DB(String meetingId, String channelID, String sessionID, String Codec, String Priority);
}
