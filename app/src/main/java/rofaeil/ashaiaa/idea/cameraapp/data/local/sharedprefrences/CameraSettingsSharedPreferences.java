package rofaeil.ashaiaa.idea.cameraapp.data.local.sharedprefrences;


import android.content.Context;
import android.content.SharedPreferences;

import rofaeil.ashaiaa.idea.cameraapp.R;
import rofaeil.ashaiaa.idea.cameraapp.camera.CameraType;
import rofaeil.ashaiaa.idea.cameraapp.camera.FlashStatus;
import rofaeil.ashaiaa.idea.cameraapp.data.CameraSettings;

import static rofaeil.ashaiaa.idea.cameraapp.camera.CameraType.BACK_CAMERA;
import static rofaeil.ashaiaa.idea.cameraapp.camera.CameraType.FRONT_CAMERA;
import static rofaeil.ashaiaa.idea.cameraapp.camera.FlashStatus.FLASH_AUTO;
import static rofaeil.ashaiaa.idea.cameraapp.camera.FlashStatus.FLASH_OFF;
import static rofaeil.ashaiaa.idea.cameraapp.camera.FlashStatus.FLASH_ON;

public class CameraSettingsSharedPreferences {

    private static CameraSettingsSharedPreferences sCameraSettingsSharedPreferences;
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    public CameraSettingsSharedPreferences(Context mContext) {
        this.mContext = mContext;
    }

    private SharedPreferences getSharedPreferences() {
        if (mSharedPreferences == null)
            mSharedPreferences =
                    mContext.getSharedPreferences(mContext.getString(R.string.preferences_camera_settings), Context.MODE_PRIVATE);

        return mSharedPreferences;
    }

    private SharedPreferences.Editor getEditor() {
        if (mEditor == null) {
            mEditor = mSharedPreferences.edit();
        }
        return mEditor;
    }

    public CameraSettings getCameraSettings() {

        CameraSettings cameraSettings = new CameraSettings();

        String CameraType = getSharedPreferences().getString(
                mContext.getString(R.string.camera_type), "BACK_CAMERA");
        if (CameraType.matches("BACK_CAMERA"))
            cameraSettings.setCameraType(BACK_CAMERA);
        else
            cameraSettings.setCameraType(FRONT_CAMERA);

        String FlashStatus = getSharedPreferences().getString(
                mContext.getString(R.string.flash_Status), "FLASH_OFF");
        switch (FlashStatus) {
            case "FLASH_OFF":
                cameraSettings.setFlashStatus(FLASH_OFF);
                break;
            case "FLASH_ON":
                cameraSettings.setFlashStatus(FLASH_ON);
                break;
            case "FLASH_AUTO":
                cameraSettings.setFlashStatus(FLASH_AUTO);
                break;
        }

        boolean IsGridEnabled = getSharedPreferences().getBoolean(
                mContext.getString(R.string.is_grid_lines_enabled), false);
        cameraSettings.setIsGridLinesEnabled(IsGridEnabled);

        int TimerSeconds = getSharedPreferences().getInt(
                mContext.getString(R.string.timer_seconds), 0);
        cameraSettings.setTimerSeconds(TimerSeconds);

        return cameraSettings;
    }

    public void ChangeFlashStatusValue(FlashStatus flashStatus) {

        getEditor()
                .putString(mContext.getString(R.string.flash_Status), flashStatus.toString())
                .apply();

    }

    public void ChangeGridLinesValue(boolean status) {
        getEditor()
                .putBoolean(mContext.getString(R.string.is_grid_lines_enabled), status)
                .apply();
    }

    public void ChangeTimerSecondsValue(int timerSeconds) {
        getEditor()
                .putInt(mContext.getString(R.string.timer_seconds), timerSeconds)
                .apply();
    }

    public void ChangeCameraTypeValue(CameraType cameraType) {
        getEditor()
                .putString(mContext.getString(R.string.camera_type), cameraType.toString())
                .apply();

    }

    public static CameraSettingsSharedPreferences newInstance(Context context) {
        if (sCameraSettingsSharedPreferences == null) {
            sCameraSettingsSharedPreferences = new CameraSettingsSharedPreferences(context);
        }

        return sCameraSettingsSharedPreferences;
    }

    public void setLastCapturedImageUri(String imageUri){
        getEditor()
                .putString(mContext.getString(R.string.last_selected_image_path), imageUri)
                .apply();
    }

    public String getLastCapturedImageUri(){
       return getSharedPreferences()
                .getString(mContext.getString(R.string.last_selected_image_path),null );
    }
}
