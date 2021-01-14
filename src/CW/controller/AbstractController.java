package CW.controller;

import CW.model.Database;
import CW.model.IModel;
import CW.view.AbstractView;

public abstract class AbstractController implements IController {
    protected Database database;
    private AbstractView view;

    public AbstractController(Database database,AbstractView view){
        this.database = database;
        this.view = view;
    }

    @Override
    public void setModel(Database database) {
        this.database = database;
    }

    @Override
    public void setView(AbstractView view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.show();
    }
}
