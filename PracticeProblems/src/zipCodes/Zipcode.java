package zipCodes;

public class Zipcode implements Comparable<Zipcode> {

	private String zipcode;
	private String city;
	private String county;

	public Zipcode(String zipcode, String city, String county) {
		this.zipcode = zipcode;
		this.city = city;
		this.county = county;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getCity() {
		return city;
	}

	public String getCounty() {
		return county;
	}

	@Override
	public int compareTo(Zipcode o) {

		return this.county.compareTo(o.getCounty());
	}
	
	

}
