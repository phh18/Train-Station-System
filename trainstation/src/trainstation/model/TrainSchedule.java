package trainstation.model;

public class TrainSchedule {
	private static final long serialVersionUID = 1L;
    private String trainId;
    private String stationId;
//    private int fare;
    private String arrivalTime;
    private String departTime;
  
    public TrainSchedule(String trainId, String stationId, String arrivalTime, String departTime) {
    	this.trainId = trainId;
    	this.stationId = stationId;
    	this.arrivalTime = arrivalTime;
    	this.departTime = departTime;
    }
    public String gettrainId() {
        return trainId;
    }
    public void settrainId(String trainId) {
        this.trainId = trainId;
    }
    public String getstationId() {
        return stationId;
    }
    public void setstationId(String stationId) {
        this.stationId = stationId;
    }
    
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String getDepartTime() {
    	return departTime;
    }
    public void setDepartTime(String departTime) {
    	this.departTime = departTime;
    }
}
