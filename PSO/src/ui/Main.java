package ui;

import java.util.List;

import business.FunctionType;
import business.PSOService;
import model.ChartItem;
import model.Function;
import model.TopologyType;

public class Main {

	public static void main(String[] args) {

		PSOService psoService = new PSOService();

//		 List<ChartItem> chartItemGlobal = psoService.executeFunctionByTopology(FunctionType.SPHERE, TopologyType.GLOBAL);
//		 List<ChartItem> chartItemLocal = psoService.executeFunctionByTopology(FunctionType.SPHERE, TopologyType.LOCAL);
//		 List<ChartItem> chartItemFocal = psoService.executeFunctionByTopology(FunctionType.SPHERE, TopologyType.FOCAL);
//		 Function function = new Function(chartItemGlobal, chartItemLocal, chartItemFocal);
//		 JFreeChartUtil.createChart(FunctionType.SPHERE, function);
//		 
//		 chartItemGlobal = psoService.executeFunctionByTopology(FunctionType.RASTRINGIN, TopologyType.GLOBAL);
//		 chartItemLocal = psoService.executeFunctionByTopology(FunctionType.RASTRINGIN, TopologyType.LOCAL);
//		 chartItemFocal = psoService.executeFunctionByTopology(FunctionType.RASTRINGIN, TopologyType.FOCAL);
//		 function = new Function(chartItemGlobal, chartItemLocal, chartItemFocal);
//		 JFreeChartUtil.createChart(FunctionType.RASTRINGIN, function);
//		 
//		chartItemGlobal = psoService.executeFunctionByTopology(FunctionType.ROSENBROCK, TopologyType.GLOBAL);
//		chartItemLocal = psoService.executeFunctionByTopology(FunctionType.ROSENBROCK, TopologyType.LOCAL);
//		chartItemFocal = psoService.executeFunctionByTopology(FunctionType.ROSENBROCK, TopologyType.FOCAL);
//		function = new Function(chartItemGlobal, chartItemLocal, chartItemFocal);
//		JFreeChartUtil.createChart(FunctionType.ROSENBROCK, function);

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

	}
}
