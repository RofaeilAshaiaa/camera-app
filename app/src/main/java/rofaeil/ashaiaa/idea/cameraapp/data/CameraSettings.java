package rofaeil.ashaiaa.idea.cameraapp.data;


import rofaeil.ashaiaa.idea.cameraapp.camera.CameraType;
import rofaeil.ashaiaa.idea.cameraapp.camera.FlashStatus;

public class CameraSettings {

    private FlashStatus mFlashStatus;
    private boolean mIsGridLinesEnabled;
    private int mTimerSeconds;
    private CameraType mCameraType;

    public CameraSettings() {
    }

    public FlashStatus getFlashStatus() {
        return mFlashStatus;
    }

    public void setFlashStatus(FlashStatus mFlashStatus) {
        this.mFlashStatus = mFlashStatus;
    }

    public boolean isIsGridLinesEnabled() {
        return mIsGridLinesEnabled;
    }

    public void setIsGridLinesEnabled(boolean mIsGridLinesEnabled) {
        this.mIsGridLinesEnabled = mIsGridLinesEnabled;
    }

    public int getTimerSeconds() {
        return mTimerSeconds;
    }

    public void setTimerSeconds(int mTimerSeconds) {
        this.mTimerSeconds = mTimerSeconds;
    }

    public CameraType getCameraType() {
        return mCameraType;
    }

    public void setCameraType(CameraType mCameraType) {
        this.mCameraType = mCameraType;
    }
}
