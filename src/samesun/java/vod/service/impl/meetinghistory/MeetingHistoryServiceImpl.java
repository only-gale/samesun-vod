package vod.service.impl.meetinghistory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.meetinghistory.MeetingHistoryEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.service.meetinghistory.MeetingHistoryServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;

@Service("meetingHistoryService")
@Transactional
public class MeetingHistoryServiceImpl extends CommonServiceImpl implements MeetingHistoryServiceI {

	@Override
	public void getHistoryFromLive(MeetingInfoEntity live) {
		MeetingHistoryEntity meetingHistory = new MeetingHistoryEntity();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(live, meetingHistory);
			save(meetingHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}