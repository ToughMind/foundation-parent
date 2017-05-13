package lq.lq.design_pattern.interpreter;

public class Division implements Interpreter {

	@Override
	public void parse(Context context) {
		if (context.getFormula().indexOf("/") != -1) {
			String str = context.getFormula();
			String[] ary = str.trim().split("\\/");
			double[] numAry = new double[ary.length];
			double count = Double.parseDouble(ary[0].trim());
			for (int i = 1; i < ary.length; i++) {
				numAry[i] = Double.parseDouble(ary[i].trim());
				count /= numAry[i];
			}

			System.out.println(context.getFormula() + " = " + count);
		}
	}

}
