package ui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import business.function.FitnessFunction;
import model.ChartItem;
import model.Function;

public class JFreeChartUtil {

	private static final String FILE_PATH = "files/";
	private static final String FILE_EXTENSION = ".jpeg";
	private static final int HEIGHT_GRAPH = 480;
	private static final int WIDTH_GRAPH = 640;
	private static final String FITNESS = "Fitness";
	private static final String NUMBER_OF_ITERATIONS = "Number of iterations";

	public static void createChart(FitnessFunction fitnessFunction, Function function) {
		try {
			DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
			List<ChartItem> globals = function.getGlobals();
			List<ChartItem> locals = function.getLocals();
			List<ChartItem> focals = function.getFocals();

			for (ChartItem chartItem : globals) {
				line_chart_dataset.addValue(chartItem.getValue(), chartItem.getTopology().toString(),
						chartItem.getIteration());

			}

			for (ChartItem chartItem : locals) {
				line_chart_dataset.addValue(chartItem.getValue(), chartItem.getTopology().toString(),
						chartItem.getIteration());

			}

			for (ChartItem chartItem : focals) {
				line_chart_dataset.addValue(chartItem.getValue(), chartItem.getTopology().toString(),
						chartItem.getIteration());

			}

			JFreeChart lineChartObject = ChartFactory.createLineChart(fitnessFunction.toString(), NUMBER_OF_ITERATIONS,
					FITNESS, line_chart_dataset, PlotOrientation.VERTICAL, true, true, false);

			// final CategoryPlot plot = lineChartObject.getCategoryPlot();
			// final NumberAxis rangeAxis = new LogarithmicAxis("Log(y)");
			// plot.setRangeAxis(rangeAxis);

			File lineChart = new File(FILE_PATH + fitnessFunction.toString() + FILE_EXTENSION);
			ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, WIDTH_GRAPH, HEIGHT_GRAPH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
