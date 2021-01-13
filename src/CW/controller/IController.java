package CW.controller;

import CW.model.IModel;
import CW.view.AbstractView;

public interface IController {
    void addAction();

    void removeAction();

    void setModel(IModel model);

    void setView(AbstractView view);

    void start();
}
