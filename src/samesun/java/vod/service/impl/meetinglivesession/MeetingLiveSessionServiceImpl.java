package vod.service.impl.meetinglivesession;

import java.text.ParseException;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DataUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.meetinglivesession.MeetingLiveSessionEntity;
import vod.service.meetinglivesession.MeetingLiveSessionServiceI;

@Service("meetingLiveSessionService")
@Transactional
public class MeetingLiveSessionServiceImpl extends CommonServiceImpl implements MeetingLiveSessionServiceI {

	@Override
	public MeetingLiveSessionEntity create(String meetingId) {
		MeetingLiveSessionEntity liveSession = new MeetingLiveSessionEntity();
		try {
			liveSession.setMeetingid(meetingId);
			liveSession.setBegindt(DataUtils.parseDate(DataUtils.datetimeFormat.format(DataUtils.getDate()), DataUtils.datetimeFormat.toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		save(liveSession);
		return liveSession;
	}
	
}