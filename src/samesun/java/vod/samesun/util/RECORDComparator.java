package vod.samesun.util;

import java.util.Comparator;

import vod.entity.confcodecinfo.ConfCodecInfoEntity;

public class RECORDComparator implements Comparator<ConfCodecInfoEntity> {

	@Override
	public int compare(ConfCodecInfoEntity o1, ConfCodecInfoEntity o2) {
		String name1 = o1.getName(), name2 = o2.getName();
		
		return name1.compareToIgnoreCase(name2);
	}

}
