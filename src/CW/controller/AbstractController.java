package CW.controller;

import CW.model.IModel;
import CW.view.AbstractView;

public abstract class AbstractController implements IController {
    protected IModel model;
    private AbstractView view;

    public AbstractController(IModel model,AbstractView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
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
