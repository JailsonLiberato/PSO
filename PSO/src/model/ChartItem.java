package model;

import java.util.ArrayList;
import java.util.List;

public class ChartItem {

	private Double value;
	private TopologyType topologyType;
	private Integer iteration;

	public static List<ChartItem> getValuesToGraphLines(List<ChartItem> items) {
		List<ChartItem> list = new ArrayList<>();
		for (int i = 0; i <= 10000; i++) {
			if (i % 1000 == 0) {
				list.add(items.get(i));
			}
		}
		return list;

	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public TopologyType getTopologyType() {
		return topologyType;
	}

	public void setTopologyType(TopologyType topologyType) {
		this.topologyType = topologyType;
	}

	public Integer getIteration() {
		return iteration;
	}

	public void setIteration(Integer iteration) {
		this.iteration = iteration;
	}

}
