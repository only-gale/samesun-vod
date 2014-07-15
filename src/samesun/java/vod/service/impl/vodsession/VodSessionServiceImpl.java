package vod.service.impl.vodsession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.vodsession.VodSessionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("vodSessionService")
@Transactional
public class VodSessionServiceImpl extends CommonServiceImpl implements VodSessionServiceI {
	
}