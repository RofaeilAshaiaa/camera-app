package rofaeil.ashaiaa.idea.cameraapp.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import rofaeil.ashaiaa.idea.cameraapp.R;
import rofaeil.ashaiaa.idea.cameraapp.data.local.sharedprefrences.CameraSettingsSharedPreferences;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set Full Screen Mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        CameraFragment cameraFragment =
                (CameraFragment) getSupportFragmentManager().findFragmentById(R.id.camera_fragment);
//
//        if(cameraFragment == null){
//            cameraFragment= CameraFragment.newInstance();
//            Utils.addFragmentToActivity(getSupportFragmentManager(),
//                    cameraFragment,R.id.fragment_container);
//        }

        CameraSettingsSharedPreferences preferences =
                new CameraSettingsSharedPreferences(this);

//        new CameraPresenter(cameraFragment, CameraSettingsSharedPreferences.newInstance(this) );
        new CameraPresenter(cameraFragment,CameraSettingsSharedPreferences.newInstance(this) );
    }

}
