package model;

import java.io.*;
import java.util.LinkedList;

/**
 * Order class
 */
public class Order
{
    private long orderId;
    private final File file = new File("order.csv");
    private final LinkedList<Pizza> pizzaList = new LinkedList<>();

    /**
     * Reading from a file
     * @return order with the pizza
     * @throws FileNotFoundException if the program can't find its file
     */
    public Order readOrderCSV() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        Order order = new Order();
        Pizza pizza;
        String pizzaString;
        String[] pizzas;
        String[] toppings;

        while ((pizzaString = br.readLine())!= null)
        {
            pizza = new Pizza();
            pizzas = pizzaString.split(","); // Divide sizes from toppings , 0 = price, 1 = size, 2 = toppings

            pizza.setPrice(Double.parseDouble(pizzas[0]));

            pizza.setSizeStr(pizzas[1]);

            toppings = pizzas[2].split(" "); // Divide pizza's toppings

            for(String s : toppings)
                pizza.addPizzaToppingStr(s);

            order.addPizza(pizza);
        }

        return order;
    }

    /**
     * Writing in a file
     */
    public void writeOrderCSV(Order order) throws IOException
    {
        FileWriter writer = new FileWriter(file, true);

        LinkedList<Pizza> pizzaList = order.getPizzaList();
        LinkedList<PizzaTopping> toppings;

        for (Pizza pizza : pizzaList)
        {
            writer.append(String.valueOf(pizza.calculatePrice())).append(",").append(String.valueOf(pizza.getSize())).append(",");

            toppings = pizza.getPizzaToppings();

            for (PizzaTopping topping : toppings)
                writer.append(String.valueOf(topping)).append(" ");

            writer.append('\n');
        }

        writer.close();
    }

    /**
     * Get order id
     * @return order id
     */
    public long getOrderId() { return orderId; }

    /**
     * Setting new order id
     * @param orderId new order id
     */
    public void setOrderId(long orderId) { this.orderId = orderId; }

    /**
     * Adding pizza to order
     * @param pizza new pizza
     */
    public void addPizza(Pizza pizza) { pizzaList.add(pizza); }

    /**
     * Removing a pizza from an order
     * @param pizza pizza to be removed
     */
    public void removePizza(Pizza pizza) { pizzaList.remove(pizza); }

    /**
     * Get all pizzas from an order
     */
    public LinkedList<Pizza> getPizzaList() { return pizzaList; }

    /**
     * Showing an order
     * @return Order to string
     */
    @Override
    public String toString()
    {
        return "Order{" +
                "orderId=" + orderId +
                ", pizzas=" + pizzaList +
                '}';
    }
}
