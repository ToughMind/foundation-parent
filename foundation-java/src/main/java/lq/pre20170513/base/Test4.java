package lq.pre20170513.base;

/**
 * 金额转换，阿拉伯数字的金额转换成中国传统的形式如：（￥1011）－>（一千零一拾一元整）输出。
 *
 */
public class Test4 {
	private static final char[] data = new char[] { '零', '壹', '贰', '叄', '肆', '伍', '陆', '柒', '捌', '玖' };
	private static final char[] units = new char[] { '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿' };

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		int unit = 0;
		int money = 800001;
		while(money != 0){
			sb.insert(0, units[unit++]);
			int number = money%10;
			sb.insert(0, data[number]);
			money/=10;
		}
		//去零的代码
		System.out.println(sb.toString().replaceAll("零[拾佰仟]","零").replaceAll("零+万","万").replaceAll("零+元","元").replaceAll("零+","零"));
	}
}
