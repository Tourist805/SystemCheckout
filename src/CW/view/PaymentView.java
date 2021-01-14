package CW.view;

import CW.controller.AllViewsController;
import CW.controller.PaymentController;
import CW.model.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PaymentView extends AbstractView{
    private JPanel paymentPanel;
    private JLabel total;
    private JButton payCard;
    private JButton payCash;
    Database database;
    private PaymentController controller1;

    public PaymentView(Database database){
        this.database = database;
        initialize();
        controller = new AllViewsController(database, this);
        controller1 = new PaymentController(database, this);

        paymentPanel = new JPanel();
        payCard = new JButton("Card");

        total = new JLabel("Total: " + database.getCount());

        payCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JTextField cardName = new JTextField(15);
                JTextField code = new JTextField(15);
                panel.add(new JLabel("Card Name: "));
                panel.add(cardName);
                panel.add(new JLabel("Card code: "));
                panel.add(code);

                int result = JOptionPane.showConfirmDialog(null, panel,
                        "Please Enter card deliverables", JOptionPane.OK_CANCEL_OPTION);

                if(result == JOptionPane.OK_OPTION){
                    controller.addAction("Welcome", database);
                }
            }
        });
        payCash = new JButton("Cash");
        payCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                JTextField cashAmount = new JTextField(15);
                panel.add(new JLabel("Cash Amount: "));
                panel.add(cashAmount);


                int result = JOptionPane.showConfirmDialog(null, panel,
                        "Please Enter card deliverables", JOptionPane.OK_CANCEL_OPTION);

                if(result == JOptionPane.OK_OPTION){
                    double cash = Double.parseDouble(cashAmount.getText());
                    controller1.count = cash;
                    controller1.addAction("compareToGetCount", database);

                    if(database.getCount() <= cash){
                        controller.addAction("Welcome", database);
                    }else{
                        JOptionPane.showMessageDialog(PaymentView.this,"Not enough, Please try again");
                    }
                }
            }
        });
        paymentPanel.add(total);
        paymentPanel.add(payCard);
        paymentPanel.add(payCash);
        add(paymentPanel);

        setSize(300, 150);
    }


    @Override
    public void setModel(Database model) {
        this.database = model;
    }

    @Override
    public void update() {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
