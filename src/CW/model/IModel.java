package CW.model;

import CW.view.IView;

public interface IModel {
    void registerObserver(IView view);

    void removeObserver(IView view);

    void notifyObservers();


}
