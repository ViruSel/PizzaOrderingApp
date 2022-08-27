package model;

import java.io.*;
import java.util.LinkedList;

/**
 * Data storage class - incomplete
 */

public class DataStorage
{
    private final String STORAGE_FORMAT = "text";
    private final File file = new File("order.bin");

    /**
     * Reads data from a .bin file
     * However, I was unable to make it actually work after a lot of tries...
     * @return Order
     * @throws IOException
     */

    public Order readOrderBin() throws IOException
    {
        DataInputStream dataIn = new DataInputStream(new FileInputStream(file));

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
     * @throws IOException
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
     * @return
     * @throws IOException
     */

    public Order readOrder() throws IOException
    {
        Order order = new Order();
        return order.readOrderCSV();
    }

    /**
     * Redirecting writing to a .csv file
     * @param order Order to be written
     * @throws IOException
     */

    public void writeOrder(Order order) throws IOException { order.writeOrderCSV(order); }

    public String getSTORAGE_FORMAT() { return STORAGE_FORMAT; }
}
