package de.hhn.mib.prog2.blatt5.view;

import de.hhn.mib.prog2.blatt5.model.Order;
import de.hhn.mib.prog2.blatt5.model.Pizza;
import de.hhn.mib.prog2.blatt5.model.PizzaSize;
import de.hhn.mib.prog2.blatt5.model.PizzaTopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Pizza configurator main view
 */

public class PizzaConfigPanel extends JFrame
{
    private Order order;
    private final Pizza pizza;
    private final JComboBox<String> pizzaSizeList;
    private final JLabel orderStatus;
    private final JButton reset = new JButton("Reset");
    private final JButton done = new JButton("Done");
    private final JRadioButton tomato, cheese, salami, ham, ananas, vegetables, seaFood, nutella, sourCream;
    private final ImageIcon imgLQ = new ImageIcon(Objects.requireNonNull(getClass().getResource("../assets/PizzaSteveLow.png")));
    private final LinkedList<JRadioButton> toppingsButtonList;
    private final HashMap<JRadioButton, PizzaTopping> toppingsList;

    /**
     * de.hhn.mib.prog2.blatt5.Main constructor
     * @param order new order
     * @param pizza new pizza
     */

    public PizzaConfigPanel(Order order, Pizza pizza)
    {
        super("Pizza Configurator");

        this.setLayout(new GridLayout());

        this.order = order;
        this.pizza = pizza;

        // Local variables
        JPanel panelLeft = new JPanel();
        JPanel panelLeftRight = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel basePanelLeft = new JPanel();
        JPanel basePanelRight = new JPanel();
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("../assets/PizzaSteve.png")));
        JLabel imgLabel = new JLabel(img);
        JLabel sizeLabel = new JLabel("Size: ");
        JLabel toppingsLabel = new JLabel("Toppings: ");
        String[] pizzaSizes = {"Small", "Medium", "Large", "Extra large"};

        // Variable's assignment
        pizzaSizeList = new JComboBox<>(pizzaSizes);
        toppingsButtonList = new LinkedList<>();
        toppingsList = new HashMap<>();
        orderStatus = new JLabel();
        tomato = new JRadioButton("Tomato");
        cheese = new JRadioButton("Cheese");
        salami = new JRadioButton("Salad");
        ham = new JRadioButton("Ham");
        ananas = new JRadioButton("Ananas");
        vegetables = new JRadioButton("Vegetables");
        seaFood = new JRadioButton("Sea Food");
        nutella = new JRadioButton("Nutella");
        sourCream = new JRadioButton("Sour Cream");

        orderStatus.setVisible(false);

        // adding toppings
        toppingsButtonList.add(tomato);
        toppingsButtonList.add(cheese);
        toppingsButtonList.add(salami);
        toppingsButtonList.add(ham);
        toppingsButtonList.add(ananas);
        toppingsButtonList.add(vegetables);
        toppingsButtonList.add(seaFood);
        toppingsButtonList.add(nutella);
        toppingsButtonList.add(sourCream);

        // adding toppings to the array of toppings
        toppingsList.put(tomato, PizzaTopping.TOMATO);
        toppingsList.put(cheese, PizzaTopping.CHEESE);
        toppingsList.put(salami, PizzaTopping.SALAMI);
        toppingsList.put(ham, PizzaTopping.HAM);
        toppingsList.put(ananas, PizzaTopping.ANANAS);
        toppingsList.put(vegetables, PizzaTopping.VEGETABLES);
        toppingsList.put(seaFood, PizzaTopping.SEAFOOD);
        toppingsList.put(nutella, PizzaTopping.NUTELLA);
        toppingsList.put(sourCream, PizzaTopping.SOUR_CREAM);

        // Laying out the panel
        panelLeft.setBackground(Color.LIGHT_GRAY);
        panelLeft.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        panelLeftRight.setBackground(Color.WHITE);
        panelLeftRight.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        panelRight.setBackground(Color.PINK);
        panelRight.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        basePanelLeft.setLayout(new GridLayout());
        basePanelRight.setLayout(new GridLayout());

        // Adding labels / buttons
        panelLeft.add(sizeLabel);
        panelLeft.add(toppingsLabel, BorderLayout.AFTER_LAST_LINE);

        panelLeftRight.add(pizzaSizeList);
        panelLeftRight.add(tomato);
        panelLeftRight.add(cheese);
        panelLeftRight.add(salami);
        panelLeftRight.add(ham);
        panelLeftRight.add(ananas);
        panelLeftRight.add(vegetables);
        panelLeftRight.add(seaFood);
        panelLeftRight.add(nutella);
        panelLeftRight.add(sourCream);

        panelRight.add(imgLabel);
        panelRight.add(done);
        panelRight.add(reset);
        panelRight.add(orderStatus);

        basePanelLeft.add(panelLeft);
        basePanelLeft.add(panelLeftRight);
        basePanelRight.add(panelRight);

        this.add(basePanelLeft);
        this.add(basePanelRight);
        this.setSize(625, 500);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    // Defining actions
    public void doneAction() throws IOException
    {
        int input = JOptionPane.showConfirmDialog(this, "Do you really want to send this order?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imgLQ);

        if (input == JOptionPane.YES_OPTION)
        {
            updatePizza();

            order.addPizza(pizza);
            order.writeOrderCSV(order);

            orderStatus.setText("Order has been sent");
            orderStatus.setVisible(true);

            order = new Order();

            resetOrder();
        }
    }

    public void resetOrderAction()
    {
        int input = JOptionPane.showConfirmDialog(this, "Do you really want to reset the order?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imgLQ);

        if(input == JOptionPane.YES_OPTION)
        {
            orderStatus.setText("Order has been reset");

            resetOrder();
        }
    }

    public void closeOnExit()
    {
        int input = JOptionPane.showConfirmDialog(this, "Do you really want to exit Pizza Configurator?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imgLQ);

        if(input == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    // Updating information
    public void updatePizza()
    {
        switch (Objects.requireNonNull(pizzaSizeList.getSelectedItem()).toString())
        {
            case "Small":
                this.pizza.setSize(PizzaSize.SMALL);
                break;
            case "Medium":
                this.pizza.setSize(PizzaSize.MEDIUM);
                break;
            case "Large":
                this.pizza.setSize(PizzaSize.LARGE);
                break;
            case "Extra large":
                this.pizza.setSize(PizzaSize.EXTRA_LARGE);
                break;
        }

        for (JRadioButton topping : toppingsButtonList)
            if(topping.isSelected())
                this.pizza.addPizzaTopping(toppingsList.get(topping));

        this.pizza.calculatePrice();
    }

    public void resetOrder()
    {
        order.removePizza(pizza);

        for (JRadioButton topping : toppingsButtonList)
            pizza.removePizzaTopping(toppingsList.get(topping));

        pizzaSizeList.setSelectedItem("Small");
        pizza.setSize(PizzaSize.SMALL);

        tomato.setSelected(false);
        cheese.setSelected(false);
        salami.setSelected(false);
        ham.setSelected(false);
        ananas.setSelected(false);
        vegetables.setSelected(false);
        seaFood.setSelected(false);
        nutella.setSelected(false);
        sourCream.setSelected(false);
    }

    // Making possible to add listeners
    public void addResetListener(ActionListener ral) { reset.addActionListener(ral); }

    public void addDoneListener(ActionListener dal) { done.addActionListener(dal); }

    public void addCloseListener(WindowListener wl) {this.addWindowListener(wl);}


    // Getters / setters
    public Order getOrder() {
        return order;
    }

    public Pizza getPizza() {
        return pizza;
    }
}
