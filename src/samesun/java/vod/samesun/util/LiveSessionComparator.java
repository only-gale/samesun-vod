package vod.samesun.util;

import java.util.Comparator;
import java.util.Date;

import vod.entity.meetinglivesession.MeetingLiveSessionEntity;
/**
 * 按照直播会话开始时间倒序排列
 */
public class LiveSessionComparator implements Comparator<MeetingLiveSessionEntity> {

	@Override
	public int compare(MeetingLiveSessionEntity o1, MeetingLiveSessionEntity o2) {
		Date beginO1 = o1.getBegindt();
		Date beginO2 = o2.getBegindt();
		return beginO2.compareTo(beginO1);
	}

}
