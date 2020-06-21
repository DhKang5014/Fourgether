package ex_chart;

class EnergyVO {
	private String division;
	private int usage;
	private String month;

	public EnergyVO(String division, int usage, String month) {
		this.usage = usage;
		this.division = division;
		this.month = month;
	}

	public int getUsage() {
		return usage;
	}

	public void setUsage(int usage) {
		this.usage = usage;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}