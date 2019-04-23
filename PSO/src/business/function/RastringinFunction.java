package business.function;

public class RastringinFunction implements FitnessFunction {

	@Override
	public double executeFunction(double[] positions) {
		double fitness = 10.0 * positions.length;

		for (double position : positions) {
			fitness += Math.pow(position, 2.0) - 10.0 * Math.cos(2 * Math.PI * position);
		}

		return fitness;
	}

	@Override
	public double getBound() {
		return 30.0;
	}
	
	@Override
	public String toString() {
		return "Rastringin";
	}

}
