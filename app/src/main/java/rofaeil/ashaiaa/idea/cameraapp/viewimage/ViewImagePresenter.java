package rofaeil.ashaiaa.idea.cameraapp.viewimage;


public class ViewImagePresenter implements ViewImageContract.Presenter {

    private ViewImageContract.View mViewImageFragment;
    public ViewImagePresenter(ViewImageFragment fragment) {
        mViewImageFragment = fragment;
        fragment.setPresenter(this);
    }

    @Override
    public void start() {
        getImagePath();
    }

    @Override
    public void sharePhoto() {
        mViewImageFragment.sharePhoto();
    }

    @Override
    public void deletePhoto() {
        mViewImageFragment.deletePhoto();
    }

    @Override
    public void editPhoto() {
        mViewImageFragment.editPhoto();
    }

    @Override
    public void getImagePath() {
        mViewImageFragment.getImagePath();
    }

    @Override
    public void setImageToView(String imagePath) {
        mViewImageFragment.setImageToView(imagePath);
    }
}
