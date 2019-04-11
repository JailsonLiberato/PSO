package ui;

import business.FunctionType;
import business.PSOService;
import model.Function;

public class Main {

	public static void main(String[] args) {
		
		PSOService psoService = new PSOService();
		Function sphereFuncion = psoService.executeFunction(FunctionType.SPHERE);
		Function rastriginFunction = psoService.executeFunction(FunctionType.RASTRINGIN);
		Function rosenbrockFunction = psoService.executeFunction(FunctionType.ROSENBROCK);
		
		JFreeChartUtil.createChart(FunctionType.SPHERE, sphereFuncion);

		JFreeChartUtil.createChart(FunctionType.RASTRINGIN, rastriginFunction);

		JFreeChartUtil.createChart(FunctionType.ROSENBROCK, rosenbrockFunction);
		

	}

}
