package rofaeil.ashaiaa.idea.cameraapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import rofaeil.ashaiaa.idea.cameraapp.R;
import rofaeil.ashaiaa.idea.cameraapp.gallery.GalleryActivity;

/**
 * Implementation of App Widget functionality.
 */
public class EditPhotoAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Create an Intent to launch Activity
        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putExtra(context.getString(R.string.is_opened_from_widget), true);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.edit_photo_app_widget);

        views.setOnClickPendingIntent(R.id.appwidget_photo , pendingIntent);

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

