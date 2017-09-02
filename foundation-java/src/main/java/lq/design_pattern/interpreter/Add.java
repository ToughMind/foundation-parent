package lq.design_pattern.interpreter;

public class Add implements Interpreter {

	@Override
	public void parse(Context context) {
		if (context.getFormula().indexOf("+") != -1) {
			String str = context.getFormula();

			String[] ary = str.trim().split("\\+");

			long[] numAry = new long[ary.length];
			int count = 0;
			for (int i = 0; i < ary.length; i++) {
				numAry[i] = Integer.parseInt(ary[i].trim());
				count += numAry[i];
			}

			System.out.println(context.getFormula() + " = " + count);
		}
	}

}
