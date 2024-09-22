package services;

import model.Wallet;
import repositorys.OrderRepository;

public class OrderService {
	private Wallet wallet;
	private OrderRepository orderRepository;
	
	public Wallet createWallet(double price,double quantity,double quantity_curency,String symbol,String buyDate,String accountID) {
		wallet = new Wallet();
		orderRepository = new OrderRepository();
		wallet.setPrice(price);
		wallet.setQuantity(quantity);
		wallet.setQuantityCurency(quantity_curency);
		wallet.setSymbol(symbol);
		wallet.setBuyDate(buyDate);
		wallet.setAccountID(accountID);
		wallet = orderRepository.saveOrder(wallet);
		return wallet;
	}
}
