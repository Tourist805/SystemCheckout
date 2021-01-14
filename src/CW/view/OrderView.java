package CW.view;

import CW.model.Database;
import CW.model.IModel;
import CW.model.StockItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class OrderView extends AbstractView {
    private JTable table;
    private JLabel count;
    private JPanel panelView;
    private JButton pay;
    private String filepath = "resources\\order.txt";
    private boolean DEBUG = false;
    private ArrayList<StockItem> orderItems;
    Database database;

    public OrderView(Database database){

        this.database = database;
        database.registerObserver(this);
        String[] col = {"Item  barcode", "Item Name", "Item Price"};
        DefaultTableModel tableModel = new DefaultTableModel(col, 0);

        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        database.loadOrderItems(new File(filepath));

        orderItems = database.getOrderItems();

        for(int i = 0; i < orderItems.size(); i++){
            String barcode = orderItems.get(i).getBarcode();
            String name = orderItems.get(i).getName();
            double price = orderItems.get(i).getPrice();

            Object[] data = {barcode, name, price};

            tableModel.addRow(data);
        }

        if(DEBUG){
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        panelView = new JPanel();

        count = new JLabel("Total: " + 0.0);
        count.setText("Total: " + database.getCount());

        panelView.add(table);
        panelView.add(count);

        JScrollPane scrollPane = new JScrollPane(panelView);

        add(scrollPane);

        initialize();

        setPreferredSize(new Dimension(400, 300));

    }

    public void printDebugData(JTable table){
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        TableModel tableModel = table.getModel();

        System.out.println("Value of data: ");

        for(int i = 0; i <  numRows; i++){
            System.out.println("   row " + i + ":");
            for(int j = 0; j < numCols; j++){
                System.out.println(" " + tableModel.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------");
    }


    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void setModel(Database model) {

    }
}
