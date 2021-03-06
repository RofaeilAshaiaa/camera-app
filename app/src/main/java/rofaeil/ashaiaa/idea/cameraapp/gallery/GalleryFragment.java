package rofaeil.ashaiaa.idea.cameraapp.gallery;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import rofaeil.ashaiaa.idea.cameraapp.R;
import rofaeil.ashaiaa.idea.cameraapp.camera.CameraActivity;
import rofaeil.ashaiaa.idea.cameraapp.data.Image;
import rofaeil.ashaiaa.idea.cameraapp.databinding.FragmentGalleryBinding;
import rofaeil.ashaiaa.idea.cameraapp.util.Utils;
import rofaeil.ashaiaa.idea.cameraapp.viewimage.ViewImageActivity;


public class GalleryFragment extends Fragment
        implements GalleryContract.View, GalleryAdapter.ListItemClickListener {

    private FragmentGalleryBinding mBinding;
    private GalleryContract.Presenter mPresenter;
    private GalleryActivity mGalleryActivity;
    private boolean isEditPhotoEnabled = false;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mGalleryActivity = (GalleryActivity) getActivity();
        boolean hasExtra = mGalleryActivity.getIntent()
                .hasExtra(getString(R.string.is_opened_from_widget));
        if (hasExtra){
            isEditPhotoEnabled =  mGalleryActivity.getIntent()
                    .getBooleanExtra(getString(R.string.is_opened_from_widget) ,false);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false);
        mGalleryActivity.setSupportActionBar(mBinding.toolbar);
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        if(isEditPhotoEnabled) mPresenter.editedPhotoClicked();
    }

    @Override
    public void setAdapterWithDataToRecyclerView(ArrayList<Image> mImages) {

        GalleryAdapter galleryAdapter = new GalleryAdapter(mImages, mGalleryActivity ,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mGalleryActivity, 3);
        mBinding.rvGallery.setLayoutManager(gridLayoutManager);
        mBinding.rvGallery.setItemAnimator(new SlideInLeftAnimator());
        mBinding.rvGallery.setAdapter(galleryAdapter);
        mBinding.rvGallery.setHasFixedSize(true);;


    }

    @Override
    public void openCameraPage() {
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        startActivity(intent);
    }

    @Override
    public void switchToEditPhotoMode() {
        mBinding.toolbar.setTitle(getString(R.string.toolbar_title_gallery_edit_mode));
        mBinding.toolbar.setTitleTextColor(
                ContextCompat.getColor(mGalleryActivity, R.color.orange_900));

    }

    @Override
    public void switchToGalleryMode() {
        mBinding.toolbar.setTitle(getString(R.string.toolbar_title_gallery));
        mBinding.toolbar.setTitleTextColor(
                ContextCompat.getColor(mGalleryActivity, R.color.colorPrimaryDark));
    }

    @Override
    public void openEditPage(Image image) {
        Utils.openEditPhotoPage(mGalleryActivity,image.getImagePath());
    }

    @Override
    public void openViewImagePage(Image image) {
        Intent intent = new Intent(getActivity(), ViewImageActivity.class);
        intent.putExtra(getString(R.string.last_selected_image_id), image.getImageId());
        startActivity(intent);
    }

    @Override
    public void setPresenter(GalleryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_gallry , menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera_page:
                mPresenter.cameraIconSelected();
                return true;
            case R.id.edit_image:
                mPresenter.editedPhotoClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void ListItemClicked(int position) {
        mPresenter.onImageClickListener(position);
    }
}
