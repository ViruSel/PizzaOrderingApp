package main;

import controller.MyMenuBarController;
import controller.PizzaConfigController;
import model.Order;
import model.Pizza;
import view.MyMenuBar;
import view.PizzaConfigPanel;

/**
 * Pizza configurator main class
 * @author Alexandru Moldovan
 * @version 5.0
 */

public class Main
{
    public static void main(String[] args)
    {
        Order order = new Order();
        Pizza pizza = new Pizza();

        PizzaConfigPanel pizzaConfigPanel = new PizzaConfigPanel(order, pizza);
        MyMenuBar menuBar = new MyMenuBar(order, pizza, pizzaConfigPanel);
        new MyMenuBarController(order, pizza, menuBar);
        new PizzaConfigController(order, pizza, pizzaConfigPanel);

        pizzaConfigPanel.setJMenuBar(menuBar);
        pizzaConfigPanel.setVisible(true);
    }
}