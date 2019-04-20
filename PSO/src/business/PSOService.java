package business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	private Particle selectedParticle;

	public PSOService() {
		particles = new ArrayList<>();
		gBest = new ArrayList<>();
	}

	public synchronized List<ChartItem> executeFunctionByTopology(FunctionType functionType,
			TopologyType topologyType) {
		this.functionType = functionType;
		this.topologyType = topologyType;
		int countIterations = 0;
		List<ChartItem> chartItems = new ArrayList<ChartItem>();
		initializeParticles(particles);

		do {
			System.out.println(countIterations);
			executePSO();
			generateChartItems(functionType, countIterations, chartItems);
			countIterations++;
		} while (countIterations <= NUMBER_MAX_ITERATIONS);
		return chartItems;

	}

	private void generateChartItems(FunctionType functionType, int countIterations, List<ChartItem> chartItems) {
		ChartItem chartItem;
		double valueFunction = 0.0;
		if (TopologyType.LOCAL == this.topologyType) {
			gBest = particles.get(0).getGbest();
			for (Particle particle : particles) {
				if (executeFunction(particle.getGbest(), gBest)) {
					gBest = particle.getGbest();
				}
			}
		}
		valueFunction = FunctionsUtil.executeFunction(functionType, gBest);
		
			chartItem = createChartItem(valueFunction, countIterations);
			chartItems.add(chartItem);
	}

	private ChartItem createChartItem(double media, int countIterations) {
		ChartItem chartItem = new ChartItem();
		chartItem.setIteration(countIterations);
		chartItem.setTopologyType(topologyType);
		chartItem.setValue(media);
		return chartItem;
	}

	private void executePSO() {
		calculateFitness();
		updateGlobalBest();
		calculateVelocity();
		updatePosition();
	}

	private synchronized void calculateVelocity() {
		if (!gBest.isEmpty() || (!particles.isEmpty() && particles.get(0).getGbest() != null
				&& !particles.get(0).getGbest().isEmpty())) {
			if (TopologyType.GLOBAL == topologyType) {
				for (int j = 0; j < particles.size(); j++) {
					for (int i = 0; i < NUMBER_DIMENSIONS; i++) {

						particles.get(j).getVelocity().set(i,
								INERTIA * COEFFICIENT1 * (Math.random() * 1)
										* (particles.get(j).getPbest().get(i) - particles.get(j).getPosition().get(i))
										+ COEFFICIENT2 * (Math.random() * 1)
												* (gBest.get(i) - particles.get(j).getPosition().get(i)));
					}
				}
			} else if (TopologyType.FOCAL == topologyType) {
				for (int j = 0; j < particles.size(); j++) {
					for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
						if (particles.get(j).equals(selectedParticle)) {
							particles.get(j).getVelocity().set(i, INERTIA * COEFFICIENT1 * (Math.random() * 1)
									* (particles.get(j).getPbest().get(i) - particles.get(j).getPosition().get(i))
									+ COEFFICIENT2 * (Math.random() * 1)
											* (gBest.get(i) - particles.get(j).getPosition().get(i)));
						} else {

							particles.get(j).getVelocity().set(i, INERTIA * COEFFICIENT1 * (Math.random() * 1)
									* (particles.get(j).getPbest().get(i) - particles.get(j).getPosition().get(i))
									+ COEFFICIENT2 * (Math.random() * 1) * (selectedParticle.getPbest().get(i)
											- particles.get(j).getPosition().get(i)));
						}
					}
				}
			} else {
				for (int j = 0; j < particles.size(); j++) {
					for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
						particles.get(j).getVelocity().set(i, INERTIA * COEFFICIENT1 * (Math.random() * 1)
								* (particles.get(j).getPbest().get(i) - particles.get(j).getPosition().get(i))
								+ COEFFICIENT2 * (Math.random() * 1)
										* (particles.get(j).getGbest().get(i) - particles.get(j).getPosition().get(i)));
					}
				}
			}
		}
	}

	private synchronized void updatePosition() {
		for (int j = 0; j < particles.size(); j++) {
			for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
				particles.get(j).getPosition().set(i,
						particles.get(j).getPosition().get(i) + particles.get(j).getVelocity().get(i));
			}
		}

	}

	/**
	 * Atualiza de acordo com a topologia e o valor da execução das funções.
	 */
	private synchronized void updateGlobalBest() {
		if (TopologyType.GLOBAL == topologyType || TopologyType.FOCAL == topologyType) {
			if (gBest.isEmpty()) {
				gBest = particles.get(0).getPbest();
			}
			for (int i = 0; i < particles.size(); i++) {
				if (executeFunction(particles.get(i).getPbest(), gBest)
						&& !isSizeExceeded(particles.get(i).getPbest())) {
					gBest = particles.get(i).getPbest();
				}
			}
		} else if (TopologyType.LOCAL == topologyType) {
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

				beforeParticle.setFitness(FunctionsUtil.executeFunction(functionType, beforeParticle.getPbest()));
				currentParticle.setFitness(FunctionsUtil.executeFunction(functionType, currentParticle.getPbest()));
				nextParticle.setFitness(FunctionsUtil.executeFunction(functionType, nextParticle.getPbest()));

				List<Particle> localList = Arrays.asList(beforeParticle, currentParticle, nextParticle);
				Collections.sort(localList);
				if (!isSizeExceeded(localList.get(0).getPbest())) {
					beforeParticle.setGbest(localList.get(0).getPbest());
					currentParticle.setGbest(localList.get(0).getPbest());
					nextParticle.setGbest(localList.get(0).getPbest());
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

	/**
	 * Se a posição for melhor, atualiza o Pbest. Para cada partícula
	 * individualmente.
	 */
	private synchronized void calculateFitness() {
		for (int i = 0; i < particles.size(); i++) {
			if (executeFunction(particles.get(i).getPosition(), particles.get(i).getPbest())
					&& !isSizeExceeded(particles.get(i).getPosition())) {
				particles.get(i).setPbest(particles.get(i).getPosition());
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
		particles.clear();
		gBest.clear();
		createParticles(particles);

		if (TopologyType.FOCAL == topologyType) {
			Random rand = new Random();
			selectedParticle = particles.get(rand.nextInt(30));
		}
	}

	private Particle createParticle(int id) {
		Particle particle = new Particle();
		particle.setId(id);
		particle = createDimensions(particle);
		return particle;
	}

	private Particle createDimensions(Particle particle) {
		for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
			Double position = generateRandomPosition();
			Double pBest = position.doubleValue();
			particle.getPosition().add(position);
			particle.getPbest().add(pBest);
			particle.getVelocity().add(0.0);
			particle.setFitness(FunctionsUtil.executeFunction(functionType, particle.getPosition()));
		}
		return particle;
	}

	private Double generateRandomPosition() {
		return Math.random() * functionType.getBound();
	}

	private void createParticles(List<Particle> particles) {
		particles.clear();
		for (int i = 0; i < NUMBER_PARTICLES; i++) {
			particles.add(createParticle(i + 1));
		}

		Collections.sort(particles);
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
