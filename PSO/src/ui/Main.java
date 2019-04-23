package ui;

import java.util.List;

import business.ChartItemService;
import business.PSOService;
import business.function.FitnessFunction;
import business.function.RastringinFunction;
import business.function.RosenbrockFunction;
import business.function.SphereFunction;
import business.topology.FocalTopology;
import business.topology.GlobalTopology;
import business.topology.LocalTopology;
import model.Function;
import util.Constants;

public class Main {
	PSOService psoService = new PSOService();
	ChartItemService chartItemService = new ChartItemService();

	public static void main(String[] args) {
		Main main = new Main();

		Thread threadSphere = new Thread() {
			public void run() {
				createSphereConstantWeight();
				createSphereLinearDecayWeight();
				createSphereConstrictionCoeffWeight();

			}

			private void createSphereConstantWeight() {
				SphereFunction sphereFunction = new SphereFunction();
				Function function = main.createLineGraph(sphereFunction, false, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_SPHERE_CONSTANT_WEIGHT, function);
			}

			private void createSphereLinearDecayWeight() {
				SphereFunction sphereFunction = new SphereFunction();
				Function function = main.createLineGraph(sphereFunction, true, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_SPHERE_LINEAR_DECAY_WEIGHT, function);
			}

			private void createSphereConstrictionCoeffWeight() {
				SphereFunction sphereFunction = new SphereFunction();
				Function function = main.createLineGraph(sphereFunction, false, true);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_SPHERE_FUNCTION_CONSTRICTION_COEFF_WEIGHT, function);
			}

		};

		threadSphere.start();

		Thread threadRastrigin = new Thread() {
			public void run() {
				createRastringinConstantWeight();
				createRastringinLinearDecayWeight();
				createRastringinConstrictionCoeffWeight();
			}

			private void createRastringinConstantWeight() {
				RastringinFunction rastringinFunction = new RastringinFunction();
				Function function = main.createLineGraph(rastringinFunction, false, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_RASTRINGIN_CONSTANT_WEIGHT, function);
			}

			private void createRastringinLinearDecayWeight() {
				RastringinFunction rastringinFunction = new RastringinFunction();
				Function function = main.createLineGraph(rastringinFunction, true, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_RASTRINGIN_LINEAR_DECAY_WEIGHT, function);
			}

			private void createRastringinConstrictionCoeffWeight() {
				RastringinFunction rastringinFunction = new RastringinFunction();
				Function function = main.createLineGraph(rastringinFunction, false, true);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_RASTRINGIN_FUNCTION_CONSTRICTION_COEFF_WEIGHT, function);
			}

		};

		threadRastrigin.start();

		Thread threadRosenbrock = new Thread() {
			public void run() {
				createRosenbrockConstantWeight();
				createRosenbrockLinearDecayWeight();
				createRosenbrockConstrictionCoeffWeight();

			}

			private void createRosenbrockConstantWeight() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
				Function function = main.createLineGraph(rosenbrockFunction, false, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_ROSENBROCK_CONSTANT_WEIGHT, function);
			}

			private void createRosenbrockLinearDecayWeight() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
				Function function = main.createLineGraph(rosenbrockFunction, true, false);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_ROSENBROCK_LINEAR_DECAY_WEIGHT, function);
			}

			private void createRosenbrockConstrictionCoeffWeight() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();
				Function function = main.createLineGraph(rosenbrockFunction, false, true);
				JFreeChartUtil.createChart(Constants.FITNESS_CURVE_ROSENBROCK_FUNCTION_CONSTRICTION_COEFF_WEIGHT, function);
			}

		};

		threadRosenbrock.start();

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
