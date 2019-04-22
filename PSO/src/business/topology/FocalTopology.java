package business.topology;

import java.util.List;
import java.util.Random;

import business.function.FitnessFunction;
import model.Particle;
import util.Constants;

public class FocalTopology implements Topology {

	@Override
	public List<Particle> calculateVelocity(List<Particle> particles, double[] gBest, FitnessFunction fitnessFunction) {
		Particle selectedParticle = particles.get(0);

		for (int i = 0; i < Constants.N_PARTICLES; i++) {
			Particle particle = particles.get(i);
			for (int j = 0; j < Constants.N_DIMENSIONS; j++) {
				if (selectedParticle.equals(particle)) {
					double r1 = generateRandomValue();
					double r2 = generateRandomValue();
					particle.getVelocity()[j] = particle.getVelocity()[j] * Constants.INERTIA
							+ Constants.COEFFICIENT1 * r1 * (particle.getPbest()[j] - particle.getPosition()[j])
							+ Constants.COEFFICIENT2 * r2 * (gBest[j] - particle.getPosition()[j]);
				} else {
					double r1 = generateRandomValue();
					double r2 = generateRandomValue();
					particle.getVelocity()[j] = particle.getVelocity()[j] * Constants.INERTIA
							+ Constants.COEFFICIENT1 * r1 * (particle.getPbest()[j] - particle.getPosition()[j])
							+ Constants.COEFFICIENT2 * r2 * (selectedParticle.getPbest()[j] - particle.getPosition()[j]);

				}

			}
		}
		return particles;

	}

	@Override
	public String toString() {
		return "Focal";
	}

	@Override
	public double generateRandomValue() {
		Random random = new Random();
		return random.nextDouble() * 1.0;
	}

}
