package rofaeil.ashaiaa.idea.cameraapp.gallery;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import java.io.File;
import java.util.ArrayList;

import rofaeil.ashaiaa.idea.cameraapp.data.Image;
import rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract;

import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.COLUMN_IMAGE_ID;
import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.COLUMN_IMAGE_PATH;

public class GalleryPresenter
        implements GalleryContract.Presenter , LoaderManager.LoaderCallbacks<Cursor>{

    private Context mContext;
    private GalleryContract.View mGalleryFragment;
    private static final int URL_LOADER = 0;
    private ArrayList<Image> mImages;
    private boolean mEditPhotoMode = false;

    public GalleryPresenter(GalleryFragment galleryFragment, Context context) {
        mContext = context;
        mGalleryFragment = galleryFragment;
        if (mGalleryFragment != null) {
            mGalleryFragment.setPresenter(this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext, ImagesContract.ImagesEntry.CONTENT_URI,
               new String[]{ ImagesContract.ImagesEntry.COLUMN_IMAGE_ID,
                       ImagesContract.ImagesEntry.COLUMN_IMAGE_PATH}, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        extractImages(cursor);

        mGalleryFragment.setAdapterWithDataToRecyclerView(mImages);
    }

    private void extractImages(Cursor cursor) {

        if (cursor != null) {
            mImages = new ArrayList<>(cursor.getCount());
            cursor.moveToFirst();
            do {
                Image image = new Image();
                String imageId = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_ID));
                image.setImageId(imageId);
                String imagePath = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH));
                image.setImagePath(imagePath);
                File file = new File(imagePath);
                image.setImageUri(Uri.fromFile(file));
                mImages.add(image);

            }while (cursor.moveToNext() );
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void start() {
//        Cursor cursor = mContext.getContentResolver()
//                .query(CONTENT_URI, new String[]{ ImagesContract.ImagesEntry.COLUMN_IMAGE_ID,
//                        ImagesContract.ImagesEntry.COLUMN_IMAGE_PATH}, null, null, null);
//        Log.d(TAG, "start: "+cursor.getCount());
//
//        cursor.moveToFirst();
//        String imagePath = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH));

//        extractImages(cursor);

//        Log.d(TAG, "start: "+mImages.size());
//        Log.d(TAG, "start: "+imagePath);

        ((GalleryActivity) mContext).getSupportLoaderManager().initLoader(URL_LOADER, null, this);
    }

    @Override
    public void cameraIconSelected() {
        mGalleryFragment.openCameraPage();
    }

    @Override
    public void editedPhotoClicked() {

        if (mEditPhotoMode){
            mEditPhotoMode = false;
            mGalleryFragment.switchToGalleryMode();
        }
        else{
            mEditPhotoMode = true;
            mGalleryFragment.switchToEditPhotoMode();
        }
    }

    @Override
    public void onImageClickListener(int position) {
        Image image = mImages.get(position);
        if(mEditPhotoMode){
            mGalleryFragment.openEditPage(image);
        }else {
            mGalleryFragment.openViewImagePage(image);
        }
    }
}
