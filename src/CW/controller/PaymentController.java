package CW.controller;

import CW.model.Database;
import CW.view.AbstractView;

public class PaymentController extends  AbstractController {
    public double count;
    public PaymentController(Database database, AbstractView view) {
        super(database, view);
    }

    private void setCount(double count){
        this.count = count;
    }

    @Override
    public void addAction(String action, Database database) {
        switch (action){
            case "compareToGetCount":
                database.compareToGetCount(count);
        }
    }

    @Override
    public void removeAction() {

    }
}
