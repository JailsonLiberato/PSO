package ui;

import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

import business.FunctionType;
import business.PSOService;
import model.ChartItem;
import model.Function;
import model.TopologyType;

public class Main {

	public static void main(String[] args) {

		PSOService psoService = new PSOService();

		Thread threadSphere = new Thread() {
			public void run() {
				List<ChartItem> chartItemGlobal = psoService.executeFunctionByTopology(FunctionType.SPHERE,
						TopologyType.GLOBAL);
				List<ChartItem> chartItemLocal = psoService.executeFunctionByTopology(FunctionType.SPHERE,
						TopologyType.LOCAL);
				List<ChartItem> chartItemFocal = psoService.executeFunctionByTopology(FunctionType.SPHERE,
						TopologyType.FOCAL);
				Function function = new Function(chartItemGlobal, chartItemLocal, chartItemFocal);
				JFreeChartUtil.createChart(FunctionType.SPHERE, function);
			}
		};

		threadSphere.start();

		Thread threadRastrigin = new Thread() {
			public void run() {
				List<ChartItem> chartItemGlobal = psoService.executeFunctionByTopology(FunctionType.RASTRINGIN,
						TopologyType.GLOBAL);
				List<ChartItem> chartItemLocal = psoService.executeFunctionByTopology(FunctionType.RASTRINGIN,
						TopologyType.LOCAL);
				List<ChartItem> chartItemFocal = psoService.executeFunctionByTopology(FunctionType.RASTRINGIN,
						TopologyType.FOCAL);
				Function function = new Function(chartItemGlobal, chartItemLocal, chartItemFocal);
				JFreeChartUtil.createChart(FunctionType.RASTRINGIN, function);
			}
		};

		threadRastrigin.start();

		Thread threadRosenbrock = new Thread() {
			public void run() {
				List<ChartItem> chartItemGlobal = psoService.executeFunctionByTopology(FunctionType.ROSENBROCK,
						TopologyType.GLOBAL);
				List<ChartItem> chartItemLocal = psoService.executeFunctionByTopology(FunctionType.ROSENBROCK,
						TopologyType.LOCAL);
				List<ChartItem> chartItemFocal = psoService.executeFunctionByTopology(FunctionType.ROSENBROCK,
						TopologyType.FOCAL);
				Function function = new Function(chartItemGlobal, chartItemLocal, chartItemFocal);
				JFreeChartUtil.createChart(FunctionType.ROSENBROCK, function);
			}
		};

		threadRosenbrock.start();
		
//		List<Double> values = new ArrayList<>();
//		values.add(1.0);
//		values.add(1.2);
//		values.add(1.3);
//		 final BoxplotUtil demo = new BoxplotUtil("Sphere", values);
//		  demo.pack();
//		  RefineryUtilities.centerFrameOnScreen(demo);
//		  demo.setVisible(true);

	}
}
