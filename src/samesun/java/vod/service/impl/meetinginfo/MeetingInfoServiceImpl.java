package vod.service.impl.meetinginfo;

import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.samesun.util.SystemType;
import vod.service.livesectionrecord.LiveSectionRecordServiceI;
import vod.service.meetinginfo.MeetingInfoServiceI;

@Service("meetingInfoService")
@Transactional
public class MeetingInfoServiceImpl extends CommonServiceImpl implements MeetingInfoServiceI {
	
	@Autowired
	private LiveSectionRecordServiceI liveSectionRecordService;
	
	@Autowired
	private SystemService systemService;
	
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
	
	@Override
	public void autoRecordTask(){
		List<MeetingInfoEntity> meetings = systemService.loadAll(MeetingInfoEntity.class);
		for(MeetingInfoEntity m : meetings){
			Integer meetingState = m.getMeetingstate();
			Integer appState = m.getAppointmentstate();
			String appDate = m.getAppointmentdt();
			if(meetingState != Integer.valueOf(SystemType.MEETING_STATE_2) &&
					StringUtil.isNotEmpty(appDate) &&
					StringUtil.isNotEmpty(appState.toString()) &&
					appState == Integer.valueOf(SystemType.APP_RECORD_1)){
				//当预约录制时间不大于当前时间时开始录制
				if(!DataUtils.getDate().before(DataUtils.str2Date(appDate, DataUtils.datetimeFormat))){
					try {
						liveSectionRecordService.StartChannelSectionRecord(m, "MeetingInfoEntity");
						m.setAppointmentstate(Integer.valueOf(SystemType.APP_RECORD_3));
						systemService.updateEntitie(m);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public List<ConfCodecInfoEntity> getCodecs(MeetingInfoEntity e) {
		List<ConfCodecInfoEntity> result = new ArrayList<ConfCodecInfoEntity>();
		//根据会议id获取频道列表
		List<AppointmentChannelInfoEntity> channels = this.findByProperty(AppointmentChannelInfoEntity.class, "meetingid", e.getId());
		for(AppointmentChannelInfoEntity channel : channels){
			String codec1id = channel.getCodec1id(), codec2id = channel.getCodec2id();;
			//主编码器
			if(StringUtil.isNotEmpty(codec1id)){
				ConfCodecInfoEntity codec1 = (ConfCodecInfoEntity)this.getEntity(ConfCodecInfoEntity.class, codec1id);
				if(null != codec1 && !result.contains(codec1)){
					result.add(codec1);
				}
			}
			//备编码器
			if(StringUtil.isNotEmpty(codec2id)){
				ConfCodecInfoEntity codec2 = (ConfCodecInfoEntity)this.getEntity(ConfCodecInfoEntity.class, codec2id);
				if(null != codec2 && !result.contains(codec2)){
					result.add(codec2);
				}
			}
		}
		return result;
	}
}