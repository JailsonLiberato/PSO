package model;

import java.util.ArrayList;
import java.util.List;

public class Function {

	private List<ChartItem> locals;
	private List<ChartItem> globals;
	private List<ChartItem> focals;

	public Function() {
		locals = new ArrayList<>();
		globals = new ArrayList<>();
		focals = new ArrayList<>();
	}

	public Function(List<ChartItem> globals, List<ChartItem> locals, List<ChartItem> focals) {
		this.globals = globals;
		this.locals = locals;
		this.focals = focals;
	}

	public List<ChartItem> getLocals() {
		return locals;
	}

	public void setLocals(List<ChartItem> locals) {
		this.locals = locals;
	}

	public List<ChartItem> getGlobals() {
		return globals;
	}

	public void setGlobals(List<ChartItem> globals) {
		this.globals = globals;
	}

	public List<ChartItem> getFocals() {
		return focals;
	}

	public void setFocals(List<ChartItem> focals) {
		this.focals = focals;
	}

}
