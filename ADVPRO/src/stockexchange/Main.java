package stockexchange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input_file>");
            return;
        }

        String inputFile = args[0];
        StockExchange stockExchange = new StockExchange();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0].substring(1));
                LocalTime time = LocalTime.parse(parts[1]);
                String stock = parts[2];
                String type = parts[3];
                double price = Double.parseDouble(parts[4]);
                int quantity = Integer.parseInt(parts[5]);

                Order order = new Order(id, time, stock, type, price, quantity);
                List<String> trades = stockExchange.processOrder(order);
                for (String trade : trades) {
                    System.out.println(trade);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
