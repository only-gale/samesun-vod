package vod.service.impl.authoritygroup;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.authoritygroup.AuthorityGroupServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("authorityGroupService")
@Transactional
public class AuthorityGroupServiceImpl extends CommonServiceImpl implements AuthorityGroupServiceI {
	
}