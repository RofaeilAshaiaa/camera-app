package rofaeil.ashaiaa.idea.cameraapp.camera;


import android.os.Handler;

import rofaeil.ashaiaa.idea.cameraapp.data.CameraSettings;
import rofaeil.ashaiaa.idea.cameraapp.data.local.sharedprefrences.CameraSettingsSharedPreferences;

public class CameraPresenter implements CameraContract.Presenter {

    private CameraSettings mCameraSettings;
    private final CameraContract.View mCameraFragment;
    private final CameraSettingsSharedPreferences mPreferences;

    public CameraPresenter(CameraContract.View mCameraFragment,
                           CameraSettingsSharedPreferences preferences) {
        this.mCameraFragment = mCameraFragment;
        mPreferences = preferences;
        if (mCameraFragment != null) {
            mCameraFragment.setPresenter(this);
        }
    }

    @Override
    public void start() {
        setCameraSettings();
    }

    @Override
    public void ChangeFlashLight() {


        switch (mCameraSettings.getFlashStatus()) {
            case FLASH_ON:
                mCameraSettings.setFlashStatus(FlashStatus.FLASH_OFF);

                break;

            case FLASH_OFF:
                mCameraSettings.setFlashStatus(FlashStatus.FLASH_AUTO);
                break;

            case FLASH_AUTO:
                mCameraSettings.setFlashStatus(FlashStatus.FLASH_ON);
                break;

//            case FLASH_TORCH:
//                mCameraSettings.setFlashStatus(FlashStatus.FLASH_TORCH);
//                break;
        }

        mCameraFragment.toggleFlashLight(mCameraSettings.getFlashStatus());
        mCameraFragment.changeFlashIcon(mCameraSettings.getFlashStatus());
        mPreferences.ChangeFlashStatusValue(mCameraSettings.getFlashStatus());
    }

    @Override
    public void changeGridLines() {

        boolean temp = mCameraSettings.isIsGridLinesEnabled();

        if (temp) {
            mCameraFragment.disableGridLines();
        } else {
            mCameraFragment.enableGridLines();
        }
        mCameraFragment.changeGridLinesIcon(!temp);
        mPreferences.ChangeGridLinesValue(!temp);
        mCameraSettings.setIsGridLinesEnabled(!temp);
    }

    @Override
    public void viewLastTakenImage() {

    }

    @Override
    public void changeCamera() {

        if (mCameraSettings.getCameraType() == CameraType.BACK_CAMERA)
            mCameraSettings.setCameraType(CameraType.FRONT_CAMERA);
        else
            mCameraSettings.setCameraType(CameraType.BACK_CAMERA);

        mCameraFragment.toggleCamera();
        mPreferences.ChangeCameraTypeValue(mCameraSettings.getCameraType());
    }

    @Override
    public void takePhoto() {

        if (mCameraSettings.getTimerSeconds() == 0) {
            mCameraFragment.takePhoto();
        } else {
            final Handler handler = new Handler();
            final int[] count = {mCameraSettings.getTimerSeconds()};
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mCameraFragment.TimerCountDown(count[0]);
                    handler.postDelayed(this, 1000);
                    if (count[0] - 1 == 0) {
                        mCameraFragment.takePhoto();
                    }
                    count[0]--;

                }
            });
        }

    }

    @Override
    public void openGallery() {

    }

    @Override
    public void changeTimer() {

        switch (mCameraSettings.getTimerSeconds()) {
            case 0:
                mCameraSettings.setTimerSeconds(3);
                break;

            case 3:
                mCameraSettings.setTimerSeconds(10);
                break;

            case 10:
                mCameraSettings.setTimerSeconds(0);
                break;
        }

        mCameraFragment.changeTimerIcon(mCameraSettings.getTimerSeconds());
        mCameraFragment.toggleTimer(mCameraSettings.getTimerSeconds());
        mPreferences.ChangeTimerSecondsValue(mCameraSettings.getTimerSeconds());

    }

    @Override
    public void saveCapturedPhoto() {
        mCameraFragment.saveCapturedPhoto();
    }

    @Override
    public void setCameraSettings() {
        mCameraSettings = mPreferences.getCameraSettings();
    }

    @Override
    public void resetTimer() {

        mCameraFragment.restTimerCounter(mCameraSettings.getTimerSeconds());
    }

}
