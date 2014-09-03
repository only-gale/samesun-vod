package vod.service.impl.authoritygroup;

import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.authgroupterminal.AuthGroupTerminalEntity;
import vod.entity.authoritygroup.AuthorityGroupEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.entity.terminalinfo.TerminalInfoEntity;
import vod.service.authoritygroup.AuthorityGroupServiceI;

@Service("authorityGroupService")
@Transactional
public class AuthorityGroupServiceImpl extends CommonServiceImpl implements AuthorityGroupServiceI {

	@Override
	public List<AuthorityGroupEntity> getAuthGroupsByMeeting(
			MeetingInfoEntity meeting) {
		List<AuthorityGroupEntity> groups = new ArrayList<AuthorityGroupEntity>();
		List<AppointmentChannelInfoEntity> channels = findByProperty(AppointmentChannelInfoEntity.class, "meetingid", meeting.getId());
		for(AppointmentChannelInfoEntity c : channels){
			AuthorityGroupEntity group = get(AuthorityGroupEntity.class, c.getAuthortiyGroupCid());
			if(null != group && !groups.contains(group)){
				groups.add(group);
			}
		}
		return groups;
	}

	@Override
	public void freeTerminals(MeetingInfoEntity meeting) {
		List<AuthorityGroupEntity> groups = this.getAuthGroupsByMeeting(meeting);
		if(null != groups && groups.size() > 0){
			for(AuthorityGroupEntity g : groups){
				List<AuthGroupTerminalEntity> ids = findByProperty(AuthGroupTerminalEntity.class, "authid", g.getId());
				if(null != ids && ids.size() > 0){
					for(AuthGroupTerminalEntity gt : ids){
						TerminalInfoEntity t = get(TerminalInfoEntity.class, gt.getTerminalid());
						if(null != t){
							//设置正在观看会议为空
							t.setNowvideo(null);
							t.setSubject(null);
							updateEntitie(t);
						}
					}
				}
			}
		}
	}
	
}