package vod.service.impl.vodsession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.entity.meetinglivesession.MeetingLiveSessionEntity;
import vod.entity.vodsession.VodSessionEntity;
import vod.samesun.util.LiveSessionComparator;
import vod.service.meetinginfo.MeetingInfoServiceI;
import vod.service.meetinglivesession.MeetingLiveSessionServiceI;
import vod.service.vodsession.VodSessionServiceI;

@Service("vodSessionService")
@Transactional
public class VodSessionServiceImpl extends CommonServiceImpl implements VodSessionServiceI {

	@Autowired
	private MeetingInfoServiceI meetingInfoService;
	
	@Autowired
	private MeetingLiveSessionServiceI meetingLiveSessionService;
	
	@Override
	public void getSessionByMeetingId(String meetingId) {
		if(StringUtil.isNotEmpty(meetingId)){
			MeetingInfoEntity meeting = meetingInfoService.get(MeetingInfoEntity.class, meetingId);
			if(null != meeting){
				List<MeetingLiveSessionEntity> liveSessions = meetingLiveSessionService.findByProperty(MeetingLiveSessionEntity.class, "meetingid", meetingId);
				Collections.sort(liveSessions, new LiveSessionComparator());
				int total = liveSessions.size(), index = 1;
				List<VodSessionEntity> vodSession = new ArrayList<VodSessionEntity>();
				for(MeetingLiveSessionEntity s : liveSessions){
					VodSessionEntity vs = new VodSessionEntity();
					vs.setLiveSession(s.getId());
					vs.setMeetingid(meetingId);
					vs.setTypeid(meeting.getTypeid());
					if(total == 1){
						vs.setSubject(meeting.getSubject());
					}else{
						vs.setSubject(meeting.getSubject() + "_" + index++);
					}
					vs.setCompere(meeting.getCompere());
					vs.setIntroduction(meeting.getIntroduction());
					vs.setBegindt(s.getBegindt());
					vs.setEnddt(s.getEnddt());
					
					vodSession.add(vs);
				}
				this.batchSave(vodSession);
			}
		}
		
	}
	
}