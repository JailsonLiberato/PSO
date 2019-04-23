package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.ChartItemService;
import business.PSOService;
import business.function.FitnessFunction;
import business.function.RastringinFunction;
import business.function.RosenbrockFunction;
import business.function.SphereFunction;
import business.topology.FocalTopology;
import business.topology.GlobalTopology;
import business.topology.LocalTopology;
import model.ChartItem;
import model.Function;
import util.BoxplotUtil;
import util.Constants;
import util.JFreeChartUtil;

public class Main {
	PSOService psoService = new PSOService();
	ChartItemService chartItemService = new ChartItemService();
	static Main main = new Main();

	public static void main(String[] args) {

		Thread threadSphere = new Thread() {
			public void run() {
				createSphereConstantWeight();
				createSphereBoxPlotConstantWeight();
				createSphereLinearDecayWeight();
				createSphereBoxplotLinearDecayWeight();
				createSphereConstrictionCoeffWeight();
				createSphereBoxplotConstrictionCoeffWeight();

			}

			private void createSphereConstantWeight() {
				SphereFunction sphereFunction = new SphereFunction();
				Function function = main.createLineGraph(sphereFunction, false, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_SPHERE_CONSTANT_WEIGHT, function);
			}

			private void createSphereBoxPlotConstantWeight() {
				SphereFunction sphereFunction = new SphereFunction();
				Map<String, List<ChartItem>> boxPlotValues = main.createBoxPlot(sphereFunction, false, false);
				BoxplotUtil.createBoxplot(Constants.SPHERE_CONSTANT_WEIGHT, boxPlotValues);
			}

			private void createSphereLinearDecayWeight() {
				SphereFunction sphereFunction = new SphereFunction();
				Function function = main.createLineGraph(sphereFunction, true, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_SPHERE_LINEAR_DECAY_WEIGHT, function);
			}

			private void createSphereBoxplotLinearDecayWeight() {
				SphereFunction sphereFunction = new SphereFunction();
				Map<String, List<ChartItem>> boxPlotValues = main.createBoxPlot(sphereFunction, true, false);
				BoxplotUtil.createBoxplot(Constants.SPHERE_LINEAR_DECAY_WEIGHT, boxPlotValues);

			}

			private void createSphereConstrictionCoeffWeight() {
				SphereFunction sphereFunction = new SphereFunction();
				Function function = main.createLineGraph(sphereFunction, false, true);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_SPHERE_FUNCTION_CONSTRICTION_COEFF_WEIGHT, function);
			}

			private void createSphereBoxplotConstrictionCoeffWeight() {
				SphereFunction sphereFunction = new SphereFunction();
				Map<String, List<ChartItem>> boxPlotValues = main.createBoxPlot(sphereFunction, false, true);
				BoxplotUtil.createBoxplot(Constants.SPHERE_FUNCTION_CONSTRICTION_COEFF_WEIGHT, boxPlotValues);
			}

		};

		threadSphere.start();

		Thread threadRastrigin = new Thread() {
			public void run() {
				createRastringinConstantWeight();
				createRastringinBoxplotConstantWeight();
				createRastringinLinearDecayWeight();
				createRastringinBoxplotLinearDecayWeight();
				createRastringinConstrictionCoeffWeight();
				createRastringinBoxplotConstrictionCoeffWeight();
			}

			private void createRastringinConstantWeight() {
				RastringinFunction rastringinFunction = new RastringinFunction();
				Function function = main.createLineGraph(rastringinFunction, false, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_RASTRINGIN_CONSTANT_WEIGHT, function);
			}

			private void createRastringinBoxplotConstantWeight() {
				RastringinFunction rastringinFunction = new RastringinFunction();
				Map<String, List<ChartItem>> boxPlotValues = main.createBoxPlot(rastringinFunction, false, false);
				BoxplotUtil.createBoxplot(Constants.RASTRINGIN_CONSTANT_WEIGHT, boxPlotValues);
			}

			private void createRastringinLinearDecayWeight() {
				RastringinFunction rastringinFunction = new RastringinFunction();
				Function function = main.createLineGraph(rastringinFunction, true, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_RASTRINGIN_LINEAR_DECAY_WEIGHT, function);
			}

			private void createRastringinBoxplotLinearDecayWeight() {
				RastringinFunction rastringinFunction = new RastringinFunction();
				Map<String, List<ChartItem>> boxPlotValues = main.createBoxPlot(rastringinFunction, true, false);
				BoxplotUtil.createBoxplot(Constants.RASTRINGIN_LINEAR_DECAY_WEIGHT, boxPlotValues);
			}

			private void createRastringinConstrictionCoeffWeight() {
				RastringinFunction rastringinFunction = new RastringinFunction();
				Function function = main.createLineGraph(rastringinFunction, false, true);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_RASTRINGIN_FUNCTION_CONSTRICTION_COEFF_WEIGHT,
						function);
			}

