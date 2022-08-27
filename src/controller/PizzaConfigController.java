package controller;

import model.Order;
import model.Pizza;
import view.PizzaConfigPanel;

import java.awt.*;
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
     * Main constructor
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

    // Overriding actions to the corresponding buttons

    class ResetActionListener implements ActionListener
    {

        /**
         * Invoked when an action occurs.
         */
        @Override
        public void actionPerformed(ActionEvent e) { pizzaConfigPanel.resetOrderAction(); }
    }

    class DoneActionListener implements ActionListener
    {
        /**
         * Invoked when an action occurs.
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try { pizzaConfigPanel.doneAction(); }
            catch (IOException ioException) { ioException.printStackTrace(); }
        }
    }

    class CloseWindowListener implements WindowListener
    {
        /**
         * Invoked the first time a window is made visible.
         */
        @Override
        public void windowOpened(WindowEvent e) {}

        /**
         * Invoked when the user attempts to close the window
         * from the window's system menu.
         */
        @Override
        public void windowClosing(WindowEvent e) { pizzaConfigPanel.closeOnExit(); }

        /**
         * Invoked when a window has been closed as the result
         * of calling dispose on the window.
         */
        @Override
        public void windowClosed(WindowEvent e) {}

        /**
         * Invoked when a window is changed from a normal to a
         * minimized state. For many platforms, a minimized window
         * is displayed as the icon specified in the window's
         * iconImage property.
         * @see Frame#setIconImage
         */
        @Override
        public void windowIconified(WindowEvent e) {}

        /**
         * Invoked when a window is changed from a minimized
         * to a normal state.
         */
        @Override
        public void windowDeiconified(WindowEvent e) {}

        /**
         * Invoked when the Window is set to be the active Window. Only a Frame or
         * a Dialog can be the active Window. The native windowing system may
         * denote the active Window or its children with special decorations, such
         * as a highlighted title bar. The active Window is always either the
         * focused Window, or the first Frame or Dialog that is an owner of the
         * focused Window.
         */
        @Override
        public void windowActivated(WindowEvent e) {}

        /**
         * Invoked when a Window is no longer the active Window. Only a Frame or a
         * Dialog can be the active Window. The native windowing system may denote
         * the active Window or its children with special decorations, such as a
         * highlighted title bar. The active Window is always either the focused
         * Window, or the first Frame or Dialog that is an owner of the focused
         * Window.
         */
        @Override
        public void windowDeactivated(WindowEvent e) { }

    }
}
