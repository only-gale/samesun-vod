package vod.service.confrecordsrvinfo;

import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

public interface ConfRecordSrvInfoServiceI extends CommonService{

	/**
	 * 据codec id号，返回录制及点播服务器
	 * @param codecId
	 * @return
	 * key为："ConfRecordsrvInfoPage"时，value代表录制服务器Page对象
	 * key为："ConfRtspsrvInfoPage"时，value代表点播服务器Page对象
	 */
	public Map<String, Object> getRecordSrvAndRtspSrv(String codecId);
}
