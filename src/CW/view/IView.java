package CW.view;

import CW.controller.AbstractController;
import CW.model.IModel;

public interface IView {
    void dispose();

    void setController(AbstractController controller);

    void setModel(IModel model);

    void show();

    void update();
}
