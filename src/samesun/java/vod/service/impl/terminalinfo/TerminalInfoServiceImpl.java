package vod.service.impl.terminalinfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.terminalinfo.TerminalInfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("terminalInfoService")
@Transactional
public class TerminalInfoServiceImpl extends CommonServiceImpl implements TerminalInfoServiceI {
	
}