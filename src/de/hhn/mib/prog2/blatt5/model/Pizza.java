package de.hhn.mib.prog2.blatt5.model;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Pizza class for creating a pizza
 */

public class Pizza
{
    private Double price;
    private PizzaSize size;
    private final LinkedList<PizzaTopping> pizzaToppings = new LinkedList<>(); // Pizza toppings list
    private final HashMap<PizzaSize, Double> pricePerPizza = new HashMap<>(); // Saving prices per pizza size
    private final HashMap<PizzaTopping, Double> pricePerTopping = new HashMap<>(); // Saving prices per pizza topping

    /**
     * Sets pizza prices per size
     */

    private void updatePizzaPrices()
    {
        pricePerPizza.put(PizzaSize.SMALL, 5.0);
        pricePerPizza.put(PizzaSize.MEDIUM, 6.0);
        pricePerPizza.put(PizzaSize.LARGE, 8.0);
        pricePerPizza.put(PizzaSize.EXTRA_LARGE, 11.0);
    }

    /**
     * Sets pizza topping's prices
     */

    private void updateToppingPrices()
    {
        pricePerTopping.put(PizzaTopping.TOMATO, 0.50);
        pricePerTopping.put(PizzaTopping.CHEESE, 0.50);
        pricePerTopping.put(PizzaTopping.SALAMI, 0.50);
        pricePerTopping.put(PizzaTopping.HAM, 0.50);
        pricePerTopping.put(PizzaTopping.ANANAS, 0.50);
        pricePerTopping.put(PizzaTopping.VEGETABLES, 0.50);
        pricePerTopping.put(PizzaTopping.SEAFOOD, 0.50);
        pricePerTopping.put(PizzaTopping.NUTELLA, 0.50);
        pricePerTopping.put(PizzaTopping.SOUR_CREAM, 0.50);
    }

    /**
     * @return Pizza price
     */

    public Double getPrice() { return price; }

    /**
     * Set new Pizza price
     * @param price new price
     */

    public void setPrice(Double price) { this.price = price; }

    /**
     * Sets pizza size by enum
     */

    public void setSize(PizzaSize size) { this.size = size; }

    /**
     * Set Pizza size by string
     * @param str - size (String)
     */

    public void setSizeStr(String str)
    {
        switch (str)
        {
            case "SMALL":
                setSize(PizzaSize.SMALL);
                break;
            case "MEDIUM":
                setSize(PizzaSize.MEDIUM);
                break;
            case "LARGE":
                setSize(PizzaSize.LARGE);
                break;
            case "EXTRA_LARGE":
                setSize(PizzaSize.EXTRA_LARGE);
                break;
        }
    }

    /**
     * @return pizza size
     */

    public PizzaSize getSize() { return size; }

    /**
     * Adds a pizza topping to the pizza toppings list
     */

    public void addPizzaTopping(PizzaTopping pizzaTopping) { pizzaToppings.add(pizzaTopping); }

    /**
     * Set Pizza topping by string
     * @param str size (String)
     */

    public void addPizzaToppingStr(String str)
    {
        switch (str)
        {
            case "TOMATO":
                pizzaToppings.add(PizzaTopping.TOMATO);
                break;
            case "CHEESE":
                pizzaToppings.add(PizzaTopping.CHEESE);
                break;
            case "SALAMI":
                pizzaToppings.add(PizzaTopping.SALAMI);
                break;
            case "HAM":
                pizzaToppings.add(PizzaTopping.HAM);
                break;
            case "ANANAS":
                pizzaToppings.add(PizzaTopping.ANANAS);
                break;
            case "VEGETABLES":
                pizzaToppings.add(PizzaTopping.VEGETABLES);
                break;
            case "SEAFOOD":
                pizzaToppings.add(PizzaTopping.SEAFOOD);
                break;
            case "NUTELLA":
                pizzaToppings.add(PizzaTopping.NUTELLA);
                break;
            case "SOUR_CREAM":
                pizzaToppings.add(PizzaTopping.SOUR_CREAM);
                break;
        }
    }

    /**
     * Removes a pizza topping from the pizza toppings list
     */

    public void removePizzaTopping(PizzaTopping pizzaTopping) { pizzaToppings.remove(pizzaTopping); }

    /**
     * Writes on screen all the toppings on the pizza
     */

    public LinkedList<PizzaTopping> getPizzaToppings() { return pizzaToppings; }

    /**
     * Calculus for Pizza's price
     * @return Pizza price
     */

    public double calculatePrice()
    {
        updatePizzaPrices();
        updateToppingPrices();

        price = pricePerPizza.get(size);

        for(PizzaTopping pizzaTopping : pizzaToppings)
            price+=pricePerTopping.get(pizzaTopping);

        return price;
    }

    /**
     * @return Pizza specifications
     */

    @Override
    public String toString() {
        return "Pizza{" +
                "price=" + price +
                ", size=" + size +
                ", pizzaToppings=" + pizzaToppings +
                '}';
    }
}
