package vod.service.impl.livesectionrecord;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.confrecordsrvinfo.ConfRecordSrvInfoEntity;
import vod.entity.confrtspsrvinfo.ConfRtspSrvInfoEntity;
import vod.entity.livesectionrecord.LiveSectionRecordEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.entity.meetinglivesession.MeetingLiveSessionEntity;
import vod.samesun.util.NetTelnet;
import vod.samesun.util.SystemType;
import vod.service.appointmentchannelinfo.AppointmentChannelInfoServiceI;
import vod.service.confcodecinfo.ConfCodecInfoServiceI;
import vod.service.confrecordsrvinfo.ConfRecordSrvInfoServiceI;
import vod.service.livesectionrecord.LiveSectionRecordServiceI;
import vod.service.meetinginfo.MeetingInfoServiceI;
import vod.service.meetinglivesession.MeetingLiveSessionServiceI;

@Service("liveSectionRecordService")
@Transactional
public class LiveSectionRecordServiceImpl extends CommonServiceImpl implements
		LiveSectionRecordServiceI {

	@Autowired
	private ConfRecordSrvInfoServiceI confRecordSrvInfoService;

	@Autowired
	private MeetingInfoServiceI meetingInfoService;
	@Autowired
	private AppointmentChannelInfoServiceI appointmentChannelInfoService;
	@Autowired
	private ConfCodecInfoServiceI confCodecInfoService;
	@Autowired
	private MeetingLiveSessionServiceI meetingLiveSessionService;

	@Override
	public String SectionRecord4DB(String meetingId, String channelID,
			String sessionID, String Codec, String Priority, String fileName) {
		String liveSectionId = "", strRecordSrvID = "", strRtspSrvID = "";
		// 据Codec可以查询到相应的RecordSrvID,RtspSrvID
		Map<String, Object> srvsMap = confRecordSrvInfoService
				.getRecordSrvAndRtspSrv(Codec);
		if(srvsMap != null && srvsMap.size() > 0){
			
			ConfRecordSrvInfoEntity recordSrv = (ConfRecordSrvInfoEntity) srvsMap
					.get("ConfRecordsrvInfoPage");
			if (null != recordSrv) {
				strRecordSrvID = recordSrv.getId();
			}
			ConfRtspSrvInfoEntity rtspSrv = (ConfRtspSrvInfoEntity) srvsMap
					.get("ConfRtspsrvInfoPage");
			if (null != rtspSrv) {
				strRtspSrvID = rtspSrv.getId();
			}
			
			LiveSectionRecordEntity liveSection = new LiveSectionRecordEntity();
			liveSection.setMeetingid(meetingId);
			liveSection.setChannelid(channelID);
			liveSection.setSessionid(sessionID);
			liveSection.setCodecpriorityflg(Integer.parseInt(Priority));
			liveSection.setCodecsrvid(Codec);
			liveSection.setRecordsrvid(strRecordSrvID);
			liveSection.setRtspsrvid(strRtspSrvID);
			try {
				liveSection.setRecStartDt(DataUtils.parseDate(
						DataUtils.getDataString(DataUtils.datetimeFormat),
						DataUtils.datetimeFormat.toPattern()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			liveSection.setRtsprelativedir(DataUtils.yyyyMM.format(DataUtils
					.getDate()));
			save(liveSection);
			liveSectionId = liveSection.getId();
		}else{
			System.out.println("由于无法查询到编码器的录制服务和点播服务信息，所以无法创建录制信息");
		}
		return liveSectionId;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=IOException.class)
	public String StartChannelSectionRecord(String meetingId) {
		String resultStr = "";
		MeetingInfoEntity t = meetingInfoService.get(MeetingInfoEntity.class,
				meetingId);
		// 改变直播会议状态到"直播并录制中"
		t.setMeetingstate(Integer.parseInt(SystemType.MEETING_STATE_2));

		// 创建直播session
		MeetingLiveSessionEntity liveSession = meetingLiveSessionService
				.create(meetingId);

		// 根据codecid查找到录制服务和点播服务，然后对每一个codec生成分段录制记录，但是需要针对相同的codec设置相同的文件名，这样只需发送一次telnet
		List<AppointmentChannelInfoEntity> channels = meetingInfoService
				.findByProperty(AppointmentChannelInfoEntity.class,
						"meetingid", meetingId);
		for (AppointmentChannelInfoEntity c : channels) {
			Integer isrecord1 = c.getIsrecord1(), isrecord2 = c.getIsrecord2();
			String codec1id = c.getCodec1id(), codec2id = c.getCodec2id();
			// 实际上，相同的codec这种情况只会出现在同一频道内
			if (StringUtil.isNotEmpty(codec1id)) {
				// 生成文件名
				String fileNameUUID = UUID.randomUUID().toString();
				// 主编码器级别为1
				if (isrecord1 == Integer.parseInt(SystemType.IS_RECORD_TYPE_1)) {
					SectionRecord4DB(meetingId, c.getId(), liveSession.getId(),
							codec1id, "1", fileNameUUID);
					// TELNET
					try {
						resultStr += SectionRecord4Telnet(fileNameUUID, codec1id);
					} catch (IOException e) {
						resultStr += e.getMessage();
						e.printStackTrace();
					}
				}
				// 备编码器级别为2
				if (StringUtil.isNotEmpty(codec2id)
						&& isrecord2 == Integer.parseInt(SystemType.IS_RECORD_TYPE_1)
						&& !codec1id.equals(codec2id)) {
					// 当主备编码器不相同时需要重新为备编码器生成文件名
					String newFileName = UUID.randomUUID().toString();
					SectionRecord4DB(meetingId, c.getId(), liveSession.getId(),
							codec2id, "2", newFileName);
					// TELNET
					try {
						resultStr += SectionRecord4Telnet(newFileName, codec1id);
					} catch (IOException e) {
						resultStr += e.getMessage();
						e.printStackTrace();
					}
				} else if (StringUtil.isNotEmpty(codec2id)
						&& isrecord2 == Integer.parseInt(SystemType.IS_RECORD_TYPE_1)
						&& codec1id.equals(codec2id)) {
					SectionRecord4DB(meetingId, c.getId(), liveSession.getId(),
							codec2id, "2", fileNameUUID);
					// TELNET
					try {
						resultStr += SectionRecord4Telnet(fileNameUUID, codec2id);
					} catch (IOException e) {
						resultStr += e.getMessage();
						e.printStackTrace();
					}
				}
			}
		}

		meetingInfoService.updateEntitie(t);
		
		return resultStr;
	}

	/**
	 * 
	 * @param Obid
	 *            分段录制记录的ID号，对应telnet的文件名
	 * @param Codec
	 *            codec服务器Id,可以查询出录制服务器ID及RtspServer服务器ID
	 * @return 返回Json String
	 * @throws IOException 
	 */
	private String SectionRecord4Telnet(String meetingId, String codecId) throws IOException {
		String strMessage = "Telnet通讯成功!";
		String strIP = "";
		String strPort = "";
		String codecName = "";
		String result = "";
		String codecUrl = "";
		ConfCodecInfoEntity codecinfo = null;
		ConfRecordSrvInfoEntity recordsrv = null;
		Map<String, Object> srvsMap = confRecordSrvInfoService
				.getRecordSrvAndRtspSrv(codecId);
		if (srvsMap != null && srvsMap.size() > 0) {
			recordsrv = (ConfRecordSrvInfoEntity) srvsMap
					.get("ConfRecordsrvInfoPage");
			if (recordsrv != null) {
				// 获取录制服务器ip地址和端口号port
				strIP = recordsrv.getIpaddress();
				strPort = String.valueOf(recordsrv.getPort());
			}
			codecinfo = (ConfCodecInfoEntity) srvsMap.get("ConfCodecInfoPage");
			if (codecinfo != null) {
				// 获取编码器名称
				codecName = codecinfo.getName();
			}
		}

		// telnet成功时
		if(StringUtil.isNotEmpty(strIP) && StringUtil.isNotEmpty(strPort)){
			NetTelnet telnet = new NetTelnet(strIP, strPort);
			if (telnet.isAvailable()) {
				// 获取编码器地址
				codecUrl = codecinfo.getCodecurl();
				// 拼接开始录制命令
				String reportMessage = ReportTelnetMessageStartRec(meetingId,
						codecUrl);
				// 发送开始录制命令
				result = telnet.sendCommand(reportMessage);
				
				// 验证telnet是否执行成功
				if (resultTelnetMessage(result)) {
					strMessage = "Codec:为" + codecName + " 录制成功 ！ ";
				} else {
					strMessage = "Codec:为" + codecName + " 录制失败 ！";
					throw new IOException(strMessage);
				}
			} else {
				strMessage = "Codec:为" + codecName
						+ ",telnet通讯不正常，无法链接，请与技术支持人员联系!";
				throw new IOException(strMessage);
			}
			// 最后一定要关闭
			telnet.disconnect();
		}else{
			if(StringUtil.isEmpty(strIP)){
				strMessage = "Codec:为" + codecName
						+ ",无法获取编码器IP地址!";
				throw new IOException(strMessage);
			}else if(StringUtil.isEmpty(strPort)){
				strMessage = "Codec:为" + codecName
						+ ",无法获取编码器端口号!";
				throw new IOException(strMessage);
			}else{
				strMessage = "Codec:为" + codecName
						+ ",无法获取编码器IP地址和端口号!";
				throw new IOException(strMessage);
			}
		}

		return strMessage;
	}

	/**
	 * 开始录制TELNET
	 * @param recordObid
	 * @param CodecURL
	 * @return
	 */
	private String ReportTelnetMessageStartRec(String recordObid,
			String CodecURL) {
		String filename = "000000000000000000000000000000000000"; // 文件名的长度为36位；
		String result = "";
		if (CodecURL == null)
			CodecURL = "rtp://";
		if (recordObid != null) {
			int len = recordObid.length();
			if (len == 36) {
				filename = recordObid;
			} else if (len < 36) { // 补空格；
				int len0 = (36 - recordObid.length());
				String prifix = "";
				for (int i = 0; i < len0; i++) {
					prifix += "0";
				}
				filename = prifix + recordObid;
			}
		}
		// 格式组成："S"+Total LEN+FileName(36)+YearMonth(6位)+CodecURL+"E#"
		String yearMonth = DataUtils.yyyyMM.format(new Date());
		int intTotalLen = 36 + 6 + CodecURL.length(); // Total Len
		String strTotalLen = "0000" + String.valueOf(intTotalLen);
		strTotalLen = strTotalLen.substring(strTotalLen.length() - 4); // 只保留4位
		//
		result = "S" + strTotalLen + filename + yearMonth + CodecURL + "E#";
		return result;
	}

	/**
	 * 
	 * 结果（成功或失败）：0：代表成功；1：代表失败；
	 * 其上示例结成的格式为：“S0000000000000000013699288866810010011E#“,代表返回失败；
	 * 形如：“E0000000000000000013699288866810010010S#“代表成功；
	 * 
	 * @param strtelnetResult
	 * @return true:表示成功；false:表示失败；
	 */
	private boolean resultTelnetMessage(String strtelnetResult) {
		String temp = "";
		if (strtelnetResult != null && strtelnetResult.length() > 36) {
			temp = strtelnetResult.substring(strtelnetResult.length() - 3);
		}
		//
		if (temp != null && temp.equals("0E#")) {
			return true;
		} else if (temp != null && temp.equals("1E#")) {
			return false;
		}
		return false;
	}

	@Override
	public String EndChannelSectionRecord(String meetingId) {
		String message="";
		//根据直播id查找所有录制片段
		List<LiveSectionRecordEntity> all = this.findByProperty(LiveSectionRecordEntity.class, "meetingid", meetingId);
		List<LiveSectionRecordEntity> distinctRecords = new ArrayList<LiveSectionRecordEntity>();
		for(LiveSectionRecordEntity r : all){
			LiveSectionRecordEntity record = new LiveSectionRecordEntity();
			record.setFilename(r.getFilename());
			record.setCodecsrvid(r.getCodecsrvid());
			if(!distinctRecords.contains(record)){
				distinctRecords.add(record);
			}
			
		}
		//更新状态为“已结束”
		String end = DataUtils.datetimeFormat.format(DataUtils.getDate());
		String sql = "update meeting_live_section_record set Rec_State="+SystemType.MEETING_STATE_3+",Rec_End_DT='"+end+"' where meetingid="+meetingId;
		this.updateBySqlString(sql);
		
		for(LiveSectionRecordEntity sr : distinctRecords){
			String fname = sr.getFilename();
			String CodecSrvId = sr.getCodecsrvid();
			message = EndPerSectionRecord4Telnet(fname, CodecSrvId);
			
		}
		return message;
	}
	
	/**
	 * 
	 * @param _filename
	 *            分段录制记录的ID号，对应telnet的文件名
	 * @param Codec
	 *            codec服务器Id,可以查询出录制服务器ID及RtspServer服务器ID
	 * @return 返回Json String
	 */
	public String EndPerSectionRecord4Telnet(String _filename, String CodecSrvId) {

		String strMessage = "";

		// //////////////////////////////////////////////////////////////
		//
		// 由Codec主备机，分别取得需要录制的"录制服务器"进行录制；由Telnet过程启动；
		//
		// ////////////////////////////////////////////////////////////////
		String strIP = "";
		String strPort = "";
		String codecName = "";
		Map<String, Object> srvsMap = confRecordSrvInfoService
				.getRecordSrvAndRtspSrv(CodecSrvId);
		if (srvsMap != null) {
			ConfRecordSrvInfoEntity recordsrv = (ConfRecordSrvInfoEntity) srvsMap
					.get("ConfRecordsrvInfoPage");
			if (recordsrv != null) {
				strIP = recordsrv.getIpaddress();
				strPort = String.valueOf(recordsrv.getPort());
			}
			ConfCodecInfoEntity codecinf = (ConfCodecInfoEntity) srvsMap
					.get("ConfCodecInfoPage");
			if (codecinf != null) {
				codecName = codecinf.getName();
			}
		}
		//
		NetTelnet telnet = new NetTelnet(strIP, strPort); // 需要修改
		String result = "";
		@SuppressWarnings("unused")
		String codecUrl = "";
		try {
			if (telnet.isAvailable()) {
				ConfCodecInfoEntity codecinfo = (ConfCodecInfoEntity) srvsMap
						.get("ConfCodecInfoPage");
				if (codecinfo != null) {
					codecUrl = codecinfo.getCodecurl();
				}
				String reportMessage = ReportTelnetMessageEndRec(_filename);
				result = telnet.sendCommand(reportMessage); // 通讯接口已改变

				if (resultTelne4EndtMessage(result)) {// 返回成功标志
					strMessage = "Codec:为" + codecName + "结束录制成功 ！ ";
				} else {
					strMessage = "Codec:为" + codecName + "结束录制失败 ！";

				}
			}
			// 最后一定要关闭
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			strMessage = "Codec:为" + codecName + "结束录制失败 ！";
		}

		return strMessage;

	}

	/**
	 * 结束录制TELNET
	 * @param recordObid
	 * @return
	 */
	public String ReportTelnetMessageEndRec(String recordObid) {
		String filename = "000000000000000000000000000000000000"; // 文件名的长度为36位；
		String result = "";

		if (recordObid != null) {
			int len = recordObid.length();
			if (len == 36) {
				filename = recordObid;
			} else if (len < 36) { // 补空格；
				int len0 = (36 - recordObid.length());
				String prifix = "";
				for (int i = 0; i < len0; i++) {
					prifix += "0";
				}
				filename = prifix + recordObid;
			}
		}
		// 格式组成："E"+FileName(36)+"E#"
		result = "E" + filename + "S#";
		return result;
	}

	public boolean resultTelne4EndtMessage(String strtelnetResult) {
		String temp = "";
		if (strtelnetResult != null && strtelnetResult.length() > 36) {
			temp = strtelnetResult.substring(strtelnetResult.length() - 3);
		}
		if (temp != null && temp.equals("0S#")) {
			return true;
		} else if (temp != null && temp.equals("1S#")) {
			return false;
		}
		return false;
	}
}