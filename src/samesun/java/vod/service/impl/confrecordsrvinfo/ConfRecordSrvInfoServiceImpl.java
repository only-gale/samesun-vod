package vod.service.impl.confrecordsrvinfo;

import java.util.HashMap;
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
		//根据编码器查找与录制服务器的依赖关系
		ConfCodecRecordSrvEntity recordSrv = this.get(ConfCodecRecordSrvEntity.class, codec.getCr());
		if(recordSrv != null){
			//得到编码器依赖的录制服务器
			String recordSrvId = recordSrv.getRecordsrvid();
			ConfRecordSrvInfoEntity recordSrvInfo = this.get(ConfRecordSrvInfoEntity.class, recordSrvId);
			srvsMap.put("ConfRecordsrvInfoPage",recordSrvInfo);
			if(recordSrvInfo != null){
				//根据录制服务器查找与点播服务器的依赖关系
				ConfRecordRtspSrvEntity recordRtspSrv = this.get(ConfRecordRtspSrvEntity.class, recordSrvInfo.getRr());
				if(recordRtspSrv != null){
					//得到录制服务器依赖的点播服务器
					String recordRtspSrvId = recordRtspSrv.getRtspsrvid();
					if(StringUtil.isNotEmpty(recordRtspSrvId)){
						ConfRtspSrvInfoEntity rtspSrv = this.get(ConfRtspSrvInfoEntity.class, recordRtspSrvId);
						srvsMap.put("ConfRtspsrvInfoPage", rtspSrv);
					}else{
						System.out.println("录制服务器ID：" + recordSrvId + "没有配置相应的点播服务器");
					}
				}
			}else{
				System.out.println("编码器ID：" + codecId + "没有配置相应的录制服务器");
			}
		}else{
			System.out.println("编码器ID：" + codecId + "没有配置相应的录制服务器和点播服务器");
		}
		
		return srvsMap;
	}
	
}