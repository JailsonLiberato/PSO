package ui;

import java.util.List;

import business.ChartItemService;
import business.PSOService;
import business.function.RastringinFunction;
import business.function.RosenbrockFunction;
import business.function.SphereFunction;
import business.topology.FocalTopology;
import business.topology.GlobalTopology;
import business.topology.LocalTopology;
import model.Function;
import util.Constants;

public class Main {

	public static void main(String[] args) {

		PSOService psoService = new PSOService();
		ChartItemService chartItemService = new ChartItemService();

		Thread threadSphere = new Thread() {
			public void run() {
				SphereFunction sphereFunction = new SphereFunction();

				GlobalTopology globalTopology = new GlobalTopology();
				List<Double> values = psoService.executePSO(sphereFunction, globalTopology);
				Function function = new Function();
				function.setGlobals(chartItemService.convertFitnessArrayToChartItems(globalTopology, values));

				LocalTopology localTopology = new LocalTopology();
				values = psoService.executePSO(sphereFunction, localTopology);
				function.setLocals(chartItemService.convertFitnessArrayToChartItems(localTopology, values));

				FocalTopology focalTopology = new FocalTopology();
				values = psoService.executePSO(sphereFunction, focalTopology);
				function.setFocals(chartItemService.convertFitnessArrayToChartItems(focalTopology, values));

				JFreeChartUtil.createChart(Constants.SPHERE_CONSTANT_WEIGHT, function);
			}
		};

		threadSphere.start();

		Thread threadRastrigin = new Thread() {
			public void run() {
				RastringinFunction rastringinFunction = new RastringinFunction();

				GlobalTopology globalTopology = new GlobalTopology();
				List<Double> values = psoService.executePSO(rastringinFunction, globalTopology);
				Function function = new Function();
				function.setGlobals(chartItemService.convertFitnessArrayToChartItems(globalTopology, values));

				LocalTopology localTopology = new LocalTopology();
				values = psoService.executePSO(rastringinFunction, localTopology);
				function.setLocals(chartItemService.convertFitnessArrayToChartItems(localTopology, values));

				FocalTopology focalTopology = new FocalTopology();
				values = psoService.executePSO(rastringinFunction, focalTopology);
				function.setFocals(chartItemService.convertFitnessArrayToChartItems(focalTopology, values));

				JFreeChartUtil.createChart(Constants.RASTRINGIN_CONSTANT_WEIGHT, function);
			}
		};

		threadRastrigin.start();

		Thread threadRosenbrock = new Thread() {
			public void run() {
				RosenbrockFunction rosenbrockFunction = new RosenbrockFunction();

				GlobalTopology globalTopology = new GlobalTopology();
				List<Double> values = psoService.executePSO(rosenbrockFunction, globalTopology);
				Function function = new Function();
				function.setGlobals(chartItemService.convertFitnessArrayToChartItems(globalTopology, values));


				LocalTopology localTopology = new LocalTopology();
				values = psoService.executePSO(rosenbrockFunction, localTopology);
				function.setLocals(chartItemService.convertFitnessArrayToChartItems(localTopology, values));

				FocalTopology focalTopology = new FocalTopology();
				values = psoService.executePSO(rosenbrockFunction, focalTopology);
				function.setFocals(chartItemService.convertFitnessArrayToChartItems(focalTopology, values));

				JFreeChartUtil.createChart(Constants.ROSENBROCK_CONSTANT_WEIGHT, function);
				
			}
		};

		threadRosenbrock.start();

	}
}
