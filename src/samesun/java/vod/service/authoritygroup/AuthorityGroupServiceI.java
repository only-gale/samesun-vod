package vod.service.authoritygroup;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.authoritygroup.AuthorityGroupEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;

public interface AuthorityGroupServiceI extends CommonService{

	public List<AuthorityGroupEntity> getAuthGroupsByMeeting(MeetingInfoEntity meeting);
	
	public void freeTerminals(MeetingInfoEntity meeting);
}
