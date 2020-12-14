package trainstation.model;

public class Station {
	private String stationId;
	private String stationName;
	
	public Station(String stationId, String stationName) {
		this.stationId = stationId;
		this.stationName = stationName;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
}
