package vod.service.impl.usergroup;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.usergroup.UserGroupServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("userGroupService")
@Transactional
public class UserGroupServiceImpl extends CommonServiceImpl implements UserGroupServiceI {
	
}