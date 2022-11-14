import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[] products = {"Молоко", "Хлеб", "Гречнивая крупа", "Курица", "Яйца куринные"};
        int[] prices = {50, 14, 80, 100, 90};
        int[] basket = new int[products.length];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
        while (true) {
            int productNumber = 0; // номер продукта
            int productCount = 0; // колличество продукта
            int totalAmount = 0;  // Итоговая сумма продуктов корзине
            int sumProduct = 0; // сумма продуктов
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("Ваша корзина:");
                for (int i = 0; i < basket.length; i++) {
                    if (basket[i] > 0) {
                        sumProduct = (prices[i] * basket[i]);
                        totalAmount = totalAmount + sumProduct;
                        System.out.println(products[i] + " " + basket[i] + " шт " + prices[i] + " руб/шт  " + sumProduct + " руб в сумме");
                    }
                }
                System.out.println("Итоговая сумма к оплате: " + totalAmount);
                break;
            } else {
                String[] parts = input.split(" ");
                if (parts.length == 1 || parts.length > 2) {
                    System.out.println("Введите номер продукта и количество через пробел в двух частях");
                    continue;
                }
                productNumber = Integer.parseInt(parts[0]) - 1;
                if (productNumber + 1 < products.length || productNumber + 1 > products.length) {
                    System.out.println("Введите номер продуктов из предложенного списка");
                    continue;
                }
                productCount = Integer.parseInt(parts[1]);

                if (productCount < 0) {
                    System.out.println("Количество продуктов не может быть отрицательным числом или нулем");
                    continue;

                }
                basket[productNumber] = productCount + basket[productNumber];


            }
        }

    }
}

