package vod.service.impl.appointmenttraining;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.appointmenttraining.AppointmentTrainingServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("appointmentTrainingService")
@Transactional
public class AppointmentTrainingServiceImpl extends CommonServiceImpl implements AppointmentTrainingServiceI {
	
}