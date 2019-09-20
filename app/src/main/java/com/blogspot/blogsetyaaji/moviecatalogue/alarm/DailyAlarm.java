package com.blogspot.blogsetyaaji.moviecatalogue.alarm;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.blogspot.blogsetyaaji.moviecatalogue.BuildConfig;
import com.blogspot.blogsetyaaji.moviecatalogue.R;
import com.blogspot.blogsetyaaji.moviecatalogue.activity.MainActivity;
import com.blogspot.blogsetyaaji.moviecatalogue.model.movie.MovieItem;
import com.blogspot.blogsetyaaji.moviecatalogue.model.movie.ResponseMovie;
import com.blogspot.blogsetyaaji.moviecatalogue.network.ApiClient;
import com.blogspot.blogsetyaaji.moviecatalogue.network.ApiInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DailyAlarm extends BroadcastReceiver {
    static final String CHANNEL_ID = "channel1";
    private static final CharSequence CHANNEL_NAME = "movie channel";
    private final int ID_REPEATING = 101;

    private int idNotif = 0;

    public void setDailyAlarm(Context context) {
        cancelDailyAlarm(context);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, DailyAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        } else {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, getPedingIntent(context));
        }

        Toast.makeText(context, R.string.daily_set, Toast.LENGTH_SHORT).show();
    }

    public void cancelDailyAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (alarmManager != null) {
            alarmManager.cancel(getPedingIntent(context));
            Log.d(TAG, "cancelDailyAlarm: exiting alarm canceled !");
        }
    }

    private PendingIntent getPedingIntent(Context context) {
        Intent intent = new Intent(context, DailyAlarm.class);
        return PendingIntent.getBroadcast(context, ID_REPEATING, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        shoNotificaton(context);
        Log.d(TAG, "onReceiveDailyAlarm: masuk");
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void shoNotificaton(Context context) {
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, ID_REPEATING, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String notifTitle = context.getString(R.string.app_name);
        String notifText = context.getString(R.string.daily_reminder_text);

        Notification.Builder builder = new Notification.Builder(context)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.ic_notif_app))
                .setSmallIcon(R.drawable.ic_notif_app)
                .setContentTitle(notifTitle)
                .setContentText(notifText)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId(CHANNEL_ID);
            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }

        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(idNotif, builder.build());
        }
    }
}



