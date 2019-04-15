package business;

import java.util.ArrayList;
import java.util.List;

import model.ChartItem;
import model.Function;
import model.Particle;
import model.TopologyType;

public class PSOService {

	private static final int NUMBER_DIMENSIONS = 30;
	private static final int NUMBER_PARTICLES = 30;
	private static final int NUMBER_MAX_ITERATIONS = 10000;
	private static final double COEFFICIENT1 = 2.05;
	private static final double COEFFICIENT2 = 2.05;
	private static final double INERTIA = 0.8;

	private List<Particle> particlesGlobal;
	private List<Particle> particlesLocal;
	private List<Particle> particlesFocal;
	private FunctionType functionType;
	private List<Double> gBestGlobal;
	private List<Double> gBestFocal;
	private List<Double> gBestLocal;

	public PSOService() {
		particlesGlobal = new ArrayList<>();
		particlesLocal = new ArrayList<>();
		particlesFocal = new ArrayList<>();
		gBestGlobal = new ArrayList<>();
		gBestFocal = new ArrayList<>();
		gBestLocal = new ArrayList<>();

	}

	public Function executeFunction(FunctionType functionType) {
		this.functionType = functionType;
		initializeParticles(particlesGlobal);
		initializeParticles(particlesLocal);
		initializeParticles(particlesFocal);

		int countIterations = 0;
		gBestGlobal.clear();
		gBestFocal.clear();
		gBestLocal.clear();
		Function function = new Function();

		List<ChartItem> chartItemsGlobal = new ArrayList<>();
		List<ChartItem> chartItemsLocal = new ArrayList<>();
		List<ChartItem> chartItemsFocal = new ArrayList<>();

		ChartItem chartItem = null;
		do {
			executePSO(TopologyType.GLOBAL);
			double media = mediaGBest(gBestGlobal);
			chartItem = createChartItem(media, countIterations);
			chartItemsGlobal.add(chartItem);

			executePSO(TopologyType.LOCAL);
			media = mediaGBest(gBestLocal);
			chartItem = createChartItem(media, countIterations);
			chartItemsLocal.add(chartItem);

			executePSO(TopologyType.FOCAL);
			media = mediaGBest(gBestFocal);
			chartItem = createChartItem(media, countIterations);
			chartItemsFocal.add(chartItem);

		} while (countIterations < NUMBER_MAX_ITERATIONS);
		function.setGlobals(chartItemsGlobal);
		function.setLocals(chartItemsLocal);
		function.setFocals(chartItemsFocal);
		return function;
	}

	private ChartItem createChartItem(double media, int countIterations) {
		ChartItem chartItem = new ChartItem();
		chartItem.setIteration(countIterations);
		chartItem.setTopologyType(TopologyType.GLOBAL);
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

	private void executePSO(TopologyType topology) {
		calculateFitness(topology);
		updateGlobalBest(topology);
		calculateVelocity(topology);
		updatePosition(topology);
	}

	private void calculateVelocity(TopologyType topology) {
		List<Double> gBest = null;
		List<Particle> particles = null;
		if (TopologyType.GLOBAL == topology) {
			gBest = gBestGlobal;
			particles = particlesGlobal;
		} else if (TopologyType.LOCAL == topology) {
			gBest = gBestLocal;
			particles = particlesLocal;
		} else if (TopologyType.FOCAL == topology) {
			gBest = gBestFocal;
			particles = particlesFocal;
		}

		for (Particle particle : particles) {
			particle.getVelocity().clear();
			for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
				particle.getVelocity()
						.add(INERTIA
								+ COEFFICIENT1 * (Math.random() * 1)
										* (particle.getPbest().get(i) - particle.getPosition().get(i))
								+ COEFFICIENT2 * (Math.random() * 1) * (gBest.get(i) - particle.getPosition().get(i)));
			}
		}

	}

	private void updatePosition(TopologyType topology) {

		List<Particle> particles = null;
		if (TopologyType.GLOBAL == topology) {
			particles = particlesGlobal;
		} else if (TopologyType.LOCAL == topology) {
			particles = particlesLocal;
		} else if (TopologyType.FOCAL == topology) {
			particles = particlesFocal;
		}

		for (Particle particle : particles) {
			particle.getPosition().clear();
			for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
				particle.getPosition().add(particle.getPosition().get(i) + particle.getVelocity().get(i));
			}
		}

	}

	private void updateGlobalBest(TopologyType topology) {
		List<Double> gBest = null;
		List<Particle> particles = null;
		if (TopologyType.GLOBAL == topology) {
			gBest = gBestGlobal;
			particles = particlesGlobal;
		} else if (TopologyType.LOCAL == topology) {
			gBest = gBestLocal;
			particles = particlesLocal;
		} else if (TopologyType.FOCAL == topology) {
			gBest = gBestFocal;
			particles = particlesFocal;
		}

		List<Double> temp = new ArrayList<>();
		for (Particle particle : particles) {
			particle.getPosition().clear();
			for (int i = 0; i < NUMBER_DIMENSIONS; i++) {
				if (gBest.get(i) > particle.getPbest().get(i)) {
					temp.add(particle.getPbest().get(i));
				} else {
					temp.add(gBest.get(i));
				}
			}
		}
		gBest = temp;
	}

	private void calculateFitness(TopologyType topology) {

		List<Particle> particles = null;
		if (TopologyType.GLOBAL == topology) {
			particles = particlesGlobal;
		} else if (TopologyType.LOCAL == topology) {
			particles = particlesLocal;
		} else if (TopologyType.FOCAL == topology) {
			particles = particlesFocal;
		}
		for (Particle particle : particles) {
			for (int i = 0; i < NUMBER_DIMENSIONS; i++) {

			}
		}

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

	public List<Particle> getParticlesGlobal() {
		return particlesGlobal;
	}

	public void setParticlesGlobal(List<Particle> particlesGlobal) {
		this.particlesGlobal = particlesGlobal;
	}

	public List<Particle> getParticlesLocal() {
		return particlesLocal;
	}

	public void setParticlesLocal(List<Particle> particlesLocal) {
		this.particlesLocal = particlesLocal;
	}

	public List<Particle> getParticlesFocal() {
		return particlesFocal;
	}

	public void setParticlesFocal(List<Particle> particlesFocal) {
		this.particlesFocal = particlesFocal;
	}

}
