package vod.service.impl.authorityusergroup;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.authorityusergroup.AuthorityUserGroupServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("authorityUserGroupService")
@Transactional
public class AuthorityUserGroupServiceImpl extends CommonServiceImpl implements AuthorityUserGroupServiceI {
	
}