package vod.service.impl.livesectionrecord;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DataUtils;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class LiveSectionRecordServiceImpl extends CommonServiceImpl implements LiveSectionRecordServiceI {

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
			String sessionID, String Codec, String Priority) {
		String strRecordSrvID="", strRtspSrvID="";
		//据Codec可以查询到相应的RecordSrvID,RtspSrvID
    	Map<String,Object> srvsMap = confRecordSrvInfoService.getRecordSrvAndRtspSrv(Codec);
    	ConfRecordSrvInfoEntity recordSrv = (ConfRecordSrvInfoEntity) srvsMap.get("ConfRecordsrvInfoPage");
    	if(null != recordSrv){
    		strRecordSrvID = recordSrv.getId();
    	}
    	ConfRtspSrvInfoEntity rtspSrv = (ConfRtspSrvInfoEntity) srvsMap.get("ConfRtspsrvInfoPage");
    	if(null != rtspSrv){
    		strRtspSrvID = rtspSrv.getId();
    	}
    	
    	LiveSectionRecordEntity liveSection = new LiveSectionRecordEntity();
    	liveSection.setMeetingid(meetingId);
    	liveSection.setChannelid(channelID);
    	liveSection.setSessionid(sessionID);
    	liveSection.setCodecpriorityflg(new Integer(Priority));
    	liveSection.setCodecsrvid(Codec);
    	liveSection.setRecordsrvid(strRecordSrvID);
    	liveSection.setRtspsrvid(strRtspSrvID);
    	try {
			liveSection.setRecStartDt(DataUtils.parseDate(DataUtils.datetimeFormat.format(DataUtils.getDate()), DataUtils.datetimeFormat.toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	liveSection.setRtsprelativedir(DataUtils.yyyyMM.format(DataUtils.getDate()));
    	save(liveSection);
    	return liveSection.getId();
	}

	@Override
	public String StartChannelSectionRecord(String meetingId) {
		String resultStr = "";
		MeetingInfoEntity t = meetingInfoService.get(MeetingInfoEntity.class, meetingId);
		t.setMeetingstate(new Integer(SystemType.MEETING_STATE_2));
		meetingInfoService.updateEntitie(t);
		
		//创建直播session
		MeetingLiveSessionEntity liveSession = meetingLiveSessionService.create(meetingId);
		
		//获取该直播会议所有需要录制的编码器(非重复)
		List<ConfCodecInfoEntity> recordCodecs = confCodecInfoService.getCodecsNeed2Record(t);
		List<AppointmentChannelInfoEntity> channels = meetingInfoService.findByProperty(AppointmentChannelInfoEntity.class, "meetingid", meetingId);
		for(AppointmentChannelInfoEntity c : channels){
			Integer isrecord1 = c.getIsrecord1(), isrecord2 = c.getIsrecord2();
			String codec1id = c.getCodec1id(), codec2id = c.getCodec2id();
			if(isrecord1 == new Integer(SystemType.IS_RECORD_TYPE_1) && StringUtil.isNotEmpty(codec1id)){
				
				SectionRecord4DB(meetingId, c.getId(), liveSession.getId(), isrecord1.toString(), "1");
			}
			if(isrecord2 == new Integer(SystemType.IS_RECORD_TYPE_1) && StringUtil.isNotEmpty(codec2id)){
				
				SectionRecord4DB(meetingId, c.getId(), liveSession.getId(), isrecord2.toString(), "1");
			}
		}
		
		for(ConfCodecInfoEntity codec : recordCodecs){
			//同一会议的同一个Codec 设置同文件名； 这样同一文件名，只发送一次Telnet
        	String fileNameUUID= UUID.randomUUID().toString();
        	String SqlupdateFilename= " update `meeting_live_section_record` set FileName='" + fileNameUUID + "' where meetingID='" + meetingId + "' and Rec_State = 0 "+
                    " and CodecSrvID='" + codec.getId() + "'" ;
        	updateBySqlString(SqlupdateFilename);
        	//4.同时将这个File作Telnet处理；
        	resultStr += SectionRecord4Telnet(fileNameUUID, codec.getId());
		}
		
		return resultStr;
	}
	
	/**
	 * 
	 * @param Obid  分段录制记录的ID号，对应telnet的文件名
	 * @param Codec codec服务器Id,可以查询出录制服务器ID及RtspServer服务器ID
	 * @return  返回Json String 
	 */
	private String SectionRecord4Telnet(String meetingId, String codecId){
		String strMessage="Telnet通讯成功!";
		String strIP="";
 	    String strPort="";
 	    String codecName="";
 	    String result ="";
 	    String codecUrl="";
 	    ConfCodecInfoEntity codecinfo = null;
 	    ConfRecordSrvInfoEntity recordsrv = null;
 	    Map<String, Object> srvsMap= confRecordSrvInfoService.getRecordSrvAndRtspSrv(codecId);
    	if (srvsMap!=null){
    		recordsrv = (ConfRecordSrvInfoEntity) srvsMap.get("ConfRecordsrvInfoPage");
    		if (recordsrv != null){
    			strIP = recordsrv.getIpaddress();
    			strPort = String.valueOf(recordsrv.getPort());
    		}
    		codecinfo = (ConfCodecInfoEntity) srvsMap.get("ConfCodecInfoPage");
    		if (codecinfo != null){
    			codecName = codecinfo.getName();
    		}
    	}
    	
    	NetTelnet telnet = new NetTelnet(strIP,strPort);
    	if (telnet.isAvailable()){
    		codecUrl = codecinfo.getCodecurl();
    		String reportMessage = ReportTelnetMessageStartRec(meetingId, codecUrl);
    		result = telnet.sendCommand(reportMessage);
    		
    		if (resultTelnetMessage(result)){
    			strMessage = "Codec:为"+codecName+ "录制成功 ！ ";
    		}else{
    			strMessage = "Codec:为"+codecName+" 录制失败 ！";
    		}
    	}else{
    		strMessage = "Codec:为"+codecName+",telnet通讯不正常，无法链接，请与技术支持人员联系!";
    	}
    	
    	//最后一定要关闭
    	telnet.disconnect();
		return strMessage;
	}
	
	private String ReportTelnetMessageStartRec(String recordObid,String CodecURL){
		   String filename="000000000000000000000000000000000000"; //文件名的长度为36位；
		   String result="";
		   if (CodecURL==null)  CodecURL="rtp://";	   
		   if (recordObid!=null){
			  int len=recordObid.length();
			  if (len==36){
				  filename=recordObid;
			  }
			  else if (len<36) { //补空格；
				  int len0=  (36- recordObid.length());
				   String prifix="";
				   for (int i=0;i<len0;i++){
					prifix+="0";   
				   }
				   filename=prifix+recordObid;
			  }
		   }	  
		  // 格式组成："S"+Total LEN+FileName(36)+YearMonth(6位)+CodecURL+"E#"
		   String yearMonth= DataUtils.yyyyMM.format(new Date());
		   int intTotalLen= 36+6+CodecURL.length(); //Total Len
		   String strTotalLen="0000"+String.valueOf(intTotalLen);
		   strTotalLen=strTotalLen.substring(strTotalLen.length()-4); //只保留4位
		   //
		   result="S"+strTotalLen+filename+yearMonth+CodecURL+"E#";	  
		   return result;	   
	   }
	
	
	/**
    * 
    * 结果（成功或失败）：0：代表成功；1：代表失败；
    *  其上示例结成的格式为：“S0000000000000000013699288866810010011E#“,代表返回失败；
    *  形如：“E0000000000000000013699288866810010010S#“代表成功；
    * @param strtelnetResult
    * @return  true:表示成功；false:表示失败；
    */
	private boolean resultTelnetMessage(String strtelnetResult){
		   String temp="";
		   if (strtelnetResult!=null&& strtelnetResult.length()>36){
			  temp=strtelnetResult.substring( strtelnetResult.length()-3); 
		   }
		   //
		   if (temp!=null&&temp.equals("0E#")){
			   return true;
		   } else if (temp!=null&&temp.equals("1E#")){
			   return false; 
		   }
		  return false;	   
	   }
}