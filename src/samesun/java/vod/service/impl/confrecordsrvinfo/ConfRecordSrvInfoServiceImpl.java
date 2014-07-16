package vod.service.impl.confrecordsrvinfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.confcodecrecordsrv.ConfCodecRecordSrvEntity;
import vod.entity.confrecordsrvinfo.ConfRecordSrvInfoEntity;
import vod.entity.confrtspsrvinfo.ConfRtspSrvInfoEntity;
import vod.entity.recordrtspsrv.ConfRecordRtspSrvEntity;
import vod.service.confrecordsrvinfo.ConfRecordSrvInfoServiceI;

@Service("confRecordSrvInfoService")
@Transactional
public class ConfRecordSrvInfoServiceImpl extends CommonServiceImpl implements ConfRecordSrvInfoServiceI {

	@Override
	public Map<String, Object> getRecordSrvAndRtspSrv(String codecId) {
		Map<String, Object> srvsMap=new HashMap<String, Object>();
		ConfCodecInfoEntity codec = this.get(ConfCodecInfoEntity.class, codecId);
		srvsMap.put("ConfCodecInfoPage",codec);
		List<ConfCodecRecordSrvEntity> recordSrves = this.findByProperty(ConfCodecRecordSrvEntity.class, "codecid", codecId);
		Collections.reverse(recordSrves);
		if(recordSrves != null && recordSrves.size() > 0){
			ConfCodecRecordSrvEntity recordSrv = recordSrves.get(0);
			String recordSrvId = recordSrv.getRecordsrvid();
			if(StringUtil.isNotEmpty(recordSrvId)){
				
				ConfRecordSrvInfoEntity recordSrvInfo = this.get(ConfRecordSrvInfoEntity.class, recordSrvId);
				srvsMap.put("ConfRecordsrvInfoPage",recordSrvInfo);
				
				List<ConfRecordRtspSrvEntity> recordRtspSrves = this.findByProperty(ConfRecordRtspSrvEntity.class, "recordsrvid", recordSrvId);
				Collections.reverse(recordRtspSrves);
				if(recordRtspSrves != null && recordRtspSrves.size() > 0){
					ConfRecordRtspSrvEntity recordRtspSrv = recordRtspSrves.get(0);
					
					String recordRtspSrvId = recordRtspSrv.getRtspsrvid();
					if(StringUtil.isNotEmpty(recordRtspSrvId)){
						ConfRtspSrvInfoEntity rtspSrv = this.get(ConfRtspSrvInfoEntity.class, recordRtspSrvId);
						srvsMap.put("ConfRtspsrvInfoPage", rtspSrv);
					}else{
						System.out.println("CRECORDSRVID服务器ID：" + recordSrvId + "没有配置相应的RTSP Server服务器");
					}
				}
			}else{
				System.out.println("codec服务器ID：" + codecId + "没有配置相应的recordsrv录制服务器");
			}
		}
		
		System.out.println("codec服务器ID：" + codecId + "没有配置相应的录制服务器和点播服务器");
		return srvsMap;
	}
	
}