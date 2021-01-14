package CW.controller;

import CW.model.Database;
import CW.model.IModel;
import CW.view.*;

import javax.swing.*;
import java.io.File;

public class AllViewsController extends AbstractController {
    private AdminLoginView loginView;
    private AdminWorkView workView;
    private KioskView kioskView;
    private OrderView orderView;
    private PaymentView paymentView;
    private Welcome welcome;

    public AllViewsController(Database database, AbstractView view) {
        super(database, view);
    }


    public void FormOpening(String view, Database database){
        switch (view){
            case "AdminLoginView":
                loginView = new AdminLoginView(database);
                break;
            case "AdminWorkView":
                workView = new AdminWorkView(database);
                break;
            case "KioskView":
                kioskView = new KioskView(database);
                break;
            case "OrderView":
                orderView = new OrderView(database);
                break;
            case "PaymentView":
                paymentView = new PaymentView(database);
                break;
            case "Welcome":
                welcome = new Welcome();
                break;
        }
    }

    public void GoToNewForm(JFrame oldOne, JFrame newOne){
        oldOne.setVisible(false);

        newOne.setVisible(true);
    }

    @Override
    public void addAction(String action, Database database) {
        FormOpening(action, database);
    }

    @Override
    public void removeAction() {

    }
}
