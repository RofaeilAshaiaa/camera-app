package rofaeil.ashaiaa.idea.cameraapp.gallery;

import java.util.ArrayList;

import rofaeil.ashaiaa.idea.cameraapp.BasePresenter;
import rofaeil.ashaiaa.idea.cameraapp.BaseView;
import rofaeil.ashaiaa.idea.cameraapp.data.Image;

public interface GalleryContract {

    interface View extends BaseView<Presenter> {

        void setAdapterWithDataToRecyclerView(ArrayList<Image> mImages);

        void openCameraPage();

        void switchToEditPhotoMode();

        void switchToGalleryMode();

        void openEditPage(Image image);

        void openViewImagePage(Image image);
    }

    interface Presenter extends BasePresenter {

        void cameraIconSelected();

        void editedPhotoClicked();

        void onImageClickListener(int position);
    }
}
