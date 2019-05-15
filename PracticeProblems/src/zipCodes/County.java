package zipCodes;

public class County implements Comparable<County> {
	private String countyName;
	private int countZip;

	public String getCountyName() {
		return countyName;
	}

	public int getCountZip() {
		return countZip;
	}

	public County(String countyName, int countZip) {
		this.countyName = countyName;
		this.countZip = countZip;
	}

	@Override
	public int compareTo(County o) {
		//reversed the normal comparison to do things in reverse order (larger first)
		if (this.getCountZip() < o.getCountZip()) {
			return 1;
		}
		if (this.getCountZip() > o.getCountZip()) {
			return -1;
		} else {
			return 0;
		}

	}

}
