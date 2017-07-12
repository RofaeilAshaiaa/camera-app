package rofaeil.ashaiaa.idea.cameraapp.camera;

import rofaeil.ashaiaa.idea.cameraapp.BasePresenter;
import rofaeil.ashaiaa.idea.cameraapp.BaseView;

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
        void viewLastTakenImagePage(String mLastCapturedImagePath);
        void toggleCamera();
        void takePhoto();
        void showNoImageToast();
        void openGalleryPage();
        void toggleTimer(int Seconds);
        void TimerCountDown(int seconds);
        void restTimerCounter(int seconds);
        boolean isFileExists(String filePath);
    }

    interface Presenter extends BasePresenter {

        void ChangeFlashLight();
        void changeGridLines();
        void viewLastTakenImage();
        void changeCamera();
        void takePhoto();
        void openGallery();
        void changeTimer();
        void saveCapturedPhoto(byte[] jpeg);
        void setCameraSettings();
        void resetTimer();
        void setLastSelectedSettings();
        void setLastCapturedPhotoUri(String imagePath);
    }
}
