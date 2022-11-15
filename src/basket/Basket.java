package basket;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.*;

public class Basket implements Serializable{

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

    public void saveBin(File binFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFile))) {
        out.writeObject(this);
        }

    }

    public static Basket loadFromBinFile(File binFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFile))) {
            return (Basket) in.readObject();
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}



