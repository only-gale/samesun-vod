package vod.service.impl.confcodecinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jodd.util.StringUtil;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.appointmentmeetinginfo.AppointmentMeetingInfoEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.samesun.util.SystemType;
import vod.service.appointmentmeetinginfo.AppointmentMeetingInfoServiceI;
import vod.service.confcodecinfo.ConfCodecInfoServiceI;

@Service("confCodecInfoService")
@Transactional
public class ConfCodecInfoServiceImpl extends CommonServiceImpl implements ConfCodecInfoServiceI {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AppointmentMeetingInfoServiceI appointmentMeetingInfoService;
	
	@Override
	public List<ConfCodecInfoEntity> combox(String meetingType, String excepts, String appointmentStarttime, String appointmentDuration) {
		List<ConfCodecInfoEntity> tmpList = new ArrayList<ConfCodecInfoEntity>();
//		List<ConfCodecInfoEntity> all = this.loadAll(ConfCodecInfoEntity.class);
		List<ConfCodecInfoEntity> all = getAvailableCodecs(meetingType, appointmentStarttime, appointmentDuration);
		if(!StringUtil.isEmpty(excepts)){
			
			for(String id : excepts.split(",")){
				ConfCodecInfoEntity c = this.getEntity(ConfCodecInfoEntity.class, id);
				if(!tmpList.contains(c)){
					tmpList.add(c);
				}
			}
		}
		all.removeAll(tmpList);
		return all;
	}

	/**
	 * <b>获取可用的编码器</b>
	 * <br>此逻辑按照所要创建的会议类型分两部分，即直播会议和预约会议：
	 * <br>当所要创建的是直播会议时，所有当前未启用的编码器都视为此次创建任务可用的编码器；
	 * <br>当所要创建的是预约会议时，所有与预约时间不冲突(和当前时刻编码器是否已启用无关)的编码器均视为此次创建任务可用的编码器。
	 * @param String meetingType 会议类型
	 */
	@Override
	public List<ConfCodecInfoEntity> getAvailableCodecs(String meetingType, String appointmentStarttime, String appointmentDuration) {
		List<ConfCodecInfoEntity> tmpList = new ArrayList<ConfCodecInfoEntity>();
		List<ConfCodecInfoEntity> all = this.loadAll(ConfCodecInfoEntity.class);
		
		//
		//直播会议只选取当前未启用的编码器
		if(SystemType.APP_MEETING_TYPE_1.replaceAll("\"", "").equals(meetingType)){
			for(ConfCodecInfoEntity e : all){
				if(SystemType.CODEC_AVILABLE_0.equals(e.getDisable()) && !tmpList.contains(e)){
					tmpList.add(e);
				}
			}
		}
		//预约会议
		else if(SystemType.APP_MEETING_TYPE_3.replaceAll("\"", "").equals(meetingType)){
			//选取预约开始时间与结束时间内未启用的编码器
			//先获取新建状态下的预约会议(所有启用和过期状态的预约会议的编码器均视为不可用)
			List<AppointmentMeetingInfoEntity> apps = appointmentMeetingInfoService.getAppsByState(SystemType.APP_MEETING_STATE_1);
			//当前预约会议开始时间
			Date appointmentbegintime = DataUtils.str2Date(appointmentStarttime, DataUtils.datetimeFormat);
			//当前预约会议结束时间
			Date appointmentendtime = DataUtils.getDate(appointmentbegintime.getTime() + new Integer(appointmentDuration).intValue() * 60 * 1000);
			for(AppointmentMeetingInfoEntity e : apps){
				Date start = e.getAppointmentStarttime(), end = DataUtils.getDate(e.getAppointmentStarttime().getTime() + (e.getAppointmentDuration()) * 60 * 1000);
				//当已新建的预约会议开始时间在当前创建预约会议的结束时间之后
				//或者
				//当已新建的预约会议结束时间在当前创建预约会议的开始时间之前
				//即视为不冲突
				if((start.after(appointmentbegintime) && start.before(appointmentendtime) || appointmentbegintime.equals(start)) ||
						(end.after(appointmentbegintime) && end.before(appointmentendtime) || appointmentendtime.equals(end)) ||
						(start.before(appointmentbegintime) && end.after(appointmentendtime))){
					for(ConfCodecInfoEntity c : getCodecs(e)){
						all.remove(c);
					}
				}
			}
			tmpList.addAll(all);
		}
		
		return tmpList;
	}

	@Override
	public List<ConfCodecInfoEntity> getCodecs(AppointmentMeetingInfoEntity e) {
		List<ConfCodecInfoEntity> result = new ArrayList<ConfCodecInfoEntity>();
		//根据会议id获取频道列表
		List<AppointmentChannelInfoEntity> channels = this.findByProperty(AppointmentChannelInfoEntity.class, "appointmentid", e.getId());
		for(AppointmentChannelInfoEntity channel : channels){
			String codec1id = channel.getCodec1id(), codec2id = channel.getCodec2id();;
			//主编码器
			if(StringUtil.isNotEmpty(codec1id)){
				ConfCodecInfoEntity codec1 = (ConfCodecInfoEntity)this.getEntity(ConfCodecInfoEntity.class, codec1id);
				if(!result.contains(codec1)){
					result.add(codec1);
				}
			}
			//备编码器
			if(StringUtil.isNotEmpty(codec2id)){
				ConfCodecInfoEntity codec2 = (ConfCodecInfoEntity)this.getEntity(ConfCodecInfoEntity.class, codec2id);
				if(!result.contains(codec2)){
					result.add(codec2);
				}
			}
		}
		return result;
	}

	@Override
	public List<ConfCodecInfoEntity> getCodecsNeed2Record(MeetingInfoEntity meeting) {
		List<ConfCodecInfoEntity> result = new ArrayList<ConfCodecInfoEntity>();
		//根据会议id获取频道列表
		List<AppointmentChannelInfoEntity> channels = this.findByProperty(AppointmentChannelInfoEntity.class, "meetingid", meeting.getId());
		for(AppointmentChannelInfoEntity channel : channels){
			
			String codec1id = channel.getCodec1id(), codec2id = channel.getCodec2id();
			Integer	isrecord1 = channel.getIsrecord1(), isrecord2 = channel.getIsrecord2();
			//主编码器
			if(isrecord1 == new Integer(SystemType.IS_RECORD_TYPE_1) && StringUtil.isNotEmpty(codec1id)){
				ConfCodecInfoEntity codec1 = (ConfCodecInfoEntity)this.getEntity(ConfCodecInfoEntity.class, codec1id);
				if(!result.contains(codec1)){
					result.add(codec1);
				}
			}
			//备编码器
			if(isrecord2 == new Integer(SystemType.IS_RECORD_TYPE_1) && StringUtil.isNotEmpty(codec2id)){
				ConfCodecInfoEntity codec2 = (ConfCodecInfoEntity)this.getEntity(ConfCodecInfoEntity.class, codec2id);
				if(!result.contains(codec2)){
					result.add(codec2);
				}
			}
		}
		return result;
	}
	
}