package vod.service.heartrequest;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.heartrequest.HeartRequestEntity;

public interface HeartRequestServiceI extends CommonService{

	public boolean hearBeatTask(HeartRequestEntity heartRequest, String strPlayID, String strisLive);
	
	public String getEPG(String mac);
	
	public boolean LogoutTask(long timeout);
}
