package ui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.ChartItem;

public class JFreeChartUtil {

	private static final String FILE_PATH = "files/";
	private static final String FILE_EXTENSION = ".jpeg";
	private static final int HEIGHT_GRAPH = 480;
	private static final int WIDTH_GRAPH = 640;
	private static final String FITNESS = "Fitness";
	private static final String NUMBER_OF_ITERATIONS = "Number of iterations";

	public static void createChart(String nameFunction, List<ChartItem> locais, List<ChartItem> globais,
			List<ChartItem> focais) {
		try {
			DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

			for (ChartItem chartItem : focais) {
				line_chart_dataset.addValue(chartItem.getValue(), chartItem.getTopologyType().getName(),
						chartItem.getIteration());

			}

			for (ChartItem chartItem : focais) {
				line_chart_dataset.addValue(chartItem.getValue(), chartItem.getTopologyType().getName(),
						chartItem.getIteration());

			}

			for (ChartItem chartItem : focais) {
				line_chart_dataset.addValue(chartItem.getValue(), chartItem.getTopologyType().getName(),
						chartItem.getIteration());

			}

			JFreeChart lineChartObject = ChartFactory.createLineChart(nameFunction, NUMBER_OF_ITERATIONS, FITNESS,
					line_chart_dataset, PlotOrientation.VERTICAL, true, true, false);
			File lineChart = new File(FILE_PATH + nameFunction + FILE_EXTENSION);
			ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, WIDTH_GRAPH, HEIGHT_GRAPH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
