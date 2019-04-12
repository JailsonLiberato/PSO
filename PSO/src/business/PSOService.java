package business;

import java.util.ArrayList;
import java.util.List;

import model.Function;
import model.Particle;

public class PSOService {

	private static final int NUMBER_DIMENSIONS = 30;
	private static final int NUMBER_PARTICLES = 30;
	private static final int NUMBER_MAX_ITERATIONS = 10000;

	private List<Particle> particles;
	private FunctionType functionType;
	private List<Double> gBest;

	public PSOService() {
		particles = new ArrayList<>();
		gBest = new ArrayList<>();

	}

	public Function executeFunction(FunctionType functionType) {
		this.functionType = functionType;
		initializeParticles();
		int countIterations = 0;
		gBest.clear();
		do {
			calculateFitness();
			updateGlobalBest();
			calculateVelocity();
			updatePosition();
		} while (countIterations < NUMBER_MAX_ITERATIONS);
		return null;
	}

	private void calculateVelocity() {
		// TODO Auto-generated method stub

	}

	private void updatePosition() {
		// TODO Auto-generated method stub

	}

	private void updateGlobalBest() {
		// TODO Auto-generated method stub

	}

	private void calculateFitness() {
		for (Particle particle : particles) {

		}

	}

	private void initializeParticles() {
		createParticles();
	}

	private Particle createParticle() {
		Particle particle = new Particle();
		particle = createDimensions(particle);
		return particle;
	}

	private Particle createDimensions(Particle particle) {
		for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
			Double position = generateRandomPosition();
			Double pBest = new Double(position);
			particle.getPosition().add(position);
			particle.getPbest().add(pBest);
		}
		return particle;
	}

	private Double generateRandomPosition() {
		return Math.random() * functionType.getBound();
	}

	private void createParticles() {
		particles.clear();
		for (int i = 0; i < NUMBER_PARTICLES; i++) {
			particles.add(createParticle());
		}
	}

	public List<Particle> getParticles() {
		return particles;
	}

	public void setParticles(List<Particle> particles) {
		this.particles = particles;
	}

	public FunctionType getFunctionType() {
		return functionType;
	}

	public void setFunctionType(FunctionType functionType) {
		this.functionType = functionType;
	}
}
