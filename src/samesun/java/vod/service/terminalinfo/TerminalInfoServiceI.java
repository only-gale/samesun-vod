package vod.service.terminalinfo;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.terminalinfo.TerminalInfoEntity;

public interface TerminalInfoServiceI extends CommonService{

	/**
	 * 根据设备分组id查询终端是否冲突
	 * @param id	设备分组id
	 * @param meetingType
	 * @param excepts
	 * @param appointmentStarttime
	 * @param appointmentDuration
	 * @return
	 */
	public List<TerminalInfoEntity> getConflicts(String id, String meetingType, String appointmentStarttime, String appointmentDuration);
}
