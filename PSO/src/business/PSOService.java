package business;

import java.util.ArrayList;
import java.util.List;

import business.function.FitnessFunction;
import business.topology.Topology;
import model.Particle;
import util.Constants;

public class PSOService {

	private List<Particle> particles;
	private double[] gBest;
	private FitnessFunction fitnessFunction;
	private ParticleService particleService;

	public PSOService() {
		particleService = new ParticleService();
		gBest = new double[Constants.N_DIMENSIONS];
	}

	public synchronized List<Double> executePSO(FitnessFunction fitnessFunction, Topology topology,
			boolean linearDecayWeight, boolean constrictionCoeffWeight) {
		this.fitnessFunction = fitnessFunction;
		int countIterations = 0;
		particles = particleService.initializeParticles(fitnessFunction);
		initializeGBest();
		List<Double> fitnessValues = new ArrayList<>();
		do {
			calculateFitness();
			updateGBest();
			double inertia = generateInertia(linearDecayWeight, constrictionCoeffWeight, countIterations);
			particles = topology.calculateVelocity(particles, gBest, fitnessFunction, inertia);
			updatePosition();
			updateBoundAdjustment();
			countIterations++;
			fitnessValues.add(fitnessFunction.executeFunction(gBest));
		} while (countIterations < Constants.N_ITERATIONS);
		return fitnessValues;
	}

	private double generateInertia(boolean linearDecayWeight, boolean constrictionCoeffWeight, int countIterations) {
		if (linearDecayWeight) {
			return (Constants.INERTIA_MAX
					- countIterations * (Constants.INERTIA_MAX - Constants.INERTIA_MIN) / Constants.N_ITERATIONS);
		} else if (constrictionCoeffWeight) {
			double phi = Constants.COEFFICIENT1 +Constants.COEFFICIENT2;
			return 2 / (Math.abs(2 - phi - Math.pow(((Math.pow(phi, 2) - 4 * phi)), 0.5)));
		}
		return Constants.INERTIA;
	}

	private void updatePosition() {
		for (int i = 0; i < Constants.N_PARTICLES; i++) {
			for (int j = 0; j < Constants.N_DIMENSIONS; j++) {
				particles.get(i).getPosition()[j] += particles.get(i).getVelocity()[j];
			}
		}
	}

	private void calculateFitness() {
		for (int i = 0; i < Constants.N_PARTICLES; i++) {
			Particle particle = particles.get(i);
			if (fitnessFunction.executeFunction(particle.getPosition()) < fitnessFunction
					.executeFunction(particle.getPbest())) {
				particle.setPbest(particle.getPosition().clone());
				particle.setFitness(fitnessFunction.executeFunction(particle.getPbest()));
			}
		}
	}

	private void updateGBest() {
		for (int i = 0; i < Constants.N_PARTICLES; i++) {
			Particle particle = particles.get(i);
			if ((fitnessFunction.executeFunction(particle.getPbest()) < fitnessFunction.executeFunction(gBest))
					&& !particleService.isLimitExceed(fitnessFunction, particle.getPbest())) {
				gBest = particle.getPbest();
			}
		}
	}

	private void updateBoundAdjustment() {
		for (int i = 0; i < Constants.N_PARTICLES; i++) {
			for (int j = 0; j < Constants.N_DIMENSIONS; j++) {
				if (fitnessFunction.getBound() < particles.get(i).getPosition()[j]) {
					particles.get(i).getPosition()[j] = fitnessFunction.getBound();
				} else if (-fitnessFunction.getBound() > particles.get(i).getPosition()[j]) {
					particles.get(i).getPosition()[j] = -fitnessFunction.getBound();
				}
			}
		}
	}

	private void initializeGBest() {
		for (int i = 0; i < Constants.N_DIMENSIONS; i++) {
			gBest[i] = particles.get(0).getPosition()[i];
		}
	}

	public List<Particle> getParticles() {
		return particles;
	}

	public void setParticles(List<Particle> particles) {
		this.particles = particles;
	}

}
