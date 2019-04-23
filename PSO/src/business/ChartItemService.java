package business;

import java.util.ArrayList;
import java.util.List;

import business.topology.Topology;
import model.ChartItem;

public class ChartItemService {

	public List<ChartItem> convertFitnessArrayToChartItems(Topology topology, List<Double> fitnessArray) {
		List<ChartItem> chartItems = new ArrayList<>();
		for (int i = 0; i < fitnessArray.size(); i++) {
			chartItems.add(createChatItem(i, topology, fitnessArray.get(i)));
		}
		return chartItems;
	}

	private ChartItem createChatItem(int iteration, Topology topology, Double value) {
		ChartItem chartItem = new ChartItem();
		chartItem.setIteration(iteration);
		chartItem.setTopology(topology);
		chartItem.setValue(value);
		return chartItem;
	}

}
