package vod.service.impl.tstype;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.tstype.TsTypeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tsTypeService")
@Transactional
public class TsTypeServiceImpl extends CommonServiceImpl implements TsTypeServiceI {
	
}