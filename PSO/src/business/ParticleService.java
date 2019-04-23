package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import business.function.FitnessFunction;
import model.Particle;
import util.Constants;

public class ParticleService {

	public List<Particle> initializeParticles(FitnessFunction fitnessFunction) {
		List<Particle> particles = new ArrayList<>();
		for (int i = 0; i < Constants.N_PARTICLES; i++) {
			Particle particle = createParticle(fitnessFunction);
			particle.setId(i + 1);
			particles.add(particle);
		}
		return particles;
	}

	private Particle createParticle(FitnessFunction fitnessFunction) {
		Particle particle = new Particle();
		for (int i = 0; i < Constants.N_DIMENSIONS; i++) {
			particle.getPosition()[i] = generatePositionsByBounds(fitnessFunction.getBound());
			particle.getVelocity()[i] = 0.0;
			particle.getPbest()[i] = particle.getPosition()[i];
			particle.setFitness(fitnessFunction.executeFunction(particle.getPbest()));
		}
		return particle;
	}

	private double generatePositionsByBounds(double bounds) {
		Random random = new Random();
		return -bounds + random.nextDouble() * (bounds - -bounds);

	}

	public boolean isLimitExceed(FitnessFunction fitnessFunction, double[] positions) {
		for (int i = 0; i < positions.length; i++) {
			if (fitnessFunction.getBound() < Math.abs(positions[i])) {
				return true;
			}

		}
		return false;
	}

}
