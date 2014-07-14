package vod.service.impl.appointmentmeetinginfo;

import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.samesun.util.SystemType;
import vod.service.appointmentmeetinginfo.AppointmentMeetingInfoServiceI;

@Service("appointmentMeetingInfoService")
@Transactional
public class AppointmentMeetingInfoServiceImpl extends CommonServiceImpl implements AppointmentMeetingInfoServiceI {

	@Override
	public List<AppointmentMeetingInfoEntity> getAppsByState(String state) {
		List<AppointmentMeetingInfoEntity> result = new ArrayList<AppointmentMeetingInfoEntity>();
		List<AppointmentMeetingInfoEntity> appointments = this.loadAll(AppointmentMeetingInfoEntity.class);
		for(AppointmentMeetingInfoEntity e : appointments){
			if(StringUtil.isNotEmpty(state) && state.replaceAll("\"", "").equals(e.getAppointmentState().toString())){
				result.add(e);
			}
		}
		return result;
	}

	@Override
	public String isRecord(AppointmentMeetingInfoEntity app) {
		//默认否
		String r = SystemType.IS_RECORD_TYPE_0;
		//根据会议id获取频道列表
		List<AppointmentChannelInfoEntity> channels = this.findByProperty(AppointmentChannelInfoEntity.class, "meetingid", app.getId());
		for(AppointmentChannelInfoEntity channel : channels){
			Integer isRecord1 = channel.getIsrecord1(), isRecord2 = channel.getIsrecord2();
			if(new Integer(SystemType.IS_RECORD_TYPE_1) == isRecord1 || new Integer(SystemType.IS_RECORD_TYPE_1) == isRecord2){
				r = SystemType.IS_RECORD_TYPE_1;
				break;
			}
		}
		return r;
	}
	
}