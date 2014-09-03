package vod.service.impl.confcodecrecordsrv;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.confcodecrecordsrv.ConfCodecRecordSrvServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("confCodecRecordSrvService")
@Transactional
public class ConfCodecRecordSrvServiceImpl extends CommonServiceImpl implements ConfCodecRecordSrvServiceI {
	
}