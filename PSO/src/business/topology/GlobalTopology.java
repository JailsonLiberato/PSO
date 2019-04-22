package business.topology;

import java.util.List;
import java.util.Random;

import business.function.FitnessFunction;
import model.Particle;
import util.Constants;

public class GlobalTopology implements Topology {

	@Override
	public List<Particle> calculateVelocity(List<Particle> particles, double[] gBest, FitnessFunction fitnessFunction) {
		Random random = new Random();
		for (int i = 0; i < Constants.N_PARTICLES; i++) {
			double r1 = random.nextDouble() * 1.0;
			double r2 = random.nextDouble() * 1.0;
			System.out.println(r1 + " " + r2);
			for (int j = 0; j < Constants.N_DIMENSIONS; j++) {
				particles.get(i).getVelocity()[j] = (Constants.INERTIA * particles.get(i).getVelocity()[j])
						+ Constants.COEFFICIENT1 * r1
								* (particles.get(i).getPbest()[j] - particles.get(i).getPosition()[j])
						+ Constants.COEFFICIENT2 * r2 * (gBest[j] - particles.get(i).getPosition()[j]);
			}
		}
		return particles;
	}

	@Override
	public double generateRandomValue() {
		Random random = new Random();
		return random.nextDouble() * 1.0;
	}

	@Override
	public String toString() {
		return "Global";
	}

}
