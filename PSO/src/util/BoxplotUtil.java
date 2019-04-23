package util;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.util.Log;
import org.jfree.util.LogContext;

import model.ChartItem;
import model.Function;

public class BoxplotUtil extends ApplicationFrame {
	
	private static final long serialVersionUID = 3577030905672499277L;
	private static final String FILE_PATH = "files/";
	private static final String FILE_EXTENSION = ".jpeg";
	private static final int HEIGHT_GRAPH = 480;
	private static final int WIDTH_GRAPH = 640;

	public static void createBoxplot(String name, Map<String, List<ChartItem>> boxPlotValues) {
		new BoxplotUtil(name, boxPlotValues);
	}

	/**
	 * Creates a new demo.
	 *
	 * @param title the frame title.
	 */
	public BoxplotUtil(String name, Map<String, List<ChartItem>> boxPlotValues) {

		super(name);

		try {
			final BoxAndWhiskerCategoryDataset dataset = createSampleDataset(boxPlotValues);

		final CategoryAxis xAxis = new CategoryAxis("Type");
		final NumberAxis yAxis = new NumberAxis("Value");
		yAxis.setAutoRangeIncludesZero(false);
		final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
		renderer.setFillBox(false);
		renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
		final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

		final JFreeChart chart = new JFreeChart(name, new Font("SansSerif", Font.BOLD, 14), plot, true);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(600, 450));
		File lineChart = new File(FILE_PATH + name + FILE_EXTENSION);
			ChartUtilities.saveChartAsJPEG(lineChart, chart, WIDTH_GRAPH, HEIGHT_GRAPH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @param boxPlotValues
	 * 
	 * @return A sample dataset.
	 */
	private BoxAndWhiskerCategoryDataset createSampleDataset(Map<String, List<ChartItem>> boxPlotValues) {

		final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();

		dataset.add(Function.chartItemToValues(boxPlotValues.get("Global")), "Series ", "Global");
		dataset.add(Function.chartItemToValues(boxPlotValues.get("Local")), "Series ", "Local");
		dataset.add(Function.chartItemToValues(boxPlotValues.get("Focal")), "Series ", "Focal");

		return dataset;
	}
}
