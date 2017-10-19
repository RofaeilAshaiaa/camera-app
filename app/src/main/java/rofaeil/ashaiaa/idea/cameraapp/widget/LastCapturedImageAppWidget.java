package rofaeil.ashaiaa.idea.cameraapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;

import rofaeil.ashaiaa.idea.cameraapp.R;

import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.COLUMN_IMAGE_PATH;
import static rofaeil.ashaiaa.idea.cameraapp.data.local.database.ImagesContract.ImagesEntry.CONTENT_URI;

/**
 * Implementation of App Widget functionality.
 */
public class LastCapturedImageAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.last_captured_image_app_widget);

        Cursor cursor = context.getContentResolver().query(CONTENT_URI, null, null, null,null );

        if (cursor != null){

            cursor.moveToLast();
            String lastCapturedImagePath = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH));
            views.setImageViewUri(R.id.appwidget_image_view , Uri.parse(lastCapturedImagePath));
            cursor.close();
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

