package de.hhn.mib.prog2.blatt5.controller;

import de.hhn.mib.prog2.blatt5.model.Order;
import de.hhn.mib.prog2.blatt5.model.Pizza;
import de.hhn.mib.prog2.blatt5.view.PizzaConfigPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 * Pizza configurator controller class
 */

public class PizzaConfigController
{
    Order order;
    Pizza pizza;
    PizzaConfigPanel pizzaConfigPanel;

    /**
     * de.hhn.mib.prog2.blatt5.Main constructor
     * @param order new order
     * @param pizza new pizza
     * @param pizzaConfigPanel pizza configurator view
     */

    public PizzaConfigController(Order order, Pizza pizza, PizzaConfigPanel pizzaConfigPanel)
    {
        this.order = order;
        this.pizza = pizza;
        this.pizzaConfigPanel = pizzaConfigPanel;

        // adding listeners to panel's buttons
        pizzaConfigPanel.addResetListener(new ResetActionListener());
        pizzaConfigPanel.addDoneListener(new DoneActionListener());
        pizzaConfigPanel.addCloseListener(new CloseWindowListener());
    }

    /**
     * Overriding actions to the corresponding buttons
     */

    class ResetActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            pizzaConfigPanel.resetOrderAction();
        }
    }

    class DoneActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            { pizzaConfigPanel.doneAction(); }
            catch (IOException ioException)
            { ioException.printStackTrace(); }
        }
    }

    class CloseWindowListener implements WindowListener
    {

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) { pizzaConfigPanel.closeOnExit(); }

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) { }
    }
}
