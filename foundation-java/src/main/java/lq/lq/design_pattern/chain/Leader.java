package lq.lq.design_pattern.chain;

public abstract class Leader {
	private Leader leader;

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public abstract void approve(Bill bill);
 
}
