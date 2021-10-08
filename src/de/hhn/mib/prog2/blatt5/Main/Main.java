package de.hhn.mib.prog2.blatt5.Main;

import de.hhn.mib.prog2.blatt5.controller.MyMenuBarController;
import de.hhn.mib.prog2.blatt5.controller.PizzaConfigController;
import de.hhn.mib.prog2.blatt5.model.Order;
import de.hhn.mib.prog2.blatt5.model.Pizza;
import de.hhn.mib.prog2.blatt5.view.MyMenuBar;
import de.hhn.mib.prog2.blatt5.view.PizzaConfigPanel;

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
        MyMenuBarController menuBarController = new MyMenuBarController(order ,pizza, menuBar);
        PizzaConfigController pizzaConfigController = new PizzaConfigController(order, pizza, pizzaConfigPanel);

        pizzaConfigPanel.setJMenuBar(menuBar);
        pizzaConfigPanel.setVisible(true);
    }
}