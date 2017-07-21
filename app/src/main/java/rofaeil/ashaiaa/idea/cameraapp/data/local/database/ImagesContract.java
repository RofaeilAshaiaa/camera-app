package rofaeil.ashaiaa.idea.cameraapp.data.local.database;

import android.net.Uri;

import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.TABLE_NAME;

public class ImagesContract {

    public static final String AUTHORITY = "rofaeil.ashaiaa.idea.cameraapp";
    public static final String IMAGES_PATH = TABLE_NAME;
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class ImagesEntry  {

        public static final String TABLE_NAME = "images";
        public static final String COLUMN_IMAGE_ID = "images_id" ;
        public static final String COLUMN_IMAGE_PATH = "images_path";
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(IMAGES_PATH).build() ;


    }
}
