package ui;

import business.FunctionType;
import business.PSOService;
import model.Function;

public class Main {

	public static void main(String[] args) {

		PSOService psoService = new PSOService();

		Thread threadSphere = new Thread() {
			public void run() {
				Function sphereFuncion = psoService.executeFunction(FunctionType.SPHERE);
				JFreeChartUtil.createChart(FunctionType.SPHERE, sphereFuncion);
			}
		};

		threadSphere.start();

		Thread threadRastrigin = new Thread() {
			public void run() {
				Function rastriginFunction = psoService.executeFunction(FunctionType.RASTRINGIN);
				JFreeChartUtil.createChart(FunctionType.RASTRINGIN, rastriginFunction);
			}
		};

		threadRastrigin.start();

		Thread threadRosenbrock = new Thread() {
			public void run() {
				Function rosenbrockFunction = psoService.executeFunction(FunctionType.ROSENBROCK);
				JFreeChartUtil.createChart(FunctionType.ROSENBROCK, rosenbrockFunction);
			}
		};

		threadRosenbrock.start();

	}
}
