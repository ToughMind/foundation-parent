package record.observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 观察者模式一个最典型应用就是在Swing框架的JButton实现。
 * 
 * @author 刘泉
 * @date 2016年12月8日 下午5:24:01
 */
public class ButtonTest {

	public static void main(String[] args) {
		JFrame p = new JFrame();
		JButton btn = new JButton("Click Me");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				System.out.println("click");
			}
		});
		p.add(btn);
		p.pack();
		p.setVisible(true);
	}
}
