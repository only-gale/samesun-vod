package vod.samesun.util;

import java.util.Comparator;

import org.jeecgframework.web.system.pojo.base.TSType;

public class TypeComparator implements Comparator<TSType> {

	@Override
	public int compare(TSType o1, TSType o2) {
		String code1 = o1.getTypecode();
		String code2 = o2.getTypecode();
		return code1.compareTo(code2);
	}

}
