package zipCodesDirty;

import java.util.Objects;

public class ZipcodeDirty implements Comparable<ZipcodeDirty> {

	private String zipcode;
	private String city;
	private String county;

	public ZipcodeDirty(String zipcode, String city, String county) {
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
	public int compareTo(ZipcodeDirty o) {

		return this.county.compareTo(o.getCounty());
	}

	@Override
	public int hashCode() {
		return Objects.hash(zipcode, city, county);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (getClass() != o.getClass())
			return false;
		ZipcodeDirty z = (ZipcodeDirty) o;
		return zipcode.equals(z.zipcode) && county.equals(z.county) && city.equals(z.city);

	}

}
