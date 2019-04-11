package business;

public enum FunctionType {
	SPHERE("Sphere", 100.0), RASTRINGIN("Rastringin", 30.0), ROSENBROCK("Rosenbrock", 5.12);

	private String name;
	private Double bound;

	private FunctionType(String name, Double bound) {
		this.name = name;
		this.bound = bound;
	}

	public Double getBound() {
		return bound;
	}

	public void setBound(Double bound) {
		this.bound = bound;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
