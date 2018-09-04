package ph.codegum.pushnotification.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ph.codegum.pushnotification.Constant;
import ph.codegum.pushnotification.R;
import ph.codegum.pushnotification.factory.NotificationFactory;

/**
 * Created by: Ian Parcon
 * Date created: Jul 05, 2018
 * Time created: 9:16 AM
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification());
        }
    }

    private void sendNotification(RemoteMessage.Notification notification) {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.finish_na);
        if (notification.getTitle() != null && notification.getTitle().equalsIgnoreCase(Constant.ANNOUNCEMENT_TOPIC))
            icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.advance_isip);

        Notification pushNotification = NotificationFactory.create(this, icon, defaultSoundUri, notification);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify((int) SystemClock.currentThreadTimeMillis(), pushNotification);
        }
    }

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        // registerTokenToServer(token);
    }
}