			private void createRastringinBoxplotConstrictionCoeffWeight() {
				RastringinFunction rastringinFunction = new RastringinFunction();
				Map<String, List<ChartItem>> boxPlotValues = main.createBoxPlot(rastringinFunction, false, true);
				BoxplotUtil.createBoxplot(Constants.RASTRINGIN_FUNCTION_CONSTRICTION_COEFF_WEIGHT, boxPlotValues);
			}

		};

		threadRastrigin.start();

		Thread threadRosenbrock = new Thread() {
			public void run() {
				createRosenbrockConstantWeight();
				createRosenbrockBoxplotConstantWeight();
				createRosenbrockLinearDecayWeight();
				createRosenbrockBoxplotLinearDecayWeight();
				createRosenbrockConstrictionCoeffWeight();
				createRosenbrockBoxplotConstrictionCoeffWeight();

			}


			private void createRosenbrockConstantWeight() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
				Function function = main.createLineGraph(rosenbrockFunction, false, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_ROSENBROCK_CONSTANT_WEIGHT, function);
			}
			
			private void createRosenbrockBoxplotConstantWeight() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
				Map<String, List<ChartItem>> boxPlotValues = main.createBoxPlot(rosenbrockFunction, false, false);
				BoxplotUtil.createBoxplot(Constants.ROSENBROCK_CONSTANT_WEIGHT, boxPlotValues);
				
			}

			private void createRosenbrockLinearDecayWeight() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
				Function function = main.createLineGraph(rosenbrockFunction, true, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_ROSENBROCK_LINEAR_DECAY_WEIGHT, function);
			}
			
			private void createRosenbrockBoxplotLinearDecayWeight() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
				Map<String, List<ChartItem>> boxPlotValues = main.createBoxPlot(rosenbrockFunction, true, false);
				BoxplotUtil.createBoxplot(Constants.ROSENBROCK_LINEAR_DECAY_WEIGHT, boxPlotValues);
			}

			private void createRosenbrockConstrictionCoeffWeight() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
				Function function = main.createLineGraph(rosenbrockFunction, false, true);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_ROSENBROCK_FUNCTION_CONSTRICTION_COEFF_WEIGHT,
						function);
			}
			
			private void createRosenbrockBoxplotConstrictionCoeffWeight() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
				Map<String, List<ChartItem>> boxPlotValues = main.createBoxPlot(rosenbrockFunction, false, true);
				BoxplotUtil.createBoxplot(Constants.ROSENBROCK_FUNCTION_CONSTRICTION_COEFF_WEIGHT, boxPlotValues);
			}

		};

		threadRosenbrock.start();

	}

	private Map<String, List<ChartItem>> createBoxPlot(FitnessFunction fitnessFunction, boolean linearDecayWeight,
			boolean constrictionCoeffWeight) {
		Map<String, List<ChartItem>> boxPlotValues = new HashMap<>();
		boxPlotValues.put("Global", new ArrayList<>());
		boxPlotValues.put("Local", new ArrayList<>());
		boxPlotValues.put("Focal", new ArrayList<>());
		for(int i=0; i< 30; i++) {
			Function function = main.createLineGraph(fitnessFunction, linearDecayWeight, constrictionCoeffWeight);
			boxPlotValues.get("Global").add(function.getGlobals().get(function.getGlobals().size() - 1));
			boxPlotValues.get("Local").add(function.getLocals().get(function.getLocals().size() -1));
			boxPlotValues.get("Focal").add(function.getFocals().get(function.getFocals().size() - 1));
			
		}
		return boxPlotValues;
	}

	private Function createLineGraph(FitnessFunction fitnessFunction, boolean linearDecayWeight,
			boolean constrictionCoeffWeight) {

		GlobalTopology globalTopology = new GlobalTopology();
		List<Double> values = psoService.executePSO(fitnessFunction, globalTopology, linearDecayWeight,
				constrictionCoeffWeight);
		Function function = new Function();
		function.setGlobals(chartItemService.convertFitnessArrayToChartItems(globalTopology, values));

		LocalTopology localTopology = new LocalTopology();
		values = psoService.executePSO(fitnessFunction, localTopology, linearDecayWeight, constrictionCoeffWeight);
		function.setLocals(chartItemService.convertFitnessArrayToChartItems(localTopology, values));

		FocalTopology focalTopology = new FocalTopology();
		values = psoService.executePSO(fitnessFunction, focalTopology, linearDecayWeight, constrictionCoeffWeight);
		function.setFocals(chartItemService.convertFitnessArrayToChartItems(focalTopology, values));
		return function;
	}
}
