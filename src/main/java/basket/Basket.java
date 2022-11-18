package main.java.basket;
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

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            //       ...
            for (String product : products) {
                out.print(product + ", ");
            }
            out.println();
            for (int price : prices) {
                out.print(price + " ");
            }
            out.println();
            for (int i : counts) {
                out.print(i + " ");
            }
            out.println();
        }

    }

    public static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(textFile))) {
            String[] products = scanner.nextLine().trim().split(", ");
            int[] prices = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] counts = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            return new Basket(products, prices, counts);

        } catch (FileNotFoundException e) {
            return null;
        }
    }
}



