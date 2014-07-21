package vod.service.impl.heartrequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.heartrequest.HeartRequestServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("heartRequestService")
@Transactional
public class HeartRequestServiceImpl extends CommonServiceImpl implements HeartRequestServiceI {
	
}