package CW;

import CW.controller.IController;
import CW.view.AdminWorkView;
import CW.view.KioskView;
import CW.model.Database;
import CW.view.AdminLoginView;
import CW.view.OrderView;

public class App {
    public static void main(String[] args){
        Database database = new Database();

        AdminLoginView adminLoginView = new AdminLoginView(database);


        KioskView kioskView = new KioskView(database);
        kioskView.setVisible(true);

        OrderView orderView = new OrderView(database);
        orderView.setVisible(true);

        AdminWorkView adminWorkView = new AdminWorkView(database);
        adminWorkView.setVisible(true);
    }
}
