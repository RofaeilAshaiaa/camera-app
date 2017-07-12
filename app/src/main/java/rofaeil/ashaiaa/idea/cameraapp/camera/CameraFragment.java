package rofaeil.ashaiaa.idea.cameraapp.camera;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flurgle.camerakit.CameraKit;
import com.flurgle.camerakit.CameraListener;

import java.io.File;

import rofaeil.ashaiaa.idea.cameraapp.R;
import rofaeil.ashaiaa.idea.cameraapp.databinding.FragmentMainBinding;
import rofaeil.ashaiaa.idea.cameraapp.util.Utils;
import rofaeil.ashaiaa.idea.cameraapp.viewimage.ViewImageActivity;

public class CameraFragment extends Fragment implements CameraContract.View {

    private FragmentMainBinding mBinding;
    private CameraContract.Presenter mPresenter;


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
                mPresenter.saveCapturedPhoto(jpeg);
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
    public void viewLastTakenImagePage(String mLastCapturedImagePath) {
        Intent intent = new Intent(getActivity(), ViewImageActivity.class);
        intent.putExtra(getString(R.string.last_selected_image_path), mLastCapturedImagePath);
        startActivity(intent);
    }

    @Override
//    public void toggleCamera(int cameraType) {
    public void toggleCamera() {

        mBinding.cameraView.toggleFacing();

    }

    @Override
    public void takePhoto() {
        mBinding.cameraView.captureImage();
    }

    @Override
    public void showNoImageToast() {
        Toast.makeText(getActivity(), getString(R.string.no_images_yet), Toast.LENGTH_SHORT).show();
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
        switch (seconds) {
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
        if (seconds == 0) return;
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

    @Override
    public boolean isFileExists(String filePath) {
        File file = new File(filePath);
        if (file.exists())
            return true;
        else
            return false;
    }


}
