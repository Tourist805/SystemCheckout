package CW.controller;

import CW.model.Database;
import CW.model.IModel;
import CW.view.AbstractView;

import java.io.File;

public interface IController {
    void addAction(String action, Database database);

    void removeAction();

    void setModel(Database database);

    void setView(AbstractView view);

    void start();
}
