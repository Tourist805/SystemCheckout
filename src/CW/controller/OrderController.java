package CW.controller;

import CW.model.Database;
import CW.view.KioskView;
import org.w3c.dom.views.AbstractView;

public class OrderController extends AbstractController{

    private Database database;
    private KioskView kioskView;

    public OrderController(Database database, AbstractView view){
        super(database, (CW.view.AbstractView) view);
    }

    @Override
    public void addAction() {

    }

    @Override
    public void removeAction() {

    }
}
