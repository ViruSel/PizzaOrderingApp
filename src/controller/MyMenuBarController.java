package controller;

import model.Order;
import model.Pizza;
import view.MyMenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Pizza menu bar controller class
 */
public class MyMenuBarController
{
    Order order;
    Pizza pizza;
    MyMenuBar myMenuBar;

    /**
     * Main constructor
     * @param order new order
     * @param pizza new pizza
     * @param myMenuBar menu bar view
     */
    public MyMenuBarController (Order order, Pizza pizza, MyMenuBar myMenuBar)
    {
        this.order = order;
        this.pizza = pizza;
        this.myMenuBar = myMenuBar;

        //adding listeners to panel's buttons
        myMenuBar.addExitListener(new exitActionListener());
        myMenuBar.addSaveListener(new saveActionListener());
        myMenuBar.addLoadListener(new loadActionListener());
        myMenuBar.addHelpListener(new helpActionListener());
        myMenuBar.addHelpVideoListener(new helpVideoActionListener());
    }

    // Overriding actions to the corresponding buttons

    class exitActionListener implements ActionListener
    {
        /**
         * Invoked when an action occurs.
         */
        @Override
        public void actionPerformed(ActionEvent e) { myMenuBar.exitAction(); }
    }

    class saveActionListener implements ActionListener
    {
        /**
         * Invoked when an action occurs.
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try { myMenuBar.savePizzaAction(); }
            catch (IOException ioException) { ioException.printStackTrace(); }
        }
    }

    class loadActionListener implements ActionListener
    {
        /**
         * Invoked when an action occurs.
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try { myMenuBar.loadAction(); }
            catch (FileNotFoundException fileNotFoundException) { myMenuBar.showError("File not found"); }
            catch (IOException ioException) { ioException.printStackTrace(); }
        }
    }

    class helpActionListener implements ActionListener
    {

        /**
         * Invoked when an action occurs.
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try { myMenuBar.openHelpAction(); }
            catch (IOException | URISyntaxException ioException) { ioException.printStackTrace(); }
        }
    }

    class helpVideoActionListener implements ActionListener
    {
        /**
         * Invoked when an action occurs.
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try { myMenuBar.openHelpVideoAction(); }
            catch (IOException | URISyntaxException ioException) { ioException.printStackTrace(); }
        }
    }
}
