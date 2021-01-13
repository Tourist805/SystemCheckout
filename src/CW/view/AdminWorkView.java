package CW.view;

import CW.model.Database;
import CW.model.IModel;
import CW.model.StockItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class AdminWorkView extends AbstractView{
    private DefaultListModel<String> model;
    private JList<String> stockList;
    private JButton remAllBtn;
    private JButton addBtn;
    private JButton renameBtn;
    private JButton deleteBtn;
    Database database;
    private String filePath = "resources\\stock.txt";
    private ArrayList<StockItem> items = new ArrayList<>();
    private String[] dataItems;

    public AdminWorkView(Database database){
        this.database = database;

        initUI();
        initialize();
        setTitle("Admin Stock");
        setSize(new Dimension(300, 300));
        setLocationRelativeTo(null);

    }

    private void createList(){
        //database.loadStockItems(new File(filePath));
        items = database.getStockItems();

        dataItems = new String[100];
        for(int i = 0; i < items.size(); i++){
            dataItems[i] = items.get(i).getName();
        }

        stockList = new JList(dataItems);

        stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        stockList.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    int index = stockList.locationToIndex(e.getPoint());
                    var item = database.getStockItemAt(index);

                    JTextField barcodeField = new JTextField(20);
                    JTextField nameField = new JTextField(20);
                    JTextField priceField = new JTextField(20);

                    JPanel panel = new JPanel();
                    panel.add(new JLabel("Barcode: "));
                    panel.add(barcodeField);
                    panel.add(new JLabel("Name: "));
                    panel.add(nameField);
                    panel.add(new JLabel("Price"));
                    panel.add(priceField);

                    int result = JOptionPane.showConfirmDialog(null, panel,
                            "Please Enter item deliverables", JOptionPane.OK_CANCEL_OPTION);

                    //var text = JOptionPane.showInputDialog("Rename item", item.getName());
                    //model.getElementAt(index);
//                    String newItem;
//
//                    if (text != null) {
//                        newItem = text.trim();
//                    } else {
//                        return;
//                    }

                    if(result == JOptionPane.OK_OPTION){
                        String newBarcode = barcodeField.getText();
                        String newName = barcodeField.getText();
                        String newPrice = priceField.getText();
                        StockItem newItem = new StockItem();
                        newItem.setBarcode(newBarcode);
                        newItem.setName(newName);
                        newItem.setPrice(Double.parseDouble(newPrice));
                        database.StockItemRemove(index);
                        database.addElementToStock(index, newItem);

                        var selModel = stockList.getSelectionModel();
                        selModel.setLeadSelectionIndex(index);
                    }
                }
            }
        });
    }

    private void createButtons(){
        remAllBtn = new JButton("Remove All");
        addBtn = new JButton("Add");
        renameBtn = new JButton("Rename");
        deleteBtn = new JButton("Delete");

        addBtn.addActionListener(e -> {

            var text = JOptionPane.showInputDialog("Add a new item");
            String item;

            if (text != null) {
                item = text.trim();
            } else {
                return;
            }

            if (!item.isEmpty()) {

                model.addElement(item);
            }
        });

        deleteBtn.addActionListener(event -> {

            var selModel = stockList.getSelectionModel();

            int index = selModel.getMinSelectionIndex();

            if (index >= 0) {
                model.remove(index);
            }
        });

        renameBtn.addActionListener(e -> {

            var selModel = stockList.getSelectionModel();
            int index = selModel.getMinSelectionIndex();

            if (index == -1) {
                return;
            }

            var item = model.getElementAt(index);
            var text = JOptionPane.showInputDialog("Rename item", item);
            String newItem;

            if (text != null) {
                newItem = text.trim();
            } else {
                return;
            }

            if (!newItem.isEmpty()) {

                model.remove(index);
                model.add(index, newItem);
            }
        });

        remAllBtn.addActionListener(e -> model.clear());
    }

    private void initUI(){
        createList();
        createButtons();

        var scrollPane = new JScrollPane(stockList);
        createLayout(scrollPane, addBtn, renameBtn, deleteBtn, remAllBtn);
    }


    private void createLayout(JComponent... arg) {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addGroup(gl.createParallelGroup()
                        .addComponent(arg[1])
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4]))
        );

        gl.setVerticalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(arg[0])
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[1])
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4]))
        );

        gl.linkSize(addBtn, renameBtn, deleteBtn, remAllBtn);

        pack();
    }

    @Override
    public void setModel(IModel model) {

    }

    @Override
    public void update() {

    }
}
