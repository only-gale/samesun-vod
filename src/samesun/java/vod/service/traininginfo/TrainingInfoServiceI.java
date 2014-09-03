package vod.service.traininginfo;

import org.jeecgframework.core.common.service.CommonService;

import vod.entity.appointmenttraining.AppointmentTrainingEntity;
import vod.entity.traininginfo.TrainingInfoEntity;

public interface TrainingInfoServiceI extends CommonService{

	/**
	 * 当结束直播时，生成会议历史信息
	 * @param live
	 */
	public void getHistoryFromLive(TrainingInfoEntity live);
	
	public TrainingInfoEntity getTrainingInfoFromAppointment(
			AppointmentTrainingEntity app);
	
	public void autoRecordTask();
}
