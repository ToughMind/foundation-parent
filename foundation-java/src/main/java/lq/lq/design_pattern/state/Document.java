package lq.lq.design_pattern.state;

public class Document {
	private String name;
	private String flag;
	private Leader leader;

	public Document() {
		this.leader = new SectionChief();
		;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public void approve() {
		this.leader.approve(this);
	}
}
