package rofaeil.ashaiaa.idea.cameraapp.data.local.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.AUTHORITY;
import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.IMAGES_PATH;
import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.COLUMN_IMAGE_ID;
import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.TABLE_NAME;


public class ImagesProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private ImagesDbHelper mDbHelper;
    private static final int IMAGES = 100;
    private static final int IMAGE_ID = 101;

    static {
        sUriMatcher.addURI(AUTHORITY, IMAGES_PATH, IMAGES);
        sUriMatcher.addURI(AUTHORITY, IMAGES_PATH + "/*", IMAGE_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new ImagesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @NonNull String[] projection, @NonNull String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case IMAGES:
                cursor = db.query(TABLE_NAME, projection,  selection, selectionArgs,
                        null,  null,   sortOrder );
                break;
            case IMAGE_ID:
                selection = COLUMN_IMAGE_ID + "=?";
                cursor = db.query(TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("can't query unknown Uri" + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {

            case IMAGES:

                long rowId = db.insert(TABLE_NAME, null, contentValues);
                return ContentUris.withAppendedId(uri, rowId);

            default:
                throw new IllegalArgumentException("can't query unknown Uri" + uri);

        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {

            case IMAGE_ID:
                selection = COLUMN_IMAGE_ID + "=?";
                return  db.delete(TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("can't query unknown Uri" + uri);

        }

    }

    @Override
    public int update(@NonNull Uri uri, @NonNull ContentValues values,
                      @NonNull String selection, @NonNull String[] selectionArgs) {

        return 0;
    }
}
