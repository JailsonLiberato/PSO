package business.topology;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import business.function.FitnessFunction;
import model.Particle;
import util.Constants;

public class LocalTopology implements Topology {

	@Override
	public List<Particle> calculateVelocity(List<Particle> particles, double[] gBest, FitnessFunction fitnessFunction,
			double inertia) {
		calculateLBest(particles, fitnessFunction);
		for (int i = 0; i < Constants.N_PARTICLES; i++) {
			double r1 = generateRandomValue();
			double r2 = generateRandomValue();
			for (int j = 0; j < Constants.N_DIMENSIONS; j++) {
				particles.get(i).getVelocity()[j] = (inertia * particles.get(i).getVelocity()[j])
						+ Constants.COEFFICIENT1 * r1
								* (particles.get(i).getPbest()[j] - particles.get(i).getPosition()[j])
						+ Constants.COEFFICIENT2 * r2
								* (particles.get(i).getLbest()[j] - particles.get(i).getPosition()[j]);
			}
		}
		return particles;
	}

	private void calculateLBest(List<Particle> particles, FitnessFunction fitnessFunction) {
		Particle beforeParticle = null;
		Particle currentParticle = null;
		Particle nextParticle = null;
		for (int i = 0; i < particles.size(); i++) {
			if (i == 0) {
				beforeParticle = particles.get(particles.size() - 1);
				currentParticle = particles.get(0);
				nextParticle = particles.get(1);
			} else if (i == particles.size() - 1) {
				beforeParticle = particles.get(particles.size() - 2);
				currentParticle = particles.get(particles.size() - 1);
				nextParticle = particles.get(0);
			} else {
				beforeParticle = particles.get(i - 1);
				currentParticle = particles.get(i);
				nextParticle = particles.get(i + 1);
			}

			beforeParticle.setFitness(fitnessFunction.executeFunction(beforeParticle.getPbest()));
			currentParticle.setFitness(fitnessFunction.executeFunction(currentParticle.getPbest()));
			nextParticle.setFitness(fitnessFunction.executeFunction(nextParticle.getPbest()));

			List<Particle> localList = Arrays.asList(beforeParticle, currentParticle, nextParticle);
			Collections.sort(localList);
			if (!isLimitExceed(fitnessFunction, localList.get(0).getPbest())) {
				beforeParticle.setLbest(localList.get(0).getPbest());
				currentParticle.setLbest(localList.get(0).getPbest());
				nextParticle.setLbest(localList.get(0).getPbest());
			}

		}
	}

	public boolean isLimitExceed(FitnessFunction fitnessFunction, double[] positions) {
		for (int i = 0; i < positions.length; i++) {
			if (fitnessFunction.getBound() < Math.abs(positions[i])) {
				return true;
			}

		}
		return false;
	}

	@Override
	public String toString() {
		return "Local";
	}

	@Override
	public double generateRandomValue() {
		Random random = new Random();
		return random.nextDouble() * 1.0;
	}

}
