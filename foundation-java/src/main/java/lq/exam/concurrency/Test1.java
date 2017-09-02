package lq.exam.concurrency;

/**
 * 有A、B、C三个线程，要求同时启动三个线程，按顺序执行ABC。（利用join方法实现）
 * <p>
 * A输出3次a，B输出5次b，C输出10次c
 * </p>
 * 
 * @author 刘泉 2017年08月26日 17:03
 */
public class Test1 {

    public static void main(String[] args) {
        a.start();
        b.start();
        c.start();
    }

    static Thread a = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                c.join();
                doJob("a", 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    static Thread b = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                a.join();
                doJob("b", 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    static Thread c = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                b.join();
                doJob("c", 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    static void doJob(String str, int num) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            System.out.println(str + i);
            Thread.sleep(100);
        }
    }

}
