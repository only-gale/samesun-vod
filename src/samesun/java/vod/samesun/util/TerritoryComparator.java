package vod.samesun.util;

import java.util.Comparator;

import org.jeecgframework.web.system.pojo.base.TSTerritory;
/**
 * 按照直播会话开始时间倒序排列
 */
public class TerritoryComparator implements Comparator<TSTerritory> {

	@Override
	public int compare(TSTerritory o1, TSTerritory o2) {
		Integer c1 = new Integer(o1.getTerritorySort());
		Integer c2 = new Integer(o2.getTerritorySort());
		return c1.compareTo(c2);
	}

}
