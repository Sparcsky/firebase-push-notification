package ph.codegum.pushnotification.factory;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import ph.codegum.pushnotification.Constant;
import ph.codegum.pushnotification.R;
import ph.codegum.pushnotification.activity.NotificationActivity;

/**
 * Created by: Ian Parcon
 * Date created: Jul 06, 2018
 * Time created: 2:23 PM
 */

public class NotificationFactory {

    public static Notification create(Context context, Bitmap icon, Uri sound, RemoteMessage.Notification notification) {
        Intent intent = new Intent(context, NotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Constant.NOTIF_TITLE, notification.getTitle());
        intent.putExtra(Constant.NOTIF_BODY, notification.getBody());

            PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) SystemClock.currentThreadTimeMillis(), intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "channel_id_123")
                .setLargeIcon(icon)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingIntent);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setSmallIcon(R.drawable.ic_launcher);
            notificationBuilder.setColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            notificationBuilder.setSmallIcon(R.drawable.ic_launcher);
        }

        return notificationBuilder.build();
    }
}
