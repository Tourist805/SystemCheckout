package CW.controller;

import CW.model.Admin;
import CW.model.Database;
import CW.model.IModel;
import CW.model.StockItem;
import CW.view.AbstractView;
import CW.view.AdminLoginView;
import CW.view.IView;

public class AdminController extends AbstractController  {
    private Admin admin;
    private StockItem currentItem;
    private Database database;
    private AdminLoginView adminLoginView;


    public AdminController(IModel model, AbstractView view){
        super(model, view);


    }


    @Override
    public void addAction() {

    }

    @Override
    public void removeAction() {

    }
}
