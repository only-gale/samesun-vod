package vod.service.impl.meetinglog;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.meetinglog.MeetingLogServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("meetingLogService")
@Transactional
public class MeetingLogServiceImpl extends CommonServiceImpl implements MeetingLogServiceI {
	
}