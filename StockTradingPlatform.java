package com.javaprogramming.stocktradingplatform;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class StockTradingPlatform {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Map<String, Stock> market = new HashMap<>();
        market.put("CodeAlpha", new Stock("CodeAlpha", 1500));
        market.put("TCS", new Stock("TCS" , 3500));
        market.put("WIPRO", new Stock("WIPRO", 450));
        
        Map<String, Integer> portfolio = new HashMap<>();

        while (true) {

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            
            switch (choice) {

            case 1:
                System.out.println("\nAvailable Stocks:");
                for (Stock stock : market.values()) {
                    System.out.println(stock.getSymbol() +
                            " - ₹" + stock.getPrice());
                }
                break;
                
            case 2:
                System.out.print("Enter Stock Symbol: ");
                String buySymbol = sc.next().toUpperCase();

                if (market.containsKey(buySymbol)) {
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    portfolio.put(
                            buySymbol,
                            portfolio.getOrDefault(buySymbol, 0) + qty);
                    
                    System.out.println("Stock purchased successfully!");
                } else {
                    System.out.println("Invalid Stock Symbol!");
                }
                break;

            case 3:
                System.out.print("Enter Stock Symbol: ");
                String sellSymbol = sc.next().toUpperCase();

                if (portfolio.containsKey(sellSymbol)) {

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    
                    int currentQty = portfolio.get(sellSymbol);

                    if (qty <= currentQty) {
                        portfolio.put(sellSymbol, currentQty - qty);

                        if (portfolio.get(sellSymbol) == 0) {
                            portfolio.remove(sellSymbol);
                        }

                        System.out.println("Stock sold successfully!");
                    } else {
                        System.out.println("Insufficient shares!");
                    } 
                    

                } else {
                    System.out.println("You don't own this stock.");
                }
                break;

            case 4:
                System.out.println("\n===== PORTFOLIO =====");

                if (portfolio.isEmpty()) {
                    System.out.println("No stocks purchased.");
                } else {

                    double totalValue = 0;
                    
                    for (String symbol : portfolio.keySet()) {

                        int quantity = portfolio.get(symbol);
                        double price = market.get(symbol).getPrice();
                        double value = quantity * price;

                        totalValue += value;

                        System.out.println(symbol + " | Quantity: " + quantity + " | Value: ₹" + value);
                    }
                    
                    System.out.println("----------------------------");
                    System.out.println("Total Portfolio Value: ₹" + totalValue);
                }
                break;

            case 5:
                System.out.println("Thank you for using the Stock Trading Platform!");
                sc.close();
                System.exit(0);

            default:
                System.out.println("Invalid Choice!");
	     }
       }
	}
	
}
