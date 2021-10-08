import de.hhn.mib.prog2.blatt5.model.Order;
import de.hhn.mib.prog2.blatt5.model.Pizza;
import de.hhn.mib.prog2.blatt5.model.PizzaSize;
import de.hhn.mib.prog2.blatt5.model.PizzaTopping;

import java.io.*;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest extends Order
{
    static Order order;
    static Pizza pizza;
    static final File file = new File("test\\order.csv");

    public static void testWriteOrderCSV() throws IOException
    {
        FileWriter writer = new FileWriter(file, true);

        order = new Order();
        pizza = new Pizza();

        LinkedList<PizzaTopping> pizzaToppings = new LinkedList<>();
        LinkedList<Pizza> pizzaList = new LinkedList<>();

        pizzaToppings.add(PizzaTopping.SALAMI);

        pizza.setSize(PizzaSize.SMALL);
        pizza.addPizzaTopping(PizzaTopping.SALAMI);
        pizza.calculatePrice();

        order.setOrderId(1);
        order.addPizza(pizza);
        pizzaList.add(pizza);

        // Order & Pizza mustn't give any errors
        assertEquals(PizzaSize.SMALL, pizza.getSize());
        assertEquals(pizzaToppings, pizza.getPizzaToppings());
        assertEquals(5.5, pizza.getPrice());
        assertEquals(pizzaList, order.getPizzaList());
        assertEquals(1, order.getOrderId());


        // Extracted from Order - writeOrderCSV()
        for (Pizza pizza : pizzaList)
        {
            writer.append(String.valueOf(pizza.calculatePrice())).append(",").append(String.valueOf(pizza.getSize())).append(",");

            pizzaToppings = pizza.getPizzaToppings();

            for (PizzaTopping topping : pizzaToppings)
                writer.append(String.valueOf(topping)).append(" ");

            writer.append('\n');
        }

        writer.close();
    }

    public static void testReadOrderCSV() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(file));

        order = new Order();
        pizza = new Pizza();

        Order orderFromFile = new Order();
        Pizza pizzaFromFile = new Pizza();

        String pizzaString;
        String[] pizzas;
        String[] toppings;

        pizza.setSize(PizzaSize.SMALL);
        pizza.addPizzaTopping(PizzaTopping.SALAMI);
        pizza.calculatePrice();

        order.setOrderId(1);
        order.addPizza(pizza);

        // Extracted from Order - readOrderCSV()
        while ((pizzaString = br.readLine())!= null)
        {
            pizzaFromFile = new Pizza();
            pizzas = pizzaString.split(",");

            pizzaFromFile.setPrice(Double.parseDouble(pizzas[0]));

            pizzaFromFile.setSizeStr(pizzas[1]);

            toppings = pizzas[2].split(" "); // Divide pizza's toppings

            for(String s : toppings)
                pizzaFromFile.addPizzaToppingStr(s);

            orderFromFile.addPizza(pizzaFromFile);
        }

        orderFromFile.setOrderId(1);

        // Verifying order from file
        assertEquals(order.getOrderId(), orderFromFile.getOrderId());

        // Verifying pizza from file
        assertEquals(pizza.getSize(), pizzaFromFile.getSize());
        assertEquals(pizza.getPrice(), pizzaFromFile.getPrice());
        assertEquals(pizza.getPizzaToppings(), pizzaFromFile.getPizzaToppings());
    }

    public static void main(String[] args) throws IOException
    {
        testWriteOrderCSV();
        testReadOrderCSV();
    }
}
