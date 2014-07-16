package vod.samesun.util;

import java.util.Comparator;

import vod.entity.confrtspsrvinfo.ConfRtspSrvInfoEntity;

public class RTSPComparator implements Comparator<ConfRtspSrvInfoEntity> {

	@Override
	public int compare(ConfRtspSrvInfoEntity o1, ConfRtspSrvInfoEntity o2) {
		String name1 = o1.getName(), name2 = o2.getName();
		
		return name1.compareToIgnoreCase(name2);
	}

}
