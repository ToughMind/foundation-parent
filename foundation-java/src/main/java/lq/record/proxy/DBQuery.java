package lq.record.proxy;

public class DBQuery implements IDBQuery {

	public DBQuery() {
		try {
			//Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String request() {
		//System.out.println("request");
		return "request";
	}

}
