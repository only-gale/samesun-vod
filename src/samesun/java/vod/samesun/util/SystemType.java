package vod.samesun.util;

public class SystemType {

	/**会议状态编码*/
	public static String MEETING_STATE = "liveState";
	/**会议状态名称*/
	public static String MEETING_STATE_NAME = "会议状态";
	/**会议状态: 直播中*/
	public static String MEETING_STATE_1 = "1";
	/**会议状态: 直播并录制中*/
	public static String MEETING_STATE_2 = "2";
	/**会议状态: 停止录制*/
	public static String MEETING_STATE_3 = "3";
	/**会议状态: 已结束*/
	public static String MEETING_STATE_4 = "4";
	/**会议状态: 待直播*/
	public static String MEETING_STATE_5 = "5";
	
	/**预约会议状态编码*/
	public static String APP_MEETING_STATE = "state";
	/**预约会议状态名称*/
	public static String APP_MEETING_STATE_NAME = "预约会议状态";
	/**预约会议状态: 新建*/
	public static String APP_MEETING_STATE_1 = "0";
	/**预约会议状态: 启用*/
	public static String APP_MEETING_STATE_2 = "1";
	/**预约会议状态: 过期*/
	public static String APP_MEETING_STATE_3 = "2";
	
	/**会议所属类型编码*/
	public static String APP_MEETING_TYPE = "meetingTyp";
	/**会议所属类型名称*/
	public static String APP_MEETING_TYPE_NAME = "会议类型";
	/**会议所属类型: 直播*/
	public static String APP_MEETING_TYPE_1 = "live";
	/**会议所属类型: 录播*/
	public static String APP_MEETING_TYPE_2 = "record";
	/**会议所属类型: 预约*/
	public static String APP_MEETING_TYPE_3 = "appointment";
	
	/**培训所属类型编码*/
	public static String TRAINING_TYPE = "trainingTp";
	/**培训所属类型名称*/
	public static String TRAINING_TYPE_NAME = "培训类型";
	
	/**是否录制编码*/
	public static String IS_RECORD_TYPE = "isRecord";
	/**是否录制名称*/
	public static String IS_RECORD_TYPE_NAME = "是否录制";
	/**否*/
	public static String IS_RECORD_TYPE_0 = "0";
	/**是*/
	public static String IS_RECORD_TYPE_1 = "1";
	
	/**LIVE类型编码*/
	public static String LIVE_TYPE = "liveType";
	/**LIVE类型名称*/
	public static String LIVE_TYPE_NAME = "LIVE类型";
	/**直播*/
	public static String LIVE_TYPE_1 = "1";
	/**点播*/
	public static String LIVE_TYPE_2 = "0";
	
	/**编码器是否启用编码*/
	public static String CODEC_AVILABLE = "isAvilable";
	/**编码器是否启用名称*/
	public static String CODEC_AVILABLE_NAME = "编码器是否启用";
	/**编码器是否启用: 未启用*/
	public static String CODEC_AVILABLE_0 = "0";
	/**编码器是否启用: 已启用*/
	public static String CODEC_AVILABLE_1 = "1";
	
	/**终端状态编码*/
	public static String TERMINAL_STATE = "tState";
	/**终端状态名称*/
	public static String TERMINAL_STATE_NAME = "终端状态";
	/**终端状态: 未激活*/
	public static String TERMINAL_STATE_0 = "0";
	/**终端状态: 离线*/
	public static String TERMINAL_STATE_1 = "1";
	/**终端状态: 在线*/
	public static String TERMINAL_STATE_2 = "2";
	
	/**会议类型编码*/
	public static String MEETING_TYPE = "meetingTyp";
	/**会议类型名称*/
	public static String MEETING_TYPE_NAME = "会议类型";
	/**会议类型: 公共类*/
	public static String MEETING_TYPE_1 = "1";
	/**会议类型: 专题类*/
	public static String MEETING_TYPE_2 = "2";
	/**会议类型: 讨论类*/
	public static String MEETING_TYPE_3 = "3";
	
	/**会议种类编码*/
	public static String MEETING_RIGHT = "meetingRit";
	/**会议种类名称*/
	public static String MEETING_RIGHT_NAME = "会议种类";
	/**会议种类: 会议*/
	public static String MEETING_RIGHT_1 = "1";
	/**会议种类: 培训*/
	public static String MEETING_RIGHT_2 = "2";
	
	/**预约录制状态编码*/
	public static String APP_RECORD = "appRecord";
	/**预约录制状态名称*/
	public static String APP_RECORD_NAME = "预约录制状态";
	/**预约录制状态: 启用*/
	public static String APP_RECORD_1 = "1";
	/**预约录制状态: 取消*/
	public static String APP_RECORD_2 = "2";
	/**预约录制状态: 过期*/
	public static String APP_RECORD_3 = "3";
	
	/**录制状态编码*/
	public static String REC_STATE = "recState";
	/**录制状态名称*/
	public static String REC_STATE_NAME = "录制状态";
	/**录制状态: 初始化*/
	public static String REC_STATE_0 = "0";
	/**录制状态: 开始录制*/
	public static String REC_STATE_1 = "1";
	/**录制状态: 结束录制*/
	public static String REC_STATE_2 = "2";
	/**录制状态: 录制错误*/
	public static String REC_STATE_3 = "3";
	/**录制状态: 转码错误*/
	public static String REC_STATE_4 = "4";
	/**录制状态: 成功*/
	public static String REC_STATE_5 = "5";
}
