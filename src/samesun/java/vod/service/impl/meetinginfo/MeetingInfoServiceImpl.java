package vod.service.impl.meetinginfo;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.samesun.util.SystemType;
import vod.service.meetinginfo.MeetingInfoServiceI;

@Service("meetingInfoService")
@Transactional
public class MeetingInfoServiceImpl extends CommonServiceImpl implements MeetingInfoServiceI {
	
	@Override
	public MeetingInfoEntity getMeetingInfoFromAppointment(
			AppointmentMeetingInfoEntity app) {
		MeetingInfoEntity meeting = new MeetingInfoEntity();
		//复制会议信息
		meeting.setBillid(app.getId());		//预约会议ID
		meeting.setBillstarttime(DataUtils.str2Date(DataUtils.datetimeFormat.format(DataUtils.getDate()), DataUtils.datetimeFormat));
		meeting.setIsasflive(new Integer(SystemType.LIVE_TYPE_1));
		meeting.setIsrecord(app.getIsRecord());
		meeting.setCompere(app.getCompere());
		meeting.setIntroduction(app.getIntroduction());
		meeting.setSubject(app.getSubject());
		meeting.setTypeid(app.getTypeid());
		meeting.setMeetingstate(new Integer(SystemType.MEETING_STATE_1));
		return meeting;
	}

	@Override
	public boolean isFinish(String id) {
		if(StringUtil.isNotEmpty(id)){
			MeetingInfoEntity meeting = get(MeetingInfoEntity.class, id);
			if(meeting.getMeetingstate().equals(SystemType.MEETING_STATE_3)){
				return true;
			}
		}
		return false;
	}
	
}