package ui;

import java.awt.Color;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.BoxAndWhiskerCalculator;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset;
import org.jfree.ui.ApplicationFrame;

public class BoxplotUtil extends ApplicationFrame {
	private static final long serialVersionUID = -4797299567446771899L;

	public BoxplotUtil(String title, List<Double> values) {
		super(title);

		final BoxAndWhiskerXYDataset dataset = createDataset(values);
		final JFreeChart chart = createChart(dataset);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 300));
		setContentPane(chartPanel);

	}

	private BoxAndWhiskerXYDataset createDataset(List<Double> values) {

		DefaultBoxAndWhiskerXYDataset dataset = new DefaultBoxAndWhiskerXYDataset("");
		for (int i = 0; i < values.size(); i++) {
			dataset.add(new Date(), BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(values));
		}
		return dataset;
	}

	private JFreeChart createChart(final BoxAndWhiskerXYDataset dataset) {
		JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(this.getTitle(), "Communication strategy", "Best fitness", dataset,
				true);
		chart.setBackgroundPaint(new Color(249, 231, 236));

		return chart;
	}
}
