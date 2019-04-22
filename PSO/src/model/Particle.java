package model;

import util.Constants;

public class Particle implements Comparable<Particle> {

	private Integer id;
	private double[] position;
	private double[] velocity;
	private double[] pbest;
	private double[] lbest;
	private double fitness;

	public Particle() {
		position = new double[Constants.N_DIMENSIONS];
		velocity = new double[Constants.N_DIMENSIONS];
		pbest = new double[Constants.N_DIMENSIONS];
		lbest = new double[Constants.N_DIMENSIONS];
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Particle)) {
			return false;
		} else {
			Particle particle = (Particle) obj;
			return this.id.equals(particle.id);
		}

	}

	@Override
	public int compareTo(Particle o) {
		if (this.fitness < o.fitness) {
			return -1;
		}
		if (this.fitness > o.fitness) {
			return 1;
		}
		return 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}

	public double[] getVelocity() {
		return velocity;
	}

	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
	}

	public double[] getPbest() {
		return pbest;
	}

	public void setPbest(double[] pbest) {
		this.pbest = pbest;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double[] getLbest() {
		return lbest;
	}

	public void setLbest(double[] lbest) {
		this.lbest = lbest;
	}

}
