package CW.view;

import CW.controller.AbstractController;
import CW.model.Database;
import CW.model.IModel;
import CW.model.StockItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class KioskView extends AbstractView {
    private JLabel label;
    private JLabel jlabel;
    private JScrollPane scrollPane;
    private String[] dataItems;
    private JButton scan;
    private JButton pay;
    private String filePath = "resources\\stock.txt";
    private String filePath1 = "resources\\order.txt";
    private Database database;
    private String currentItem;
    private IModel model;

    private ArrayList<StockItem> items = new ArrayList<>();

    public KioskView(Database database){
        this.database = database;
        this.controller = controller;
        database.registerObserver(this);
        //this.model = model;
        initUI();
        initialize();
        setTitle("KioskUserInterface");
        setSize(new Dimension(300, 300));
        setLocationRelativeTo(null);
    }

    private void initUI(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        database.loadStockItems(new File(filePath));
        items = database.getStockItems();

        dataItems = new String[100];
        for(int i = 0; i < items.size(); i++){
            dataItems[i] = items.get(i).getName();
        }

        JList list = new JList(dataItems);
        label = new JLabel();
        jlabel = new JLabel();
        scan = new JButton("Scan");
        scan.setToolTipText("Scan in order to add your item to the order list");
        pay = new JButton("Pay");

        list.addListSelectionListener(e ->{
            if(!e.getValueIsAdjusting()){
                String name = (String) list.getSelectedValue();

                label.setText("You've selected: " + name);
                jlabel.setText(name);
                currentItem = name;
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(list);

        scan.addActionListener(new ScanAction(currentItem));

        createLayout(scrollPane, label, scan, pay);
    }

    private void createLayout(JComponent... arg){
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[2])
                        .addComponent(arg[3]))

        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[2])
                        .addComponent(arg[3]))
        );



        pack();
    }

    @Override
    public void update() {
        if(database != null){
            SwingUtilities.updateComponentTreeUI(this);
        }
    }

    @Override
    public void setModel(Database model) {
        this.database = model;
    }

    private class ScanAction extends AbstractAction{
        private String itemName;

        public ScanAction(String itemName){
            this.itemName = itemName;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            scanAction();
        }

        private void scanAction(){

            //database.loadOrderItems(new File(filePath1));

            database.loadStockItems(new File(filePath));

            StockItem item = database.getStockItemAt(currentItem);

            database.addOrderItems(item);

            database.saveOrderItem(new File(filePath1));
        }
    }

    private class PayAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}
