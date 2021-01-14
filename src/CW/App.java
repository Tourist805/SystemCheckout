package CW;

import CW.controller.IController;
import CW.view.*;
import CW.model.Database;

public class App {
    public static void main(String[] args){
        Database database = new Database();

        AdminLoginView adminLoginView = new AdminLoginView(database);

        KioskView kioskView = new KioskView(database);
        kioskView.setVisible(true);

        OrderView orderView = new OrderView(database);
        orderView.setVisible(true);

        PaymentView view = new PaymentView(database);
        view.setVisible(true);
    }
}
