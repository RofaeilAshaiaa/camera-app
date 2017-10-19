package rofaeil.ashaiaa.idea.cameraapp.viewimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import rofaeil.ashaiaa.idea.cameraapp.R;

public class ViewImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_view_image);

        ViewImageFragment fragment =
                (ViewImageFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.view_image_fragment);

        new ViewImagePresenter( fragment );
    }
}
