package ui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartPanel;
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

import business.FunctionType;
import model.Function;
import model.TopologyType;

public class BoxplotUtil extends ApplicationFrame  {
	private static final long serialVersionUID = -1666802072253471714L;
	/** Access to logging facilities. */
    private static final LogContext LOGGER = Log.createContext(BoxplotUtil.class);

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public BoxplotUtil(FunctionType functionType, Function function) {

        super(functionType.getName());
        
        final BoxAndWhiskerCategoryDataset dataset = createSampleDataset(function);

        final CategoryAxis xAxis = new CategoryAxis("Type");
        final NumberAxis yAxis = new NumberAxis("Value");
        yAxis.setAutoRangeIncludesZero(false);
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(false);
        renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        final JFreeChart chart = new JFreeChart(
            functionType.getName(),
            new Font("SansSerif", Font.BOLD, 14),
            plot,
            true
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 450));
        setContentPane(chartPanel);
        setVisible(true);

    }

    /**
     * Creates a sample dataset.
     * @param function 
     * 
     * @return A sample dataset.
     */
    private BoxAndWhiskerCategoryDataset createSampleDataset(Function function) {
        
        final DefaultBoxAndWhiskerCategoryDataset dataset 
            = new DefaultBoxAndWhiskerCategoryDataset();
        
        
        dataset.add(Function.chartItemToValues(function.getGlobals()), "Series " , TopologyType.GLOBAL.getName());
        dataset.add(Function.chartItemToValues(function.getLocals()), "Series " , TopologyType.LOCAL.getName());
        dataset.add(Function.chartItemToValues(function.getFocals()), "Series " , TopologyType.FOCAL.getName());
            

        return dataset;
    }
}
