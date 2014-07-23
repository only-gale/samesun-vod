package vod.service.impl.traininginfohistory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vod.service.traininginfohistory.TrainingInfoHistoryServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("trainingInfoHistoryService")
@Transactional
public class TrainingInfoHistoryServiceImpl extends CommonServiceImpl implements TrainingInfoHistoryServiceI {
	
}