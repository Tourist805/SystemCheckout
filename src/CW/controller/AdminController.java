package CW.controller;

import CW.model.Admin;
import CW.model.Database;
import CW.model.IModel;
import CW.model.StockItem;
import CW.view.AbstractView;
import CW.view.AdminLoginView;
import CW.view.IView;

import java.io.File;

public class AdminController extends AbstractController  {
    private Admin admin;
    private StockItem currentItem;
    private AdminLoginView adminLoginView;


    public AdminController(Database database, AbstractView view){
        super(database, view);


    }

    @Override
    public void addAction(String action, Database database) {

    }

    @Override
    public void removeAction() {

    }
}
