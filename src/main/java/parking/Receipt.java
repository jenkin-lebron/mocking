package parking;

public class Receipt {

	private String carName = null;
	private String parkingLotName = null;
	
	public Receipt() {
		
	}
	
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	public String getCarName() {
		return this.carName;
	}

	public String getParkingLotName() {
		return parkingLotName;
	}

	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Receipt receipt = (Receipt) o;

		if (carName != null ? !carName.equals(receipt.carName) : receipt.carName != null) {
			return false;
		}
		return parkingLotName != null ? parkingLotName.equals(receipt.parkingLotName) : receipt.parkingLotName == null;
	}

	@Override
	public int hashCode() {
		int result = carName != null ? carName.hashCode() : 0;
		result = 31 * result + (parkingLotName != null ? parkingLotName.hashCode() : 0);
		return result;
	}
}
