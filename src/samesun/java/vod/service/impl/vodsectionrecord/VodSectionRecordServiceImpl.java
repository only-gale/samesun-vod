package vod.service.impl.vodsectionrecord;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.vodsectionrecord.VodSectionRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("vodSectionRecordService")
@Transactional
public class VodSectionRecordServiceImpl extends CommonServiceImpl implements VodSectionRecordServiceI {
	
}