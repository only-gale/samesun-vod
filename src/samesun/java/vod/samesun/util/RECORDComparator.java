package vod.samesun.util;

import java.util.Comparator;

import vod.entity.confrecordsrvinfo.ConfRecordSrvInfoEntity;

public class RECORDComparator implements Comparator<ConfRecordSrvInfoEntity> {

	@Override
	public int compare(ConfRecordSrvInfoEntity o1, ConfRecordSrvInfoEntity o2) {
		String name1 = o1.getName(), name2 = o2.getName();
		
		return name1.compareToIgnoreCase(name2);
	}

}
