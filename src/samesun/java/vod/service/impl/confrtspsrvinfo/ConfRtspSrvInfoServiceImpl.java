package vod.service.impl.confrtspsrvinfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.confrtspsrvinfo.ConfRtspSrvInfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("confRtspSrvInfoService")
@Transactional
public class ConfRtspSrvInfoServiceImpl extends CommonServiceImpl implements ConfRtspSrvInfoServiceI {
	
}