package main.java.basket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Basket {

    private int totalAmount;  // Итоговая сумма продуктов корзине
    private int sumProduct; // сумма продуктов
    protected String[] products;
    protected int[] prices;
    protected int[] counts;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.counts = new int[products.length];
    }

    private Basket(String[] products, int[] prices, int[] counts) {
        this.products = products;
        this.prices = prices;
        this.counts = counts;
    }

    public void addToCart(int productNum, int productCount) {
        counts[productNum] += productCount;

    }

    public void printCart() { // вывод корзины покупателя

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                sumProduct = (prices[i] * counts[i]);
                totalAmount = totalAmount + sumProduct;
                System.out.println(products[i] + " " + counts[i] + " шт " + prices[i] + " руб/шт  " + sumProduct + " руб в сумме");
            }
        }
        System.out.println("Итоговая сумма к оплате: " + totalAmount);
    }

    public void spisok() { // список продуктов вывод на консоль
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
    }

//    public void saveTxt(File textFile) throws IOException {
//        try (PrintWriter out = new PrintWriter(textFile)) {
//            //       ...
//            for (String product : products) {
//                out.print(product + ", ");
//            }
//            out.println();
//            for (int price : prices) {
//                out.print(price + " ");
//            }
//            out.println();
//            for (int i : counts) {
//                out.print(i + " ");
//            }
//            out.println();
//        }
//
//    }


    public void saveJSON() throws IOException {
        JSONArray list1 = new JSONArray();
        for (String product : products) {
            list1.add(product);
        }
        JSONArray list2 = new JSONArray();
        for (int price : prices) {
            list2.add(price);
        }
        JSONArray list3 = new JSONArray();
        for (int i : counts) {
            list3.add(i);
        }
        JSONObject obj = new JSONObject();
        obj.put("Наименовение продуктов", list1);
        obj.put("Цена продуктов", list2);
        obj.put("Колличество продуктов", list3);

        try (FileWriter file = new FileWriter("basket.json")) {
            file.write(obj.toJSONString());
        }
    }


    public static Basket loadFromJSON() throws ParseException, FileNotFoundException, IOException {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("basket.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray products1 = (JSONArray) jsonObject.get("Наименовение продуктов");
            String[] products = new String[products1.size()];
            for (int i = 0; i < products1.size(); i++) {
                products[i] = (String) products1.get(i);
            }
            JSONArray prices1 = (JSONArray) jsonObject.get("Цена продуктов");
            int[] prices = new int[prices1.size()];
            for (int i = 0; i < prices1.size(); i++) {
                Long pricesLong = (Long) prices1.get(i);
                prices[i] = pricesLong.intValue();
            }
            JSONArray counts1 = (JSONArray) jsonObject.get("Колличество продуктов");
            int[] counts = new int[counts1.size()];
            for (int i = 0; i < counts1.size(); i++) {
                Long countsLong = (Long) counts1.get(i);
                counts[i] = countsLong.intValue();
            }
            return new Basket(products, prices, counts);

        } catch (FileNotFoundException e) {
            return null;
        }
    }


//    public static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
//        try (Scanner scanner = new Scanner(new FileInputStream(textFile))) {
//            String[] products = scanner.nextLine().trim().split(", ");
//            int[] prices = Arrays.stream(scanner.nextLine().trim().split(" "))
//                    .mapToInt(Integer::parseInt)
//                    .toArray();
//            int[] counts = Arrays.stream(scanner.nextLine().trim().split(" "))
//                    .mapToInt(Integer::parseInt)
//                    .toArray();
//            return new Basket(products, prices, counts);
//
//        } catch (FileNotFoundException e) {
//            return null;
//        }
//    }


}



