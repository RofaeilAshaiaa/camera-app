package rofaeil.ashaiaa.idea.cameraapp.camera;

import rofaeil.ashaiaa.idea.cameraapp.BasePresenter;
import rofaeil.ashaiaa.idea.cameraapp.BaseView;
import rofaeil.ashaiaa.idea.cameraapp.data.CameraSettings;

public interface CameraContract {

    interface View extends BaseView<Presenter>{

        void changeGridLinesIcon(boolean Enabled);
        void changeFlashIcon(FlashStatus flashStatus);
        void changeTimerIcon( int Seconds);
        void showTimerSeconds();
        void hideTimerSeconds();
        void toggleFlashLight(FlashStatus flashStatus);
        void enableGridLines();
        void disableGridLines();
        void viewLastTakenImagePage();
        void toggleCamera();
        void takePhoto();
        void saveCapturedPhoto();
        void openGalleryPage();
        void toggleTimer(int Seconds);
        void TimerCountDown(int seconds);
        void restTimerCounter(int seconds);
    }

    interface Presenter extends BasePresenter {

        void ChangeFlashLight();
        void changeGridLines();
        void viewLastTakenImage();
        void changeCamera();
        void takePhoto();
        void openGallery();
        void changeTimer();
        void saveCapturedPhoto();
        void setCameraSettings();
        void resetTimer();
    }
}
