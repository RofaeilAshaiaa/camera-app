package rofaeil.ashaiaa.idea.cameraapp.data;


import android.net.Uri;

public class Image {

    private String ImageId;
    private String ImagePath;
    private Uri ImageUri;

    public Image() {
    }

    public Image(String imageId, String imagePath, Uri imageUri) {
        ImageId = imageId;
        ImagePath = imagePath;
        ImageUri = imageUri;
    }

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public Uri getImageUri() {
        return ImageUri;
    }

    public void setImageUri(Uri imageUri) {
        ImageUri = imageUri;
    }
}
