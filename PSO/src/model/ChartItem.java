package model;

import java.util.ArrayList;
import java.util.List;

import business.topology.Topology;

public class ChartItem {

	private Double value;
	private Topology topology;
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

	public Topology getTopology() {
		return topology;
	}

	public void setTopology(Topology topology) {
		this.topology = topology;
	}

	public Integer getIteration() {
		return iteration;
	}

	public void setIteration(Integer iteration) {
		this.iteration = iteration;
	}

}
