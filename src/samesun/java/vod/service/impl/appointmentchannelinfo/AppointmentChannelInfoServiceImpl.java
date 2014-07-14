package vod.service.impl.appointmentchannelinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.service.appointmentchannelinfo.AppointmentChannelInfoServiceI;

@Service("appointmentChannelInfoService")
@Transactional
public class AppointmentChannelInfoServiceImpl extends CommonServiceImpl implements AppointmentChannelInfoServiceI {

	@Override
	public void delAllNullMeetingId() {
		List<AppointmentChannelInfoEntity> list = this.getList(AppointmentChannelInfoEntity.class);
		List<AppointmentChannelInfoEntity> result = new ArrayList<AppointmentChannelInfoEntity>();
		for(AppointmentChannelInfoEntity a : list){
			Date createDate = a.getCreateDate(), delDate = a.getDelDate();
			
			if(null != createDate && null != delDate && createDate.equals(delDate)){
				result.add(a);
			}
		}
		this.deleteAllEntitie(result);
		
	}

	@Override
	public void linkChannel(AppointmentMeetingInfoEntity from,
			MeetingInfoEntity to) {
		List<AppointmentChannelInfoEntity> channels = this.findByProperty(AppointmentChannelInfoEntity.class, "appointmentid", from.getId());
		//以,分隔的id   用于批量更新
		String ids = "";
		for(AppointmentChannelInfoEntity channel : channels){
			channel.setMeetingid(to.getId());
			ids += ("," + channel.getId());
		}
		if(StringUtil.isNotEmpty(ids)){
			this.updateBySqlString("update appointment_channel_info set meetingID='"+to.getId()+"' where ID in('"+ids.substring(1)+"')");
		}
		
	}

	@Override
	public void linkCodec(AppointmentChannelInfoEntity channel, String state) {
		String id1 = channel.getCodec1id(), id2 = channel.getCodec2id();
		if(StringUtil.isNotEmpty(id1)){
			ConfCodecInfoEntity c1 = get(ConfCodecInfoEntity.class, id1);
			c1.setDisable(state);
			updateEntitie(c1);
		}
		if(StringUtil.isNotEmpty(id2)){
			ConfCodecInfoEntity c2 = get(ConfCodecInfoEntity.class, id2);
			c2.setDisable(state);
			updateEntitie(c2);
		}
		
	}
	
	
}