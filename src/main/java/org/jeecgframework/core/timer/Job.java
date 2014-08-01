package org.jeecgframework.core.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import vod.service.heartrequest.HeartRequestServiceI;
import vod.service.meetinginfo.MeetingInfoServiceI;
import vod.service.traininginfo.TrainingInfoServiceI;

@Component
public class Job {

	@Autowired
	private HeartRequestServiceI heartRequestService;
	
	@Autowired
	private MeetingInfoServiceI meetingInfoService;
	
	@Autowired
	private TrainingInfoServiceI trainingInfoService;
 
//    @Scheduled(cron="*/10 * * * * *") 
//    public void s10(){
//        org.jeecgframework.core.util.LogUtil.info("==== 十秒执行一次=======10s");
//    }
//    
//    @Scheduled(cron="0 */1 * * * *") 
//    public void m1(){
//        org.jeecgframework.core.util.LogUtil.info("1m");
//    }
    
    /**
     * 每天1点执行一次
     * */
    @Scheduled(cron="0 0 1 * * ?") 
    public void oneOClockPerDay(){
        org.jeecgframework.core.util.LogUtil.info("1h");
    }
    
    /**
     *  心跳Heatbeat检测；超出一定时间（timeout）,没有心跳的情况；
     *  每20秒检测一次
     */
    @Scheduled(cron="0/20 * * * * ?")
	public void HeartBeatlogoutTask() {
		// System.out.println("================ 心跳检测 ============== ");
		long timeoutL = 60 * 1000L; // 参数timeout:50秒
		heartRequestService.LogoutTask(timeoutL);
		
		//检测预约录制
		meetingInfoService.autoRecordTask();
		
		trainingInfoService.autoRecordTask();
	}
    
}