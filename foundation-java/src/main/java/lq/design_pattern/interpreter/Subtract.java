package lq.design_pattern.interpreter;

public class Subtract implements Interpreter {
	@Override
	public void parse(Context context) {
		if (context.getFormula().indexOf("-") != -1) {
			String str = context.getFormula();
//			int start = str.indexOf("-");
//			String str1 = str.substring(0,start);
//			String str2 = str.substring(start + 1);
			String[] ary = str.trim().split("\\-");

			long[] numAry = new long[ary.length];
			int count = Integer.parseInt(ary[0].trim());
			for(int i = 1; i < ary.length; i++){
				numAry[i] = Integer.parseInt(ary[i].trim());
				count -= numAry[i];
			}

            System.out.println(context.getFormula() + " = " + count);
        }
	}
}
