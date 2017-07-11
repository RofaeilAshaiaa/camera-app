package rofaeil.ashaiaa.idea.cameraapp.viewimage;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;

import java.io.File;

import rofaeil.ashaiaa.idea.cameraapp.R;
import rofaeil.ashaiaa.idea.cameraapp.databinding.FragmentViewImageBinding;


public class ViewImageFragment extends Fragment implements ViewImageContract.View {

    private ViewImageContract.Presenter mPresenter;
    private FragmentViewImageBinding mBinding;
    private String mLastCapturedImagePath;
    private ViewImageActivity mImageActivity;

    public ViewImageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mImageActivity = (ViewImageActivity) getActivity();
        boolean hasExtra = mImageActivity.getIntent()
                .hasExtra(getString(R.string.last_selected_image_path));
        if(hasExtra)
        mLastCapturedImagePath = mImageActivity.getIntent()
                .getStringExtra(getString(R.string.last_selected_image_path));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_view_image, container, false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setAlpha(0.9f);
        mImageActivity.setSupportActionBar(toolbar);
        mBinding.imageView.setImage(ImageSource.uri(Uri.parse(mLastCapturedImagePath)));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_view_image, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share_photo:
                mPresenter.sharePhoto();
                return true;
            case R.id.delete_image:
                mPresenter.deletePhoto();
                return true;
            case R.id.edit_image:
                mPresenter.editPhoto();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void setPresenter(ViewImageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void sharePhoto() {

    }

    @Override
    public void deletePhoto() {

        File file = new File(mLastCapturedImagePath);
        boolean status = file.delete() ;
        if (status){
            mImageActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(mLastCapturedImagePath))));
            Toast.makeText(mImageActivity, getString(R.string.photo_deleted_toast), Toast.LENGTH_SHORT).show();
            mImageActivity.finish();

        }


    }

    @Override
    public void editPhoto() {

    }
}