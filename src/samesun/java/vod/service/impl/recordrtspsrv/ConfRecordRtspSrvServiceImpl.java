package vod.service.impl.recordrtspsrv;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.recordrtspsrv.ConfRecordRtspSrvServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("confRecordRtspSrvService")
@Transactional
public class ConfRecordRtspSrvServiceImpl extends CommonServiceImpl implements ConfRecordRtspSrvServiceI {
	
}