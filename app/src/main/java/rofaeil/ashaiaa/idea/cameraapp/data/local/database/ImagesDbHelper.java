package rofaeil.ashaiaa.idea.cameraapp.data.local.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.COLUMN_IMAGE_ID;
import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.COLUMN_IMAGE_PATH;
import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.TABLE_NAME;


public class ImagesDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Images.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_IMAGE_ID + " TEXT PRIMARY KEY," +
                    COLUMN_IMAGE_PATH + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public ImagesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
