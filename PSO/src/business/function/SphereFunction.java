package business.function;

public class SphereFunction implements FitnessFunction{

	@Override
	public double executeFunction(double[] positions) {
		double fitness = 0.0;

		for (double position : positions) {
			fitness += Math.pow(position, 2);
		}
		return fitness;
	}

	@Override
	public double getBound() {
		return 100.0;
	}
	
	@Override
	public String toString() {
		return "Sphere";
	}

}
