package trainstation.model;

public class Profit {
	private String title;
	private int profit;
	
	public Profit (String title, int profit) {
		this.title = title;
		this.profit = profit;
		
	}
	public String getTitle() {
		return title;
	}
	public int getProfit() {
		return profit;
	}
}
