package vod.service.impl.vodsectionrecord;

import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.livesectionrecord.LiveSectionRecordEntity;
import vod.entity.vodsectionrecord.VodSectionRecordEntity;
import vod.service.vodsectionrecord.VodSectionRecordServiceI;

@Service("vodSectionRecordService")
@Transactional
public class VodSectionRecordServiceImpl extends CommonServiceImpl implements VodSectionRecordServiceI {

	@Override
	public void getVodSectionRecordByMeetingId(String meetingId) {
		List<VodSectionRecordEntity> toSave = new ArrayList<VodSectionRecordEntity>();
		
		//获取直播录制明细
		List<LiveSectionRecordEntity> liveRecords = this.findByProperty(LiveSectionRecordEntity.class, "meetingid", meetingId);
		
		for(LiveSectionRecordEntity lr : liveRecords){
			//获取频道
			AppointmentChannelInfoEntity channel = this.get(AppointmentChannelInfoEntity.class, lr.getChannelid());
			
			VodSectionRecordEntity vodRecord = new VodSectionRecordEntity();
			vodRecord.setId(lr.getId());
			vodRecord.setMeetingid(meetingId);
			vodRecord.setChannelid(lr.getChannelid());
			vodRecord.setSessionid(lr.getSessionid());
			vodRecord.setCodecpriorityflg(lr.getCodecpriorityflg());
			vodRecord.setCodecsrvid(lr.getCodecsrvid());
			vodRecord.setRecordsrvid(lr.getRecordsrvid());
			vodRecord.setRtspsrvid(lr.getRtspsrvid());
			vodRecord.setRtsprelativedir(lr.getRtsprelativedir());
			vodRecord.setFilename(lr.getFilename());
			vodRecord.setRecState(lr.getRecState());
			vodRecord.setRecMessage(lr.getRecMessage());
			vodRecord.setRecStartDt(lr.getRecStartDt());
			vodRecord.setRecEndDt(lr.getRecEndDt());
			vodRecord.setBillduration((int) ((lr.getRecEndDt().getTime() - lr.getRecStartDt().getTime()) / (60 * 1000)));
			vodRecord.setAsfurl(lr.getAsfurl());
			
			vodRecord.setAuthortiyGroupCid(channel.getAuthortiyGroupCid());
			vodRecord.setAuthortiyTerminlgroupCid(channel.getAuthortiyTerminlgroupCid());
			vodRecord.setAuthortiyUsergroupCid(channel.getAuthortiyUsergroupCid());
			
			toSave.add(vodRecord);
		}
		batchSave(toSave);
	}
	
}