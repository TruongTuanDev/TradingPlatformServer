package model;

public class Wallet {
	private double price;
	private double quantity;
	private double quantityCurency;
	private String symbol;
	private String buyDate;
	private String accountID;
	
	
	public Wallet() {
		
	}
	public Wallet(double price, double quantity, double quantityCurency, String symbol, String buyDate,
			String accountID) {
		
		this.price = price;
		this.quantity = quantity;
		this.quantityCurency = quantityCurency;
		this.symbol = symbol;
		this.buyDate = buyDate;
		this.accountID = accountID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getQuantityCurency() {
		return quantityCurency;
	}
	public void setQuantityCurency(double quantityCurency) {
		this.quantityCurency = quantityCurency;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	
	
}
