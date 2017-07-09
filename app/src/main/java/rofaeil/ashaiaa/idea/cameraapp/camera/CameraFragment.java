package rofaeil.ashaiaa.idea.cameraapp.camera;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flurgle.camerakit.CameraKit;
import com.flurgle.camerakit.CameraListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import rofaeil.ashaiaa.idea.cameraapp.R;
import rofaeil.ashaiaa.idea.cameraapp.databinding.FragmentMainBinding;
import rofaeil.ashaiaa.idea.cameraapp.util.Utils;

import static rofaeil.ashaiaa.idea.cameraapp.util.Utils.TAG;

public class CameraFragment extends Fragment implements CameraContract.View {

    private FragmentMainBinding mBinding;
    private CameraContract.Presenter mPresenter;
    private Bitmap mCapturedPhoto;


    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_main, container, false);
        setClickListenersOnViews();
        Utils.verifyStoragePermissions(getActivity());
        return mBinding.getRoot();
    }

    private void setClickListenersOnViews() {

        mBinding.ivHomeGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.openGallery();
            }
        });

        mBinding.ivToggleCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.changeCamera();

            }
        });

        mBinding.cameraView.setCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);
                mCapturedPhoto = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
                mPresenter.saveCapturedPhoto();
            }
        });

        mBinding.ivTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.takePhoto();
            }
        });

        mBinding.ivToggleGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.changeGridLines();
            }
        });

        mBinding.ivToggleTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.changeTimer();
            }
        });

        mBinding.ivToggleFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.ChangeFlashLight();
            }
        });

        mBinding.ivViewLastImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.viewLastTakenImage();
            }
        });
    }

    @Override
    public void onPause() {
        mBinding.cameraView.stop();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.cameraView.start();
        mPresenter.start();
    }

    @Override
    public void setPresenter(CameraContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void changeGridLinesIcon(boolean Enabled) {

        if (Enabled)
            mBinding.ivToggleGrid.setImageResource(R.drawable.ic_grid_on_orange_500_24dp);

        else
            mBinding.ivToggleGrid.setImageResource(R.drawable.ic_grid_on_black_24dp);

    }

    @Override
    public void changeFlashIcon(FlashStatus flashStatus) {
        switch (flashStatus) {
            case FLASH_ON:
                mBinding.ivToggleFlash.setImageResource(R.drawable.ic_flash_on_black_24dp);
                break;

            case FLASH_OFF:
                mBinding.ivToggleFlash.setImageResource(R.drawable.ic_flash_default_black_24dp);

                break;

            case FLASH_AUTO:
                mBinding.ivToggleFlash.setImageResource(R.drawable.ic_flash_auto_black_24dp);
                break;
        }

    }

    @Override
    public void changeTimerIcon(int Seconds) {

        switch (Seconds) {
            case 0:
                mBinding.ivToggleTimer.setImageResource(R.drawable.ic_access_time_black_24dp);
                break;

            case 3:
                mBinding.ivToggleTimer.setImageResource(R.drawable.ic_timer_3_white_24dp);

                break;

            case 10:
                mBinding.ivToggleTimer.setImageResource(R.drawable.ic_timer_10_white_24dp);
                break;
        }
    }

    @Override
    public void showTimerSeconds() {
        mBinding.ivTimerSeconds.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTimerSeconds() {
        mBinding.ivTimerSeconds.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toggleFlashLight(FlashStatus flashStatus) {
        switch (flashStatus) {
            case FLASH_ON:
                mBinding.cameraView.setFlash(CameraKit.Constants.FLASH_ON);
                break;

            case FLASH_OFF:
                mBinding.cameraView.setFlash(CameraKit.Constants.FLASH_OFF);
                break;

            case FLASH_AUTO:
                mBinding.cameraView.setFlash(CameraKit.Constants.FLASH_AUTO);
                break;

//            case FLASH_TORCH:
//                mBinding.cameraView.setFlash(CameraKit.Constants.FLASH_TORCH);
//                break;
        }
    }

    @Override
    public void enableGridLines() {
        mBinding.gridLinesContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void disableGridLines() {
        mBinding.gridLinesContainer.setVisibility(View.INVISIBLE);

    }

    @Override
    public void viewLastTakenImagePage() {

    }

    @Override
    public void toggleCamera() {

        mBinding.cameraView.toggleFacing();

//        switch (cameraType){
//
//            case BACK_CAMERA:
//                break;
//
//            case FRONT_CAMERA:
//
//                break;
//        }
    }

    @Override
    public void takePhoto() {
        mBinding.cameraView.captureImage();
    }

    @Override
    public void saveCapturedPhoto() {

//        SaveImageFile(CreateImageFile());

//        AsyncTaskLoader<String> loader = new AsyncTaskLoader<String>(getActivity()) {
//            @Override
//            public String loadInBackground() {
//
//                SaveImageFile( CreateImageFile());
//
//                return null;
//            }
//        };
//
//        loader.forceLoad();


        new AsyncTask<Void, Void, Void>()
        {@Override
        protected Void doInBackground(Void... params) {

            SaveImageFile( CreateImageFile());


            return null;
        }

        }.execute((Void[])null);

        mPresenter.resetTimer();
    }

    @Override
    public void openGalleryPage() {

    }

    @Override
    public void toggleTimer(int Seconds) {
        switch (Seconds) {
            case 0:
                hideTimerSeconds();
                break;

            case 3:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.ic_timer_3_white_24dp);
                showTimerSeconds();
                break;

            case 10:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.ic_timer_10_white_24dp);
                showTimerSeconds();
                break;
        }
    }

    @Override
    public void TimerCountDown(int seconds) {
        switch (seconds){
            case 9:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.nine);
                break;
            case 8:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.eight);
                break;
            case 7:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.seven);
                break;
            case 6:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.six);
                break;
            case 5:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.five);
                break;
            case 4:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.four);
                break;
            case 3:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.three);
                break;
            case 2:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.two);
                break;
            case 1:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.one);
                break;
        }
    }

    @Override
    public void restTimerCounter(int seconds) {
        switch (seconds) {
            case 3:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.ic_timer_3_white_24dp);
                showTimerSeconds();
                break;

            case 10:
                mBinding.ivTimerSeconds.setImageResource(R.drawable.ic_timer_10_white_24dp);
                showTimerSeconds();
                break;
        }
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
