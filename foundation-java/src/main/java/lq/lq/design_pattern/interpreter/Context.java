package lq.lq.design_pattern.interpreter;

public class Context {
	private String formula;

	public Context(String formula) {
		this.formula = formula;
	}

	public String getFormula() {
		return this.formula;
	}

}
