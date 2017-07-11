package rofaeil.ashaiaa.idea.cameraapp.viewimage;


import rofaeil.ashaiaa.idea.cameraapp.BasePresenter;
import rofaeil.ashaiaa.idea.cameraapp.BaseView;

public interface ViewImageContract {

    interface View extends BaseView<Presenter>{

        void sharePhoto();
        void deletePhoto();
        void editPhoto();

    }
    interface Presenter extends BasePresenter{

        void sharePhoto();
        void deletePhoto();
        void editPhoto();

    }
}
