package business.topology;

import java.util.List;

import business.function.FitnessFunction;
import model.Particle;

public interface Topology {

	public List<Particle> calculateVelocity(List<Particle> particles, double[] gBest, FitnessFunction fitnessFunction, double inertia);
	public double generateRandomValue();
}
