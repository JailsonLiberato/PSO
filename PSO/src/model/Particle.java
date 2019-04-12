package model;

import java.util.ArrayList;
import java.util.List;

public class Particle {

	private List<Double> position;
	private List<Double> velocity;
	private List<Double> pbest;

	public Particle() {
		position = new ArrayList<>();
		velocity = new ArrayList<>();
		pbest = new ArrayList<>();
	}

	public List<Double> getPosition() {
		return position;
	}

	public void setPosition(List<Double> position) {
		this.position = position;
	}

	public List<Double> getVelocity() {
		return velocity;
	}

	public void setVelocity(List<Double> velocity) {
		this.velocity = velocity;
	}

	public List<Double> getPbest() {
		return pbest;
	}

	public void setPbest(List<Double> pbest) {
		this.pbest = pbest;
	}

}
