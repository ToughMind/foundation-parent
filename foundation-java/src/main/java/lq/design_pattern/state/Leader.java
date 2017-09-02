package lq.design_pattern.state;

public abstract class Leader {
	private Leader leader;

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public abstract void approve(Bill bill);

	public abstract void approve(Document document);
}
