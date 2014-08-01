package vod.service.impl.traininginfo;

import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmenttraining.AppointmentTrainingEntity;
import vod.entity.traininginfo.TrainingInfoEntity;
import vod.entity.traininginfohistory.TrainingInfoHistoryEntity;
import vod.samesun.util.SystemType;
import vod.service.livesectionrecord.LiveSectionRecordServiceI;
import vod.service.traininginfo.TrainingInfoServiceI;

@Service("trainingInfoService")
@Transactional
public class TrainingInfoServiceImpl extends CommonServiceImpl implements TrainingInfoServiceI {
	
	@Autowired
	private LiveSectionRecordServiceI liveSectionRecordService;
	
	@Autowired
	private SystemService systemService;
	
	@Override
	public void getHistoryFromLive(TrainingInfoEntity live) {
		TrainingInfoHistoryEntity meetingHistory = new TrainingInfoHistoryEntity();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(live, meetingHistory);
			save(meetingHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public TrainingInfoEntity getTrainingInfoFromAppointment(
			AppointmentTrainingEntity app) {
		TrainingInfoEntity meeting = new TrainingInfoEntity();
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
	public void autoRecordTask() {
		List<TrainingInfoEntity> meetings = systemService.loadAll(TrainingInfoEntity.class);
		for(TrainingInfoEntity m : meetings){
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
						liveSectionRecordService.StartChannelSectionRecord(m, "TrainingInfoEntity");
						m.setAppointmentstate(Integer.valueOf(SystemType.APP_RECORD_3));
						systemService.updateEntitie(m);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
}