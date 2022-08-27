package model;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.util.LinkedList;

/**
 * Data storage class
 */
//TODO Complete functionalities
public class DataStorage
{
    private final File file = new File("order.bin");

    /**
     * Reads data from a .bin file
     * However, I was unable to make it actually work after a lot of tries...
     * @return Order
     */
    public Order readOrderBin() throws IOException
    {
        DataInputStream dataIn = new DataInputStream(Files.newInputStream(file.toPath()));

        Order order = new Order();

        int count = dataIn.available();
        byte[] b = new byte[count];

        int bytes = dataIn.read(b, 100, 100);

        System.out.println(bytes);

        for (byte by : b)
        {
            // Print the character
            System.out.print((char)by);
        }

        return order;
    }

    /**
     * Writes data to a .bin file
     * @param order Order to be written
     */
    public void writeOrderBin(Order order) throws IOException
    {
        DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(file, true));

        LinkedList<Pizza> pizzaList = order.getPizzaList();
        LinkedList<PizzaTopping> toppings;

        for (Pizza pizza : pizzaList)
        {
            dataOut.writeInt((int)pizza.calculatePrice());
            dataOut.writeBytes(pizza.getSize()+",");

            toppings = pizza.getPizzaToppings();

            for (PizzaTopping topping : toppings)
                dataOut.writeBytes(topping +" ");

            dataOut.writeBytes("\n");
        }
    }

    /**
     * Redirecting to reading from a .csv file
     * @return new order from .csv file
     */
    public Order readOrder() throws IOException
    {
        Order order = new Order();
        return order.readOrderCSV();
    }

    /**
     * Redirecting writing to a .csv file
     * @param order Order to be written
     */
    public void writeOrder(Order order) throws IOException { order.writeOrderCSV(order); }

    public String getSTORAGE_FORMAT() {
        String STORAGE_FORMAT = "text";
        return STORAGE_FORMAT; }
}
