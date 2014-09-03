package vod.service.impl.heartrequest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.entity.appointmentchannelinfo.AppointmentChannelInfoEntity;
import vod.entity.authgroupterminal.AuthGroupTerminalEntity;
import vod.entity.authoritygroup.AuthorityGroupEntity;
import vod.entity.confcodecinfo.ConfCodecInfoEntity;
import vod.entity.confrtspsrvinfo.ConfRtspSrvInfoEntity;
import vod.entity.heartrequest.HeartRequestEntity;
import vod.entity.meetinginfo.MeetingInfoEntity;
import vod.entity.meetinglog.MeetingLogEntity;
import vod.entity.terminalinfo.TerminalInfoEntity;
import vod.entity.vodsectionrecord.VodSectionRecordEntity;
import vod.samesun.util.SystemType;
import vod.samesun.util.VideoPlayConfig;
import vod.service.heartrequest.HeartRequestServiceI;

@Service("heartRequestService")
@Transactional
public class HeartRequestServiceImpl extends CommonServiceImpl implements
		HeartRequestServiceI {

	static private Map<String, String> MAC_STATE = new HashMap<String, String>(); // key:MAC地址；Value:时间（long与String）;

	@Autowired
	private SystemService systemService;

	@Override
	public boolean hearBeatTask(HeartRequestEntity heartRequest,
			String strPlayID, String strisLive) {
		String ip = heartRequest.getIpaddress(), mac = heartRequest
				.getMacaddress();
		TerminalInfoEntity terminal = findUniqueByProperty(
				TerminalInfoEntity.class, "macaddress", mac);
		boolean IsLog = false; // 是否作日志保存
		if (null == terminal) {// 不存在时，做一条心跳请求记录；同时返回；
			
			HeartRequestEntity heart = findUniqueByProperty(
					HeartRequestEntity.class, "macaddress", mac);
			if(heart == null){
				heart = new HeartRequestEntity();
				heart.setIpaddress(ip);
				heart.setMacaddress(mac);
				heart.setRequestDt(DataUtils.str2Date(
						DataUtils.datetimeFormat.format(DataUtils.getDate()),
						DataUtils.datetimeFormat));
				save(heart);
			}else{
				heart.setIpaddress(ip);
				heart.setMacaddress(mac);
				heart.setRequestDt(DataUtils.str2Date(
						DataUtils.datetimeFormat.format(DataUtils.getDate()),
						DataUtils.datetimeFormat));
				saveOrUpdate(heart);
			}
			return false;
		} else {
			MAC_STATE.put(mac, String.valueOf(DataUtils.getMillis()));
			int oldstate = terminal.getStatus();
			String oldplayId = terminal.getNowvideo();
			String oldIP = terminal.getIpaddress();
			// 有心跳信息则为在线状态
			if (oldstate != Integer.valueOf(SystemType.TERMINAL_STATE_2)) {
				terminal.setStatus(Integer.valueOf(SystemType.TERMINAL_STATE_2));
			}

			// 更新IP地址
			if (StringUtil.isNotEmpty(oldIP) && StringUtil.isNotEmpty(ip)
					&& !ip.trim().equalsIgnoreCase(oldIP)) {
				terminal.setIpaddress(ip);
			}

			// playID号的统一处理
			strPlayID = StringUtil.isNotEmpty(strPlayID) ? strPlayID : "";
			oldplayId = StringUtil.isNotEmpty(oldplayId) ? oldplayId : "";
			
			if(StringUtil.isEmpty(strPlayID)){
				terminal.setNowvideo(null);
				terminal.setSubject(null);
			}else if (!strPlayID.trim().equalsIgnoreCase(oldplayId)) {
				if (SystemType.LIVE_TYPE_1.equals(strisLive)) { // 直播

					MeetingInfoEntity meeting = systemService.get(
							MeetingInfoEntity.class, strPlayID);
					if (null != meeting) {
						terminal.setNowvideo(strPlayID);
						terminal.setSubject(meeting.getSubject());
					}
				} else if (SystemType.LIVE_TYPE_2.equals(strisLive)) { // 点播
					VodSectionRecordEntity vod = systemService.get(
							VodSectionRecordEntity.class, strPlayID);
					if (null != vod) {
						MeetingInfoEntity meeting = systemService.get(
								MeetingInfoEntity.class, vod.getMeetingid());
						if (null != meeting) {
							terminal.setNowvideo(strPlayID);
							terminal.setSubject(meeting.getSubject());
						}
					}
				}
				if (StringUtil.isNotEmpty(strPlayID)) {
					IsLog = true;
				}
			}

			// 持久化操作
			updateEntitie(terminal);
		}
		if (IsLog) {
			MeetingLogEntity log = new MeetingLogEntity();
			log.setEdgeid(terminal.getId());
			log.setEdgemac(terminal.getMacaddress());
			log.setEdgename(terminal.getName());
			log.setIsliveflag(strisLive);
			log.setMeetingid(terminal.getNowvideo());
			log.setState(terminal.getStatus());
			log.setSubject(terminal.getSubject());
			save(log);
			IsLog = false;
		}

		return true;
	}

	@Override
	public String getEPG(String mac) {
		String StrXML = "";
		StrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> ";
		StrXML = StrXML + "\n<epg> ";
		StrXML = StrXML + getEPG4Live(mac);
		StrXML = StrXML + getEPG4SectionRec(mac);
		StrXML = StrXML + "\n</epg> ";
		return StrXML;

	}

	private String getEPG4Live(String mac) {
		String prefixXML = "live";// 直播
		String strXML = "\n<" + prefixXML + "Programs name=\"" + prefixXML
				+ "\">  ";
		String oldTypeid = ""; // EPG节目的类型
		// 新记录的组合
		String record = "";
		List<MeetingInfoEntity> meetings = new ArrayList<MeetingInfoEntity>();
		if (StringUtil.isNotEmpty(mac)) {
			// 由mac地址得到唯一终端
			TerminalInfoEntity terminal = findUniqueByProperty(
					TerminalInfoEntity.class, "macaddress", mac);
			if (null != terminal) {
				String tid = terminal.getId();
				// 由终端得到所属的所有组
				List<AuthGroupTerminalEntity> groupids = systemService
						.findByProperty(AuthGroupTerminalEntity.class,
								"terminalid", tid);
				if (groupids != null && groupids.size() > 0) {
					for (AuthGroupTerminalEntity agt : groupids) {
						AuthorityGroupEntity t = systemService.get(
								AuthorityGroupEntity.class, agt.getAuthid());
						if (t != null) {
							// 由终端组得到所有频道
							List<AppointmentChannelInfoEntity> channels = findByProperty(
									AppointmentChannelInfoEntity.class,
									"authortiyGroupCid", t.getId());
							for (AppointmentChannelInfoEntity c : channels) {
								// 由频道得到唯一会议
								MeetingInfoEntity meeting = systemService.get(
										MeetingInfoEntity.class,
										StringUtil.isEmpty(c.getMeetingid()) ? "" : c.getMeetingid());
								if (meeting != null
										&& !meeting.getMeetingstate().toString().equals(SystemType.MEETING_STATE_4) && !meeting.getMeetingstate().toString().equals(SystemType.MEETING_STATE_5)) {
									meetings.add(meeting);
									// 当频道所属会议未结束时
									ConfCodecInfoEntity codec1 = get(
											ConfCodecInfoEntity.class,
											c.getCodec1id()), codec2 = get(
											ConfCodecInfoEntity.class,
											c.getCodec2id());
									if (null != codec1) {
										String codec1url = codec1.getCodecurl();
										if (StringUtil.isNotEmpty(codec1url)) {
											String strUUID = meeting.getId()
													+ "_" + codec1.getId();
											record += "\n  <" + prefixXML
													+ "Program id=\"" + strUUID
													+ "\">";
											record += "\n     <meetingid>"
													+ meeting.getId()
													+ "</meetingid>";
											record += "\n     <fullname>"
													+ codec1url + "</fullname>";
											record += "\n     <subject> "
													+ meeting.getSubject()
													+ "  </subject> ";
											record += "\n     <date>"
													+ DataUtils.datetimeFormat
															.format(meeting
																	.getBillstarttime())
													+ "</date> ";
											record += "\n     <compere>"
													+ meeting.getCompere()
													+ " </compere> ";
											record += "\n     <introduction>"
													+ meeting.getIntroduction()
													+ "</introduction> ";
											record += "\n  </" + prefixXML
													+ "Program> ";
										}
									}
									if (null != codec2) {
										String codec2url = codec2.getCodecurl();
										if (StringUtil.isNotEmpty(codec2url)) {
											String strUUID = meeting.getId()
													+ "_" + codec2.getId();
											record += "\n  <" + prefixXML
													+ "Program id=\"" + strUUID
													+ "\">";
											record += "\n     <meetingid>"
													+ meeting.getId()
													+ "</meetingid>";
											record += "\n     <fullname>"
													+ codec2url + "</fullname>";
											record += "\n     <subject> "
													+ meeting.getSubject()
													+ "  </subject> ";
											record += "\n     <date>"
													+ DataUtils.datetimeFormat
															.format(meeting
																	.getBillstarttime())
													+ "</date> ";
											record += "\n     <compere>"
													+ meeting.getCompere()
													+ " </compere> ";
											record += "\n     <introduction>"
													+ meeting.getIntroduction()
													+ "</introduction> ";
											record += "\n  </" + prefixXML
													+ "Program> ";
										}
									}

									String typeid = meeting.getTypeid()
											.toString();
									String typename = systemService.getType(
											typeid, SystemType.MEETING_TYPE)
											.getTypename();
									if (StringUtil.isEmpty(oldTypeid)) { // 第一次
										oldTypeid = typeid; // 更新typeid
										strXML += "\n<type id='" + typeid
												+ "' name='" + typename + "' >";
									} else if (!oldTypeid.trim().equals(typeid)) { // 出现新的typeid
										oldTypeid = typeid; // 更新typeid
										strXML += "\n</type> "; // 成对，结束上一个typeid节点
										strXML += "\n<type id='" + typeid
												+ "' name='" + typename + "' >"; // 开始一个新节点；
									}

									strXML += record;
								}
							}
						}
					}
					if(meetings.size() > 0){
						strXML += "\n</type> "; // 成对，最后一个typeid节点
					}
				}
			}
		}

		return strXML + "\n  </" + prefixXML + "Programs> ";
	}

	private String getEPG4SectionRec(String mac) {
		String prefixXML = "request";// 点播
		String strXML = "\n<" + prefixXML + "Programs name=\"" + prefixXML
				+ "\">  ";
		String oldTypeid = ""; // EPG节目的类型

		if (StringUtil.isNotEmpty(mac)) {
			// 由mac地址得到唯一终端
			TerminalInfoEntity terminal = findUniqueByProperty(
					TerminalInfoEntity.class, "macaddress", mac);
			if (null != terminal) {
				// 新记录的组合
				String record = "";
				String tid = terminal.getId();
				// 由终端得到所属的所有组
				List<AuthGroupTerminalEntity> groupids = systemService
						.findByProperty(AuthGroupTerminalEntity.class,
								"terminalid", tid);
				VideoPlayConfig vpc = VideoPlayConfig.getInstance();
				for (AuthGroupTerminalEntity agt : groupids) {
					// 由终端组得到所有可见录制片段
					List<VodSectionRecordEntity> vods = findByProperty(
							VodSectionRecordEntity.class, "authortiyGroupCid",
							agt.getAuthid());
					if (vods != null && vods.size() > 0) {
						for (VodSectionRecordEntity v : vods) {
							// 由片段得到会议
							MeetingInfoEntity meeting = systemService.get(
									MeetingInfoEntity.class, StringUtil.isEmpty(v.getMeetingid()) ? "" : v.getMeetingid());
							if (meeting != null && v.getRecState().toString().equals(SystemType.REC_STATE_5)) {
								String rtspId = v.getRtspsrvid();
								String sectionID = v.getId();
								String strRecodFilename = v.getFilename() + "."
										+ vpc.videoplay_videoFileExt;
								String strUrl = getRtspUrl(rtspId)
										+ v.getRtsprelativedir()
										+ File.separator + strRecodFilename;
								String strbillID = meeting.getId();
								String strbillStartTime = DataUtils.datetimeFormat
										.format(v.getRecStartDt());
								String strsubject = this.NulltoSpace(meeting
										.getSubject());
								String strcompere = this.NulltoSpace(meeting
										.getCompere());
								String strintroduction = this
										.NulltoSpace(meeting.getIntroduction());
								String strtypeid = meeting.getTypeid()
										.toString();
								String strtypeName = systemService.getType(
										strtypeid, SystemType.MEETING_TYPE)
										.getTypename();

								record = "\n  <" + prefixXML + "Program id=\""
										+ sectionID + "\">";
								record += "\n     <meetingid>" + strbillID
										+ "</meetingid>";
								record += "\n     <fullname>" + strUrl
										+ "</fullname>";
								record += "\n     <subject> " + strsubject
										+ "  </subject> ";
								record += "\n     <date>" + strbillStartTime
										+ "</date> ";
								record += "\n     <compere>" + strcompere
										+ " </compere> ";
								record += "\n     <introduction>"
										+ strintroduction + "</introduction> ";
								record += "\n  </" + prefixXML + "Program> ";
								//
								if (oldTypeid == null
										|| oldTypeid.trim().equals("")) { // 第一次
									oldTypeid = strtypeid; // 更新typeid
									strXML = strXML + "\n<type id='"
											+ strtypeid + "' name='"
											+ strtypeName + "' >";
								} else if (!oldTypeid.trim().equals(strtypeid)) { // 出现新的typeid
									oldTypeid = strtypeid; // 更新typeid
									strXML = strXML + "\n</type> "; // 成对，结束上一个typeid节点
									strXML = strXML + "\n<type id='"
											+ strtypeid + "' name='"
											+ strtypeName + "' >"; // 开始一个新节点；
								}
								strXML = strXML + record;
							}
						}
						strXML = strXML + "\n</type> "; // 成对，最后一个typeid节点
					}
				}
			}

		}

		return strXML + "\n  </" + prefixXML + "Programs> ";
	}

	private String getRtspUrl(String strRtspID) {
		String rtspUrl = "";
		ConfRtspSrvInfoEntity rtsp = get(ConfRtspSrvInfoEntity.class, strRtspID);
		if (null != rtsp) {
			rtspUrl = rtsp.getRtspurl();
		}

		return rtspUrl;
	}

	private String NulltoSpace(String str) {
		if (StringUtil.isEmpty(str)) {
			return " ";
		}
		return str;
	}

	/**
	 * 遍历Cache,判断是否存在超时的心跳消息条目； 若有，代表这个终端，其实已退出系统；
	 * 
	 * @param timeout
	 *            超时的时长，单位是毫秒
	 * @return
	 */
	@Override
	public boolean LogoutTask(long timeout) {
		boolean flag = false;
		Iterator<String> iter = MAC_STATE.keySet().iterator();
		while (iter.hasNext()) {
			String strKey = (String) iter.next();
			String strValue = MAC_STATE.get(strKey);
			TerminalInfoEntity terminal = findUniqueByProperty(
					TerminalInfoEntity.class, "macaddress", strKey);
			if (StringUtil.isNotEmpty(strValue)) {
				long lngTime = Long.parseLong(strValue);
				long lngNow = DataUtils.getMillis();
				long betweenms = lngNow - lngTime;
				if (betweenms > timeout) { // 超时处理
					MAC_STATE.remove(strKey);
					terminal.setStatus(Integer
							.valueOf(SystemType.TERMINAL_STATE_1));// 下线处理
					terminal.setNowvideo("");
					terminal.setSubject("");
					updateEntitie(terminal);
				}
			}
		}
		return flag;
	}
}