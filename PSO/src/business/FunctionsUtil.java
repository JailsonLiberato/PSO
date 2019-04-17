package business;

import java.util.List;

public class FunctionsUtil {

	public static Double executeFunction(FunctionType functionType, List<Double> positions) {
		if (FunctionType.SPHERE == functionType) {
			return sphereFunction(positions);
		} else if (FunctionType.RASTRINGIN == functionType) {
			return rastringinFunction(positions);
		} else if (FunctionType.ROSENBROCK == functionType) {
			return rosenbrockFunction(positions);
		}
		return null;
	}

	public static Double sphereFunction(List<Double> positions) {
		Double fitness = 0.0;

		for (Double position : positions) {
			fitness += Math.pow(position, 2);
		}
		return fitness;

	}

	/*
	 * Compute the fitness function for Rastrigin's Function:
	 * 
	 * f(X) = 10n + sigma (Xi^2 - 10cos(2*pi*Xi))
	 * 
	 */
	public static Double rastringinFunction(List<Double> positions) {
		Double fitness = 10.0 * positions.size();

		for (Double parameter : positions) {
			fitness += Math.pow(parameter, 2.0) - 10.0 * Math.cos(2 * Math.PI * parameter);
		}

		return fitness;
	}

	/*
	 * Compute the fitness function for Rosenbrock's Valley:
	 * 
	 * f(X) = sigma,n-1 [100(Xi+1 - Xi^2)^2 + (1 - Xi)^2]
	 * 
	 */
	public static Double rosenbrockFunction(List<Double> positions) {
		Double fitness = 0.0;

		for (int i = 0; i < positions.size() - 1; ++i) {
			fitness += 100 * Math.pow((positions.get(i + 1) - Math.pow(positions.get(i), 2.0)), 2.0)
					+ Math.pow((1 - positions.get(i)), 2.0);
		}

		return fitness;
	}

}
