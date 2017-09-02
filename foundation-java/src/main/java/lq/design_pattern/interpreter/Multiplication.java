package lq.design_pattern.interpreter;

public class Multiplication implements Interpreter {

	@Override
	public void parse(Context context) {
		if (context.getFormula().indexOf("*") != -1) {
			String str = context.getFormula();
			String[] ary = str.trim().split("\\*");

			double[] numAry = new double[ary.length];
			int count = 1;
			for (int i = 0; i < ary.length; i++) {
				numAry[i] = Double.parseDouble(ary[i].trim());
				count *= numAry[i];
			}

			System.out.println(context.getFormula() + " = " + count);
		}
	}

}
