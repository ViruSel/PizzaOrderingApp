package de.hhn.mib.prog2.blatt5.view;

import de.hhn.mib.prog2.blatt5.model.DataStorage;
import de.hhn.mib.prog2.blatt5.model.Order;
import de.hhn.mib.prog2.blatt5.model.Pizza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;

public class MyMenuBar extends JMenuBar
{
    // Global variables
    private final JMenuItem saveFile, loadFile, openHelp, openHelpVideo, exit;
    private Pizza pizza;
    private final Order order;
    private final PizzaConfigPanel configPanel;
    private final ImageIcon imgLQ = new ImageIcon(Objects.requireNonNull(getClass().getResource("../assets/PizzaSteveLow.png")));

    /**
     * de.hhn.mib.prog2.blatt5.Main constructor
     * @param order new Order
     * @param pizza new Pizza
     * @param configPanel config panel view to make references and instances
     */
    public MyMenuBar(Order order, Pizza pizza, PizzaConfigPanel configPanel)
    {
        this.order = order;
        this.pizza = pizza;
        this.configPanel = configPanel;

        JMenu fileMenu = new JMenu("File");
        JMenu orderMenu = new JMenu("Order");
        JMenu helpMenu = new JMenu("Help");

        exit = new JMenuItem("Exit");
        saveFile = new JMenuItem("Save Order");
        loadFile = new JMenuItem("Load");
        openHelp = new JMenuItem("Open Support Page");
        openHelpVideo = new JMenuItem("Open Help Video");

        helpMenu.add(openHelpVideo);

        fileMenu.add(exit);
        orderMenu.add(loadFile);
        orderMenu.add(saveFile);
        helpMenu.add(openHelp);

        this.add(fileMenu);
        this.add(orderMenu);
        this.add(helpMenu);
    }

    // Actions to be performed

    public void exitAction()
    {
        int input = JOptionPane.showConfirmDialog(this, "Do you really want to exit Pizza Configurator?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imgLQ);

        if(input == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    public void loadAction() throws IOException
    {
        JDialog dialog = new JDialog();

        Order order = new Order();
        DataStorage dataStorage = new DataStorage();
        LinkedList<Pizza> pizzaList;

        switch (dataStorage.getSTORAGE_FORMAT())
        {
            case "text" :
                order = order.readOrderCSV();
                break;
            case "binary":
                order = dataStorage.readOrderBin();
                break;
        }

        pizzaList = order.getPizzaList();

        for(Pizza pizza : pizzaList)
            dialog.add(new JLabel(pizza.getPrice()+" "+pizza.getSize()+" "+pizza.getPizzaToppings()));

        dialog.setLayout(new FlowLayout());
        dialog.setSize(700, 300);
        dialog.setVisible(true);
    }

    public void savePizzaAction() throws IOException
    {
        int input = JOptionPane.showConfirmDialog(this, "Do you really want save your pizza configuration?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imgLQ);

        DataStorage dataStorage = new DataStorage();

        configPanel.updatePizza();
        this.pizza = configPanel.getPizza();

        if(input == JOptionPane.YES_OPTION)
        {
            order.addPizza(pizza);

            switch (dataStorage.getSTORAGE_FORMAT())
            {
                case "text" :
                    dataStorage.writeOrder(order);
                    break;
                case "binary":
                    dataStorage.writeOrderBin(order);
                    break;
            }

            configPanel.resetOrder();
        }
    }

    public void openHelpAction() throws IOException, URISyntaxException { Desktop.getDesktop().browse(new URL("https://pizzahut.de/").toURI()); }

    public void openHelpVideoAction() throws IOException, URISyntaxException { Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=dQw4w9WgXcQ").toURI()); }

    // Showing errors
    public void showError(String error) { JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE, imgLQ); }


    // Adding listeners for every Button/Menu/Item
    public void addExitListener(ActionListener eal) {exit.addActionListener(eal);}

    public void addSaveListener(ActionListener sal) {saveFile.addActionListener(sal);}

    public void addLoadListener(ActionListener lal) {loadFile.addActionListener(lal);}

    public void addHelpListener(ActionListener hal) {openHelp.addActionListener(hal);}

    public void addHelpVideoListener(ActionListener val) {openHelpVideo.addActionListener(val);}

}
