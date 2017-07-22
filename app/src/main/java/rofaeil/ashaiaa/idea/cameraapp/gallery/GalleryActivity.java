package rofaeil.ashaiaa.idea.cameraapp.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rofaeil.ashaiaa.idea.cameraapp.R;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_acivity);

        GalleryFragment cameraFragment =
                (GalleryFragment) getSupportFragmentManager().findFragmentById(R.id.gallery_fragment);

        new GalleryPresenter(cameraFragment ,this);
    }
}
