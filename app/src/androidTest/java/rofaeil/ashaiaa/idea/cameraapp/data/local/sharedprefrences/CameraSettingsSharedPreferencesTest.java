package rofaeil.ashaiaa.idea.cameraapp.data.local.sharedprefrences;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import rofaeil.ashaiaa.idea.cameraapp.camera.FlashStatus;
import rofaeil.ashaiaa.idea.cameraapp.data.CameraSettings;

import static org.junit.Assert.*;


public class CameraSettingsSharedPreferencesTest {

    CameraSettingsSharedPreferences preferences  ;
    Context appContext;
    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();
        preferences = CameraSettingsSharedPreferences.newInstance(appContext);
    }

    @Test
    public void getCameraSettings() throws Exception {

        CameraSettings cameraSettings = preferences.getCameraSettings();
        assertEquals(cameraSettings.getFlashStatus(), FlashStatus.FLASH_OFF);

        preferences.ChangeFlashStatusValue(FlashStatus.FLASH_AUTO);
        CameraSettings cameraSettings2 = preferences.getCameraSettings();
        assertEquals(cameraSettings2.getFlashStatus() , FlashStatus.FLASH_AUTO);
    }

    @Test
    public void changeFlashStatusValue() throws Exception {
    }

    @Test
    public void changeGridLinesValue() throws Exception {
        CameraSettings cameraSettings = preferences.getCameraSettings();
        assertEquals(cameraSettings.isIsGridLinesEnabled() ,false);

        preferences.ChangeGridLinesValue(true);
        cameraSettings = preferences.getCameraSettings();
        assertEquals(cameraSettings.isIsGridLinesEnabled() , true);

    }

    @Test
    public void changeTimerSecondsValue() throws Exception {
    }

    @Test
    public void changeCameraTypeValue() throws Exception {
    }

    @Test
    public void newInstance() throws Exception {
    }

}