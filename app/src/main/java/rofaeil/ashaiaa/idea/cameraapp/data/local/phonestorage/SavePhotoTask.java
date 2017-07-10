package rofaeil.ashaiaa.idea.cameraapp.data.local.phonestorage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import rofaeil.ashaiaa.idea.cameraapp.camera.CameraPresenter;

import static rofaeil.ashaiaa.idea.cameraapp.util.Utils.TAG;

public class SavePhotoTask extends AsyncTask<Void,Void,Uri> {

    private byte[] jpeg ;
    private Bitmap mCapturedPhoto;
    private CameraPresenter mCameraPresenter;


    public SavePhotoTask(byte[] jpeg, CameraPresenter cameraPresenter) {
        this.jpeg = jpeg ;
        mCameraPresenter =cameraPresenter ;
    }

    @Override
    protected Uri doInBackground(Void... voids) {

        mCapturedPhoto = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
        File file = CreateImageFile() ;
        SaveImageFile(file );

        return  Uri.fromFile(file);
    }

    @Override
    protected void onPostExecute(Uri uri) {
        super.onPostExecute(uri);
        mCameraPresenter.photoSavedToStorage(uri);
    }

    public File CreateImageFile() {

        File imageFile = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                , "Sho Camera");
        String timestamp = new SimpleDateFormat(
                "yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String finalPath = imageFile.getPath() + "/" + timestamp + ".jpg";

        if (!imageFile.exists()) {
            imageFile.mkdirs();
        }

        return new File(finalPath);
    }

    public void SaveImageFile(File file) {
        if (file == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            mCapturedPhoto.compress(Bitmap.CompressFormat.PNG, 10, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
    }

}
