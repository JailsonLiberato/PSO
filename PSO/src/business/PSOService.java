package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.ChartItem;
import model.Particle;
import model.TopologyType;

public class PSOService {

	private static final int NUMBER_DIMENSIONS = 30;
	private static final int NUMBER_PARTICLES = 30;
	private static final int NUMBER_MAX_ITERATIONS = 10000;
	private static final double COEFFICIENT1 = 2.05;
	private static final double COEFFICIENT2 = 2.05;
	private static final double INERTIA = 0.8;

	private List<Particle> particles;
	private FunctionType functionType;
	private TopologyType topologyType;
	private List<Double> gBest;

	public PSOService() {
		particles = new ArrayList<>();
		gBest = new ArrayList<>();
	}

	public List<ChartItem> executeFunctionByTopology(FunctionType functionType, TopologyType topologyType) {
		this.functionType = functionType;
		this.topologyType = topologyType;
		initializeParticles(particles);
		int countIterations = 0;
		gBest.clear();
		List<ChartItem> chartItems = new ArrayList<>();
		ChartItem chartItem = null;
		if (TopologyType.FOCAL == topologyType) {
			Random rand = new Random();
			gBest = particles.get(rand.nextInt(30)).getPbest();
		}
		do {

			executePSO();
			double media = mediaGBest(gBest);
			chartItem = createChartItem(media, countIterations);
			chartItems.add(chartItem);
		} while (countIterations < NUMBER_MAX_ITERATIONS);
		return chartItems;
	}

	private ChartItem createChartItem(double media, int countIterations) {
		ChartItem chartItem = new ChartItem();
		chartItem.setIteration(countIterations);
		chartItem.setTopologyType(topologyType);
		chartItem.setValue(media);
		return chartItem;
	}

	private double mediaGBest(List<Double> gBest) {
		double sum = 0.0;
		for (Double g : gBest) {
			sum += sum + g;
		}
		double media = sum / (double) NUMBER_DIMENSIONS;
		return media;
	}

	private void executePSO() {
		calculateFitness();
		if (TopologyType.FOCAL != topologyType) {
			updateGlobalBest();
		}
		calculateVelocity();
		updatePosition();
	}

	private void calculateVelocity() {
		if (TopologyType.GLOBAL == topologyType || TopologyType.FOCAL == topologyType) {
			for (Particle particle : particles) {
				particle.getVelocity().clear();
				for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
					particle.getVelocity().add(INERTIA
							+ COEFFICIENT1 * (Math.random() * 1)
									* (particle.getPbest().get(i) - particle.getPosition().get(i))
							+ COEFFICIENT2 * (Math.random() * 1) * (gBest.get(i) - particle.getPosition().get(i)));
				}
			}
		} else {
			for (Particle particle : particles) {
				particle.getVelocity().clear();
				for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
					particle.getVelocity()
							.add(INERTIA
									+ COEFFICIENT1 * (Math.random() * 1)
											* (particle.getPbest().get(i) - particle.getPosition().get(i))
									+ COEFFICIENT2 * (Math.random() * 1)
											* (particle.getGbest().get(i) - particle.getPosition().get(i)));
				}
			}
		}

	}

	private void updatePosition() {
		for (Particle particle : particles) {
			particle.getPosition().clear();
			for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
				particle.getPosition().add(particle.getPosition().get(i) + particle.getVelocity().get(i));
			}
		}

	}

	private void updateGlobalBest() {
		if (TopologyType.GLOBAL == topologyType) {
			for (Particle particle : particles) {
				if (executeFunction(particle.getPbest(), gBest)) {
					gBest = particle.getPbest();
				}
			}
		} else if (TopologyType.LOCAL == topologyType) {
			Particle beforeParticle = null;
			Particle currentParticle = null;
			Particle nextParticle = null;
			for (int i = 0; i < particles.size(); i++) {
				if (i == 0) {
					beforeParticle = particles.get(particles.size());
					currentParticle = particles.get(0);
					nextParticle = particles.get(1);
				} else if (i == particles.size()) {
					beforeParticle = particles.get(particles.size() - 1);
					currentParticle = particles.get(particles.size());
					nextParticle = particles.get(0);
				} else {
					beforeParticle = particles.get(i - 1);
					currentParticle = particles.get(i);
					nextParticle = particles.get(i + 1);
				}

				if (executeFunction(beforeParticle.getPbest(), currentParticle.getPbest()) && !isSizeExceeded(beforeParticle.getPbest())) {
					beforeParticle.setGbest(beforeParticle.getPbest());
					currentParticle.setGbest(beforeParticle.getPbest());
					nextParticle.setGbest(beforeParticle.getPbest());
				}
				if (executeFunction(currentParticle.getPbest(), nextParticle.getPbest()) && !isSizeExceeded(currentParticle.getPbest())) {
					beforeParticle.setGbest(currentParticle.getPbest());
					currentParticle.setGbest(currentParticle.getPbest());
					nextParticle.setGbest(currentParticle.getPbest());
				}
				if (executeFunction(nextParticle.getPbest(), beforeParticle.getPbest()) && !isSizeExceeded(nextParticle.getPbest())) {
					beforeParticle.setGbest(nextParticle.getPbest());
					currentParticle.setGbest(nextParticle.getPbest());
					nextParticle.setGbest(nextParticle.getPbest());
				}

			}
		}
	}

	private boolean isSizeExceeded(List<Double> positions) {
		for (Double position : positions) {
			if (functionType.getBound() < Math.abs(position)) {
				return true;
			}
		}
		return false;

	}

	private void calculateFitness() {
		for (Particle particle : particles) {
			if (executeFunction(particle.getPosition(), particle.getPbest())) {
				particle.setPbest(particle.getPosition());
			}
		}

	}

	private boolean executeFunction(List<Double> positions, List<Double> bestPositions) {
		if (FunctionType.SPHERE == functionType) {
			if (FunctionsUtil.sphereFunction(positions) < FunctionsUtil.sphereFunction(bestPositions)) {
				return true;
			}
		} else if (FunctionType.RASTRINGIN == functionType) {
			if (FunctionsUtil.rastringinFunction(positions) < FunctionsUtil.rastringinFunction(bestPositions)) {
				return true;
			}
		} else if (FunctionType.ROSENBROCK == functionType) {
			if (FunctionsUtil.rosenbrockFunction(positions) < FunctionsUtil.rosenbrockFunction(bestPositions)) {
				return true;
			}
		}
		return false;
	}

	private void initializeParticles(List<Particle> particles) {
		createParticles(particles);
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

	private void createParticles(List<Particle> particles) {
		particles.clear();
		for (int i = 0; i < NUMBER_PARTICLES; i++) {
			particles.add(createParticle());
		}
	}

	public FunctionType getFunctionType() {
		return functionType;
	}

	public void setFunctionType(FunctionType functionType) {
		this.functionType = functionType;
	}

	public List<Particle> getParticles() {
		return particles;
	}

	public void setParticles(List<Particle> particles) {
		this.particles = particles;
	}

	public List<Double> getgBest() {
		return gBest;
	}

	public void setgBest(List<Double> gBest) {
		this.gBest = gBest;
	}

	public TopologyType getTopologyType() {
		return topologyType;
	}

	public void setTopologyType(TopologyType topologyType) {
		this.topologyType = topologyType;
	}

}
