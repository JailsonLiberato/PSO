package business.function;

public class RosenbrockFunction implements FitnessFunction {

	@Override
	public double executeFunction(double[] positions) {
		double fitness = 0.0;

		for (int i = 0; i < positions.length - 1; ++i) {
			fitness += 100 * Math.pow((positions[i + 1] - Math.pow(positions[i], 2.0)), 2.0)
					+ Math.pow((1 - positions[i]), 2.0);
		}

		return fitness;
	}

	@Override
	public double getBound() {
		return 5.12;
	}

}
