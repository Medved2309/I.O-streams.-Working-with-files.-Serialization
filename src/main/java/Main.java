import main.java.basket.Basket;
import main.java.clientLog.ClientLog;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        String[] products = {"Молоко", "Хлеб", "Гречневая крупа", "Курица", "Яйца куринные"};
        int[] prices = {50, 14, 80, 100, 90};

        //Basket basket = new Basket(products, prices);
        Basket basket2 = Basket.loadFromJSON();

        if (basket2 == null) {
            basket2 = new Basket(products, prices);
        } else {
            basket2 = Basket.loadFromJSON();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Список возможных товаров для покупки:");
        basket2.spisok();
        while (true) {
            int productNumber = 0; // номер продукта
            int productCount = 0; // колличество продукта
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("Ваша корзина:");
                basket2.printCart();

                break;
            } else {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("Введите номер продукта и количество через пробел в двух частях");
                    continue;
                }
                try {
                    productNumber = Integer.parseInt(parts[0]) - 1;
                    if (productNumber < 0 || products.length < productNumber - 1) {
                        System.out.println("Не верно введен номер продуктов. Введите номер продуктов из предложенного списка");
                        continue;
                    }
                    productCount = Integer.parseInt(parts[1]);
                    if (productCount < 0) {
                        System.out.println("Количество продуктов не может быть отрицательным числом или нулем");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Не верно введены данные. Введите номер продукта и количество прдуктов только числами");
                    continue;
                }
                basket2.addToCart(productNumber, productCount);
                ClientLog clientLog = new ClientLog(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                clientLog.log();

            }
        }
      //  basket2.saveTxt(new File("basket.txt"));
//        basket2.saveTxt(new File("basket.txt"));
//        basket2.saveJSON(new File("basket.json"));
        basket2.saveJSON();
//        Basket basket3 = new Basket();

    }
}

