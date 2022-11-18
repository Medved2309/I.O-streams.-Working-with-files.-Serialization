package main.java.clientLog;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;


public class ClientLog {

    private int productNumber; // номер продукта
    private int productCount; // колличество продукта

    @Override
    public String toString() {
        return productNumber + "," + productCount;
    }

    public ClientLog(int productNumber, int productCount) {
        this.productNumber = productNumber;
        this.productCount = productCount;
    }


    public void log() {
        try (CSVWriter writer = new CSVWriter(new FileWriter("log.csv", true))) {
            writer.writeNext(toString().split(","));
        } catch (IOException e) {
            System.out.println("Ы");
        }
    }
}
