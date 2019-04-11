package business;

import java.util.ArrayList;
import java.util.List;

import model.Function;
import model.Particle;

public class PSOService {

	private List<Particle> particles;

	public PSOService() {
		particles = new ArrayList<>();
	}

	public Function executeFunction(FunctionType functionType) {
		return null;
	}

	public List<Particle> getParticles() {
		return particles;
	}

	public void setParticles(List<Particle> particles) {
		this.particles = particles;
	}
}
