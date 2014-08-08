package vod.service.impl.terminalinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.authgroupterminal.AuthGroupTerminalEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.entity.terminalinfo.TerminalInfoEntity;
import vod.samesun.util.SystemType;
import vod.service.terminalinfo.TerminalInfoServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.StringUtil;

@Service("terminalInfoService")
@Transactional
public class TerminalInfoServiceImpl extends CommonServiceImpl implements TerminalInfoServiceI {

	@Override
	public List<TerminalInfoEntity> getConflicts(String id, String meetingType, String appointmentStarttime,
			String appointmentDuration) {
		// 当前分组里所有终端
		List<TerminalInfoEntity> terminals = getTerminalByGroupId(id);
		List<TerminalInfoEntity> temp = new ArrayList<TerminalInfoEntity>();
		//获取所有冲突会议的终端
		List<TerminalInfoEntity> conflictMeetingTerminals = getConflictTerminals(meetingType, appointmentStarttime, appointmentDuration);
		for(TerminalInfoEntity e : terminals){
			if(null != conflictMeetingTerminals && conflictMeetingTerminals.contains(e)){
				temp.add(e);
			}
		}
		return temp;
	}
	
	/**
	 * 根据分组id获取所有终端
	 * @param id
	 * @return
	 */
	public List<TerminalInfoEntity> getTerminalByGroupId(String id){
		List<AuthGroupTerminalEntity> gts = findByProperty(AuthGroupTerminalEntity.class, "authid", id);
		List<TerminalInfoEntity> result = new ArrayList<TerminalInfoEntity>();
		for(AuthGroupTerminalEntity e : gts){
			TerminalInfoEntity t = get(TerminalInfoEntity.class, e.getTerminalid());
			if(null != t && !result.contains(t)){
				result.add(t);
			}
		}
		return result;
	}
	
	public List<TerminalInfoEntity> getConflictTerminals(String meetingType, String appointmentStarttime,
			String appointmentDuration){
		List<TerminalInfoEntity> result = new ArrayList<TerminalInfoEntity>();
		if(SystemType.APP_MEETING_TYPE_1.equals(meetingType)){
			List<MeetingInfoEntity> meetings = loadAll(MeetingInfoEntity.class);
			for(MeetingInfoEntity m : meetings){
				Integer state = m.getMeetingstate();
				//当有直播会议冲突时
				if(state == Integer.valueOf(SystemType.MEETING_STATE_1) ||
						state == Integer.valueOf(SystemType.MEETING_STATE_2) ||
						state == Integer.valueOf(SystemType.MEETING_STATE_3)){
					//获取该会议的所有终端分组
					List<String> groupids = getGroupByMeeting(m);
					for(String id : groupids){
						
						result.addAll(getTerminalByGroupId(id));
					}
				}
			}
		}else if(SystemType.APP_MEETING_TYPE_3.equals(meetingType) && StringUtil.isNotEmpty(appointmentStarttime) && StringUtil.isNotEmpty(appointmentDuration)){
			//所有启用的预约会议
			List<AppointmentMeetingInfoEntity> apps = findByProperty(AppointmentMeetingInfoEntity.class, "appointmentState", SystemType.APP_MEETING_STATE_2);
			List<AppointmentMeetingInfoEntity> temp = new ArrayList<AppointmentMeetingInfoEntity>();
			Date appointmentbegintime = DataUtils.str2Date(appointmentStarttime, DataUtils.datetimeFormat);
			//当前预约会议结束时间
			Date appointmentendtime = DataUtils.getDate(appointmentbegintime.getTime() + new Integer(appointmentDuration).intValue() * 60 * 1000);
			for(AppointmentMeetingInfoEntity a : apps){
				Date start = a.getAppointmentStarttime(), end = DataUtils.getDate(a.getAppointmentStarttime().getTime() + (a.getAppointmentDuration()) * 60 * 1000);
				if(start.after(appointmentendtime) || end.before(appointmentbegintime)){
					temp.add(a);
				}
			}
			apps.removeAll(temp);
			//循环查找所有冲突的预约会议
			for(AppointmentMeetingInfoEntity a : apps){
				//获取当前循次预约会议的所有编码器
				List<String> groupids = getGroupByAppMeeting(a);
				for(String id : groupids){
					result.addAll(getTerminalByGroupId(id));
				}
			}
		}
		return result;
	}
	
	/**
	 * 根据直播id获取所有终端分组id
	 * @param m
	 * @return
	 */
	public List<String> getGroupByMeeting(MeetingInfoEntity m){
		List<String> result = new ArrayList<String>();
		if(m != null){
			List<AppointmentChannelInfoEntity> channels = findByProperty(AppointmentChannelInfoEntity.class, "meetingid", m.getId());
			for(AppointmentChannelInfoEntity e : channels){
				if(e != null){
					String id = e.getAuthortiyGroupCid();
					if(StringUtil.isNotEmpty(id) && !result.contains(id)){
						result.add(id);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 根据预约id获取所有终端分组id
	 * @param m
	 * @return
	 */
	public List<String> getGroupByAppMeeting(AppointmentMeetingInfoEntity m){
		List<String> result = new ArrayList<String>();
		if(m != null){
			List<AppointmentChannelInfoEntity> channels = findByProperty(AppointmentChannelInfoEntity.class, "appointmentid", m.getId());
			for(AppointmentChannelInfoEntity e : channels){
				if(e != null){
					String id = e.getAuthortiyGroupCid();
					if(StringUtil.isNotEmpty(id) && !result.contains(id)){
						result.add(id);
					}
				}
			}
		}
		return result;
	}
}